package alura.spring.cursoSpring.medico;

import alura.spring.cursoSpring.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {//Aqui posso uasr o DadosEndereco, poias as regras que estao validades no DTO nos agradam.
    //Todos os campos sao adicionais, menos o campo ID.
}
