/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wc_multithread;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Pierre
 */
class Requete {
    int timestamp;
    int clientID;
    int objectID;
    int size;
    int method;
    int status_httpversion;
    int type;
    int status_response;
    int server_region;
    int server_number;
    public Requete(){
        this.timestamp=0;
        this.clientID=0;
        this.objectID=0;
        this.size=0;
        this.method=0;
        this.status_httpversion=0;
        this.status_response=0;
        this.type=0;
        this.server_region=0;
        this.server_number=0;
    }
    
    public void affiche(){
        System.out.println("Le timestamp "+this.timestamp+" "+this.clientID+" "+this.objectID+" "+this.size+" "+" ");
    }
    
    public void vide(){
        this.timestamp=0;
        this.clientID=0;
        this.objectID=0;
        this.size=0;
        this.method=0;
        this.status_httpversion=0;
        this.status_response=0;
        this.type=0;
        this.server_region=0;
        this.server_number=0;
    }
    
    public Requete decrypte(FichierBinaire Fichier) throws IOException{
    DataInputStream dis=Fichier.fichier;
    this.vide();
    this.timestamp = dis.readInt();//Date
    //System.out.println(temp.timestamp);
    this.clientID = dis.readInt(); //Affichage de clientID //
    this.objectID = dis.readInt(); // Affichage de objectID
    this.size = dis.readInt(); // Affichage de size
 
                    /* Method */
   int j=dis.read(); // lecture de la méthode
   String Sj=Integer.toBinaryString(j);
   while (Sj.length()<8){Sj="0"+Sj;}
   this.method = Integer.parseInt(Sj,2);
   
                    /* Status */
   int k = dis.read(); // lecture du status
   String S=Integer.toBinaryString(k);
   while (S.length()<8){S="0"+S;}
   String S1=S.substring(0,2);
   String S2=S.substring(2,8);
   //System.out.println(S1+" "+S2); // pour vérifier la taille des sous chinaes de caractères
   this.status_httpversion = Integer.parseInt(S1,2);  // Affichage du http version indicated
   this.status_response = Integer.parseInt(S2,2);   // Affichage du status code
   
   /* QUESTION : pourquoi protocole que 1 et 2 et jamais 0 ou 3
                 très peu de status code différent alors qu'il en existe 36*/
   
                        /* File Type */ 
   
   
   int l = dis.read();
   
   String Sl=Integer.toBinaryString(l);
   while (Sl.length()<8){Sl="0"+Sl;}
   //System.out.println(Sl);
      this.type = Integer.parseInt(Sl,2);
   
   
                    /* EMPLACEMENT SERVEUR */
   

   int m= dis.read();
   
   String T=Integer.toBinaryString(m);
   while (T.length()<8){T="0"+T;} // on fait ceci au cas ou le byte commence par des 0, pour qu'il les prenne en compte
   //System.out.println(T);
   String T1=T.substring(0,T.length()-5);
   String T2=T.substring(T.length()-5,T.length());
   this.server_region= Integer.parseInt(T1,2); //Emplacement du serveur
   this.server_number = Integer.parseInt(T2,2); //Numéro du serveur
    return this;

    }

   
}
