package com.project.container.genectic.mutation;

import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;

public class Mutation {
    private ObterResultado_Model resultado;
    private Avaliador avaliador;

    public Mutation(ObterResultado_Model resultado){
        this.resultado = resultado;
    }

    //esta mutação é de troca simples
    public void executarMutação(){
        int[] pop = resultado.getPopulacao();
        int tamanhoProblema = resultado.getTamanhoProblema();
        int posicao = (int)Math.random()*tamanhoProblema;
        int capacidadeMax = resultado.getPesoMaximo();

        while(pop[posicao] == 0){
             posicao = (int)Math.random()*tamanhoProblema;

        }
        pop[posicao] = 0; //inverter o valor; tirar da mochila

        for(int i = 0; i < tamanhoProblema; i++){
            pop[i] = 1;
            int valor = 0; //implementar função avaliador.avaliarPopulacao();

            if(valor < capacidadeMax){
                pop[i] = 0;
            }
        }

        for(int i = posicao + 1; i < tamanhoProblema; i ++){
            pop[i] = 1;
            int valor = 0; //implementar função avaliador.avaliarPopulacao();

            if(valor > capacidadeMax){
                pop[i] = 0;
            }
        }

        resultado.setPopulacao(pop);

    }
}
