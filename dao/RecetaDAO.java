/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexion;
import entidades.Receta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class RecetaDAO {
    
    private Conexion conexion = new Conexion();
    
    public boolean agregarItemReceta(Receta receta) throws SQLException{
        
        boolean fueAgregado = false;
        Connection conn = conexion.conectar();
        
        try {
            String sql="insert into recetas(id_consulta,medicamento,cantidad)"
                    + " values(?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, receta.getIdConsulta());
            stmt.setString(2, receta.getNombreMedicamento());
            stmt.setInt(3, receta.getCantidad());
            
            int cantidad = stmt.executeUpdate();

            fueAgregado = (cantidad > 0);
        } catch (Exception e) {
            System.out.println("Error al agregar item " + e.getMessage());
        }finally{
            conn.close();
        }
        
        return fueAgregado;
    }
    
    public boolean eliminarItemsReceta(int idConsulta) throws SQLException{
        
        boolean fueEliminado = false;
        Connection conn = conexion.conectar();
        
        try {
            String sql="delete from recetas where id_consulta=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idConsulta);
                        
            int cantidad = stmt.executeUpdate();

            fueEliminado = (cantidad > 0);
        } catch (Exception e) {
            System.out.println("Error al actualizar receta " + e.getMessage());
        }finally{
            conn.close();
        }
        
        return fueEliminado;
    }
}
