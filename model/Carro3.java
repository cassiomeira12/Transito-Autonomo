/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 08/11/17
* Ultima alteracao: 17/11/17
* Nome: Carro 3
* Funcao: Classe do Carro Rosa
***********************************************************************/

package model;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Carro3 extends Carro {

  private Percurso percurso;//Classe que guarda o percurso que o Carro vai fazer
  private Velocimetro velocimetro;//Classe que possui o controle do Carro

  /*********************************************
  * Metodo: Carro - Construtor
  * Funcao: Constroi objetos da classe Carro
  * Parametros: void
  * Retorno: void
  *********************************************/
  public Carro3(int posX, int posY, int ang) {
    super(posX, posY, ang);

    this.cor = "Rosa";//Atribuindo a cor do Carro

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
    this.velocimetro.setPosicao(150,235);//Setando a posicao do Velocimetro

    this.percurso = new Percurso();
    this.percurso.adicionarCoordenada(210,370);//Percurso 0
    this.percurso.adicionarCoordenada(330,370);//Percurso 1
    this.percurso.adicionarCoordenada(515,370);//Percurso 2
    this.percurso.adicionarCoordenada(575,370);//Percurso 3
    this.percurso.adicionarCoordenada(637,250);//Percurso 4
    this.percurso.adicionarCoordenada(575,190);//Percurso 5
    this.percurso.adicionarCoordenada(515,190);//Percurso 6
    this.percurso.adicionarCoordenada(335,190);//Percurso 7
    this.percurso.adicionarCoordenada(205,190);//Percurso 8
    this.percurso.adicionarCoordenada(145,190);//Percurso 9
    this.percurso.adicionarCoordenada(86,315);// Percurso 10
    this.percurso.adicionarCoordenada(posX,posY);//Posicao Inicial
  }

  public void run() {
    try {

      while (true) {
        this.fazerPercurso(percurso.get(0));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[3].acquire()");
        this.semaphoros[3].acquire();

        System.out.println("Carro "+cor+" Semaphore[2].acquire()");
        this.semaphoros[2].acquire();

        this.fazerPercurso(percurso.get(1));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[1].release()");
        this.semaphoros[1].release();

        this.fazerPercurso(percurso.get(2));

        System.out.println("Carro "+cor+" Semaphore[2].release()");
        this.semaphoros[2].release();

        System.out.println("Carro "+cor+" Semaphore[9].release()");
        this.semaphoros[9].release();
        //SAI DO SEMAPHORO QUE EVITA DEADLOCK

        this.fazerPercurso(percurso.get(3));
        this.verificarPausa();//Verificando se o usuario pausou o Carro
        
        System.out.println("Carro "+cor+" Semaphore[4].acquire()");
        this.semaphoros[4].acquire();
        
        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(4),90);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(5),0);

        System.out.println("Carro "+cor+" Semaphore[4].release()");
        this.semaphoros[4].release();

        System.out.println("Carro "+cor+" Semaphore[3].release()");
        this.semaphoros[3].release();

        this.fazerPercurso(percurso.get(6));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        System.out.println("Carro "+cor+" Semaphore[6].acquire()");
        this.semaphoros[6].acquire();

        this.fazerPercurso(percurso.get(7));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        //CRUZAMENTO
        System.out.println("Carro "+cor+" Semaphore[8].acquire()");
        this.semaphoros[8].acquire();

        this.fazerPercurso(percurso.get(8));
        System.out.println("Carro "+cor+" Semaphore[8].release()");
        this.semaphoros[8].release();
        //SAIU DO CRUZAMENTO

        System.out.println("Carro "+cor+" Semaphore[6].release()");
        this.semaphoros[6].release();

        this.fazerPercurso(percurso.get(9));
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        this.fazerPercursoCurva(Percurso.HORIZONTAL,percurso.get(10),-90);
        this.verificarPausa();//Verificando se o usuario pausou o Carro

        //ENTRA NO SEMAPHORO QUE EVITA DEADLOCK
        System.out.println("Carro "+cor+" Semaphore[9].acquire()");
        this.semaphoros[9].acquire();

        System.out.println("Carro "+cor+" Semaphore[1].acquire()");
        this.semaphoros[1].acquire();

        this.fazerPercursoCurva(Percurso.VERTICAL,percurso.get(11),180);
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