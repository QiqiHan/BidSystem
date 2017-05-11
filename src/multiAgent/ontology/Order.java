package multiAgent.ontology;

import jade.content.AgentAction;
import jade.core.AID;

import java.util.Date;

/**
 * Created by H77 on 2017/5/5.
 */
public class Order implements AgentAction {
    private String customer;
    private String address;
    private String roomType;
    private int roomNum;
    private Date start;
    private Date end;
    private int price;
    private AID source;
    public Order(String customer, String address, String type,int num,Date s,Date e,int price, AID source) {
        this.customer = customer;
        this.address = address;
        this.roomType = type;
        this.roomNum = num;
        this.start = s;
        this.end = e;
        this.price = price;
        this.source = source;
    }
    public Order(){

    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
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
