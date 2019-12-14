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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/experiencias/{usuario}")
    public List<Experiencia> getExperienciaByUsuario(@PathVariable String usuario) {
        return experienciaService.getExperienciaByUsuario(usuarioService.getUsuarioById(usuario));
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

    //Comentarios /////////////////////////
    @GetMapping("/comentarios")
    public List<Comentario> getComentarios(@RequestParam(value = "experiencia", required = false) Integer id) {
        List<Comentario> comentarios = null;
        if(id != null){
            Experiencia experiencia = experienciaService.getExperienciaById(id);
            if (experiencia == null) {
                throw new RuntimeException("Experiencia id not found -" + id);
            }
            comentarios = comentarioService.getComentariosByIdExperiencia(experiencia);
        }else{
            comentarios = comentarioService.getAllComentarios();
        }
        return comentarios;
    }

    @GetMapping("/comentarios/{id}")
    public Comentario getComentarioById(@PathVariable Integer id) {
        Comentario comentario = comentarioService.getComentarioById(id);
        if (comentario == null) {
            throw new RuntimeException("Comentario id not found -" + id);
        }
        return comentario;
    }

    @PostMapping("/comentarios")
    public void addComentario(@RequestBody Comentario comentario) {
        comentarioService.addComentario(comentario);
    }

    @PutMapping("/comentarios")
    public void updateComentario(@RequestBody Comentario comentario) {
        Comentario c = comentarioService.getComentarioById(comentario.getId());
        if (c == null) {
            throw new RuntimeException("Comentario id not found -" + comentario.getId());
        }
        comentarioService.updateComentario(comentario);
    } 
    
    @DeleteMapping("/comentarios/{id}")
    public void deleteComentario(@PathVariable int id) {
        Comentario comentario = comentarioService.getComentarioById(id);
        if (comentario == null) {
            throw new RuntimeException("Comentario id not found -" + id);
        }
        comentarioService.deleteComentario(comentario);
    }
    //Comentarios /////////////////////////

    @GetMapping("/perfiles")
    public List<Perfil> getPerfiles() {
        return perfilService.getAllPerfiles();
    }

    @GetMapping("/ofertas")
    public List<Oferta> getOfertas() {
        return ofertaService.getAllOfertas();
    }

    @DeleteMapping("/oferta/{id}")
    public void deleteOferta(@PathVariable int id) {
        Oferta oferta = ofertaService.getOfertaById(id);
        ofertaService.deleteOferta(oferta);
    }
}
