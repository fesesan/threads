public class TarefaEntregarPedidoDelivery implements Runnable {

    private Restaurant restaurant;
    public TarefaEntregarPedidoDelivery(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        restaurant.entregarPedido();
    }
}
