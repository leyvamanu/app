package com.discoverme.app.repository.impl;

import com.discoverme.app.domain.Rol;
import com.discoverme.app.domain.Tipo;
import com.discoverme.app.repository.RolRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Clase que representa un repositorio de roles con ella accedemos a la BD y
 * recuperamos los datos que se necesiten
 *
 * @author Manuel Leyva
 */
@Transactional
@Repository("rolRepositoryImpl")
public class RolRepositoryImpl implements RolRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Rol getRolById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Rol rol = currentSession.get(Rol.class, id);
        return rol;
    }

    @Override
    public Rol getRolByNombre(String nombre) {
        Session currentSession = entityManager.unwrap(Session.class);
        Rol rol = currentSession.get(Rol.class, nombre);
        return rol;
    }

    @Override
    public List<Rol> getAllRoles() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Rol> theQuery = currentSession.createQuery("from Rol", Rol.class);
        List<Rol> roles = theQuery.getResultList();
        return roles;
    }

}
