package mycai.service;

import mycai.dao.ProductDao;
import mycai.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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

    @Transactional
    public List<Product> getLatest(int limit) {
        return productDao.getLatest(limit);
    }

    @Transactional
    public List<Product> getOnsaleList(int limit) {
        return productDao.getOnsaleList(limit);
    }

    @Transactional
    public void upsert(Product product) {
        product.setDataChangeLastTime(new Timestamp(System.currentTimeMillis()));
        productDao.saveOrUpdateByName(product);
    }

    @Transactional
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Transactional
    public Product getById(int id) {
        return productDao.getById(id);
    }

    @Transactional
    public void update(Product product) {
        Product productInDB = productDao.getById(product.getId());
        if (productInDB != null) {
            productInDB.setName(product.getName());
            productInDB.setDescription(product.getDescription());
            productInDB.setType(product.getType());
            productInDB.setCategory(product.getCategory());
            productInDB.setPrice(product.getPrice());
            productInDB.setUnit(product.getUnit());
            productInDB.setDataChangeLastTime(new Timestamp(System.currentTimeMillis()));
        }
        productDao.update(productInDB);
    }
}
