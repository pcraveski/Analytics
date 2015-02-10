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
            Dossier.findFiles("../../../../../Downloads/WC_bin");
            

            System.out.println("Fini");
            //Dossier.read();
            for (int bc=1;bc<Dossier.liste.size();bc=bc+1){
            Callable<ArrayList<Requete >> tache1 = new FichierBinaire(Dossier.liste.get(bc));
            
            /*Callable<ArrayList<Requete >> tache2 = new FichierBinaire(Dossier.liste.get(bc+1));

            Callable<ArrayList<Requete >> tache3 = new FichierBinaire(Dossier.liste.get(bc+2));
            Callable<ArrayList<Requete >> tache4 = new FichierBinaire(Dossier.liste.get(bc+3));
            Callable<ArrayList<Requete >> tache5 = new FichierBinaire(Dossier.liste.get(bc+4));
            Callable<ArrayList<Requete >> tache6 = new FichierBinaire(Dossier.liste.get(bc+5));
            Callable<ArrayList<Requete >> tache7 = new FichierBinaire(Dossier.liste.get(bc+7));
            Callable<ArrayList<Requete >> tache8 = new FichierBinaire(Dossier.liste.get(bc+8));*/
            List<Callable<ArrayList<Requete >>> taches = new ArrayList<Callable<ArrayList<Requete>>>();
            taches.add(tache1);
            /*taches.add(tache2);
            taches.add(tache3);
            taches.add(tache4);
            taches.add(tache5);
            taches.add(tache6);
            taches.add(tache7);
            taches.add(tache8);*/
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
		/*Callable<String > envoi11 = new Misenforme(somme,MS,rang+10);
                Callable<String > envoi12= new Misenforme(somme,MS,rang+11);
                Callable<String > envoi13= new Misenforme(somme,MS,rang+12);
                Callable<String > envoi14= new Misenforme(somme,MS,rang+13);
                Callable<String > envoi15= new Misenforme(somme,MS,rang+14);
                Callable<String > envoi16= new Misenforme(somme,MS,rang+15);
                Callable<String > envoi17= new Misenforme(somme,MS,rang+16);
                Callable<String > envoi18= new Misenforme(somme,MS,rang+17);
                Callable<String > envoi19= new Misenforme(somme,MS,rang+18);
                Callable<String > envoi20= new Misenforme(somme,MS,rang+19);
                Callable<String > envoi21= new Misenforme(somme,MS,rang+20);
                Callable<String > envoi22= new Misenforme(somme,MS,rang+21);
                Callable<String > envoi23= new Misenforme(somme,MS,rang+22);
                Callable<String > envoi24= new Misenforme(somme,MS,rang+23);
                Callable<String > envoi25= new Misenforme(somme,MS,rang+24);
                Callable<String > envoi26= new Misenforme(somme,MS,rang+25);
		Callable<String > envoi27 = new Misenforme(somme,MS,rang+26);
                Callable<String > envoi28 = new Misenforme(somme,MS,rang+27);
                Callable<String > envoi29 = new Misenforme(somme,MS,rang+28);
                Callable<String > envoi30 = new Misenforme(somme,MS,rang+29);
                Callable<String > envoi31 = new Misenforme(somme,MS,rang+30);
                Callable<String > envoi32 = new Misenforme(somme,MS,rang+31);
                Callable<String > envoi33 = new Misenforme(somme,MS,rang+32);
                Callable<String > envoi34 = new Misenforme(somme,MS,rang+33);
                Callable<String > envoi35 = new Misenforme(somme,MS,rang+34);
                Callable<String > envoi36 = new Misenforme(somme,MS,rang+35);
		Callable<String > envoi37 = new Misenforme(somme,MS,rang+36);
                Callable<String > envoi38= new Misenforme(somme,MS,rang+37);
                Callable<String > envoi39= new Misenforme(somme,MS,rang+38);
                Callable<String > envoi40= new Misenforme(somme,MS,rang+39);
                Callable<String > envoi41= new Misenforme(somme,MS,rang+40);
                Callable<String > envoi42= new Misenforme(somme,MS,rang+41);
                Callable<String > envoi43= new Misenforme(somme,MS,rang+42);
                Callable<String > envoi44= new Misenforme(somme,MS,rang+43);
                Callable<String > envoi45= new Misenforme(somme,MS,rang+44);
                Callable<String > envoi46= new Misenforme(somme,MS,rang+45);
                Callable<String > envoi47= new Misenforme(somme,MS,rang+46);
                Callable<String > envoi48= new Misenforme(somme,MS,rang+47);
                Callable<String > envoi49= new Misenforme(somme,MS,rang+48);*/

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
                /*envois.add(envoi11);
                envois.add(envoi12);
                envois.add(envoi13);
                envois.add(envoi14);
                envois.add(envoi15);
                envois.add(envoi16);
                envois.add(envoi17);
                envois.add(envoi18);
                envois.add(envoi19);
                envois.add(envoi20);
                envois.add(envoi21);
                envois.add(envoi22);
                envois.add(envoi23);
                envois.add(envoi24);
                envois.add(envoi25);
                envois.add(envoi26);
                envois.add(envoi27);
                envois.add(envoi28);
                envois.add(envoi29);
                envois.add(envoi30);
                envois.add(envoi31);
                envois.add(envoi32);
                envois.add(envoi33);
                envois.add(envoi34);
                envois.add(envoi35);
                envois.add(envoi36);
                envois.add(envoi37);
                envois.add(envoi38);
                envois.add(envoi39);
                envois.add(envoi40);
                envois.add(envoi41);
                envois.add(envoi42);
                envois.add(envoi43);
                envois.add(envoi44);
                envois.add(envoi5);
                envois.add(envoi46);
                envois.add(envoi47);
                envois.add(envoi48);
                envois.add(envoi49);*/

                
                }

                /*for (int i=1;i<taille%49;i++){
                    System.out.println(taches.size());
                    Callable<String > envoi1 = new Misenforme(somme,MS,rang+i);
                    envois.add(envoi1);}*/
            
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
                /*String res11= null;
                String res12= null;
                String res13= null;
                String res14= null;
                String res15= null;
                String res16= null;
                String res17= null;
                String res18 = null;
                String res19= null;
                String res20= null;
                String res21= null;
                String res22= null;
                String res23= null;
                String res24= null;
                String res25= null;
                String res26= null;*/
                
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
                /*res11= null;
                res13=null;
                res12=null;*/
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
                                   /*res11= completionService.take().get();
                                   res12= completionService.take().get();
                                   res13= completionService.take().get();*/
                                   /*res14= completionService.take().get();
                                   res15= completionService.take().get();
                                   res16= completionService.take().get();
                                   res17= completionService.take().get();
                                   res18= completionService.take().get();
                                   res19= completionService.take().get();
                                   res20= completionService.take().get();
                                   res21= completionService.take().get();
                                   res22= completionService.take().get();
                                   res23= completionService.take().get();
                                   res24= completionService.take().get();
                                   res25= completionService.take().get();
                                   res26= completionService.take().get();*/

                                   if (res != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res);
                                       //System.out.println(res);
}
                                   else {res="";}
                                   //System.out.println(res);
                                   if (res1 != null /*&& res2!= null && res3!=null && res4!=null && res5!=null && res6!=null*/ ) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res1);
                                       //System.out.println(res);
}
                                       else {res1="";}
                                   if (res2 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res2);
                                       //System.out.println(res);
}
                                       else {res2="";}
                                    if (res3 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res3);
                                       //System.out.println(res);
}
                                       else {res3="";}
                                    if (res4 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res4);
                                      // System.out.println(res);
}
                                       else {res4="";}
                                    if (res5 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res5);
                                       //System.out.println(res);
}
                                       else {res5="";}
                                    if (res6 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res6);
                                       //System.out.println(res);
 
                                   }
                                    else {res6="";}
                                    if (res7 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res7);
                                       //System.out.println(res);
 
                                   }
                                    else {res7="";}
                                    if (res8 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res8);
                                       //System.out.println(res);

                                   }
                                    else {res8="";}
                                    if (res9 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res9);
                                       //System.out.println(res);
 
                                   }
                                    else {res9="";}
                                    if (res10 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res10);
                                       //System.out.println(res);

                                   }
                                   /*else {res10="";}
                                    if (res11 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res11);
                                       System.out.println(res);

                                   }
                                   
                                    else {res11="";}
                                    if (res12 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res11);
                                       System.out.println(res);

                                   }
                                    
                                    else {res12="";}
                                    if (res13 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res11);
                                       System.out.println(res);

                                   }
                                    
                                    else {res13="";}

                                   /* if (res12 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res12);
                                       //System.out.println(res);
                                       if (i==70000 || i==80000 || i==90000 ){
                                           System.out.println("c'est la fin");
                                           System.out.println("taches = "+taches.size());
 
                                       }  
                                   }
                                    
                                    if (res13 != null) {

                    	                //On affiche le resultat de la tâche
                                       MS.lancerequete(res13);
                                       //System.out.println(res);
                                       if (i==70000 || i==80000 || i==90000 ){
                                           System.out.println("c'est la fin");
                                           System.out.println("taches = "+taches.size());
 
                                       }  
                                   }   
*/
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
                                       /*MS.lancerequete(res);*/

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