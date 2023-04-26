package alura.spring.cursoSpring.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {//dentro do JpaRepository passamos dois tipos de Generics
    //o primeiro e qual o tipo da Entidade que esse repository vai trabalhar "Medico" o segundo qual o tipo do atributo da chave primaria da entidade do primeiro parametro
    //essa interface esta "vazia", na verdade nao esta pois estamos HERDANDO todos os metodos DO JPA, entao nao precisa mais criar todas entidades DAO (entity manager, comecar transacao, etc) tudo isso o spring faz automatico pra nos
//agora depois de criado o Repository posso utilizar no CONTROLLER





//    *** MONTANDO UM METODO COM JPA PARA FAZER A EXCLUIR/DELETE LOGICO ***
    /* Existe um padrao de nomeclatura no SpringDATA que se criarmos um METODO seguindo um determinado padrao de nomeclatura
    * ele consegue montar a consulta QUERY e gerar um COMANDO SQL da meneira que desejarmos
    * A nomeclatura e: findAllByNomeDeUmAtributoTrue (utilizei o true pois ele e um BOOLEANO) ele ja vai saber que e para fazer
    * um SELECT * FROM MEDICOS WHERE ATIVO=TRUE e ele faz isso automaticamente*/
    //Optional<Object> findAllByAtivoTrue(Pageable paginacao);//Pegou o retorno errado entao vou corrigir

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);





}



