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
import multiAgent.behavior.consultListener;
import multiAgent.ontology.*;

/**
 * Created by h77 on 2017/5/5.
 * 简单的协商Agent
 */
public class consultAgent extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

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

        addBehaviour(new consultListener(this));
    }
}