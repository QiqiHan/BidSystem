package DO;

import java.math.BigDecimal;

public class landlord {
    private Integer landlordid;

    private String landlordname;

    private String landlordtype;

    private String feature;

    private Integer startprice;

    private String city;

    private String area;

    private String detailaddress;

    private Integer comment;

    private BigDecimal long;

    private BigDecimal lat;

    private String concat;

    private String introduction;

    public landlord(Integer landlordid, String landlordname, String landlordtype, String feature, Integer startprice, String city, String area, String detailaddress, Integer comment, BigDecimal long, BigDecimal lat, String concat, String introduction) {
        this.landlordid = landlordid;
        this.landlordname = landlordname;
        this.landlordtype = landlordtype;
        this.feature = feature;
        this.startprice = startprice;
        this.city = city;
        this.area = area;
        this.detailaddress = detailaddress;
        this.comment = comment;
        this.long = long;
        this.lat = lat;
        this.concat = concat;
        this.introduction = introduction;
    }

    public landlord() {
        super();
    }

    public Integer getLandlordid() {
        return landlordid;
    }

    public void setLandlordid(Integer landlordid) {
        this.landlordid = landlordid;
    }

    public String getLandlordname() {
        return landlordname;
    }

    public void setLandlordname(String landlordname) {
        this.landlordname = landlordname == null ? null : landlordname.trim();
    }

    public String getLandlordtype() {
        return landlordtype;
    }

    public void setLandlordtype(String landlordtype) {
        this.landlordtype = landlordtype == null ? null : landlordtype.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public Integer getStartprice() {
        return startprice;
    }

    public void setStartprice(Integer startprice) {
        this.startprice = startprice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress == null ? null : detailaddress.trim();
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public BigDecimal getLong() {
        return long;
    }

    public void setLong(BigDecimal long) {
        this.long = long;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat == null ? null : concat.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}