package com.project.container.genectic.mutation;

import com.project.container.model.ObterResultado_Model;

public class Mutation {
    private ObterResultado_Model resultado;

    public Mutation(ObterResultado_Model resultado){
        this.resultado = resultado;
    }

    public void executarMutação(){
        int[] pop = resultado.getPopulacao();
        int tamanhoProblema = resultado.getTamanhoProblema();
        //PAROU AQUI
    }
}
