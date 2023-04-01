import java.util.Arrays;
import java.util.LinkedList;

public class Restaurant {

    private LinkedList<Integer> mesasDisponiveis = new LinkedList<>(
            Arrays.asList(1,2,3,4,5)
    );

    private LinkedList<Integer> mesasOcupadas = new LinkedList<>();

    public Restaurant(){

    }

    public void praComerAgora(){
            String nome = Thread.currentThread().getName();

            System.out.println(nome + " senta na Mesa: " + pegaUmaMesa());
            System.out.println(nome + " faz Pedido");
            System.out.println(nome +" aguarda o preparo");
            System.out.println(nome + " come");
            System.out.println(nome + " faz o pagamento");
            System.out.println(nome + " vai embora");
    }

    public void paraLevar(){
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " entra na fila");
        System.out.println(nome + " aguarda sua vez");
        System.out.println(nome + " faz o pedido");
        System.out.println(nome + " faz o pagamento");
        System.out.println(nome + " espera pedido ficar pronto");
        System.out.println(nome + " retira e vai embora");
    }

    public void paraEntregar(){
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " entra no aplicativo");
        System.out.println(nome + " faz o pedido");
        System.out.println(nome + " faz o pagamento");
        System.out.println(nome + " aguarda o envio");
        System.out.println(nome + " recebe o pedido");
    }

    private int pegaUmaMesa(){
        int numeroDaMesa = mesasDisponiveis.remove();
        mesasOcupadas.add(numeroDaMesa);
        return numeroDaMesa;
    }
}
