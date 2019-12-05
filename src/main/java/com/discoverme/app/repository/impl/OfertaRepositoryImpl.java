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
    public List<Oferta> getOfertasServicios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Oferta> getOfertasByExperiencia(Experiencia experiencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Oferta getOfertaByCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Oferta getOfertaByNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Oferta getOfertaById(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Oferta oferta = currentSession.get(Oferta.class, id);
        return oferta;
    }

    @Override
    public void addOferta(Oferta oferta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOferta(Oferta oferta) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.remove(oferta);
    }
    


}
