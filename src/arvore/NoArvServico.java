package arvore;

import dados.Servico;

public class NoArvServico {
    private Servico info;
    private NoArvServico esq, dir;
    private NoArvServico prox; 

    public NoArvServico(Servico info) {
        this.info = info;
        this.esq = null;
        this.dir = null;
        this.prox = null; 
    }

    public Servico getInfo() {
        return info;
    }

    public void setInfo(Servico info) {
        this.info = info;
    }

    public NoArvServico getEsq() {
        return esq;
    }

    public void setEsq(NoArvServico esq) {
        this.esq = esq;
    }

    public NoArvServico getDir() {
        return dir;
    }

    public void setDir(NoArvServico dir) {
        this.dir = dir;
    }

    public NoArvServico getProx() {
        return prox;
    }

    public void setProx(NoArvServico prox) {
        this.prox = prox;
    }
}