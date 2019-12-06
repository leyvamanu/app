package com.discoverme.app.repository.impl;

import com.discoverme.app.domain.Servicio;
import com.discoverme.app.repository.ServicioRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Clase que representa el repositorio de servicios con la cual accedemos a la
 * BD y recuperamos los datos que se necesiten
 *
 * @author Jose Luis Sanchez Escoda
 */
@Transactional
@Repository("servicioRepositoryImpl")
public class ServicioRepositoryImpl implements ServicioRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Servicio> getAllServicios() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Servicio> theQuery = currentSession.createQuery("from Servicio", Servicio.class);
        List<Servicio> servicios = theQuery.getResultList();
        return servicios;
    }

    @Override
    public Servicio getServicioById(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Servicio servicio = currentSession.get(Servicio.class, id);
        return servicio;
    }

    @Override
    public Servicio getServicioByNombre(String nombre) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Servicio> theQuery = currentSession.createQuery("from Servicio where nombre = " + nombre, Servicio.class);
        Servicio servicio = theQuery.getSingleResult();
        return servicio;
    }

    @Override
    public void addServicio(Servicio servicio) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(servicio);
    }

    @Override
    public void updateServicio(Servicio servicio) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.update(servicio);
    }

    @Override
    public void deleteServicio(Servicio servicio) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.remove(servicio);
    }

}
