package com.project.container.utils;

import com.project.container.model.ObterResultado_Model;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class Verificador {

    private ObterResultado_Model resultado;

    public Verificador(ObterResultado_Model resultado) {
        this.resultado = resultado;

        }

    public void verificarPeso(){System.out.println("VERIFICADOR DIZ-> Pesos salvos: " + Arrays.toString(resultado.getPesos()));}

    public void verificarPesoMaximo(){System.out.println("VERIFICADOR DIZ-> Peso máximo: " + resultado.getPesoMaximo());}

    public void verificarPesoMinimo(){System.out.println("VERIFICADOR DIZ-> Peso mínimo: " + resultado.getPesoMinimo());}

    public void verificarLucros(){System.out.println("VERIFICADOR DIZ-> Lucros salvos: " + Arrays.toString(resultado.getValores()));}

    public void verificarSolucaoInicial(){System.out.println("VERIFICADOR DIZ-> Solucao inicial salva: " + Arrays.toString(resultado.getSolucaoInicial()));}

    public void verificarResultadosAvaliados(){System.out.println("VERIFICADOR DIZ-> Resultados avaliados salvos [peso, lucro]: " + Arrays.toString(resultado.getAvaliado()));}

    public void verificarSubidaEncosta(){System.out.println("VERIFICADOR DIZ-> subida de encosta: " + Arrays.toString(resultado.getSubidaEncosta()));}

    public void verificarMaximoTentativas(){System.out.println("VERIFICADOR DIZ-> max tentativas " + resultado.getMaxTentativas());}

    public void verificarTamanhoVetor(){System.out.println("VERIFICADOR DIZ-> tamanho do vetor " + resultado.getTamanhoVetor());}

    public void verificarSomaLucros(){System.out.println("VERIFICADOR DIZ-> soma dos lucros: " + resultado.getSomaLucro());}

    public void verificarTemperaturaInicial(){System.out.println("VERIFICADOR DIZ-> temperatura inicial: " + resultado.getTemperaturaInicial());}

    public void verificarTemperaturaFinal(){System.out.println("VERIFICADOR DIZ-> temperatura final: " + resultado.getTemperaturaFinal());}

    public void verificarFatorRedutor(){System.out.println("VERIFICADOR DIZ-> fato redutor: " + resultado.getFatorRedutor());}

    public void verificarTemperaSimulada(){System.out.println("VERIFICADOR DIZ-> temperaSimilada: " + Arrays.toString(resultado.getTemperaSimulada()));}

}
