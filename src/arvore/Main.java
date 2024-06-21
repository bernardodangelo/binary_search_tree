package arvore;

import java.util.Scanner;
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
                    buscarCarro();
                    break;
                case 4:
                    alterarCarro();
                    break;
                case 5:
                    inserirServico();
                    break;
                case 6:
                    removerServico();
                    break;
                case 7:
                    buscarServico();
                    break;
                case 8:
                    alterarServico();
                    break;
                case 9:
                    exibirCarros();
                    break;
                case 10:
                    exibirServicos();
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
        System.out.println("\n===== Oficina de Carros do Bernardo =====");
        System.out.println("1. Inserir Carro");
        System.out.println("2. Remover Carro");
        System.out.println("3. Buscar Carro");
        System.out.println("4. Alterar Carro");
        System.out.println("5. Inserir Serviço");
        System.out.println("6. Remover Serviço");
        System.out.println("7. Buscar Serviço");
        System.out.println("8. Alterar Serviço");
        System.out.println("9. Exibir Carros");
        System.out.println("10. Exibir Serviços");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void inserirCarro() {
        System.out.print("Digite o ID do carro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a marca do carro: ");
        String marca = scanner.nextLine();
        System.out.print("Digite o modelo do carro: ");
        String modelo = scanner.nextLine();

        Carro carro = new Carro(id, marca, modelo);
        if (arvoreCarro.inserir(carro)) {
            System.out.println("Carro inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir carro: ID já existe.");
        }
    }

    private static void removerCarro() {
        System.out.print("Digite o ID do carro a ser removido: ");
        int id = scanner.nextInt();

        if (arvoreCarro.remover(id)) {
            System.out.println("Carro removido com sucesso!");
        } else {
            System.out.println("Erro ao remover carro: ID não encontrado.");
        }
    }

    private static void buscarCarro() {
        System.out.print("Digite o ID do carro a ser buscado: ");
        int id = scanner.nextInt();

        Carro carro = arvoreCarro.buscarPorId(id);
        if (carro != null) {
            System.out.println("Carro encontrado: " + carro.getMarca() + " " + carro.getModelo());
            System.out.println("Serviços associados:");
            for (Servico servico : carro.getServicos()) {
                System.out.println("- " + servico.getDescricao() + " (Preço: " + servico.getPreco() + ")");
            }
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    private static void alterarCarro() {
        System.out.print("Digite o ID do carro a ser alterado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a nova marca do carro: ");
        String novaMarca = scanner.nextLine();
        System.out.print("Digite o novo modelo do carro: ");
        String novoModelo = scanner.nextLine();

        if (arvoreCarro.alterarCarro(id, novaMarca, novoModelo)) {
            System.out.println("Carro alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar carro: ID não encontrado.");
        }
    }

    private static void inserirServico() {
        System.out.print("Digite o ID do carro associado ao serviço: ");
        int carroId = scanner.nextInt();
        scanner.nextLine();

        Carro carro = arvoreCarro.buscarPorId(carroId);
        if (carro == null) {
            System.out.println("Erro ao inserir serviço: Carro não encontrado.");
            return;
        }

        System.out.print("Digite o ID do serviço: ");
        int servicoId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a descrição do serviço: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o preço do serviço: ");
        double preco = scanner.nextDouble();

        Servico servico = new Servico(servicoId, descricao, preco);
        if (arvoreServico.inserir(carro, servico)) {
            System.out.println("Serviço inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir serviço: ID já existe.");
        }
    }

    private static void removerServico() {
        System.out.print("Digite o ID do serviço a ser removido: ");
        int id = scanner.nextInt();

        if (arvoreServico.remover(id)) {
            System.out.println("Serviço removido com sucesso!");
        } else {
            System.out.println("Erro ao remover serviço: ID não encontrado.");
        }
    }

    private static void buscarServico() {
        System.out.print("Digite o ID do serviço a ser buscado: ");
        int id = scanner.nextInt();

        Servico servico = arvoreServico.buscarPorId(id);
        if (servico != null) {
            System.out.println("Serviço encontrado: " + servico.getDescricao() + " (Preço: " + servico.getPreco() + ")");
            System.out.println("Carros associados:");
            for (Carro carro : servico.getCarros()) {
                System.out.println("- " + carro.getId() + ": " + carro.getMarca() + " " + carro.getModelo());
            }
        } else {
            System.out.println("Serviço não encontrado.");
        }
    }

    private static void alterarServico() {
        System.out.print("Digite o ID do serviço a ser alterado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a nova descrição do serviço: ");
        String novaDescricao = scanner.nextLine();
        System.out.print("Digite o novo preço do serviço: ");
        double novoPreco = scanner.nextDouble();

        if (arvoreServico.alterarServico(id, novaDescricao, novoPreco)) {
            System.out.println("Serviço alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar serviço: ID não encontrado.");
        }
    }


    private static void exibirCarros() {
        Carro[] carros = arvoreCarro.camPreFixado();
        if (carros.length == 0) {
            System.out.println("Nenhum carro cadastrado.");
        } else {
            System.out.println("Carros cadastrados:");
            for (Carro carro : carros) {
                System.out.println("- " + carro.getId() + ": " + carro.getMarca() + " " + carro.getModelo());
                System.out.println("  Serviços associados:");
                for (Servico servico : carro.getServicos()) {
                    System.out.println("  - " + servico.getDescricao() + " (Preço: " + servico.getPreco() + ")");
                }
            }
        }
    }

    private static void exibirServicos() {
        Servico[] servicos = arvoreServico.camPreFixado();
        if (servicos.length == 0) {
            System.out.println("Nenhum serviço cadastrado.");
        } else {
            System.out.println("Serviços cadastrados:");
            for (Servico servico : servicos) {
                System.out.println("- " + servico.getId() + ": " + servico.getDescricao() + " (Preço: " + servico.getPreco() + ")");
                System.out.println("  Carros associados:");
                for (Carro carro : servico.getCarros()) {
                    System.out.println("  - " + carro.getId() + ": " + carro.getMarca() + " " + carro.getModelo());
                }
            }
        }
    }
}
