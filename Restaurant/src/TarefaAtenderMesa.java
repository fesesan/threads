public class TarefaAtenderMesa implements Runnable {
    private Restaurant restaurant;

    public TarefaAtenderMesa(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        restaurant.atenderMesa();
    }
}
