import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class CoffeeFactory {
    private List<Container> cups;
    private CoffeeMachine coffeeMachine;
    private GroundCoffee groundCoffee;
    private Faucet faucet;
    private Sugar sugar;

    public CoffeeFactory() {
    }

    public void start() throws InterruptedException {
        out.println("****-------------------- Começou a preparar o Café --------------------**** \n");

        Thread t1 = new Thread(() -> setCups(2), "CupsThread");
        Thread t2 = new Thread(this::setGroudCoffee, "GroundCoffeeThread");
        Thread t3 = new Thread(this::setSugar, "SugarThread");
        Thread t4 = new Thread(() -> putWaterOnCup(cups), "putWaterOnCupThread");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t4.start();

        t2.join();
        t3.join();
        t4.join();

        makeCoffee();

        out.println("****-------------------- Finalizou o preparo do Café --------------------**** \n");
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

    private List<Container> getCups(int quantity){
       List<Container> cups = new ArrayList<>();
       for(int i = 0; i < quantity; i++ )
           cups.add(new Cup());
       return cups;
    }

    public void setCups(List<Container> cups) {
        this.cups = cups;
    }

    private void putWaterOnCup(List<Container> cups) {
        out.println(Thread.currentThread().getName() + "\n");
        Faucet faucet = new Faucet();
        faucet.open();
        try {
            faucet.putWaterIn(cups);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            faucet.close();
        }
    }

    private void makeCoffee() throws InterruptedException {
        out.println(Thread.currentThread().getName() + "\n");
        setCoffeeMachine(new CoffeeMachine());
        coffeeMachine.putWater(cups);
        coffeeMachine.putCoffee(groundCoffee);
        coffeeMachine.on();
        putSugar();
        out.println("***** Seu Café está prontinho! :] ***** \n");
    }

    private void putSugar(){
        out.println("***** Adoçando... ***** \n");
    }

    public CoffeeMachine getCoffeeMachine() {
        return coffeeMachine;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }
}
