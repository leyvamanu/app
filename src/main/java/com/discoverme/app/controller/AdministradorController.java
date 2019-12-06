package com.discoverme.app.controller;

import com.discoverme.app.domain.Comentario;
import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Perfil;
import com.discoverme.app.domain.Rol;
import com.discoverme.app.domain.Usuario;
import com.discoverme.app.service.ComentarioService;
import com.discoverme.app.service.RolService;
import com.discoverme.app.service.ExperienciaService;
import com.discoverme.app.service.FotoService;
import com.discoverme.app.service.OfertaService;
import com.discoverme.app.service.PerfilService;
import com.discoverme.app.service.ServicioService;
import com.discoverme.app.service.TipoService;
import com.discoverme.app.service.UsuarioService;
import com.discoverme.app.utils.ComprobarRol;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador para los administradores
 *
 * @author leyva
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    TipoService tipoService;

    @Autowired
    FotoService fotoService;

    @Autowired
    ServicioService servicioService;
    
    @Autowired
    PerfilService perfilService;
    
    @Autowired
    OfertaService ofertaService;

    private static final String ROL = "Administrador";

    /**
     * Funcion que nos muestra la pantalla inicial del administrador
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorInicio");
        Rol rol = rolService.getRolByNombre("Huesped");
        modelview.getModelMap().addAttribute("apps", usuarioService.getUsuariosByRol(rol).size());
        modelview.getModelMap().addAttribute("comentarios", comentarioService.getAllComentarios().size());
        modelview.getModelMap().addAttribute("experiencias", experienciaService.getAllExperiencias(null).size());
        modelview.getModelMap().addAttribute("servicios",servicioService.getAllServicios().size());
        //modelview.getModelMap().addAttribute("ofertas",ofertaService.getOfertasServicios().size());
        rol = rolService.getRolByNombre("Colaborador");
        modelview.getModelMap().addAttribute("colaboradores", usuarioService.getUsuariosByRol(rol).size());
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que nos muestra el formulario para crear experiencia
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/formularioExperiencia", method = RequestMethod.GET)
    public ModelAndView getFormExperiencias(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorFormularioExperiencia");
        modelview.getModelMap().addAttribute("tipos", tipoService.getAllTipos());
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que nos muestra el formulario para crear un usuario
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/formularioUsuario", method = RequestMethod.GET)
    public ModelAndView getFormUsuarios(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorFormularioUsuario");
        modelview.getModelMap().addAttribute("roles", rolService.getAllRoles());
        modelview.getModelMap().addAttribute("avatares", fotoService.getAllAvatares());
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que nos muestra el formulario para crear un servicio
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/formularioServicioHotel", method = RequestMethod.GET)
    public ModelAndView getFormServicios(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorFormularioServicioHotel");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que crea una experiencia
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param descripcion
     * @param precio
     * @param distancia
     * @param mapa
     * @param imagenDestacada
     * @param imagen1
     * @param imagen2
     * @param imagen3
     * @param tipo
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/agregarExperiencia/", method = RequestMethod.POST)
    public String addExperiencia(@RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "fecha_inicio", required = true) String fecha_inicio,
            @RequestParam(value = "fecha_fin", required = true) String fecha_fin,
            @RequestParam(value = "hora_inicio", required = true) String hora_inicio,
            @RequestParam(value = "hora_fin", required = true) String hora_fin,
            @RequestParam(value = "descripcion", required = true) String descripcion,
            @RequestParam(value = "precio", required = true) String precio,
            @RequestParam(value = "distancia", required = true) String distancia,
            @RequestParam(value = "mapa", required = true) String mapa,
            @RequestParam(value = "imagenDestacada", required = true) MultipartFile imagenDestacada,
            @RequestParam(value = "imagen1", required = true) MultipartFile imagen1,
            @RequestParam(value = "imagen2", required = true) MultipartFile imagen2,
            @RequestParam(value = "imagen3", required = true) MultipartFile imagen3,
            @RequestParam(value = "tipo", required = true) String tipo, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (experienciaService.getExperienciaByNombre(nombre) != null) {
            return "redirect:/administrador/formularioExperiencia/?error=nombre";
        }
        if (ComprobarRol.comprobar(usuario, ROL)) {
            experienciaService.addExperiencia(usuario, nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, descripcion, precio, distancia, mapa, imagenDestacada, imagen1, imagen2, imagen3, tipo);
        }
        return "redirect:/administrador/listadoExperiencia/?info=create";
    }

    /**
     * Funcion que devuelve un listado de experiencias
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoExperiencia/", method = RequestMethod.GET)
    public ModelAndView getExperiencias(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorListadoExperiencias");
        String orden = (String) request.getSession().getAttribute("orden");
        modelview.getModelMap().addAttribute("experiencias", experienciaService.getAllExperiencias(orden));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que elimina una experiencia
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoExperiencia/eliminar/{id}", method = RequestMethod.GET)
    public String deleteExperiencia(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            experienciaService.deleteExperiencia(experienciaService.getExperienciaById(id));
        }
        return "redirect:/administrador/listadoExperiencia/?info=delete";
    }
    
    /**
     * Funcion que elimina ina experiencia
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoExperiencia/{id}/eliminar", method = RequestMethod.GET)
    public String deleteWhenEditExperiencia(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            experienciaService.deleteExperiencia(experienciaService.getExperienciaById(id));
        }
        return "redirect:/administrador/listadoExperiencia/?info=delete";
    }
    
    /**
     * Funcion que muestra el formulario para editar una experiencia
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoExperiencia/{id}", method = RequestMethod.GET)
    public ModelAndView editExperiencia(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("AdministradorEditarExperiencia");
        modelview.getModelMap().addAttribute("tipos", tipoService.getAllTipos());
        modelview.getModelMap().addAttribute("experiencia", experienciaService.getExperienciaById(id));
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que actualiza una experiencia
     * @param id
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param descripcion
     * @param precio
     * @param distancia
     * @param mapa
     * @param imagenDestacada
     * @param imagen1
     * @param imagen2
     * @param imagen3
     * @param tipo
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoExperiencia/actualizar/{id}", method = RequestMethod.POST)
    public String updateExperiencia(@PathVariable("id") Integer id,
            @RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "fecha_inicio", required = true) String fecha_inicio,
            @RequestParam(value = "fecha_fin", required = true) String fecha_fin,
            @RequestParam(value = "hora_inicio", required = true) String hora_inicio,
            @RequestParam(value = "hora_fin", required = true) String hora_fin,
            @RequestParam(value = "descripcion", required = true) String descripcion,
            @RequestParam(value = "precio", required = true) String precio,
            @RequestParam(value = "distancia", required = true) String distancia,
            @RequestParam(value = "mapa", required = true) String mapa,
            @RequestParam(value = "imagenDestacada", required = false) MultipartFile imagenDestacada,
            @RequestParam(value = "imagen1", required = false) MultipartFile imagen1,
            @RequestParam(value = "imagen2", required = false) MultipartFile imagen2,
            @RequestParam(value = "imagen3", required = false) MultipartFile imagen3,
            @RequestParam(value = "tipo", required = true) String tipo, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            experienciaService.updateExperiencia(id, usuario, nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, descripcion, precio, distancia, mapa, imagenDestacada, imagen1, imagen2, imagen3, tipo);
        }
        return "redirect:/administrador/listadoExperiencia/?info=update";
    }

    /**
     * Funcion que muestra un listado de servicios
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoServicio/", method = RequestMethod.GET)
    public ModelAndView getServicios(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorListadoServicios");
        modelview.getModelMap().addAttribute("servicios", servicioService.getAllServicios());
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que crea un servicio
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param descripcion
     * @param precio
     * @param imagenDestacada
     * @param imagen1
     * @param imagen2
     * @param imagen3
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/agregarServicioHotel/", method = RequestMethod.POST)
    public String addServicio(@RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "fecha_inicio", required = true) String fecha_inicio,
            @RequestParam(value = "fecha_fin", required = true) String fecha_fin,
            @RequestParam(value = "hora_inicio", required = true) String hora_inicio,
            @RequestParam(value = "hora_fin", required = true) String hora_fin,
            @RequestParam(value = "descripcion", required = true) String descripcion,
            @RequestParam(value = "precio", required = true) String precio,
            @RequestParam(value = "imagenDestacada", required = true) MultipartFile imagenDestacada,
            @RequestParam(value = "imagen1", required = true) MultipartFile imagen1,
            @RequestParam(value = "imagen2", required = true) MultipartFile imagen2,
            @RequestParam(value = "imagen3", required = true) MultipartFile imagen3, HttpServletRequest request, HttpServletResponse response) {
        if (servicioService.getServicioByNombre(nombre) != null) {
            return "redirect:/administrador/formularioServicioHotel/?error=nombre";
        }
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            servicioService.addServicio(nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, descripcion, precio, imagenDestacada, imagen1, imagen2, imagen3);
        }
        return "redirect:/administrador/listadoServicio/?info=create";
    }

    /**
     * Funcion que elimina un servicio
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoServicio/eliminar/{id}", method = RequestMethod.GET)
    public String deleteServicio(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            servicioService.deleteServicio(servicioService.getServicioById(id));
        }
        return "redirect:/administrador/listadoServicio/?info=delete";
    }
    
    /**
     * Funcion que elimina un servicio
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoServicio/{id}/eliminar", method = RequestMethod.GET)
    public String deleteWhenEditServicio(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            servicioService.deleteServicio(servicioService.getServicioById(id));
        }
        return "redirect:/administrador/listadoServicio/?info=delete";
    }
    
    @RequestMapping(value = "/listadoServicio/{id}", method = RequestMethod.GET)
    public ModelAndView editServicio(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("AdministradorEditarServicio");
        modelview.getModelMap().addAttribute("servicio", servicioService.getServicioById(id));
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que actualiza un servicio
     * @param id
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param disponible
     * @param descripcion
     * @param precio
     * @param imagenDestacada
     * @param imagen1
     * @param imagen2
     * @param imagen3
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoServicio/actualizar/{id}", method = RequestMethod.POST)
    public String updateServicio(@PathVariable("id") Integer id,
            @RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "fecha_inicio", required = true) String fecha_inicio,
            @RequestParam(value = "fecha_fin", required = true) String fecha_fin,
            @RequestParam(value = "hora_inicio", required = true) String hora_inicio,
            @RequestParam(value = "hora_fin", required = true) String hora_fin,
            @RequestParam(value = "disponible", required = true) String disponible, //OJO poner disponible no disponible?
            @RequestParam(value = "descripcion", required = true) String descripcion,
            @RequestParam(value = "precio", required = true) String precio,
            @RequestParam(value = "imagenDestacada", required = false) MultipartFile imagenDestacada,
            @RequestParam(value = "imagen1", required = false) MultipartFile imagen1,
            @RequestParam(value = "imagen2", required = false) MultipartFile imagen2,
            @RequestParam(value = "imagen3", required = false) MultipartFile imagen3, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            servicioService.updateServicio(id, nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, disponible, descripcion, precio, imagenDestacada, imagen1, imagen2, imagen3);
        }
        return "redirect:/administrador/listadoServicio/?info=update";
    }

    /**
     * Funcion que crea un usuario
     * @param rol
     * @param nombre
     * @param avatar
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/agregarUsuario", method = RequestMethod.POST)
    public String addUsuario(@RequestParam(value = "rol", required = true) int rol,
            @RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "avatar", required = true) int avatar, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String idNewUsuario = "";
        if (ComprobarRol.comprobar(usuario, ROL)) {
            idNewUsuario = usuarioService.addUsuario(rol, nombre, avatar, null, null);
        }
        return "redirect:/administrador/datosNewUsuario/"+idNewUsuario;
    }
    
    /**
     * Funcion qu emuestra los datos del ultimo usuario creado
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/datosNewUsuario/{id}", method = RequestMethod.GET)
    public ModelAndView getDatosNewUsuario(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("AdministradorDatosNuevoUsuario");
        modelview.getModelMap().addAttribute("user", usuarioService.getUsuarioById(id));
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion que nos muestra la vista de huespedes de la aplicacion con diferentes datos
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoHuespedes/", method = RequestMethod.GET)
    public ModelAndView getHuespedes(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorAppsActivas");
        Perfil soltero = perfilService.getPerfilByNombre("Soltero");
        Perfil familiar = perfilService.getPerfilByNombre("Familiar");
        Perfil deluxe = perfilService.getPerfilByNombre("Deluxe");
        Perfil aventurero = perfilService.getPerfilByNombre("Aventurero");
        Perfil masCincuenta = perfilService.getPerfilByNombre("Mayor de 50");
        Perfil lowCost = perfilService.getPerfilByNombre("Low Cost");
        modelview.getModelMap().addAttribute("soltero", usuarioService.getUsuariosByPerfil(soltero).size());
        modelview.getModelMap().addAttribute("familiar", usuarioService.getUsuariosByPerfil(familiar).size());
        modelview.getModelMap().addAttribute("deluxe", usuarioService.getUsuariosByPerfil(deluxe).size());
        modelview.getModelMap().addAttribute("aventurero", usuarioService.getUsuariosByPerfil(aventurero).size());
        modelview.getModelMap().addAttribute("masCincuenta", usuarioService.getUsuariosByPerfil(masCincuenta).size());
        modelview.getModelMap().addAttribute("lowCost", usuarioService.getUsuariosByPerfil(lowCost).size());
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion que nos muestra un listado de colaboradores
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoColaboradores/", method = RequestMethod.GET)
    public ModelAndView getColaboradores(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorListadoColaborador");
        List<Usuario> colaboradores = new ArrayList<Usuario>();
        for (Usuario usuario : usuarioService.getUsuariosByRol(rolService.getRolByNombre("colaborador"))){
            Set<Experiencia> experiencias = new HashSet();
            for(Experiencia experiencia : experienciaService.getExperienciaByUsuario(usuario)){
                experiencias.add(experiencia);
            }
            //usuario.setExperiencias(experiencias);
            colaboradores.add(usuario);
        }
        modelview.getModelMap().addAttribute("colaboradores", colaboradores);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion qu emuestra un listado de colaboradores
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoColaboradores/eliminarColaborador/{id}", method = RequestMethod.GET)
    public String deleteColaborador(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            usuarioService.deleteUsuario(usuarioService.getUsuarioById(id));
        }
        return "redirect:/administrador/listadoColaboradores/?info=colaborador";
    }
    
    /**
     * Funcion que elimina la experiencia de un colaborador
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoColaboradores/eliminarExperiencia/{id}", method = RequestMethod.GET)
    public String deleteExperienciaColaborador(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            experienciaService.deleteExperiencia(experienciaService.getExperienciaById(id));
        }
        return "redirect:/administrador/listadoColaboradores/?info=experiencia";
    }
    
    /**
     * Funcion que muestra un listado de todos los comentarios
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoComentarios/", method = RequestMethod.GET)
    public ModelAndView getComentarios(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorListadoComentarios");
        List<Experiencia> experiencias = new ArrayList<Experiencia>();
        for (Experiencia experiencia : experienciaService.getAllExperiencias(null)){
            Set<Comentario> comentarios = new HashSet();
            for(Comentario comentario : comentarioService.getComentariosByIdExperiencia(experiencia)){
                comentarios.add(comentario);
            }
            //experiencia.setComentarios(comentarios);
            experiencias.add(experiencia);
        }
        modelview.getModelMap().addAttribute("experiencias", experiencias);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    
    /**
     * Funcion que muestra el formulario para crear una oferta
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/formularioOferta", method = RequestMethod.GET)
    public ModelAndView getFormOferta(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorFormularioOferta");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciaByUsuario(usuario));
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion que crea una oferta
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param descripcion
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/agregarOferta", method = RequestMethod.POST)
    public String addOferta(@RequestParam(value = "nombre", required = true) String nombre,
                            @RequestParam(value = "fecha_inicio", required = true) String fecha_inicio,
                            @RequestParam(value = "fecha_fin", required = true) String fecha_fin,
                            @RequestParam(value = "hora_inicio", required = true) String hora_inicio,
                            @RequestParam(value = "hora_fin", required = true) String hora_fin,
                            @RequestParam(value = "descripcion", required = true) String descripcion,HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ofertaService.getOfertaByNombre(nombre) != null) {
            return "redirect:/administrador/formularioOferta/?error=nombre";
        }
        if (ComprobarRol.comprobar(usuario, ROL)) {
            ofertaService.addOferta(null, nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, descripcion);
        }
        return "redirect:/administrador/listadoOfertas/?info=create";
    }
    
    /**
     * Funcion que muertra el listado de ofertas
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoOfertas/", method = RequestMethod.GET)
    public ModelAndView getOfertas(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("AdministradorListadoOfertas");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        //modelview.getModelMap().addAttribute("ofertas", ofertaService.getOfertasServicios());
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion que elimina una oferta
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoOfertas/eliminar/{id}", method = RequestMethod.GET)
    public String deleteOferta(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            ofertaService.deleteOferta(ofertaService.getOfertaById(id));
        }
        return "redirect:/administrador/listadoOfertas/?info=delete";
    }
    
    /**
     * Funcion que muestra el formulario para editar una oferta
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoOfertas/{id}", method = RequestMethod.GET)
    public ModelAndView editOferta(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("AdministradorEditarOferta");
        modelview.getModelMap().addAttribute("oferta", ofertaService.getOfertaById(id));
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion que actualiza una oferta
     * @param id
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param descripcion
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoOfertas/actualizar/{id}", method = RequestMethod.POST)
    public String updateOferta(@PathVariable("id") Integer id,
                               @RequestParam(value = "nombre", required = true) String nombre,
                               @RequestParam(value = "fecha_inicio", required = true) String fecha_inicio,
                               @RequestParam(value = "fecha_fin", required = true) String fecha_fin,
                               @RequestParam(value = "hora_inicio", required = true) String hora_inicio,
                               @RequestParam(value = "hora_fin", required = true) String hora_fin,
                               @RequestParam(value = "descripcion", required = true) String descripcion,HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            ofertaService.updateOferta(id,nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, descripcion);
        }
        return "redirect:/administrador/listadoOfertas/?info=update";
    }
    
    /**
     * Funcion que elimina una oferta
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoOfertas/{id}/eliminar", method = RequestMethod.GET)
    public String deleteWhenEditOferta(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            ofertaService.deleteOferta(ofertaService.getOfertaById(id));
        }
        return "redirect:/administrador/listadoOfertas/?info=delete";
    }
}

