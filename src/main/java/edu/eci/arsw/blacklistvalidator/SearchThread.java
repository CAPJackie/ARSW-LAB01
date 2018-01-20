/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2110805
 */
public class SearchThread extends Thread{

    private int inicio;
    private int fin;
    private int ocurrencias;
    private List<Integer> listaEncontradas;
    private int chequeadas;
    private final HostBlacklistsDataSourceFacade skds;
    private final String ipAddress;

    SearchThread(int inicio, int fin, HostBlacklistsDataSourceFacade skds, String ipAddress) {
        this.inicio = inicio;
        this.fin = fin;
        this.skds = skds;
        this.ipAddress = ipAddress;
        ocurrencias = 0;
        chequeadas = 0;
        listaEncontradas = new ArrayList();
    }
    
    public void run(){
        for(int i = inicio; i <= fin ; i++){
            chequeadas++;
            
            if(skds.isInBlackListServer(i, ipAddress)){
                listaEncontradas.add(i);
                ocurrencias++;
            }
        }
    }

    public List<Integer> getListaEncontradas() {
        return listaEncontradas;
    }

    public void setListaEncontradas(List<Integer> listaEncontradas) {
        this.listaEncontradas = listaEncontradas;
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
