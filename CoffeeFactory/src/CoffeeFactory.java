import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class CoffeeFactory {
    private List<Cup> cups;
    private CoffeeMachine coffeeMachine;
    private GroundCoffee groundCoffee;
    private Faucet faucet;

    public CoffeeFactory() {
    }

    public void start() throws InterruptedException {
        out.println("**** Começou a preparar o Café...**** \n");

        Thread t1 = new Thread(() -> { separeteCups(2); }, "CupsThread");
        Thread t2 = new Thread(() -> { separeteGroudCoffee(); }, "GroundCoffeeThread");

        t1.start();
        t2.start();

       // out.println("***** Pegou " + cups.size() + " Xícara(s) ******");
    }

    private void separeteGroudCoffee() {
        out.println(Thread.currentThread().getName());
        groundCoffee = new GroundCoffee();
    }

    public void separeteCups(int quantity) {
        out.println(Thread.currentThread().getName());
        setCups(getCups(quantity));
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
