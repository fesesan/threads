
public class Restaurant {

    CoordenadorDeMesas coordenadorDeMesas;

    public Restaurant(CoordenadorDeMesas coordenadorDeMesas) {
        this.coordenadorDeMesas = coordenadorDeMesas;
    }

    public void paraComerAgora() throws InterruptedException {
            String nome = Thread.currentThread().getName();

            Mesa mesa = this.coordenadorDeMesas.ocuparMesa();

            while (!mesa.isEmAtendimento())
                    mesa.aguardarAtendimento();

            System.out.println(nome + " faz Pedido");
            System.out.println(nome + " aguarda o preparo");
            System.out.println(nome + " come");
            System.out.println(nome + " faz o pagamento");
            System.out.println(nome + " vai embora");

            coordenadorDeMesas.desocuparMesa(mesa);
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

    public void atenderMesa(Garcom garcom) throws InterruptedException {
        Mesa mesa = coordenadorDeMesas.obterMesaParaAtender();

        garcom.atenderMesa(mesa);

        System.out.println(garcom.nome + " anota o pedido");
        System.out.println(garcom.nome + " envia o pedido para a cozinha");
        System.out.println(garcom.nome + " aguarda pedido ficar pronto");
        System.out.println(garcom.nome + " entrega pedido na mesa");
        System.out.println(garcom.nome + " aguarda cliente comer");
        System.out.println(garcom.nome + " recebe o pagamento");
        System.out.println(garcom.nome + " limpa mesa");
    }

    public void entregarPedido(){
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " verifica se tem pedido pronto");
        System.out.println(nome + " retira o pedido");
        System.out.println(nome + " leva o pedido ate o cliente");
        System.out.println(nome + " vai embora");
    }
}
