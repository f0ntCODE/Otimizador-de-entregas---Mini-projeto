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

    public void verificarLucros(){System.out.println("VERIFICADOR DIZ-> Lucros salvos: " + Arrays.toString(resultado.getValores()));}

    public void verificarSolucaoInicial(){System.out.println("VERIFICADOR DIZ-> Solucao inicial salva: " + Arrays.toString(resultado.getSolucaoInicial()));}

    public void verificarResultadosAvaliados(){System.out.println("VERIFICADOR DIZ-> Resultados avaliados salvos [peso, lucro]: " + Arrays.toString(resultado.getAvaliado()));}

}
