/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]) throws InterruptedException{
        HostBlackListsValidator hblv=new HostBlackListsValidator();
        //PRUEBAS
        /*List<Integer> blackListOcurrences=hblv.checkHost("212.24.24.55", 1);
        List<Integer> blackListOcurrences=hblv.checkHost("212.24.24.55", Runtime.getRuntime().availableProcessors());
        List<Integer> blackListOcurrences=hblv.checkHost("212.24.24.55", 2*Runtime.getRuntime().availableProcessors());
        List<Integer> blackListOcurrences=hblv.checkHost("212.24.24.55", 50);
        List<Integer> blackListOcurrences=hblv.checkHost("212.24.24.55", 100);*/
        List<Integer> blackListOcurrences=hblv.checkHost("212.24.24.55", 200);
        System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        
    }
    
}
