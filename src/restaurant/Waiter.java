package restaurant;

public class Waiter {
    private Order order;

    public Waiter() {
    }

    public Waiter(Order order) {
        setOrder(order);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
