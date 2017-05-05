package multiAgent.ontology;

import jade.content.AgentAction;
import jade.core.AID;

/**
 * Created by H77 on 2017/5/5.
 */
public class Order implements AgentAction {
    private String customer;
    private String address;
    private int price;
    private AID source;
    public Order(String customer, String address, int price, AID source) {
        this.customer = customer;
        this.address = address;
        this.price = price;
        this.source = source;
    }
    public Order(){

    }
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public AID getSource() {
        return source;
    }

    public void setSource(AID source) {
        this.source = source;
    }
}
