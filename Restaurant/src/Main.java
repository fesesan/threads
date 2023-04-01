public class Main {


    public static void main(String[] args) {
        Restaurant mcDonalds = new Restaurant();

        Thread clienteJose = new Thread( new TarefaComerNoLocal(mcDonalds), "Jose" );
        Thread clienteJoao = new Thread( new TarefaPedirParaLevar(mcDonalds), "Joao");
        Thread clientePedro = new Thread(new TarefaPedirDelivery(mcDonalds), "Pedro");

        clienteJose.start();
        clienteJoao.start();
        clientePedro.start();

    }
}