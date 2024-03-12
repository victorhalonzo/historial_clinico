/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Usuario
 */
public class Conexion {
    
    private Connection conexion;
    private String host="localhost";
    private String usuario="root";
    private String password="123456";
    private String nombreBD="historial_clinico";
    
    private String url= "jdbc:mysql://"+host+"/"+nombreBD;
    
    public Connection conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conexion = DriverManager.getConnection(this.url,this.usuario,this.password);
        }catch(Exception e){
            System.out.println("Error de conexión "+e.getMessage());
        }
        return this.conexion;
    }
    
}
