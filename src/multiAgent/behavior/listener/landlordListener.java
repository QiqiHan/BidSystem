package multiAgent.behavior.listener;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import multiAgent.behavior.logical.landlordDealTender;
import multiAgent.behavior.message.landlordPropose;
import multiAgent.ontology.*;

import java.util.Random;

/**
 * Created by H77 on 2017/5/6.
 */
public class landlordListener extends CyclicBehaviour {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    public landlordListener(Agent agent){
        super(agent);
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontology.getName()));
        ACLMessage msg = myAgent.receive(mt);

        if(msg != null){
            if(msg.getPerformative() == ACLMessage.PROPOSE){
                ContentElement ce = null;
                try {
                    ce = myAgent.getContentManager().extractContent(msg);
                    Action act = (Action) ce;
                    Tender tender = (Tender) act.getAction();
                    Order order = tender.getOrder();
                    System.out.println("lanlord" + myAgent.getName() + "收到信息地址" + order.getAddress() + " 价格:" + order.getMinPrice()+"—"+order.getMaxPrice());
                    myAgent.addBehaviour(new landlordDealTender(myAgent,tender,msg.getSender()));
                } catch (Codec.CodecException e) {
                    e.printStackTrace();
                } catch (OntologyException e) {
                    e.printStackTrace();
                }

            }else if(msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                //监听客户端协商消息
                try {
                    ContentElement ce = myAgent.getContentManager().extractContent(msg);
                    System.out.println(myAgent.getName()+" 收到房客的协商消息");
                    Action act = (Action) ce;
                    if(act.getAction() instanceof Negotiation){
                        Negotiation negotiation = (Negotiation)act.getAction();
                        Random r=new Random();
                        int a=r.nextInt(2);
                        if(a<1){
                            negotiation.setResult(0);
                        }else{
                            negotiation.setResult(1);
                            negotiation.setActualPrice(5);
                        }

                        //回复给房客
                        Action sendAct = new Action();
                        sendAct.setActor(myAgent.getAID());
                        sendAct.setAction(negotiation);

                        ACLMessage message = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                        message.addReceiver(msg.getSender());
                        message.setLanguage(codec.getName());
                        message.setOntology(ontology.getName());

                        myAgent.getContentManager().fillContent(message, sendAct);
                        //发消息
                        myAgent.send(message);

                    }
                } catch (Codec.CodecException e) {
                    e.printStackTrace();
                } catch (OntologyException e) {
                    e.printStackTrace();
                }
            }
            /*
               可以扩展监听其它类型的消息
             */
        }else{
            block();
        }

    }
}
