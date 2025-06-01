package com.project.container.functions.subidaEncosta;

import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;
import com.project.container.utils.Gerador;
import com.project.container.utils.Verificador;

public class ComTentativa {
    private ObterResultado_Model resultados;
    private Verificador verificador;
    private Gerador gerador;
    private Avaliador avaliador;

    public ComTentativa(ObterResultado_Model resultados){
        this.resultados  = resultados;//obter acesso ao modelo
        this.verificador = new Verificador(resultados); //verificar os itens existentes do modelo atual
        this.gerador     = new Gerador(resultados.getPesoMaximo(), resultados.getTamanhoVetor(), resultados);
        this.avaliador   = new Avaliador(resultados);

    }

    public int[] subidaComTentativa(){
        final int limiteTentativa = resultados.getMaxTentativas();
        int[] atual        = resultados.getSolucaoInicial();
        int valorAtual     = resultados.getSomaLucro();
        int tentativaAtual = 0;

        while(true){

            int[] novoVetor = gerador.gerarSucessores();
            int[] avalia   = avaliador.avaliar();

            int valorNovo = avalia[0];

            if(valorNovo <= valorAtual){
                if(tentativaAtual > limiteTentativa) {


                    return atual;
                }
                else{
                    tentativaAtual ++;
                }
            }
            else{
                atual          = novoVetor;
                valorAtual     = valorNovo;
                tentativaAtual = 0;
            }
        }
    }
}

