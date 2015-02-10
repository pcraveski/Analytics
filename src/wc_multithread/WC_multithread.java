/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wc_multithread;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author Pierre
 */
public class WC_multithread {

	public static void main(String[] args) throws MalformedURLException, IOException, FileNotFoundException, SQLException, Exception {
            ConnectBDD MS = new ConnectBDD();
            FilesFinder Dossier = new FilesFinder();
            for (int bc=1;bc<Dossier.liste.size();bc=bc+1){
            Callable<ArrayList<Requete >> tache1 = new FichierBinaire(Dossier.liste.get(bc));
            List<Callable<ArrayList<Requete >>> taches = new ArrayList<Callable<ArrayList<Requete>>>();
            taches.add(tache1);
            ExecutorService executor = Executors.newFixedThreadPool(16);
            ArrayList<Requete> somme = new ArrayList<Requete>();
            resoudre(executor, taches,MS,somme);
            //System.out.println("Un des derniers est : "+somme.get(somme.size()-100).timestamp);
            //On vient de récupérer une Arraylist avec 8 fichiers chargés dedans, on veut maintenant les mettre en forme et les envoyer
            
            List<Callable<String>> envois = new ArrayList<Callable<String>>();

                int taille=somme.get(somme.size()-1).timestamp-somme.get(0).timestamp+1;
                //System.out.println("Le nombre de ligne est : "+taille);
                for (int rang=somme.get(0).timestamp;rang<somme.get(somme.size()-10).timestamp;rang=rang+10){
                   
		Callable<String > envoi1 = new Misenforme(somme,MS,rang);
                Callable<String > envoi2 = new Misenforme(somme,MS,rang+1);
                Callable<String > envoi3 = new Misenforme(somme,MS,rang+2);
                Callable<String > envoi4 = new Misenforme(somme,MS,rang+3);
                Callable<String > envoi5 = new Misenforme(somme,MS,rang+4);
                Callable<String > envoi6 = new Misenforme(somme,MS,rang+5);
                Callable<String > envoi7 = new Misenforme(somme,MS,rang+6);
                Callable<String > envoi8 = new Misenforme(somme,MS,rang+7);
                Callable<String > envoi9 = new Misenforme(somme,MS,rang+8);
                Callable<String > envoi10 = new Misenforme(somme,MS,rang+9);
		

                envois.add(envoi1);
                envois.add(envoi2);
                envois.add(envoi3);
                envois.add(envoi4);
                envois.add(envoi5);
                envois.add(envoi6);
                envois.add(envoi7);
                envois.add(envoi8);
                envois.add(envoi9);
                envois.add(envoi10);


                
                }


            
            System.out.println("Avant l'execution la tache est de taille : "+envois.size());
            ExecutorService executor2 = Executors.newFixedThreadPool(16);
            resoudre2(executor2, envois,MS);
           
            }


}
        
        public static void resoudre(final ExecutorService executor, List<Callable<ArrayList<Requete >>> taches,ConnectBDD MS,ArrayList<Requete> somme ) {

		//Le service de terminaison
		CompletionService<ArrayList<Requete >> completionService = new ExecutorCompletionService<ArrayList<Requete >>(executor);
         
        		//une liste de Future pour récupérer les résultats
		List<Future<ArrayList<Requete >>> futures = new ArrayList<Future<ArrayList<Requete >>>();
                ArrayList<Requete > res = null;
                try {
			//On soumet toutes les tâches à l'executor
                    for(Callable<ArrayList<Requete >> t : taches){
                    futures.add(completionService.submit(t));
			}
                    for(int i=0;i<taches.size();i++) {
                        try {

                	           //On récupère le premier résultat disponible
                	           //sous la forme d'un Future avec take(). Puis l'appel
                	           //à get() nous donne le résultat du Callable.
                                   res = completionService.take().get();
                                   somme.addAll(res);
                                   
                    }
                        catch(ExecutionException ignore) {}
                    }
                        
                    }
                catch(Exception e){
			e.printStackTrace();
		}
		finally {
                    System.out.println("shut");
			executor.shutdown();
		}
        }
        
        //////////
        
        
        
        public static void resoudre2(final ExecutorService executor, List<Callable<String>> taches,ConnectBDD MS) {

		//Le service de terminaison
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
         
        		//une liste de Future pour récupérer les résultats
		List<Future<String>> futures = new ArrayList<Future<String>>();
		System.out.println("Longueur de la tache : "+taches.size());
                String res = null;
                String res1 = null;
                String res2 = null;
                String res3 = null;
                String res4 = null;
                String res5 = null;
                String res6 = null;
                String res7 = null;
                String res8 = null;
                String res9= null;
                String res10 = null;
                
                
		try {
			//On soumet toutes les tâches à l'executor
			for(Callable<String> t : taches){
			     futures.add(completionService.submit(t));
			}
			
			for(int i=0;i<taches.size()/11;i++) {
                res = null;
                res1 = null;
                res2 = null;
                res3 = null;
                res4 = null;
                res5 = null;
                res6 = null;
                res7 = null;
                res8 = null;
                res9= null;
                res10 = null;

                              try {

                	           //On récupère le premier résultat disponible
                	           //sous la forme d'un Future avec take(). Puis l'appel
                	           //à get() nous donne le résultat du Callable.
                                   res = completionService.take().get();
                                   res1 = completionService.take().get();
                                   res2 = completionService.take().get();
                                   res3 = completionService.take().get();
                                   res4 = completionService.take().get();
                                   res5 = completionService.take().get();
                                   res6 = completionService.take().get();
                                   res7 = completionService.take().get();
                                   res8 = completionService.take().get();
                                   res9 = completionService.take().get();
                                   res10 = completionService.take().get();


                                   if (res != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res);

}
                                   else {res="";}

                                   if (res1 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res1);

}
                                       else {res1="";}
                                   if (res2 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res2);

}
                                       else {res2="";}
                                    if (res3 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res3);

}
                                       else {res3="";}
                                    if (res4 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res4);

}
                                       else {res4="";}
                                    if (res5 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res5);

}
                                       else {res5="";}
                                    if (res6 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res6);

 
                                   }
                                    else {res6="";}
                                    if (res7 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res7);

 
                                   }
                                    else {res7="";}
                                    if (res8 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res8);


                                   }
                                    else {res8="";}
                                    if (res9 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res9);

 
                                   }
                                    else {res9="";}
                                    if (res10 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res10);


                                   }
                                   
                              } 
                              catch(ExecutionException ignore) {}
                         }
                        System.out.println("on sort");
                        res=null;
                        
			for (int i = 0; i < taches.size()%14; ++i) {

                              try {

                	           //On récupère le premier résultat disponible
                	           //sous la forme d'un Future avec take(). Puis l'appel
                	           //à get() nous donne le résultat du Callable.
                                   res = completionService.take().get();
                                   if (res != null) {

                    	                //On affiche le resultat de la tâche

                                   }
                              } 
                              catch(ExecutionException ignore) {}
                         }              
                        
                        
                        
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
                    System.out.println("shut");
			executor.shutdown();
		}
        }
   

}