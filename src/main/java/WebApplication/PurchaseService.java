package WebApplication;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {
    private SessionFactory sessionFactory;

    public PurchaseService(){
        sessionFactory = createSessionFactory(createConfiguration());
    }

    public void addNew(Purchase purchase) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DAO dao = new DAO(session);
        dao.insert(purchase);
        transaction.commit();
        session.close();
    }

    public void delete(Purchase purchase) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DAO dao = new DAO(session);
        dao.delete(purchase);
        transaction.commit();
        session.close();
    }

    public void change(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DAO dao = new DAO(session);
        dao.change(id);
        transaction.commit();
        session.close();
    }
    public Purchase getById(long id) {
        Session session = sessionFactory.openSession();
        DAO dao = new DAO(session);
        Purchase profile = dao.get(id);
        session.close();
        return profile;
    }

    public List<Purchase> getAll(){
        Session session = sessionFactory.openSession();
        DAO dao = new DAO(session);
        List<Purchase> res = dao.getAll();
        session.close();
        return res;
    }
    private Configuration createConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Purchase.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/purchase");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "123456");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }
    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
