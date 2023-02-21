import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class CoffeeFactory {
    private List<Cup> cups;
    private CoffeeMachine coffeeMachine;
    private GroundCoffee groundCoffee;
    private Faucet faucet;
    private Sugar sugar;

    public CoffeeFactory() {
    }

    public void start() throws InterruptedException {
        out.println("**** Começou a preparar o Café...**** \n");

        Thread t1 = new Thread(() -> setCups(2), "CupsThread");
        Thread t2 = new Thread(this::setGroudCoffee, "GroundCoffeeThread");
        Thread t3 = new Thread(this::setSugar, "SugarThread");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        out.println("***** Finalizou o preparo do Café ****** \n");
    }

    private void setGroudCoffee() {
        out.println(Thread.currentThread().getName() + "\n");
        groundCoffee = new GroundCoffee();
    }

    private void setCups(int quantity) {
        out.println(Thread.currentThread().getName() + "\n");
        setCups(getCups(quantity));
    }

    private void setSugar(){
        out.println(Thread.currentThread().getName() + "\n");
        sugar = new Sugar();
    }

    private List<Cup> getCups(int quantity){
       List<Cup> cups = new ArrayList<>();
       for(int i = 0; i < quantity; i++ )
           cups.add(new Cup());
       return cups;
    }

    public void setCups(List<Cup> cups) {
        this.cups = cups;
    }
}
