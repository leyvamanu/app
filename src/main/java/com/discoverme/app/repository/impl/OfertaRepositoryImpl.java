package com.discoverme.app.repository.impl;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Oferta;
import com.discoverme.app.repository.OfertaRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Clase que representa un repositorio de ofertas y con ella accedemos a la BD y recuperamso los datos que se necesiten
 * @author Manuel Leyva
 */
@Transactional
@Repository("ofertaRepositoryImpl")
public class OfertaRepositoryImpl implements OfertaRepository{
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Oferta> getAllOfertas() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Oferta> theQuery = currentSession.createQuery("from Oferta", Oferta.class);
        List<Oferta> ofertas = theQuery.getResultList();
        return ofertas;
    }

    @Override
    public List<Oferta> getOfertasByExperiencia(Experiencia experiencia) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Oferta> theQuery = currentSession.createQuery("from Oferta where experiencia_id = :experiencia",Oferta.class).setParameter("experiencia",  experiencia.getId());
        List<Oferta> ofertas = theQuery.getResultList();
        return ofertas;
    }

    @Override
    public Oferta getOfertaByCodigo(String codigo) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Oferta> theQuery = currentSession.createQuery("from Oferta where codigo = :codigo",Oferta.class).setParameter("codigo", codigo);
        Oferta oferta = theQuery.getSingleResult();
        return oferta;
    }

    @Override
    public Oferta getOfertaByNombre(String nombre) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Oferta> theQuery = currentSession.createQuery("from Oferta where nombre = :nombre",Oferta.class).setParameter("nombre", nombre);
        Oferta oferta = theQuery.getSingleResult();
        return oferta;
    }

    @Override
    public Oferta getOfertaById(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Oferta oferta = currentSession.get(Oferta.class, id);
        return oferta;
    }

    @Override
    public void addOferta(Oferta oferta) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(oferta);
    }

    @Override
    public void deleteOferta(Oferta oferta) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.remove(oferta);
    }

}
