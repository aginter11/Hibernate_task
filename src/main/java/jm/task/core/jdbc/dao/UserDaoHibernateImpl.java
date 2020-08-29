package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users_hib(id int primary key auto_increment, name varchar(40), lastName varchar(40), age int)";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users_hib;";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.createQuery("delete User where id = :param").setParameter("param", id).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM users_hib";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(User.class);
            users = query.list();
       //     users = session.createQuery("from User").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Exception - rollback");
            }
        }
        System.out.println(users);
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            String sql = "DELETE from users_hib";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
