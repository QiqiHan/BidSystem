package multiAgent.ontology;

import jade.content.AgentAction;
import jade.core.AID;

import java.util.Date;
import java.util.List;

/**
 * Created by H77 on 2017/5/6.
 *
 */
public class Tender implements AgentAction {
    private Integer tenderId;

    private String orderId;

    private Integer userId;

    private Integer price;

    private String address;

    private Date startTime;

    private Date leaveTime;

    private String roomType;

    private Integer roomNum;

    private Date createDat;

    private List<String> facilities;

    private String hotelType;

    private String aroundsite;

    private AID source;

    public Tender(Integer tenderId,
                  String orderId,
                  Integer userId,
                  Integer price,
                  String address,
                  Date startTime,
                  Date leaveTime,
                  String roomType,
                  Integer roomNum,
                  Date createDat,
                  List<String> facilities,
                  String hotelType,
                  String aroundsite,
                  AID source)
    {
        this.tenderId = tenderId;
        this.orderId = orderId;
        this.userId = userId;
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

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getAroundsite() {
        return aroundsite;
    }

    public void setAroundsite(String aroundsite) {
        this.aroundsite = aroundsite;
    }
}
