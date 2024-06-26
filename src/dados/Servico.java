package dados;

public class Servico {
	    private static int contadorId = 1;
	    private int id;
	    private String descricao;
	    private double preco;
	    private int carroId;

	    public static final Servico REPAROS = new Servico(1, "Reparos automotivos diversos", 800, -1);
	    public static final Servico TROCA_DE_OLEO = new Servico(2, "Troca de óleo", 200, -1);

	    public Servico(int id, String descricao, double preco, int carroId) {
	        this.id = id;
	        this.descricao = descricao;
	        this.preco = preco;
	        this.carroId = carroId;
	    }

	    public static int gerarNovoId() {
	        return contadorId++;
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

	    public int getCarroId() {
	        return carroId;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public void setPreco(double preco) {
	        this.preco = preco;
	    }

	    public void setCarroId(int carroId) {
	        this.carroId = carroId;
	    }
    
    public String toString() {
        return "ID: " + id + ", Descrição: " + descricao + ", Preço: " + preco + ", ID do Carro vinculado: " + carroId;
    }
}
