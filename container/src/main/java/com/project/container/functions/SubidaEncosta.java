package com.project.container.functions;

public class SubidaEncosta {
    private Sucessores sucessores;
    private Geradores gerador;
    private Avalia avalia;
    private SolucaoInicial solucaoInicial;

    public int[] subidaEncosta(int[] solucaoInicial, int valorInicial){
        int[] atual    = solucaoInicial;
        int valorAtual = valorInicial;

        int[] valores = gerador.gerarLucro();//valores adaptador para a situação
        int[] pesos   = gerador.gerarPeso();

        while(true){
            int[] novoVetor = sucessores.gerarSucessores(atual, pesos, valores, 600);
            int[]avaliados   = avalia.avaliaSolucao(novoVetor, pesos, valores, 6);
            int valorNovo = avaliados[1];

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

        int[] valores = gerador.gerarLucro();//valores adaptados para a situação
        int[] pesos   = gerador.gerarPeso();

        while(true){

            int[] novoVetor = sucessores.gerarSucessores(atual, pesos, valores, 600);
            //int vNovo = new SolucaoInicial()
            int valorNovo   = avalia.avaliaSolucao(novoVetor, pesos, valores, 6);

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
