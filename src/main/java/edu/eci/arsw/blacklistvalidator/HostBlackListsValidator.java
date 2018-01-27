
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
 * @author hcadavid
 */
public class HostBlackListsValidator {

    private static final int BLACK_LIST_ALARM_COUNT=5;
    
    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     * @param ipaddress suspicious host's IP address.
     * @return  Blacklists numbers where the given host's IP address was found.
     */
    public List<Integer> checkHost(String ipaddress, int N) throws InterruptedException{
        
        LinkedList<Integer> blackListOcurrences=new LinkedList<>();
        
        
        int ocurrencesCount=0;
        
        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
            
        
        
        int numeroDeListas = skds.getRegisteredServersCount();
        
        int residuo = numeroDeListas % N;
        
        int cantidad = numeroDeListas / N;
        
        List<SearchThread> hilos = new ArrayList();
        
        int sum = 0;
        
        int sumatoria = 0;
        
        for(int i=0; i<N; i++){
            sumatoria += cantidad + residuo;
            hilos.add(new SearchThread(sum,sumatoria,skds,ipaddress, blackListOcurrences));
            hilos.get(i).start();
            residuo=0;
            sum = sumatoria+1;
        }
        
       
        
        int checkedListsCount=0;
        
        for (int i=0;i<hilos.size();i++){
            
            hilos.get(i).join();
            checkedListsCount += hilos.get(i).getChequeadas();
            ocurrencesCount += hilos.get(i).getOcurrencias();
            //blackListOcurrences.addAll(hilos.get(i).getListaEncontradas());
        }
        
        
        if (ocurrencesCount>=BLACK_LIST_ALARM_COUNT){
            skds.reportAsNotTrustworthy(ipaddress);
        }
        else{
            skds.reportAsTrustworthy(ipaddress);
        }                
        
        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});
        
        return blackListOcurrences;
    }
    
    
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());

    public static int getBLACK_LIST_ALARM_COUNT() {
        return BLACK_LIST_ALARM_COUNT;
    }
    
    
    
    
}
