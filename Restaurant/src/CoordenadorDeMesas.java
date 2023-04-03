import java.util.ArrayList;
import java.util.List;

public class CoordenadorDeMesas {

    private List<Mesa> mesas;

    public CoordenadorDeMesas(int quantidade){
        disponibilizarMesas(quantidade);
    }

    private void disponibilizarMesas(int quantidadeDeMesas){
        mesas = new ArrayList<>();
        for(int i =1; i <= quantidadeDeMesas; i++)
            mesas.add(new Mesa(i, true, false));
        System.out.println("Total de Mesas disponiveis: " + mesas.size());
    }

    public synchronized int ocuparMesa() throws InterruptedException {
        while(todasAsMesasOcupadas())
            aguardarMesa();

        var mesaDisponivel =
                this.mesas
                .stream()
                .filter(Mesa::isDisponivel)
                .findFirst();

        if(mesaDisponivel.isPresent()){
            Mesa mesa = mesaDisponivel.get();
            mesa.setDisponivel(false);
            int i = mesas.indexOf(mesa);
            mesas.set(i, mesa);

            return mesa.getNumero();
        } else
            throw new RuntimeException("Todas as mesas estao ocupadas");
    }

    private boolean todasAsMesasOcupadas(){
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
            int i = mesas.indexOf(mesa);
            mesas.set(i, mesa);

            liberarMesa(numeroDaMesa);
        }else
            throw new RuntimeException(
                    "Numero " + numeroDaMesa + " de mesa nao encontrado."
            );
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

    public int obterMesaParaAtender() throws InterruptedException {
        synchronized (mesas){
            while (todasAsMesasAtendidas())
                aguardarMesa();

            var mesaPendente =
                    this.mesas
                    .stream()
                    .filter(m -> !m.isEmAtendimento())
                    .findFirst();

            if(mesaPendente.isPresent()){
                Mesa mesa = mesaPendente.get();
                mesa.setEmAtendimento(true);
                int i = mesas.indexOf(mesa);
                mesas.set(i, mesa);

                return  mesa.getNumero();
            } else
                throw new RuntimeException("Nao ha mesas a serem atendidas.");
        }
    }

    private boolean todasAsMesasAtendidas(){
        return this.mesas.stream().allMatch(Mesa::isEmAtendimento);
    }
}
