/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexion;
import entidades.Consulta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ConsultaDAO {

    private Conexion conexion = new Conexion();

    public int agregarConsulta(Consulta consulta) throws SQLException {

        boolean fueAgregado = false;
        int key = 0;
        Connection conn = conexion.conectar();
        try {
            String sql = "insert into consultas(id_paciente,fecha,talla,"
                    + "peso,imc,temperatura,pulso,presion_arterial,descripcion,cuadro_clinico,examenes_complementarios,"
                    + "diagnostico,tratamiento,receta)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, consulta.getIdPaciente());
            stmt.setDate(2, new Date(consulta.getFecha().getTime()));
            stmt.setDouble(3, consulta.getTalla());
            stmt.setDouble(4, consulta.getPeso());
            stmt.setDouble(5, consulta.getImc());
            stmt.setDouble(6, consulta.getTemperatura());
            stmt.setInt(7, consulta.getPulso());
            stmt.setString(8, consulta.getPresionArterial());
            stmt.setString(9, consulta.getDescripcion());
            stmt.setString(10, consulta.getCuadroClinico());
            stmt.setString(11, consulta.getExamenesComplementarios());
            stmt.setString(12, consulta.getDiagnostico());
            stmt.setString(13, consulta.getTratamiento());
            stmt.setString(14, consulta.getReceta());

            int cantidad = stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            key = keys.getInt(1);
            fueAgregado = (cantidad > 0);

        } catch (Exception e) {
            System.out.println("Error al agregar consulta " + e.getMessage());
        } finally {
            conn.close();
        }

        return key;
    }

    public boolean actualizarConsulta(Consulta consulta) throws SQLException {

        boolean fueActualizado = false;
        int key = 0;
        Connection conn = conexion.conectar();
        try {
            String sql = "update consultas set "
                    + "id_paciente=?,fecha=?,talla=?,"
                    + "peso=?,imc=?,temperatura=?,pulso=?,presion_arterial=?,descripcion=?,cuadro_clinico=?,"
                    + "examenes_complementarios=?,"
                    + "diagnostico=?,tratamiento=?"
                    + " where id=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, consulta.getIdPaciente());
            stmt.setDate(2, new Date(consulta.getFecha().getTime()));
            stmt.setDouble(3, consulta.getTalla());
            stmt.setDouble(4, consulta.getPeso());
            stmt.setDouble(5, consulta.getImc());
            stmt.setDouble(6, consulta.getTemperatura());
            stmt.setInt(7, consulta.getPulso());
            stmt.setString(8, consulta.getPresionArterial());
            stmt.setString(9, consulta.getDescripcion());
            stmt.setString(10, consulta.getCuadroClinico());
            stmt.setString(11, consulta.getExamenesComplementarios());
            stmt.setString(12, consulta.getDiagnostico());
            stmt.setString(13, consulta.getTratamiento());
            stmt.setInt(14, consulta.getId());

            int cantidad = stmt.executeUpdate();

            fueActualizado = (cantidad > 0);

        } catch (Exception e) {
            System.out.println("Error al actualizar consulta " + e.getMessage());
        } finally {
            conn.close();
        }

        return fueActualizado;
    }

    public ArrayList<Consulta> ultimasConsultas() throws SQLException {
        ArrayList<Consulta> consultas = new ArrayList<>();
        Connection conn = conexion.conectar();

        try {
            String sql = "select t.id, id_paciente,fecha,concat(nombres, ' ',apellidos) as nombres_paciente,"
                    + "talla,peso,imc,"
                    + "temperatura,pulso,presion_arterial,"
                    + "descripcion,cuadro_clinico,examenes_complementarios,"
                    + "diagnostico,tratamiento"
                    + " from consultas t join pacientes t1 on t.id_paciente=t1.id\n"
                    + "where t.estado = 1\n"
                    +  "order by t.id desc limit 10";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));
                c.setIdPaciente(rs.getInt("id_paciente"));
                c.setFecha(rs.getDate("fecha"));
                c.setNombresPaciente(rs.getString("nombres_paciente"));
                c.setTalla(rs.getDouble("talla"));
                c.setPeso(rs.getDouble("peso"));
                c.setImc(rs.getDouble("imc"));
                c.setTemperatura(rs.getDouble("temperatura"));
                c.setPulso(rs.getInt("pulso"));
                c.setPresionArterial(rs.getString("presion_arterial"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setCuadroClinico(rs.getString("cuadro_clinico"));
                c.setExamenesComplementarios(rs.getString("examenes_complementarios"));
                c.setDiagnostico(rs.getString("diagnostico"));
                c.setTratamiento(rs.getString("tratamiento"));
                
                consultas.add(c);

            }

        } catch (Exception e) {
            System.out.println("Error listado " + e.getMessage());
        } finally {
            conn.close();
        }

        return consultas;
    }

    public boolean eliminarConsulta(int idConsulta) throws SQLException{
        
        boolean fueEliminada = false;
        Connection conn = conexion.conectar();
        
        try {
            String sql="update consultas set estado=2 where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idConsulta);
            
            int cantidad = stmt.executeUpdate();

            fueEliminada = (cantidad > 0);
            
        } catch (Exception e) {
            System.out.println("Error al eliminar consulta "+e.getMessage());
        }finally {
            conn.close();
        }
        
        return fueEliminada;
        
    }
}
