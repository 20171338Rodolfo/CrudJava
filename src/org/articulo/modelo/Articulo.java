package org.articulo.modelo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Articulo {

	static Conexion cnnConexion = new Conexion();

	public static void agrega_Articulo(String c_barras, String nombre, float p_entrada, String presentacion,
			String descripcion, String estatus, int proveedor) throws IOException, SQLException {
		try {
			cnnConexion.connectdatabaseOracle();
			String sql = "INSERT INTO ARTICULO (ID,CODIGO_BARRAS,NOMBRE,PRECIO_ENTRADA,PRESENTACION,DESCRIPCION,ESTATUS, PROVEEDOR) VALUES(SQARTICULO.NEXTVAL,?,?,?,?,?,?,?)";
			PreparedStatement ps = cnnConexion.connection.prepareStatement(sql);
			ps.setString(1, c_barras);
			ps.setString(2, nombre);
			ps.setFloat(3, p_entrada);
			ps.setString(4, presentacion);
			ps.setString(5, descripcion);
			ps.setString(6, estatus);
			ps.setInt(7, proveedor);
			ps.executeQuery();

			System.out.println("Se agrego correctamente el registro: " + c_barras + "-" + nombre);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	public static void modifica_Articulo(String codigo_barras, String nombre, float precio_entrada, String presentacion,
			String descripcion, String estatus, int id_proveedor, int id) throws IOException, SQLException {
		try {
			cnnConexion.connectdatabaseOracle();
			String sql = "UPDATE ARTICULO SET CODIGO_BARRAS = ?, NOMBRE = ?, PRECIO_ENTRADA=?, PRESENTACION=?, DESCRIPCION=?, ESTATUS=?,PROVEEDOR=? WHERE ID=?";
			PreparedStatement ps = cnnConexion.connection.prepareStatement(sql);
			ps.setString(1, codigo_barras);
			ps.setString(2, nombre);
			ps.setFloat(3, precio_entrada);
			ps.setString(4, presentacion);
			ps.setString(5, descripcion);
			ps.setString(6, estatus);
			ps.setInt(7, id_proveedor);
			ps.setInt(8, id);
			ps.executeQuery();

			System.out.println("Se ha Actualizado correctamente el registro=" + id + " " + codigo_barras + " " + nombre);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	public static void elimina_Articulo(int id) throws IOException, SQLException {
		try {
			cnnConexion.connectdatabaseOracle();
			String sql = "DELETE FROM ARTICULO WHERE ID=?";
			PreparedStatement ps = cnnConexion.connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();

			System.out.println("Se eliminó correctamente el registro=" + id);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	public static void consultaIndividualArticulo(int id) throws IOException, SQLException {
		try {
			cnnConexion.connectdatabaseOracle();
			String sql = "select * from articulo where id=?";
			PreparedStatement ps = cnnConexion.connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Registro encontrado " + rs.getInt("ID") + "-" + rs.getString("CODIGO_BARRAS") + "-"
						+ rs.getString("NOMBRE") + "-" + rs.getFloat("PRECIO_ENTRADA") + "-"
						+ rs.getFloat("PRECIO_SALIDA") + "-" + rs.getString("PRESENTACION") + "-"
						+ rs.getString("DESCRIPCION") + "-" + rs.getString("ESTATUS") + rs.getInt("PROVEEDOR"));

			} else {
				System.out.println("Registro con id: " + id + " no encontrado");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	public static List<Map<String, Object>> retorna() {
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		try {
			cnnConexion.connectdatabaseOracle();
			String sql = "select * from articulo";
			PreparedStatement ps = cnnConexion.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columncount = md.getColumnCount();

			while (rs.next()) {
				Map<String, Object> rowdata = new HashMap<String, Object>();
				for (int i = 1; i <= columncount; i++) {
					rowdata.put(md.getColumnName(i), rs.getObject(i));
				}
				// System.out.println(rowdata);
				
				lista.add(rowdata);

			}

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		// System.out.println(lista);
		return lista;
	}

	public static ArrayList<String[]> datos() {
		ArrayList<String[]> lista = null;
		try {
			cnnConexion.connectdatabaseOracle();
			lista = new ArrayList<String[]>();
			String sql = "select * from articulo";
			PreparedStatement ps = cnnConexion.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columncount = md.getColumnCount();
			String[] datos;
			while (rs.next()) {
				datos = new String[columncount];
				for (int x = 0; x < columncount; x++) {
					datos[x] = rs.getObject(x + 1).toString();

				}
				lista.add(datos);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

	public static ArrayList<Object> Lista() {
		ArrayList<Object> datos = new ArrayList<Object>();

		try {
			cnnConexion.connectdatabaseOracle();
			
			String sql = "select * from articulo";
			PreparedStatement ps = cnnConexion.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columncount = md.getColumnCount();
			while (rs.next()) {
				Object dato = new Object[columncount];
				for (int i = 1; i <= columncount; i++) {
					dato = rs.getObject(i);
				}
				datos.add(dato);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return datos;
		
	}

	public static void main(String[] args) throws IOException, SQLException {

		// agrega_Articulo("Treda","7503000269910",118.22f,"caja con 24
		// tabletas","Alivia diarrea infecciosa", "activo",1);
		// modifica_Articulo("Lomotil","7503000269910",186.30f,"caja con 12
		// tabletas","Loperamida", "activo",1,26);
		// elimina_Articulo(26);
		// consultaIndividualArticulo(26);
		// consultaGeneralArticulo();
		// connectdatabaseOracle();
		System.out.println(retorna());

	}

}
