package com.discoverme.app.service;

import com.discoverme.app.domain.Tipo;
import java.util.List;

/**
 * Interficie que nos da servicio para acceder al repositorio de tipos/preferencias
 * @author Manuel Leyva
 */
public interface TipoService {
    Tipo getTipoById(Integer id);
    List<Tipo> getAllTipos();
}
