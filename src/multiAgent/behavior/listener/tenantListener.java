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
import multiAgent.ontology.OrderResponse;

import jade.util.leap.List;


/**
 * Created by H77 on 2017/5/6.
 */
public class tenantListener extends CyclicBehaviour {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    public tenantListener(Agent agent){
        super(agent);
    }
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontology.getName()));
        ACLMessage msg = myAgent.receive();
        if(msg != null){
            if(msg.getPerformative() == ACLMessage.INFORM){
                //得到返回的房东竞标集合，下一步是与房东之间的智能讨价还价过程
                try {
                    ContentElement ce = myAgent.getContentManager().extractContent(msg);
                    Action act = (Action)ce;
                    if(act.getAction() instanceof OrderResponse){
                        OrderResponse response = (OrderResponse)act.getAction();
                        List bids = response.getBids();

                        for(int i = 0 ; i<bids.size() ; i++){
                            Bid bid = (Bid)bids.get(i);
                            System.out.println(bid.getLandlordId().getName() +"竞标___房间:"+bid.getRoom()+"   ___价格:"+bid.getPrice());
                        }
                    }
                } catch (Codec.CodecException e) {
                    e.printStackTrace();
                } catch (OntologyException e) {
                    e.printStackTrace();
                }
            }else{
                //用来测试的
                String content = msg.getContent();
                System.out.println("房客Agent："+content);
            }
            /*
              可以扩展监听其它类型的Message
             */
        }else{
            block();
        }
    }
}
