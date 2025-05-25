package com.project.container.functions;

public class SubidaEncosta {
    private Sucessores sucessores;
    private Geradores gerador;
    private Avalia avalia;

    public int[] subidaEncosta(int[] solucaoInicial, int valorInicial){
        int[] atual    = solucaoInicial;
        int valorAtual = valorInicial;

        int[] valores = gerador.gerarLucro(6);//valores adaptador para a situação
        int[] pesos   = gerador.gerarPeso(6);

        while(true){
            int[] novoVetor = sucessores.gerarSucessores(atual, pesos, valores, 600);
            int valorNovo   = Integer.parseInt(avalia.avaliaSolucao(novoVetor, pesos, valores, 6));

            if(valorNovo <= valorAtual){

                return atual;
            }
            else{
                atual      = novoVetor;
                valorAtual = valorNovo;
            }
        }
    }

    public int[] subidaComTentativa(int[] solucaoInicial, int valorInicial, int maxTentativa){
        final int limiteTentativa = maxTentativa;

        int[] atual        = solucaoInicial;
        int valorAtual     = valorInicial;
        int tentativaAtual = 0;

        int[] valores = gerador.gerarLucro(6);//valores adaptados para a situação
        int[] pesos   = gerador.gerarPeso(6);

        while(true){
            int[] novoVetor = sucessores.gerarSucessores(atual, pesos, valores, 600);
            int valorNovo   = Integer.parseInt(avalia.avaliaSolucao(novoVetor, pesos, valores, 6));

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
