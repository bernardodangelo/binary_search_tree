package dados;

public class Carro {
    private int id;
    private String marca;
    private double preco;
    private Servico servico;

    public Carro(int id, String marca, double preco) {
        this.id = id;
        this.marca = marca;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }
    
    public double getPreco() {
        return preco;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String toString() {
        return "ID: " + id + "\nMarca: " + marca + "\nPreço: " + preco;
    }
}
