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

    public List<Product> getList(String type, String category) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Product where type = '%s' and category='%s'", type.toUpperCase(), category.toUpperCase())).list();
    }

    public List<Product> getLatest(int limit) {
        return sessionFactory.getCurrentSession().createQuery("from Product p order by p.dataChangeLastTime desc").setMaxResults(limit).list();

    }

    public List<Product> getOnsaleList(int limit) {
        return sessionFactory.getCurrentSession().createQuery("from Product p where p.onsale = 1 order by p.dataChangeLastTime desc").setMaxResults(limit).list();
    }
}
