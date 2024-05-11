/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Vista.*;
import Modelo.ExceptionLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ASUS
 */
public class ControladorLogin implements ActionListener {
    private VistaLogin vistal;
    private Controlador ctrl;
    private String text1;
    private String text2;

    public ControladorLogin(VistaLogin vistal, Controlador ctrl) {
        this.vistal= vistal;
        this.ctrl = ctrl;
        
        
        
        vistal.setVisible(true);
  
        this.vistal.btnStart.addActionListener(this); 
        
        vistal.lblException.setVisible(false);
        vistal.pnlException.setVisible(false);
    }
    
    @Override
     public void actionPerformed(ActionEvent e) {
         
         
         if (e.getSource().equals(vistal.btnStart)){
             try {
                 if(checkNameText()) {
                     
                    openMainWindow();
                     
                 } 
             } catch (ExceptionLogin ex) {
                 Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
     
     private boolean checkNameText() throws ExceptionLogin {
         
         try{
             text1 = getPlayer1Name();
             text2 = getPlayer2Name();
             
             if (text1.isEmpty() || text2.isEmpty() || text2.equals(text1) || text1.length()>14 || text2.length()>14 ) {
                throw new ExceptionLogin("");
             }else return true;
             
         }catch(ExceptionLogin e) {
             
            vistal.lblException.setVisible(true);
            vistal.pnlException.setVisible(true);
            return false;
         }
    }
     
    private void openMainWindow() {
        ctrl.setPlayerNames(text1, text2);
        ctrl.initializeGame();
        vistal.setVisible(false);
        
    }
    
    private String getPlayer1Name() {
        return vistal.txtNameJ1.getText();
    }

    private String getPlayer2Name() {
        return vistal.txtNameJ2.getText();
    }
    
}
