import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Restaurant {

    private CopyOnWriteArrayList<Integer> mesasDisponiveis = new CopyOnWriteArrayList<>(
            Arrays.asList(1,2,3)
    );

    CopyOnWriteArrayList<Integer> mesasOcupadas = new CopyOnWriteArrayList<>();
    ;

    public Restaurant() {
    }

    public void paraComerAgora() throws InterruptedException {
            String nome = Thread.currentThread().getName();

            int numeroMesa;
            synchronized (mesasDisponiveis){
                while (mesasDisponiveis.isEmpty())
                    aguardarMesa();
                numeroMesa = ocuparMesa();
            }
            System.out.println(nome + " senta na mesa: " + numeroMesa);
            System.out.println(nome + " faz Pedido");
            System.out.println(nome + " aguarda o preparo");
            System.out.println(nome + " come");
            System.out.println(nome + " faz o pagamento");
            System.out.println(nome + " vai embora");

            synchronized (mesasDisponiveis){
                desocuparMesa(numeroMesa);
                mesasDisponiveis.notifyAll();
            }
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

    public void atenderCliente(){
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " apresenta a casa");
        System.out.println(nome + " anota o pedido");
        System.out.println(nome + " envia o pedido para a cozinha");
        System.out.println(nome + " aguarda pedido ficar pronto");
        System.out.println(nome + " entrega pedido na mesa");
        System.out.println(nome + " aguarda cliente comer");
        System.out.println(nome + " recebe o pagamento");
        System.out.println(nome + " limpa mesa");
    }

    public void entregarPedido(){
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " verifica se tem pedido pronto");
        System.out.println(nome + " retira o pedido");
        System.out.println(nome + " leva o pedido ate o cliente");
        System.out.println(nome + " vai embora");
    }


    private int ocuparMesa(){
        int numeroDaMesa = mesasDisponiveis.remove(0);
        mesasOcupadas.add(numeroDaMesa);
        return numeroDaMesa;
    }

    private void desocuparMesa(Integer numeroMesa){
        String nome = Thread.currentThread().getName();
        mesasOcupadas.remove(numeroMesa);
        mesasDisponiveis.add(numeroMesa);
        System.out.println(nome + " liberou mesa " + numeroMesa + " para uso");
    }

    private void aguardarMesa() throws InterruptedException {
        String nome = Thread.currentThread().getName();
        System.out.println(nome + " esta aguardando uma mesa");
        mesasDisponiveis.wait();
    }
}
