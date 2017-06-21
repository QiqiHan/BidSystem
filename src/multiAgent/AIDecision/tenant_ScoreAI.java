package multiAgent.AIDecision;

import DO.bid;
import dao.daoImpl.bidDao;
import multiAgent.ontology.RoomType;
import smile.classification.RandomForest;
import smile.data.Attribute;
import smile.data.NumericAttribute;

import java.util.HashMap;
import java.util.List;

/**
 * Created by H77 on 2017/6/14.
 */
public class tenant_ScoreAI {
    List<bid> bids;
    private HashMap<String,Integer> roomPoint = new HashMap<String, Integer>();
    public  tenant_ScoreAI(List<bid> bids){
        this.bids = bids;
//        fill_hashmap();
    }
    public static void main(String[] args) {

        List<bid> bids = bidDao.selectAllBids();
        tenant_ScoreAI tenant = new tenant_ScoreAI(bids);
        tenant.trainBid();

    }

    public  Attribute[] initAttribute(){
        Attribute[] attrs = new Attribute[3];
        attrs[0] = new NumericAttribute("facility");
        attrs[1] = new NumericAttribute("arroundSite");
        attrs[2] = new NumericAttribute("price");
        return attrs;
    }
    public void trainBid(){
        Attribute[] attrs  = initAttribute();
        double[][] x = new double[bids.size()][4];
        int[] y = new int[bids.size()];
        int count = 0;
//        while(count < 100) {
            for (int i = 0; i < bids.size(); i++) {
                bid bid = bids.get(i);
//            double facility = parseFacility(bid.getFacility());
//            double arroundSite = parseArroundSite(bid.getArroundsite());
//            double price = bid.getPrice();
//            int roomType = roomPoint.get(bid.getRoomtype());
                double[] a = parseOneBid(bid);
//            double score = Integer.parseInt(bid.getScore())*1.0/100;
                x[i] = a;
                y[i] = bid.getResult();
            }
//        }
        RandomForest forest = new RandomForest(attrs,x,y,4,3);


        double[] results = forest.importance();
        for(int i = 0 ; i < results.length ; i++){
            System.out.println(attrs[i].getName()+": "+ results[i]);
        }
    }
    public double[] parseOneBid(bid bid){
        double[] a = new double[3];
        a[0] = Double.parseDouble(bid.getFacility())/100;
        a[1] = Double.parseDouble(bid.getArroundsite())/100;
        a[2] = bid.getPrice()*1.0/100;
        return a;
    }
    //目前用这种解析方式表示bid中某种List<facility>的唯一性
//    public double parseFacility(String facility){
//        int num = 8; //默认是8个设施
//        String str = facility.split("-")[0];
//        while(str.length() < 8){
//            str = str+"2";
//        }
////        int result = str.hashCode();
//        double result = Double.parseDouble(str);
//        return result;
//    }
//    public double parseArroundSite(String arroundSite){
//        String[] str = arroundSite.split(":");
//        byte[] results = new byte[str.length];
//
//        for( int i = 0 ; i < str.length ; i++){
//            int num = Integer.parseInt(str[i]);
//            results[i] = (byte)num;
//        }
//        int result = bytesToInt(results,0);
//        return result;
//    }

//    public int bytesToInt(byte[] src, int offset) {
//        int value;
//        value = (int) ((src[offset] & 0xFF)
//                | ((src[offset+1] & 0xFF)<<8)
//                | ((src[offset+2] & 0xFF)<<16)
//                | ((src[offset+3] & 0xFF)<<24));
//        return value;
//    }
//    private void fill_hashmap(){
//        roomPoint.put("Standard",1);
//        roomPoint.put("Superior",3);
//        roomPoint.put("Special",2);
//        roomPoint.put("Business",4);
//        roomPoint.put("Deluxe",5);
//    }

}
