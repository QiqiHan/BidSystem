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
import multiAgent.ontology.Bid;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Negotiation;
import multiAgent.ontology.Tender;

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
                   int type = (int)(Math.random()*2);
                ContentElement ce = null;
                try {
                   ce = myAgent.getContentManager().extractContent(msg);
                   Action act = (Action) ce;
                   Tender tender = (Tender) act.getAction();
                   System.out.println("lanlord"+myAgent.getName()+"收到信息地址"+tender.getAddress()+" 价格"+tender.getPrice());
                   //暂时随机 且成功失败都反馈个Bid 好统计
                   ACLMessage reply = msg.createReply();
                   if(type == 0){
                       reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                   }else if (type == 1) {
                       reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                   }

                 Bid bid = new Bid(tender.getSource(),"南京大酒店",100,myAgent.getAID());
                 Action info = new Action();
                 info.setActor(myAgent.getAID());
                 info.setAction(bid);
                 myAgent.getContentManager().fillContent(reply,info);
                 myAgent.send(reply);
                } catch (Codec.CodecException e) {
                    e.printStackTrace();
                } catch (OntologyException e) {
                    e.printStackTrace();
                }
            }else if(msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                //监听客户端协商消息
                try {
                    ContentElement ce = myAgent.getContentManager().extractContent(msg);
                    System.out.println("收到房客的协商消息");
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
