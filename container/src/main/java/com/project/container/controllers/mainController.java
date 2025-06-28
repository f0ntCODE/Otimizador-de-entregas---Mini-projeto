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
        final int pesoMax = 500;    //final = valores constantes
        final int pesoMin = 20;

        mochila = new Mochila(capacidadeMochila, pesoMax, pesoMin, numeroItens); //inicializar valores

        int[] dados = mochila.executarMetodoBasico();
        System.out.println("\n\t DADOS DO MÉTODO BÁSICO");

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
                                Model model,
                                  @RequestParam (value = "tentativas", required = false) Integer tentativas,
                                  @RequestParam(value = "tempInicial", required = false) Integer tempInicial,
                                  @RequestParam(value = "tempFinal", required = false) Double tempFinal,
                                  @RequestParam(value = "fatorRedutor", required = false) Double fatorRedutor){
        model.addAttribute("metodoSelecionado", opcao);
        System.out.println("CONTROLADOR DIZ: Opção selecionada -> " + opcao);
        ObterResultado_Model resultadoModel = mochila.getSolucaoInicial();

        int[] resultado;

        if(opcao.equals("subidaEncosta")){

            resultado = subidaEncosta();
            model.addAttribute("subidas", Arrays.toString(resultado));
            model.addAttribute("lucro", resultadoModel.getSomaValorSubidaEncosta());
            model.addAttribute("peso", resultadoModel.getSomaPesoSubidaEncosta());

        }
        else if (opcao.equals("subidaEncostaTentativas")) {
            if(tentativas == null){tentativas = 5;}

                resultado = subidaEncostaComTentativa(tentativas);
                model.addAttribute("subidas", Arrays.toString(resultado));
            model.addAttribute("lucro", resultadoModel.getSomaValorSubidaEncosta());
            model.addAttribute("peso", resultadoModel.getSomaPesoSubidaEncosta());

        }
        else if (opcao.equals("temperaSimulada")) {
            if (tempInicial == null) {tempInicial = 10000;}
            if (tempFinal == null) {tempFinal = 0.9;}
            if (fatorRedutor == null) {fatorRedutor = 0.8;}

            resultado = temperaSimulada(tempInicial, tempFinal, fatorRedutor);
            model.addAttribute("tempera", Arrays.toString(resultado));
            model.addAttribute("lucro", resultadoModel.getSomaValorTempera());
            model.addAttribute("peso", resultadoModel.getSomaPesoTemperaSimulada());

        }
        else if (opcao == "todos") {

            //resultado = executarTodos();
            //model.addAttribute("ganhos", resultado);
        }
        return "basic_methods";
    }

    public int[] subidaEncosta(){

        System.out.println("executando subida de encosta");

        int[] resultadoSubida = mochila.executarSubidaDeEncosta();

        return resultadoSubida;
    }

    public int[] subidaEncostaComTentativa(int tentativas){

        System.out.println("executando subida de encosta");

        int[] resultadoSubida = mochila.executarSubidaEncostaTentativa(tentativas);

        return resultadoSubida;
    }

    public int[] temperaSimulada(int tempInicial, double tempFinal,double fatorRedutor){

        System.out.println("executando Tempera simulada");

        int[] resultadoTempera = mochila.executarTemperaSimulada(tempInicial, tempFinal, fatorRedutor);

        return resultadoTempera;
    }

    public int[] executarTodos(int tempInicial, double tempFinal,double fatorRedutor, int tentativas){

    System.out.println("Executando todos");

    mochila.executarTodos(tempInicial, tempFinal, fatorRedutor, tentativas);

        return new int[0];
    }


}
