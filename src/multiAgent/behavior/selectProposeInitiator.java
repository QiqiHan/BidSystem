package multiAgent.behavior;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;


import java.util.List;
import java.util.Vector;

/**
 * Created by H77 on 2017/5/6.
 */
public class selectProposeInitiator extends ContractNetInitiator {

    public selectProposeInitiator(Agent a, ACLMessage cfp) {
        super(a, cfp);
    }

    protected void handlePropose(ACLMessage propose, Vector acceptances) {
        if(propose.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
           acceptances.add(propose.getSender());
        }
    }
    public List<AID> getAcceptAIDs (){
        Vector acceptances = (Vector) this.getDataStore().get(this.ALL_ACCEPTANCES_KEY);
        return getAcceptAIDs();
    }

}
