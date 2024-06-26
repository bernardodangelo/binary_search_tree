package arvore;

import dados.Carro;
import dados.Servico;
import java.util.Scanner;

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
                case 11:
                    calcularGastoComServicos();
                    break;
                case 0:
                    System.out.println("A Oficina do Bernardo foi encerrada.");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha uma opção válida.");
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
        System.out.println("11. Calcular Gasto com Serviços");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void calcularGastoComServicos() {
        System.out.print("\nDigite o ID do carro para calcular o gasto com serviços: ");
        int carroId = scanner.nextInt();
        double totalGasto = 0.0;

        Servico[] servicos = arvoreServico.camPreFixado();
        for (Servico servico : servicos) {
            if (servico.getCarroId() == carroId) {
                totalGasto += servico.getPreco();
            }
        }

        System.out.println("Total gasto com serviços: " + carroId + ": " + totalGasto);
    }
    
    private static void exibirServicosDoCarro(int carroId) {
        System.out.println("Serviços do Carro: ");
        Servico[] servicos = arvoreServico.camPreFixado();
        boolean servicosEncontrados = false;
        for (Servico servico : servicos) {
            if (servico.getCarroId() == carroId) {
                System.out.println(servico.toString());
                servicosEncontrados = true;
            }
        }
        if (!servicosEncontrados) {
            System.out.println("Nenhum serviço encontrado para este carro.");
        }
    }
    
    private static void inserirCarro() {
        System.out.print("\nDigite o ID do carro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a Marca do carro: ");
        String marca = scanner.nextLine();
        System.out.print("Digite o Preço do carro: ");
        double preco = scanner.nextDouble();

        Carro carro = new Carro(id, marca, preco);
        if (arvoreCarro.inserir(carro)) {
            System.out.println("Carro inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir carro. ID já existe.");
        }
    }

    private static void removerCarro() {
        System.out.print("\nDigite o ID do carro a ser removido: ");
        int id = scanner.nextInt();

        if (arvoreCarro.remover(id)) {
            System.out.println("Carro removido com sucesso!");
        } else {
            System.out.println("Erro ao remover carro. ID não encontrado.");
        }
    }

    private static void buscarCarro() {
        System.out.print("\nDigite o ID do carro a ser buscado: ");
        int id = scanner.nextInt();
        Carro carro = arvoreCarro.buscarPorId(id);

        if (carro != null) {
            System.out.println("Carro encontrado!\n" + "\n" + carro.toString());
            exibirServicosDoCarro(id);
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    private static void alterarCarro() {
        System.out.print("\nDigite o ID do carro a ser alterado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a nova marca do carro: ");
        String novaMarca = scanner.nextLine();
        System.out.print("Digite o novo preço do carro: ");
        double novoPreco = scanner.nextDouble();

        if (arvoreCarro.alterarCarro(id, novaMarca, novoPreco)) {
            System.out.println("Carro alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar carro. ID não encontrado.");
        }
    }

    private static void inserirServico() {
        System.out.print("\nDigite o ID do carro associado ao serviço: ");
        int carroId = scanner.nextInt();
        scanner.nextLine();

        Carro carro = arvoreCarro.buscarPorId(carroId);
        if (carro == null) {
            System.out.println("Erro ao inserir serviço., Carro não encontrado.");
            return;
        }

        System.out.println("Escolha um dos serviços:");
        System.out.println("1. Reparos automotivos diversos (Preço: 800)");
        System.out.println("2. Troca de óleo (Preço: 200)");
        System.out.print("Digite a opção desejada: ");
        int servicoOpcao = scanner.nextInt();
        scanner.nextLine();

        String descricao;
        double preco;
        switch (servicoOpcao) {
            case 1:
                descricao = "Reparos automotivos diversos";
                preco = 800;
                break;
            case 2:
                descricao = "Troca de óleo";
                preco = 200;
                break;
            default:
                System.out.println("Opção inválida! Serviço não adicionado.");
                return;
        }

        Servico[] servicos = arvoreServico.camPreFixado();
        for (Servico servico : servicos) {
            if (servico.getCarroId() == carroId && servico.getDescricao().equals(descricao)) {
                System.out.println("Erro ao inserir serviço. O serviço já existe para este carro.");
                return;
            }
        }

        int servicoId = Servico.gerarNovoId();
        Servico novoServico = new Servico(servicoId, descricao, preco, carroId);

        if (arvoreServico.inserir(novoServico)) {
            System.out.println("Serviço inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir serviço. ID já existe.");
        }
    }

    private static void removerServico() {
        System.out.print("\nDigite o ID do serviço a ser removido: ");
        int id = scanner.nextInt();

        if (arvoreServico.remover(id)) {
            System.out.println("Serviço removido com sucesso!");
        } else {
            System.out.println("Erro ao remover serviço. ID não encontrado.");
        }
    }

    private static void buscarServico() {
        System.out.print("\nDigite o ID do serviço a ser buscado: ");
        int id = scanner.nextInt();
        Servico servico = arvoreServico.buscarPorId(id);

        if (servico != null) {
            System.out.println("Serviço encontrado: \n\n" + servico.toString());
        } else {
            System.out.println("Serviço não encontrado.");
        }
    }

    private static void alterarServico() {
        System.out.print("\nDigite o ID do serviço a ser alterado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a nova descrição do serviço: ");
        String novaDescricao = scanner.nextLine();
        System.out.print("Digite o novo preço do serviço: ");
        double novoPreco = scanner.nextDouble();

        if (arvoreServico.alterarServico(id, novaDescricao, novoPreco)) {
            System.out.println("Serviço alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar serviço. ID não encontrado.");
        }
    }

    private static void exibirCarros() {
        System.out.println("\nExibindo todos os Carros (Pré-fixado):");
        Carro[] carros = arvoreCarro.camPreFixado();
        if (carros.length == 0) {
            System.out.println("Não há carros cadastrados.");
        } else {
            for (Carro carro : carros) {
                System.out.println("\nID: " + carro.getId() + ", Marca: " + carro.getMarca() + ", Preço: " + carro.getPreco());
                exibirServicosDoCarro(carro.getId());
            }
        }
    }

    private static void exibirServicos() {
        System.out.println("\nExibindo todos os Serviços (Pré-fixado):\n");
        Servico[] servicos = arvoreServico.camPreFixado();
        if (servicos.length == 0) {
            System.out.println("Não há serviços cadastrados.");
        } else {
            for (Servico servico : servicos) {
                System.out.println(servico.toString());
            }
        }
    }
}
