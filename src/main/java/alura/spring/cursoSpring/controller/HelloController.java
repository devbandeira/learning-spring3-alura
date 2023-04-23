package alura.spring.cursoSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Dizendo aqui para o spring entender que e um controller(no caso REST para uma api REST).
@RequestMapping("/hello")//Passando o caminho de rota que quando for chamado, sera direcionado para aqui.
public class HelloController {

    @GetMapping//quando receber um GET na rota hello, vai responder com esse metodo.
    public String hello(){
        return "Ola mundo";
    }

}
