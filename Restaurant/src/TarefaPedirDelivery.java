public class TarefaPedirDelivery implements Runnable {

    private final Restaurant restaurant;

    public TarefaPedirDelivery(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        this.restaurant.paraEntregar();
    }
}
