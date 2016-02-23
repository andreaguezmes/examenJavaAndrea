package com.colega.modelo.negocio;

import java.sql.SQLException;
import java.util.Collection;

import com.colega.modelo.entidades.Colega;
import com.colega.persistencia.ColegaDao;

public class ImplGestionColegas implements GestionColegas {


		private ColegaDao colegaDao;
		
		
	public ImplGestionColegas(ColegaDao colegaDao) {
			super();
			this.colegaDao = colegaDao;
		}

	@Override
	public void insertarNuevoColega(Colega colega) throws SQLException {
		colegaDao.insertarNuevoColega(colega);
		
	}

	@Override
	public void borrarColegaPorId(int id) throws SQLException {
		colegaDao.borrarColegaPorId(id);
	}

	@Override
	public void modificarColega(Colega colega) throws SQLException {
		colegaDao.modificarColega(colega);
		
	}

	@Override
	public Colega verColegaPorId(int id) throws SQLException {
		return colegaDao.verColegaPorId(id);
	}

	@Override
	public Collection<Colega> verTodosLosColegas() throws SQLException {
		return colegaDao.verTodosLosColegas();
	}

}