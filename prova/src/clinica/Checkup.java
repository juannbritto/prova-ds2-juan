package clinica;

public class Checkup extends Consulta {
    private double valorAdicional;

    public Checkup(Paciente paciente, String dataAtendimento, String localAtendimento, double valorConsulta, double valorAdicional) throws EValorInvalidoException {
        super(paciente, dataAtendimento, localAtendimento, valorConsulta);

        if (valorAdicional < 0) {
            throw new EValorInvalidoException("Valor Adicional Inválido");
        }
        this.valorAdicional = valorAdicional;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public double getValorConsulta() {

        double valorTotal = super.getValorConsulta() + valorAdicional;
        if ("SenaiMed".equalsIgnoreCase(getPaciente().getPlanoSaude())) {
            return valorTotal * 0.82;
        } else {
            return valorTotal;
        }
    }
}
