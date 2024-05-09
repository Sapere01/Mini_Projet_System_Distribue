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



public interface Vote extends java.rmi.Remote {
    
    public int initialise() throws java.rmi.RemoteException;

	public int register(int votantid) throws java.rmi.RemoteException; 

	public int castvote(String name,int votantid) throws java.rmi.RemoteException;

	public String[][] candidatelist() throws java.rmi.RemoteException;	

	public int votecount(String name) throws java.rmi.RemoteException;

}




