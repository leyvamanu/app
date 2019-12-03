package com.discoverme.app.repository;

import com.discoverme.app.domain.Perfil;
import java.util.List;

/**
 * Interficie que representa un repositorio de perfiles de huesped
 *
 * @author Manuel Leyva
 */
public interface PerfilRepository {
    Perfil getPerfilById(Integer id);
    Perfil getPerfilByNombre(String nombre);
    List<Perfil> getAllPerfiles();
}
