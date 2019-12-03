package com.discoverme.app.service;

import com.discoverme.app.domain.Perfil;
import java.util.List;

/**
 * Interficie que nos da servicio para acceder al repositorio de perfiles
 * @author Manuel Leyva
 */
public interface PerfilService {
    Perfil getPerfilById(Integer id);
    Perfil getPerfilByNombre(String nombre);
    int getNumPerfilByName(String perfil);
    List<Perfil> getAllPerfiles();
}
