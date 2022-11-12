/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Iñaki
 */
@Entity
@Table(name = "recibo_sueldo")
@Getter @Setter
public class Recibo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_recibo;
            
    @Column
    public float sueldo_bruto;
    
    @Column
    public float monto_antiguedad;
    
    @Column
    public float jubilacion;
    
    @Column
    public float obra_social;
    
    @Column
    public float fondo_alta_complejidad;
    
    @Column
    public Date fecha_recibo;
    
    @ManyToOne(targetEntity=Recibo.class)
    @JoinColumn(name="id_empleado")
    @JsonManagedReference
    private Empleado empleado ;
    
    public Recibo(){}
    
    public Recibo(int id_recibo, float sueldo_bruto, float monto_antiguedad, float jubilacion, float obra_social, float obra_alta_complejidad, Date fecha_recibo) {
        this.fecha_recibo = fecha_recibo;
        this.monto_antiguedad = monto_antiguedad;
        this.jubilacion = jubilacion;
        this.obra_social = obra_social;
        this.fondo_alta_complejidad = obra_alta_complejidad;
        this.fecha_recibo = fecha_recibo;
    }
}
