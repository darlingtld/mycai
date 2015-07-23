package mycai.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mycai.dao.OrderDao;
import mycai.dao.ProductDao;
import mycai.dao.UserDao;
import mycai.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        if (user != null) {
            user.setConsignee(order.getConsignee());
            user.setConsigneeContact(order.getConsigneeContact());
            user.setShopInfo(order.getShopInfo());
            userDao.update(user);
        }
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

    @Transactional
    public List<Dispatch> getDispatchList() {
        List<Order> orderList = orderDao.getOrderListByStatus(OrderStatus.NOT_DELIVERED);
        List<Product> allProductList = productDao.getAll();
        HashMap<Integer, Product> allProductMap = new HashMap<>();
        for (Product product : allProductList) {
            allProductMap.put(product.getId(), product);
        }
        Map<Integer, Dispatch> dispatchMap = new HashMap<>();
        for (Order order : orderList) {
            JSONObject jsonObject = JSON.parseObject(order.getBill());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.size(); i++) {
                Integer productId = jsonArray.getJSONObject(i).getInteger("productId");
                Integer amount = jsonArray.getJSONObject(i).getInteger("amount");
                String unit = jsonArray.getJSONObject(i).getString("productUnit");
                Product product = allProductMap.get(productId);
                if (product == null) {
                    continue;
                }
                if (dispatchMap.containsKey(productId)) {
                    dispatchMap.get(productId).setQuantity(dispatchMap.get(productId).getQuantity() + amount);
                    dispatchMap.get(productId).getOrderInfoList().add(getKeyInfo(order, amount, unit));
                } else {
                    Dispatch dispatch = new Dispatch();
                    dispatch.setProduct(product);
                    dispatch.setQuantity(amount);
                    List<String> orders = new ArrayList<>();
                    orders.add(getKeyInfo(order, amount, unit));
                    dispatch.setOrderInfoList(orders);
                    dispatch.setUnit(unit);
                    dispatchMap.put(productId, dispatch);
                }

            }
        }
        return new ArrayList<>(dispatchMap.values());
    }

    private String getKeyInfo(Order order, Integer amount, String unit) {
        return String.format("[数量]%s%s [送货时间]%s [收件人]%s %s [电话]%s [下单时间]%s", amount, unit, order.getDeliveryTs(), order.getConsignee(), order.getShopInfo(), order.getConsigneeContact(), order.getOrderTs());
    }

    @Transactional
    public void exportDispatchList() {

    }

    public List<String> getStatusList() {
        return Arrays.asList(OrderStatus.NOT_DELIVERED, OrderStatus.IN_DELIVERY, OrderStatus.DELIVERED_NOT_PAID, OrderStatus.DELIVERED_PAID);
    }
}
