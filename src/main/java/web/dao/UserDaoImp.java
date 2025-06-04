package web.dao;

import web.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImp implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImp.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

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
                "PRIMARY KEY (id)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
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
    @Transactional(readOnly = true)
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

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user); // Обновление существующей записи
        logger.info("Пользователь успешно обновлён.");
    }
}