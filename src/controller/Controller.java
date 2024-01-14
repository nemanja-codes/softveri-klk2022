/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author necam
 */
public class Controller {
    private static Controller instance;

    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    private Controller() {
    }
    
    
}
