package web.dao;

import org.hibernate.HibernateException;
import web.MainApp;
import web.config.DataBaseConfig;
import web.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDaoImp implements UserDao {

    private static final Logger logger = Logger.getLogger(MainApp.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void saveUser(String firstname, String lastName) {
        User user = new User(firstname, lastName);
        entityManager.persist(user);
        logger.info("Пользователь успешно сохранён.");
    }

    @Override
    @Transactional
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "firstname VARCHAR(45) NOT NULL, " +
                "lastName VARCHAR(45) NOT NULL, " +
                "PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        entityManager.createNativeQuery(sql).executeUpdate();
        logger.info("Таблица 'users' успешно создана.");
    }

    @Override
    @Transactional
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        entityManager.createNativeQuery(sql).executeUpdate();
        logger.info("Таблица 'users' успешно удалена.");
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            logger.info("Пользователь с id=" + id + " успешно удалён.");
        } else {
            logger.warning("Пользователь с id=" + id + " не найден.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        entityManager.createNativeQuery(sql).executeUpdate();
        logger.info("Таблица успешно очищена.");
    }
}