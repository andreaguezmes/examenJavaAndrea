package com.colega.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import javax.sql.DataSource;

import com.colega.modelo.entidades.Colega;

public class MySqlColegaDao implements ColegaDao {

	private static final String INSERT_COLEGA = "INSERT INTO colegajdbc.colega (id, nombre, ciudad, fecha) VALUES (?,?,?,?) ";
	private static final String DELETE_COLEGA = "DELETE FROM colegajdbc.colega WHERE id=? ";
	private static final String UPDATE_COLEGA = "UPDATE colegajdbc.colega SET nombre=?, ciudad=?, fecha=? WHERE id=? ";	
	private static final String SELECT_COLEGA_BY_ID = "SELECT * FROM colegajdbc.colega WHERE id=? ";
	private static final String SELECT_COLEGA = "SELECT * FROM colegajdbc.colega ";

	private DataSource ds;
	
	public MySqlColegaDao(DataSource ds) {
		super();
		this.ds = ds;
	}

	@Override
	public void insertarNuevoColega(Colega colega) throws SQLException {
		 //1.- Crear la conexion
		Connection connection = null;
		
		try {	
			connection = ds.getConnection();
			//2.- Obtener el statement
			PreparedStatement ps = connection.prepareStatement(INSERT_COLEGA);
			ps.setInt(1, colega.getId());
			ps.setString(2, colega.getNombre());
			ps.setString(3, colega.getCiudad());
			ps.setDate(4, new java.sql.Date(colega.getFecha().getTime()));
			
			//3.- Ejecutar la sentencia ps.executeUpdate();
			ps.executeUpdate();
			
			//4.-Procesado de los resultados
						
		} finally {
			//5.- Cerrar la conexion
			if (connection!=null){
				connection.close();
				}
		}

	}

	@Override
	public void borrarColegaPorId(int id) throws SQLException {
		 //1.- Crear la conexion
		Connection connection = null;
		try {
			connection = ds.getConnection();
			// 2.- Obtener el statement			
			PreparedStatement ps = connection.prepareStatement(DELETE_COLEGA);
			ps.setInt(1, id);
			// 3.- Ejecutar la sentencia
			ps.executeUpdate();
			
		}finally {
			// 5.- Cerrar la conexion
			if (connection!=null){
				connection.close();
			}
		}
	}
	
	@Override
	public void modificarColega(Colega colega) throws SQLException {
		 //1.- Crear la conexion
		Connection connection = null;
		
		try {		
			connection = ds.getConnection();
			// 2.- Obtener el statement
			PreparedStatement ps = connection.prepareStatement(UPDATE_COLEGA);
			ps.setInt(1, colega.getId());			
			ps.setString(2, colega.getNombre());
			ps.setString(3, colega.getCiudad());
			ps.setDate(4, new java.sql.Date(colega.getFecha().getTime()));
			
			// 3.- Ejecutar la sentencia
			ps.executeUpdate();
			
		} finally {
			// 5.- Cerrar la conexion
			if (connection!=null){
				connection.close();
				}
		}

	}
	@Override
	public Colega verColegaPorId(int id) throws SQLException {
		 //1.- Crear la conexion
		Connection connection= null;
		
		try {
			connection = ds.getConnection();
			// 2.- Obtener el statement
			PreparedStatement ps = connection.prepareStatement(SELECT_COLEGA_BY_ID);
			ps.setInt(1, id);
			// 3.- Ejecutar la sentencia
			ResultSet rs = ps.executeQuery();
			
			if (rs.first()){
			//4.- Procesado de los resultados
				Colega colega = new Colega(
						id, 
						rs.getString("nombre"), 
						rs.getString("ciudad"), 
						rs.getDate("fecha"));
						
				return colega;
			}else{
			return null;
			}
			
		} finally {
			// 5.- Cerrar la conexion
			if (connection!=null){
				connection.close();
				}
			}
		}
	
	@Override
	public Collection<Colega> verTodosLosColegas() throws SQLException {
		 //1.- Crear la conexion
		Connection connection= null;
		
		Collection<Colega> todosLosColegas = new HashSet<>();
		
		try {
			connection=ds.getConnection();
			
			// 2.- Obtener el statement
			PreparedStatement ps= connection.prepareStatement(SELECT_COLEGA);
			
			// 3.- Ejecutar la sentencia
			ResultSet rs = ps.executeQuery();
			if (rs.first()){
				//4.- Procesado de los resultados
				do{
					Colega colega = new Colega(
							rs.getInt("id"), 
							rs.getString("nombre"), 
							rs.getString("ciudad"), 	
							rs.getDate("fecha"));
																	
							todosLosColegas.add(colega);
			}
				while (rs.next());
			}
				return todosLosColegas;	
						
		} finally {
			// 5.- Cerrar la conexion
			if (connection!=null){
				connection.close();
				}
		}
	}
}
