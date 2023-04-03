public class Mesa {

    private int numero;
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
}


