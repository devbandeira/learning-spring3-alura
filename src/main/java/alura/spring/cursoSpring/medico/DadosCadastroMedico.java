package alura.spring.cursoSpring.medico;

import alura.spring.cursoSpring.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//** 1.A ** meu DTO DadosCadastroMedico que estou passando no OBJETO MEDICO para facilitar o meu SAVE (persistencia) no meu OBJETO MEDICO
public record DadosCadastroMedico(
        @NotBlank//verifica se nao e nulo e nem vazio
        String nome,
        @NotBlank
        @Email//para validar o tipo de email, pois deve vir em formato de email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp =  "\\d{4,6}")//digo que a expresao regular sao do tipo d"digitos" que sao de 4 a 5 digitos
        String crm,
        @NotNull//aqui nao e um notblank porque notblank e so para strings. Nao validamos o enum, pois o proprio Spring ja valida o ENUM e de fato uma constante do ENUM.
        Especialidade especialidade,
        @NotNull//tbm nao pode ser nullo,mas preciso tambem validar os dados que vem dele, entao uso a anotacao...
        @Valid//To dznd "O bean validation vc ta validando meu DTO DadosCadastroMedico, mas dentro dele tem um outro DTO como atributo
                //..e dentro dele vai ter outras validacoes do bean validation que tambem quero que vc valide.
        DadosEndereco endereco) {
//1.0 Passo para o record os atributos que vou receber na requisicao via POSTMAN ou outra coisa
    //1.1 Passando Especialidade nao vamos usar String, especialidade e FIXA(constantes com 4 opcoes), entao vou usar um ENUM
    //1.2 Criar um ENUM chamado especialidade
    //1.3 Vou para a classe ENUM especialidade
    //1.4 Ao inves de passar varios dados do endereco e deixar solto, crio uma CLASSE RECORD endereco e passo tambem
    //1.5 Ir para classe RECORD endereco que sera usada em varios cadastros (paciente e medicos)
    //1.6 Lembrando quando eu passar os dados no POSTMAN a ESPECILIDADE tem que ser em maiusculo, as constantes tenho que avisar ao time do front.
    //1.7 Se eu nao passar nada no POSTMAN, por exemplo excluir o campo complemento, ele vai retornar como complemento = null por padrao
    //1.8
}