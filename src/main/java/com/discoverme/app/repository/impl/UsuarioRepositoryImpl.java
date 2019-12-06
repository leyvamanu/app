package com.discoverme.app.repository.impl;

import com.discoverme.app.domain.Perfil;
import com.discoverme.app.domain.Rol;
import com.discoverme.app.domain.Usuario;
import com.discoverme.app.repository.UsuarioRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Clase que representa un repositorio de usuarios con ella accedemos a la BD y
 * recuperamos los datos que se necesiten
 *
 * @author Manuel Leyva
 */
@Transactional
@Repository("usuarioRepositoryImpl")
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Usuario> getAllUsuarios() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> theQuery = currentSession.createQuery("from Usuario", Usuario.class);
        List<Usuario> usuarios = theQuery.getResultList();
        return usuarios;
    }

    @Override
    public List<Usuario> getUsuariosByRol(Rol rol) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> theQuery = currentSession.createQuery("from Usuario where rol_id = "+rol.getId(), Usuario.class);
        List<Usuario> usuarios = theQuery.getResultList();
        return usuarios;
    }

    @Override
    public List<Usuario> getUsuariosByPerfil(Perfil perfil) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> theQuery = currentSession.createQuery("from Usuario where perfil_id = "+perfil.getId(), Usuario.class);
        List<Usuario> usuarios = theQuery.getResultList();
        return usuarios;
    }

    @Override
    public Usuario getUsuarioById(String id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Usuario usuario = currentSession.get(Usuario.class, id);
        return usuario;
    }

    @Override
    public void addUsuario(Usuario usuario) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(usuario);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.update(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.remove(usuario);
    }

}
