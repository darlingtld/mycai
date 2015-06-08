package mycai.dao;

import mycai.crawler.ImageCrawler;
import mycai.pojo.Category;
import mycai.pojo.Product;
import mycai.service.ProductService;
import mycai.util.ImageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Properties;

/**
 * Created by darlingtld on 2015/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class ProductDaoTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testProductSave() {
        Product product = new Product();
        product.setName("青菜");
        product.setDescription("最好吃的青菜");
        product.setCategory(Category.YECAILEI);
        product.setPrice(10.9);
        int id = productService.save(product);
        System.out.println(id);
    }

    @Test
    public void updateProductImages() {
        List<Product> productList = productService.getAll();
        for (Product product : productList) {
//            ImageCrawler.getProductImg(product);
//            product.setPicurl();
            productService.upsert(product);
        }
    }


}
