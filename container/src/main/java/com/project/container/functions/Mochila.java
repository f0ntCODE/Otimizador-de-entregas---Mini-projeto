package com.project.container.functions;

import java.util.Arrays;

public abstract class Mochila {

    private Geradores gerador;
    private Avalia avalia;
    private SolucaoInicial solucaoInicial;


    public String[] obterResultados(int capacidadeMochila, int pMax, int pMin, int numeroItens) {

        this.gerador = new Geradores(capacidadeMochila, pMax, pMin, numeroItens);

        // Gerar pesos e lucros
        int[] pesos = gerador.gerarPeso(numeroItens, pMax, pMin);

        int[] lucros = gerador.gerarLucro(numeroItens, pMax, pMin);
    
        // Obter solução inicial
        this.solucaoInicial = new SolucaoInicial(numeroItens, pesos, lucros, capacidadeMochila);

        int[] solucao = solucaoInicial.gerarSolucaoInicial(numeroItens, pesos, lucros, capacidadeMochila);
    
        this.avalia = new Avalia(solucao, pesos, lucros, numeroItens);

        // Avaliar a solução
        String resultadoAvaliado = avalia.avaliaSolucao(solucao, pesos, lucros, numeroItens);
            
        // Formatar os resultados
        String resultadoPesos = "Todos os Pesos (KG): "   + Arrays.toString(pesos);
        String resultadoLucros = "Todos os Lucros (R$): " + Arrays.toString(lucros);
    
        return new String[]{resultadoPesos, resultadoLucros, resultadoAvaliado};
    }
}
