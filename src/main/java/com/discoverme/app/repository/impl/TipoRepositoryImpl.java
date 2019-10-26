package com.discoverme.app.repository.impl;


import com.discoverme.app.domain.Tipo;
import com.discoverme.app.repository.TipoRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Clase que representa un repositorio de tipos/preferencias de experiencias con ella accedemos a la BD y recuperamso los datos que se necesiten
 * @author Manuel Leyva
 */
@Transactional
@Repository("tipoRepositoryImpl")
public class TipoRepositoryImpl implements TipoRepository{
    
	@Autowired
	private EntityManager entityManager;

	@Override
	public Tipo getTipoById(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Tipo tipo = currentSession.get(Tipo.class, id);
		return tipo;
	}

	@Override
	public List<Tipo> getAllTipos() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Tipo> theQuery = currentSession.createQuery("from Tipo", Tipo.class);
		List<Tipo> tipos = theQuery.getResultList();
		return tipos;
	}
}