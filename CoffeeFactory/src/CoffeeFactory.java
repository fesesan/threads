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

            Thread tCup = new Thread(coffeeThreads, () -> setCups(2), "CupsThread");
            Thread tGround = new Thread(coffeeThreads, this::setGroudCoffee, "GroundCoffeeThread");
            Thread tSugar = new Thread(coffeeThreads, this::setSugar, "SugarThread");
            Thread tWaterOnCup = new Thread(coffeeThreads, () -> putWaterOnCup(cups), "putWaterOnCupThread");

            tCup.start();
            tGround.start();
            tSugar.start();

            tCup.join();
            tWaterOnCup.start();

            tGround.join();
            tSugar.join();
            tWaterOnCup.join();

            makeCoffee();

        } catch (Exception e){
            out.println("**** Tivemos um problema ao preparar o seu café...vamos ter que cancelar! **** \n");
        } finally {
            out.println("****-------------------- Finalizou o preparo do Café --------------------**** \n");
        }
    }

    private void setGroudCoffee() {
        groundCoffee = new GroundCoffee();
    }

    private void setCups(int quantity) {
        try {
            Thread.sleep(5000);
            setCups(getCups(quantity));
        } catch (InterruptedException e){
            out.println(Thread.currentThread().getName() + " -  Tive que abortar...parece que tá faltando algo para o café...");
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
        try {
            faucet.open();
            faucet.putWaterIn(cups);
        } catch (InterruptedException e) {
            out.println(Thread.currentThread().getName() + " " + e.getMessage() + " Tivemos que abortar...");
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
