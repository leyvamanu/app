package com.discoverme.app.service;

import com.discoverme.app.domain.Comentario;
import com.discoverme.app.domain.Experiencia;
import java.util.List;

/**
 * Interficie que nos da servicio para acceder al repositorio de comentarios
 * @author Manuel Leyva
 */
public interface ComentarioService {
    List<Comentario> getAllComentarios();
    List<Comentario> getComentariosByIdExperiencia(Experiencia experiencia);
    Comentario getComentarioById(Integer id);
    void addComentario(Comentario comentario);
    void deleteComentario(Comentario comentario);
    void updateComentario(Comentario comentario);
}
