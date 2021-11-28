package com.example;

import java.io.*;
import java.net.*;

public class Client {
    
    String nomeServer = "localhost";//indirizzo del server
    int portaServer = 5000;//porta del server
    Socket mioSocket;
    BufferedReader tastiera;//buffer per l'input da tastiera
    String stringaUtente;              //stringa inserita dall'utente
    String stringaRicevutaDalServer;   //stringa ricevuta dal server
    DataOutputStream outVersoServer;   //Stream di output
    BufferedReader inDalServer;        //stream di input



    protected Socket connetti(){

        System.out.println("Il CLIENT è in esecuzione");

        try {
            //per input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));

            //creo un socket
            mioSocket = new Socket(nomeServer, portaServer);


            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.out.println("Host sconosciuto");
        }
        catch(Exception e){

            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }

        return mioSocket;
    }

    public void comunica(){

        

            try {
                
            System.out.println("Inserisci la stringa da trasmettere al server: ");
            stringaUtente = tastiera.readLine();
            System.out.println("Invio la stringa al server e attendo...");//spedisco la stringa al server
            outVersoServer.writeBytes(stringaUtente + "\n");
            stringaRicevutaDalServer = inDalServer.readLine();//leggo la risposta dal server
            System.out.println("Risposta dal server: " + stringaRicevutaDalServer);
            System.out.println("Il Client termina la sua elaborazione e chiude la connessione");
            mioSocket.close();//chiudo la connessione
                
            } catch (Exception e) {
                
                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione con il server");
                System.exit(1);
            }

        
        
    }

    public static void main(String[] args){

        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();
    }

}