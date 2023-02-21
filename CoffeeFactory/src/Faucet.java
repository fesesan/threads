import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Faucet {

    public void open(){
        System.out.println("***** Torneira Aberta... ***** \n");
        if(waterItsOver()){
            close();
            throw new RuntimeException("***** Ops... A água acabou! ***** \n");
        }
    }

    private boolean waterItsOver() {
        return (ThreadLocalRandom.current().nextInt() % 2) == 0;
    }

    public List<Container> putWaterIn(List<Container> containers) throws InterruptedException {
        System.out.println("***** Colocando Água nos copos... ***** \n");
        Thread.sleep(3000);
        containers.forEach(container -> container.isFull = true);
        return containers;
    }

    public void close(){
        System.out.println("***** Torneira Fechada... ***** \n");
    }
}




