package dados;

import java.util.ArrayList;
import java.util.List;

public class Servico {
    private int id;
    private String descricao;
    private double preco;
    private List<Carro> carros;

    public Servico(int id, String descricao, double preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.carros = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void adicionarCarro(Carro carro) {
        this.carros.add(carro);
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
