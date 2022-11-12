/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;

import TUP.LC4.TPI_2w2.commands.PostReciboSueldo;
import TUP.LC4.TPI_2w2.models.Empleado;
import TUP.LC4.TPI_2w2.models.Recibo;
import TUP.LC4.TPI_2w2.resultados.ResultadoBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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

            PreparedStatement prst = mySqlConn.prepareStatement("select r.id_empleado, r.fecha_recibo, r.sueldo_bruto, r.antiguedad, r.jubilacion, r.obra_social, r.fondo_alta_complejidad from recibo_sueldo r join empleado e on r.id_empleado = e.id_empleado where e.legajo = ? and r.mes = ? and r.anio = ?");

            prst.setInt(1, legajo);
            prst.setInt(2, cal.get(Calendar.MONTH));
            prst.setInt(3, cal.get(Calendar.YEAR));
            ResultSet rs = prst.executeQuery();

            if (rs.next()) {
                recibo = new Recibo();
                recibo.setId_empleado(rs.getInt("id_empleado"));
                recibo.setFecha_recibo(rs.getString("fecha_recibo"));
                recibo.setSueldo_bruto(rs.getFloat("sueldo_bruto"));
                recibo.setAntiguedad(rs.getString("antiguedad"));
                recibo.setJubilacion(rs.getFloat("jubilacion"));
                recibo.setObra_social(rs.getFloat("obra_social"));
                recibo.setFondo_alta_complejidad(rs.getFloat("fondo_alta_complejidad"));
            }

            prst.close();
            mySqlConn.close();

            if (recibo == null) {
                resultado.setCode(200);
                resultado.setMessage("Recibo de sueldo no existente para esta fecha y legajo!.");
                resultado.setResultado(recibo);
            } else {
                resultado.setCode(400);
                resultado.setMessage("Recibo de sueldo existente para esta fecha y legajo!.");
                resultado.setResultado(recibo);
            }
        } catch (SQLException | ParseException ex) {
            resultado.setCode(500);
            resultado.setMessage(ex.getMessage());
            resultado.setResultado(null);
        }

        return resultado;
    }

    public ResultadoBase insertReciboSueldo(PostReciboSueldo dto, Empleado emp) {
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
            prst.setFloat(3, ((emp.getSueldo_bruto() * (dto.getPorcentajeDescuentoJubilacion() / 100)) * -1));
            prst.setFloat(4, ((emp.getSueldo_bruto() * (dto.getPorcentajDescuentObraSocial() / 100)) * -1));
            prst.setFloat(5, ((emp.getSueldo_bruto() * (dto.getPorcentajeDescuentFondoComplejidad() / 100)) * -1));
            prst.setInt(6, emp.getId_empleado());
            prst.setDate(7, new java.sql.Date(parsed.getTime()));
            prst.setInt(8, cal.get(Calendar.MONTH));
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
}
