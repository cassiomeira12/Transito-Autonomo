/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 08/11/17
* Ultima alteracao: 13/11/17
* Nome: Classe Carro
* Funcao: Modelo para criar carro (Thread)
***********************************************************************/

package model;

import img.Imagem;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import java.util.concurrent.Semaphore;


public class Carro extends Thread {

  protected Semaphore[] semaphoros;//Array dos Semaphoros
  protected String cor;//Cor do Carro
  protected ImageView imageCarro;//Imagem do Carro
  protected int posX;//Posicao X atual da Imagem
  protected int posY;//Posicao Y atual da Imagem
  protected int ang;//Angulacao da Imagem
  protected int velocidade = 40;//Velocidade de execucao do Barbeiro
  protected boolean pausar = false;//Pausa a execucao da Thread


  /*********************************************
  * Metodo: Carro - Construtor
  * Funcao: Constroi objetos da classe Carro
  * Parametros: void
  * Retorno: void
  *********************************************/
  public Carro(int posX, int posY, int ang) {
    this.posX = posX;//Atribuindo coordenada X do Carro
    this.posY = posY;//Atribuindo coordenada Y do Carro
    this.ang = ang;//Atribuindo angulo do Carro
  }

  /*********************************************
  * Metodo: setSemaphoros
  * Funcao: Atribuir a Array de Semaphoros
  * Parametros: semaphoros : Semaphore[]
  * Retorno: void
  *********************************************/
  public void setSemaphoros(Semaphore[] semaphoros) {
    this.semaphoros = semaphoros;
  }

  /*********************************************
  * Metodo: Fazer Percurso
  * Funcao: Move a imagem para fazer uma curva
  * Parametros: xFinal : int, yFinal : int, angFinal : int
  * Retorno: void
  *********************************************/
  protected void fazerPercurso(int []coordenadas) throws InterruptedException {
    int xFinal = coordenadas[0];//Posicao X final
    int yFinal = coordenadas[1];//Posicao Y final

    if (posX > xFinal) {
      if (posY > yFinal) {

        while (posX > xFinal || posY > yFinal) {
          if (posX > xFinal) {
            posX-=2;
          }
          if (posY > yFinal) {
            posY-=2;
          }
          Imagem.moverImagem(imageCarro,posX,posY,ang);
          Thread.sleep(velocidade);
        }
        
      } else {// posY < yFinal
        
        while (posX > xFinal || posY < yFinal) {
          if (posX > xFinal) {
            posX-=2;
          }
          if (posY < yFinal) {
            posY+=2;
          }
          Imagem.moverImagem(imageCarro,posX,posY,ang);
          Thread.sleep(velocidade);
        }

      }
    } else {// posX < xFinal
      if (posY > yFinal) {
        
        while (posX < xFinal || posY > yFinal) {
          if (posX < xFinal) {
            posX+=2;
          }
          if (posY > yFinal) {
            posY-=2;
          }
          Imagem.moverImagem(imageCarro,posX,posY,ang);
          Thread.sleep(velocidade);
        }

      } else {// posY < yFinal
        
        while (posX < xFinal || posY < yFinal) {
          if (posX < xFinal) {
            posX+=2;
          }
          if (posY < yFinal) {
            posY+=2;
          }
          Imagem.moverImagem(imageCarro,posX,posY,ang);
          Thread.sleep(velocidade);
        }

      }
    }
    
    ang = corrigirAng(ang);//Corrigindo um angulo que ficou negativo
  }

  /*********************************************
  * Metodo: Fazer Percurso com Curva
  * Funcao: Move a imagem para fazer uma curva e um Percurso
  * Parametros: orientacao : int, coordenadas : int[], angFinal : int
  * Retorno: void
  *********************************************/
  protected void fazerPercursoCurva(int orientacao, int[] coordenadas, int angFinal) throws InterruptedException {
    int xFinal = coordenadas[0];//Posicao X final
    int yFinal = coordenadas[1];//Posicao Y final

    int finalMedia = orientacao == Percurso.HORIZONTAL ? xFinal : yFinal;
    int coordenadaMedia = orientacao == Percurso.HORIZONTAL ? this.posX : this.posY;

    if (coordenadaMedia > finalMedia) {
      int posMedia = coordenadaMedia - 23;//Posicao X do meio da rua

      if (finalMedia == xFinal) {
        while (coordenadaMedia > posMedia) {
          coordenadaMedia-=2;
          Imagem.moverImagem(imageCarro,coordenadaMedia,posY,ang);
          Thread.sleep(velocidade);
        }
      } else {
        while (coordenadaMedia > posMedia) {
          coordenadaMedia-=2;
          Imagem.moverImagem(imageCarro,posX,coordenadaMedia,ang);
          Thread.sleep(velocidade);
        }
      }

    } else {// coordenadaMedia < finalMedia
      int posMedia = coordenadaMedia + 23;//Posicao X do meio da rua

      if (finalMedia == xFinal) {
        while (coordenadaMedia < posMedia) {
          coordenadaMedia+=2;
          Imagem.moverImagem(imageCarro,coordenadaMedia,posY,ang);
          Thread.sleep(velocidade);
        }
      } else {
        while (coordenadaMedia < posMedia) {
          coordenadaMedia+=2;
          Imagem.moverImagem(imageCarro,posX,coordenadaMedia,ang);
          Thread.sleep(velocidade);
        }
      }

    }

    posX = (int) imageCarro.getLayoutX();//Atualizando posicao X da imagem
    posY = (int) imageCarro.getLayoutY();//Atualizando posicao Y da imagem

    if (posX > xFinal) {
      if (posY > yFinal) {
        if (ang > angFinal) {// (posX > xFinal) && (posY > yFinal) && (ang > angFinal)

          while (posX > xFinal || posY > yFinal || ang > angFinal) {
            if (posX > xFinal) {
              posX-=2;
            }
            if (posY > yFinal) {
              posY-=2;
            }
            if (ang > angFinal){
              ang-=6;
            }
            Imagem.moverImagem(imageCarro,posX,posY,ang);
            Thread.sleep(velocidade);
          }

        } else {// (posX > xFinal) && (posY > yFinal) && (ang < angFinal)

          while (posX > xFinal || posY > yFinal || ang < angFinal) {
            if (posX > xFinal) {
              posX-=2;
            }
            if (posY > yFinal) {
              posY-=2;
            }
            if (ang < angFinal){
              ang+=6;
            }
            Imagem.moverImagem(imageCarro,posX,posY,ang);
            Thread.sleep(velocidade);
          }

        }
      } else {// posY < yFinal
        if (ang > angFinal) {// (posX > xFinal) && (posY < yFinal) && (ang > angFinal)

          while (posX > xFinal || posY < yFinal || ang > angFinal) {
            if (posX > xFinal) {
              posX-=2;
            }
            if (posY < yFinal) {
              posY+=2;
            }
            if (ang > angFinal){
              ang-=6;
            }
            Imagem.moverImagem(imageCarro,posX,posY,ang);
            Thread.sleep(velocidade);
          }


        } else {// (posX > xFinal) && (posY < yFinal) && (ang < angFinal)

          while (posX > xFinal || posY < yFinal || ang < angFinal) {
            if (posX > xFinal) {
              posX-=2;
            }
            if (posY < yFinal) {
              posY+=2;
            }
            if (ang < angFinal){
              ang+=6;
            }
            Imagem.moverImagem(imageCarro,posX,posY,ang);
            Thread.sleep(velocidade);
          }

        }
      }
    } else {// posX < xFinal
      if (posY > yFinal) {
        if (ang > angFinal) {// (posX < xFinal) && (posY > yFinal) && (ang > angFinal)

          while (posX < xFinal || posY > yFinal || ang > angFinal) {
            if (posX < xFinal) {
              posX+=2;
            }
            if (posY > yFinal) {
              posY-=2;
            }
            if (ang > angFinal){
              ang-=6;
            }
            Imagem.moverImagem(imageCarro,posX,posY,ang);
            Thread.sleep(velocidade);
          }

        } else {// (posX < xFinal) && (posY > yFinal) && (ang < angFinal)

          while (posX < xFinal || posY > yFinal || ang < angFinal) {
            if (posX < xFinal) {
              posX+=2;
            }
            if (posY > yFinal) {
              posY-=2;
            }
            if (ang < angFinal){
              ang+=6;
            }
            Imagem.moverImagem(imageCarro,posX,posY,ang);
            Thread.sleep(velocidade);
          }

        }
      } else {// posY < yFinal
        if (ang > angFinal) {// (posX < xFinal) && (posY < yFinal) && (ang > angFinal)

          while (posX < xFinal || posY < yFinal || ang > angFinal) {
            if (posX < xFinal) {
              posX+=2;
            }
            if (posY < yFinal) {
              posY+=2;
            }
            if (ang > angFinal){
              ang-=6;
            }
            Imagem.moverImagem(imageCarro,posX,posY,ang);
            Thread.sleep(velocidade);
          }


        } else {// (posX < xFinal) && (posY < yFinal) && (ang < angFinal)

          while (posX < xFinal || posY < yFinal || ang < angFinal) {
            if (posX < xFinal) {
              posX+=2;
            }
            if (posY < yFinal) {
              posY+=2;
            }
            if (ang < angFinal){
              ang+=6;
            }
            Imagem.moverImagem(imageCarro,posX,posY,ang);
            Thread.sleep(velocidade);
          }

        }
      }
    }
    
    ang = corrigirAng(ang);//Corrigindo um angulo que ficou negativo
  }

  /*********************************************
  * Metodo: Mover Imagem
  * Funcao: Move a ImageView para uma nova posicao (X,Y)
  * Parametros: ImageView, posX, posY
  * Retorno: void
  *********************************************/
  private void moverImagem(int posX, int posY, int ang) {
    Platform.runLater(new Runnable(){
      @Override
      public void run() {
        imageCarro.setLayoutX(posX);//Alterando a posicao X
        imageCarro.setLayoutY(posY);//Alterando a posicao Y
        imageCarro.setRotate(ang);//Rotacionando a imagem
      }
    });
  }

  /*********************************************
  * Metodo: setImage
  * Funcao: Adiciona a ImageView do carro
  * Parametros: imageCarro : ImageView
  * Retorno: void
  *********************************************/
  protected void setImage(ImageView imageCarro) {
    this.imageCarro = imageCarro;
    this.posX = (int) imageCarro.getLayoutX();
    this.posY = (int) imageCarro.getLayoutY();
    this.ang = (int) imageCarro.getRotate();
  }

  /*********************************************
  * Metodo: getImage
  * Funcao: Retorna a ImageView do carro
  * Parametros: void
  * Retorno: imageCarro : ImageView
  *********************************************/
  public ImageView getImage() {
    return imageCarro;
  }

  /*********************************************
  * Metodo: setVelocidade
  * Funcao: Modifica a velocidade de execucao dessa Thread
  * Parametros: int : Velocidade
  * Retorno: void
  *********************************************/
  public void setVelocidade(int velocidade) {
    this.velocidade = velocidade;//Tranformando velocidade em tempo de espera
  }

  /*********************************************
  * Metodo: Verificar Pausa
  * Funcao: Verifica se o usuario Pausou o Programa
  * Parametros: void
  * Retorno: void
  *********************************************/
  protected synchronized void verificarPausa() throws InterruptedException {
    if (pausar) {//Verificando se pode Pausar a execucao
      wait();//Trava a Thread
    }
  }

  /*********************************************
  * Metodo: setPausar
  * Funcao: Atribui a pausa ou o desbloqueio da Thread
  * Parametros: pausar : boolean
  * Retorno: void
  *********************************************/
  public synchronized void setPausar(boolean pausar) {
    this.pausar = pausar;//Pausa ou Continua a execucao
    if (!pausar) {//Quando for (false) a Thread tem que acordar
      this.notify();//Acorda a Thread
    }
  }

  /*********************************************
  * Metodo: Corrigir Angulo
  * Funcao: Corrige um angulo negativo para positivo
  * Parametros: ang : int
  * Retorno: int
  *********************************************/
  private int corrigirAng(int ang) {
    switch (ang) {
      case -90:
        ang = 270;
        break;
      case -180:
        ang = 180;
        break;
      case -270:
        ang = 90;
        break;
      case 360:
        ang = 0;
        break;
    }
    return ang;
  }

}//Fim class