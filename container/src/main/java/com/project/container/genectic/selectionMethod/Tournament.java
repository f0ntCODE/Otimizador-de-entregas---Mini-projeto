package com.project.container.genectic.selectionMethod;

import com.project.container.model.ObterResultado_Model;

public class Tournament {
    private ObterResultado_Model resultado;

    public Tournament(ObterResultado_Model resultado){
        this.resultado = resultado;
    }

    public int selecionarCandidato(){
        double[] fitness = resultado.getAptidao();
        int tamanhoPop = resultado.getTamanhoPopulacao();
        int candidato1 = (int)(Math.random() * tamanhoPop);
        int candidato2 = (int)(Math.random() * tamanhoPop);

        if(fitness[candidato1] > fitness[candidato2]){
          return candidato1;
        }

        else{
            return candidato2;
        }
    }
}
