package mycai.controller;

import mycai.pojo.Product;
import mycai.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{type}/{category}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getList(@PathVariable("type") String type, @PathVariable("category") String category) {
        return productService.getList(type, category);
    }

    @RequestMapping(value = "/zuixincaipin/{limit}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getLatest(@PathVariable("limit") int limit) {
        return productService.getLatest(limit);
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
}