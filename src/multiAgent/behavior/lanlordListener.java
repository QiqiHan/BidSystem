package multiAgent.behavior;

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
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Tender;

/**
 * Created by H77 on 2017/5/6.
 */
public class lanlordListener extends CyclicBehaviour {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    private Agent myAgent = null;
    public lanlordListener(Agent agent){
        myAgent = agent;
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
                   System.out.println("lanlordAgent收到信息地址"+tender.getAddress()+" 价格"+tender.getPrice());
                   //暂时随机
                   ACLMessage reply = msg.createReply();
                   if(type == 0){
                       reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                       reply.setContent("lanlordAgent接受竞标");
                   }else if ( type == 1){
                       reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                       reply.setContent("lanlordAgent拒绝竞标");
                   }
                  myAgent.send(reply);
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
