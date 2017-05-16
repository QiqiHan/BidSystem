package multiAgent.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import multiAgent.behavior.listener.selectListener;
import multiAgent.ontology.Bid;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.OrderResponse;
import multiAgent.agentHelper.DFUtil;

import java.util.*;


/**
 * Created by zy on 17/5/6.
 */
public class selectAgent extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();
    //暂时将AID作为Key
    private Map<String,OrderResponse> store = new HashMap<String,OrderResponse>();
    //用来计数reply数，暂时也将AID作为Key
    private Map<String,Integer> counts = new HashMap<String, Integer>();
    //setup方法，负责为agent各个属性赋值，并且注册到dfAgent上
    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        System.out.println("创建 selectAgent");
        DFUtil.registerService(this,"select");
        addBehaviour(new selectListener(this));
    }
    public void setOrderResponse(String orderId, OrderResponse response){
        store.put(orderId,response);
        counts.put(orderId,0);
    }
    public  boolean isAllReply(String orderId,Bid bid){
        int replys = counts.get(orderId)+1;
        counts.put(orderId,replys);
        OrderResponse response = store.get(orderId);
        if(bid.getType() == 1) response.addBid(bid);
        if(replys == response.getResponseNum()){
            return true;
        }
        return false;
    }
    public  OrderResponse getAndRemove(String id){
        OrderResponse order = store.get(id);
        store.remove(id);
        counts.remove(id);
        return order;
    }
    public List<AID> createAgent(){
        AgentContainer c = getContainerController();
        List<AID> aids = new ArrayList<AID>();
        try {
            for(int i =  4 ; i< 8 ; i++) {
                String name = "f"+i;
                AgentController agent = c.createNewAgent(name, "multiAgent.agent.landlordAgent", null);
                AID id = new AID(name,false);
                aids.add(id);
                agent.start();
                System.out.println("Manager Agent创建Agent" +agent.getName());
            }
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        return aids;
    }


}
