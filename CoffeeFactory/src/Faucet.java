import java.util.List;

public class Faucet {

    public void open(){
        System.out.println("***** Torneira Aberta... ***** \n");
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




