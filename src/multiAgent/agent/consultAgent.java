package multiAgent.agent;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.*;
import jade.content.onto.basic.Action;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.Agent;
import multiAgent.ontology.*;

/**
 * Created by h77 on 2017/5/5.
 * 简单的协商Agent
 */
public class consultAgent extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    Behaviour readMessages = new CyclicBehaviour(this) {
        @Override
        public void action() {
            MessageTemplate mt = MessageTemplate.and(
                    MessageTemplate.MatchLanguage(codec.getName()),
                    MessageTemplate.MatchOntology(ontology.getName()));
            ACLMessage msg = receive(mt);
            if (msg != null) {
                try {
                    ContentElement ce = getContentManager().extractContent(msg);
                    Action act = (Action)ce;
                    if(act.getAction() instanceof Order){
                        Order o = (Order) act.getAction();
                        System.out.println("收到"+o.getCustomer()+"客户的订单");
                        ACLMessage al  = new ACLMessage(ACLMessage.INFORM);
                        al.addReceiver(o.getSource());
                        al.setContent("接受处理订单");
                        send(al);
                    }
                } catch (Codec.CodecException e) {
                    e.printStackTrace();
                } catch (OntologyException e) {
                    e.printStackTrace();
                }
            } else {
                block();
            }
        }
    };



    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Order");
        sd.setName(getLocalName()+"Order");
        dfd.addServices(sd);
        try {
            DFService.register(this,dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        addBehaviour(readMessages);
    }
}