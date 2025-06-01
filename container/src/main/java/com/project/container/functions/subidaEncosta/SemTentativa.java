package com.project.container.functions.subidaEncosta;

import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;
import com.project.container.utils.Gerador;
import com.project.container.utils.Verificador;

public class SemTentativa {

    private ObterResultado_Model resultados;
    private Verificador verificador;
    private Gerador gerador;
    private Avaliador avaliador;

    public SemTentativa(ObterResultado_Model resultados){
        this.resultados  = resultados;//obter acesso ao modelo
        this.verificador = new Verificador(resultados); //verificar os itens existentes do modelo atual
        this.gerador     = new Gerador(resultados.getPesoMaximo(), resultados.getTamanhoVetor(), resultados);
        this.avaliador   = new Avaliador(resultados);

    }

    public int[] subidaEncosta(){
        int[] atual    = resultados.getSolucaoInicial();
        int valorAtual = resultados.getSomaLucro();

        while(true){
            int[] novoVetor = gerador.gerarSucessores();
            int[]avaliados   = avaliador.avaliar();
            int valorNovo = avaliados[0]; //pegar o lucro

            if(valorNovo <= valorAtual){

                resultados.setSubidaEncosta(atual);

                return atual;
            }
            else{
                atual      = novoVetor;
                valorAtual = valorNovo;
            }
        }
    }

}
