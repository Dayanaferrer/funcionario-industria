package industria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));


        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Imprimir funcionários");
            System.out.println("2 - Excluir funcionário 'João'");
            System.out.println("3 - Aplicar aumento de 10% nos salários");
            System.out.println("4 - Imprimir funcionários, agrupados por função");
            System.out.println("5 - Imprimir funcionários que fazem aniversário em Outubro ou Dezembro");
            System.out.println("6 - Imprimir o funcionário mais velho");
            System.out.println("7 - Imprimir funcionários por ordem alfabética");
            System.out.println("8 - Imprimir o total dos salários dos funcionários");
            System.out.println("9 - Imprimir quantos salários mínimos ganha cada funcionário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    imprimirFuncionarios(funcionarios);
                    break;
                case 2:
                    funcionarios = funcionarios.stream()
                            .filter(funcionario -> !funcionario.getNome().equals("João"))
                            .collect(Collectors.toList());
                    System.out.println("Funcionário 'João' excluído com sucesso.");
                    break;
                case 3:
                    aplicarAumentoSalario(funcionarios);
                    System.out.println("Aumento de salário aplicado com sucesso.");
                    break;
                case 4:
                    imprimirFuncionariosAgrupadosPorFuncao(funcionarios);
                    break;
                case 5:
                    imprimirMesAniversario(funcionarios);
                    break;
                case 6: 
                    imprimirFuncionarioMaisVelho(funcionarios);
                    break;
                case 7:
                    imprimirFuncionariosOrdemAlfabetica(funcionarios);
                    break;
                case 8:
                    imprimirTotalSalarios(funcionarios);
                    break;
                case 9:
                    imprimirSalariosMinimos(funcionarios);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void aplicarAumentoSalario(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            BigDecimal novoSalario = funcionario.getSalario().add(aumento);
            funcionario.setSalario(novoSalario);
        }
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        System.out.printf("%-10s %-15s %-15s %-15s\n", "Nome", "Data Nascimento", "Salário", "Função");
        for (Funcionario funcionario : funcionarios) {
            String salarioFormatado = numberFormatter.format(funcionario.getSalario());
            System.out.printf("%-10s %-15s %-15s %-15s\n",
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(formatter),
                    salarioFormatado,
                    funcionario.getFuncao());
        }
    }
    
    
    private static void imprimirFuncionariosAgrupadosPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));

        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("\nFunção: " + funcao);
            System.out.printf("%-10s %-15s %-15s %-15s\n", "Nome", "Data Nascimento", "Salário", "Função");
            listaFuncionarios.forEach(funcionario -> {
                String salarioFormatado = numberFormatter.format(funcionario.getSalario());
                System.out.printf("%-10s %-15s %-15s %-15s\n",
                        funcionario.getNome(),
                        funcionario.getDataNascimento().format(formatter),
                        salarioFormatado,
                        funcionario.getFuncao());
            });
        });
    }
    
    private static void imprimirMesAniversario(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        System.out.println("\nFuncionários que fazem aniversário em Outubro ou Dezembro:");
        System.out.printf("%-10s %-15s %-15s %-15s\n", "Nome", "Data Nascimento", "Salário", "Função");
        funcionarios.stream()
            .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
            .forEach(funcionario -> {
                String salarioFormatado = numberFormatter.format(funcionario.getSalario());
                System.out.printf("%-10s %-15s %-15s %-15s\n",
                        funcionario.getNome(),
                        funcionario.getDataNascimento().format(formatter),
                        salarioFormatado,
                        funcionario.getFuncao());
            });
    }
    private static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        LocalDate dataAtual = LocalDate.now();
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparing(funcionario -> Period.between(funcionario.getDataNascimento(), dataAtual).getYears()))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), dataAtual).getYears();
            System.out.printf("\nFuncionário mais velho: %s, Idade: %d anos\n", funcionarioMaisVelho.getNome(), idade);
        } else {
            System.out.println("Não foi possível encontrar o funcionário mais velho.");
        }
    }
    private static void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        System.out.println("\nFuncionários por ordem alfabética:");
        System.out.printf("%-10s %-15s %-15s %-15s\n", "Nome", "Data Nascimento", "Salário", "Função");
        funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .forEach(funcionario -> {
                String salarioFormatado = numberFormatter.format(funcionario.getSalario());
                System.out.printf("%-10s %-15s %-15s %-15s\n",
                        funcionario.getNome(),
                        funcionario.getDataNascimento().format(formatter),
                        salarioFormatado,
                        funcionario.getFuncao());
            });
    }
    
    private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario) 
            .reduce(BigDecimal.ZERO, BigDecimal::add); 

        System.out.printf("O total dos salários dos funcionários é: R$ %s\n", totalSalarios);
    }
    
    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos que cada funcionário ganha:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal quantosSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("%s ganha %.2f salários mínimos.\n", funcionario.getNome(), quantosSalariosMinimos);
        }
    }
}