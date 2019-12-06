package com.discoverme.app.repository.impl;

import com.discoverme.app.domain.Foto;
import com.discoverme.app.repository.FotoRepository;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Clase que representa un repositorio de fotos y con ella accedemos a la BD y
 * recuperamso los datos que se necesiten
 *
 * @author leyva
 */
@Transactional
@Repository("fotoRepositoryImpl")
public class FotoRepositoryImpl implements FotoRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Foto getFotoById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Foto foto = currentSession.get(Foto.class, id);
        return foto;
    }

    @Override
    public Foto getFotoByNombre(String nombre) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Foto> theQuery = currentSession.createQuery("from Comentario where nombre = "+ nombre, Foto.class);
        Foto fotos = theQuery.getSingleResult();
        return fotos;
    }

    @Override
    public void addFoto(Foto foto) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(foto);
    }

    @Override
    public void deleteFoto(Foto foto) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.remove(foto);
    }

    @Override
    public List<Foto> getAllAvatares() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Foto> theQuery = currentSession.createQuery("from Foto", Foto.class);
        List<Foto> fotos = theQuery.getResultList();
        return fotos;
    }

}
