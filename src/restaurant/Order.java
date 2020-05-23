package restaurant;

import java.util.Random;

public class Order {

    String[] content = {"FISH", "MEAT", "SALAD"};

    private String orderContent;

    public Order() {
    }

    public Order(String orderContent) {
        setOrderContent(orderContent);
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        if(!orderContent.equals(null))
        {this.orderContent = orderContent;}
    }

    public void setPropertyOrder(){
        setOrderContent(content[(int) (Math.random()* 3)]);
    }
}
