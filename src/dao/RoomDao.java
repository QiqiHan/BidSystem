package dao;

/**
 * Created by zcy on 2017/5/8.
 *
 */
public class RoomDao {
    private int aid;
    private String type;
    private int price;

    public RoomDao() {
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}