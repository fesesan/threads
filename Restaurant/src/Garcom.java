public class Garcom {

    String nome;

    public Garcom(String nome){
        this.nome = nome;
    }

    public void atenderMesa(Mesa mesa){
        synchronized (mesa){
            mesa.setEmAtendimento(true);
            System.out.println(nome + " comecou a atender mesa " + mesa.getNumero());
            mesa.notifyAll();
        }
    }
}
