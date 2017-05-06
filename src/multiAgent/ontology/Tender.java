package multiAgent.ontology;

import jade.content.AgentAction;
import jade.core.AID;

/**
 * Created by H77 on 2017/5/6.
 */
public class Tender implements AgentAction {
    private String address;
    private int price;
    private AID source;

    public Tender(String address, int price, AID source) {
        this.address = address;
        this.price = price;
        this.source = source;
    }
    public Tender(){

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
