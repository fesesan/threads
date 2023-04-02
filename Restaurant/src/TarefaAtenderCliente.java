public class TarefaAtenderCliente implements Runnable {
    private Restaurant restaurant;

    public TarefaAtenderCliente(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        restaurant.atenderCliente();
    }
}
