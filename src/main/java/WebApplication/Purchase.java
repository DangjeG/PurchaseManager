package WebApplication;

import javax.persistence.*;


@Entity
@Table(name = "purchases")


public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private double prise;
    @Column
    private boolean purchased;

    public Purchase(){}

    public Purchase(String name, double prise){
        this.name = name;
        this.prise = prise;
        purchased = false;
    }

    public long getId(){return id;}
    public String getName() {
        return name;
    }
    public double getPrise() {
        return prise;
    }
    public boolean isPurchased() {
        return purchased;
    }
    public void setPurchased(boolean purchased){
        this.purchased = purchased;
    }
}
