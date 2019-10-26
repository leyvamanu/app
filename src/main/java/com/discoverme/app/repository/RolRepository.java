package com.discoverme.app.repository;

import com.discoverme.app.domain.Rol;
import java.util.List;

/**
 * Interficie que representa un repositorio de perfiles de rol
 * @author leyva
 */
public interface RolRepository {
    Rol getRolById(int id);
    Rol getRolByNombre(String nombre);
    List<Rol> getAllRoles();
}
