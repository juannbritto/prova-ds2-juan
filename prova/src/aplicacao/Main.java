package aplicacao;
import clinica.*;
import clinica.Clinica;
import clinica.EAtendimentoNaoRegistradoException;
import clinica.EValorInvalidoException;
import clinica.Paciente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Clinica clinica = new Clinica("Senai Med", "Av Dendezeiros, 188, Bonfim", "0123456789", 80, 120);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Bem Vindo a clicnica Senai Med");
                System.out.println("Digite a opção desejada:");
                System.out.println("1 - Realizar atendimento");
                System.out.println("2 - Consultar a média de valor dos atendimentos realizados");
                System.out.println("3 -  Alterar o valor da consulta simples ou o adicional para exames de checkup");
                System.out.println("4 -  Finalizar/Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao ) {
                    case 1:
                        System.out.println("Digite o tipo de consulta (simples ou check-up):");
                        String tipoConsulta = scanner.nextLine();

                        System.out.println("Digite os dados do paciente:");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
                        String dataNascimento = scanner.nextLine();
                        System.out.print("Plano de Saúde: ");
                        String planoSaude = scanner.nextLine();

                        Paciente paciente = new Paciente(cpf, nome, dataNascimento, planoSaude, clinica.getValorConsultaSimples());

                        System.out.print("Data de Atendimento (dd/MM/yyyy): ");
                        String dataAtendimento = scanner.nextLine();
                        System.out.print("Local de Atendimento: ");
                        String localAtendimento = scanner.nextLine();

                        try {
                            double valorAtendimento = clinica.realizarAtendimento(tipoConsulta, paciente, dataAtendimento, localAtendimento);


                            System.out.println("Atendimento realizado com sucesso. Valor: R$ " + paciente.getValorAtendimento());
                        } catch (EAtendimentoNaoRegistradoException e) {
                            System.out.println("Não foi possível realizar o atendimento: " + e.getMessage());
                        } catch (EValorInvalidoException e) {
                            System.out.println("Não foi possível realizar o atendimento: " + e.getMessage());
                        }
                        break;

                    case 2:
                        double media = clinica.getValorTotal() / clinica.getAtendimentosRealizados().size();
                        System.out.println("Média de valor dos atendimentos realizados é de R$: " + media);
                        break;

                    case 3:
                        System.out.print("Digite o novo valor da consulta simples");
                        double novoValorConsultaSimples = Double.parseDouble(scanner.nextLine());
                        System.out.print("Digite o novo valor adicional para exames");
                        double novoValorAdicionalCheckup = Double.parseDouble(scanner.nextLine());

                        try {
                            clinica.alterarValores(novoValorConsultaSimples, novoValorAdicionalCheckup);
                            System.out.println("Valores alterados com sucesso.");
                        } catch (EValorInvalidoException e) {
                            System.out.println("Não foi possível alterar os valores");
                        }
                        break;

                    case 4:
                        System.out.println("Encerrando o programa.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (EValorInvalidoException e) {
            System.out.println("Erro ao criar a clínica: O valor está inválido ");
        }
    }
}

