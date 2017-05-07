package multiAgent.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import multiAgent.behavior.listener.landlordListener;
import multiAgent.ontology.BidOntology;
import multiAgent.util.DFUtil;

/**
 * Created by H77 on 2017/5/6.
 */
public class landlordAgent extends Agent{

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();

    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        DFUtil.registerService(this,"landlord");
        addBehaviour(new landlordListener(this));
    }

}
