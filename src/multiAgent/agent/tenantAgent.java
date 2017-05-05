package multiAgent.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Order;

/**
 * Created by h77 on 2017/5/5.
 * 简单的房客Agent
 */
public class tenantAgent extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    Behaviour readMessages = new CyclicBehaviour(this) {
        @Override
        public void action() {
            MessageTemplate mt = MessageTemplate.and(
                    MessageTemplate.MatchLanguage(codec.getName()),
                    MessageTemplate.MatchOntology(ontology.getName()));
            ACLMessage msg = receive();
            if (msg != null) {
                String content = msg.getContent();
                System.out.println("协商管理："+content);
            } else {
                block();
            }
        }
    };
    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);

        Order order = new Order("aaa","bb",1235,getAID());

        /*
          向DFagent 询问了注册了Order服务的Agent 最后是通过 DFService.search查找相应的Agent
         */
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Order");
        template.addServices(sd);

        AID aid = null;
        /*
           这部分代码是向AMS Agent要全部的Agent的信息
         */
//        AMSAgentDescription[] agents = null;
//        AID aid = null;
//        try {
//            SearchConstraints c = new SearchConstraints();
//            c.setMaxResults (new Long(-1));
//            agents = AMSService.search( this ,new AMSAgentDescription (), c );
//            for (int i=0; i<agents.length;i++) {
//                AID agentID = agents[i].getName();
//                if(agentID.getName().startsWith("f1")){
//                    aid = agentID;
//                }
//            }
//        }catch (Exception e) {
//            System.out.println( "Problem searching AMS: " + e );
//            e.printStackTrace();
//        }
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        try {
            DFAgentDescription[] result = DFService.search(this,template);
            aid = result[0].getName();

            Action act = new Action();
            act.setActor(aid);
            act.setAction(order);

            msg.addReceiver(aid);
            msg.setLanguage(codec.getName());
            msg.setOntology(ontology.getName());
            getContentManager().fillContent(msg,act);
        } catch (Codec.CodecException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        send(msg);
        addBehaviour(readMessages);
    }
}
