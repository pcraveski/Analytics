/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wc_multithread;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 *
 * @author Pierre
 */
class Misenforme implements Callable<String> {
    public ArrayList<Requete> fich;
    public int times;
    public Misenforme(ArrayList<Requete> somme, ConnectBDD MS, int rang) {
        this.fich=somme;
        this.times=rang;
    }
    public String test(){
                int k=0;
        int nbr_conn0=0;
        int nbr_conn1=0;
        int nbr_conn2=0;
        int nbr_conn3=0;
        int size=0;
        int size1=0;
        int size2=0;
        int size3=0;
        ArrayList servernumb=new ArrayList();
        ArrayList servernumb1=new ArrayList();
        ArrayList servernumb2=new ArrayList();
        ArrayList servernumb3=new ArrayList();
        while (this.fich.get(k).timestamp!=this.times & k<this.fich.size()-1){k++; }
        while (this.fich.get(k).timestamp==this.times& k<this.fich.size()-1){           //Il faut vérifier le nbre de srveru car il est égal au nobre de connex
            //System.out.println("je suis au bon timestamp");
           
            if  (this.fich.get(k).server_region==0){ //Pour la région 0
               if (servernumb.indexOf(this.fich.get(k).server_number)==-1){servernumb.add(this.fich.get(k).server_number);}
               nbr_conn0++;
               size=size+this.fich.get(k).size;
               
           }
            
            if  (this.fich.get(k).server_region==1){ //Pour la région 1
               if (servernumb.indexOf(this.fich.get(k).server_number)==-1){servernumb1.add(this.fich.get(k).server_number);}
               nbr_conn1++;
               size1=size1+this.fich.get(k).size;
               
           }
            
            if  (this.fich.get(k).server_region==2){ //Pour la région 2
               if (servernumb.indexOf(this.fich.get(k).server_number)==-1){servernumb2.add(this.fich.get(k).server_number);}
               nbr_conn2++;
               size2=size2+this.fich.get(k).size;
               
           }
            
            if  (this.fich.get(k).server_region==3){ //Pour la région 3
               if (servernumb.indexOf(this.fich.get(k).server_number)==-1){servernumb3.add(this.fich.get(k).server_number);}
               nbr_conn3++;
               //System.out.println(nbr_conn3);

               size3=size3+this.fich.get(k).size;
               
           }
           k++;
           
           
        }   
int t[][]={{nbr_conn0,size,servernumb.size()},{nbr_conn1,size1,servernumb1.size()},{nbr_conn2,size2,servernumb2.size()},{nbr_conn3,size3,servernumb3.size()}};
         //System.out.println("INSERT INTO `Pouf`(`timestamp`, `nbr_conn0`, `taille_donnee0`, `nbre_serveur0`, `nbr_conn1`, `taille_donnee1`, `nbre_serveur1`, `nbr_conn2`, `taille_donnee2`, `nbre_serveur2`, `nbr_conn3`, `taille_donnee3`, `nbre_serveur3`) VALUES ("+this.times+","+t[0][0]+","+t[0][1]+","+t[0][2]+","+t[1][0]+","+t[1][1]+","+t[1][2]+","+t[2][0]+","+t[2][1]+","+t[2][2]+","+t[3][0]+","+t[3][1]+","+t[3][2]+")");
        
        return "INSERT INTO `Pouf`(`timestamp`, `nbr_conn0`, `taille_donnee0`, `nbre_serveur0`, `nbr_conn1`, `taille_donnee1`, `nbre_serveur1`, `nbr_conn2`, `taille_donnee2`, `nbre_serveur2`, `nbr_conn3`, `taille_donnee3`, `nbre_serveur3`) VALUES ("+this.times+","+t[0][0]+","+t[0][1]+","+t[0][2]+","+t[1][0]+","+t[1][1]+","+t[1][2]+","+t[2][0]+","+t[2][1]+","+t[2][2]+","+t[3][0]+","+t[3][1]+","+t[3][2]+")";

    
    }
    @Override
    public String call() throws Exception {
        int k=0;
        int nbr_conn0=0;
        int nbr_conn1=0;
        int nbr_conn2=0;
        int nbr_conn3=0;
        int size=0;
        int size1=0;
        int size2=0;
        int size3=0;
        ArrayList servernumb=new ArrayList();
        ArrayList servernumb1=new ArrayList();
        ArrayList servernumb2=new ArrayList();
        ArrayList servernumb3=new ArrayList();
        //System.out.println(this.times);
        while (this.fich.get(k).timestamp!=this.times & k<this.fich.size()-1){k++; }
        //System.out.println(k);
        while (this.fich.get(k).timestamp==this.times& k<this.fich.size()-1){           //Il faut vérifier le nbre de srveru car il est égal au nobre de connex
            //System.out.println("je suis au bon timestamp");
           
            if  (this.fich.get(k).server_region==0){ //Pour la région 0
               if (servernumb.indexOf(this.fich.get(k).server_number)==-1){servernumb.add(this.fich.get(k).server_number);}
               nbr_conn0++;
               size=size+this.fich.get(k).size;
               
           }
            
            else if  (this.fich.get(k).server_region==1){ //Pour la région 1
               if (servernumb.indexOf(this.fich.get(k).server_number)==-1){servernumb1.add(this.fich.get(k).server_number);}
               nbr_conn1++;
               size1=size1+this.fich.get(k).size;
               
           }
            
            if  (this.fich.get(k).server_region==2){ //Pour la région 2
               if (servernumb.indexOf(this.fich.get(k).server_number)==-1){servernumb2.add(this.fich.get(k).server_number);}
               nbr_conn2++;
               size2=size2+this.fich.get(k).size;
               
           }
            
            if  (this.fich.get(k).server_region==3){ //Pour la région 0
               if (servernumb.indexOf(this.fich.get(k).server_number)==-1){servernumb3.add(this.fich.get(k).server_number);}
               nbr_conn3++;
               //System.out.println(nbr_conn3);
               size3=size3+this.fich.get(k).size;
               
           }
           k++;
           
           
        }   
        int t[][]={{nbr_conn0,size,servernumb.size()},{nbr_conn1,size1,servernumb1.size()},{nbr_conn2,size2,servernumb2.size()},{nbr_conn3,size3,servernumb3.size()}};
        return "INSERT INTO `Pouf`(`timestamp`, `nbr_conn0`, `taille_donnee0`, `nbre_serveur0`, `nbr_conn1`, `taille_donnee1`, `nbre_serveur1`, `nbr_conn2`, `taille_donnee2`, `nbre_serveur2`, `nbr_conn3`, `taille_donnee3`, `nbre_serveur3`) VALUES ("+this.times+","+t[0][0]+","+t[0][1]+","+t[0][2]+","+t[1][0]+","+t[1][1]+","+t[1][2]+","+t[2][0]+","+t[2][1]+","+t[2][2]+","+t[3][0]+","+t[3][1]+","+t[3][2]+")";

    }
    
}
