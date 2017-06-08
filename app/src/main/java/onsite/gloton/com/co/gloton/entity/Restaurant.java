package onsite.gloton.com.co.gloton.entity;

/**
 * Created by Prometheus on 5/06/2017.
 */

public class Restaurant
{
    private String name;
    private String address;
    private String nit;
    private String telephone;
    private String owner;
    private int logo;
    private boolean state; //true for active false for deleted

    public Restaurant(String name, String address, String nit, String telephone, String owner, int logo) {
        this.name = name;
        this.address = address;
        this.nit = nit;
        this.telephone = telephone;
        this.owner = owner;
        this.logo = logo;
        this.state = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNit() {
        return nit;
    }

    public boolean getState() {
        return state;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
