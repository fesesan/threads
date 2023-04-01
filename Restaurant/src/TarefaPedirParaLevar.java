public class TarefaPedirParaLevar implements Runnable {

    private Restaurant restaurant;

    public TarefaPedirParaLevar(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        restaurant.paraLevar();
    }
}
