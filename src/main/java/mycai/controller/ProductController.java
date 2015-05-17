package mycai.controller;

import mycai.pojo.Product;
import mycai.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}