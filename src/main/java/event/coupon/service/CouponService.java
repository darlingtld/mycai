package event.coupon.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import event.coupon.dao.CouponDao;
import event.coupon.pojo.Coupon;
import event.coupon.pojo.Voucher;
import mycai.pojo.Order;
import mycai.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by darlingtld on 2015/8/8 0008.
 */
@Service
public class CouponService {

    private static Logger logger = LoggerFactory.getLogger(CouponService.class);

    @Autowired
    private CouponDao couponDao;

    @Transactional
    public void createCoupon(Coupon coupon) {
        logger.info("Create coupon {}", coupon.toString());
        couponDao.createCoupon(coupon);
    }

    @Transactional
    public Coupon getCoupon(int id) {
        logger.info("Get coupon {}", id);
        return couponDao.getCoupon(id);
    }

    @Transactional
    public List<Coupon> getCouponList(String openid) {
        logger.info("Get coupon list of {}", openid);
        return couponDao.getCouponList(openid);
    }

    @Transactional
    public List<Voucher> getCouponList(String wechatid, Class<Voucher> voucherClass) {
        logger.info("Get voucher list of {}", wechatid);
        return couponDao.getCouponList(wechatid, voucherClass);
    }

    @Transactional
    public List<Coupon> getCalculatedCouponList(String wechatid, String billJson) {
        logger.info("Get calculated coupon list of {}", wechatid);
        Order order = transformOrderFromBill(billJson);
        List<Coupon> couponList = couponDao.getCouponList(wechatid);
        List<Coupon> suitableCouponList = new ArrayList<>();
        for (Coupon coupon : couponList) {
            if (coupon.isSuitableFor(order)) {
                coupon.setModifiedTotalPrice(coupon.deduct(order));
                suitableCouponList.add(coupon);
            }
        }
        return suitableCouponList;
    }

    private Order transformOrderFromBill(String billJson) {
        Order order = new Order();
        order.setBill(billJson);
        order.setOrderTs(Utils.yyyyMMddHHmmssFormat(new Date()));
        return order;
    }

    @Transactional
    public List<Coupon> getAllCouponList() {
        return couponDao.getAll();
    }
}
