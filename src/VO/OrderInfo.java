package VO;

import jade.util.leap.List;

import java.util.Date;

/**
 * Created by H77 on 2017/5/28.
 */
public class OrderInfo {

    private int userId;
    private String address;
    private String hotelType;
    private String roomType;
    private int roomNum;
    private int minPrice;
    private int maxPrice;
    private List facilities;
    private Date startTime;
    private Date endTime;

    public OrderInfo(int userId, String address, String hotelType, String roomType, int roomNum, int minPrice, int maxPrice, List facilities ,Date start,Date end) {
        this.userId = userId;
        this.address = address;
        this.hotelType = hotelType;
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.facilities = facilities;
        this.startTime = start;
        this.endTime = end;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
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

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List getFacilities() {
        return facilities;
    }

    public void setFacilities(List facilities) {
        this.facilities = facilities;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


}
