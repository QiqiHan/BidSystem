package DO;

import java.math.BigDecimal;
import java.util.Date;

public class room {
    private Integer roomid;

    private Integer landlordid;

    private String roomtype;

    private Integer price;

    private Date validstarttime;

    private Date validendtime;

    private String facilities;

    private BigDecimal area;

    private Integer restnum;

    public room(Integer roomid, Integer landlordid, String roomtype, Integer price, Date validstarttime, Date validendtime, String facilities, BigDecimal area, Integer restnum) {
        this.roomid = roomid;
        this.landlordid = landlordid;
        this.roomtype = roomtype;
        this.price = price;
        this.validstarttime = validstarttime;
        this.validendtime = validendtime;
        this.facilities = facilities;
        this.area = area;
        this.restnum = restnum;
    }

    public room() {
        super();
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getLandlordid() {
        return landlordid;
    }

    public void setLandlordid(Integer landlordid) {
        this.landlordid = landlordid;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype == null ? null : roomtype.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getValidstarttime() {
        return validstarttime;
    }

    public void setValidstarttime(Date validstarttime) {
        this.validstarttime = validstarttime;
    }

    public Date getValidendtime() {
        return validendtime;
    }

    public void setValidendtime(Date validendtime) {
        this.validendtime = validendtime;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities == null ? null : facilities.trim();
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getRestnum() {
        return restnum;
    }

    public void setRestnum(Integer restnum) {
        this.restnum = restnum;
    }
}