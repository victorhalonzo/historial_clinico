/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexion;
import entidades.Consulta;
import entidades.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class PacienteDAO {

    private Conexion conexion = new Conexion();

    public int agregarPaciente(Paciente paciente) throws SQLException {

        boolean fueAgregado = false;
        int idPaciente = 0;
        Connection conn = conexion.conectar();
        try {
            String sql = "insert into pacientes(nombres,apellidos,sexo,domicilio,telefono,"
                    + "fecha_nacimiento,lugar_nacimiento,estado_civil,antecedentes_pp,antecedentes_pf,"
                    + "alergias,cirugias,antecedentes_prenatales,antecedentes_obs,"
                    + "tabaco,tabaco_observacion,alcohol,alcohol_observacion,"
                    + "menarquia,fecha_ultima_menstruacion,cantidad_hijos,inspeccion_general)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, paciente.getNombres());
            stmt.setString(2, paciente.getApellidos());
            stmt.setString(3, String.valueOf(paciente.getSexo()));
            stmt.setString(4, paciente.getDomicilio());
            stmt.setString(5, paciente.getTelefono());
            stmt.setDate(6, new Date(paciente.getFechaNacimiento().getTime()));
            stmt.setString(7, paciente.getLugarNacimiento());
            stmt.setString(8, paciente.getEstadoCivil());
            stmt.setString(9, paciente.getAntecedentes_pp());
            stmt.setString(10, paciente.getAntecedentes_pf());
            stmt.setString(11, paciente.getAlergias());
            stmt.setString(12, paciente.getCirugias());
            stmt.setString(13, paciente.getAntecedentes_prenatales());
            stmt.setString(14, paciente.getAntecedentes_obs());
            stmt.setString(15, String.valueOf(paciente.getTabaco()));
            stmt.setString(16, paciente.getTabaco_observacion());
            stmt.setString(17, String.valueOf(paciente.getAlcohol()));
            stmt.setString(18, paciente.getAlcohol_observacion());
            if (paciente.getMenarquia() != null) {
                stmt.setDate(19, new Date(paciente.getMenarquia().getTime()));
            } else {
                stmt.setDate(19, null);
            }
            if (paciente.getFecha_ultima_menstruacion() != null) {
                stmt.setDate(20, new Date(paciente.getFecha_ultima_menstruacion().getTime()));
            } else {
                stmt.setDate(20, null);
            }
            stmt.setInt(21, paciente.getCantidad_hijos());
            stmt.setString(22, paciente.getInspeccionGeneral());

            int cantidad = stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            idPaciente = keys.getInt(1);

            fueAgregado = (cantidad > 0);

        } catch (Exception e) {
            System.out.println("Error al agregar paciente " + e.getMessage());
        } finally {
            conn.close();
        }

        return idPaciente;
    }

    public ArrayList<Paciente> listarPacientes() throws SQLException {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Connection conn = conexion.conectar();

        try {
            String sql = "select id,nombres,apellidos,sexo,ifnull(fecha_nacimiento,'') as fecha_nacimiento,"
                    + "timestampdiff(year,fecha_nacimiento,now()) as edad,"
                    + "ifnull(domicilio,'') as domicilio,ifnull(telefono,'') as telefono,"
                    + "ifnull(lugar_nacimiento,'') as lugar_nacimiento,estado_civil,"
                    + "antecedentes_pp,antecedentes_pf,alergias,cirugias,"
                    + "antecedentes_prenatales,tabaco,tabaco_observacion,alcohol,alcohol_observacion,"
                    + "antecedentes_obs,menarquia,"
                    + "fecha_ultima_menstruacion,"
                    + "cantidad_hijos,inspeccion_general"
                    + " from pacientes"
                    + " where estado=1"
                    + " order by apellidos;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setSexo(rs.getString("sexo").charAt(0));
                p.setDomicilio(rs.getString("domicilio"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                p.setTelefono(rs.getString("telefono"));
                p.setLugarNacimiento(rs.getString("lugar_nacimiento"));
                p.setEstadoCivil(rs.getString("estado_civil"));
                p.setEdad(rs.getInt("edad"));
                p.setAntecedentes_pp(rs.getString("antecedentes_pp"));
                p.setAntecedentes_pf(rs.getString("antecedentes_pf"));
                p.setAlergias(rs.getString("alergias"));
                p.setCirugias(rs.getString("cirugias"));
                p.setAntecedentes_prenatales(rs.getString("antecedentes_prenatales"));
                p.setTabaco(rs.getString("tabaco"));
                p.setTabaco_observacion(rs.getString("tabaco_observacion"));
                p.setAlcohol(rs.getString("alcohol"));
                p.setAlcohol_observacion(rs.getString("alcohol_observacion"));
                p.setAntecedentes_obs(rs.getString("antecedentes_obs"));
                p.setMenarquia(rs.getDate("menarquia"));
                p.setFecha_ultima_menstruacion(rs.getDate("fecha_ultima_menstruacion"));
                p.setCantidad_hijos(rs.getInt("cantidad_hijos"));
                p.setInspeccionGeneral(rs.getString("inspeccion_general"));
                
                pacientes.add(p);

            }

        } catch (Exception e) {
            System.out.println("Error listado " + e.getMessage());
        } finally {
            conn.close();
        }

        return pacientes;
    }
    
    public boolean actualizarPaciente(Paciente paciente) throws SQLException {

        boolean fueActualizado = false;
        Connection conn = conexion.conectar();
        try {
            String sql = "update pacientes set "
                    + "nombres=?,apellidos=?,sexo=?,domicilio=?,telefono=?,"
                    + "fecha_nacimiento=?,lugar_nacimiento=?,estado_civil=?,antecedentes_pp=?,antecedentes_pf=?,"
                    + "alergias=?,cirugias=?,antecedentes_prenatales=?,antecedentes_obs=?,"
                    + "tabaco=?,tabaco_observacion=?,alcohol=?,alcohol_observacion=?,menarquia=?,fecha_ultima_menstruacion=?,"
                    + "cantidad_hijos=?,inspeccion_general=?"
                    + " where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, paciente.getNombres());
            stmt.setString(2, paciente.getApellidos());
            stmt.setString(3, String.valueOf(paciente.getSexo()));
            stmt.setString(4, paciente.getDomicilio());
            stmt.setString(5, paciente.getTelefono());
            stmt.setDate(6, new Date(paciente.getFechaNacimiento().getTime()));
            stmt.setString(7, paciente.getLugarNacimiento());
            stmt.setString(8, paciente.getEstadoCivil());
            stmt.setString(9, paciente.getAntecedentes_pp());
            stmt.setString(10, paciente.getAntecedentes_pf());
            stmt.setString(11, paciente.getAlergias());
            stmt.setString(12, paciente.getCirugias());
            stmt.setString(13, paciente.getAntecedentes_prenatales());
            stmt.setString(14, paciente.getAntecedentes_obs());
            stmt.setString(15, String.valueOf(paciente.getTabaco()));
            stmt.setString(16, paciente.getTabaco_observacion());
            stmt.setString(17, String.valueOf(paciente.getAlcohol()));
            stmt.setString(18, paciente.getAlcohol_observacion());
            if (paciente.getMenarquia() != null) {
                stmt.setDate(19, new Date(paciente.getMenarquia().getTime()));
            } else {
                stmt.setDate(19, null);
            }
            if (paciente.getFecha_ultima_menstruacion() != null) {
                stmt.setDate(20, new Date(paciente.getFecha_ultima_menstruacion().getTime()));
            } else {
                stmt.setDate(20, null);
            }
            stmt.setInt(21, paciente.getCantidad_hijos());
            stmt.setString(22, paciente.getInspeccionGeneral());
            stmt.setInt(23, paciente.getId());

            int cantidad = stmt.executeUpdate();

            fueActualizado = (cantidad > 0);

        } catch (Exception e) {
            System.out.println("Error al agregar paciente " + e.getMessage());
        } finally {
            conn.close();
        }

        return fueActualizado;
    }
    
    public boolean eliminarPaciente(int idPaciente) throws SQLException{
        
        boolean fueEliminado = false;
        Connection conn = conexion.conectar();
        
        try {
            String sql="update pacientes set estado=2 where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPaciente);
            
            int cantidad = stmt.executeUpdate();

            fueEliminado = (cantidad > 0);
        } catch (Exception e) {
            System.out.println("Error al eliminar paciente "+e.getMessage());
        }finally {
            conn.close();
        }
        
        return fueEliminado;
        
    }

}
