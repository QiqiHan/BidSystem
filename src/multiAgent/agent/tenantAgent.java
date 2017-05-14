package multiAgent.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;
import multiAgent.behavior.listener.tenantApiListener;
import multiAgent.behavior.listener.tenantListener;
import multiAgent.behavior.message.tenantRequest;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Order;
import service.CondVar;

/**
 * Created by h77 on 2017/5/5.
 * 简单的房客Agent
 */
public class tenantAgent extends Agent {


    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();


    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        setEnabledO2ACommunication(true,10);

        Object[] args = getArguments();
        if (args.length > 0) {
            CondVar latch = (CondVar) args[0];
            latch.signal();
        }
        System.out.println("创建 tenantAgent");
        addBehaviour(new tenantListener(this));
        addBehaviour(new tenantApiListener(this));
    }

//    public boolean done(){
//        //结束生命周期
//        return true;
//    }

    public void takeDown(){
        setEnabledO2ACommunication(false,0);
    }

}
