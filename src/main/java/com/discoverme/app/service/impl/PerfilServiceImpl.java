package com.discoverme.app.service.impl;

import com.discoverme.app.domain.Perfil;
import com.discoverme.app.repository.PerfilRepository;
import com.discoverme.app.service.PerfilService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que nos da servicio para acceder al repositorio de perfiles
 * @author Manuel Leyva
 */
@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    /**
     * Funcion que revuelve un perfil dado un id
     * @param id
     * @return
     * @author Manuel Leyva
     */
    @Override
    public Perfil getPerfilById(Integer id) {
        return perfilRepository.getPerfilById(id);
    }

    /**
     * Funcion que devuelve un perfil dado un nombre
     * @param nombre
     * @return
     * @author Manuel Leyva
     */
    @Override
    public Perfil getPerfilByNombre(String nombre) {
        return perfilRepository.getPerfilByNombre(nombre);
    }

    /**
     * Funcion que devuelve todos los perfiles
     * @return
     * @author Manuel Leyva
     */
    @Override
    public List<Perfil> getAllPerfiles() {
        return perfilRepository.getAllPerfiles();
    }

    /**
     * Funcion que devuelve el numero equivalente al nombre del tipo de perfil
     * @param perfil
     * @return
     * @author Manuel Leyva
     */
    @Override
    public int getNumPerfilByName(String perfil) {

        if (perfil.equals("Deluxe")) {
            return 1;
        } else if (perfil.equals("Soltero")) {
            return 2;
        } else if (perfil.equals("Low Cost")) {
            return 3;
        } else if (perfil.equals("Aventurero")) {
            return 4;
        } else if (perfil.equals("Familiar")) {
            return 5;
        } else {
            return 6;
        }

    }
}
