package alura.spring.cursoSpring.controller;

import alura.spring.cursoSpring.Paciente.DadosPaciente;
import alura.spring.cursoSpring.Paciente.PacienteEntitity;
import alura.spring.cursoSpring.Paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosPaciente obj){
        repository.save(new PacienteEntitity(obj));
    }
}
