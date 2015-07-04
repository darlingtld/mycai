package mycai.dao;

import mycai.crawler.ImageCrawler;
import mycai.pojo.Category;
import mycai.pojo.Product;
import mycai.pojo.Type;
import mycai.service.ProductService;
import mycai.util.ImageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

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
            if (null != product.getPicurl() && product.getPicurl().contains("pic")) {
                continue;
            }
            String picUUID = product.generatePicurlHash();
            try {
                ImageCrawler.getProductImg(product, picUUID);
                product.setPicurl("images/" + picUUID + ".jpg");
                productService.upsert(product);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
//            break;
//
        }
    }

    @Test
    public void updateProductImageById() {
//        for (int i = 127; i < 212; i++) {
        try {
            Product product = productService.getById(211);
            String picUUID = product.generatePicurlHash();
            ImageCrawler.getProductImg(product, picUUID);
            product.setPicurl("images/" + picUUID + ".jpg");
            productService.upsert(product);
        } catch (Exception e) {
            e.printStackTrace();
//                continue;
        }
//        }
    }

    @Test
    public void importImages() {
        String imgSrcDir = "C:\\Users\\darlingtld\\IdeaProjects\\mycai_main\\src\\main\\webapp\\product_images";
        File file = new File(imgSrcDir);
        File[] imgFiles = file.listFiles();
        for (File imgFile : imgFiles) {
            System.out.println(imgFile.getName());
            Product product = new Product();
            product.setName(imgFile.getName().substring(0, imgFile.getName().indexOf(".")));
            product.setType(Type.SHUCAISHUIGUO);
            product.setCategory(Category.YECAILEI);
            product.setDescription("精选" + imgFile.getName().substring(0, imgFile.getName().indexOf(".")));
            product.setPrice(2.68);
            product.setUnit("斤");
            product.setPicurl("product_images/" + product.generatePicurlHash() + ".jpg");
            product.setDataChangeLastTime(new Timestamp(System.currentTimeMillis()));
            System.out.println(product);
            imgFile.renameTo(new File(imgSrcDir + "\\" + product.generatePicurlHash() + ".jpg"));
            productService.save(product);
//            break;
        }
    }
}

