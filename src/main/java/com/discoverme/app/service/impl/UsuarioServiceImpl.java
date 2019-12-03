package com.discoverme.app.service.impl;

import com.discoverme.app.domain.Perfil;
import com.discoverme.app.domain.Rol;
import com.discoverme.app.domain.Usuario;
import com.discoverme.app.repository.FotoRepository;
import com.discoverme.app.repository.PerfilRepository;
import com.discoverme.app.repository.RolRepository;
import com.discoverme.app.repository.UsuarioRepository;
import com.discoverme.app.service.UsuarioService;
import com.discoverme.app.utils.GeneradorCodigos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que nos da servicio para acceder al repositorio de usuarios
 *
 * @author Manuel Leyva
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    FotoRepository fotoRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    PerfilRepository perfilRepository;

    /**
     * Funcion que devuelve todos los usuarios por tipo de rol
     * @param rol
     * @return
     * @author Manuel Leyva
     */
    @Override
    public List<Usuario> getUsuariosByRol(Rol rol) {
        return usuarioRepository.getUsuariosByRol(rol);
    }

    /**
     * Funcion que devuelve listado de usuarios por perfil
     * @param perfil
     * @return
     * @author Manuel Leyva
     */
    @Override
    public List<Usuario> getUsuariosByPerfil(Perfil perfil) {
        return usuarioRepository.getUsuariosByPerfil(perfil);
    }

    /**
     * Funcion que devuelve un usuario por id
     * @param id
     * @return
     * @author Manuel Leyva
     */
    @Override
    public Usuario getUsuarioById(String id) {
        return usuarioRepository.getUsuarioById(id);
    }

    /**
     * Funcion que agrega un nuevo usuario
     * @param rol
     * @param nombre
     * @param avatar
     * @param procedencia
     * @param perfil
     * @return
     * @author Manuel Leyva
     */
    @Override
    public String addUsuario(Integer rol, String nombre, Integer avatar, String procedencia, Integer perfil) {
        Usuario usuario = new Usuario();
        String id = "";
        do {
            id = generarIdUsuario(rol, nombre);
        } while (usuarioRepository.getUsuarioById(id) != null);
        usuario.setId(id);
        usuario.setPasswd(GeneradorCodigos.getCodigo(6));
        usuario.setNombre(nombre);
        usuario.setRol(rolRepository.getRolById((int) rol));
        usuario.setFoto(fotoRepository.getFotoById(avatar));
        usuarioRepository.addUsuario(usuario);
        return id;
    }

    /**
     * Funcion que agrega un nuevo huesped
     * @param nombre
     * @param avatar
     * @param procedencia
     * @param perfil
     * @return
     * @author Manuel Leyva
     */
    @Override
    public String addHuesped(String nombre, Integer avatar, String procedencia, String perfil) {
        int Idrol = 4;
        int Idperfil;
        /*el rol siempre es con valor de id 4 (huesped) y perfil depende del string
        que viene del formulario*/
        if (perfil.equals("Deluxe")) {
            Idperfil = 1;
        } else if (perfil.equals("Soltero")) {
            Idperfil = 2;
        } else if (perfil.equals("Low Cost")) {
            Idperfil = 3;
        } else if (perfil.equals("Aventurero")) {
            Idperfil = 4;
        } else if (perfil.equals("Familiar")) {
            Idperfil = 5;
        } else {
            Idperfil = 6;
        }
        Usuario usuario = new Usuario();
        String id = "";
        do {
            id = generarIdUsuario(Idrol, nombre);
        } while (usuarioRepository.getUsuarioById(id) != null);
        usuario.setId(id);
        usuario.setPasswd(GeneradorCodigos.getCodigo(6));
        usuario.setNombre(nombre);
        usuario.setRol(rolRepository.getRolById(Idrol));
        usuario.setFoto(fotoRepository.getFotoById(avatar));
        usuario.setProcedencia(procedencia);
        usuario.setPerfil(perfilRepository.getPerfilById(Idperfil));
        usuarioRepository.addUsuario(usuario);
        return id;
    }

    /**
     * Funcion que borra un usuario
     * @param usuario
     * @author Manuel Leyva
     */
    @Override
    public void deleteUsuario(Usuario usuario) {
        usuarioRepository.deleteUsuario(usuario);
    }

    /**
     * Funcion que actualiza datos de un usuario
     * @param usuario
     * @param nombre
     * @param password
     * @param avatar
     * @author Manuel Leyva
     */
    @Override
    public void updateUsuario(Usuario usuario, String nombre, String password, int avatar) {
        Usuario user = usuarioRepository.getUsuarioById(usuario.getId());
        user.setNombre(nombre);
        user.setPasswd(password);
        user.setFoto(fotoRepository.getFotoById(avatar));
        usuarioRepository.addUsuario(user);
    }

    /**
     * Funcion que actualiza datos de un huesped
     * @param usuario
     * @param nombre
     * @param password
     * @param procedencia
     * @param avatar
     * @author Manuel Leyva
     */
    @Override
    public void updateHuesped(Usuario usuario, String nombre, String password, String procedencia, int avatar) {
        Usuario user = usuarioRepository.getUsuarioById(usuario.getId());
        user.setNombre(nombre);
        user.setPasswd(password);
        user.setProcedencia(procedencia);
        user.setFoto(fotoRepository.getFotoById(avatar));
        usuarioRepository.addUsuario(user);
    }

    /**
     * Funcion que genera un nuevo id para un usuario a partir de su rol y
     * nombre
     * @param rol
     * @param nombre
     * @return
     * @author Manuel Leyva
     */
    private String generarIdUsuario(Integer rol, String nombre) {
        String user = "";
        switch (rol) {
            case 1:
                user = "ADM";
                break;
            case 2:
                user = "REC";
                break;
            case 3:
                user = "CAM";
                break;
            case 4:
                user = "HUE";
                break;
            case 5:
                user = "COL";
                break;
        }
        user += nombre.substring(0, 3) + GeneradorCodigos.getPinNumber();
        return user;
    }

}
