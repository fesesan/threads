public class TarefaComerNoLocal implements Runnable {

    private Restaurant restaurant;

    public  TarefaComerNoLocal(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        try {
            restaurant.paraComerAgora();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
