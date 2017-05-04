package multiAgent.agent;

import jade.content.*;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

/**
 * Created by H77 on 2017/5/4.
 */
public class demoAgent extends Agent {

    Behaviour oneShot = new OneShotBehaviour(this) {
        @Override
        public void action() {
            System.out.println("hello Agent");
        }
    };

    protected void setup(){
        addBehaviour(oneShot);
    }


}
