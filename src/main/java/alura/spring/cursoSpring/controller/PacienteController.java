package alura.spring.cursoSpring.controller;

import alura.spring.cursoSpring.Paciente.DadosListarPaciente;
import alura.spring.cursoSpring.Paciente.DadosPaciente;
import alura.spring.cursoSpring.Paciente.PacienteEntitity;
import alura.spring.cursoSpring.Paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<DadosListarPaciente> listar(@PageableDefault(size = 5, page = 0, sort = {"nome"})Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListarPaciente::new);
    }
}
