package multiAgent.ontology;

import jade.content.AgentAction;
import jade.core.AID;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H77 on 2017/5/7.
 */
public class OrderResponse implements AgentAction {

    private int responseNum;
    private AID orderId;
    private List<Bid> bids;

    public OrderResponse(int responseNum, AID orderId) {
        this.responseNum = responseNum;
        this.orderId = orderId;
        bids = new ArrayList<Bid>();
    }
    public OrderResponse(){
    }
    public int getResponseNum() {
        return responseNum;
    }
    public void setResponseNum(int responseNum) {
        this.responseNum = responseNum;
    }
    public AID getOrderId() {
        return orderId;
    }
    public void setOrderId(AID orderId) {
        this.orderId = orderId;
    }
    public List getBids() {
        return bids;
    }
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    public void addBid(Bid bid){
       bids.add(bid);
    }

}
