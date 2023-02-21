import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class CoffeeFactory {
    private List<Container> cups;
    private CoffeeMachine coffeeMachine;
    private GroundCoffee groundCoffee;
    private Faucet faucet;
    private Sugar sugar;

    public CoffeeFactory() {
    }

    public void start() {
        out.println("****-------------------- Começou a preparar o Café --------------------**** \n");

        ThreadGroup coffeeThreads = new ThreadGroup("coffeeThreads");
        try {

            Thread t1 = new Thread(coffeeThreads, () -> setCups(2), "CupsThread");
            Thread t2 = new Thread(coffeeThreads, this::setGroudCoffee, "GroundCoffeeThread");
            Thread t3 = new Thread(coffeeThreads, this::setSugar, "SugarThread");
            Thread t4 = new Thread(coffeeThreads, () -> putWaterOnCup(cups), "putWaterOnCupThread");

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t4.start();

            t2.join();
            t3.join();
            t4.join();

            makeCoffee();

        } catch (InterruptedException e){
            out.println("**** Threads Ativas: " + coffeeThreads.activeCount());
            out.println("**** Tivemos um problema ao preparar o seu café...vamos ter que cancelar! **** \n");
            coffeeThreads.interrupt();
            out.println("**** Threads Ativas: " + coffeeThreads.activeCount());
        } finally {
            out.println("****-------------------- Finalizou o preparo do Café --------------------**** \n");
        }
    }

    private void setGroudCoffee() {
        groundCoffee = new GroundCoffee();
    }

    private void setCups(int quantity) {
        try {
            Thread.sleep(30000);
            setCups(getCups(quantity));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void setSugar(){
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
        setCoffeeMachine(new CoffeeMachine());
        coffeeMachine.putWater(cups);
        coffeeMachine.putCoffee(groundCoffee);
        coffeeMachine.on();
        putSugar();
        coffeeMachine.off();
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
