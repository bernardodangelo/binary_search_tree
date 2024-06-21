package arvore;

import java.util.Scanner;
import arvore.ArvoreCarro;
import arvore.ArvoreServico;
import dados.Carro;
import dados.Servico;

public class Main {

    private static ArvoreCarro arvoreCarro = new ArvoreCarro();
    private static ArvoreServico arvoreServico = new ArvoreServico();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    inserirCarro();
                    break;
                case 2:
                    removerCarro();
                    break;
                case 3:
                    buscarCarroPorId();
                    break;
                case 4:
                    alterarCarro();
                    break;
                case 5:
                	inserirServico();
                    break;
                case 7:
                    exibirCarros();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== Menu de Operações =====");
        System.out.println("1. Inserir Carro");
        System.out.println("2. Remover Carro por ID");
        System.out.println("3. Buscar Carro por ID");
        System.out.println("4. Inserir Serviço");
        System.out.println("5. Remover Serviço por Descrição");
        System.out.println("6. Buscar Serviço por Descrição");
        System.out.println("7. Exibir Todos os Carros (Pré-fixado)");
        System.out.println("8. Exibir Todos os Serviços (Pré-fixado)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void inserirCarro() {
        System.out.println("\nInserção de Carro:");
        System.out.print("Digite o ID do Carro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a Marca do Carro: ");
        String marca = scanner.nextLine();
        System.out.print("Digite o Modelo do Carro: ");
        String modelo = scanner.nextLine();

        Carro novoCarro = new Carro(id, marca, modelo);
        if (arvoreCarro.inserir(novoCarro)) {
            System.out.println("Carro inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir o Carro. Verifique se o ID já existe na árvore.");
        }
    }

    private static void removerCarro() {
        System.out.println("\nRemoção de Carro:");
        System.out.print("Digite o ID do Carro que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (arvoreCarro.remover(id)) {
            System.out.println("Carro removido com sucesso!");
        } else {
            System.out.println("Erro ao remover o Carro. Verifique se o ID está correto.");
        }
    }

    private static void buscarCarroPorId() {
        System.out.println("\nBusca de Carro por ID:");
        System.out.print("Digite o ID do Carro que deseja buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Carro carroEncontrado = arvoreCarro.buscarPorId(id);
        if (carroEncontrado != null) {
            System.out.println("Carro encontrado:");
            System.out.println("ID: " + carroEncontrado.getId());
            System.out.println("Marca: " + carroEncontrado.getMarca());
            System.out.println("Modelo: " + carroEncontrado.getModelo());
        } else {
            System.out.println("Carro não encontrado com o ID especificado.");
        }
    }
    
    private static void alterarCarro() {
        System.out.println("\nAlteração de Carro:");
        System.out.print("Digite o ID do Carro que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a nova Marca do Carro: ");
        String novaMarca = scanner.nextLine();
        System.out.print("Digite o novo Modelo do Carro: ");
        String novoModelo = scanner.nextLine();

        if (arvoreCarro.alterarCarro(id, novaMarca, novoModelo)) {
            System.out.println("Carro alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar o Carro. Verifique se o ID está correto.");
        }
    }
    
    private static void exibirCarros() {
        System.out.println("\nExibindo todos os Carros (Pré-fixado):");
        Carro[] carros = arvoreCarro.camPreFixado();
        if (carros.length == 0) {
            System.out.println("Não há carros cadastrados.");
        } else {
            for (Carro carro : carros) {
                System.out.println("ID: " + carro.getId() + ", Marca: " + carro.getMarca() + ", Modelo: " + carro.getModelo());
            }
        }
    }

    private static void inserirServico() {
        System.out.println("\nInserção de Serviço:");
        System.out.print("Digite o ID do Serviço: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a Descrição do Serviço: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o Preço do Serviço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        Servico novoServico = new Servico(id, descricao, preco);
        if (arvoreServico.inserir(novoServico)) {
            System.out.println("Serviço inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir o Serviço. Verifique se o ID já existe na árvore.");
        }
    }
}
