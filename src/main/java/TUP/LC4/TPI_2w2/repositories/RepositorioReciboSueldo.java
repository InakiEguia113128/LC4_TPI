/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;

import TPU.LC4.TPI_2w2.dto.DTOEmpleado;
import TPU.LC4.TPI_2w2.dto.DTOListadoReciboSueldosPorLegajo;
import TPU.LC4.TPI_2w2.dto.DTOListadoReciboSueldosPorAnioMes;
import TUP.LC4.TPI_2w2.commands.PostReciboSueldo;
import TUP.LC4.TPI_2w2.models.Recibo;
import TUP.LC4.TPI_2w2.resultados.ResultadoBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ortiz
 */
@Repository
public class RepositorioReciboSueldo {

    private final String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&serverTimezone=UTC", "2022-api-tpi-iv-dev.mysql.database.azure.com", 3306, "recibossueldotpiiv");
    private Connection mySqlConn;

    public ResultadoBase existeReciboPorLegajoFecha(int legajo, String fecha) {
        ResultadoBase resultado = new ResultadoBase();
        Recibo recibo = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
            Date parsed = format.parse(fecha);
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(parsed);
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");

            PreparedStatement prst = mySqlConn.prepareStatement("select r.id_recibo, r.id_empleado, r.fecha_recibo, r.sueldo_bruto, r.antiguedad, r.jubilacion, r.obra_social, r.fondo_alta_complejidad, r.anio, r.mes from recibo_sueldo r join empleado e on r.id_empleado = e.id_empleado where e.legajo = ? and r.mes = ? and r.anio = ?");

            prst.setInt(1, legajo);
            prst.setInt(2, cal.get(Calendar.MONTH) + 1);
            prst.setInt(3, cal.get(Calendar.YEAR));
            ResultSet rs = prst.executeQuery();

            if (rs.next()) {
                recibo = new Recibo();
                recibo.setId_recibo(rs.getInt("id_recibo"));
                recibo.setId_empleado(rs.getInt("id_empleado"));
                recibo.setFecha_recibo(rs.getString("fecha_recibo"));
                recibo.setSueldo_bruto(rs.getFloat("sueldo_bruto"));
                recibo.setAntiguedad(rs.getFloat("antiguedad"));
                recibo.setJubilacion(rs.getFloat("jubilacion"));
                recibo.setObra_social(rs.getFloat("obra_social"));
                recibo.setFondo_alta_complejidad(rs.getFloat("fondo_alta_complejidad"));
                recibo.setAnio(rs.getInt("anio"));
                recibo.setMes(rs.getInt("mes"));
            }

            prst.close();
            mySqlConn.close();

            if (recibo == null) {
                resultado.setCode(200);
                resultado.setMessage(String.format("Recibo de sueldo no existente para esta fecha %s y legajo %s!.", legajo, fecha));
                resultado.setResultado(recibo);
            } else {
                resultado.setCode(400);
                resultado.setMessage(String.format("Recibo de sueldo no existente para esta fecha %s y legajo %s!.", legajo, fecha));
                resultado.setResultado(recibo);
            }
        } catch (SQLException | ParseException ex) {
            resultado.setCode(500);
            resultado.setMessage(ex.getMessage());
            resultado.setResultado(null);
        }

        return resultado;
    }

    public ResultadoBase insertReciboSueldo(PostReciboSueldo dto, DTOEmpleado emp) {
        var resultado = new ResultadoBase();

        try {
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");

            PreparedStatement prst = mySqlConn.prepareStatement("insert recibo_sueldo (sueldo_bruto, antiguedad, jubilacion, obra_social, fondo_alta_complejidad, id_empleado, fecha_recibo, mes, anio) values (?,?,?,?,?,?,?,?,?)");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
            Date parsed = format.parse(dto.getFechaRecibo());
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(parsed);

            prst.setFloat(1, emp.getSueldo_bruto());
            prst.setFloat(2, ((emp.getSueldo_bruto() * (dto.getPorcentajeIncrementoAntiguedad() / 100))));
            prst.setFloat(3, ((emp.getSueldo_bruto() * (dto.getPorcentajeDescuentoJubilacion() / 100))));
            prst.setFloat(4, ((emp.getSueldo_bruto() * (dto.getPorcentajDescuentObraSocial() / 100))));
            prst.setFloat(5, ((emp.getSueldo_bruto() * (dto.getPorcentajeDescuentFondoComplejidad() / 100))));
            prst.setInt(6, emp.getId_empleado());
            prst.setDate(7, new java.sql.Date(parsed.getTime()));
            prst.setInt(8, cal.get(Calendar.MONTH) + 1);
            prst.setInt(9, cal.get(Calendar.YEAR));
            prst.execute();

            prst.close();
            mySqlConn.close();

            resultado.setCode(200);
            resultado.setMessage("Recibo de sueldo creado exitosamente!.");
            resultado.setResultado(null);
        } catch (SQLException | ParseException ex) {
            resultado.setCode(500);
            resultado.setMessage(ex.getMessage());
            resultado.setResultado(null);
        }

        return resultado;
    }

    public ResultadoBase GetDtoListadoReciboSueldosPorLegajo(int legajo) {
        var resultado = new ResultadoBase();
        var recibos = new ArrayList<DTOListadoReciboSueldosPorLegajo>();

        try {
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");

            PreparedStatement prst = mySqlConn.prepareStatement("SELECT e.legajo as legajo_empleado, \n"
                    + "SUM((r.sueldo_bruto + r.antiguedad) - (r.jubilacion + r.obra_social + r.fondo_alta_complejidad)) as sueldo_neto, \n"
                    + "(r.fecha_recibo) as fecha_recibo, \n"
                    + "(r.mes) as mes_recibo, \n"
                    + "(r.anio) as a??o_recibo \n"
                    + "FROM recibo_sueldo r \n"
                    + "JOIN empleado e on r.id_empleado = e.id_empleado \n"
                    + "where e.legajo = ? \n"
                    + "group by legajo_empleado, fecha_recibo, mes_recibo, a??o_recibo");

            prst.setInt(1, legajo);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                recibos.add(new DTOListadoReciboSueldosPorLegajo(
                        rs.getInt("legajo_empleado"),
                        rs.getFloat("sueldo_neto"),
                        rs.getDate("fecha_recibo"),
                        rs.getInt("mes_recibo"),
                        rs.getInt("a??o_recibo")
                ));
            }

            prst.close();
            mySqlConn.close();

            resultado.setCode(200);
            resultado.setMessage(recibos.isEmpty() ? String.format("No existen recibos de sueldo para el legajo ingresado: %s", legajo) : String.format("Existen recibos de sueldo para el legajo ingresado: %s", legajo));
            resultado.setResultado(recibos);
        } catch (SQLException ex) {
            resultado.setCode(500);
            resultado.setMessage(ex.getMessage());
            resultado.setResultado(null);
        }

        return resultado;
    }

    public ResultadoBase GetDtoListadoReciboSueldosPorFecha(Date fechaRecibo) {
        var resultado = new ResultadoBase();
        var recibos = new ArrayList<DTOListadoReciboSueldosPorAnioMes>();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(fechaRecibo);

        try {
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");

            PreparedStatement prst = mySqlConn.prepareStatement("SELECT SUM(((r.sueldo_bruto + r.antiguedad) - (r.jubilacion + r.obra_social + r.fondo_alta_complejidad))) as sueldo_neto, \n"
                    + "a.descripcion as area \n"
                    + "FROM recibo_sueldo r \n"
                    + "JOIN empleado e on r.id_empleado = e.id_empleado \n"
                    + "JOIN area a on a.id_area = e.id_area \n"
                    + "where r.mes = ? and r.anio = ? \n"
                    + "group by area \n"
                    + "order by sueldo_neto");

            prst.setInt(1, cal.get(Calendar.MONTH) + 1);
            prst.setInt(2, cal.get(Calendar.YEAR));
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                recibos.add(new DTOListadoReciboSueldosPorAnioMes(
                        rs.getFloat("sueldo_neto"),
                        rs.getString("area")
                ));
            }

            prst.close();
            mySqlConn.close();

            resultado.setCode(200);
            resultado.setMessage(recibos.isEmpty() ? String.format("No existen recibos de sueldo para la fecha ingresada: %s", fechaRecibo) : String.format("Existen recibos de sueldo para la fecha ingresada: %s", fechaRecibo));
            resultado.setResultado(recibos);
        } catch (SQLException ex) {
            resultado.setCode(500);
            resultado.setMessage(ex.getMessage());
            resultado.setResultado(null);
        }

        return resultado;
    }

    public ResultadoBase GetDtoListadoReciboSueldosPorAnio(int anio, int mes) {
        var resultado = new ResultadoBase();
        var recibos = new ArrayList<DTOListadoReciboSueldosPorAnioMes>();

        try {
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");

            PreparedStatement prst = mySqlConn.prepareStatement("SELECT SUM(((r.sueldo_bruto + r.antiguedad) - (r.jubilacion + r.obra_social + r.fondo_alta_complejidad))) as sueldo_neto, \n"
                    + "a.descripcion as area \n"
                    + "FROM recibo_sueldo r \n"
                    + "JOIN empleado e on r.id_empleado = e.id_empleado \n"
                    + "JOIN area a on a.id_area = e.id_area \n"
                    + "where r.mes = ? and r.anio = ? \n"
                    + "group by area \n"
                    + "order by sueldo_neto");

            prst.setInt(1, mes);
            prst.setInt(2, anio);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                recibos.add(new DTOListadoReciboSueldosPorAnioMes(
                        rs.getFloat("sueldo_neto"),
                        rs.getString("area")
                ));
            }

            prst.close();
            mySqlConn.close();

            resultado.setCode(200);
            resultado.setMessage(recibos.isEmpty() ? String.format("No existen recibos de sueldo para el mes %s y a??o %s", mes, anio) : String.format("Existen recibos de sueldo para el mes %s y a??o %s", mes, anio));
            resultado.setResultado(recibos);
        } catch (SQLException ex) {
            resultado.setCode(500);
            resultado.setMessage(ex.getMessage());
            resultado.setResultado(null);
        }

        return resultado;
    }
}
