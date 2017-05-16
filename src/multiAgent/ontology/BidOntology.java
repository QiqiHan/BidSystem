package multiAgent.ontology;

import jade.content.Concept;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;

import java.util.BitSet;

/**
 * Created by H77 on 2017/5/4.
 *
 * 这部分代码的目的是让Order实体能够通过一定规则编码，填充在ACLMessage的Content中，和序列化相似，Jade采用了这种方式。
 */
public class BidOntology extends Ontology{

    private static final String ONTOLOGY_NAME ="Room-order-ontology";

    private static final String Order = "Order";
    private static final String Order_Customer = "customer";
    private static final String Order_Address = "address";
    private static final String Order_RoomType = "roomType";
    private static final String Order_RoomNum = "roomNum";
    private static final String Order_Start = "start";
    private static final String Order_End = "end";
    private static final String Order_Price = "price";
    private static final String Order_SourceAid = "source";

    private static final String Tender = "Tender";
    private static final String Tender_Address = "address";
    private static final String Tender_Price = "price";
    private static final String Tender_SourceAid = "source";

    private static final String Bid = "Bid";
    private static final String Bid_Order = "orderId";
    private static final String Bid_Room = "room";
    private static final String Bid_Price = "price";
    private static final String Bid_Landlord = "landlordId";
    private static final String Bid_Type = "type";


    private static final String OrderResponse = "OrderResponse";
    private static final String OrderResponse_Order = "orderId";
    private static final String OrderResponse_Num = "responseNum";
    private static final String OrderResponse_Bids = "bids";

    private static final String Room = "Room";
    private static final String Room_Owner = "owner";
    private static final String Room_Type = "type";
    private static final String Room_Price = "price";

    private static final String Negotiation = "Negotiation";
    private static final String Negotiation_UpPrice = "upPrice";
    private static final String Negotiation_LowPrice = "lowPrice";
    private static final String Negotiation_Result="result";
    private static final String Negotiation_ActualPrice="actualPrice";

    private static final String MapObject = "MapObject";
    private static final String MapObject_Name="name";
    private static final String MapObject_Lng="lng";
    private static final String MapObject_Lat="lat";
    private static final String MapObject_Address="address";
    private static final String MapObject_Distance="distance";
    private static final String MapObject_Overall_rating="overall_rating";
    private static final String MapObject_Tag="tag";


    private static  Ontology theInstance = new BidOntology();

    public static Ontology getInstance(){
          return theInstance;
    }
    private BidOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        try {
            add(new AgentActionSchema(Order),Order.class);
            add(new AgentActionSchema(Tender),Tender.class);
            add(new ConceptSchema(Bid),Bid.class);
            add(new AgentActionSchema(OrderResponse),OrderResponse.class);
            add(new ConceptSchema(Room),Room.class);
            add(new AgentActionSchema(Negotiation),Negotiation.class);

            AgentActionSchema order = (AgentActionSchema)getSchema(Order);
            order.add(Order_Customer,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            order.add(Order_Address,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            order.add(Order_RoomType,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            order.add(Order_RoomNum,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            order.add(Order_Start,(PrimitiveSchema)getSchema(BasicOntology.DATE));
            order.add(Order_End,(PrimitiveSchema)getSchema(BasicOntology.DATE));
            order.add(Order_Price,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            order.add(Order_SourceAid,(ConceptSchema)getSchema(BasicOntology.AID));

            AgentActionSchema tender = (AgentActionSchema)getSchema(Tender);
            tender.add(Tender_Address,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            tender.add(Tender_Price,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            tender.add(Tender_SourceAid,(ConceptSchema)getSchema(BasicOntology.AID));

            ConceptSchema  bid = (ConceptSchema)getSchema(Bid);
            bid.add(Bid_Order,(ConceptSchema)getSchema(BasicOntology.AID));
            bid.add(Bid_Room,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            bid.add(Bid_Price,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            bid.add(Bid_Landlord,(ConceptSchema)getSchema(BasicOntology.AID));
            bid.add(Bid_Type,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));

            AgentActionSchema orderResponse = (AgentActionSchema)getSchema(OrderResponse);
            orderResponse.add(OrderResponse_Order,(ConceptSchema)getSchema(BasicOntology.AID));
            orderResponse.add(OrderResponse_Num,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            orderResponse.add(OrderResponse_Bids,(ConceptSchema)getSchema(Bid),0, ObjectSchema.UNLIMITED);

            ConceptSchema room = (ConceptSchema)getSchema(Room);
            room.add(Room_Owner,(ConceptSchema)getSchema(BasicOntology.AID));
            room.add(Room_Type,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            room.add(Room_Price,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));

            AgentActionSchema negotiation = (AgentActionSchema)getSchema(Negotiation);
            negotiation.add(Negotiation_UpPrice,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            negotiation.add(Negotiation_LowPrice,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            negotiation.add(Negotiation_Result,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            negotiation.add(Negotiation_ActualPrice,(PrimitiveSchema)getSchema(BasicOntology.INTEGER));

            ConceptSchema  mapObject = (ConceptSchema)getSchema(MapObject);
            mapObject.add(MapObject_Name,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            mapObject.add(MapObject_Address,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            mapObject.add(MapObject_Distance,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            mapObject.add(MapObject_Lat,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            mapObject.add(MapObject_Lng,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            mapObject.add(MapObject_Overall_rating,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            mapObject.add(MapObject_Tag,(PrimitiveSchema)getSchema(BasicOntology.STRING));

        } catch (OntologyException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args){
//        System.out.println(BidOntology.getInstance().getName());
//    }

}
