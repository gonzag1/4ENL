/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author ASUS
 */
public class Modelo {
    private final int white = 0;
    private final int blue = 1;
    private final int red = 2;
    private String player1, player2;
    private int[][] boxes;
    private Stack<Integer>[] columnStacks;
    private int rows = 6;
    private int columns = 8;
    private int turn = 0;
    
    public Stack<Integer>[] getColumnStacks() {
        return columnStacks;
    }
    
    public int getTurn(){
        return turn;
    }
    public void setTurn(int turn){
        this.turn = turn;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }    
    public int getColumns() {
        return columns;
    }    
    public void setColumns(int columns) {
        this.columns = columns;
    }    
    public int[][] getBoxes() {
        return boxes;
    }
    public final void setBoxes() {
        this.boxes = new int[this.rows][this.columns];
    }    
    public String getPlayer1() {
        return player1;
    }
    public void setPlayer1(String player1) {
        this.player1 = player1;
    }
    public String getPlayer2() {
        return player2;
    }
    public void setPlayer2(String player2) {
        this.player2 = player2;
    }   
    private int getElementRandom(){
        return ThreadLocalRandom.current().nextInt(1,3);
    }
    public int whoStart(){
        
        if(turn==white){
            turn = getElementRandom();
            return turn;
        }
        return changeTurn();  
    }
    private int changeTurn(){
        if (turn == red) {
            turn = blue;
        } else {
            turn = red;
        }
        return turn;
    }
    
    public boolean verificateToken(int column, int[][] boxes) {
        
         return column >= 0 && column < boxes[0].length && boxes[0][column] == white;
    }
    
    public void initializeStack(){
        columnStacks = new Stack[getColumns()];
        
        for (int j = 0; j < getColumns(); j++) {
            columnStacks[j] = new Stack<>();
            for (int i = 0; i < getRows(); i++) {
                columnStacks[j].push(i);
            }
        }

        
    }
    
    public int putToken(int column, int turn, int[][] boxes){
        if(columnStacks[column].isEmpty()){
            return -1;
        }
        
        int row= columnStacks[column].pop();
        boxes[row][column]=turn;
        return row;
        
    }
    
    public boolean checkRow(int row,int turn){
        
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j+3 < getColumns(); j++) {
                if (boxes[i][j] == turn &&
                    boxes[i][j + 1] == turn &&
                    boxes[i][j + 2] == turn &&
                    boxes[i][j + 3] == turn) {
                    return true;
                }
            } 
        }    
        
        return false;
    }
    
    public boolean checkColumn(int row, int column,int turn){
        int cont=0;
        
            if(row<=2){
                for(int i=row;i<getRows();i++){
                    if(boxes[i][column]==turn){
                        cont++;
                        if(cont==4){
                            return true;
                        }
                    }else return false;
                } 
            }
        return false;
    }
    
    public boolean checkDiagonal1(int row, int column,int turn){
      //Verifica columna ascendente  
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (j + 3 < getColumns() && i - 3 >= 0) {
                    if (boxes[i][j] == turn &&
                        boxes[i - 1][j + 1] == turn &&
                        boxes[i - 2][j + 2] == turn &&
                        boxes[i - 3][j + 3] == turn) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean checkDiagonal2(int row, int column,int turn){
      //Verifica columna descendente   
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (j + 3 < getColumns() && i + 3 < getRows()) {
                    if (boxes[i][j] == turn &&
                        boxes[i + 1][j + 1] == turn &&
                        boxes[i + 2][j + 2] == turn &&
                        boxes[i + 3][j + 3] == turn) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean getWinner(int row, int column,int turn){
        return(checkRow(row,turn) || checkColumn(row,column,turn) || checkDiagonal1(row,column,turn) || checkDiagonal2(row,column,turn));
    }
    
    public boolean checkDraw(){
        int cont=0;
            for(int j=0;j<getColumns();j++){
                if(boxes[0][j]!=white){
                    cont++;
                }
            }
        return cont==8;
        
    }
    
    public boolean checkFullColumn(int column){//Verifica si la columna esta completa 
        return columnStacks[column].isEmpty();
    }
    
}
