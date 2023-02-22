import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;

public class GroundCoffee {

    public GroundCoffee(){
        try {
            System.out.println("***** Pegando Pó de Café... ***** \n");

            if (coffeeItsOver())
                throw new InterruptedException("***** Ops...O café acabou! ***** \n");

            System.out.println("***** Pó de Café separado! ***** \n");

        }catch (InterruptedException e){
            out.println(Thread.currentThread().getName() + " " + e.getMessage() + " Tivemos que abortar...");
            Thread.currentThread().getThreadGroup().interrupt();
        }
    }

    private boolean coffeeItsOver(){
        return (ThreadLocalRandom.current().nextInt() % 2) == 0;
    }
}
