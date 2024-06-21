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

    
    public boolean remover(int id) {
        if (!pesquisar(id)) {
            return false; // Carro não encontrado
        } else {
            this.raiz = remover(id, this.raiz);
            this.quantNos--;
            return true;
        }
    }

    private NoArvCarro remover(int id, NoArvCarro no) {
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

            NoArvCarro minimo = encontrarMinimo(no.getDir());
            no.setInfo(minimo.getInfo());
            no.setDir(remover(minimo.getInfo().getId(), no.getDir()));
        }

        return no;
    }
    
    public boolean alterarCarro(int id, String novaMarca, String novoModelo) {
        NoArvCarro no = buscarNo(id, this.raiz);
        if (no != null) {
            no.getInfo().setMarca(novaMarca);
            no.getInfo().setModelo(novoModelo);
            return true;
        } else {
            return false;
        }
    }

    private NoArvCarro buscarNo(int id, NoArvCarro no) {
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

    private NoArvCarro encontrarMinimo(NoArvCarro no) {
        if (no == null || no.getEsq() == null) {
            return no;
        }
        return encontrarMinimo(no.getEsq());
    }
    
    public Carro buscarPorId(int id) {
        return buscarPorId(id, this.raiz);
    }

    private Carro buscarPorId(int id, NoArvCarro no) {
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

    public Carro[] camPreFixado() {
        int[] n = new int[1];
        n[0] = 0;
        Carro[] vet = new Carro[this.quantNos];
        return fazCamPreFixado(this.raiz, vet, n);
    }

    private Carro[] fazCamPreFixado(NoArvCarro no, Carro[] vet, int[] n) {
        if (no != null) {
            vet[n[0]] = no.getInfo();
            n[0]++;
            vet = fazCamPreFixado(no.getEsq(), vet, n);
            vet = fazCamPreFixado(no.getDir(), vet, n);
        }
        return vet;
    }
}
