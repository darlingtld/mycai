package mycai.dao;

import mycai.pojo.Procurement;
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

    public Product getById(int id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }

    public Product getByName(String name) {
        return (Product) sessionFactory.getCurrentSession().createQuery(String.format("from Product where name = '%s'", name)).uniqueResult();
    }

    public void saveOrUpdateByName(Product product) {
        Product productInDB = getByName(product.getName());
        if (productInDB != null) {
            productInDB.setPrice(product.getPrice());
            productInDB.setPicurl(product.getPicurl());
            productInDB.setUnit(product.getUnit());
            productInDB.setDataChangeLastTime(product.getDataChangeLastTime());
            sessionFactory.getCurrentSession().saveOrUpdate(productInDB);
        } else {
            sessionFactory.getCurrentSession().save(product);
        }
    }

    public List<Product> getList(String type, String category) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Product where type = '%s' and category='%s'", type.toUpperCase(), category.toUpperCase())).list();
    }

    public List<Product> getLatest(int limit) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Product p order by p.dataChangeLastTime desc")).setMaxResults(limit).list();

    }

    public List<Product> getOnsaleList(int limit) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Product p where p.onsale = 1 order by p.dataChangeLastTime desc")).setMaxResults(limit).list();
    }

    public List<Product> getAll() {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Product")).list();
    }

    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    public List<Product> getList(String category) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Product where category='%s'", category.toUpperCase())).list();
    }

    public List<Procurement> getProcurement() {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Procurement")).list();
    }
}