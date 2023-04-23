package alura.spring.cursoSpring.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {//dentro do JpaRepository passamos dois tipos de Generics
    //o primeiro e qual o tipo da Entidade que esse repository vai trabalhar "Medico" o segundo qual o tipo do atributo da chave primaria da entidade do primeiro parametro
    //essa interface esta "vazia", na verdade nao esta pois estamos HERDANDO todos os metodos DO JPA, entao nao precisa mais criar todas entidades DAO (entity manager, comecar transacao, etc) tudo isso o spring faz automatico pra nos
//agora depois de criado o Repository posso utilizar no CONTROLLER
}
