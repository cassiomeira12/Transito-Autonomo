/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 08/11/17
* Ultima alteracao: 17/11/17
* Nome: Carro 1
* Funcao: Classe do Carro Amarelo
***********************************************************************/

package model;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Carro1 extends Carro {

  private Percurso percurso;//Classe que guarda o percurso que o Carro vai fazer
  private Velocimetro velocimetro;//Classe que possui o controle do Carro

  /*********************************************
  * Metodo: Carro - Construtor
  * Funcao: Constroi objetos da classe Carro
  * Parametros: void
  * Retorno: void
  *********************************************/
  public Carro1(int posX, int posY, int ang) {
    super(posX,posY,ang);

    this.cor = "Amarelo";//Atribuindo a cor do Carro

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
    this.velocimetro.setPosicao(150,420);//Setando a posicao do Velocimetro

    this.percurso = new Percurso();
    this.percurso.adicionarCoordenada(215,556);//Percurso 0
    this.percurso.adicionarCoordenada(270,432);//Percurso 1
    this.percurso.adicionarCoordenada(210,370);//Percurso 2
    this.percurso.adicionarCoordenada(140,370);//Percurso 3
    this.percurso.adicionarCoordenada(86,430);//Percurso 4
    this.percurso.adicionarCoordenada(86,500);//Percurso 5
    this.percurso.adicionarCoordenada(posX,posY);//Posicao Inicial
  }

  public void run() {
    try {

      while (true) {
        this.fazerPercurso(percurso.get(0));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        //ENTRA NO SEMAPHORO QUE EVITA DEADLOCK
        System.out.println("Carro "+cor+" Semaphore[9].acquire()");
        this.semaphoros[9].acquire();

        System.out.println("Carro "+cor+" Semaphore[0].acquire()");
        this.semaphoros[0].acquire();

        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(1),90);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[1].acquire()");
        this.semaphoros[1].acquire();

        //ENTRA NO CRUZAMENTO
        System.out.println("Carro "+cor+" Semaphore[7].acquire()");
        this.semaphoros[7].acquire();

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(2),0);

        System.out.println("Carro "+cor+" Semaphore[7].release()");
        this.semaphoros[7].release();
        //SAI DO CRUZAMENTO

        System.out.println("Carro "+cor+" Semaphore[0].release()");
        this.semaphoros[0].release();
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercurso(percurso.get(3));
        this.verificarPausa();//Verificando se o usuario pausou o Carro
        
        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(4),-90);

        System.out.println("Carro "+cor+" Semaphore[1].release()");
        this.semaphoros[1].release();

        System.out.println("Carro "+cor+" Semaphore[9].release()");
        this.semaphoros[9].release();
        this.verificarPausa();//Verificando se o usuario pausou o Carro
        //SAI DO SEMAPHORO QUE EVITA DEADLOCK

        this.fazerPercurso(percurso.get(5));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(6),180);
        this.verificarPausa();//Verificando se o usuario pausou o Carro
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