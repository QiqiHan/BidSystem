package multiAgent.ontology;

import jade.content.AgentAction;
import jade.core.AID;
import jade.util.leap.List;

import java.util.Date;

/**
 * Created by H77 on 2017/5/6.
 *
 */
public class Tender implements AgentAction {

    private String orderId;

    private Integer price;

    private String address;

    private Date startTime;

    private Date leaveTime;

    private String roomType;

    private Integer roomNum;

    private Date createDat;

    private List facilities;

    private String hotelType;

    private List aroundsite;

    private AID source;

    public Tender(
                  String orderId,
                  Integer price,
                  String address,
                  Date startTime,
                  Date leaveTime,
                  String roomType,
                  Integer roomNum,
                  Date createDat,
                  List facilities,
                  String hotelType,
                  List aroundsite,
                  AID source)
    {
        this.orderId = orderId;
        this.price = price;
        this.address = address;
        this.startTime = startTime;
        this.leaveTime = leaveTime;
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.createDat = createDat;
        this.facilities = facilities;
        this.hotelType = hotelType;
        this.aroundsite = aroundsite;
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
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public AID getSource() {
        return source;
    }
    public void setSource(AID source) {
        this.source = source;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Date getCreateDat() {
        return createDat;
    }

    public void setCreateDat(Date createDat) {
        this.createDat = createDat;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public List getFacilities() {
        return facilities;
    }

    public void setFacilities(List facilities) {
        this.facilities = facilities;
    }

    public List getAroundsite() {
        return aroundsite;
    }

    public void setAroundsite(List aroundsite) {
        this.aroundsite = aroundsite;
    }

}
