/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author Apo
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Vote extends Remote {
    // Méthode pour voter
    void voter(String electeur, String candidat, String mdp) throws RemoteException;

    // Méthode pour récupérer les résultats
    Map<String, Integer> getResultats() throws RemoteException;

    // Méthode pour vérifier l'authentification
    boolean login(String electeur, String mdp) throws RemoteException;

    // Méthode pour obtenir la liste des candidats
    Map<String, String> getCandidats() throws RemoteException;
}

