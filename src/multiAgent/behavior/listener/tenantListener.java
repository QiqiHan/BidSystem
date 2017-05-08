package multiAgent.behavior.listener;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import multiAgent.behavior.message.negotiation;
import multiAgent.ontology.Bid;
import multiAgent.ontology.BidOntology;
import multiAgent.ontology.Negotiation;
import multiAgent.ontology.OrderResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by H77 on 2017/5/6.
 */
public class tenantListener extends CyclicBehaviour {

    private Codec codec = new SLCodec();
    private Ontology ontology = BidOntology.getInstance();
    private int responseNum = 0;
    private int lowerPriceNum = 0;
    private int currentResponse = 0;
    private List<Bid> bids = new ArrayList<Bid>();

    public tenantListener(Agent agent){
        super(agent);
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontology.getName()));
        ACLMessage msg = myAgent.receive();
        if(msg != null){
            if(msg.getPerformative() == ACLMessage.INFORM){
                //得到返回的房东竞标集合，下一步是与房东之间的智能讨价还价过程
                System.out.println(myAgent.getName() +" 收到筛选结果");
                try {
                    ContentElement ce = myAgent.getContentManager().extractContent(msg);
                    Action act = (Action) ce;
                    if(act.getAction() instanceof  OrderResponse){
                        OrderResponse orderResponse = (OrderResponse)act.getAction();
                        responseNum = orderResponse.getResponseNum();
                        for(int i = 0 ; i<orderResponse.getBids().size();i++){
                            bids.add((Bid)orderResponse.getBids().get(i));
                        }
                        myAgent.addBehaviour(new negotiation(myAgent,orderResponse.getBids()));
                    }
                }catch (Codec.CodecException e){
                    e.printStackTrace();
                } catch (UngroundedException e) {
                    e.printStackTrace();
                } catch (OntologyException e) {
                    e.printStackTrace();
                }
            }else if(msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                //收到房源的回应
                currentResponse++;
                try {
                    ContentElement ce = myAgent.getContentManager().extractContent(msg);
                    Action act = (Action) ce;
                    if(act.getAction() instanceof  Negotiation){
                        Negotiation negotiation = (Negotiation)act.getAction();
                        if(negotiation.getResult() == 1){
                            System.out.println("房源"+msg.getSender().getName()+"接收降价");
                            System.out.println("降低"+negotiation.getActualPrice());
                            lowerPriceNum ++ ;
                        }else if(negotiation.getResult() == 0){
                            System.out.println("房源"+msg.getSender().getName()+"拒绝降价");
                        }else {
                            System.out.println("房源" + msg.getSender().getName() + "未响应降价");
                        }
                    }

                    if(currentResponse == responseNum){
                        //收到全部的回复
                        if(lowerPriceNum == 0){
                            //所有房源都不降价
                            System.out.println("所有房源都不降价了");
                            //返回现在的最好的房源
                        }else{
                            lowerPriceNum = 0;
                            currentResponse = 0;
                            responseNum = lowerPriceNum;

                        }
                    }

                } catch (Codec.CodecException e) {
                    e.printStackTrace();
                } catch (OntologyException e) {
                    e.printStackTrace();
                }
            }else{
                //用来测试的
                String content = msg.getContent();
                System.out.println("房客Agent："+content);
            }
            /*
              可以扩展监听其它类型的Message
             */
        }else{
            block();
        }
    }
}
