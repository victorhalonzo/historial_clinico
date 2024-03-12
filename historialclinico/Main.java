/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package historialclinico;

import entidades.Paciente;
import dao.PacienteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.JFrame;
import vistas.Menu;
import vistas.Menu2;
/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args){
        
        Menu menu= new Menu();
        menu.setVisible(true);
        
        //menu.setLocationRelativeTo(null);
        
        //PacienteDAO pDAO= new PacienteDAO();
//        Paciente p= new Paciente("Cristian","Alonzo",'M',"Canada","Portoviejo","Casado");
//        
//        PacienteDAO pDAO= new PacienteDAO();
//        
//        try {
//            if(pDAO.agregarPaciente(p)){
//                System.out.println("agregado satisfactoriamente");
//            }else{
//                System.out.println("error");
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            ArrayList<Paciente> pacientes= pDAO.listarPacientes();
//            for(Paciente paciente:pacientes){
//                System.out.println(paciente);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    }
    
}

