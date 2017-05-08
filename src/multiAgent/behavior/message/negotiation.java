package multiAgent.behavior.message;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.List;
import multiAgent.ontology.Bid;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Negotiation;


/**
 * Created by  on 17/5/8.
 */
public class negotiation  extends OneShotBehaviour {

    private List Bids;
    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    public negotiation(Agent agent,List Bids){
        super(agent);
        this.Bids = Bids;
    }

    public void action() {
        try {
            for (int i = 0 ; i<Bids.size() ;i ++) {
                Bid bid = (Bid)Bids.get(i);
                ACLMessage message = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                message.addReceiver(bid.getLandlordId());
                message.setLanguage(codec.getName());
                message.setOntology(ontology.getName());

                //组装Negotitation对象，这边其实需要计算大概还价多少
                Negotiation negotiation = new Negotiation(1, 10, -1,0);
                Action sendAct = new Action();
                sendAct.setActor(myAgent.getAID());
                sendAct.setAction(negotiation);

                myAgent.getContentManager().fillContent(message, sendAct);
                //发消息
                myAgent.send(message);
            }
        }catch (Codec.CodecException e){
            e.printStackTrace();
        } catch (UngroundedException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        }
    }


}
