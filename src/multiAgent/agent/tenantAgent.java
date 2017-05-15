package multiAgent.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import multiAgent.behavior.listener.tenantApiListener;
import multiAgent.behavior.listener.tenantListener;
import multiAgent.behavior.logical.tenantBackOrderResult;
import multiAgent.ontology.Bid;
import multiAgent.ontology.BidOntology;
import service.common.agentHandler;
import util.CondVar;

import java.util.ArrayList;
import java.util.List;

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
        addBehaviour(new tenantBackOrderResult(null,null));
    }

//    public boolean done(){
//        //结束生命周期
//        return true;
//    }

    public void takeDown(){
        System.out.println("tenantAgent 被销毁");
        setEnabledO2ACommunication(false,0);
    }

}
