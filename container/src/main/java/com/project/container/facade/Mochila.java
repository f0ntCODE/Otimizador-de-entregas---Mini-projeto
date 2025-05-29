package com.project.container.facade;

import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;
import com.project.container.utils.Gerador;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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

        this.resultado = new ObterResultado_Model();
     }


     public int[] executarMetodoBasico() throws NullPointerException{
        this.gerador = new Gerador(capacidadeMochila, numeroItens, resultado);

        resultado.setTamanhoVetor(numeroItens);//armazenar o tamanho do vetor

        int[] solucao = gerador.gerarSolucaoInicial();

         System.out.println("Resultado da solucao: " + Arrays.toString(solucao));

         resultado.setSolucaoInicial(solucao); //salvar no modelo

         avaliador = new Avaliador(resultado);

         int[] resultados = avaliador.avaliar();//ativar o avalia

         return resultados;//lembre-se: posição 0 = lucro; posição 1 = peso
     }

     //obter o modelo de dados
     public ObterResultado_Model getSolucaoInicial(){
         return this.resultado;
     }
    
}
