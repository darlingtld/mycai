package mycai.service;

import mycai.dao.OrderDao;
import mycai.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by darlingtld on 2015/5/16.
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public int save(Order order) {
        return orderDao.save(order);
    }

    @Transactional
    public List<Order> getList(String wechatid) {
        return orderDao.getList(wechatid);
    }

    @Transactional
    public Order getById(int orderId) {
        return orderDao.getById(orderId);
    }

    @Transactional
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Transactional
    public boolean update(Order order) {
        Order orderInDb=getById(order.getId());
        orderInDb.setDeliveryTs(order.getDeliveryTs());
        orderInDb.setStatus(order.getStatus());
        return orderDao.update(orderInDb);
    }
}
