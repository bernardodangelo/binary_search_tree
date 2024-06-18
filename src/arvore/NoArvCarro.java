package arvore;

import dados.Carro;

public class NoArvCarro {
    private Carro info;
    private NoArvCarro esq, dir;

    public NoArvCarro(Carro carro) {
        this.info = carro;
        this.esq = null;
        this.dir = null;
    }

    public Carro getInfo() {
        return info;
    }

    public NoArvCarro getEsq() {
        return esq;
    }

    public void setEsq(NoArvCarro esq) {
        this.esq = esq;
    }

    public NoArvCarro getDir() {
        return dir;
    }

    public void setDir(NoArvCarro dir) {
        this.dir = dir;
    }
}
