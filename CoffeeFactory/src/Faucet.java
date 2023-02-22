import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;

public class Faucet {

    public void open(){
        System.out.println("***** Torneira Aberta... ***** \n");
        try {
            if(waterItsOver()){
                throw new InterruptedException("***** Ops... A água acabou! ***** \n");
            }
        }catch (InterruptedException e){
            out.println(Thread.currentThread().getName() + " " + e.getMessage() + " Tivemos que abortar...");
            Thread.currentThread().getThreadGroup().interrupt();
        }
    }

    private boolean waterItsOver() {
        return (ThreadLocalRandom.current().nextInt() % 2) == 0;
    }

    public List<Container> putWaterIn(List<Container> containers) throws InterruptedException {
        System.out.println("***** Colocando Água nos copos... ***** \n");
        if (Objects.isNull(containers)){
            out.println("***** Não recebi os copos... ***** \n");
            return null;
        }
        Thread.sleep(3000);
        if(Thread.interrupted()){
            out.println(Thread.currentThread().getName() + "..parece que está faltando algo para o café... Tivemos que abortar...");
            return containers;
        }
        containers.forEach(container -> container.isFull = true);
        return containers;
    }

    public void close(){
        System.out.println("***** Torneira Fechada... ***** \n");
    }
}




