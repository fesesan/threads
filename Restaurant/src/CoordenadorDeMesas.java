import java.util.ArrayList;
import java.util.List;

public class CoordenadorDeMesas {

    private List<Mesa> mesas;

    public CoordenadorDeMesas(int quantidadeDeMesas){
        disponibilizar(quantidadeDeMesas);
    }

    private void disponibilizar(int quantidadeDeMesas){
        mesas = new ArrayList<>();
        for(int i =1; i <= quantidadeDeMesas; i++)
            mesas.add(new Mesa(i, true, false));
    }

    public synchronized int ocuparMesa() throws InterruptedException {
        while(todasAsMesasEstaoOcupadas())
            aguardarMesa();

        var mesaDisponivel =
                this.mesas
                .stream()
                .filter(Mesa::isDisponivel)
                .findFirst();

        if(mesaDisponivel.isPresent()){
            Mesa mesa = mesaDisponivel.get();
            mesa.setDisponivel(false);
            mesa.setEmAtendimento(true);

            int index = mesas.indexOf(mesa);
            mesas.set(index,mesa);

            return mesa.getNumero();
        } else
            throw new RuntimeException("Todas as mesas estao ocupadas");
    }

    private boolean todasAsMesasEstaoOcupadas(){
        return this.mesas.stream().noneMatch(Mesa::isDisponivel);
    }

    public void desocuparMesa(int numeroDaMesa){
        var mesaOcupada =
                this.mesas
                .stream()
                .filter(m -> m.getNumero() == numeroDaMesa && !m.isDisponivel())
                .findFirst();

        if(mesaOcupada.isPresent()){
            Mesa mesa = mesaOcupada.get();
            mesa.setDisponivel(true);
            mesa.setEmAtendimento(false);

            int index = mesas.indexOf(mesa);
            mesas.set(index, mesa);

            liberarMesa(numeroDaMesa);

        }else
            throw new RuntimeException("Numero de mesa nao encontrado. A comanda ta certa ?");
    }

    private void aguardarMesa() throws InterruptedException {
        synchronized (mesas){
            String nome = Thread.currentThread().getName();
            System.out.println(nome + " esta aguardando uma mesa.");
            mesas.wait();
        }
    }

    private void liberarMesa(int numeroDaMesa){
        synchronized (mesas){
            String nome = Thread.currentThread().getName();
            System.out.println(nome + " desocupou a mesa " + numeroDaMesa);
            mesas.notifyAll();
        }
    }
}
