package multiAgent.ontology;

import jade.content.Concept;
import jade.core.AID;

/**
 * Created by zcy on 2017/5/8.
 *
 */
public class Room implements Concept{
    private String name;
    private AID owner;
    private RoomType type;

    public Room(String name,AID owner,RoomType type){
        this.name = name;
        this.owner = owner;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AID getOwner() {
        return owner;
    }

    public void setOwner(AID owner) {
        this.owner = owner;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}
