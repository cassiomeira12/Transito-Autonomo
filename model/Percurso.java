/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 09/11/17
* Ultima alteracao: 11/11/17
* Nome: Percurso
* Funcao: Armazena as coordenadas do percurso que o carro deve fazer
***********************************************************************/

package model;


import java.util.ArrayList;


public class Percurso {

  public static int HORIZONTAL = 1;//Para fazer uma Curva na Horizontal
  public static int VERTICAL = 0;//Para fazer uma Curva na Vertical
  private ArrayList<int[]> percurso;//Array com coordenadas X e Y do percurso


  /*********************************************
  * Metodo: Percurso - Construtor
  * Funcao: Constroi objetos da classe Percurso
  * Parametros: void
  * Retorno: void
  *********************************************/
  public Percurso() {
    this.percurso = new ArrayList<>();
  }

  /*********************************************
  * Metodo: Adicionar Coordenada
  * Funcao: Adiciona uma nova coordenada ao percurso do carro
  * Parametros: xFinal : int, yFinal : int
  * Retorno: void
  *********************************************/
  public void adicionarCoordenada(int xFinal, int yFinal) {
    int[] coordenadas = {xFinal, yFinal};
    percurso.add(coordenadas);
  }

  /*********************************************
  * Metodo: get
  * Funcao: Retorna uma coordenada de percurso do carro
  * Parametros: index : int
  * Retorno: int[] vetor
  *********************************************/
  public int[] get(int index) {
    return percurso.get(index);
  }

}//Fim class