package multiAgent.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import multiAgent.behavior.selectListener;
import multiAgent.ontology.BidOntology;
import multiAgent.util.DFUtil;


/**
 * Created by zy on 17/5/6.
 */
public class selectAgent extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    //setup方法，负责为agent各个属性赋值，并且注册到dfAgent上
    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        DFUtil.registerService(this,"select");
        addBehaviour(new selectListener(this));
    }
}
