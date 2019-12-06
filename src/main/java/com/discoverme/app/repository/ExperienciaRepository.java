package com.discoverme.app.repository;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Usuario;
import java.util.List;

/**
 * Interficie que representa un repositorio de experiencia
 *
 * @author Manuel Leyva
 */
public interface ExperienciaRepository {
    List<Experiencia>getAllExperiencias();
    List<Experiencia> getAllExperiencias(String orden);
    Experiencia getExperienciaById(Integer id);
    Experiencia getExperienciaByNombre(String nombre);
    List<Experiencia> getExperienciaByUsuario(Usuario usuario);
    void addExperiencia(Experiencia experiencia);
    void updateExperiencia(Experiencia experiencia);
    void deleteExperiencia(Experiencia experiencia);
}