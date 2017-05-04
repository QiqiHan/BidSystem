package multiAgent.agent;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import multiAgent.behavior.demoBehaviour;

/**
 * Created by H77 on 2017/5/4.
 */
public class demoAgent extends Agent {



    protected void setup(){
        Behaviour demo = new demoBehaviour();
        addBehaviour(demo);
    }


}
