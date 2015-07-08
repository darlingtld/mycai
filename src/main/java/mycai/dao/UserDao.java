package mycai.dao;

import mycai.pojo.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by darlingtld on 2015/5/16.
 */
@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public int save(User user) {

        int id = (int) sessionFactory.getCurrentSession().save(user);
        return id;
    }

    public List<User> getListByRole(String role) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from User where role = '%s' order by id asc", role)).list();
    }

    public User getById(int userId) {
        return (User) sessionFactory.getCurrentSession().get(User.class, userId);
    }

    public List<User> getAll() {
        return sessionFactory.getCurrentSession().createQuery(String.format("from User user")).list();

    }

    public boolean update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByWechatId(String fromUserName) {
        return (User) sessionFactory.getCurrentSession().createQuery(String.format("from User where openid = '%s", fromUserName)).uniqueResult();
    }
}
