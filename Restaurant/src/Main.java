public class Main {


    public static void main(String[] args) {
        Restaurant outBack = new Restaurant( new CoordenadorDeMesas(2));

        Thread clienteJose = new Thread( new TarefaComerNoLocal(outBack), "Jose" );
        Thread clienteJeremias = new Thread( new TarefaComerNoLocal(outBack), "Jeremias" );
        Thread clienteMoises = new Thread( new TarefaComerNoLocal(outBack), "Moises" );

        Thread clienteJoao = new Thread( new TarefaPedirParaLevar(outBack), "Joao");
        Thread clientePedro = new Thread(new TarefaPedirDelivery(outBack), "Pedro");


        Thread garcomMessias = new Thread(new TarefaAtenderCliente(outBack), "Messias");
        Thread motoboyAugusto = new Thread(new TarefaEntregarPedidoDelivery(outBack), "Augusto");

        clienteJose.start();
        clienteJeremias.start();
        clienteMoises.start();
        //garcomMessias.start();


        //clienteJoao.start();
        //clientePedro.start();
        //motoboyAugusto.start();

    }
}