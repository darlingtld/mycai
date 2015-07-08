package mycai.service;

import mycai.dao.OrderDao;
import mycai.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * Created by darlingtld on 2015/5/16.
 */
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public int save(Order order) {
        String code = generateConfirmCode();
        while (isConfirmCodeExisted(code)) {
            code = generateConfirmCode();
        }
        order.setConfirmCode(code);
        return orderDao.save(order);
    }

    @Transactional
    private boolean isConfirmCodeExisted(String code) {
        return orderDao.isConfirmCodeExisted(code);
    }

    private String generateConfirmCode() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        String value = String.valueOf(random.nextInt(1000000000));
        StringBuffer code = new StringBuffer(value);
        for (int i = 0; i < 9 - value.length(); i++) {
            code.insert(0, "0");
        }
        return code.toString();
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
        Order orderInDb = getById(order.getId());
        orderInDb.setDeliveryTs(order.getDeliveryTs());
        orderInDb.setStatus(order.getStatus());
        return orderDao.update(orderInDb);
    }

    @Transactional
    public List<Order> getLatestList(String wechatId, int count) {
        return orderDao.getLatestList(wechatId, count);
    }

    @Transactional
    public Order getOrderByConfirmCode(String confirmCode) {
        logger.info("Get order using confirm code {}", confirmCode);
        return orderDao.getByConfirmCode(confirmCode);
    }
}
