package alura.spring.cursoSpring.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {//coloquei apenas os campos que quero devolver para o front end

    //1.b criando um construtor que recebe um parametro do tipo medico
    public DadosListagemMedico(Medico medico){
        //preciso chamar o proprio construtor do record que a PARTIR DO MEDICO ele preenche as informacoes do construtor do record
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
