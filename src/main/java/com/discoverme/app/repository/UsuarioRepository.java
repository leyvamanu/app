/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.discoverme.app.repository;

import com.discoverme.app.domain.Perfil;
import com.discoverme.app.domain.Rol;
import com.discoverme.app.domain.Usuario;
import java.util.List;

/**
 *
 * @author Manuel
 */
public interface UsuarioRepository {
    List<Usuario> getAllUsuarios();
    List<Usuario> getUsuariosByRol(Rol rol);
    List<Usuario> getUsuariosByPerfil(Perfil rol);
    Usuario getUsuarioById(String id);
    void addUsuario(Usuario usuario);
    void updateUsuario(Usuario usuario);
    void deleteUsuario(Usuario usuario);
}
