package WebApplication;

import org.hibernate.Session;

import java.util.List;

public class DAO {
    private Session session;
    public DAO(Session session) {
        this.session = session;
    }
    public Purchase get(long id){
        return (Purchase) session.load(Purchase.class, id);
    }

    public void delete(Purchase purchase){
        session.delete(purchase);
    }
    public List getAll(){
        return session.createCriteria(Purchase.class).list();
    }

    public void change(long id){
        Purchase purchase = (Purchase) session.load(Purchase.class, id);
        purchase.setPurchased(true);
    }
    public void insert(Purchase purchase){
        session.save(purchase);
    }
}
