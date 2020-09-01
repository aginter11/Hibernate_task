package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
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
        try (Session session = sessionFactory.openSession();) {
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
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            String sqlSaveUser = "INSERT INTO users_hib (name, lastName, age) VALUES(?, ?, ?)";
//            User user = new User(name, lastName, age);
//            session.save(user);
            Query query =
                    session.createSQLQuery(sqlSaveUser);
            query.setParameter(1, name);
            query.setParameter(2, lastName);
            query.setParameter(3, age);
            query.executeUpdate();

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
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            //   session.createQuery("delete User where id = :param").setParameter("param", id).executeUpdate();
            session.createSQLQuery("DELETE FROM users_hib WHERE id=?").setParameter(1, id).executeUpdate();

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
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM users_hib";
            SQLQuery query = session.createSQLQuery(sql).addEntity(User.class);
            users = query.list();
            //     users = session.createQuery("from User").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Exception - rollback");
            }
        }
        users.forEach(System.out::println);

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
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
