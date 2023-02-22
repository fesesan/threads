import static java.lang.System.out;

public class Sugar {
    public Sugar(){
        System.out.println("***** Pegando Açucar... ***** \n");
        if(Thread.interrupted()){
            out.println(Thread.currentThread().getName() + "..parece que está faltando algo para o café... Tivemos que abortar...");
        }
    }
}
