package cadastropoo;

import model.PessoaFisicaRepo;
import model.PessoaFisica;
import model.PessoaJuridicaRepo;
import model.PessoaJuridica;
import java.util.Scanner;
import java.io.IOException;

public class CadastroPOO {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
            PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        
            int opcao;
            do {
            System.out.println("==============================================");
            System.out.println("Escolha uma opcao:");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar programa");
            System.out.println("==============================================");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1 ->
                    incluir(scanner, repoFisica, repoJuridica);
                case 2 ->
                    alterar(scanner, repoFisica, repoJuridica); 
                case 3 ->
                    excluir(scanner, repoFisica, repoJuridica);                
                case 4 ->
                    exibirPeloId(scanner, repoFisica, repoJuridica);                
                case 5 ->
                    exibirTodos(scanner, repoFisica, repoJuridica);           
                case 6 ->
                    salvarDados(scanner, repoFisica, repoJuridica);            
                case 7 ->
                    recuperarDados(scanner, repoFisica, repoJuridica);           
                case 0 ->
                    System.out.println("Finalizando...");
                default ->
                    System.out.println("Opcao invalida!");
                }
            } while (opcao != 0);
        }     
    }
    
    private static void incluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        char tipo = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();
        
        switch (tipo) {
            case 'F' -> {
                System.out.println("Digite o id da pessoa:");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Insira os dados...");
                System.out.println("Nome:");
                String nome = scanner.nextLine();
                System.out.println("CPF:");
                String cpf = scanner.nextLine();
                System.out.println("Idade:");
                int idade = scanner.nextInt();
                scanner.nextLine();
            
                PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
                repoFisica.inserir(pf);
            }
            case 'J' -> {
                System.out.println("Digite o id da pessoa:");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Insira os dados...");
                System.out.println("Digite o nome:");
                String nome = scanner.nextLine();
                System.out.println("CNPJ:");
                String cnpj = scanner.nextLine();
            
            PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
            repoJuridica.inserir(pj);
            }
            default ->
            System.out.println("Tipo invalido!");
        }
    }
    
    private static void alterar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        char tipo = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();
        
        switch (tipo) {
            case 'F' -> {
                System.out.println("Digite o ID da pessoa fisica a ser alterada:");
                int id = scanner.nextInt();
                scanner.nextLine();
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    System.out.println("Digite o novo nome:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o novo CPF:");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite a nova idade:");
                    int idade = scanner.nextInt();
                    scanner.nextLine();
                    pf.setNome(nome);
                    pf.setCpf(cpf);
                    pf.setIdade(idade);
                    repoFisica.alterar(pf);
                    System.out.println("Pessoa fisica alterada com sucesso!");
                } else {
                    System.out.println("Pessoa fisica nao encontrada!");
                }
            }
            case 'J' -> {
                System.out.println("Digite o ID da pessoa juridica a ser alterada:");
                int id = scanner.nextInt();
                scanner.nextLine();
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    System.out.println("Digite o novo nome:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o novo CNPJ:");
                    String cnpj = scanner.nextLine();
                    pj.setNome(nome);
                    pj.setCnpj(cnpj);
                    repoJuridica.alterar(pj);
                    System.out.println("Pessoa juridica alterada com sucesso!");
                } else {
                    System.out.println("Pessoa juridica nao encontrada!");
                }
            }
            default -> System.out.println("Tipo invalido!");
        }
    }
    
    private static void excluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        String tipo = scanner.nextLine();
        
        switch (tipo.toUpperCase()) {
            case "F" -> {
                System.out.println("Digite o ID da pessoa fisica a ser excluida:");
                int id = scanner.nextInt();
                scanner.nextLine();
                if (repoFisica.excluir(id)) {
                    System.out.println("Pessoa fisica removida com sucesso!");
                } else {
                    System.out.println("pessoa fisica nao encontrada!");
                }
            }
            case "J" -> {
                System.out.println("Digite o ID da pessoa juridica a ser excluida:");
                int id = scanner.nextInt();
                scanner.nextLine();
                if (repoJuridica.excluir(id)) {
                    System.out.println("Pessoa juridica removida com sucesso!");
                } else {
                    System.out.println("Pessoa juridica nao encontrada!");
                }
            }
            default ->
                System.out.println("Tipo invalido");
        }
    }
    
    private static void exibirPeloId(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        char tipo = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();
        
        switch (tipo) {
            case 'F' -> {
                System.out.println("Digite o ID da pessoa fisica:");
                int id = scanner.nextInt();
                scanner.nextLine();
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    System.out.println(pf);
                } else {
                    System.out.println("Pessoa fisica nao encontrada!");
                }
            }
            case 'J' -> {
                System.out.println("Digite o ID da pessoa juridica:");
                int id = scanner.nextInt();
                scanner.nextLine();
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    System.out.println(pj);
                } else {
                    System.out.println("Pessoa juridica nao encontrada!");
                }
            }
            default -> 
                System.out.println("Tipo invalido!");
        }
    }
    
    private static void exibirTodos(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        char tipo = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();
        
        switch (tipo) {
            case 'F' ->
                repoFisica.obterTodos().forEach(System.out::println);
            case 'J' -> 
                repoJuridica.obterTodos().forEach(System.out::println);
            default ->
                System.out.println("Tipo invalido!");
        }
    }
    
    private static void salvarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Digite o prefixo dos arquivos:");
        String prefixo = scanner.nextLine();
        
        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage()); 
        }
    } 
    
    private static void recuperarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Digite o prefixo dos arquivos:");
        String prefixo = scanner.nextLine();
        
        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }
}

