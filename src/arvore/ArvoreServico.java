package arvore;

import dados.Carro;
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

    public boolean inserir(Carro carro, Servico servico) {
        if (pesquisar(servico.getId())) {
            return false;
        } else {
            this.raiz = inserir(servico, this.raiz);
            this.quantNos++;
            servico.adicionarCarro(carro);
            carro.adicionarServico(servico);
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

    public boolean remover(int id) {
        if (!pesquisar(id)) {
            return false;
        } else {
            this.raiz = remover(id, this.raiz);
            this.quantNos--;
            return true;
        }
    }

    private NoArvServico remover(int id, NoArvServico no) {
        if (no == null) {
            return null;
        }

        if (id < no.getInfo().getId()) {
            no.setEsq(remover(id, no.getEsq()));
        } else if (id > no.getInfo().getId()) {
            no.setDir(remover(id, no.getDir()));
        } else {
            if (no.getEsq() == null) {
                return no.getDir();
            } else if (no.getDir() == null) {
                return no.getEsq();
            }

            NoArvServico minimo = encontrarMinimo(no.getDir());
            no.setInfo(minimo.getInfo());
            no.setDir(remover(minimo.getInfo().getId(), no.getDir()));
        }

        return no;
    }

    public boolean alterarServico(int id, String novaDescricao, double novoPreco) {
        NoArvServico no = buscarNo(id, this.raiz);
        if (no != null) {
            no.getInfo().setDescricao(novaDescricao);
            no.getInfo().setPreco(novoPreco);
            return true;
        } else {
            return false;
        }
    }

    private NoArvServico buscarNo(int id, NoArvServico no) {
        if (no == null) {
            return null;
        } else if (id == no.getInfo().getId()) {
            return no;
        } else if (id < no.getInfo().getId()) {
            return buscarNo(id, no.getEsq());
        } else {
            return buscarNo(id, no.getDir());
        }
    }

    private NoArvServico encontrarMinimo(NoArvServico no) {
        if (no == null || no.getEsq() == null) {
            return no;
        }
        return encontrarMinimo(no.getEsq());
    }

    public Servico buscarPorId(int id) {
        return buscarPorId(id, this.raiz);
    }

    private Servico buscarPorId(int id, NoArvServico no) {
        if (no == null) {
            return null;
        } else if (id == no.getInfo().getId()) {
            return no.getInfo();
        } else if (id < no.getInfo().getId()) {
            return buscarPorId(id, no.getEsq());
        } else {
            return buscarPorId(id, no.getDir());
        }
    }

    public Servico[] camPreFixado() {
        int[] n = new int[1];
        n[0] = 0;
        Servico[] vet = new Servico[this.quantNos];
        return fazCamPreFixado(this.raiz, vet, n);
    }

    private Servico[] fazCamPreFixado(NoArvServico no, Servico[] vet, int[] n) {
        if (no != null) {
            vet[n[0]] = no.getInfo();
            n[0]++;
            vet = fazCamPreFixado(no.getEsq(), vet, n);
            vet = fazCamPreFixado(no.getDir(), vet, n);
        }
        return vet;
    }
}
