public class Mesa {

    private final int numero;
    private boolean disponivel;
    private  boolean emAtendimento;

    public Mesa(int numero, boolean disponivel, boolean emAtendimento){
        this.numero = numero;
        this.disponivel = disponivel;
        this.emAtendimento = emAtendimento;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean isEmAtendimento() {
        return emAtendimento;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    public void aguardarAtendimento() throws InterruptedException {
        String nome = Thread.currentThread().getName();
        synchronized (this){
            System.out.println(nome + " aguardando atendimento");
            this.wait();
        }
    }

    public void liberar(){
        String nome = Thread.currentThread().getName();
        synchronized (this){
            this.setDisponivel(true);
            this.setEmAtendimento(false);
            this.notifyAll();
            System.out.println(nome + " liberou mesa " + getNumero());
        }
    }
}


