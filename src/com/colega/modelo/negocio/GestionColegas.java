package com.colega.modelo.negocio;

import java.sql.SQLException;
import java.util.Collection;

import com.colega.modelo.entidades.Colega;

public interface GestionColegas {

	void insertarNuevoColega(Colega colega) throws SQLException;
	
	void borrarColegaPorId(int id) throws SQLException;
	
	void modificarColega(Colega colega) throws SQLException;
	
	Colega verColegaPorId(int id) throws SQLException;
	
	Collection<Colega> verTodosLosColegas() throws SQLException;
}
