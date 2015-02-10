package wc_multithread;


import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pierre
 */
public class ConnectBDD{
        private static String dbUrl = "jdbc:mysql://localhost:8889/Essai";//"jdbc:mysql://liristwv.univ-lyon1.fr/SITN_worldcup";
    private static String dbUsername = "root";//"SITN_worldcup";
    private static String dbPassword = "root";//"toto";
    private Statement statement = null;
    private int a;
    public ConnectBDD() throws SQLException{  
            /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            this.statement = connection.createStatement();
            System.out.println("ça marche");
            }
        catch ( ClassNotFoundException e ) {
    /* Gérer les éventuelles erreurs ici. */
            System.out.println("tttt");
            }
        
    }

    public void lancerequete(String a){
            try {
                int resultat =  (this.statement).executeUpdate(a);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
    
    void get_ID(String requete) throws SQLException{
        ResultSet resultat =  (this.statement).executeQuery(requete);
        while ( resultat.next() ) {
            System.out.println(resultat.getInt("ID"));
        }
    }
    
    void get_Nom(String requete) throws SQLException{
        ResultSet resultat =  (this.statement).executeQuery(requete);
        while ( resultat.next() ) {
            System.out.println(resultat.getInt("Nom"));
        }
    }
    
    void get_Password(String requete) throws SQLException{
        ResultSet resultat =  (this.statement).executeQuery(requete);
        while ( resultat.next() ) {
            System.out.println(resultat.getInt("Password"));
        }
    }

    
    }


