package com.discoverme.app.utils;


import com.discoverme.app.domain.Usuario;
import org.springframework.web.servlet.ModelAndView;

/**
 * Clase que nos ayuda a la seguridad de la applicacion comprovando que el rol del usuario de sesion es el requerido
 * @author Manuel Leyva
 */
public class ComprobarRol {
    
    /**
    * Funcion que devuelve la vista pasada por parametro en caso de rol correcto o la de login en caso incorrecto
    * @param vista
    * @param usuario
    * @param rol
    * @return Vista 
    * @author Manuel Leyva    
    */
    public static ModelAndView comprobar(ModelAndView vista, Usuario usuario,String rol){
        ModelAndView modelview;
        if (usuario == null){
            modelview = new ModelAndView("login");
            modelview.getModelMap().addAttribute("error", "Acceso denegado.");
        }else if(usuario.getRol().getNombre().equals(rol)){
            modelview = vista;
        }else{
            modelview = new ModelAndView("login");
            modelview.getModelMap().addAttribute("error", "Acceso denegado.");
        }
        return modelview;
    }
    
    /**
     * Funcion qu ecomprueba el rol del usuario
     * @param usuario
     * @param rol
     * @return 
     */
    public static boolean comprobar(Usuario usuario,String rol){
        boolean resultado = false;
        if(rol.equals(usuario.getRol().getNombre())){
            resultado = true;
        }
        return resultado;
    }
}


