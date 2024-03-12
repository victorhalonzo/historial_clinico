/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Paciente {
    private int id;
    private String nombres;
    private String apellidos;
    private char sexo;
    private String domicilio;
    private String telefono;
    private Date fechaNacimiento;
    private String lugarNacimiento;
    private String estadoCivil;
    private int edad;
    private String antecedentes_pp;
    private String antecedentes_pf;
    private String alergias;
    private String cirugias;
    private String antecedentes_prenatales;
    private String antecedentes_obs;
    private String tabaco;
    private String tabaco_observacion;
    private String alcohol;
    private String alcohol_observacion;
    private Date menarquia;
    private Date fecha_ultima_menstruacion;
    private int cantidad_hijos;
    private String inspeccion_general;
    

    public Paciente() {
    }

    
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        if(nombres.length()<3 || nombres.length()>100){
            throw new IllegalArgumentException("El nombre debe tener entre 3 y 100 caracteres");
        }
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        if(apellidos.length()<3 || apellidos.length()>100){
            throw new IllegalArgumentException("El apellido debe tener entre 3 y 100 caracteres");
        }
        this.apellidos = apellidos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
 
    
    public String getInspeccionGeneral(){
        return inspeccion_general;
    }
    
    public void setInspeccionGeneral(String inspeccion_general){
        this.inspeccion_general=inspeccion_general;
    }

    public String getAntecedentes_pp() {
        return antecedentes_pp;
    }

    public void setAntecedentes_pp(String antecedentes_pp) {
        this.antecedentes_pp = antecedentes_pp;
    }

    public String getAntecedentes_pf() {
        return antecedentes_pf;
    }

    public void setAntecedentes_pf(String antecedentes_pf) {
        this.antecedentes_pf = antecedentes_pf;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCirugias() {
        return cirugias;
    }

    public void setCirugias(String cirugias) {
        this.cirugias = cirugias;
    }

    public String getAntecedentes_prenatales() {
        return antecedentes_prenatales;
    }

    public void setAntecedentes_prenatales(String antecedentes_prenatales) {
        this.antecedentes_prenatales = antecedentes_prenatales;
    }

    public String getAntecedentes_obs() {
        return antecedentes_obs;
    }

    public void setAntecedentes_obs(String antecedentes_obs) {
        this.antecedentes_obs = antecedentes_obs;
    }

    public String getTabaco() {
        return tabaco;
    }

    public void setTabaco(String tabaco) {
        this.tabaco = tabaco;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public Date getMenarquia() {
        return menarquia;
    }

    public void setMenarquia(Date menarquia) {
        this.menarquia = menarquia;
    }

    public Date getFecha_ultima_menstruacion() {
        return fecha_ultima_menstruacion;
    }

    public void setFecha_ultima_menstruacion(Date fecha_ultima_menstruacion) {
        this.fecha_ultima_menstruacion = fecha_ultima_menstruacion;
    }

    public int getCantidad_hijos() {
        return cantidad_hijos;
    }

    public void setCantidad_hijos(int cantidad_hijos) {
        this.cantidad_hijos = cantidad_hijos;
    }

    public String getTabaco_observacion() {
        return tabaco_observacion;
    }

    public void setTabaco_observacion(String tabaco_observacion) {
        this.tabaco_observacion = tabaco_observacion;
    }

    public String getAlcohol_observacion() {
        return alcohol_observacion;
    }

    public void setAlcohol_observacion(String alcohol_observacion) {
        this.alcohol_observacion = alcohol_observacion;
    }
    
    

    @Override
    public String toString() {
        return "Paciente{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", sexo=" + sexo + ", domicilio=" + domicilio + ", lugarNacimiento=" + lugarNacimiento + ", estadoCivil=" + estadoCivil + '}';
    }
    
    
}
