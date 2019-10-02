/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 11/11/17
* Ultima alteracao: 11/11/17
* Nome: Velocimetro
* Funcao: Classe que controlar inicializacao e velocidade do carro
***********************************************************************/

package model;


import img.Imagem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.Cursor;
import javafx.event.ActionEvent;


public class Velocimetro {

  private Carro carro;//Referencia a Thread do Carro

  private AnchorPane painel;//Painel para agrupar itens do Velocimetro
  private ImageView imageVelocimetro;//ImageView do Velocimetro
  private Label corCarroLabel;//Label que define o Carro
  private Label velocidadeLabel;//Label que mostra a velocidade
  private Button buttonIniciar;//Button para controlar operacoes do Carro
  private Button velocidade1Button;//Button para selecionar a Velocidade 1
  private Button velocidade2Button;//Button para selecionar a Velocidade 2
  private Button velocidade3Button;//Button para selecionar a Velocidade 3
  private Button velocidade4Button;//Button para selecionar a Velocidade 4

  private Imagem allImage;//Classe que contem todas as imagens


  /*********************************************
  * Metodo: Velocimetro - Construtor
  * Funcao: Constroi objetos da classe Velocimetro
  * Parametros: void
  * Retorno: void
  *********************************************/
  public Velocimetro(Carro carro) {
    this.carro = carro;//Guardando referencia do Carro
    this.painel = new AnchorPane();//Inicializando painel do Velocimetro
    painel.setPrefSize(140,120);//Atribuindo tamanho ao Painel
    this.imageVelocimetro = new ImageView();//Inicializando ImageView do Velocimetro
    this.corCarroLabel = new Label(carro.cor);//Inicializando Label do Velocimetro a partir do Carro
    this.velocidadeLabel = new Label("10");//Inicializando Label da Velocidade do Carro
    this.buttonIniciar = new Button();//Inicializando Button para controlar Carro

    this.velocidade1Button = new Button();//Inicializando Button da Velocidade 1
    this.velocidade2Button = new Button();//Inicializando Button da Velocidade 2
    this.velocidade3Button = new Button();//Inicializando Button da Velocidade 3
    this.velocidade4Button = new Button();//Inicializando Button da Velocidade 4

    this.allImage = new Imagem();
  }

  /*********************************************
  * Metodo: setPosicao
  * Funcao: Atribuir as coordenadas da posicao do Velocimetro
  * Parametros: posX : int, posY : int
  * Retorno: void
  *********************************************/
  public void setPosicao(int posX, int posY) {
    this.painel.setLayoutX(posX);
    this.painel.setLayoutY(posY);
  }

  /*********************************************
  * Metodo: RetornarPainel
  * Funcao: Inicializa todos os itens do Velocimetro e retorna o Painel
  * Parametros: void
  * Retorno: AnchorPane
  *********************************************/
  public AnchorPane retornarPainel() {

    /*******************************************
    * ImageView do Carro
    ********************************************/
    allImage.trocarImagem(imageVelocimetro, "velocimetro1");
    imageVelocimetro.setPreserveRatio(true);
    imageVelocimetro.setFitWidth(140);//Adicionando largura
    painel.getChildren().add(imageVelocimetro);//Adicionando ao Painel

    /*******************************************
    * Label da Cor do Carro
    ********************************************/
    corCarroLabel.setStyle("-fx-font-weight: bold");//Deixando label em negrito
    corCarroLabel.setPrefWidth(100);//Atribuindo largura a Label
    corCarroLabel.setAlignment(Pos.CENTER);//Deixando o texto centralizado
    corCarroLabel.setLayoutX(20);//Posicao X do Label
    corCarroLabel.setLayoutY(30);//Posicao Y do Label
    painel.getChildren().add(corCarroLabel);//Adicionando ao Painel

    /*******************************************
    * Label da Velocidade do Carro
    ********************************************/
    //Deixando label branco e em negrito
    velocidadeLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: WHITE;");
    velocidadeLabel.setPrefWidth(20);//Atribuindo largura a Label
    velocidadeLabel.setAlignment(Pos.CENTER);//Deixando o texto centralizado
    velocidadeLabel.setLayoutX(60);//Posicao X do Label
    velocidadeLabel.setLayoutY(62);//Posicao Y do Label
    painel.getChildren().add(velocidadeLabel);//Adicionando ao Painel

    /*******************************************
    * Button Iniciar do Carro
    ********************************************/
    buttonIniciar.setText("Iniciar");
    buttonIniciar.setPrefSize(90,25);//Adicionando tamanho do Button
    buttonIniciar.setLayoutX(25);//Atribuindo a posicao X do Button
    buttonIniciar.setLayoutY(90);//Atribuindo a posicao Y do Button
    buttonIniciar.setFocusTraversable(false);//Retirando o foco do botao ao iniciar
    buttonIniciar.setCursor(Cursor.HAND);//Alterando o Cursor do mouse
    buttonIniciar.setOnAction((ActionEvent e) -> { contolarCarro(); });//Adicionando acao
    painel.getChildren().add(buttonIniciar);//Adicionando ao Painel

    /*******************************************
    * Button Velocidade 1 do Carro
    ********************************************/
    velocidade1Button.setPrefSize(40,20);//Adicionando tamanho do Button
    velocidade1Button.setMinHeight(20);//Adicionando altura
    velocidade1Button.setLayoutX(-3);//Atribuindo a posicao X do Botao
    velocidade1Button.setLayoutY(39);//Atribuindo a posicao Y do Botao
    velocidade1Button.setRotate(290);//Adicionando rotacao
    velocidade1Button.setOpacity(0);//Adicionando Opacidade ao Button
    velocidade1Button.setCursor(Cursor.HAND);//Alterando o Cursor do mouse
    velocidade1Button.setOnAction((ActionEvent e) -> { setVelocidade1(); });//Adicionando acao
    painel.getChildren().add(velocidade1Button);//Adicionando ao Painel

    /*******************************************
    * Button Velocidade 2 do Carro
    ********************************************/
    velocidade2Button.setPrefSize(40,20);//Adicionando tamanho do Button
    velocidade2Button.setPrefHeight(20);//Adicionando altura
    velocidade2Button.setLayoutX(28);//Atribuindo a posicao X do Botao
    velocidade2Button.setLayoutY(7);//Atribuindo a posicao Y do Botao
    velocidade2Button.setRotate(335);//Adicionando rotacao
    velocidade2Button.setOpacity(0);//Adicionando Opacidade ao Button
    velocidade2Button.setCursor(Cursor.HAND);//Alterando o Cursor do mouse
    velocidade2Button.setOnAction((ActionEvent e) -> { setVelocidade2(); });//Adicionando acao
    painel.getChildren().add(velocidade2Button);//Adicionando ao Painel

    /*******************************************
    * Button Velocidade 3 do Carro
    ********************************************/
    velocidade3Button.setPrefSize(40,20);//Adicionando tamanho do Button
    velocidade3Button.setPrefHeight(20);//Adicionando altura
    velocidade3Button.setLayoutX(72);//Atribuindo a posicao X do Botao
    velocidade3Button.setLayoutY(7);//Atribuindo a posicao Y do Botao
    velocidade3Button.setRotate(25);//Adicionando rotacao
    velocidade3Button.setOpacity(0);//Adicionando Opacidade ao Button
    velocidade3Button.setCursor(Cursor.HAND);//Alterando o Cursor do mouse
    velocidade3Button.setOnAction((ActionEvent e) -> { setVelocidade3(); });//Adicionando acao
    painel.getChildren().add(velocidade3Button);//Adicionando ao Painel

    /*******************************************
    * Button Velocidade 4 do Carro
    ********************************************/
    velocidade4Button.setPrefSize(40,20);//Adicionando tamanho do Button
    velocidade4Button.setPrefHeight(20);//Adicionando altura
    velocidade4Button.setLayoutX(103);//Atribuindo a posicao X do Botao
    velocidade4Button.setLayoutY(39);//Atribuindo a posicao Y do Botao
    velocidade4Button.setRotate(70);//Adicionando rotacao
    velocidade4Button.setOpacity(0);//Adicionando Opacidade ao Button
    velocidade4Button.setCursor(Cursor.HAND);//Alterando o Cursor do mouse
    velocidade4Button.setOnAction((ActionEvent e) -> { setVelocidade4(); });//Adicionando acao
    painel.getChildren().add(velocidade4Button);//Adicionando ao Painel

    return painel;
  }

  /*********************************************
  * Metodo: controlarCarro
  * Funcao: Inicializa ou Pausa a execucao da Thread do Carro
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void contolarCarro() {
    String operacao = this.buttonIniciar.getText().toString();
    if (operacao.equals("Pausar")) {
      this.carro.setPausar(true);//Alterando a variavel Pausar do Carro
      this.buttonIniciar.setText("Continuar");
    } else if (operacao.equals("Continuar")) {
      this.carro.setPausar(false);//Alterando a variavel Pausar do Carro
      this.buttonIniciar.setText("Pausar");
    } else {
      this.carro.start();//Inicializando Thread do Carro
      this.buttonIniciar.setText("Pausar");
    }
  }

  /*********************************************
  * Metodo: setVelocidade 1
  * Funcao: Altera a velocidade do Carro para a Minima
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void setVelocidade1() {
    this.carro.setVelocidade(40);//Aumentando o Sleep de espera do Carro
    this.velocidadeLabel.setText("10");//Alterando Label de velocidade
    this.allImage.trocarImagem(imageVelocimetro,"velocimetro1");//Alterando a imagem do Velocimetro
  }

  /*********************************************
  * Metodo: setVelocidade 2
  * Funcao: Altera a velocidade do Carro para superior a Minima
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void setVelocidade2() {
    this.carro.setVelocidade(30);//Aumentando o Sleep de espera do Carro
    this.velocidadeLabel.setText("20");//Alterando Label de velocidade
    this.allImage.trocarImagem(imageVelocimetro,"velocimetro2");//Alterando a imagem do Velocimetro
  }

  /*********************************************
  * Metodo: setVelocidade 3
  * Funcao: Altera a velocidade do Carro para inferior a Maxima
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void setVelocidade3() {
    this.carro.setVelocidade(20);//Diminuindo o Sleep de espera do Carro
    this.velocidadeLabel.setText("30");//Alterando Label de velocidade
    this.allImage.trocarImagem(imageVelocimetro,"velocimetro3");//Alterando a imagem do Velocimetro
  }

  /*********************************************
  * Metodo: setVelocidade 4
  * Funcao: Altera a velocidade do Carro para a Maxima
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void setVelocidade4() {
    this.carro.setVelocidade(10);//Diminuindo o Sleep de espera do Carro
    this.velocidadeLabel.setText("40");//Alterando Label de velocidade
    this.allImage.trocarImagem(imageVelocimetro,"velocimetro4");//Alterando a imagem do Velocimetro
  }

}//Fim class