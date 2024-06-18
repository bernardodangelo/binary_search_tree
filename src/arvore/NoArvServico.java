package arvore;

import dados.Servico;

public class NoArvServico {
    private Servico info;
    private NoArvServico esq, dir;

    public NoArvServico(Servico servico) {
        this.info = servico;
        this.esq = null;
        this.dir = null;
    }

    public Servico getInfo() {
        return info;
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
}
