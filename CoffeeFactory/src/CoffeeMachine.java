import java.util.List;
import java.util.Objects;

public class CoffeeMachine {

    private boolean on;

    private boolean hasWater;

    private boolean hasGroudCoffee;

    public CoffeeMachine(){

    }

    public void on() throws InterruptedException {
        setOn(true);
        prepareCoffee();
        coffeeReady();
    }

    public void off(){
        setOn(false);
    }

    public void putWater(List<Container> containers){
        if (hasWaterIn(containers)){
            throw new RuntimeException(
                    "***** Recipiente(s) está(ão) vazio(s), não é possível colocar água... ***** \n"
            );
        } else
            setHasWater(true);
    }

    private boolean hasWaterIn(List<Container> containers) {
        return containers.isEmpty() || containers.stream().anyMatch(container -> !container.isFull);
    }

    public void putCoffee(GroundCoffee coffee){
        if(Objects.nonNull(coffee))
            setHasGroudCoffee(true);
    }

    private void prepareCoffee() throws InterruptedException {
        if(hasWater && hasGroudCoffee){
            System.out.println("***** Preparando o seu cafézinho... ***** \n");
            Thread.sleep(2000);
        } else
            System.out.println("***** A máquina foi ligada sem o pó de café ou sem água ***** \n");
    }
    private void coffeeReady(){
        System.out.println("***** Café Pronto. ***** \n");
        setOn(false);
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isHasWater() {
        return hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean isHasGroudCoffee() {
        return hasGroudCoffee;
    }
    public void setHasGroudCoffee(boolean hasGroudCoffee) {
        this.hasGroudCoffee = hasGroudCoffee;
    }
}
