package com.discoverme.app.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Clase que representa un usuario (dotada de anotaciones de Hibernate para poder mapearla con la BD)
 *
 * @author Manuel Leyva
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
    @Id
    @Column(name = "id", length = 10)
    private String id;
    
    @NotNull
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @NotNull
    @Column(name = "passwd", length = 10)
    private String passwd;
    
    @Column(name = "procedencia", length = 50)
    private String procedencia;
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Rol rol;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foto_id")
    private Foto foto;
    
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy="id", fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Experiencia> experiencias = new HashSet();

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Set<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(Set<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }
    
}