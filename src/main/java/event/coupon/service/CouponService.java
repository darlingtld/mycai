package event.coupon.service;

import event.coupon.dao.CouponDao;
import event.coupon.pojo.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Coupon getCoupon(int id){
        logger.info("Get coupon {}", id);
        return couponDao.getCoupon(id);
    }

    @Transactional
    public List<Coupon> getCouponList(String openid){
        logger.info("Get coupon list of {}", openid);
        return couponDao.getCouponList(openid);
    }
}
