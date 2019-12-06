package com.discoverme.app.service.impl;

import com.discoverme.app.domain.Comentario;
import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.repository.ComentarioRepository;
import com.discoverme.app.repository.UsuarioRepository;
import com.discoverme.app.service.ComentarioService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.discoverme.app.repository.ExperienciaRepository;

/**
 * Clase que nos da servicio para acceder al repositorio de comentarios
 *
 * @author Manuel Leyva
 */
@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    ExperienciaRepository experienciaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    /**
    * Funcion que accede al repositorio de comentarios y devuelve todos los comentarios la bd
    * @return Listado de comentarios
    * @author Manuel Leyva    
     */
    @Override
    public List<Comentario> getAllComentarios() {
        return comentarioRepository.getAllComentarios();
    }

    /**
    * Funcion que accede al repositorio de comentarios y devuelve todos los comentarios de un experiencia
    * @param experiencia Experiencia del cual queremos que devuelva los comentarios
    * @return Listado de comentarios
    * @author Manuel Leyva    
     */
    @Override
    public List<Comentario> getComentariosByIdExperiencia(Experiencia experiencia) {
        return comentarioRepository.getComentariosByIdExperiencia(experiencia);
    }

    /**
     * Funcion que devuelve un comentario por id
     * @param id
     * @return
     * @author Manuel Leyva
     */
    @Override
    public Comentario getComentarioById(Integer id) {
        return comentarioRepository.getComentarioById(id);
    }

    /**
    * Funcion que accede al repositorio de comentarios y guarda un comentario 
    * en la BD, crea el objeto comentaro a partir de los parametros recibidos
    * @param comentario comentario
    * @author Manuel Leyva    
     */
    @Override
    public void addComentario(Comentario comentario) {
        Comentario c = new Comentario();
        c.setExperiencia(experienciaRepository.getExperienciaById(comentario.getExperiencia().getId()));
        c.setComentario(comentario.getComentario());
        c.setPuntos(comentario.getPuntos());
        c.setTitulo(comentario.getTitulo());
        c.setUsuario(usuarioRepository.getUsuarioById(comentario.getUsuario().getId()));
        LocalDate fecha = LocalDate.now();
        c.setFecha(fecha);
        comentarioRepository.addComentario(c);
    }

    /**
     * Funcion que borra comentarios
     * @param comentario
     * @author Manuel Leyva
     */
    @Override
    public void deleteComentario(Comentario comentario) {
        comentarioRepository.deleteComentario(comentario);
    }

    /**
     * Funcion que actualiza comentarios
     * @param comentario
     * @author Manuel Leyva
     */
    @Override
    public void updateComentario(Comentario comentario) {
        Comentario c = comentarioRepository.getComentarioById(comentario.getId());
        c.setTitulo(comentario.getTitulo());
        c.setPuntos(comentario.getPuntos());
        c.setComentario(comentario.getComentario());
        comentarioRepository.addComentario(c);
    }

}
