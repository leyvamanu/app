package com.discoverme.app.repository;

import com.discoverme.app.domain.Comentario;
import com.discoverme.app.domain.Experiencia;
import java.util.List;

/**
 * Interficie que representa un repositorio de comentarios
 *
 * @author Manuel Leyva
 */
public interface ComentarioRepository {
    List<Comentario> getAllComentarios();
    List<Comentario> getComentariosByIdExperiencia(Experiencia experiencia);
    Comentario getComentarioById(Integer id);
    void addComentario(Comentario comentario);
    void deleteComentario(Comentario comentario);
    void updateComentario(Comentario comentario);
}
