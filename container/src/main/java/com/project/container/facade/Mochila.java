package com.project.container.facade;

import com.project.container.functions.subidaEncosta.ComTentativa;
import com.project.container.functions.subidaEncosta.SemTentativa;
import com.project.container.functions.tempera.TemperaSimulada;
import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;
import com.project.container.utils.Ganho;
import com.project.container.utils.Gerador;
import com.project.container.utils.Verificador;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

public class Mochila {
    /*  
     * VARIÁVEIS
     */

    private int capacidadeMochila, pesoMax, pesoMin, numeroItens = 0;
    private Gerador gerador;
    private ObterResultado_Model resultado;
    private Avaliador avaliador;
    private SemTentativa subidaSemTentativa;
    private ComTentativa subidaComTentativa;
    private Verificador verificador;
    private TemperaSimulada tempera;
    private Ganho ganho;

    //genetico
    private int tamanhoProblema;
    private double taxaMutacao;
    private double taxaCruzamento;
    private int numeroGeracoes;
    private int tamanhoPop;
    private int indiceGenetico;

     public Mochila(int capacidadeMochila, int pesoMax, int pesoMin, int numeroItens){
        this.capacidadeMochila = capacidadeMochila;
        this.pesoMax = pesoMax;
        this.pesoMin = pesoMin;
        this.numeroItens = numeroItens;

        this.resultado = new ObterResultado_Model();
        this.verificador = new Verificador(resultado);
        this.subidaSemTentativa = new SemTentativa(resultado);
        this.subidaComTentativa = new ComTentativa(resultado);
        this.tempera = new TemperaSimulada(resultado);

     }

    public Mochila(int indiceGenetico, int tamanhoPop, int numeroGeracoes, double taxaCruzamento,
                   double taxaMutacao, int tamanhoProblema, ObterResultado_Model resultado) {
        this.resultado = resultado;
        this.indiceGenetico = indiceGenetico;
        this.tamanhoPop = tamanhoPop;
        this.numeroGeracoes = numeroGeracoes;
        this.taxaCruzamento = taxaCruzamento;
        this.taxaMutacao = taxaMutacao;
        this.tamanhoProblema = tamanhoProblema;

    }

    public int[] executarMetodoBasico() throws NullPointerException{
        this.gerador = new Gerador(capacidadeMochila, numeroItens, resultado);

        resultado.setTamanhoVetor(numeroItens);//armazenar o tamanho do vetor
         resultado.setPesoMaximo(pesoMax);
         resultado.setPesoMinimo(pesoMin);


        int[] solucao = gerador.gerarSolucaoInicial();

         resultado.setSolucaoInicial(solucao); //salvar no modelo

         avaliador = new Avaliador(resultado);

         int[] resultados = avaliador.avaliar();//ativar o avalia

         return resultados;//lembre-se: posição 0 = lucro; posição 1 = peso
     }

     //obter o modelo de dados
     public ObterResultado_Model getSolucaoInicial(){
         return this.resultado;
     }

     public int[] executarSubidaDeEncosta() {

         int[] resultadoSubida = subidaSemTentativa.subidaEncosta();

         return resultadoSubida;
     }

     public int[] executarSubidaEncostaTentativa(int tentativas){

         resultado.setMaxTentativas(tentativas);
         verificador.verificarMaximoTentativas();

         int[] resultadoSubida = subidaComTentativa.subidaComTentativa();

         resultado.setSubidaEncosta(resultadoSubida);
         return resultadoSubida;
     }

     public int[] executarTemperaSimulada(int tInicial, double tFinal, double fatorRedutor){
         //armazenar os valores
         resultado.setTemperaturaInicial(tInicial);
         resultado.setTemperaturaFinal(tFinal);
         resultado.setFatorRedutor(fatorRedutor);

         int[] resultadoTempera = tempera.iniciarTempera();

         resultado.setTemperaSimulada(resultadoTempera);

         return resultadoTempera;
     }

     public int[] executarTodos(int tInicial, double tFinal, double fatorRedutor, int tentativas){

         //gerar o método básico

         this.gerador = new Gerador(capacidadeMochila, numeroItens, resultado);

         resultado.setTamanhoVetor(numeroItens);//armazenar o tamanho do vetor
         resultado.setPesoMaximo(pesoMax);
         resultado.setPesoMinimo(pesoMin);


         int[] solucao = gerador.gerarSolucaoInicial();

         resultado.setSolucaoInicial(solucao); //salvar no modelo

         avaliador = new Avaliador(resultado);

         avaliador.avaliar();//ativar o avalia e armazenar na memória

         //subida de encosta s/tentativa

         subidaSemTentativa.subidaEncosta(); //valor já armazenado na memória

         //subida de encosta com tentativa

         resultado.setMaxTentativas(tentativas);
         verificador.verificarMaximoTentativas();

         subidaComTentativa.subidaComTentativa();

         //têmpera simulada

         resultado.setTemperaturaInicial(tInicial);
         resultado.setTemperaturaFinal(tFinal);
         resultado.setFatorRedutor(fatorRedutor);

         tempera.iniciarTempera();

         //calcular o ganho

         ganho = new Ganho(resultado);
         double[] ganhos = ganho.calcularGanhos();

         System.out.println("Ganho Têmpera vs Encosta: " + ganhos[0] + "%");
         System.out.println("Ganho Têmpera vs Encosta com Tentativa: " + ganhos[1] + "%");
         System.out.println("Ganho Encosta vs Encosta com Tentativa: " + ganhos[2] + "%");


        return new int[5];
     }
}
