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

public class VoteServeur 
{

	public VoteServeur() 
	{
	     	try 
		{
	       		Vote c = new VoteImpl();
	       		Naming.rebind("rmi://localhost:1099/VotingService", c);
		} 
		catch (Exception e) 
		{
		       System.out.println("Trouble: " + e);
		}
	}

	public static void main(String args[])
	{
		new VoteServeur();
	}
}






