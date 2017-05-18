package multiAgent.behavior.logical;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import multiAgent.agent.landlordAgent;
import multiAgent.behavior.message.landlordPropose;
import multiAgent.ontology.*;

import java.util.Date;

/**
 * Created by H77 on 2017/5/8.
 * 这个Behaviour主要是处理招标书，并生成相应结果。
 */
public class landlordDealTender extends OneShotBehaviour{

    private Tender tender;
    private AID receive;
    private landlordAgent agent;
    public landlordDealTender(Agent agent , Tender tender, AID receive){
        super(agent);
        this.agent = (landlordAgent) agent;
        this.tender = tender;
        this.receive = receive;
    }
    public void action() {
        //决定是否要竞标
        //首先查看该房间类型是否有，如果有房间数量是否满足。如果没有该类型房间或者房间数量不满足，则放弃竞标
        //其次查看招标价格与该房间价格的差值，如果招标价格高于等于房间价格，直接参与竞标
        //如果招标价格低于房间价格，如果差值大于某个阀值，则放弃竞标
        //阀值由该房东的经济情况确定。
        //比如tension的threshold = 0
        //affordable的threshold = 10
        //amiable的threshold = 20
        //promotion的threshold = 40
//        landlord lord = agent.get
        int type;
        Order order = tender.getOrder();
        int price_min_tender = order.getMinPrice();
        int price_max_tender = order.getMaxPrice();
        int price_room = 1; //该房东该类型房间的价格
        if(price_room<=price_min_tender){
            type = 1;
        }else if(price_room<price_max_tender){

        }else{

        }
        type = (int)(Math.random()*2);
        Bid bid = null;
        if(type == 1) {
            bid = new Bid(order.getId(),
                    new Room(1,1, RoomType.Business+"",agent.getAID(),200,new Date(2017,5,2),new Date(2017,5,9),"200",2),
                    100,
                    null,
                    null,
                    myAgent.getAID(),
                    order.getSource(),
                    type);
        }else{
            bid = new Bid(order.getId(),
                    new Room(1,1, RoomType.Business+"",agent.getAID(),200,new Date(2017,5,2),new Date(2017,5,9),"200",2),
                    0,
                    null,
                    null,
                    myAgent.getAID(),
                    order.getSource(),
                    type);
        }
        myAgent.addBehaviour(new landlordPropose(myAgent,bid,receive));
    }
}
