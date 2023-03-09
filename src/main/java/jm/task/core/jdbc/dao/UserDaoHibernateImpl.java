package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS userstable").executeUpdate();
            session.createSQLQuery("CREATE TABLE userstable(\n" +
                    "id int not null auto_increment,\n" +
                    "name varchar(30),\n" +
                    "lastName varchar(30),\n" +
                    "age int,\n" +
                    "primary key(id));").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS userstable").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.delete(session.get(User.class, id));

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> allUsers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            allUsers = session.createQuery("from User", User.class).getResultList();
//            allUsers = session.createCriteria(User.class).list();

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();


            session.createQuery("delete User").executeUpdate();

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }
}
