/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author Apo
 */
import java.util.*;
import java.lang.*;

public class VoteImpl extends java.rmi.server.UnicastRemoteObject implements Vote 
{ 
	
	public VoteImpl() throws java.rmi.RemoteException 
	{ 
	        super(); 
	} 

	public int initialise() throws java.rmi.RemoteException 
	{
		try
		{
			System.out.println ("intialisation à zero");
			VoteClient cc= new VoteClient();
			cc.nvotant=0;
			cc.nvotes=0;
			cc.cand = new String[10][2];
			cc.votant=new int[100][2];
			return 0;
		}
		catch(Exception ex)
		{
		return 1;
		}
	}

	public int register(int votantid) throws java.rmi.RemoteException 
	{
		try
		{
			VoteClient cc1= new VoteClient();
			int i=0;
			System.out.println ("Enregistrement");
			while(cc1.votant[i][0]!='\0')
			{
				if(cc1.votant[i][0]==votantid)
				{
					System.out.println (" L'identifiant de l'électeur que vous essayez d'enregistrer existe déjà dans la liste des électeurs enregistrés");
					return 1;
				}
			i++;
			}

			System.out.println ("Enregistrement reussi!");
			cc1.nvotant++;
			cc1.votant[i][0]=votantid;
			cc1.votant[i][1]=0;
			return 0;
		}
		catch(Exception ex)
		{
			System.out.println (ex);
			return 2;
		}	
	}


	public int castvote(String s,int votantid) throws java.rmi.RemoteException 
	{
		try
		{
			int i=0,j=0;
			VoteClient cc2=new VoteClient();
			while(cc2.votant[i][0]!='\0')
			{
				if(cc2.votant[i][0]==votantid)
				{
					if( cc2.votant[i][1]==0)
					{
						while(cc2.cand[j][0]!=null)
						{
							if(cc2.cand[j][0].equals(s))
							{
								int t=Integer.parseInt(cc2.cand[j][1]);
								t++;
								cc2.cand[j][1]=Integer.toString(t);
								System.out.println("Le décompte des votes a augmenté de un pour le candidat sélectionné");	
								return 0;	   
							}	
							j++;        
						}
						cc2.cand[j][0]=s;
						cc2.cand[j][1]="1";
						cc2.votant[i][1]=1;
						System.out.println("Un nouveau candidat a été ajouté et le décompte des votes a été fixé à 1.");
						return 1;		 
					}
					else return 3;
				}
				i++;
			}  
			System.out.println("Identifiant de l'électeur non trouvé.");
			return 2;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return 4;
		}			
	}


	public String[][] candidatelist() throws java.rmi.RemoteException
	{
		VoteClient cc3=new VoteClient();
		System.out.println("Envoi de la liste des candidats");
		return(cc3.cand);
	}


	public int votecount(String name) throws java.rmi.RemoteException 
	{
		try
		{
			int j=0;
			VoteClient cc3=new VoteClient();
			while(cc3.votant[j][0]!='\0')
			{
				if(cc3.cand[j][0].equals(name))
				{
					System.out.println(" Décompte des votes pour le candidat demandé ");
					int t=Integer.parseInt(cc3.cand[j][1]);
					return(t);
				}
				j++;
			}
			System.out.println(" Je suis ici");
			int t1=-1;
			return(t1);
		}
		catch(Exception Ex)
		{ 
			return -1;
		}
	}

}
