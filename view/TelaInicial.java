/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 08/11/17
* Ultima alteracao: 17/11/17
* Nome: Tela Inicial
* Funcao: Mostar a inicial do Programa
***********************************************************************/

package view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.scene.text.Font;

import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import img.Imagem;
import model.*;
import java.util.concurrent.Semaphore;


public class TelaInicial {

  private static final String NOME_PROGRAMA = "Simulacao de Transito Autonomo";//Titulo da Janela
  private static final int PAINEL_WIDTH = 800;//Largura da Tela
  private static final int PAINEL_HEIGHT = 600;//Altura da Tela
  private static final boolean MAXIMIZAR_JANELA = false;//Desativando opcao de Maximizar a tela

  /*********************************************
  * Metodo: show
  * Funcao: Mostrar Tela do Programa
  * Parametros: void
  * Retorno: void
  *********************************************/
  public static void show(Stage palco) {

    palco.setTitle(NOME_PROGRAMA);//Adicionando titulo 
    palco.setResizable(MAXIMIZAR_JANELA);//Definir se a tela pode ser Maximizada
    AnchorPane painel = new AnchorPane();//Painel onde adiciona os componentes
    painel.setPrefSize(PAINEL_WIDTH, PAINEL_HEIGHT);//Adicionando tamanho ao Painel


    Imagem allImage = new Imagem();//Objeto que armazenas todas as imagens


    /*******************************************
    * Adicionar ImagemView Background
    ********************************************/
    ImageView imageBackground = new ImageView();
    allImage.trocarImagem(imageBackground, "background");//Adicionando a Imagem
    imageBackground.setPreserveRatio(true);
    imageBackground.setFitWidth(800);//Adicionando largura
    //Atribuir posicao (X,Y)
    imageBackground.setLayoutX(0);
    imageBackground.setLayoutY(-19);
    painel.getChildren().add(imageBackground);//Adicionando ao painel


    /*******************************************
    * Adicionar Button Semaphoros (Ajuda)
    ********************************************/
    Button semaphorosButton = new Button("Semaphoros");
    semaphorosButton.setLayoutX(700);//Posicao X do botao
    semaphorosButton.setLayoutY(5);//Posicao Y do botao
    semaphorosButton.setFocusTraversable(false);//Retirando o foco do botao ao iniciar
    painel.getChildren().add(semaphorosButton);
    //Abre a tela que mostra os Semaphoros e Percurso dos carros
    semaphorosButton.setOnAction((ActionEvent e) -> { telaSemaphorosPercurso(allImage); });



    int quantidade = 8;
    Semaphore[] semaphoros = new Semaphore[quantidade+2];
    for (int i=0; i<7; i++) {
      semaphoros[i] = new Semaphore(1);
    }

    semaphoros[3]  = new Semaphore(0);//Carro Azul inicia dentro de uma regiao critica
    semaphoros[1]  = new Semaphore(0);//Carro Rosa inicia dentro de uma regiao critica
    
    semaphoros[7] = new Semaphore(1);//Semaphoro para cruzamento 1
    semaphoros[8] = new Semaphore(1);//Semaphoro para cruzamento 2
    semaphoros[9] = new Semaphore(1);//Semaphoro para impedir DeadLock


    //Criando Carro 1 (AMARELO)
    Carro1 carro1 = new Carro1(180,556,180);
    carro1.setSemaphoros(semaphoros);
    allImage.trocarImagem(carro1.getImage(), "carro_amarelo");//Adicionando a Imagem
    //Adicionando ImageView do Carro e Velocimetro ao Painel
    painel.getChildren().addAll(carro1.getImage(), carro1.getVelocimetro());

    //Criando Carro 2 (AZUL)
    Carro2 carro2 = new Carro2(545,370,180);
    carro2.setSemaphoros(semaphoros);
    allImage.trocarImagem(carro2.getImage(), "carro_azul");//Adicionando a Imagem
    //Adicionando ImageView do Carro e Velocimetro ao Painel
    painel.getChildren().addAll(carro2.getImage(), carro2.getVelocimetro());

    //Criando Carro 3 (ROSA)
    Carro3 carro3 = new Carro3(180,370,180);
    carro3.setSemaphoros(semaphoros);
    allImage.trocarImagem(carro3.getImage(), "carro_rosa");//Adicionando a Imagem
    //Adicionando ImageView do Carro e Velocimetro ao Painel
    painel.getChildren().addAll(carro3.getImage(), carro3.getVelocimetro());

    //Criando Carro 4 (VERDE)
    Carro4 carro4 = new Carro4(545,556,180);
    carro4.setSemaphoros(semaphoros);
    allImage.trocarImagem(carro4.getImage(), "carro_verde");//Adicionando a Imagem
    //Adicionando ImageView do Carro e Velocimetro ao Painel
    painel.getChildren().addAll(carro4.getImage(), carro4.getVelocimetro());




    palco.setScene(new Scene(painel, PAINEL_WIDTH, PAINEL_HEIGHT));//Adicionando Cena ao Palco
    palco.show();//Mostrando o Palco

  }//Fim show

  /*********************************************
  * Metodo: telaSemaphorosPercurso
  * Funcao: Abre a tela que mostra os percurso e a regiao que cada semaphoro protege
  * Parametros: void
  * Retorno: void
  *********************************************/
  private static void telaSemaphorosPercurso(Imagem allImage) {
    Stage palco = new Stage();
    palco.setTitle("Semaphoros e Percursos");//Adicionando titulo 
    palco.setResizable(false);//Definir se a tela pode ser Maximizada
    AnchorPane painel = new AnchorPane();//Painel onde adiciona os componentes
    painel.setPrefSize(600,480);//Adicionando tamanho ao Painel


    /*******************************************
    * Adicionar ImagemView Background
    ********************************************/
    ImageView imageBackground = new ImageView();
    allImage.trocarImagem(imageBackground, "ajuda");//Adicionando a Imagem
    imageBackground.setPreserveRatio(true);
    imageBackground.setFitWidth(600);//Adicionando largura
    imageBackground.setFitHeight(550);//Adicionando largura
    painel.getChildren().add(imageBackground);//Adicionando ao painel


    palco.setScene(new Scene(painel,600,480));//Adicionando Cena ao Palco
    palco.show();//Mostrando o Palco
  }

}//Fim class
