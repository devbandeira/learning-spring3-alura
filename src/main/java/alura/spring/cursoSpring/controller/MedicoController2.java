package alura.spring.cursoSpring.controller;

import alura.spring.cursoSpring.endereco.Endereco;
import alura.spring.cursoSpring.medico.DadosCadastroMedico;
import alura.spring.cursoSpring.medico.DadosListagemMedico;
import alura.spring.cursoSpring.medico.Medico;
import alura.spring.cursoSpring.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medico2")
public class MedicoController2 {

    //Fazendo a injecao de dependencia, deixando o Spring como responsavel para instanciar o Repository
    @Autowired//Fznd isso, o SpringBoot ja vai saber que ele quem vai Instanciar e passar o Atributo Repository dentro da nossa Classe CONTROLER
    private MedicoRepository repository;
    @PostMapping
    @Transactional//PQ esse metodo e um metodo de ESCRITA(Vou fazer um insert no DB), entao precisa de uma transacao ativa com o BD.
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){//Apos fazer as validacoes dos DTO, devo por aqui tabem o @VALID para dizer para o BEAN fazer as validacoes aqui tambem e as validacoes vao aocntecer em cascata, aqui DTO MEDICOS e DTO ENDERECO
        //1.0 Passando DadosPAcientes sem ainda ter o DTO, vai ficar vermelho, vou entao criar um CREATE RECORD ... (alt + space)
        //1.1 ir para classe record DadosCadastroMEdico
        //System.out.println(dados);
        //No metodo cadastrar temos que pegar o REPOSITORY e mandar ele persistir o medico no BD.
        //como preciso da interface Repository, vou declarar como atributo desta classe Controller e eu preciso dizer
        //...ao spring boot, que quem vai instanciar esse MedicoRepository sera o Spring boot. Ja que e uma interface e ele conhece ela
        //...Conhecido como injecao de dependencias.


        // APOS FAZER ISSO TUDO, temos que pegar o repository e chamar ele aqui dentro
        //repository.save(new Medico(null, dados.nome(), dados.email(), dados.crm(), new Endereco(e eu passaria os atributos aqui)));//metodo save para fazer o inserto no banco de dados.
        //E eu preciso passar para o metodo save, um objeto do tipo MEDICO e na minha funcao eu recebo um DTO  @RequestBody DadosCadastroMedico dados
        //...Entao teremos que converter, recebo um DTO e converso para o tipo MEDICO.

        //** 1.A **Para facilitar vamos passar la no meu OBJETO Medico que quero salvar, um construtor que recebe todos os parametros
    // voltando para ca apos fazer as entidades e classes receberem um construtor do DTO
        repository.save(new Medico(dados));/*Entao aqui eu to injetando o repository como sendo um atributo e no metodo CADASTRAR
        //..tera um Objeto ENTIDADE JPA (new MEdico) e eu passo os parametros que estao vindo do JSON da requisicao
        //..no construtor da entidade MEDICO e la dentro faz a atribuicao
        //LEMBRAR DE IMPORTAR O MEDICO JA QUE ESTOU DANDO UM NEW*/

    }

    @GetMapping//n preciso usar a anotation @Transitional pois eu nao estou escrevendo ou excluindo algo no BD, somente lendo
    public /*mudar o List<> para PAGE<> que contem informacao sobre paginacao alem dos dados de medico*/Page<DadosListagemMedico> listar(Pageable paginacao/*importar ela do pacote do spring para fazer a paginacao*/){/*Se eu deixei no generics <Medico> Vou retornar tudo do medico e nao e para retornar tudo
        //..da mesma forma que criamos um DTO para DadosCadastroMedico/Paciente, vamos criar um DTO para devolver dados da API, entao sera um <DadosListagemMEdico>
        /*return repository.findAll();*/
        //Apos criar o DTO, o repository.findall() dara erro, pois findAll retorna uma lista de medico, a antidade JPA, mas o nosso retorno e DadosListagemMedico, entao vou ter que converter. Converter de medico para DadosListagemMEdico*/
        return repository.findAll(paginacao/*faco uma sobrecarga aqui no findall passando a paginacao*/).stream().map(DadosListagemMedico::new).toList();/* 1.b .map(DadosListagemMedico::new) dara erro, assim eu preciso chamar um construtor do record DadosListagemMEdico, mas aqui dentro do DTO n existe um construtor que recebe um objeto do tipo medico */
        /*fazendo mapeamento para converter medicos para DadosListagemMEdicos*/
        /*com isso eu converto uma lista de medicos para uma lista de DadosListagemMEdico que e o nosso DTO*/
    }
}

//depois de ter feito tudo e estar tudo ok, teremos o erro onde nao e possivel criar o novo usuario
//..pois a requisicao que vem do postman para salvar no banco de dados, em uma tabela inexistente ainda
//..entao vamos trabalhar com o flyaway que e uma biblioteca que tem compatibilidade direta com o spring boot
//..devo criar uma pasta em resources da seguinte forma (criar diretorio: db/migration "a IDE vai entender como uma pasta DB e uma subpasta Migration e ficara escrito da seguinte forma -> db.migration"
//..Apos isso crio um arquivo .SQL que recebera meu codigo SQL, e esse arquivo deve ter a seguinte estrutura de nome
//..V1__algum-nome-descritivo-para-o-codigo-a-ser-executado.sql (v1 mostra a versao daquela migration e tem que ter dois underlines)