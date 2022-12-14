/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ortiz
 */
@Getter @Setter
public class Area {
    public int id_area;
    
    public String descripcion;
    
    public Area() {}
    
    public Area(int id_area, String descripcion) {
        this.id_area = id_area;
        this.descripcion = descripcion;
    }
}
