public class TarefaAtenderCliente implements Runnable {
    private Restaurant restaurant;

    public TarefaAtenderCliente(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            String nome = Thread.currentThread().getName();
            Garcom garcom = new Garcom(nome);
            restaurant.atenderMesa(garcom);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
