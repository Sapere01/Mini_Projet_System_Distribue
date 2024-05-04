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

import java.rmi.Naming;
import java.util.Map;
import java.util.Scanner;

public class VoteClient {
    private static final String SERVEUR_URL = "rmi://localhost/VoteServeur";

    public static void main(String[] args) {
        try {
            Vote voteService = (Vote) Naming.lookup(SERVEUR_URL);
            Scanner scanner = new Scanner(System.in);

            // Authentification de l'électeur
            System.out.print("Nom d'utilisateur : ");
            String username = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String password = scanner.nextLine();

            // Authentification
            if (voteService.login(username, password)) {
                System.out.println("Authentification réussie. Voici les candidats :");
                // Afficher les candidats disponibles
                Map<String, String> candidats = voteService.getCandidats();
                for (Map.Entry<String, String> candidat : candidats.entrySet()) {
                    System.out.println(candidat.getKey() + ": " + candidat.getValue());
                }

                // Voter
                System.out.print("Entrez l'ID du candidat pour lequel vous votez : ");
                String candidatID = scanner.nextLine();
                voteService.voter(username, candidatID, password);

                System.out.println("Votre vote a été enregistré avec succès.");

            } else {
                System.out.println("Authentification échouée. Veuillez réessayer.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
