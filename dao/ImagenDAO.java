/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexion;
import entidades.Imagen;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ImagenDAO {
    
    private Conexion conexion = new Conexion();
    
    public boolean agregarImagen(Imagen imagen) throws SQLException{
        
        boolean fueAgregado = false;
        Connection conn = conexion.conectar();
        
        try{
            String sql="insert into imagenes(id_consulta,nombre,archivo,extension,ruta) "
                    + "values(?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, imagen.getIdConsulta());
            stmt.setString(2, imagen.getNombre());
            stmt.setBytes(3, imagen.getArchivo());
            stmt.setString(4, imagen.getExtension());
            stmt.setString(5, imagen.getRuta());
            
            int cantidad = stmt.executeUpdate();

            fueAgregado = (cantidad > 0);
            
        }catch(Exception e){
            System.out.println("Error al agregar imagen " + e.getMessage());
        }finally{
            conn.close();
        }
        
        return fueAgregado;
    }
    
    public boolean eliminarImagenes(int idConsulta) throws SQLException{
        
        boolean fueEliminado = false;
        Connection conn = conexion.conectar();
        
        try{
            String sql="delete from imagenes where id_consulta=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idConsulta);
                        
            int cantidad = stmt.executeUpdate();

            fueEliminado = (cantidad > 0);
            
        }catch(Exception e){
            System.out.println("Error al actualizar imagenes " + e.getMessage());
        }finally{
            conn.close();
        }
        
        return fueEliminado;
    }
    
    public void abrirImagen(int id){
        Connection conn = conexion.conectar();
        PreparedStatement ps = null;
        ResultSet rs=null;
        byte[] b=null;
        String ext="";
        
        try {
            String sql="select archivo,extension from imagenes where id="+id+"";
            ps=conn.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                b= rs.getBytes(1);
                ext= rs.getString(2);
            }
            InputStream bos= new ByteArrayInputStream(b);
            
            int tamanioInput= bos.available();
            byte[] datosImagen= new byte[tamanioInput];
            bos.read(datosImagen,0,tamanioInput);
            
            OutputStream out= new FileOutputStream("new"+ext);
            out.write(datosImagen);
            
            out.close();
            bos.close();
            ps.close();
            rs.close();
            conn.close();
            
            
        } catch (IOException| NumberFormatException| SQLException e) {
            System.out.println("Error al abrir imagen "+e.getMessage());
        }
        
    }
}
