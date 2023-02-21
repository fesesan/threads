import java.util.concurrent.ThreadLocalRandom;

public class GroundCoffee {

    public GroundCoffee(){
        System.out.println("***** Pegando Pó de Café... ***** \n");
        if (coffeeItsOver())
            throw new RuntimeException("***** Ops...O café acabou! ***** \n");
    }

    private boolean coffeeItsOver(){
        return (ThreadLocalRandom.current().nextInt() % 2) == 0;
    }
}
