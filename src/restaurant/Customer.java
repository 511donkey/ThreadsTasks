package restaurant;

public class Customer {
    private Order order;

    public Customer() {
    }

    public Customer(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
