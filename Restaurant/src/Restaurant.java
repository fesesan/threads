
public class Restaurant {

    CoordenadorDeMesas coordenadorDeMesas;

    public Restaurant(CoordenadorDeMesas coordenadorDeMesas) {
        this.coordenadorDeMesas = coordenadorDeMesas;
    }

    public void paraComerAgora() throws InterruptedException {
            String nome = Thread.currentThread().getName();

            int numeroMesa = this.coordenadorDeMesas.ocuparMesa();

            System.out.println(nome + " senta na mesa: " + numeroMesa);
            System.out.println(nome + " faz Pedido");
            System.out.println(nome + " aguarda o preparo");
            System.out.println(nome + " come");
            System.out.println(nome + " faz o pagamento");
            System.out.println(nome + " vai embora");

            coordenadorDeMesas.desocuparMesa(numeroMesa);
    }

    public void paraLevar(){
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " entra na fila");
        System.out.println(nome + " aguarda sua vez");
        System.out.println(nome + " faz o pedido");
        System.out.println(nome + " faz o pagamento");
        System.out.println(nome + " espera pedido ficar pronto");
        System.out.println(nome + " retira pedido");
        System.out.println(nome + " vai embora");
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
}
