package clinica;

import java.util.ArrayList;
import java.util.List;

public class Clinica {
    protected String nome;
    protected String endereco;
    protected String cnpj;
    protected double valorConsultaSimples;
    protected double valorAdicionalCheckup;
    protected List<Consulta> atendimentosRealizados;

    public Clinica(String nome, String endereco, String cnpj, double valorConsultaSimples, double valorAdicionalCheckup) throws EValorInvalidoException {
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;

        if (valorConsultaSimples < 0 || valorAdicionalCheckup < 0) {
            throw new EValorInvalidoException("Valores não podem ser negativos");
        }

        this.valorConsultaSimples = valorConsultaSimples;
        this.valorAdicionalCheckup = valorAdicionalCheckup;
        this.atendimentosRealizados = new ArrayList<>();
    }

    public double getValorConsultaSimples() {
        return valorConsultaSimples;
    }

    public double getValorAdicionalCheckup() {
        return valorAdicionalCheckup;
    }

    public double getValorTotal() {
        double total = 0;
        for (Consulta consulta : atendimentosRealizados) {
            total += consulta.getValorConsulta();
        }
        return total;
    }

    public void alterarValores(double novoValorConsultaSimples, double novoValorAdicionalCheckup) throws EValorInvalidoException {
        if (novoValorConsultaSimples < 0 || novoValorAdicionalCheckup < 0) {
            throw new EValorInvalidoException("Valor invalido");
        }

        this.valorConsultaSimples = novoValorConsultaSimples;
        this.valorAdicionalCheckup = novoValorAdicionalCheckup;
    }

    public double realizarAtendimento(String tipoConsulta, Paciente paciente, String dataAtendimento, String localAtendimento) throws EAtendimentoNaoRegistradoException, EValorInvalidoException {
        Consulta atendimento;

        if ("simples".equalsIgnoreCase(tipoConsulta)) {
            atendimento = new Consulta(paciente, dataAtendimento, localAtendimento, valorConsultaSimples);
        } else if ("checkup".equalsIgnoreCase(tipoConsulta)) {
            atendimento = new Checkup(paciente, dataAtendimento, localAtendimento, valorConsultaSimples, valorAdicionalCheckup);
        } else {
            throw new EAtendimentoNaoRegistradoException();
        }

        atendimentosRealizados.add(atendimento);


        paciente.setValorAtendimento(atendimento.getValorConsulta());

        return atendimento.getValorConsulta();
    }
    public List<Consulta> getAtendimentosRealizados() {
        return atendimentosRealizados;
    }
}
