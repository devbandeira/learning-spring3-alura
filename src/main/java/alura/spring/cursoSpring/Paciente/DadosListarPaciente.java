package alura.spring.cursoSpring.Paciente;

public record DadosListarPaciente(String nome, String email, String cpf) {
    public DadosListarPaciente(PacienteEntitity paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
