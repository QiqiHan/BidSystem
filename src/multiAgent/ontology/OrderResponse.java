package multiAgent.ontology;

import jade.content.AgentAction;
import jade.core.AID;
import jade.util.leap.List;

/**
 * Created by H77 on 2017/5/7.
 */
public class OrderResponse implements AgentAction {

    private int responseNum;
    private AID orderId;
    private List bids;

    public OrderResponse(int responseNum, AID orderId,List bids) {
        this.responseNum = responseNum;
        this.orderId = orderId;
        this.bids = bids;
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
    public void setBids(List bids) {
        this.bids = bids;
    }
    public void addBid(Bid bid){
       bids.add(bid);
    }

}
