package dados;

import java.util.ArrayList;
import java.util.List;

public class Carro {
    private int id;
    private String marca;
    private String modelo;
    private List<Servico> servicos;

    public Carro(int id, String marca, String modelo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.servicos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void adicionarServico(Servico servico) {
        this.servicos.add(servico);
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
