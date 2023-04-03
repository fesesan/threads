import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

public class CoordenadorDeMesas {

    private List<Mesa> mesas;

    public CoordenadorDeMesas(int quantidade){
        disponibilizarMesas(quantidade);
    }

    private void disponibilizarMesas(int quantidadeDeMesas){
        mesas = new ArrayList<>();
        for(int i =1; i <= quantidadeDeMesas; i++)
            mesas.add(
                    new Mesa(i, true, false)
            );
        System.out.println("Total de Mesas disponiveis: " + mesas.size());
    }

    public synchronized Mesa ocuparMesa() throws InterruptedException {
        String nome = Thread.currentThread().getName();

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

           System.out.println(nome + " senta na mesa: " + mesa.getNumero());
           notificarMesaParaAtender();

           return mesa;
        } else
            throw new RuntimeException("Todas as mesas estao ocupadas");
    }

    public void desocuparMesa(Mesa mesa){
        if (mesas.contains(mesa)){
            mesa.liberar();
            notificarMesaDisponivel(mesa.getNumero());
        } else
            throw new RuntimeException(
                    format("Mesa %s nao encontrada.", mesa.getNumero())
            );
    }

    private void aguardarMesa() throws InterruptedException {
        synchronized (mesas){
            String nome = Thread.currentThread().getName();
            System.out.println(nome + " esta aguardando uma mesa.");
            mesas.wait();
        }
    }

    private void notificarMesaDisponivel(int numeroMesa){
        String nome = Thread.currentThread().getName();
        synchronized (mesas){
            System.out.println(nome + " desocupou a mesa " + numeroMesa);
            mesas.notifyAll();
        }
    }

    private void notificarMesaParaAtender(){
        String nome = Thread.currentThread().getName();
        synchronized (mesas){
            mesas.notifyAll();
        }
    }

    public Mesa obterMesaParaAtender() throws InterruptedException {
        synchronized (mesas){
            while (!algumaMesaParaAtender() || todasAsMesasAtendidas())
                aguardarMesa();

            var mesaPendente =
                    this.mesas
                    .stream()
                    .filter(m -> !m.isDisponivel() && !m.isEmAtendimento())
                    .findFirst();

            if(mesaPendente.isPresent()){
                return mesaPendente.get();
            } else
                throw new RuntimeException("Nao ha mesas a serem atendidas.");
        }
    }

    private boolean algumaMesaParaAtender(){
        return this.mesas.stream().anyMatch(
                m -> !m.isDisponivel() && !m.isEmAtendimento()
        );
    }

    private boolean todasAsMesasOcupadas(){
        return this.mesas.stream().noneMatch(Mesa::isDisponivel);
    }

    private boolean todasAsMesasAtendidas(){
        return this.mesas.stream().allMatch(
                m -> !m.isDisponivel() && m.isEmAtendimento()
        );
    }
}
