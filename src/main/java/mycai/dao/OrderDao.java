package mycai.dao;

import mycai.pojo.Order;
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

    public List<Order> getList(String wechatid) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Order where wechatId = '%s' order by id desc", wechatid)).list();
    }

    public Order getById(int orderId) {
        return (Order) sessionFactory.getCurrentSession().get(Order.class, orderId);
    }

    public List<Order> getAll() {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Order order by orderTs desc")).list();

    }

    public boolean update(Order order) {
        try {
            sessionFactory.getCurrentSession().update(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> getLatestList(String wechatId, int count) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Order where wechatId = '%s' order by orderTs desc", wechatId)).setMaxResults(count).list();
    }


    public boolean isConfirmCodeExisted(String code) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Order where confirmCode = '%s'", code)).uniqueResult() == null ? false : true;
    }
}
