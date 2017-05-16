package multiAgent.behavior.logical;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import multiAgent.agent.selectAgent;
import multiAgent.behavior.message.selectPropose;
import multiAgent.ontology.Order;
import multiAgent.ontology.Tender;

import java.util.List;

/**
 * Created by H77 on 2017/5/6.
 * 这个Behaviour主要是根据订单请求信息，筛选搜索对应的潜在房东
 * 并向这些房东发送招标书
 */
public class selectAnalysis extends OneShotBehaviour {

    private Order order = null;
    private selectAgent agent = null;
    public selectAnalysis(Agent agent,Order order){
        super(agent);
        this.agent = (selectAgent)agent;
        this.order = order;
    }
    public void action() {
        Tender tender = new Tender(1,
                order.getId(),
                order.getCustomerId(),
                order.getMinPrice(),
                order.getAddress(),
                order.getStartTime(),
                order.getEndTime(),
                order.getRoomType(),
                order.getRoomNum(),
                order.getCreateTime(),
                order.getFacilities(),
                order.getHotelType(),
                "",
                order.getSource());
        //创建Agent的例子
        List<AID> aids  = agent.createAgent();
        myAgent.addBehaviour(new selectPropose(myAgent,tender,aids));
    }



}
