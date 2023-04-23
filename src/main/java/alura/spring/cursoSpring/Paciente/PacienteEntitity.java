package alura.spring.cursoSpring.Paciente;

import alura.spring.cursoSpring.endereco.DadosEndereco;
import alura.spring.cursoSpring.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

@Table(name = "pacientes")
@Entity(name = "PacienteEntitity")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class PacienteEntitity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public PacienteEntitity(@RequestBody DadosPaciente dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());

    }
}
