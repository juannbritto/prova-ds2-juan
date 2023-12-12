package clinica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta {
    private Paciente paciente;
    private Date dataAtendimento;
    private String localAtendimento;
    protected double valorConsulta;

    public Consulta(Paciente paciente, String dataAtendimento, String localAtendimento, double valorConsulta) throws EValorInvalidoException {
        this.paciente = paciente;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            this.dataAtendimento = dateFormat.parse(dataAtendimento);
        } catch (ParseException e) {
            throw new EValorInvalidoException("Data de atendimento inválida");
        }
        // não consegui validar com localdate, tava dando erro :(

        this.localAtendimento = localAtendimento;

        if (valorConsulta < 0) {
            throw new EValorInvalidoException("Valor da consulta não pode ser negativo");
        }
        this.valorConsulta = valorConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public String getLocalAtendimento() {
        return localAtendimento;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }
}
