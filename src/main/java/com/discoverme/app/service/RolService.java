package com.discoverme.app.service;

import com.discoverme.app.domain.Rol;
import java.util.List;

/**
 * Interficie que nos da servicio para acceder al repositorio de roles
 * @author leyva
 */
public interface RolService {
	Rol getRolById(Integer id);
	Rol getRolByNombre(String nombre);
    List<Rol> getAllRoles();
}
