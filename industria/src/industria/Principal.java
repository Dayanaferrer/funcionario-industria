package industria;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
}