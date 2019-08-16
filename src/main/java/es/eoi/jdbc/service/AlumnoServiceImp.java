package es.eoi.jdbc.service;

import java.sql.SQLException;
import java.util.List;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.repository.AlumnoRepositoryImp;

public class AlumnoServiceImp implements AlumnoService {

	private AlumnoRepositoryImp myRepository;

	public AlumnoServiceImp() {
		this.myRepository = new AlumnoRepositoryImp();
	}

	public Alumno findByDni(String dni) throws SQLException {
		return this.myRepository.findByDni(dni);
	}

	public List<Alumno> findAll() throws SQLException {
		return this.myRepository.findAll();
	}

	public boolean create(Alumno alumno) throws SQLException {
		return this.myRepository.create(alumno);
	}

	public boolean delete(String dni) throws SQLException {
		return this.myRepository.delete(dni);
	}

	public boolean update(String dni, String nombre, String apellidos) throws SQLException {
		return this.myRepository.update(dni, nombre, apellidos);
	}

}
