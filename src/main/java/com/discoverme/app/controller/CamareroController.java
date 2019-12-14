package com.discoverme.app.controller;

import com.discoverme.app.domain.Usuario;
import com.discoverme.app.service.OfertaService;
import com.discoverme.app.utils.ComprobarRol;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador para los camareros
 * @author leyva
 */
@Controller
@RequestMapping("/camarero")
public class CamareroController {
    
    @Autowired
    OfertaService ofertaService;
    
    private static final String ROL = "Camarero";
    
    /**
     * Funcion qu emuestra la pagina inicial del camarero
     * @param request
     * @param response
     * @return 
     */
    @GetMapping(value = "/")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("CamareroInicio");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        return ComprobarRol.comprobar(modelview, usuario, ROL);
    }
    
    
    /*
    * Funcion para validar oferta huésped
    * @param oferta
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
            redirect = "redirect:/camarero/codigoError";
        }else{
            redirect = "redirect:/camarero/codigoValidado";
        }
        return redirect;
    }
        
    /*
    * Funcion para controlar el error de código
    * @param request
    * @param response 
    * @return Vista de login
    * @author Carlos Litwiñiuk Zarza  
    */
    @GetMapping(value = "/codigoError")
    public ModelAndView ofertaError(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("CamareroInicio");
        modelview.getModelMap().addAttribute("codigoError", "El código no es correcto.");
        return modelview;
    }
    
    /*
    * Funcion para controlar la validéz del código
    * @param request
    * @param response 
    * @return Vista de login
    * @author Carlos Litwiñiuk Zarza      
    */
    @GetMapping(value = "/codigoValidado")
    public ModelAndView ofertaValidada(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelview = new ModelAndView("CamareroInicio");
        modelview.getModelMap().addAttribute("codigoValidado", "El código es correcto.");
        return modelview;
    }
}
