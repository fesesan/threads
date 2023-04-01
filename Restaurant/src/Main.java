public class Main {


    public static void main(String[] args) {
        Restaurant mcDonalds = new Restaurant();

        Thread clienteJose = new Thread( new TarefaComerNoLocal(mcDonalds), "Jose" );

        //Thread clienteJoao = new Thread( new TarefaPedirParaLevar(mcDonalds), "Joao");
        //Thread clientePedro = new Thread(new TarefaPedirDelivery(mcDonalds), "Pedro");

        Thread garcomMessias = new Thread(new TarefaAtenderMesa(mcDonalds), "Messias");
        Thread motoboyAugusto = new Thread(new TarefaEntregarPedidoDelivery(mcDonalds), "Augusto");

        clienteJose.start();
        //clienteJoao.start();
        //clientePedro.start();
        garcomMessias.start();
        motoboyAugusto.start();

    }
}