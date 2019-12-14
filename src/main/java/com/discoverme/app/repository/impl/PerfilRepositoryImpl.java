package com.discoverme.app.repository.impl;

import com.discoverme.app.domain.Perfil;
import com.discoverme.app.repository.PerfilRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Clase que representa un repositorio de perfiles de huesped
 *
 * @author Manuel Leyva
 */
@Transactional
@Repository("perfilRepositoryImpl")
public class PerfilRepositoryImpl implements PerfilRepository {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Perfil getPerfilById(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Perfil perfil = currentSession.get(Perfil.class, id);
        return perfil;
    }

    @Override
    public Perfil getPerfilByNombre(String nombre) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Perfil> theQuery = currentSession.createQuery("from Perfil where nombre = :nombre",Perfil.class).setParameter("nombre", nombre);
        Perfil perfil = theQuery.getSingleResult();
        return perfil;
    }

    @Override
    public List<Perfil> getAllPerfiles() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Perfil> theQuery = currentSession.createQuery("from Perfil", Perfil.class);
        List<Perfil> perfil = theQuery.getResultList();
        return perfil;
    }

}
