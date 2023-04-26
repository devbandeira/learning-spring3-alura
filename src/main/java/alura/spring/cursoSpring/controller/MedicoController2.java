package alura.spring.cursoSpring.controller;

import alura.spring.cursoSpring.endereco.Endereco;
import alura.spring.cursoSpring.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public /*mudar o List<> para PAGE<> que contem informacao sobre paginacao alem dos dados de medico*/Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao/*importar ela do pacote do spring para fazer a paginacao*/){/*Se eu deixei no generics <Medico> Vou retornar tudo do medico e nao e para retornar tudo
        //..da mesma forma que criamos um DTO para DadosCadastroMedico/Paciente, vamos criar um DTO para devolver dados da API, entao sera um <DadosListagemMEdico>
        /*return repository.findAll();*/
        //Apos criar o DTO, o repository.findall() dara erro, pois findAll retorna uma lista de medico, a antidade JPA, mas o nosso retorno e DadosListagemMedico, entao vou ter que converter. Converter de medico para DadosListagemMEdico*/
        return repository.findAll(paginacao/*faco uma sobrecarga aqui no findall passando a paginacao*/).map(DadosListagemMedico::new)/*tiro o map, tolist e o strem, pois o map ja faz a conversao e ja retorna um page de dto automaticamente*/;/* 1.b .map(DadosListagemMedico::new) dara erro, assim eu preciso chamar um construtor do record DadosListagemMEdico, mas aqui dentro do DTO n existe um construtor que recebe um objeto do tipo medico */
        /*fazendo mapeamento para converter medicos para DadosListagemMEdicos*/
        /*com isso eu converto uma lista de medicos para uma lista de DadosListagemMEdico que e o nosso DTO*/
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){//@RequestBody @Valid DadosCadastroMedico dados so que n pdoemos usar esse DTO DadosCadsatroMEdico pois ele tem todos os campos obrigatorios e o enunciado diz que nao sao obrigatorios
        //crio um DTO com base no DadosCadastroMedico
        //Vou trocar o DadosCadastroMedico pelo novo DTO criado (DadosAtualizacaoMedico)

        var medico = repository.getReferenceById(dados.id());//carreguei o medico pelo ID que chega pelo DTO.
        medico.atualizarInformacoes(dados);//crio um metodo no meu OBJETO/CLASSE MEDICO e passo meu DTO dados. //criando os dados **b**
        /*Carreguei o MEdico pelo ID, chamei os metodos para atualizar baseados no DTO.*/
        /*Como falo para ele fazer um UPDATE no BD. Nao preciso fazer nada, pois o JPA se encarrega disso, pois nosso metodo esta anotado com uma @Transational dai nosso codigo vai ser, entao nosso trecho de codigo roda dentro de uma transacao, entao a JPA se vc carrega uma entidade do banco de dados e muda um atributo, quando a transacao for completa, a JPA detecta que teve uma mudanca e atualiza o atributo e faz update sozinho*/
    }

    @DeleteMapping("/{id}")//O spring sabe que i {id} que vem da URL e dinamico pq esta entre chaves e ele e um complemento do endereco inicial
    @Transactional//preciso usar pois vou fazer uma escrita (igual metodo de CADASTRAR E ATUALIZAR)
    //Vai receber um parametro dinamico, na barra de enderecos URI {ID} (OBS: todos parametro que tem ?size...?talCoisa sao parametros de queryes, convencionalmente para GET HTTP
    //
    public void excluir(@PathVariable Long id){//Para capturar o ID que vai chegar da nossa URL, basta eu passar ele como parametro. Utilizando o @PathVariable, ou seja, uma variavel do PATH da URL (caminho da URL)
        //Entao preciso acessar o banco de dados e chamo o repository
        repository.deleteById(id);//Ate aqui eu to fazendo uma esclusao fisica e nao uma exclusao logica(somente deixar em status OFF) conforme pede o enunciado

    }
}

//depois de ter feito tudo e estar tudo ok, teremos o erro onde nao e possivel criar o novo usuario
//..pois a requisicao que vem do postman para salvar no banco de dados, em uma tabela inexistente ainda
//..entao vamos trabalhar com o flyaway que e uma biblioteca que tem compatibilidade direta com o spring boot
//..devo criar uma pasta em resources da seguinte forma (criar diretorio: db/migration "a IDE vai entender como uma pasta DB e uma subpasta Migration e ficara escrito da seguinte forma -> db.migration"
//..Apos isso crio um arquivo .SQL que recebera meu codigo SQL, e esse arquivo deve ter a seguinte estrutura de nome
//..V1__algum-nome-descritivo-para-o-codigo-a-ser-executado.sql (v1 mostra a versao daquela migration e tem que ter dois underlines)

/*lembrar de fazer o retorno do controller retornar um page e nao uma lista, pois assim devolveremos informacoes sobre a paginacao tambem. Que sera mt util para o front*/
/*Para controlar o numero de registros que queremos devolver, devo passar na URL apos "?size=1" para retornar 1 registro e para paginar apos o size passo "&page=2" */

/** ordenacao***/
/*Para fazer ordenacao eu passo na URL tbm "?sort=nome", neste caso esta ordenando pelo campo nome do meu objeto json retornado na paginacao.*/
/*por padrao a ordenacao e de forma crescente, para mudar, posso passar na url tbm "?sorte=crm,desc" desc de decrescente e asc para ascendente (crescente)*/
/*por padrao o sort, sizem page, etc sao em ingles. Mas conseguimos mudar la "Customizacao de parametros application.properties"*/
/* @PageableDefault(size = 10, page = 0, sort = {"nome"} */
/*esse PageableDefault e utilizado caso n seja passado nada na url*/

/*PARA SABER COMO ELE TA FAZENDO A PAGINACAO E A ORDENACAO E EU QUERO VER ISSO NO CONSOLE, VER O LOG SQL QUE ELE ESTA USANDO PARA FAZER ESSA QUERY*/
/*POSSO FAZER ESSA CONFIG NO application.properties (spring.jpa.show-sql=true)*/
/*formatar o comando sql JPA que vai aparecer no console (spring.jpa.properties.hibernate.format_sql=true)*/


//****resumo put****
/*Nossa resuisicao de ATT, disparo uma requisicao do tipo PUT, passando o ID e os atributos que quero atualizar para identificar no BACKEND qual registro vai ser atualizado no banco de dados e no nosso controller/logica para att e bem simples. " carregamoso registro atual atraves do ID sobrescreve os atributos baseados nos novos campos que chegaram do DTO e pronto, nao preciso chamar nada do REPOSITORY o update vai ser feito atuomaticamente pela JPA"*/
