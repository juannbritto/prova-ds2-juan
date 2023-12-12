package clinica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class Paciente {
        private String cpf;
        private String nome;
        private Date dataNascimento;
        private String planoSaude;
        private double valorAtendimento;

        public Paciente(String cpf, String nome, String dataNascimento, String planoSaude, double valorAtendimento) throws EValorInvalidoException {
            this.cpf = cpf;
            this.nome = nome;
            this.valorAtendimento = valorAtendimento;

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                this.dataNascimento = dateFormat.parse(dataNascimento);
            } catch (ParseException e) {
                throw new EValorInvalidoException("Data de nascimento inválida");
            }

            this.planoSaude = planoSaude;
        }

        public String getCpf() {
            return cpf;
        }

        public String getNome() {
            return nome;
        }

        public Date getDataNascimento() {
            return dataNascimento;
        }

        public String getPlanoSaude() {
            return planoSaude;
        }

        public double getValorAtendimento() {
            return valorAtendimento;
        }

        public void setValorAtendimento(double valorAtendimento) {
            this.valorAtendimento = valorAtendimento;
        }
    }

