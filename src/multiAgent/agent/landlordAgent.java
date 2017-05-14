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

    //营销策略
    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();
    //landlordAgent 生命周期
    private boolean isDone = false;

    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        DFUtil.registerService(this,"landlord");
        System.out.println("创建 landlordAgent");
        addBehaviour(new landlordListener(this));
    }

    protected boolean done(){
        return isDone;
    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean done) {
        isDone = done;
    }

}
