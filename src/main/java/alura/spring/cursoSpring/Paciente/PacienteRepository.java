package alura.spring.cursoSpring.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntitity, Long> {
}
