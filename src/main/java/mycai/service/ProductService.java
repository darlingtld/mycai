package mycai.service;

import mycai.dao.ProductDao;
import mycai.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by darlingtld on 2015/5/16.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Transactional
    public int save(Product product) {
        return productDao.save(product);
    }

    @Transactional
    public List<Product> getList(String type, String category) {
        return productDao.getList(type, category);
    }
}
