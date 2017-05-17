package multiAgent.behavior.logical;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import multiAgent.agent.selectAgent;
import multiAgent.behavior.message.selectPropose;
import multiAgent.ontology.Order;
import multiAgent.ontology.Tender;

import java.util.*;

/**
 * Created by H77 on 2017/5/6.
 * 这个Behaviour主要是根据订单请求信息，筛选搜索对应的潜在房东
 * 并向这些房东发送招标书
 */
public class selectAnalysis extends OneShotBehaviour {

    private Order order = null;
    private selectAgent agent = null;
    public selectAnalysis(Agent agent,Order order){
        super(agent);
        this.agent = (selectAgent)agent;
        this.order = order;
    }
    public void action() {
        Tender tender = new Tender(order);


        String addr = order.getAddress();
//        MapObject map = MapUtil.convert(addr);
//        Double lat = Double.parseDouble(map.getLat());
//        Double lng = Double.parseDouble(map.getLng());
//        //寻找附近酒店，暂时算距离。因为数据太少了
//        SqlSession sqlSession = DBTools.getSession();
//        landlordMapper lordMapper = sqlSession.getMapper(landlordMapper.class);
//        List<landlord> landlords = lordMapper.selectAllLandlord();
//        //筛选最近的5个酒店
//        PriorityQueue<landlordCompare> queue = new PriorityQueue<landlordCompare>(5, new Comparator<landlordCompare>() {
//            public int compare(landlordCompare o1, landlordCompare o2) {
//                if(o1.getDistance()<o2.getDistance()) return -1;
//                else if(o1.getDistance() == o2.getDistance()) return 0;
//                else return 1;
//            }
//        });
//        for(landlord land : landlords) {
//            double distance = countDistance(lat, lng, land.getLat().doubleValue(),land.getLongitude().doubleValue());
//            landlordCompare compare = new landlordCompare(land,distance);
//            queue.add(compare);
//        }

//        Iterator<landlordCompare> landlordCompares = queue.iterator();
//        //应该是如果应标 再查周边信息
//        while(landlordCompares.hasNext()) {
//            landlordCompare landcompare = landlordCompares.next();
//            landlord land = landcompare.getLand();
//            //寻找酒店周边信息
//            List<String> keywords = new ArrayList<String>();
//            keywords.add("超市");
//            keywords.add("公交");
//            keywords.add("景点");
//            Map<String,List<MapObject>> maps = MapUtil.searchAroundSite(keywords,land.getLat().toString(),land.getLongitude().toString());
//        }

        //创建Agent的例子
        List<AID> aids  = agent.createAgent();
        myAgent.addBehaviour(new selectPropose(myAgent,tender,aids));
    }

    public double countDistance(double lat1, double lng1, double lat2, double lng2){
        double  EARTH_RADIUS = 6378.137;

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    double rad(double d){
        return d*Math.PI/180.0;
    }

}
