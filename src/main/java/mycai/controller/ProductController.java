package mycai.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mycai.excel.ExcelFactory;
import mycai.pojo.Category;
import mycai.pojo.Procurement;
import mycai.pojo.Product;
import mycai.pojo.Type;
import mycai.service.ProductService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

//    @RequestMapping(value = "/{type}/{category}", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    List<Product> getList(@PathVariable("type") String type, @PathVariable("category") String category) {
//        return productService.getList(type, category);
//    }

    @RequestMapping(value = "/category/{category}/wechatid/{wechatid}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getList(@PathVariable("category") String category, @PathVariable("wechatid") String wechatid) {
        return productService.getListByFavourites(category, wechatid);
    }

    @RequestMapping(value = "/category/{category}/pinyin/asc", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getListByPy(@PathVariable("category") String category) {
        return productService.getListSortByPinyin(category, "name", "asc");
    }

    @RequestMapping(value = "/zuixincaipin/{limit}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getLatest(@PathVariable("limit") int limit) {
        return productService.getLatest(limit);
    }

    @RequestMapping(value = "/most_bought/{frequency}/wechatid/{wechatid}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getMostBought(@PathVariable("frequency") int frequency, @PathVariable("wechatid") String wechatid) {
        return productService.getMostBought(wechatid, frequency);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping(value = "/procurement/all", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Procurement> getProcurement() {
        return productService.getProcurement();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public
    @ResponseBody
    void create(@RequestBody Product product) {
        productService.save(product);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Content-Type=application/json")
     public
     @ResponseBody
     void update(@RequestBody Product product) {
        productService.update(product);
    }

    @RequestMapping(value = "/delete/{product_id}", method = RequestMethod.POST)
    public
    @ResponseBody
    void delete(@PathVariable("product_id") int productId) {
        productService.delete(productId);
    }

    @RequestMapping(value = "/type_map", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray getTypeMap() {
        JSONArray jsonArray = new JSONArray();
        Map<Type, List<Category>> typeMap = productService.getTypeMap();
        for (Type key : typeMap.keySet()) {
            List<Category> categoryList = typeMap.get(key);
            JSONArray categoryJsonArray = new JSONArray();
            for (Category category : categoryList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", category.getCategory());
                jsonObject.put("value", category.toString());
                categoryJsonArray.add(jsonObject);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", key.getType());
            jsonObject.put("value", key.toString());
            jsonObject.put("list", categoryJsonArray);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @RequestMapping("/export")
    public ResponseEntity<byte[]> download() throws IOException {
        String fileName = String.format("菜品列表%s.xlsx", new SimpleDateFormat("yyyyMMdd").format(new Date()));
        ExcelFactory.exportProducts(fileName, productService.getAll(), productService.getTypeMap(), productService.getProcurement());
        File file = new File(fileName);
        HttpHeaders headers = new HttpHeaders();
        String cnfileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", cnfileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}