package mycai.dao;

import mycai.pojo.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by darlingtld on 2015/5/16.
 */
@Repository
public class ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    public int save(Product product) {
        int id = (int) sessionFactory.getCurrentSession().save(product);
        return id;
    }

    public List<Product> getList(String category) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Product where category='%s'", category.toUpperCase())).list();
    }
}
