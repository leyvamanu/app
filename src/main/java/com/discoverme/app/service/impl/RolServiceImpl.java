package com.discoverme.app.service.impl;

import com.discoverme.app.domain.Rol;
import com.discoverme.app.repository.RolRepository;
import com.discoverme.app.service.RolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que nos da servicio para acceder al repositorio de roles
 * @author leyva
 */
@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    /**
     * Funcion que devuelve el tipo de rol dado un nombre
     * @param nombre
     * @return
     * @author Manuel Leyva
     */
    @Override
    public Rol getRolById(Integer id) {
        return rolRepository.getRolById(id);
    }
    
    /**
     * Funcion que devuelve el tipo de rol dado un nombre
     * @param nombre
     * @return
     * @author Manuel Leyva
     */
    @Override
    public Rol getRolByNombre(String nombre) {
        return rolRepository.getRolByNombre(nombre);
    }

    /**
     * Funcion que devuelve un listado con todos los roles
     * @return
     * @author Manuel Leyva
     */
    @Override
    public List<Rol> getAllRoles() {
        return rolRepository.getAllRoles();
    }
}
