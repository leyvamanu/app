package com.discoverme.app.service;

import com.discoverme.app.domain.Perfil;
import com.discoverme.app.domain.Rol;
import com.discoverme.app.domain.Usuario;
import java.util.List;

/**
 * Interficie que nos da servicio para acceder al repositorio de usuarios
 * @author Manuel Leyva
 */
public interface UsuarioService {
    List<Usuario> getUsuariosByRol(Rol rol);
    List<Usuario> getUsuariosByPerfil(Perfil perfil);
    Usuario getUsuarioById(String id);
    String addUsuario(Integer rol,String nombre,Integer avatar, String procedencia, Integer perfil);
    String addHuesped (String nombre,Integer avatar, String procedencia, String perfil);
    void deleteUsuario(Usuario usuario);
    void updateUsuario(Usuario usuario, String nombre, String password, int avatar);
    void updateHuesped(Usuario usuario, String nombre, String password, String procedencia, int avatar);
}
