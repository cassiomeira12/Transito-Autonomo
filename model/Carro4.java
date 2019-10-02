/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 08/11/17
* Ultima alteracao: 17/11/17
* Nome: Carro 4
* Funcao: Classe do Carro Verde
***********************************************************************/

package model;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Carro4 extends Carro {

  private Percurso percurso;//Classe que guarda o percurso que o Carro vai fazer
  private Velocimetro velocimetro;//Classe que possui o controle do Carro
  /*********************************************
  * Metodo: Carro - Construtor
  * Funcao: Constroi objetos da classe Carro
  * Parametros: void
  * Retorno: void
  *********************************************/
  public Carro4(int posX, int posY, int ang) {
    super(posX, posY, ang);

    this.cor = "Verde";//Atribuindo a cor do Carro

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
    this.velocimetro.setPosicao(515,420);//Setando a posicao do Velocimetro

    this.percurso = new Percurso();
    this.percurso.adicionarCoordenada(580,556);// Percurso 0
    this.percurso.adicionarCoordenada(638,430);// Percurso 1
    this.percurso.adicionarCoordenada(638,135);// Percurso 2
    this.percurso.adicionarCoordenada(638,60);//  Percurso 3
    this.percurso.adicionarCoordenada(330,6);//   Percurso 4
    this.percurso.adicionarCoordenada(270,130);// Percurso 5
    this.percurso.adicionarCoordenada(335,190);// Percurso 6
    this.percurso.adicionarCoordenada(395,190);// Percurso 7
    this.percurso.adicionarCoordenada(455,250);// Percurso 8
    this.percurso.adicionarCoordenada(455,310);// Percurso 9
    this.percurso.adicionarCoordenada(330,370);// Percurso 10
    this.percurso.adicionarCoordenada(270,435);// Percurso 11
    this.percurso.adicionarCoordenada(270,500);// Percurso 12
    this.percurso.adicionarCoordenada(330,555);// Percurso 13
    this.percurso.adicionarCoordenada(posX,posY);//Posicao Inicial
  }

  public void run() {
    try {

      while (true) {
        this.fazerPercurso(percurso.get(0));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(1),90);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[5].acquire()");
        this.semaphoros[5].acquire();

        System.out.println("Carro "+cor+" Semaphore[4].acquire()");
        this.semaphoros[4].acquire();

        this.fazerPercurso(percurso.get(2));

        System.out.println("Carro "+cor+" Semaphore[4].release()");
        this.semaphoros[4].release();
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercurso(percurso.get(3));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(4),0);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(5),-90);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[6].acquire()");
        this.semaphoros[6].acquire();

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(6),180);

        System.out.println("Carro "+cor+" Semaphore[5].release()");
        this.semaphoros[5].release();

        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercurso(percurso.get(7));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(8),270);

        System.out.println("Carro "+cor+" Semaphore[6].release()");
        this.semaphoros[6].release();

        this.fazerPercurso(percurso.get(9));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        //ENTRA NO SEMAPHORO QUE EVITA DEADLOCK
        System.out.println("Carro "+cor+" Semaphore[9].acquire()");
        this.semaphoros[9].acquire();

        System.out.println("Carro "+cor+" Semaphore[2].acquire()");
        this.semaphoros[2].acquire();

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(10),360);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[0].acquire()");
        this.semaphoros[0].acquire();

        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(11),-90);

        System.out.println("Carro "+cor+" Semaphore[2].release()");
        this.semaphoros[2].release();

        this.fazerPercurso(percurso.get(12));

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(13),180);

        System.out.println("Carro "+cor+" Semaphore[0].release()");
        this.semaphoros[0].release();

        System.out.println("Carro "+cor+" Semaphore[9].release()");
        this.semaphoros[9].release();
        //SAI DO SEMAPHORO QUE EVITA DEADLOCK

        this.fazerPercurso(percurso.get(14));
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