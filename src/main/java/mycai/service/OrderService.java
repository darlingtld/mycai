package mycai.service;

import mycai.dao.OrderDao;
import mycai.dao.ProductDao;
import mycai.pojo.Order;
import mycai.pojo.Product;
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
    public List<Order> getList(String userid) {
        return orderDao.getList(userid);
    }
}
