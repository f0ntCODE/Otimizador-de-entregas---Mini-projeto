package com.project.container.genectic.crossover;

import com.project.container.model.ObterResultado_Model;

public class Crossover {
    private ObterResultado_Model resultado;

    public Crossover(ObterResultado_Model resultado){
        this.resultado = resultado;
    }

    public void cruzarIndividuos(){
        int corte = resultado.getCorte();
        int tamanhoPop = resultado.getTamanhoPopulacao();
        int tamanhoProblema = resultado.getTamanhoProblema();

        int[] pop1 = resultado.getPop1();
        int[] pop2 = resultado.getPop2();

        int[] descend1 = new int[tamanhoPop];
        int[] descend2 = new int[tamanhoPop];

        for(int i = 0; i < corte; i ++){
            descend1[i] = pop1[i];
            descend2[i] = pop2[i];
        }

        for(int i = corte; i < tamanhoProblema; i ++){
            descend1[i] = pop2[i];
            descend2[i] = pop1[i];
        }

        resultado.setDescentendeCruzado1(descend1);
        resultado.setDescentendeCruzado2(descend2);

        System.out.println("Descendentes do cruzamento armazenados na memória");
    }
}
