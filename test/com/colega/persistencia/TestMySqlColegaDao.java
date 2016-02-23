package com.colega.persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import com.colega.modelo.entidades.Colega;
import com.colega.persistencia.MySqlColegaDao;

public class TestMySqlColegaDao {
	@Test
	public void testVerColegaPorId() {
		// He dado de alta en la base de datos a mano el usuario 2 de nombre "Ana" y ciudad "Madrid"
		BasicDataSource ds=new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/colegajdbc"); //cadena de conexion
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		
		MySqlColegaDao sut = new MySqlColegaDao(ds);
		
		try {
			Colega colegaQueExiste = sut.verColegaPorId(2);
			
			//Se que este assert tiene que fallar
			//assertNull(colegaQueExiste);
			
			//Se que estos assert no deben fallar
			assertNotNull(colegaQueExiste);
			assertEquals(colegaQueExiste.getId(), 2);
			assertEquals(colegaQueExiste.getNombre(), "Ana");
			assertEquals(colegaQueExiste.getCiudad(), "Madrid");
			
			// Podemos hacer mas completa la prueba
			
			Colega colegaQueNoExiste = sut.verColegaPorId(9876543);
			assertNull(colegaQueNoExiste);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testInsertarNuevoColega() {
		
		//Datos de Entrada
		
		
		Colega colega=new Colega(1, "Andrea", "Santander",new Date());
		
		
		//Datos esperados de Salida
		BasicDataSource ds=new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/colegajdbc"); //cadena de conexion
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		
		//Sut
		
		MySqlColegaDao sut = new MySqlColegaDao(ds);
		
		//Ejecucion
		
		try {
			sut.insertarNuevoColega(colega);
			Colega colegaObtenido = sut.verColegaPorId(colega.getId());
			assertEquals(colega, colegaObtenido);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public void testBorrarColegaPorId() {
		
		BasicDataSource ds=new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/colegajdbc"); //cadena de conexion
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		
		MySqlColegaDao sut = new MySqlColegaDao(ds);
		
		try {
			sut.borrarColegaPorId(1);
			Colega colegaObtenido = sut.verColegaPorId(1);
			
			assertNull(colegaObtenido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
