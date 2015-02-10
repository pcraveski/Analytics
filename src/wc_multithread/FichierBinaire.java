/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wc_multithread;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.concurrent.Executors.callable;

/**
 *
 * @author Pierre
 */
class FichierBinaire implements Callable<ArrayList<Requete>>{
    final String chemin;
    final DataInputStream fichier;

    FichierBinaire(String filename) throws FileNotFoundException {
        System.out.println(filename);
        this.chemin=filename;
        FileInputStream is= new FileInputStream (filename);
        DataInputStream dis = new DataInputStream(is);
        this.fichier=dis;
    }
    
    public Requete decrypte() throws FileNotFoundException, IOException, SQLException{
    ArrayList<Requete> Final = new ArrayList<>();
    Requete temp = new Requete();
    DataInputStream dis=this.fichier;
    temp.vide();
    temp.timestamp = dis.readInt();//Date
    //System.out.println(temp.timestamp);
    temp.clientID = dis.readInt(); //Affichage de clientID //
    temp.objectID = dis.readInt(); // Affichage de objectID
    temp.size = dis.readInt(); // Affichage de size
 
                    /* Method */
   int j=dis.read(); // lecture de la méthode
   String Sj=Integer.toBinaryString(j);
   while (Sj.length()<8){Sj="0"+Sj;}
   temp.method = Integer.parseInt(Sj,2);
   
                    /* Status */
   int k = dis.read(); // lecture du status
   String S=Integer.toBinaryString(k);
   while (S.length()<8){S="0"+S;}
   String S1=S.substring(0,2);
   String S2=S.substring(2,8);
   //System.out.println(S1+" "+S2); // pour vérifier la taille des sous chinaes de caractères
   temp.status_httpversion = Integer.parseInt(S1,2);  // Affichage du http version indicated
   temp.status_response = Integer.parseInt(S2,2);   // Affichage du status code
   
   /* QUESTION : pourquoi protocole que 1 et 2 et jamais 0 ou 3
                 très peu de status code différent alors qu'il en existe 36*/
   
                        /* File Type */ 
   
   
   int l = dis.read();
   
   String Sl=Integer.toBinaryString(l);
   while (Sl.length()<8){Sl="0"+Sl;}
   //System.out.println(Sl);
      temp.type = Integer.parseInt(Sl,2);
   
   
                    /* EMPLACEMENT SERVEUR */
   

   int m= dis.read();
   
   String T=Integer.toBinaryString(m);
   while (T.length()<8){T="0"+T;} // on fait ceci au cas ou le byte commence par des 0, pour qu'il les prenne en compte
   //System.out.println(T);
   String T1=T.substring(0,T.length()-5);
   String T2=T.substring(T.length()-5,T.length());
   temp.server_region= Integer.parseInt(T1,2); //Emplacement du serveur
   temp.server_number = Integer.parseInt(T2,2); //Numéro du serveur

        return temp;
    

    }

    @Override
    public ArrayList<Requete> call() throws Exception {
        ArrayList<Requete> Final = new ArrayList<Requete>();
        while (this.fichier.available()!=0){
                Requete al1=this.decrypte();
                Final.add(al1);
        
    
    }
        return Final;
    }


}
