package alura.spring.cursoSpring.medico;

import alura.spring.cursoSpring.endereco.DadosEndereco;
import alura.spring.cursoSpring.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

//Vamos ter os mesmos ATRIBUTOS que temos la no RECORD, la e uma ENTIDADE RECORD e aqui sera uma ENTIDADE JPA
//para podermos fazer a persistencia do dado.
//adicionando o JPA fazendo essa classe ser uma entidade JPA
@Table(name = "medicos")//tem que usar aspas duplas
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor//contrutor sem argumento que o JPA exige em todas entidades
@AllArgsConstructor
@EqualsAndHashCode(of = "id")//gerar o equals e o hashCode em cima do ID e nao de todos os atributos
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    //private Especialidade especialidade;//Aqui temos a classe enum
    //private Endereco endereco;//aqui o nome endereco e diferente do RECORD, entao ele ira reclamar para criar a classe Endereco, criada agora na pasta endereco

    //criando a anotacao @enum para a especialidade
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded//nao vou criar uma tabela no banco de dados e fazer um relacionamento
    //vou usar o esquema de EMBEDDABLE ATTRIBUTE do jpa para ele ficar em uma classe separada, mas no Banco de dados
    //ele considera que os campos dessa classe fazem parte da mesma tabela da tabela MEDICO
    //para isso funcionar vou la em endereco e adiciono @Embeddable
    private Endereco endereco;

    //Adicionando mais um dado no OBJETO MEDICO para fazer sentido na coluna criada no flyway V4, onde crio a coluna para exclusao logica
    private Boolean ativo;

    // ** 1.A ** Passando um construtor que recebe todos os parametros para Passar no MedicoController2
    public Medico(DadosCadastroMedico dados){
        this.ativo = true;//toda vez que eu instanciar um medico com esse construtor, ja vira com o status de ativo.
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());//Aqui eu vou fazer a mesma coisa, vou instanciar um OBJETO endereco e passar la na CLASSE ENDERECO crio um construtor que recebe Dados.Endereco que e o nosso DTO.


        // ** 1.A **  apos atribuir todos os dados volto para o controler
    }


    //POSSO CRIAR NA ENTIDADE E CLASSE ENDERECO POSSO CRIAR CONSTRUTORES QUE TRABALHAM COM DTO.

    //criando os dados para atualizar**b**
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        //implementando: Pegar o DTO que chega como parametro e usar os campos para atualizar os campos do OBJETO MEDICO.
        /*this.nome = dados.nome();//Se o nome nao conter o nome no JSON, o spring responde isso como nulo. Nao e bem isso que quero fazer, quero att apenas se o nome nao tiver nulo*/
        if(dados.nome() != null){ //Diferente de nulo
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarInformacoesEndereco(dados.endereco());/*posso criar um metodo na classe de endreco onde passo o Dados.endereco e la dentro ele att cada campo que necessita ser att.*/
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
//*****************************
// CURIOSIDADE: Ate aqui eu to mapeando a entidade, mas como vou pegar esse objeto medico e salvar no banco de dados?
//no geral usamos o padrao DAO (Padrao Data Access Object), mas aqui no Spring Boot, Spring DATA nao costuma ser o DAO
//pois o Spring Data ciou um mecanismo qye simplificou essa persistencia
//*****************************