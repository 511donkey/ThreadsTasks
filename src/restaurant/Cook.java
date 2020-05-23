package restaurant;

public class Cook {
    private Order order;

    public Cook() {
    }

    public Cook(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
