package multiAgent.ontology;

import jade.content.AgentAction;
import jade.content.Concept;
import jade.core.AID;

import java.util.List;

/**
 * Created by H77 on 2017/5/7.
 *
 */
public class Bid implements Concept {

    //市场价，是否再同意降价,床的大小，房间大小
    private AID orderId;
    private String id; //就是orderId,现在暂时用的AID
    private Room room;
    private int price;
    private List<String> facilities;
    private List<String> aroundsites;
    private AID landlordId;
    private int type; // 0 拒绝竞标  1 竞标

    public Bid(AID orderId,
               Room room,
               int price,
               List<String> facilities,
               List<String> aroundsites,
               AID landlordId ,
               int type)
    {
        this.orderId = orderId;
        this.room = room;
        this.price = price;
        this.facilities =facilities;
        this.aroundsites = aroundsites;
        this.landlordId = landlordId;
        this.type = type;
    }
    public Bid(){}
    public AID getOrderId() {
        return orderId;
    }
    public void setOrderId(AID orderId) {
        this.orderId = orderId;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
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
    public int getType() { return type; }
    public void setType(int type) { this.type = type;}

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }

    public List<String> getAroundsites() {
        return aroundsites;
    }

    public void setAroundsites(List<String> aroundsites) {
        this.aroundsites = aroundsites;
    }
}
