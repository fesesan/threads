public class Main {


    public static void main(String[] args) {
        Restaurant outBack = new Restaurant( new CoordenadorDeMesas(2));

        Thread clienteJose = new Thread( new TarefaComerNoLocal(outBack), "cliente Jose" );
        Thread clienteJeremias = new Thread( new TarefaComerNoLocal(outBack), "cliente Jeremias" );
        Thread clienteMoises = new Thread( new TarefaComerNoLocal(outBack), "cliente Moises" );

        Thread clienteJoao = new Thread( new TarefaPedirParaLevar(outBack), "cliente Joao");
        Thread clientePedro = new Thread(new TarefaPedirDelivery(outBack), "cliente Pedro");


        Thread garcomMessias = new Thread(new TarefaAtenderCliente(outBack), "garcom Messias");
        garcomMessias.setDaemon(true);

        Thread garcomAbraao = new Thread(new TarefaAtenderCliente(outBack), "garcom Abraao");
        garcomAbraao.setDaemon(true);

        Thread garcomNoe = new Thread(new TarefaAtenderCliente(outBack), "garcom Noe");
        garcomNoe.setDaemon(true);


        Thread motoboyAugusto = new Thread(new TarefaEntregarPedidoDelivery(outBack), "Augusto");

        clienteJose.start();
        clienteJeremias.start();
        clienteMoises.start();

        garcomMessias.start();
        garcomAbraao.start();
        garcomNoe.start();

        //clienteJoao.start();
        //clientePedro.start();
        //motoboyAugusto.start();

    }
}