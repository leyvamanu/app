package com.discoverme.app.repository;

import com.discoverme.app.domain.Tipo;
import java.util.List;

/**
 * Interficie que representa un repositorio de tipos/preferencias
 *
 * @author Manuel Leyva
 */
public interface TipoRepository {
    Tipo getTipoById(Integer id);
    List<Tipo> getAllTipos();
}
