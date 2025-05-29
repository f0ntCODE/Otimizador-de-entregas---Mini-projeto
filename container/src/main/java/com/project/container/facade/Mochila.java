package com.project.container.facade;

import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;
import com.project.container.utils.Gerador;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mochila {
    /*  
     * VARIÁVEIS
     */

    private int capacidadeMochila, pesoMax, pesoMin, numeroItens = 0;
    private Gerador gerador;
    private ObterResultado_Model resultado;
    private Avaliador avaliador;

     public Mochila(int capacidadeMochila, int pesoMax, int pesoMin, int numeroItens){
        this.capacidadeMochila = capacidadeMochila;
        this.pesoMax = pesoMax;
        this.pesoMin = pesoMin;
        this.numeroItens = numeroItens;
     }


     public void executarMetodoBasico(){
        this.gerador = new Gerador(capacidadeMochila, numeroItens);

        int[] solucao = gerador.gerarSolucaoInicial();

         System.out.println("Resultado da solucao: " + solucao);

         resultado.setSolucaoInicial(solucao); //salvar no modelo
         int[] avalia = avaliador.avaliar();//ativar o avalia

         try {
             resultado.setSomaLucro(avalia[0]);
             resultado.setSomaPeso(avalia[1]);
         }
         catch (Exception ex){
             System.err.println("ERRO: problema ao salvar itens no modelo. CLASSE: MOCHILA");
             ex.printStackTrace();
         }
         //return avalia; //lembre-se: posição 0 = lucro; posição 1 = peso
     }
    
}
