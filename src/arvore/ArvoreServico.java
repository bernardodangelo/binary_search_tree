package arvore;

import dados.Servico;

public class ArvoreServico {
    private NoArvServico raiz;
    private int quantNos;

    public ArvoreServico() {
        this.quantNos = 0;
        this.raiz = null;
    }

    public boolean eVazia() {
        return (this.raiz == null);
    }

    public NoArvServico getRaiz() {
        return this.raiz;
    }

    public int getQuantNos() {
        return this.quantNos;
    }

    public Servico buscarPorPrecoMenorQue(double valor) {
        return buscarPorPrecoMenorQue(this.raiz, valor);
    }

    private Servico buscarPorPrecoMenorQue(NoArvServico no, double valor) {
        if (no == null) {
            return null;
        }

        if (no.getInfo().getPreco() < valor) {
            return no.getInfo(); // Encontrou o serviço com preço menor que o valor especificado
        } else {
            Servico encontrado = buscarPorPrecoMenorQue(no.getEsq(), valor);
            if (encontrado == null) {
                encontrado = buscarPorPrecoMenorQue(no.getDir(), valor);
            }
            return encontrado;
        }
    }

    
    public boolean inserir(Servico servico) {
        if (pesquisar(servico.getId())) {
            return false;
        } else {
            this.raiz = inserir(servico, this.raiz);
            this.quantNos++;
            return true;
        }
    }

    private NoArvServico inserir(Servico servico, NoArvServico no) {
        if (no == null) {
            NoArvServico novo = new NoArvServico(servico);
            return novo;
        } else {
            if (servico.getId() < no.getInfo().getId()) {
                no.setEsq(inserir(servico, no.getEsq()));
                return no;
            } else {
                no.setDir(inserir(servico, no.getDir()));
                return no;
            }
        }
    }

    public boolean pesquisar(int id) {
        return pesquisar(id, this.raiz);
    }

    private boolean pesquisar(int id, NoArvServico no) {
        if (no == null) {
            return false;
        } else if (id == no.getInfo().getId()) {
            return true;
        } else if (id < no.getInfo().getId()) {
            return pesquisar(id, no.getEsq());
        } else {
            return pesquisar(id, no.getDir());
        }
    }
    
    

}
