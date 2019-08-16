package es.eoi.jdbc.repository;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.eoi.jdbc.entity.Alumno;

public class AlumnoRepositoryImp implements AlumnoRepository {

	private List<Alumno> alumnos;

	public AlumnoRepositoryImp() {
		alumnos = new ArrayList<Alumno>();
	}

	private Connection openConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/alumnos?serverTimezone=UTC";
		String user = "root";
		String pass = "1234";

		Connection conexion = DriverManager.getConnection(url, user, pass);
		return conexion;
	}

	private Statement statConnection(Connection conexion) throws SQLException {
		Statement st = conexion.createStatement();
		return st;
	}

	private ResultSet resultConnection(ResultSet resultSet, Statement st) throws SQLException {
		ResultSet rs = resultSet;
		return rs;
	}

	private void closeConnection(Connection conexion) throws SQLException {
		conexion.close();
	}

	public Alumno findByDni(String dni) throws SQLException {
		Connection cx = openConnection();
		Statement st = statConnection(cx);

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ALUMNO ");
		sql.append("WHERE DNI=");
		sql.append(dni.toString());

		PreparedStatement pst = cx.prepareStatement(sql.toString());

		ResultSet rs = resultConnection(pst.executeQuery(), st);

		Alumno alumno = null;

		while (rs.next()) {
			alumno = new Alumno(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"),
					rs.getInt("EDAD"));
		}

		closeConnection(cx);

		return alumno;
	}

	public List<Alumno> findAll() throws SQLException {
		Connection cx = openConnection();
		Statement st = statConnection(cx);

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ALUMNO");

		PreparedStatement pst = cx.prepareStatement(sql.toString());

		ResultSet rs = resultConnection(pst.executeQuery(), st);

		Alumno alumno;

		while (rs.next()) {
			alumno = new Alumno(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"),
					rs.getInt("EDAD"));
			alumnos.add(alumno);
		}

		closeConnection(cx);

		return alumnos;
	}

	public boolean create(Alumno alumno) throws SQLException {
		try {
			Connection cx = openConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ALUMNO ");
			sql.append("(DNI, NOMBRE, APELLIDOS, EDAD) ");
			sql.append("VALUES (? ,? ,? ,?)");

			PreparedStatement pst = cx.prepareStatement(sql.toString());
			pst.setString(1, alumno.getDni());
			pst.setString(2, alumno.getNombre());
			pst.setString(3, alumno.getApellidos());
			pst.setInt(4, alumno.getEdad());

			pst.executeUpdate();

			closeConnection(cx);

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean delete(String dni) throws SQLException {
		try {
			Connection cx = openConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ALUMNO ");
			sql.append("WHERE DNI = ?");

			PreparedStatement pst = cx.prepareStatement(sql.toString());
			pst.setString(1, dni);

			pst.executeUpdate();

			closeConnection(cx);

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean update(String dni, String nombre, String apellidos) throws SQLException {
		try {
			Connection cx = openConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ALUMNO ");
			sql.append("SET DNI=?, NOMBRE=?, APELLIDOS=?");
			sql.append("WHERE DNI=?");

			PreparedStatement pst = cx.prepareStatement(sql.toString());
			pst.setString(1, dni);
			pst.setString(2, nombre);
			pst.setString(3, apellidos);
			pst.setString(4, dni);

			pst.executeUpdate();

			closeConnection(cx);

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
