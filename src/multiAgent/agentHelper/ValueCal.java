package multiAgent.agentHelper;

import DO.tenant;
import multiAgent.ontology.Bid;
import multiAgent.ontology.Room;
import multiAgent.ontology.RoomType;
import sun.management.resources.agent;

import jade.util.leap.List;
import jade.util.leap.ArrayList;

/**
 * Created by zy on 17/5/8.
 */
public class ValueCal {
    private String character = null;

    //评估房源的类，目前为止只评估price，因为暂时没有别的因素
    public double ValCal(Bid bid){
        return bid.getPrice();
    }

    public List ChooseRoom(List bids, tenant user){
        List rooms = new ArrayList();

        if(user.getPreference().equals("economical")){
            int sum=0;
            for(int i=0;i<bids.size();i++){
                sum+=((Bid)bids.get(i)).getPrice();
            }
            int average = sum/(bids.size());
            for(int i=0;i<bids.size();i++){
                Bid tempbid = (Bid)bids.get(i);
                if(tempbid.getPrice()<average){
                    rooms.add(tempbid);
                }
            }

        }else if(user.getPreference().equals("comfortable")){
            for(int i=0;i<bids.size();i++){
                Bid tempbid = (Bid)bids.get(i);
                Room room = tempbid.getRoom();
                if(room.getType().equals(RoomType.Deluxe)){
                    rooms.add(tempbid);
                }

            }

        }else{

        }


        return rooms;
    }
}
