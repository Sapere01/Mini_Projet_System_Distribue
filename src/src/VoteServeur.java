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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class VoteServeur extends UnicastRemoteObject implements Vote {
    private Map<String, String> votes; // Correction ici
    private Map<String, String> candidats;
    private Map<String, String> motsDePasse;

    public VoteServeur() throws RemoteException {
        super();
        this.votes = new HashMap<>();
        this.candidats = new HashMap<>();
        this.motsDePasse = new HashMap<>();

        // Initialisation des candidats et des mots de passe
        candidats.put("candidat1", "Candidat 1");
        candidats.put("candidat2", "Candidat 2");
        candidats.put("candidat3", "Candidat 3");

        // Initialisation des mots de passe des électeurs
        motsDePasse.put("electeur1", "motdepasse1");
        motsDePasse.put("electeur2", "motdepasse2");
        motsDePasse.put("electeur3", "motdepasse3");
    }

    @Override
    public void voter(String electeur, String candidat, String mdp) throws RemoteException {
        if (login(electeur, mdp)) {
            // Vérifier si le candidat est valide
            if (candidats.containsKey(candidat)) {
                // Enregistrer le vote
                if (votes.containsKey(electeur)) {
                    throw new RemoteException("Vous avez déjà voté.");
                } else {
                    votes.put(electeur, candidat);
                    System.out.println("Vote de " + electeur + " pour " + candidats.get(candidat) + " enregistré.");
                }
            } else {
                throw new RemoteException("Candidat invalide.");
            }
        } else {
            throw new RemoteException("Authentification échouée.");
        }
    }

    // Les autres méthodes restent inchangées

    public static void main(String[] args) {
        try {
            VoteServeur voteServeur = new VoteServeur();

            // Enregistrement du serveur RMI
            try {
                java.rmi.registry.LocateRegistry.createRegistry(1099);
                java.rmi.Naming.rebind("VoteServeur", voteServeur);
                System.out.println("Serveur de vote enregistré.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    @Override
    public boolean login(String electeur, String mdp) throws RemoteException {
      // Vérifiez le mot de passe pour chaque électeur
       String motDePasse = motsDePasse.get(electeur);
       return motDePasse != null && motDePasse.equals(mdp);
    }


    @Override
    public Map<String, Integer> getResultats() throws RemoteException {
      // Initialisation de la map des résultats
      Map<String, Integer> resultats = new HashMap<>();
    
      // Compter les votes pour chaque candidat
      for (String candidat : candidats.keySet()) {
          int count = 0;
          for (String vote : votes.values()) {
              if (vote.equals(candidat)) {
                  count++;
              }
          }
          resultats.put(candidat, count);
      }
    
      return resultats;
    }


    @Override
    public Map<String, String> getCandidats() throws RemoteException {
      return candidats;
    }

}

