package multiAgent.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;
import multiAgent.behavior.listener.tenantListener;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Order;

import java.util.Date;

/**
 * Created by h77 on 2017/5/5.
 * 简单的房客Agent
 */
public class tenantAgent extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);

        Date dStart = new Date(2017,5,9);
        Date dEnd = new Date(2017,5,12);
        Order order = new Order("小明","南京市南大","豪华套件",1,dStart,dEnd,120,getAID());
        /*
           这部分代码是向AMS Agent要全部的Agent的信息
         */
        AMSAgentDescription[] agents = null;
        AID aid = null;
        try {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults (new Long(-1));
            agents = AMSService.search( this ,new AMSAgentDescription (), c );
            for (int i=0; i<agents.length;i++) {
                AID agentID = agents[i].getName();
                if(agentID.getName().startsWith("f1")){
                    aid = agentID;
                }
            }
        }catch (Exception e) {
            System.out.println( "Problem searching AMS: " + e );
            e.printStackTrace();
        }

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        try {
            Action act = new Action();
            act.setActor(getAID());
            act.setAction(order);
            msg.addReceiver(aid);
            msg.setLanguage(codec.getName());
            msg.setOntology(ontology.getName());
            getContentManager().fillContent(msg,act);
        } catch (Codec.CodecException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        }
        System.out.println("tenant"+this.getName()+"发送订单请求");
        send(msg);
        addBehaviour(new tenantListener(this));
    }

}
