package mycai.dao;

import mycai.pojo.Order;
import mycai.pojo.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by darlingtld on 2015/5/16.
 */
@Repository
public class OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    public int save(Order order) {
        int id = (int) sessionFactory.getCurrentSession().save(order);
        return id;
    }

    public List<Order> getList(String userid) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Order where userid = '%s' order by id desc", userid)).list();
    }

    public Order getById(int orderId) {
        return (Order) sessionFactory.getCurrentSession().get(Order.class, orderId);
    }

    public List<Order> getAll() {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Order order by orderTs desc")).list();

    }
}
