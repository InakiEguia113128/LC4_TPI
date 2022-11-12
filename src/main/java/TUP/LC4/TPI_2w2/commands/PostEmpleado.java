/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.commands;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Iñaki
 */
@Getter @Setter
public class PostEmpleado { 
    public int legajo;
    public String nombre;
    public String apellido;
    public  String fecha_nac;
    public String fecha_ingreso;
    public float  sueldo_bruto;
    public int id_area;
    
}
