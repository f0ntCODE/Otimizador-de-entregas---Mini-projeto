package com.project.container.utils;

import com.project.container.model.ObterResultado_Model;

import java.util.Arrays;

public class Verificador {

    private ObterResultado_Model resultado;

    public Verificador(){resultado = new ObterResultado_Model();}

    public void verificarPeso(){System.out.println("Pesos salvos: " + Arrays.toString(resultado.getPesos()));}

    public void verificarLucros(){System.out.println("Lucros salvos: " + Arrays.toString(resultado.getValores()));}

    public void verificarSolucaoInicial(){System.out.println("Solucao inicial salva: " + Arrays.toString(resultado.getSolucaoInicial()));}

    public void verificarResultadosAvaliados(){System.out.println("Resultados avaliados salvos: " + Arrays.toString(resultado.getAvaliado()));}

}
