package com.discoverme.app.controller;

import com.discoverme.app.domain.Comentario;
import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Oferta;
import com.discoverme.app.domain.Usuario;
import com.discoverme.app.domain.Perfil;
import com.discoverme.app.domain.Rol;
import com.discoverme.app.service.ComentarioService;
import com.discoverme.app.service.ExperienciaService;
import com.discoverme.app.service.OfertaService;
import com.discoverme.app.service.PerfilService;
import com.discoverme.app.service.RolService;
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
 * Controlador para los colaboradores
 * @author leyva
 */
@Controller
@RequestMapping("/colaborador")
public class ColaboradorController {
    
    @Autowired
    ExperienciaService experienciaService;
    
    @Autowired
    ComentarioService comentarioService;
    
    @Autowired
    TipoService tipoService;
    
    @Autowired
    PerfilService perfilService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    OfertaService ofertaService;
    
    @Autowired
    RolService rolService;
    
    private static final String ROL = "Colaborador";
    
    /**
     * Funcion que muestra la pagina inicial del colaborador
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("ColaboradorInicio");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        //calculamos los comentarios
        Integer comentarios = 0;
        for(Experiencia experiencia: experienciaService.getExperienciaByUsuario(usuario)){
            for(Comentario comentario : comentarioService.getComentariosByIdExperiencia(experiencia)){
                comentarios++;
            }   
        }
        Integer ofertas = 0;
        for(Experiencia experiencia: experienciaService.getExperienciaByUsuario(usuario)){
            for(Oferta oferta : ofertaService.getOfertasByExperiencia(experiencia)){
                ofertas++;
            }   
        }
        Rol rol = rolService.getRolByNombre("Huesped");
        modelview.getModelMap().addAttribute("apps", usuarioService.getUsuariosByRol(rol).size());
        modelview.getModelMap().addAttribute("comentarios", comentarios);
        modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciaByUsuario(usuario).size());
        modelview.getModelMap().addAttribute("ofertas", ofertas);
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion que muestra un listado de la experiencias
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoExperiencia/", method = RequestMethod.GET)
    public ModelAndView getExperiencias(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("ColaboradorListadoExperiencias");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciaByUsuario(usuario));
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
        return "redirect:/colaborador/listadoExperiencia/?info=delete";
    }
    
    /**
     * Funcion que elimnia una experiencia
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
        return "redirect:/colaborador/listadoExperiencia/?info=delete";
    }
    
    /**
     * Funcion que mierta el formulario para editar una experiencia
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoExperiencia/{id}", method = RequestMethod.GET)
    public ModelAndView editExperiencia(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("ColaboradorEditarExperiencia");
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
        return "redirect:/colaborador/listadoExperiencia/?info=update";
    }

    /**
     * Funcion qu emuestra un listado de comentarios
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoComentarios/", method = RequestMethod.GET)
    public ModelAndView getComentarios(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("ColaboradorListadoComentarios");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        List<Experiencia> experiencias = new ArrayList<Experiencia>();
        for (Experiencia experiencia : experienciaService.getExperienciaByUsuario(usuario)){
            Set<Comentario> comentarios = new HashSet();
            for(Comentario comentario : comentarioService.getComentariosByIdExperiencia(experiencia)){
                comentarios.add(comentario);
            }
            //experiencia.setComentarios(comentarios);
            experiencias.add(experiencia);
        }
        modelview.getModelMap().addAttribute("experiencias", experiencias);
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Controlador que muestra las diferentes apps por tipo de huesped
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoHuespedes/", method = RequestMethod.GET)
    public ModelAndView getHuespedes(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("ColaboradorAppsActivas");
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
     * Funcion que muertra el formulario para crear experiencias
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/formularioExperiencia", method = RequestMethod.GET)
    public ModelAndView getFormExperiencias(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("ColaboradorFormularioExperiencia");
        modelview.getModelMap().addAttribute("tipos", tipoService.getAllTipos());
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
    @RequestMapping(value = "/agregarExperiencia", method = RequestMethod.POST)
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
            return "redirect:/colaborador/formularioExperiencia/?error=nombre";
        }
        if (ComprobarRol.comprobar(usuario, ROL)) {
            experienciaService.addExperiencia(usuario, nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, descripcion, precio, distancia, mapa, imagenDestacada, imagen1, imagen2, imagen3, tipo);
        }
        return "redirect:/colaborador/listadoExperiencia/?info=create";
    }
    
    /**
     * Funcion que muestra el formulario para crear ofertas
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/formularioOferta", method = RequestMethod.GET)
    public ModelAndView getFormOferta(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("ColaboradorFormularioOferta");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciaByUsuario(usuario));
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion que crea una oferta
     * @param idExperiencia
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
    public String addOferta(@RequestParam(value = "experiencia", required = true) Integer idExperiencia,
                            @RequestParam(value = "nombre", required = true) String nombre,
                            @RequestParam(value = "fecha_inicio", required = true) String fecha_inicio,
                            @RequestParam(value = "fecha_fin", required = true) String fecha_fin,
                            @RequestParam(value = "hora_inicio", required = true) String hora_inicio,
                            @RequestParam(value = "hora_fin", required = true) String hora_fin,
                            @RequestParam(value = "descripcion", required = true) String descripcion,HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ofertaService.getOfertaByNombre(nombre) != null) {
            return "redirect:/colaborador/formularioOferta/?error=nombre";
        }
        if (ComprobarRol.comprobar(usuario, ROL)) {
            ofertaService.addOferta(idExperiencia, nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, descripcion);
        }
        return "redirect:/colaborador/listadoOfertas/?info=create";
    }

    /**
     * Funcion que muestra las ofertas
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/listadoOfertas/", method = RequestMethod.GET)
    public ModelAndView getOfertas(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("ColaboradorListadoOfertas");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        List<Experiencia> experiencias = new ArrayList<Experiencia>();
        for (Experiencia experiencia : experienciaService.getExperienciaByUsuario(usuario)){
            Set<Oferta> ofertas = new HashSet();
            for(Oferta oferta : ofertaService.getOfertasByExperiencia(experiencia)){
                ofertas.add(oferta);
            }
            //experiencia.setOfertas(ofertas);
            experiencias.add(experiencia);
        }
        modelview.getModelMap().addAttribute("experiencias", experiencias);
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
        return "redirect:/colaborador/listadoOfertas/?info=delete";
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
        ModelAndView modelview = new ModelAndView("ColaboradorEditarOferta");
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
        return "redirect:/colaborador/listadoOfertas/?info=update";
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
        return "redirect:/colaborador/listadoOfertas/?info=delete";
    }
}