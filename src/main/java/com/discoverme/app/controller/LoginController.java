package com.discoverme.app.controller;

import com.discoverme.app.domain.Usuario;
import com.discoverme.app.service.FotoService;
import com.discoverme.app.service.RolService;
import com.discoverme.app.service.UsuarioService;
import com.discoverme.app.utils.ComprobarRol;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Clase que nos hara de controlador para mostrar las vistas del login
 *
 * @author Manuel Leyva
 */
@Controller
public class LoginController {

    private static final String ROL = "ERROR";

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    FotoService fotoService;

    @Autowired
    RolService rolService;

    /**
     * Funcion que devuelve la vista de la landing page
     *
     * @return Vista de login
     * @author Manuel Leyva
     */
    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView modelview = new ModelAndView("index");
        return modelview;
    }

    /**
     * Funcion que devuelve la vista del login
     *
     * @return Vista de login
     * @author Manuel Leyva
     */
    @GetMapping(value = "/inicio")
    public ModelAndView init(@RequestParam(value = "rol", required = false) String rol) {
        ModelAndView modelview = new ModelAndView("login");
        Usuario user = new Usuario();
        if (rol != null) {
            user = usuarioService.getUsuariosByRol(rolService.getRolByNombre(rol)).get(0);
        }
        modelview.addObject("usuarioDemo", user);
        return modelview;
    }

    /**
     * Funcion para controlar el login de los usuarios
     *
     * @param nombre (id usuario)
     * @param password contraseña usuario
     * @param request
     * @param response
     * @return Vista de login
     * @author Manuel Leyva
     */
    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "password", required = true) String password, HttpServletRequest request, HttpServletResponse response) {
        String redirect = null;
        Usuario usuario = usuarioService.getUsuarioById(nombre);
        if (usuario == null) {
            redirect = "redirect:/errorUser";
        } else if (!usuario.getPasswd().equals(password)) {
            redirect = "redirect:/errorPassword";
        } else {
            request.getSession().setAttribute("usuario", usuario);
            String rol = usuario.getRol().getNombre();
            switch (rol) {
                case "Administrador":
                    redirect = "redirect:/administrador/";
                    break;
                case "Recepcionista":
                    redirect = "redirect:/recepcionista/";
                    break;
                case "Camarero":
                    redirect = "redirect:/camarero/";
                    break;
                case "Colaborador":
                    redirect = "redirect:/colaborador/";
                    break;
                case "Huesped":
                    redirect = "redirect:/huesped/";
            }
        }
        return redirect;
    }

    /**
     * Funcion para controlar usuario incorrecto
     *
     * @return Vista de login
     * @author Manuel Leyva
     */
    @GetMapping(value = "/errorUser")
    public ModelAndView errorUsuario() {
        ModelAndView modelview = new ModelAndView("login");
        modelview.addObject("usuarioDemo", null);
        modelview.getModelMap().addAttribute("error", "Usuario incorrecto.");
        return modelview;
    }

    /**
     * Funcion para controlar password incorrecto
     *
     * @param request
     * @param response
     * @return Vista de login
     * @author Manuel Leyva
     */
    @GetMapping(value = "/errorPassword")
    public ModelAndView errorPasswoerd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("login");
        modelview.addObject("usuarioDemo", null);
        modelview.addObject("error", "Contraseña incorrecta.");
        return modelview;
    }

    /**
     * Funcion para controlar el logout de los usuarios
     *
     * @param request
     * @param response
     * @return Vista de login
     * @author Manuel Leyva
     */
    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("usuario");
        ModelAndView modelview = new ModelAndView("inicio");
        modelview.addObject("usuarioDemo", null);
        modelview.addObject("error", "Contraseña incorrecta.");
        return modelview;
    }

    /**
     * Funcion qu emuestra la vista de editar usuario de cada rol
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/editar")
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = null;
        int rol = (usuario != null) ? usuario.getRol().getId() : 0;
        switch (rol) {
            case 1:
                modelview = new ModelAndView("AdministradorEditarUsuario");
                break;
            case 2:
                modelview = new ModelAndView("RecepcionistaEditarUsuario");
                break;
            case 3:
                modelview = new ModelAndView("CamareroEditarUsuario");
                break;
            case 4:
                modelview = new ModelAndView("HuespedEditarUsuario");
                break;
            case 5:
                modelview = new ModelAndView("ColaboradorEditarUsuario");
                break;
            default:
                modelview = ComprobarRol.comprobar(modelview, usuario, ROL);
                break;
        }
        modelview.getModelMap().addAttribute("user", usuario);
        modelview.getModelMap().addAttribute("avatares", fotoService.getAllAvatares());
        return modelview;
    }

    /**
     * Funcion qu eactualiza la informacion de un usuario
     *
     * @param nombre
     * @param password
     * @param avatar
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/actualizarUsuario")
    public String updateUsuario(@RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "avatar", required = true) int avatar, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        int rol = (usuario != null) ? usuario.getRol().getId() : 0;
        if (rol != 0) {
            usuarioService.updateUsuario(usuario, nombre, password, avatar);
            request.getSession().setAttribute("usuario", usuarioService.getUsuarioById(usuario.getId()));
        }
        return "redirect:/editar/?info=update";
    }
}
