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
import multiAgent.ontology.Order;

/**
 * Created by zy on 17/5/6.
 */
public class selectListener extends CyclicBehaviour {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();
    private Agent agent;

    public selectListener(Agent agent){
            this.agent = agent;
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontology.getName()));
        ACLMessage msg = agent.receive(mt);
        if (msg != null) {
            try {
                ContentElement ce = agent.getContentManager().extractContent(msg);
                Action act = (Action)ce;
                if(act.getAction() instanceof Order){
                    Order o = (Order) act.getAction();
                    System.out.println("收到筛选条件信息"+o.getAddress());
                    /**
                     * todo
                     * 下面是去查询并且筛选数据，然后生成招标书，并且发送给房东agent
                     */
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
}
