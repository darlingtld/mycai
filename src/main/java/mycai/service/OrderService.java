package mycai.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mycai.dao.OrderDao;
import mycai.dao.ProductDao;
import mycai.dao.UserDao;
import mycai.pojo.Order;
import mycai.pojo.Product;
import mycai.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by darlingtld on 2015/5/16.
 */
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    public int save(Order order) {
        User user = userDao.getUserByWechatId(order.getWechatId());
        user.setConsignee(order.getConsignee());
        user.setConsigneeContact(order.getConsigneeContact());
        user.setShopInfo(order.getShopInfo());
        userDao.update(user);
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
//        Order orderInDb = getById(order.getId());
//        orderInDb.setDeliveryTs(order.getDeliveryTs());
//        orderInDb.setStatus(order.getStatus());
        return orderDao.update(order);
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

    @Transactional
    public List<Product> getListByTimeFrame(String wechatid, Calendar then, Calendar now) {
        logger.info("Get order of {} from {} to {}", wechatid, then.getTime().toString(), now.getTime().toString());
        List<Order> orderList = orderDao.getListByTimeFrame(wechatid, then, now);
        List<Product> productList = new ArrayList<>();
        List<Product> allProductList = productDao.getAll();
        HashMap<Integer, Product> allProductMap = new HashMap<>();
        for (Product product : allProductList) {
            allProductMap.put(product.getId(), product);
        }
        for (Order order : orderList) {
            JSONObject jsonObject = JSON.parseObject(order.getBill());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.size(); i++) {
                Integer productId = jsonArray.getJSONObject(i).getInteger("productId");
                Product product = allProductMap.get(productId);
                if (!productList.contains(product)) {
                    if (product != null)
                        productList.add(product);
                }
            }
        }
        return productList;
    }
}
