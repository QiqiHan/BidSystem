package multiAgent.behavior;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Order;

/**
 * Created by zy on 17/5/6.
 */
public class consultListener extends CyclicBehaviour {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();
    private Agent agent;

    public consultListener(Agent agent){
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
                    System.out.println("收到"+o.getCustomer()+"客户的订单");
                    //下面是传递到select
                    //先去df中搜索相应的select
                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType("select");
                    template.addServices(sd);
                    DFAgentDescription[] result = DFService.search(agent,template);
                    AID aid = result[0].getName();
                    //搜索结束，组装传输的action
                    Action sendAct = new Action();
                    sendAct.setActor(aid);
                    sendAct.setAction(o);
                    //action组装完成，组装aclmessage
                    ACLMessage al  = new ACLMessage(ACLMessage.QUERY_REF);
                    al .addReceiver(aid);
                    al .setLanguage(codec.getName());
                    al .setOntology(ontology.getName());
                    agent.getContentManager().fillContent(al,sendAct);
                    //发消息
                    agent.send(al);
                }
            } catch (Codec.CodecException e) {
                e.printStackTrace();
            } catch (OntologyException e) {
                e.printStackTrace();
            } catch (FIPAException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}
