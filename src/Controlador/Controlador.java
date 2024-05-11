/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Vista.Vista;
import Modelo.Modelo;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author ASUS
 */
public class Controlador implements ActionListener {

    
    private Vista vista;
    private Modelo modelo;
   
    private int countp1 = 0;
    private int countp2 = 0;
    
    
    
     public Controlador(Vista vista,Modelo modelo) {
         this.vista = vista;
         this.modelo = modelo;
         
         vista.lbltokenTurn.addMouseListener(new java.awt.event.MouseAdapter() {// Label Clicked Reiniciar
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltokenTurnMouseClicked(evt);
            }     
         });
         
         
         vista.setVisible(false);
         this.vista.btncolumn0.addActionListener(this);
         this.vista.btncolumn1.addActionListener(this);
         this.vista.btncolumn2.addActionListener(this);
         this.vista.btncolumn3.addActionListener(this);
         this.vista.btncolumn4.addActionListener(this);
         this.vista.btncolumn5.addActionListener(this);
         this.vista.btncolumn6.addActionListener(this);
         this.vista.btncolumn7.addActionListener(this);
         this.vista.btnExit.addActionListener(this);
         
    }
    @Override
     public void actionPerformed(ActionEvent e) {
         
         
         if (e.getSource().equals(vista.btncolumn0)){
             startGame(0);
             invalidFullColumn(0);
         }else if (e.getSource().equals(vista.btncolumn1)){
             startGame(1);
             invalidFullColumn(1);
         }else if (e.getSource().equals(vista.btncolumn2)){
             startGame(2);
             invalidFullColumn(2);
         }else if (e.getSource().equals(vista.btncolumn3)){
             startGame(3);
             invalidFullColumn(3);
         }else if (e.getSource().equals(vista.btncolumn4)){
             startGame(4);
             invalidFullColumn(4);
         }else if (e.getSource().equals(vista.btncolumn5)){
             startGame(5);
             invalidFullColumn(5);
         }else if (e.getSource().equals(vista.btncolumn6)){
             startGame(6);
             invalidFullColumn(6);
         }else if (e.getSource().equals(vista.btncolumn7)){
             startGame(7);
             invalidFullColumn(7);
         }else if (e.getSource().equals(vista.btnExit)){
             System.exit(0);
         }
    }
     
    private void startGame(int column) {
        int initialTurn = initializeGameTurn();
        playTurn(initialTurn, column);
    }

    private int initializeGameTurn() {
        int initialTurn;
        initialTurn = modelo.whoStart();
        colorTurnPlayer(initialTurn);
       // playerTurn(initialTurn);
        
        return initialTurn;
    }

    private void playTurn(int turn, int column) {
        if (modelo.verificateToken(column, modelo.getBoxes())) {//verificar si la columna no esta llena
            
            int row = modelo.putToken(column, turn, modelo.getBoxes());//buscar la ubicacion de la ficha que esta vacia
            setIconToken(getTokenValue(row, column), turn);//mostar en la vista el lugar seleccionado y le asigna el color

            if (modelo.getWinner(row, column, turn)) {
                manageWinner(turn);
            }
        } else modelo.whoStart();

        if (modelo.checkDraw()) {
            playersDraw();
            setIconToken(vista.lbltokenTurn, 3);
        }
    }

    private void manageWinner(int turn) {
        playerWinner(turn);
        winsCount(turn);

        for (int i = 0; i < 8; i++) {
            getColumnButton(i, false);
        }

        setVisibleLbl(true);
        setIconToken(vista.lbltokenTurn, 3);
    }
     
    private void playerTurn(int turn){
        
        if(turn == 1){
            vista.lblplayerTurn.setText(modelo.getPlayer2());
        }else{
            vista.lblplayerTurn.setText(modelo.getPlayer1());
        }
    }
    
    private void playerWinner(int turn){//Cambio de texto y colores cuando haya un ganador
        
        Color colorBlue = new Color(0, 0, 153);
        
        vista.lbltitleOrwinner.setText(" GANADOR:");
        if(turn == 1){
            vista.lbltitleOrnameW.setText(modelo.getPlayer1());
            vista.lbltitleOrnameW.setForeground(colorBlue);
            vista.lbltitleOrwinner.setForeground(colorBlue);
            colorTurnPlayer(2);
        }else{
            vista.lbltitleOrnameW.setText(modelo.getPlayer2());
            vista.lbltitleOrnameW.setForeground(Color.RED);
            vista.lbltitleOrwinner.setForeground(Color.RED);
            colorTurnPlayer(1);
        }
        
        vista.lblturnoftext.setText("");
        vista.lblplayerTurn.setText("");
    }
    
    private void playersDraw(){ //Texto de empate
        vista.lbltitleOrwinner.setText("EMPATE");
        vista.lbltitleOrnameW.setText("EMPATE");
        vista.lblturnoftext.setText("");
        vista.lblplayerTurn.setText("");
    }
    
    
     
    private javax.swing.JLabel getTokenValue(int row, int column) {
    //Llamo a la fila y la columna de la matriz y le asigno un lbl en especifico
    //dentro de la vista, cada lbl tiene su numero indicando primero la fila y 
    // despues la columna.
    //Esto me ayuda a poder identificar la ficha que tengo que cambiar de color 
        switch (row) {
        case 0:
            switch (column) {
                case 0: return vista.lbltoken00;
                case 1: return vista.lbltoken01;
                case 2: return vista.lbltoken02;
                case 3: return vista.lbltoken03;
                case 4: return vista.lbltoken04;
                case 5: return vista.lbltoken05;
                case 6: return vista.lbltoken06;
                case 7: return vista.lbltoken07;               
            }
            break;
        case 1:
            switch (column) {
                case 0: return vista.lbltoken10;
                case 1: return vista.lbltoken11;
                case 2: return vista.lbltoken12;
                case 3: return vista.lbltoken13;
                case 4: return vista.lbltoken14;
                case 5: return vista.lbltoken15;
                case 6: return vista.lbltoken16;
                case 7: return vista.lbltoken17;                
            }
            break;
        case 2:
            switch (column) {
                case 0: return vista.lbltoken20;
                case 1: return vista.lbltoken21;
                case 2: return vista.lbltoken22;
                case 3: return vista.lbltoken23;
                case 4: return vista.lbltoken24;
                case 5: return vista.lbltoken25;
                case 6: return vista.lbltoken26;
                case 7: return vista.lbltoken27;                
            }
            break;
        case 3:
            switch (column) {
                case 0: return vista.lbltoken30;
                case 1: return vista.lbltoken31;
                case 2: return vista.lbltoken32;
                case 3: return vista.lbltoken33;
                case 4: return vista.lbltoken34;
                case 5: return vista.lbltoken35;
                case 6: return vista.lbltoken36;
                case 7: return vista.lbltoken37;                
            }
            break;
        case 4:
            switch (column) {
                case 0: return vista.lbltoken40;
                case 1: return vista.lbltoken41;
                case 2: return vista.lbltoken42;
                case 3: return vista.lbltoken43;
                case 4: return vista.lbltoken44;
                case 5: return vista.lbltoken45;
                case 6: return vista.lbltoken46;
                case 7: return vista.lbltoken47;                
            }
            break;
        case 5:
            switch (column) {
                case 0: return vista.lbltoken50;
                case 1: return vista.lbltoken51;
                case 2: return vista.lbltoken52;
                case 3: return vista.lbltoken53;
                case 4: return vista.lbltoken54;
                case 5: return vista.lbltoken55;
                case 6: return vista.lbltoken56;
                case 7: return vista.lbltoken57;     
            }
            break;  
     }
    return null;
}
     
    private void setIconToken(JLabel lblToken, int turno) {
    //Llamo al token de getTokenValue() y le asigno un icono dependiendo del turno
        String url = "";
        switch (turno) {
            case 0:
                url= "/tokensph/blanco.png";
                
                break;
            case 1:
                url= "/tokensph/azul.png";
                
                break;
            case 2:
                url= "/tokensph/rojo.png";
                
                break;
            case 3:
                url= "/tokensph/reiniciar.png";
                
                break;
            default:
                throw new AssertionError();
        }
        lblToken.setIcon(new ImageIcon(getClass().getResource(url)));
        
    }
     
    private void initializeBoxes(){
        
        for (int i =0 ; i <modelo.getRows(); i++) {
            for (int j = 0; j <modelo.getColumns() ; j++) {
                javax.swing.JLabel etiqueta = getTokenValue(i, j);
                setIconToken(etiqueta,0);
            }
        }
    }
    
    public void initializeGame(){
        vista.setVisible(true);//Despues del login se ve la pantalla del juego 
        
        modelo.setTurn(0);
        int initialTurn = modelo.whoStart();
        colorTurnPlayer(initialTurn);
        playerTurn(initialTurn);
        modelo.setBoxes();
        modelo.getColumnStacks();
        modelo.initializeStack();
        initializeBoxes();
        
        
        vista.lbltitleOrwinner.setText("4 EN LÍNEA");
        vista.lbltitleOrnameW.setText("4 EN LÍNEA");
        vista.lblturnoftext.setText(" TURNO DE:");
        
        Color colorBlue = new Color(0, 0, 153);
        
        vista.lbltitleOrnameW.setForeground(Color.RED);
        vista.lbltitleOrwinner.setForeground(colorBlue);
        
        //Se habilitan los botones para insertar las fichas
        for(int i=0; i<8; i++){
            getColumnButton(i,true);
        }
        //se ocultan los botones y lbl del ganador
        setVisibleLbl(false);
        
    }
    
    private void colorTurnPlayer(int turn) {
        if (turn == 2) {
            setIconToken(vista.lbltokenTurn, 1);  // Color azul para el jugador 1
        } else if(turn == 1) {
            setIconToken(vista.lbltokenTurn, 2);  // Color rojo para el jugador 2
        }
    }
    
    private void winsCount(int turn){
        
        Color colorBlue = new Color(0, 0, 153);
        
        if(turn==1){
            countp1++;
        }else countp2++;
        
        vista.lblcontWins1.setForeground(colorBlue);
        vista.lblcontWins2.setForeground(Color.RED);
        
        vista.lblcontWins1.setText(Integer.toString(countp1));
        vista.lblcontWins2.setText(Integer.toString(countp2));
    }
    
    public void setPlayerNames(String player1, String player2) {// Guarda los nombres de Pantalla de Carga
        modelo.setPlayer1(player1);
        modelo.setPlayer2(player2);    
    }
    
    private void getColumnButton(int column, boolean status){//Busca el boton de la columna
        
        switch (column) {
                case 0: vista.btncolumn0.setEnabled(status);
                    break;
                case 1: vista.btncolumn1.setEnabled(status);
                    break;
                case 2: vista.btncolumn2.setEnabled(status);
                    break;
                case 3: vista.btncolumn3.setEnabled(status);
                    break;
                case 4: vista.btncolumn4.setEnabled(status);
                    break;
                case 5: vista.btncolumn5.setEnabled(status);
                    break;
                case 6: vista.btncolumn6.setEnabled(status);
                    break;
                case 7: vista.btncolumn7.setEnabled(status);
                    break;
                default:System.out.println("Error");
                    
            }
    }
    
    private void setVisibleLbl(boolean status){
        vista.btnExit.setVisible(status);
        vista.lblcontWins1.setVisible(status);
        vista.lblcontWins2.setVisible(status);
        vista.lblcontWins.setVisible(status);
    }
    
    private void invalidFullColumn(int column){// Inhabilita Boton de una Columna Completa
        if(modelo.checkFullColumn(column)){
            getColumnButton(column,false);
        }
    }

    public void lbltokenTurnMouseClicked(MouseEvent evt) {
        initializeGame();// Reiniciar Juego   
    }
}
