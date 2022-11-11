/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Iñaki
 */
@Entity
@Table(name = "recibosSueldo")
public class ReciboSueldo {
    
    @Id @Basic
    public int idLiquidacion;
    @Basic
    public int anio;
    @Basic
    public int mes;
    @Basic
    public float sueldoBruto;
    @Basic
    public float montoAntiguedad;
    @Basic
    public float deducciones;

    public ReciboSueldo(int anio, int mes, float sueldoBruto, float montoAntiguedad, float deducciones) {
        this.anio = anio;
        this.mes = mes;
        this.sueldoBruto = sueldoBruto;
        this.montoAntiguedad = montoAntiguedad;
        this.deducciones = deducciones;
    }

    public ReciboSueldo() {
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public float getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(float sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public float getMontoAntiguedad() {
        return montoAntiguedad;
    }

    public void setMontoAntiguedad(float montoAntiguedad) {
        this.montoAntiguedad = montoAntiguedad;
    }

    public float getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(float deducciones) {
        this.deducciones = deducciones;
    }

    @Override
    public String toString() {
        return "Añio " + anio + ", mes " + mes + ", sueldoBruto " + sueldoBruto + ", montoAntiguedad " + montoAntiguedad + ", deducciones " + deducciones;
    }   
}
