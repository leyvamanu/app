package com.discoverme.app.repository.impl;

import com.discoverme.app.domain.Comentario;
import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.repository.ComentarioRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Clase que representa un repositorio de comentarios con ella accedemos a la BD y recuperamso los datos que se necesiten
 * @author Manuel Leyva
 */
@Transactional
@Repository("comentarioRepositoryImpl")
public class ComentarioRepositoryImpl implements ComentarioRepository {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Comentario> getAllComentarios() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Comentario> theQuery = currentSession.createQuery("from Comentario", Comentario.class);
        List<Comentario> comentario = theQuery.getResultList();
        return comentario;
    }

    @Override
    public List<Comentario> getComentariosByIdExperiencia(Experiencia experiencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comentario getComentarioById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addComentario(Comentario comentario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteComentario(Comentario comentario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
