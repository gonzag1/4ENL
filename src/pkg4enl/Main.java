/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4enl;


import Controlador.Controlador;
import Controlador.ControladorLogin;
import Modelo.Modelo;
import Vista.Vista;
import Vista.VistaLogin;
/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        VistaLogin vistal = new VistaLogin();
        Vista vista = new Vista();
        Modelo modelo = new Modelo();

        Controlador ctrl = new Controlador(vista, modelo);
        ControladorLogin ctrllogin = new ControladorLogin(vistal,ctrl);
    }
}
