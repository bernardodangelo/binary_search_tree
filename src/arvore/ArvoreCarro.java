package arvore;

import dados.Carro;

public class ArvoreCarro {
    private NoArvCarro raiz;
    private int quantNos;

    public ArvoreCarro() {
        this.quantNos = 0;
        this.raiz = null;
    }

    public boolean eVazia() {
        return (this.raiz == null);
    }

    public NoArvCarro getRaiz() {
        return this.raiz;
    }

    public int getQuantNos() {
        return this.quantNos;
    }
    
    

    public boolean inserir(Carro carro) {
        if (pesquisar(carro.getId())) {
            return false;
        } else {
            this.raiz = inserir(carro, this.raiz);
            this.quantNos++;
            return true;
        }
    }

    private NoArvCarro inserir(Carro carro, NoArvCarro no) {
        if (no == null) {
            NoArvCarro novo = new NoArvCarro(carro);
            return novo;
        } else {
            if (carro.getId() < no.getInfo().getId()) {
                no.setEsq(inserir(carro, no.getEsq()));
                return no;
            } else {
                no.setDir(inserir(carro, no.getDir()));
                return no;
            }
        }
    }

    public boolean pesquisar(int id) {
        return pesquisar(id, this.raiz);
    }

    private boolean pesquisar(int id, NoArvCarro no) {
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
