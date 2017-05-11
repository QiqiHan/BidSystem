package multiAgent.ontology;

import jade.content.Concept;
import jade.core.AID;

/**
 * Created by zcy on 2017/5/8.
 *
 */
public class Room implements Concept{
    private String type;
    private AID owner;
    private int price;

    public Room(String type,AID owner,int price){
        this.type = type;
        this.owner = owner;
        this.price = price;
    }


    public AID getOwner() {
        return owner;
    }

    public void setOwner(AID owner) {
        this.owner = owner;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
