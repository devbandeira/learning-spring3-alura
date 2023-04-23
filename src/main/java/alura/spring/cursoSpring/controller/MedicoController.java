package alura.spring.cursoSpring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Dizendo aqui para o spring entender que e um controller(no caso REST para uma api REST).
@RequestMapping("medicos")//Passando o caminho de rota que quando for chamado, sera direcionado para aqui.
public class MedicoController {

    //colocando um metodo para o cadastro de medico
    @PostMapping//Dizer qual o verbo do protocolo HTTP que esse metodo vai usar
    public void medico(@RequestBody String json){//1. Posto passar um PARAMETRO para receber la do Postman um JSON.
        //1.1 Se eu passar uma "String json" e dentro do metodo vou dar um sout(json) para imprimir o que ele receber
        System.out.println(json);
        //1.2 Se eu reparar, vai me retornar NULL, pois nao passei para o metodo junto com o parametro o @RequestBody, para dizer que o spring vai pegar do corpo da requisicao
        //1.3 Passando o @RequestBody agora ele vai me retornar uma STRING do JSON
        //1.4 Caso eu queria pegar um atributo especifico do JSON que venha no meu metodo POST, fica dificil pois e uma STRING LITERAL e para chegar em um atributo especifico eu teria que fdazer um PARSER ate chegar no local que quero.
        //1.5 Para resolver isso e receber os campos de maneira SEPARADA. Tenho que criar uma classe e dentro da classe declarar os mesmos atributos que estao vindo dentro do JSON la do meu POSTMAN ou INSOMINIA

    }
}
