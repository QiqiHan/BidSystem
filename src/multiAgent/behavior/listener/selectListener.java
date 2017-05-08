package multiAgent.behavior.listener;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import multiAgent.agent.selectAgent;
import multiAgent.behavior.logical.selectAnalysis;
import multiAgent.behavior.message.selectInform;
import multiAgent.ontology.Bid;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Order;
import multiAgent.ontology.OrderResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 17/5/6.
 */
public class selectListener extends CyclicBehaviour {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();
    private List<AID> lanlordAids;
    private selectAgent agent;
    public selectListener(Agent agent){
         super(agent);
         this.agent = (selectAgent) agent;
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontology.getName()));
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            try {
                if(msg.getPerformative() == ACLMessage.QUERY_REF) {
                    ContentElement ce = myAgent.getContentManager().extractContent(msg);
                    Action act = (Action) ce;
                    if (act.getAction() instanceof Order) {
                        Order o = (Order) act.getAction();
                        System.out.println("selectAgent收到筛选条件信息" + o.getAddress());
                        /**
                         * todo
                         * 下面是去查询并且筛选数据，然后生成招标书，并且发送给房东agent
                         */
                        lanlordAids = new ArrayList<AID>();
                        myAgent.addBehaviour(new selectAnalysis(myAgent,o));
                    }
                }else if( msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                    ContentElement ce = myAgent.getContentManager().extractContent(msg);
                    Action act = (Action) ce;
                    Bid bid = (Bid) act.getAction();
                    boolean isAll = agent.isAllReply(bid.getOrderId(),bid);
                    if(isAll){
                        OrderResponse order = agent.getAndRemove(bid.getOrderId());
                        //以下代码为将OrderResponse返回给房客Agent
                        myAgent.addBehaviour(new selectInform(myAgent,order));
                    }
                    System.out.println(bid.getLandlordId().getName() +" 同意竞标");
                    System.out.println("selectAgent获取房客竞标情况，暂时结束");
                }else if(msg.getPerformative() == ACLMessage.REJECT_PROPOSAL){
                    ContentElement ce = myAgent.getContentManager().extractContent(msg);
                    Action act = (Action) ce;
                    Bid bid = (Bid) act.getAction();
                    boolean isAll = agent.isAllReply(bid.getOrderId(),null);
                    if(isAll){
                        OrderResponse order = agent.getAndRemove(bid.getOrderId());
                        //以下代码为将OrderResponse返回给房客Agent
                        myAgent.addBehaviour(new selectInform(myAgent,order));
                    }
                    System.out.println(bid.getLandlordId().getName() +" 拒绝竞标");
                    System.out.println("selectAgent获取房客竞标情况，暂时结束");
                }
                /*
                  可扩展其它Message种类
                */
            } catch (Codec.CodecException e) {
                e.printStackTrace();
            } catch (OntologyException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }

    }
}
