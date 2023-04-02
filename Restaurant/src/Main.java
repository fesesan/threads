public class Main {


    public static void main(String[] args) {
        Restaurant mcDonalds = new Restaurant();

        Thread clienteJose = new Thread( new TarefaComerNoLocal(mcDonalds), "Jose" );
        Thread clienteJeremias = new Thread( new TarefaComerNoLocal(mcDonalds), "Jeremias" );
        Thread clienteMoises = new Thread( new TarefaComerNoLocal(mcDonalds), "Moises" );

        Thread clienteJoao = new Thread( new TarefaPedirParaLevar(mcDonalds), "Joao");
        Thread clientePedro = new Thread(new TarefaPedirDelivery(mcDonalds), "Pedro");

        Thread garcomMessias = new Thread(new TarefaAtenderCliente(mcDonalds), "Messias");
        Thread motoboyAugusto = new Thread(new TarefaEntregarPedidoDelivery(mcDonalds), "Augusto");

        clienteJose.start();
        clienteJeremias.start();
        clienteMoises.start();
        //garcomMessias.start();


        //clienteJoao.start();
        //clientePedro.start();
        //motoboyAugusto.start();

    }
}