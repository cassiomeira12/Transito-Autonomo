/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 08/11/17
* Ultima alteracao: 17/11/17
* Nome: Carro 2
* Funcao: Classe do Carro Azul
***********************************************************************/

package model;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Carro2 extends Carro {

  private Percurso percurso;//Classe que guarda o percurso que o Carro vai fazer
  private Velocimetro velocimetro;//Classe que possui o controle do Carro


  /*********************************************
  * Metodo: Carro - Construtor
  * Funcao: Constroi objetos da classe Carro
  * Parametros: void
  * Retorno: void
  *********************************************/
  public Carro2(int posX, int posY, int ang) {
    super(posX,posY,ang);

    this.cor = "Azul";//Atribuindo a cor do Carro

    /*******************************************
    * Adicionar ImagemView do Carro 
    ********************************************/
    ImageView imageCarro = new ImageView();
    imageCarro.setPreserveRatio(true);
    imageCarro.setFitWidth(80);//Adicionando altura
    imageCarro.setLayoutX(posX);//Posicao inicial X da Imagem
    imageCarro.setLayoutY(posY);//Posicao inicial Y da Imagem
    imageCarro.setRotate(ang);//Atribuindo angulacao na imagem
    this.setImage(imageCarro);//Atribuindo a imagem do carro criada

    this.velocimetro = new Velocimetro(this);//Inicializando Velocimetro
    this.velocimetro.setPosicao(515,235);//Setando a posicao do Velocimetro

    this.percurso = new Percurso();
    this.percurso.adicionarCoordenada(575,370);//Percurso 0
    this.percurso.adicionarCoordenada(637,128);//Percurso 1
    this.percurso.adicionarCoordenada(637,60);// Percurso 2
    this.percurso.adicionarCoordenada(330,6);//  Percurso 3
    this.percurso.adicionarCoordenada(270,128);//Percurso 4
    this.percurso.adicionarCoordenada(270,250);//Percurso 5
    this.percurso.adicionarCoordenada(270,310);//Percurso 6
    this.percurso.adicionarCoordenada(330,370);//Percurso 7
    this.percurso.adicionarCoordenada(515,370);//Percurso 8
  }

  public void run() {
    try {

      while (true) {
        this.fazerPercurso(percurso.get(0));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[5].acquire()");
        this.semaphoros[5].acquire();

        System.out.println("Carro "+cor+" Semaphore[4].acquire()");
        this.semaphoros[4].acquire();

        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(1),90);

        System.out.println("Carro "+cor+" Semaphore[4].release()");
        this.semaphoros[4].release();

        System.out.println("Carro "+cor+" Semaphore[3].release()");
        this.semaphoros[3].release();

        this.fazerPercurso(percurso.get(2));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(3),0);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(4),-90);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        //ENTRA NO CRUZAMENTO
        System.out.println("Carro "+cor+" Semaphore[8].acquire()");
        this.semaphoros[8].acquire();

        this.fazerPercurso(percurso.get(5));

        System.out.println("Carro "+cor+" Semaphore[8].release()");
        this.semaphoros[8].release();
        //SAIU DO CRUZAMENTO

        System.out.println("Carro "+cor+" Semaphore[5].release()");
        this.semaphoros[5].release();

        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercurso(percurso.get(6));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[3].acquire()");
        this.semaphoros[3].acquire();

        System.out.println("Carro "+cor+" Semaphore[2].acquire()");
        this.semaphoros[2].acquire();

        //ENTRA NO CRUZAMENTO
        System.out.println("Carro "+cor+" Semaphore[7].acquire()");
        this.semaphoros[7].acquire();

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(7),180);

        System.out.println("Carro "+cor+" Semaphore[7].release()");
        this.semaphoros[7].release();
        //SAI DO CRUZAMENTO

        this.fazerPercurso(percurso.get(8));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[2].release()");
        this.semaphoros[2].release();
      }

    } catch (InterruptedException e) {
      System.err.println(e.toString());
    }
  }

  /*********************************************
  * Metodo: getVelocimetro
  * Funcao: Retorna o painel do Velocimetro
  * Parametros: void
  * Retorno: painel : AnchorPane
  *********************************************/
  public AnchorPane getVelocimetro() {
    return this.velocimetro.retornarPainel();
  }

}//Fim class