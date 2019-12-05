package com.discoverme.app.controller;

import com.discoverme.app.domain.Comentario;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Foto;
import com.discoverme.app.domain.Oferta;
import com.discoverme.app.domain.Perfil;
import com.discoverme.app.domain.Rol;
import com.discoverme.app.domain.Servicio;
import com.discoverme.app.domain.Tipo;
import com.discoverme.app.domain.Usuario;
import com.discoverme.app.service.ComentarioService;
import com.discoverme.app.service.ExperienciaService;
import com.discoverme.app.service.FotoService;
import com.discoverme.app.service.OfertaService;
import com.discoverme.app.service.PerfilService;
import com.discoverme.app.service.RolService;
import com.discoverme.app.service.ServicioService;
import com.discoverme.app.service.TipoService;
import com.discoverme.app.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class HuespedRestController {

    @Autowired
    private RolService rolService;

    @Autowired
    private ExperienciaService experienciaService;

    @Autowired
    private TipoService tipoService;

    @Autowired
    private FotoService fotoService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private OfertaService ofertaService;

//    @GetMapping("/hola")
//    public String getHello() {
//        return "HOLA MUNDO";
//    }

    @GetMapping("/rol")
    public List<Rol> getRoles() {
        return rolService.getAllRoles();
    }

    @GetMapping("/rol/{id}")
    public Rol getRolById(@PathVariable int id) {
        Rol rol = rolService.getRolById(id);
        if (rol == null) {
            throw new RuntimeException("Rol id not found -" + id);
        }
        return rol;
    }

    @GetMapping("/tipos")
    public List<Tipo> getTipos() {
        return tipoService.getAllTipos();
    }

    @GetMapping("/tipos/{id}")
    public Tipo getTipoById(@PathVariable int id) {
        Tipo tipo = tipoService.getTipoById(id);
        if (tipo == null) {
            throw new RuntimeException("Tipo id not found -" + id);
        }
        return tipo;
    }

    @GetMapping("/fotos")
    public List<Foto> getFotos() {
        return fotoService.getAllAvatares();
    }

    @GetMapping("/experiencias")
    public List<Experiencia> getExperiencias() {
        return experienciaService.getAllExperiencias();
    }

    @GetMapping("/servicios")
    public List<Servicio> getServicios() {
        return servicioService.getAllServicios();
    }

    @GetMapping("/servicios/{id}")
    public Servicio getServicioById(@PathVariable int id) {
        Servicio servicio = servicioService.getServicioById(id);
        if (servicio == null) {
            throw new RuntimeException("Servicio id not found -" + id);
        }
        return servicio;
    }

    @GetMapping("/usuario/{id}")
    public Usuario getUsuarioById(@PathVariable String id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            throw new RuntimeException("Servicio id not found -" + id);
        }
        return usuario;
    }
    
    @GetMapping("/comentarios")
    public List<Comentario> getComentarios() {
        return comentarioService.getAllComentarios();
    }

    @GetMapping("/perfiles")
    public List<Perfil> getPerfiles() {
        return perfilService.getAllPerfiles();
    }

    @GetMapping("/ofertas")
    public List<Oferta> getOfertas() {
        return ofertaService.getAllOfertas();
    }
}
