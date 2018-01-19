/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
        
        CountThread hilo1 = new CountThread();
        CountThread hilo2 = new CountThread();
        CountThread hilo3 = new CountThread();
        
        hilo1.setLow(0);
        hilo1.setMax(99);
        
        hilo2.setLow(99);
        hilo2.setMax(199);
        
        hilo1.setLow(200);
        hilo1.setMax(299);
        
        hilo1.run();
        hilo2.run();
        hilo3.run();
        
    }
    
}
