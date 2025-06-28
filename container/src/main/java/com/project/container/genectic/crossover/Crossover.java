package com.project.container.genectic.crossover;

import com.project.container.model.ObterResultado_Model;

public class Crossover {
    private ObterResultado_Model resultado;

    public Crossover(ObterResultado_Model resultado){
        this.resultado = resultado;
    }

    public void cruzarIndividuos() {
        int corte = resultado.getCorte();
        int tamanhoPop = resultado.getTamanhoProblema();
        int tamanhoProblema = resultado.getTamanhoProblema();

        int[] pop1 = resultado.getPop1();
        int[] pop2 = resultado.getPop2();

        if (pop1.length != tamanhoProblema || pop2.length != tamanhoProblema) {


            if (corte < 0 || corte > tamanhoProblema) {
                throw new IllegalArgumentException("O ponto de corte está fora dos limites do cromossomo.");
            }

            int[] descend1 = new int[tamanhoPop];
            int[] descend2 = new int[tamanhoPop];

            for (int i = 0; i < corte; i++) {
                descend1[i] = pop1[i];
                descend2[i] = pop2[i];
            }

            for (int i = corte; i < tamanhoProblema; i++) {
                descend1[i] = pop2[i];
                descend2[i] = pop1[i];
            }

            resultado.setDescendendeCruzado1(descend1);
            resultado.setDescendendeCruzado2(descend2);

            System.out.println("Descendentes do cruzamento armazenados na memória");
        }
    }
}
