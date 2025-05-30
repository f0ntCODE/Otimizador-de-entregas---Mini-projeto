package com.project.container.controllers;

import com.project.container.model.ObterResultado_Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.container.facade.Mochila;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class mainController {


    private Mochila mochila;

    //************************************Redirecionamento de páginas

    //iniciar página inicial
    @GetMapping("/")
    public String mainPage() {
     
        return "index";
    }

    @GetMapping("/metodos")
    public String metodosPage() {



        return "basic_methods";
    }

    @GetMapping("/info")
    public String infoPage() {

        return "info";
    }

    /* AÇÃO */

    @PostMapping("/gerar_problema")
        public String gerarProblema(@RequestParam("capacidadeMochila") int capacidadeMochila,
                            @RequestParam("numeroItens") int numeroItens,
                            Model model) {

    // Obter os resultados da classe Mochila
        final int pesoMax = 550;    //final = valores constantes
        final int pesoMin = 50;

        mochila = new Mochila(capacidadeMochila, pesoMax, pesoMin, numeroItens); //inicializar valores

        int[] dados = mochila.executarMetodoBasico();
        ObterResultado_Model resultadoModel = mochila.getSolucaoInicial();

        //para fins de debug
         System.out.println("CONTROLLER DIZ -> peso: " + dados[1]);
        System.out.println("CONTROLLER DIZ -> lucro: " + dados[0]);
        System.out.println("CONTROLLER DIZ: -> solução inicial" + Arrays.toString(resultadoModel.getSolucaoInicial()));

        model.addAttribute("lucro", dados[0]);
        model.addAttribute("peso", dados[1]);
        model.addAttribute("solucaoInicial", Arrays.toString(resultadoModel.getSolucaoInicial()));
    // Retornar a mesma página do formulário
        return "basic_methods";
    }

    @GetMapping("/metodo")
    public String metodoEscolhido(@RequestParam ("opcaoSelecionada") String opcao,
                                Model model, @RequestParam ("tentativas") int tentativas){
        model.addAttribute("metodoSelecionado", opcao);
        System.out.println("CONTROLADOR DIZ: Opção selecionada -> " + opcao);

        int[] resultado;

        if(opcao.equals("subidaEncosta")){

            resultado = subidaEncosta();
            model.addAttribute("subidas", Arrays.toString(resultado));

        } 
        else if (opcao.equals("subidaEncostaTentativas")) {

            resultado = subidaEncostaComTentativa();

        } 
        else if (opcao.equals("temperaSimulada")) {
            
        }
        else if (opcao == "todos") {
            
        }
        return "basic_methods";
    }

    public int[] subidaEncosta(){

        System.out.println("executando subida de encosta");

        int[] resultadoSubida = mochila.executarSubidaDeEncosta();

        return resultadoSubida;
    }

    public int[] subidaEncostaComTentativa(){

        System.out.println("executando subida de encosta");

        int[] resultadoSubida = mochila.executarSubidaDeEncosta();

        return resultadoSubida;
    }
}
