package mycai.service;

import event.coupon.pojo.Coupon;
import event.coupon.pojo.Discount;
import event.coupon.pojo.Voucher;
import event.coupon.service.CouponService;
import mycai.pojo.Order;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

/**
 * Created by darlingtld on 2015/8/8 0008.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private OrderService orderService;

    String openid = "o5Irvt5957jQ4xmdHmDp59epk0UU";

    @Test
    public void createCouponTest() {
        // 12 - 1
        Voucher voucher = new Voucher();
        voucher.setOpenid(openid);
        DateTime startTime = new DateTime(2015, 6, 30, 0, 0, 0);
        DateTime endTime = new DateTime(2015, 8, 31, 0, 0, 0);
        voucher.setStartTime(startTime.toDate());
        voucher.setEndTime(endTime.toDate());
        voucher.setReachedMoney(12);
        voucher.setDeductedMoney(1);
        couponService.createCoupon(voucher);

        // 0.95
        Discount discount = new Discount();
        discount.setOpenid(openid);
        discount.setStartTime(startTime.toDate());
        discount.setEndTime(endTime.toDate());
        discount.setDiscountFactor(0.95);
        couponService.createCoupon(discount);
    }

    @Test
    public void runCouponRules() {
        Order order = orderService.getList(openid).get(1);
        System.out.println(order.evalBillTotalMoney());
        // get coupons
        List<Coupon> couponList = couponService.getCouponList(openid);

        for (Coupon coupon : couponList) {
            double preferredMoney = coupon.deduct(order);
            System.out.print(preferredMoney);
        }

    }
}
