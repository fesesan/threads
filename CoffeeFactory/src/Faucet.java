import java.util.List;

public class Faucet {

    public void open(){
        System.out.println("***** Torneira Aberta... ***** \n");
    }

    public List<Container> putWaterIn(List<Container> containers){
        System.out.println("***** Colocando Água nos copos... ***** \n");
        containers.forEach(container -> container.isFull = true);
        return containers;
    }

    public void close(){
        System.out.println("***** Torneira Fechada... ***** \n");
    }
}




