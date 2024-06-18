package arvore;

import dados.Item;

public class Arvore {
	private NoArv raiz;
	private int quantNos;// opcional

	public Arvore() {
		this.quantNos = 0;
		this.raiz = null;
	}

	public boolean eVazia() {
		return (this.raiz == null);
	}

	public NoArv getRaiz() {
		return this.raiz;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	// inserir um novo n� na arvore. Sempre insere em um atributo que seja igual a
	// null
	public boolean inserir(Item elem) {
		if (pesquisar(elem.getChave())) {
			return false;
		} else {
			this.raiz = inserir(elem, this.raiz);
			this.quantNos++;
			return true;
		}
	}

	public NoArv inserir(Item elem, NoArv no) {
		if (no == null) {
			NoArv novo = new NoArv(elem);
			return novo;
		} else {
			if (elem.getChave() < no.getInfo().getChave()) {
				no.setEsq(inserir(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(inserir(elem, no.getDir()));
				return no;
			}
		}
	}

	// Pesquisa se um determinado valor est� na �rvore
	public boolean pesquisar(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			return true;
		} else {
			return false;
		}
	}

	private NoArv pesquisar(int chave, NoArv no) {
		if (no != null) {
			if (chave < no.getInfo().getChave()) {
				no = pesquisar(chave, no.getEsq());
			} else {
				if (chave > no.getInfo().getChave()) {
					no = pesquisar(chave, no.getDir());
				}
			}
		}
		return no;
	}

//remove um determinado n� procurando pela chave. O n� pode estar em qualquer
//posi��o na �rvore
	public boolean remover(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			this.raiz = remover(chave, this.raiz);
			this.quantNos--;
			return true;
		} else {
			return false;
		}
	}

	public NoArv remover(int chave, NoArv arv) {
		if (chave < arv.getInfo().getChave()) {
			arv.setEsq(remover(chave, arv.getEsq()));
		} else {
			if (chave > arv.getInfo().getChave()) {
				arv.setDir(remover(chave, arv.getDir()));
			} else {
				if (arv.getDir() == null) {
					return arv.getEsq();
				} else {
					if (arv.getEsq() == null) {
						return arv.getDir();
					} else {
						arv.setEsq(arrumar(arv, arv.getEsq()));
					}
				}
			}
		}
		return arv;
	}

	private NoArv arrumar(NoArv arv, NoArv maior) {
		if (maior.getDir() != null) {
			maior.setDir(arrumar(arv, maior.getDir()));
		} else {
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}
		return maior;
	}

	// caminhamento central
	public Item[] CamCentral() {
		int[] n = new int[1];
		n[0] = 0;
		Item[] vet = new Item[this.quantNos];
		return (FazCamCentral(this.raiz, vet, n));
	}

	private Item[] FazCamCentral(NoArv arv, Item[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamCentral(arv.getEsq(), vet, n);
			if (arv.getInfo().getChave() % 2 == 0) {
				vet[n[0]] = arv.getInfo();
				n[0]++;
			}
			vet = FazCamCentral(arv.getDir(), vet, n);
		}
		return vet;
	}

	// caminhamento pr�-fixado
	public Item[] CamPreFixado() {
		int[] n = new int[1];
		n[0] = 0;
		Item[] vet = new Item[this.quantNos];
		return (FazCamPreFixado(this.raiz, vet, n));
	}

	private Item[] FazCamPreFixado(NoArv arv, Item[] vet, int[] n) {
		if (arv != null) {
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamPreFixado(arv.getEsq(), vet, n);
			vet = FazCamPreFixado(arv.getDir(), vet, n);
		}
		return vet;
	}

	// caminhamento p�s-fixado
	public Item[] CamPosFixado() {
		int[] n = new int[1];
		n[0] = 0;
		Item[] vet = new Item[this.quantNos];
		return (FazCamPosFixado(this.raiz, vet, n));
	}

	private Item[] FazCamPosFixado(NoArv arv, Item[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamPosFixado(arv.getEsq(), vet, n);
			vet = FazCamPosFixado(arv.getDir(), vet, n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
		}
		return vet;
	}

}
