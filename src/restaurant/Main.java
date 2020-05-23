package restaurant;

import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {

        ArrayBlockingQueue<Order> customerWaiter = new ArrayBlockingQueue<>(10, true);
        ArrayBlockingQueue<Order> waiterCook = new ArrayBlockingQueue<>(10, true);
        ArrayBlockingQueue<Order> cookCustomer = new ArrayBlockingQueue<>(10, true);

        new Thread(new CustomerPutOrder(customerWaiter,cookCustomer)).start();
        new Thread(new WaiterOrder(customerWaiter, waiterCook)).start();
        new Thread(new CookOrder(waiterCook, cookCustomer)).start();
    }
}
 class CustomerPutOrder implements Runnable{
    private ArrayBlockingQueue<Order> customerWaiter;
    private ArrayBlockingQueue<Order> cookCustomer;

     public CustomerPutOrder(ArrayBlockingQueue<Order> customerWaiter, ArrayBlockingQueue<Order> cookCustomer) {
         this.customerWaiter = customerWaiter;
         this.cookCustomer = cookCustomer;
     }

     @Override
     public void run() {
         while (!Thread.currentThread().isInterrupted()){
             try {
                 Thread.sleep(5000);
                 Order order = new Order();
                 order.setPropertyOrder();
                 customerWaiter.put(order);
                 System.out.println("CustomerPutOrder " + order.getOrderContent());
                 Order order1 = cookCustomer.take();
                 System.out.println("CustomerGetOrder" + order1.getOrderContent());
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
 }

 class WaiterOrder implements Runnable {
     private ArrayBlockingQueue<Order> customerWaiter;
     private ArrayBlockingQueue<Order> waiterCook;

     public WaiterOrder(ArrayBlockingQueue<Order> customerWaiter, ArrayBlockingQueue<Order> waiterCook) {
         this.customerWaiter = customerWaiter;
         this.waiterCook = waiterCook;
     }

     @Override
     public void run() {
         while (!Thread.currentThread().isInterrupted()){
             System.out.println("WaiterGetOrder " + Thread.currentThread().getState());
             try {
                 Thread.sleep(5000);
                 Order order = customerWaiter.take();
                 System.out.println("WaiterTakeCustomer" + order.getOrderContent());
                 waiterCook.put(order);
                 System.out.println("WaiterPutCook" + order.getOrderContent());
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
 }

 class CookOrder implements Runnable{
    private ArrayBlockingQueue<Order> waiterCook;
    private ArrayBlockingQueue<Order> cookCustomer;

     public CookOrder(ArrayBlockingQueue<Order> waiterCook, ArrayBlockingQueue<Order> cookCustomer) {
         this.waiterCook = waiterCook;
         this.cookCustomer = cookCustomer;
     }

     @Override
     public void run() {
       while (!Thread.currentThread().isInterrupted()) {
           System.out.println("CookOrder " + Thread.currentThread().getState());
           try {
               Thread.sleep(5000);
               Order order = waiterCook.take();
               System.out.println("Cook get from waiter " + order.getOrderContent());
               cookCustomer.put(order);
               System.out.println("Cook take customer " + order.getOrderContent());
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
     }
 }