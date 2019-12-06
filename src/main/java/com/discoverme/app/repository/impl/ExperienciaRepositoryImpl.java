package com.discoverme.app.repository.impl;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Usuario;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.discoverme.app.repository.ExperienciaRepository;

/**
 * Clase que representa un repositorio de experiencias/actividades con ella
 * accedemos a la BD y recuperamso los datos que se necesiten
 *
 * @author Manuel Leyva
 */
@Transactional
@Repository("experienciaRepositoryImpl")
public class ExperienciaRepositoryImpl implements ExperienciaRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Experiencia> getAllExperiencias() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Experiencia> theQuery = currentSession.createQuery("from Experiencia", Experiencia.class);
        List<Experiencia> experiencias = theQuery.getResultList();
        return experiencias;
    }

    @Override
    public List<Experiencia> getAllExperiencias(String orden) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Experiencia getExperienciaById(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Experiencia experiencia = currentSession.get(Experiencia.class, id);
        return experiencia;
    }

    @Override
    public Experiencia getExperienciaByNombre(String nombre) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Experiencia> getExperienciaByUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addExperiencia(Experiencia experiencia) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteExperiencia(Experiencia experiencia) {
        // TODO Auto-generated method stub

    }

}
