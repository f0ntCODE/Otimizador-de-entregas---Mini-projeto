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
        System.out.println("\n\t DADOS DA SUBIDA DE ENCOSTA COM TENTATIVA");

        final int limiteTentativa = resultados.getMaxTentativas();
        int[] atual        = resultados.getSolucaoInicial();
        int valorAtual     = resultados.getSomaLucro();
        int tentativaAtual = 0;

        while(true){

            int[] novoVetor = gerador.gerarSucessores();
            int[] avaliados   = avaliador.avaliarSucessor(novoVetor);

            int valorNovo = avaliados[0];

            if(valorNovo <= valorAtual){

                if(tentativaAtual > limiteTentativa) {
                    resultados.setSubidaEncostaTentativa(atual);

                    resultados.setSubidaEncostaTentativa(atual);
                    resultados.setSomaValorSubidaEncostaTentativa(valorAtual);
                    resultados.setSomaPesoSubidaEncostaTentativa(calcularPeso(resultados.getPesos(), atual));

                    return atual;
                }
                else{
                    tentativaAtual ++;
                    System.out.println("TENTATIVA: " + tentativaAtual);
                }
            }
            else{
                atual          = novoVetor;
                valorAtual     = valorNovo;
                tentativaAtual = 0;
            }
        }
    }

    private static int calcularPeso(int[] pesos, int[] solucao){
        int soma = 0;

        for(int i = 0; i < pesos.length; i ++){
            if(solucao[i] == 1) {

                soma += pesos[i];
            }
        }

        return soma;
    }
}

