


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2110805
 */
public class SearchThread extends Thread{

    private int inicio;
    private int fin;
    private int ocurrencias;
    private int chequeadas;
    private final HostBlacklistsDataSourceFacade skds;
    private final String ipAddress;
    private LinkedList<Integer> blackListOcurrences;

    SearchThread(int inicio, int fin, HostBlacklistsDataSourceFacade skds, String ipAddress, LinkedList<Integer> blackListOcurrences) {
        this.inicio = inicio;
        this.fin = fin;
        this.skds = skds;
        this.ipAddress = ipAddress;
        ocurrencias = 0;
        chequeadas = 0;
        this.blackListOcurrences = blackListOcurrences;
    }

    
    @Override
    public void run(){
        System.out.println(blackListOcurrences.size());
        for(int i = inicio; i <= fin ; i++){
            if(blackListOcurrences.size()==5){
                System.out.println("KHE "+blackListOcurrences.size());
                synchronized(this){
                    this.interrupt();
                }
            }
            else{
                chequeadas++;

                if(skds.isInBlackListServer(i, ipAddress)){
                    blackListOcurrences.add(i);
                    ocurrencias++;
                }
            }
        }
    }


    public int getChequeadas() {
        return chequeadas;
    }

    public void setChequeadas(int chequeadas) {
        this.chequeadas = chequeadas;
    }
      
    
    public int getOcurrencias(){  
        return ocurrencias;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
    
    
    
}
