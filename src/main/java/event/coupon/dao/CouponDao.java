package event.coupon.dao;

import event.coupon.pojo.Coupon;
import event.coupon.pojo.Voucher;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by darlingtld on 2015/8/8 0008.
 */
@Repository
public class CouponDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void createCoupon(Coupon coupon) {
        sessionFactory.getCurrentSession().save(coupon);
    }

    public Coupon getCoupon(int id) {
        return (Coupon) sessionFactory.getCurrentSession().get(Coupon.class, id);
    }

    public List<Coupon> getCouponList(String openid) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from Coupon where openid='%s'", openid)).list();
    }

    public List<Voucher> getCouponList(String wechatid, Class<Voucher> voucherClass) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(voucherClass);
        cr.add(Restrictions.eq("openid", wechatid));
        return cr.list();
    }
}