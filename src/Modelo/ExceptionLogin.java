/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Modelo;

/**
 *
 * @author ASUS
 */
public class ExceptionLogin extends Exception {
    
    /**
     * Creates a new instance of <code>ExceptionLogin</code> without detail
     * message.
     */
    public ExceptionLogin() {
    }

    /**
     * Constructs an instance of <code>ExceptionLogin</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionLogin(String msg) {
        super(msg);
    }
}
