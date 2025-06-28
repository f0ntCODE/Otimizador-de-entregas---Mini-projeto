package com.project.container.facade;

import com.project.container.functions.subidaEncosta.ComTentativa;
import com.project.container.functions.subidaEncosta.SemTentativa;
import com.project.container.functions.tempera.TemperaSimulada;
import com.project.container.genectic.GenecticAlgorithm;
import com.project.container.genectic.crossover.Crossover;
import com.project.container.genectic.descendent.Descendent;
import com.project.container.genectic.mutation.Mutation;
import com.project.container.genectic.selectionMethod.Roulette;
import com.project.container.genectic.selectionMethod.Tournament;
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
    private Descendent descendente;
    private Crossover cruzamento;
    private Mutation mutacao;
    private Roulette roleta;
    private Tournament torneio;
    private GenecticAlgorithm algoritmoGenetico;
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
        this.descendente = new Descendent(resultado, torneio);

        this.cruzamento = new Crossover(resultado);
        this.mutacao = new Mutation(resultado);
        this.roleta = new Roulette(resultado);
        this.torneio = new Tournament(resultado);

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

        this.avaliador = new Avaliador(resultado);
        this.descendente = new Descendent(resultado, torneio);
        this.cruzamento = new Crossover(resultado);
        this.mutacao = new Mutation(resultado);
        this.roleta = new Roulette(resultado);
        this.torneio = new Tournament(resultado);
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

     public int[] executarAlgoritmoGenetico(double taxaCruzamento, double taxaMutacao, int tamanhoProblema,
                                           int tamanhoPopulacao, int numeroGeracoes, int metodoSelecao){
         resultado.setTamanhoProblema(tamanhoProblema);
         resultado.setTamanhoPopulacao(tamanhoPopulacao);

         if (resultado.getPopulacao() == null || resultado.getPopulacao().length == 0) {
             // Gere a população inicial aleatória
             int[] populacaoInicial = new int[tamanhoPopulacao * tamanhoProblema];
             Random rand = new Random();
             for (int i = 0; i < populacaoInicial.length; i++) {
                 populacaoInicial[i] = rand.nextBoolean() ? 1 : 0;
             }
             resultado.setPopulacao(populacaoInicial);
         }
         avaliador.avaliarIndividuos(resultado);

         descendente.gerarDescendentes(taxaCruzamento, taxaMutacao, 1);//gerar descendente inicial

         algoritmoGenetico = new GenecticAlgorithm(numeroGeracoes, taxaCruzamento, taxaMutacao, cruzamento,
                 mutacao, torneio, roleta, avaliador, resultado, descendente, metodoSelecao);

        return resultado.getPopAvaliado(); //última população
     }

}
