package multiAgent.ontology;

import jade.content.AgentAction;
import jade.core.AID;

/**
 * Created by H77 on 2017/5/7.
 */
public class Bid implements AgentAction {

    private AID orderId;
    private String room;
    private int price;
    private AID landlordId;

    public Bid(AID orderId, String room, int price, AID landlordId) {
        this.orderId = orderId;
        this.room = room;
        this.price = price;
        this.landlordId = landlordId;
    }
    public Bid(){}
    public AID getOrderId() {
        return orderId;
    }
    public void setOrderId(AID orderId) {
        this.orderId = orderId;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public AID getLandlordId() {
        return landlordId;
    }
    public void setLandlordId(AID landlordId) {
        this.landlordId = landlordId;
    }
}
