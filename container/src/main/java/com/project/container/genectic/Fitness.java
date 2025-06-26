package com.project.container.genectic;

import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;

public class Fitness {
    private ObterResultado_Model resultado;
    private Avaliador avaliador;

    public Fitness(ObterResultado_Model resultado){
            this.resultado = resultado;
        }

    public int[] calcularFitness(){
        int tamanhoPop = resultado.getTamanhoPopulacao();
        int tamanhoProblema = resultado.getTamanhoProblema();
        int capacidadeMax = resultado.getPesoMaximo();
        int soma  = 0;
        int[] pop = resultado.getPopulacao();
        int[] fitness = new int[tamanhoProblema];

        for(int cont = 0; cont < tamanhoPop; cont ++){
            //PAROU AQUI fitness[cont] = avaliador.avaliar();

        }
    }
}
