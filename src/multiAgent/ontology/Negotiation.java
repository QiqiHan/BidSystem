package multiAgent.ontology;

import jade.content.AgentAction;

/**
 * Created by mac on 17/5/8.
 */
public class Negotiation implements AgentAction {

    private int upPrice;

    private int lowPrice;

    private int result;//1表示同意，0表示拒绝，-1表示还未处理

    private int actualPrice;

    public Negotiation(int upPrice, int lowPrice, int result,int actualPrice) {
        this.upPrice = upPrice;
        this.lowPrice = lowPrice;
        this.result = result;
        this.actualPrice=actualPrice;
    }

    public Negotiation(){}


    public int getUpPrice() {
        return upPrice;
    }

    public void setUpPrice(int upPrice) {
        this.upPrice = upPrice;
    }

    public int getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }
}
