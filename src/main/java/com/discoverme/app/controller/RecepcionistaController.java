package com.discoverme.app.controller;

import com.discoverme.app.domain.Usuario;
import com.discoverme.app.service.ExperienciaService;
import com.discoverme.app.service.FotoService;
import com.discoverme.app.service.OfertaService;
import com.discoverme.app.service.PerfilService;
import com.discoverme.app.service.RolService;
import com.discoverme.app.service.TipoService;
import com.discoverme.app.service.UsuarioService;
import com.discoverme.app.utils.ComprobarRol;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador para los recepcionistas
 * @author leyva
 */
@Controller
@RequestMapping("/recepcionista")
public class RecepcionistaController {
    @Autowired
    FotoService fotoService;
     
    @Autowired
    RolService rolService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    PerfilService perfilService;
    
    @Autowired
    TipoService tipoService;
    
    @Autowired
    ExperienciaService experienciaService;
    
    @Autowired
    OfertaService ofertaService;
    
    private static final String ROL = "Recepcionista";
    
    /**
     * Funcion que muestra la vista inicial del recepcionista
     * @param request
     * @param response
     * @return 
     */
    @GetMapping(value = "/")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview= new ModelAndView("RecepcionistaInicio");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
      
    /**
     * Funcion que muestra el formulario para agregar huespedes
     * @param id
     * @param request
     * @param response
     * @return 
     */
    @GetMapping(value = "/formularioUsuarioRecepcionista/{id}")
    public ModelAndView getFormAgregarUsuarioHuesped(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("RecepcionistaFormularioHuesped");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        modelview.getModelMap().addAttribute("roles", rolService.getAllRoles());
        modelview.getModelMap().addAttribute("avatares", fotoService.getAllAvatares());
        modelview.getModelMap().addAttribute("perfiles",perfilService.getAllPerfiles());
        modelview.getModelMap().addAttribute("perfil",perfilService.getPerfilById(id));
        return ComprobarRol.comprobar(modelview, usuario, ROL);
            
    }
    
    /**
     * Funcion qu ecrea un huesped
     * @param nombre
     * @param avatar
     * @param procedencia
     * @param perfil
     * @param request
     * @param response
     * @return 
     */
    @PostMapping(value = "/agregarHuesped/")
    public String addHuesped(
            @RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "avatar", required = true) int avatar, 
            @RequestParam(value = "procedencia", required = true) String procedencia,
            @RequestParam(value = "perfil", required = true) String perfil,
            HttpServletRequest request, HttpServletResponse response) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            String idNewHuesped = "";
            String idperfil = "";
            if (ComprobarRol.comprobar(usuario, ROL)) {
            idNewHuesped = usuarioService.addHuesped(nombre, avatar, procedencia, perfil);
            int numPerfil = perfilService.getNumPerfilByName(perfil);
            
            idperfil = Integer.toString(numPerfil);
            }
            
            return "redirect:/recepcionista/datosNewHuesped/"+idNewHuesped+"/"+idperfil+"/";
    }
    
    /**
     * Funcion qu emuestr ala vista con los datos del nuevo huesped
     * @param id
     * @param idp
     * @param request
     * @param response
     * @return 
     */
    @GetMapping(value = "/datosNewHuesped/{id}/{idp}")
    public ModelAndView getDatosNewUsuario(@PathVariable("id") String id,
        @PathVariable("idp") String idp,HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView modelview = new ModelAndView("RecepcionistaDatosNuevoHuesped");
        modelview.getModelMap().addAttribute("huesped", usuarioService.getUsuarioById(id));
        Integer numIdp= Integer.parseInt(idp);
        modelview.getModelMap().addAttribute("perfil", perfilService.getPerfilById(numIdp));
        
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
     * Funcion qu emuestra el formulario de crear experiencia
     * @param request
     * @param response
     * @return 
     */
    @GetMapping(value = "/formularioExperiencia")
    public ModelAndView getFormExperiencias(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("RecepcionistaFormularioExperiencia");
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
    @PostMapping(value = "/agregarExperiencia/")
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
            return "redirect:/recepcionista/formularioExperiencia/?error=nombre";
        }
        if (ComprobarRol.comprobar(usuario, ROL)) {
            experienciaService.addExperiencia(usuario, nombre, fecha_inicio, fecha_fin, hora_inicio, hora_fin, descripcion, precio, distancia, mapa, imagenDestacada, imagen1, imagen2, imagen3, tipo);
        }
        return "redirect:/recepcionista/?info=create";
    }
    
    /**
     * Funcion para validar una oferta
     * @param request
     * @param response
     * @return 
     */
    @GetMapping(value = "/recepcionistaValidador")
    public ModelAndView validador(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("RecepcionistaValidador");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }

    /**
    * Funcion para validar oferta huésped
    * @param codigo
    * @param request
    * @param response 
    * @return recepcionistaValidador
    * @author Carlos Litwiñiuk  
    */
    @PostMapping(value = "/ofertaValidador")
    public String validadar(@RequestParam(value = "codigo", required = true) String codigo, 
                        HttpServletRequest request,HttpServletResponse response){
        String redirect = null;
        boolean opcion = ofertaService.getOfertaByCodigo(codigo);
        if (!opcion){
            redirect = "redirect:/recepcionista/codigoError";
        }else{
            redirect = "redirect:/recepcionista/codigoValidado";
        }
        return redirect;
    }
        
    /**
    * Funcion para controlar el error de código
    * @param request
    * @param response 
    * @return Vista de login
    * @author Carlos Litwiñiuk Zarza  
    */
    @GetMapping(value = "/codigoError")
    public ModelAndView ofertaError(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("RecepcionistaValidador");
        modelview.getModelMap().addAttribute("codigoError", "El código no es correcto.");
        return modelview;
    }
    
    /**
    * Funcion para controlar la validéz del código
    * @param request
    * @param response 
    * @return Vista de login
    * @author Carlos Litwiñiuk Zarza      
    */
    @GetMapping(value = "/codigoValidado")
    public ModelAndView ofertaValidada(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("RecepcionistaValidador");
        modelview.getModelMap().addAttribute("codigoValidado", "El código es correcto.");
        return modelview;
    }
}
