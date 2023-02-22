import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;

public class Sugar {
    public Sugar(){
        System.out.println("***** Pegando Açucar... ***** \n");
        try {
            if(sugarItsOver()){
                throw new InterruptedException("***** Ops...O açucar acabou! ***** \n");
            }
            if(Thread.interrupted()){
                out.println(Thread.currentThread().getName() + "..parece que está faltando algo para o café... Tivemos que abortar...");
            }
            out.println("***** Açucar separado! ***** \n");
        }catch (InterruptedException e){
            out.println(Thread.currentThread().getName() + " " + e.getMessage() + " Tivemos que abortar...");
            Thread.currentThread().getThreadGroup().interrupt();
        }
    }

    private boolean sugarItsOver(){
        return (ThreadLocalRandom.current().nextInt() % 2) == 0;
    }
}
