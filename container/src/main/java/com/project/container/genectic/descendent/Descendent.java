package com.project.container.genectic.descendent;

import com.project.container.genectic.crossover.Crossover;
import com.project.container.genectic.selectionMethod.Tournament;
import com.project.container.model.ObterResultado_Model;

public class Descendent {

    private ObterResultado_Model resultados;
    private Crossover cruzamento;

    public Descendent(ObterResultado_Model resultados){
        this.resultados = resultados;

    }

    public void gerarDescendente(){
        int tamanhoPop = resultados.getTamanhoPopulacao();
        int tamanhoProblema = resultados.getTamanhoProblema();
        int tc = 0; //ainda a definir
        int quant = 5 * tamanhoPop;
        int corte = (int)Math.random() * tamanhoProblema;
        int i = 0;

        int[] pop = resultados.getPopulacao();
        int[] aptidao = resultados.getAptidao();

        double taxaMutacao = 0; //construir no modelo

        while(i < quant){
            int[] pai1 = new int[1]; //implementar método torneio
            int[] pai2 = new int[1]; //implementar método torneio
            int ale = (int)Math.random()*tamanhoProblema;

            if(ale < taxaMutacao){
                cruzamento.cruzarIndividuos();
                //parou aqui

            }
        }

    }
}
