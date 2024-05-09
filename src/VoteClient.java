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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
import java.util.Arrays;

 
public class VoteClient 
{ 

	public static int nvotant;
	public static int nvotes;
	public static String [][]cand;
	public static int [][]votant;
	public static void main(String[] args) 
	{ 
	        try 
		{ 
			Vote c = (Vote)Naming.lookup("rmi://localhost/VotingService"); 
                        int temp=c.initialise() ;
			if(temp==0)
    				System.out.println("Succès");
	    		else
	    			System.out.println("Echoué");	
            		System.out.println( "Bienven!!u");

	    		while(true)
			{
				System.out.println("**************************************************************************"); 
				System.out.println(" 1.Enregitrer \n 2.Voter \n 3.Liste des candidats \n 4.Decompte des votes \n 5.Sortir");
		 		System.out.println("**************************************************************************"); 
            			System.out.println("Entrez votre choix");  
	 	 		try
				{
					InputStreamReader isr = new InputStreamReader(System.in);
					BufferedReader br = new BufferedReader(isr);
					String s1 = br.readLine();
	        			int input1=Integer.parseInt(s1);
	  				switch(input1)
					{
						case 1: System.out.println("Entrer l'id de votre electeur");
						String s2 = br.readLine();
	      		        		int input2=Integer.parseInt(s2);
						int temp1=c.register(input2);
						if(temp1==0) 
							System.out.println(" Enregistré avec succès");
						else if(temp1==1) 
							System.out.println(" L'id de l'électeur existe déjà!");
						else 
							System.out.println("Erreur essayer encore");
						break;

						case 2:	System.out.println("Entrez le nom de votre candidat:");
						String s3= br.readLine();
						System.out.println("Entrez l'id de votre électeur:");
						String s4= br.readLine();
						int input3=Integer.parseInt(s4);
						int temp2=c.castvote(s3.toUpperCase(),input3);
						if(temp2==0) 
							System.out.println("Vote réussi");
						else if(temp2==1) 
							System.out.println("Candidat ajouté avec succès et voté");
						else if(temp2==2)
							System.out.println("Electeur non enregistré, vueuillez l'enrégistrer s'il vous plait");
						else if(temp2==3) 
							System.out.println("Votre vote a déjà été enregistré pour cet électeur, les votes dupliqués ne sont pas comptabilisés");
						else if(temp2==4)
							System.out.println("Erreur essayer encore");
						break;

						case 3: String [][]temp5=c.candidatelist();
							if(temp5[0][0]==null) 
							{
								System.out.println(" Aucun candidat ne correspond à la sélection effectuée ");
								break;
							}
							for (int i1=0; i1<temp5.length; i1++) 
							{
								if(temp5[i1][0]==null)
								{
									break;
								}
								for (int j1=0; j1<temp5[i1].length; j1++) 
								{
									if(temp5[i1][j1]!=null)
					   				     System.out.print(" " + temp5[i1][j1]);
									else	
										break;
								}
					
							System.out.println("");
		 					}

							break;
	
						case 4:System.out.println("Entrez le nom du candidat:");
						String s5= br.readLine();
						int temp3=c.votecount(s5.toUpperCase());
						if(temp3!=-1) 
							System.out.println("Nombre total de votes pour le candidat sélectionné:"+temp3);
						else 
							System.out.println("Candidat non présent");
						break;
	
						case 5: System.exit(0);
						default: System.out.println("Entrée invalide");
			
					}
				}
				catch(Exception ex)
				{ 
					System.out.println("Veuillez entrer une saisie valide"); 
					continue;
				}
	  
			}		      
		} 
	        catch (MalformedURLException murle) 
		{ 
			System.out.println(); 
		        System.out.println("MalformedURLException"); 
			System.out.println(murle); 
	        } 
		catch (RemoteException re) 
		{ 
			System.out.println(); 
			System.out.println("RemoteException"); 
			System.out.println(re); 
		} 
		catch (NotBoundException nbe) 
		{ 
			System.out.println(); 
			System.out.println("NotBoundException"); 
            		System.out.println(nbe); 
		} 
		catch (java.lang.ArithmeticException ae) 
		{ 
			System.out.println(); 
        		System.out.println("java.lang.ArithmeticException"); 
            		System.out.println(ae); 
        	}
 		catch(IOException ex)
		{ 
			System.out.println(ex);
		} 

	} 
} 

