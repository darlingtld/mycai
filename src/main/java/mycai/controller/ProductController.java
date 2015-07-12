package mycai.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mycai.pojo.Category;
import mycai.pojo.Product;
import mycai.pojo.Type;
import mycai.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
}