package DO;

public class bid {
    private Integer bidid;

    private Integer landlordid;

    private Integer result;

    private Integer price;

    private Integer roomid;

    private String orderid;

    public bid(Integer bidid, Integer landlordid, Integer result, Integer price, Integer roomid, String orderid) {
        this.bidid = bidid;
        this.landlordid = landlordid;
        this.result = result;
        this.price = price;
        this.roomid = roomid;
        this.orderid = orderid;
    }

    public bid() {
        super();
    }

    public Integer getBidid() {
        return bidid;
    }

    public void setBidid(Integer bidid) {
        this.bidid = bidid;
    }

    public Integer getLandlordid() {
        return landlordid;
    }

    public void setLandlordid(Integer landlordid) {
        this.landlordid = landlordid;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }
}