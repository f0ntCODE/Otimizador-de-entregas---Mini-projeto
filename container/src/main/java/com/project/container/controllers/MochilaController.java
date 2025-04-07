
 
package com.project.container.controllers;

import com.project.container.functions.Mochila;
import com.project.container.functions.backPack;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MochilaController {

    @PostMapping("/gerarProblema")
    public String gerarProblema(
            @RequestParam("capacidadeMochila") int capacidadeMochila,
            @RequestParam("numeroItens") int numeroItens,
            Model model
    ) {
        int pMin = 1;
        int pMax = 10;

        // Gerar pesos e lucros aleatórios
        int[] pesos = Mochila.gerarPeso(numeroItens, pMax, pMin);
        int[] lucros = Mochila.gerarLucro(numeroItens, pMax, pMin);

        // Gerar solução inicial
        int[] solucao = Mochila.solucaoInicial(numeroItens, pesos, lucros, capacidadeMochila);

        // Avaliar solução
        int valorPeso = 0;
        int valorLucro = 0;
        for (int i = 0; i < numeroItens; i++) {
            valorPeso += solucao[i] * pesos[i];
            valorLucro += solucao[i] * lucros[i];
        }

        // Construir texto para exibir na view
        StringBuilder resultado = new StringBuilder();
        resultado.append("Pesos: ");
        for (int p : pesos) resultado.append(p).append(" ");
        resultado.append("\nLucros: ");
        for (int l : lucros) resultado.append(l).append(" ");
        resultado.append("\nSolução Inicial: ");
        for (int s : solucao) resultado.append(s).append(" ");
        resultado.append("\nLucro Total: ").append(valorLucro);
        resultado.append("\nPeso Total: ").append(valorPeso);

        model.addAttribute("resultados", resultado.toString());

        return "basic_methods";
    }

    @GetMapping("/")
    public String home() {
        return "basic_methods";
    }
}
