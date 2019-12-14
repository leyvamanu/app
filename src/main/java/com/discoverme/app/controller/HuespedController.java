package com.discoverme.app.controller;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Oferta;
import com.discoverme.app.domain.Tipo;
import com.discoverme.app.domain.Usuario;
import com.discoverme.app.service.ComentarioService;
import com.discoverme.app.service.PerfilService;
import com.discoverme.app.service.ServicioService;
import com.discoverme.app.service.ExperienciaService;
import com.discoverme.app.service.OfertaService;
import com.discoverme.app.utils.ComprobarRol;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Clase que nos hara de controlador para mostrar las preferencias
 *
 * @author Manuel Leyva
 */
@Controller
@RequestMapping("/huesped")
public class HuespedController {
    
    private static final String ROL = "Huesped";
    
    @Autowired
    PerfilService perfilService;
    
    @Autowired
    ExperienciaService experienciaService;
    
    @Autowired
    ServicioService servicioService;
    
    @Autowired
    ComentarioService comentarioService;
    
    @Autowired
    OfertaService ofertaService;
    
    /**
    * Funcion que devuelve la vista de preferencias segun el perfil del usuario en sesion
    * @param request
    * @param response 
    * @return Vista de preferencias
    * @author Manuel Leyva    
    */
    @GetMapping(value = "/")
    public ModelAndView getPreferencias(HttpServletRequest request,HttpServletResponse response){
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("HuespedPreferencias");
        modelview.getModelMap().addAttribute("preferencias", perfilService.getPerfilById(usuario.getPerfil().getId()).getTipos());
        String[] preferencias = new String[perfilService.getPerfilById(usuario.getPerfil().getId()).getTipos().size()];
        int i = 0;
        for (Tipo t: perfilService.getPerfilById(usuario.getPerfil().getId()).getTipos()){
            preferencias[i] = t.getId().toString();
            i++;
        }
        request.getSession().setAttribute("preferencias", preferencias);
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
    * Funcion que devuelve la vista de listadoExperiencias cuando es llamada por el metodo GET leyendo el objeto de sesion preferencias con el listado de preferencias
    * @param request
    * @param response 
    * @return Vista de listadoExperiencias
    * @author Manuel Leyva    
    */
    @GetMapping(value = "/listadoExperiencia/")
    public ModelAndView getExperiencias(HttpServletRequest request,HttpServletResponse response){
        System.out.println("holaaaaaaaaaaa");
        ModelAndView modelview = new ModelAndView("HuespedListadoExperiencias");
        String[] preferencias = (String[]) request.getSession().getAttribute("preferencias");
        String orden = (String) request.getSession().getAttribute("orden");
        modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciasByPreferencias(preferencias,orden));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        System.out.println("holaaaaaaaaaaa");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
    * Funcion que devuelve la vista de listadoExperiencias cuando es llamada por el metodo POST creando el objeto de sesion preferencias con el listado de preferencias
    * @param preferencias
    * @param request
    * @param response 
    * @return Vista de listadoExperiencias
    * @author Manuel Leyva    
    */
    @PostMapping(value = "/listadoExperiencia/")
    public ModelAndView getExperienciasByPreferencias(@RequestParam(value = "preferencias", required = false) String[] preferencias,HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("HuespedListadoExperiencias");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String orden = (String) request.getSession().getAttribute("orden");
        if (preferencias != null){
            request.getSession().setAttribute("preferencias", preferencias);
        }else{
            preferencias = (String[]) request.getSession().getAttribute("preferencias");
        }
        modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciasByPreferencias(preferencias,orden));
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    
    /**
    * Funcion que devuelve la vista de listadoExperiencias ordenada por precio
    * @param request
    * @param response 
    * @return Vista de listadoExperiencias
    * @author Manuel Leyva    
    */
    @GetMapping(value = "/listadoExperiencia/precio")
    public ModelAndView getExperienciasOrderByPrecio(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("HuespedListadoExperiencias");
        request.getSession().setAttribute("orden", "precio");
        String[] preferencias = (String[]) request.getSession().getAttribute("preferencias");
        if (preferencias == null){
            modelview.getModelMap().addAttribute("experiencias", experienciaService.getAllExperiencias("precio"));
        }else{
            modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciasByPreferencias(preferencias,"precio"));
        }
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
    * Funcion que devuelve la vista de listadoExperiencias ordenada por distancia
    * @param request
    * @param response 
    * @return Vista de listadoExperiencias
    * @author Manuel Leyva    
    */
    @GetMapping(value = "/listadoExperiencia/distancia")
    public ModelAndView getExperienciasOrderByDistancia(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("HuespedListadoExperiencias");
        request.getSession().setAttribute("orden", "distancia");
        String[] preferencias = (String[]) request.getSession().getAttribute("preferencias");
        if (preferencias == null){
            modelview.getModelMap().addAttribute("experiencias", experienciaService.getAllExperiencias("distancia"));
        }else{
            modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciasByPreferencias(preferencias,"distancia"));
        }
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
    * Funcion que devuelve la vista de listadoExperiencias ordenada por puntuacion
    * @param request
    * @param response 
    * @return Vista de listadoExperiencias
    * @author Manuel Leyva    
    */
    @GetMapping(value = "/listadoExperiencia/puntuacion")
    public ModelAndView getExperienciasOrderByPuntuacion(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("HuespedListadoExperiencias");
        request.getSession().setAttribute("orden", "puntuacion");
        String[] preferencias = (String[]) request.getSession().getAttribute("preferencias");
        if (preferencias == null){
            modelview.getModelMap().addAttribute("experiencias", experienciaService.getAllExperiencias("puntuacion"));
        }else{
            modelview.getModelMap().addAttribute("experiencias", experienciaService.getExperienciasByPreferencias(preferencias,"puntuacion"));
        }
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
    * Funcion que devuelve la vista de actividad definida en la URL {id}
    * @param id del experiencia/actividad
    * @param request
    * @param response 
    * @return Vista de listadoExperiencias
    * @author Manuel Leyva    
    */
    @GetMapping("/listadoExperiencia/{id}")
    public ModelAndView getExperienciaById(@PathVariable("id") Integer id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("HuespedExperiencia");
        modelview.getModelMap().addAttribute("experiencia", experienciaService.getExperienciaById(id));
        modelview.getModelMap().addAttribute("comentarios", comentarioService.getComentariosByIdExperiencia(experienciaService.getExperienciaById(id)));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
    * Funcion que devuelve la vista de actividad definida en la URL {id} i añade un comentario
    * @param id del experiencia/actividad
    * @param titulo del comentario
    * @param puntuacion del comentario
    * @param comentario
    * @param request
    * @param response 
    * @return Vista de listadoExperiencias
    * @author Manuel Leyva    
    */
    @PostMapping(value = "/listadoExperiencia/{id}/agregarComentario")
    public String addComentario(@PathVariable("id") Integer id, 
                                @RequestParam(value = "titulo", required = true) String titulo, 
                                @RequestParam(value = "puntuacion", required = true) String puntuacion, 
                                @RequestParam(value = "comentario", required = true) String comentario,HttpServletRequest request,HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
           //comentarioService.addComentario(id,titulo,puntuacion,comentario,usuario.getId());
        }
        return "redirect:/huesped/listadoExperiencia/"+id+"/?info=create";
    }
    
    /**
     * Funcion que elimina un comentario
     * @param id
     * @param idComentario
     * @param request
     * @param response
     * @return 
     */
    @GetMapping(value = "/listadoExperiencia/{id}/eliminar/{idComentario}")
    public String deleteComentario(@PathVariable("id") Integer id, @PathVariable("idComentario") Integer idComentario,HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            comentarioService.deleteComentario(comentarioService.getComentarioById(idComentario));
        }
        return "redirect:/huesped/listadoExperiencia/"+id+"/?info=delete";
    }
    
    /**
     * Funcion que muestra el formulario de editar un comentario
     * @param id
     * @param idComentario
     * @param request
     * @param response
     * @return 
     */
    @GetMapping(value = "/listadoExperiencia/{id}/{idComentario}")
    public ModelAndView editComentario(@PathVariable("id") Integer id, @PathVariable("idComentario") Integer idComentario, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("HuespedEditarComentario");
        modelview.getModelMap().addAttribute("comentario", comentarioService.getComentarioById(idComentario));
        System.out.println("Comentario: "+comentarioService.getComentarioById(idComentario).getExperiencia().getNombre());
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion que actualiza un comentario
     * @param id
     * @param idComentario
     * @param titulo
     * @param puntuacion
     * @param comentario
     * @param request
     * @param response
     * @return 
     */
    @PostMapping(value = "/listadoExperiencia/{id}/{idComentario}/updateComentario")
    public String updateComentario(@PathVariable("id") Integer id,
            @PathVariable("idComentario") Integer idComentario,
            @RequestParam(value = "titulo", required = true) String titulo,
            @RequestParam(value = "puntuacion", required = true) Integer puntuacion,
            @RequestParam(value = "comentario", required = true) String comentario,
            HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (ComprobarRol.comprobar(usuario, ROL)) {
            //comentarioService.updateComentario(idComentario,titulo,puntuacion,comentario);
        }
        return "redirect:/huesped/listadoExperiencia/"+id+"/?info=update";
    }
    
    /**
    * Funcion que devuelve la vista de listadoServicios 
    * @param request
    * @param response 
    * @return Vista de listadoServicios
    * @author Jose Luis Sanchez Escoda   
    */
    @GetMapping(value = "/listadoServicios")
    public ModelAndView getServicios(HttpServletRequest request,HttpServletResponse response){
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("HuespedListadoServicios");
        modelview.getModelMap().addAttribute("servicios", servicioService.getAllServicios());
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
    * Funcion que devuelve la vista de servicio definido por url/{id}
    * @param id del servicio
    * @param request
    * @param response 
    * @return vista del servicio
    * @author Jose Luis Sanchez Escoda   
    */
    @GetMapping("/listadoServicios/{id}")
    public ModelAndView getServicioByid(@PathVariable("id") Integer id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("HuespedServicio");
        modelview.getModelMap().addAttribute("servicio", servicioService.getServicioById(id));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    /**
     * Funcion que muestra un listado de ofertas
     * @param request
     * @param response
     * @return 
     */
    @GetMapping("/listadoOfertas")
    public ModelAndView getOfertas(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("HuespedListadoOfertas");
        //modelview.getModelMap().addAttribute("ofertasServicios", ofertaService.getOfertasServicios());
        String[] preferencias = (String[]) request.getSession().getAttribute("preferencias");
        String orden = (String) request.getSession().getAttribute("orden");
        List<Oferta> ofertasExperiencias = new ArrayList<Oferta>();
        for(Experiencia experiencia: experienciaService.getExperienciasByPreferencias(preferencias,orden)){
            for (Oferta oferta: ofertaService.getOfertasByExperiencia(experiencia)){
                ofertasExperiencias.add(oferta);
            }
        }
        modelview.getModelMap().addAttribute("ofertasExperiencias", ofertasExperiencias);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
}