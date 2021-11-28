package com.example;

import java.io.*;
import java.net.*;

public class Server{
    
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;//stringa ricevuta dal client
    String stringaModificata = null;
    BufferedReader inDalClient;//stream di input
    DataOutputStream outVersoClient;//Stream di output

    public Socket attendi(){

        try {

            System.out.println("Il server è partito in esecuzione");
            server = new ServerSocket(5000); //creo un server sulla porta 5000
            client = server.accept(); //rimane in attesa di un client
            server.close();//chiudo il server

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){

        try {
            
            System.out.println("Benvenuto, scrivi una frase e la trasformo in maiuscolo");//rimango in attesa della stringa trasmessa dal client
            stringaRicevuta = inDalClient.readLine();
            System.out.println("Stringa ricevuta dal client: " + stringaRicevuta);

            stringaModificata = stringaRicevuta.toUpperCase();//modifico la stringa e la rispedisco al client
            System.out.println("Invio la stringa modificata al client");
            outVersoClient.writeBytes(stringaModificata + "\n");

            System.out.println("Fine elaborazione, ciao ciao...");//termina l'elaborazione sul server: chiudo la connessione  del client
            client.close();

        } catch (Exception e) {
            
        }

    }

    public static void main(String[] args){

        Server server = new Server();
        server.attendi();
        server.comunica();
    }
}