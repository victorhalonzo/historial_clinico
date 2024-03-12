/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexion;
import entidades.Consulta;
import entidades.Imagen;
import entidades.Paciente;
import entidades.Receta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class HCDAO {

    private Conexion conexion = new Conexion();

    public Paciente datosPaciente(int id) throws SQLException {

        Paciente p = new Paciente();
        Connection conn = conexion.conectar();

        try {
            String sql = "select nombres,apellidos,sexo,timestampdiff(year,fecha_nacimiento,now()) as edad,"
                    + "domicilio,telefono,lugar_nacimiento,estado_civil,antecedentes_pp,"
                    + "antecedentes_pf,alergias,cirugias,antecedentes_prenatales,"
                    + "antecedentes_obs,menarquia,fecha_ultima_menstruacion,"
                    + "tabaco,tabaco_observacion,alcohol,alcohol_observacion,cantidad_hijos,inspeccion_general"
                    + " from pacientes"
                    + " where id=" + id + " and estado=1";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setSexo(rs.getString("sexo").charAt(0));
                p.setDomicilio(rs.getString("domicilio"));
                p.setTelefono(rs.getString("telefono"));
                p.setLugarNacimiento(rs.getString("lugar_nacimiento"));
                p.setEstadoCivil(rs.getString("estado_civil"));
                p.setEdad(rs.getInt("edad"));
                p.setAntecedentes_pp(rs.getString("antecedentes_pp"));
                p.setAntecedentes_pf(rs.getString("antecedentes_pf"));
                p.setAlergias(rs.getString("alergias"));
                p.setCirugias(rs.getString("cirugias"));
                p.setAntecedentes_prenatales(rs.getString("antecedentes_prenatales"));
                p.setAntecedentes_obs(rs.getString("antecedentes_obs"));
                p.setMenarquia(rs.getDate("menarquia"));
                p.setFecha_ultima_menstruacion(rs.getDate("fecha_ultima_menstruacion"));
                p.setTabaco(rs.getString("tabaco"));
                p.setTabaco_observacion(rs.getString("tabaco_observacion"));
                p.setAlcohol(rs.getString("alcohol"));
                p.setAlcohol_observacion(rs.getString("alcohol_observacion"));
                p.setCantidad_hijos(rs.getInt("cantidad_hijos"));
                p.setInspeccionGeneral(rs.getString("inspeccion_general"));

            }

        } catch (Exception e) {
            System.out.println("Error listado " + e.getMessage());
        } finally {
            conn.close();
        }

        return p;
    }

    public Consulta datosUltimaConsulta(int id) throws SQLException {
        Consulta c = new Consulta();

        Connection conn = conexion.conectar();

        try {
            String sql = "select id,fecha,round(talla) as talla,peso,imc,temperatura,pulso,presion_arterial,"
                    + "descripcion,cuadro_clinico,examenes_complementarios,diagnostico,tratamiento,receta\n"
                    + "from consultas where id_paciente=" + id + " and estado=1\n"
                    + "order by fecha_registro desc limit 1;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setFecha(rs.getDate("fecha"));
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
                c.setReceta(rs.getString("receta"));

            }

        } catch (Exception e) {
            System.out.println("Error listado " + e.getMessage());
        } finally {
            conn.close();
        }

        return c;
    }

    public ArrayList<Consulta> datosOtrasConsultas(int id) throws SQLException {

        ArrayList<Consulta> consultas = new ArrayList<>();
        Connection conn = conexion.conectar();

        try {
            String sql = "select fecha,descripcion,diagnostico,tratamiento,receta,fecha_registro\n"
                    + "from consultas where id_paciente=" + id + " and estado=1\n"
                    + "order by fecha_registro desc limit 1,100;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setFecha(rs.getDate("fecha"));
                
                c.setDescripcion(rs.getString("descripcion"));
                c.setDiagnostico(rs.getString("diagnostico"));
                c.setTratamiento(rs.getString("tratamiento"));
                c.setReceta(rs.getString("receta"));
                consultas.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error en listado " + e.getMessage());
        } finally {
            conn.close();
        }

        return consultas;

    }

    public ArrayList<Consulta> datosTodasConsultas(int id) throws SQLException {

        ArrayList<Consulta> consultas = new ArrayList<>();
        Connection conn = conexion.conectar();

        try {
            String sql = "select @rownum:=@rownum-1 as n,id,date(fecha) as fecha,"
                    + "round(talla) as talla,peso,imc,temperatura,pulso,presion_arterial,"
                    + "descripcion,cuadro_clinico,examenes_complementarios,diagnostico,tratamiento,receta\n"
                    + "from (select @rownum:=4) t,consultas t1 where id_paciente="+id+" and estado=1\n"
                    + "order by fecha_registro desc";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));
                c.setFecha(rs.getDate("fecha"));
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
                c.setReceta(rs.getString("receta"));
                consultas.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error en listado " + e.getMessage());
        } finally {
            conn.close();
        }

        return consultas;

    }
    
    public ArrayList<Receta> datosReceta(int idConsulta) throws SQLException {

        ArrayList<Receta> receta = new ArrayList<>();
        Connection conn = conexion.conectar();

        try {
            String sql = "select medicamento, cantidad from recetas where id_consulta="+idConsulta+" and estado=1;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Receta r = new Receta();
                r.setNombreMedicamento(rs.getString("medicamento"));
                r.setCantidad(rs.getInt("cantidad"));
                
                receta.add(r);
            }

        } catch (Exception e) {
            System.out.println("Error en listado " + e.getMessage());
        } finally {
            conn.close();
        }

        return receta;

    }
    
    public ArrayList<Imagen> imagenesConsulta(int idConsulta) throws SQLException{
        
        ArrayList<Imagen> imagenes= new ArrayList<>();
        Connection conn = conexion.conectar();
        
        try{
            String sql="select id,nombre,archivo,extension,ruta from imagenes where id_consulta="+idConsulta+";";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Imagen img= new Imagen();
                img.setId(rs.getInt("id"));
                img.setNombre(rs.getString("nombre"));
                img.setArchivo(rs.getBytes("archivo"));
                img.setExtension(rs.getString("extension"));
                img.setRuta(rs.getString("ruta"));
                
                imagenes.add(img);
            }
            
        }catch(Exception ex){
            System.out.println("Error en listado " + ex.getMessage());
        }finally{
            conn.close();
        }
        
        return imagenes;
    }
    
      public ArrayList<Paciente> listarPacientesHC() throws SQLException {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Connection conn = conexion.conectar();

        try {
            String sql = "select t.id,nombres,apellidos,sexo,ifnull(fecha_nacimiento,'') as fecha_nacimiento,"
                    + "timestampdiff(year,fecha_nacimiento,now()) as edad,"
                    + "ifnull(domicilio,'') as domicilio,ifnull(telefono,'') as telefono,"
                    + "ifnull(lugar_nacimiento,'') as lugar_nacimiento,estado_civil,"
                    + "antecedentes_pp,antecedentes_pf,alergias,cirugias,"
                    + "antecedentes_prenatales,tabaco,tabaco_observacion,alcohol,alcohol_observacion,"
                    + "antecedentes_obs,menarquia,"
                    + "fecha_ultima_menstruacion,"
                    + "cantidad_hijos,inspeccion_general"
                    + " from pacientes t"
                    + " join consultas t1 on t.id=t1.id_paciente and t1.estado=1"
                    + " where t.estado=1 group by t.id order by apellidos;";

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
}
