package com.discoverme.app.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Clase que representa un experiencia/actividad (dotada de anotaciones de Hibernate para poder mapearla con la BD)
 *
 * @author Manuel Leyva
 */
@Entity
@Table(name = "experiencias")
public class Experiencia implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Column(name = "nombre", length = 75)
    private String nombre;
    
    @NotNull
    @Column(name = "descripcion", length = 500)
    private String descripcion;
    
    @Column(name = "puntuacion", columnDefinition = "int default 0", length = 1)
    private Integer puntuacion;
    
    @Column(name = "precio", columnDefinition = "int default 0")
    private Integer precio;
    
    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;
    
    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;
    
    @Column(name = "hora_inicio")
    private LocalTime hora_inicio;
    
    @Column(name = "hora_fin")
    private LocalTime hora_fin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @Column(name = "mins_distancia")
    private Integer mins_distancia;
    
    @Column(name = "mapa", length = 500)
    private String mapa;
    
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinTable(name = "experiencias_tipos",joinColumns = @JoinColumn(name = "experiencia_id"), inverseJoinColumns = @JoinColumn(name = "tipos_id"))
    private List<Tipo> tipos = new ArrayList();

    @OneToMany(cascade = {CascadeType.REMOVE})
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinTable(name = "experiencias_fotos",joinColumns = @JoinColumn(name = "experiencia_id"), inverseJoinColumns = @JoinColumn(name = "fotos_id"))
    private List<Foto> fotos = new ArrayList();
    
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy="id", fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Oferta> ofertas = new HashSet();
    
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy="id", fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Comentario> comentarios = new HashSet();

    public Experiencia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getMins_distancia() {
        return mins_distancia;
    }

    public void setMins_distancia(Integer mins_distancia) {
        this.mins_distancia = mins_distancia;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(Set<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

}