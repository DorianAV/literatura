package org.dorian.literatura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String titulo;
    private Integer descargas;
    @Column(unique = true)
    private Integer idAPI;
    private List<String> idiomas;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,},fetch = FetchType.EAGER)
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autor;

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.descargas = datos.descargas();
        this.idiomas = datos.idioma();
        this.idAPI = datos.idAPI();
        this.autor = datos.autor();
    }

    public Libro() {

    }

    @Override
    public String toString() {
        return "----- LIBRO ----- \n" +
                "Titulo:'" + titulo + '\''+"\n" +
                "Autor=" + autor.get(0).getNombre() +"\n"+
                "Idiomas=" + idiomas +"\n"+
                "Numero de descargas=" + descargas +"\n"+
                "----------------";
    }

    public Integer getIdAPI() {
        return idAPI;
    }

    public void setIdAPI(Integer idAPI) {
        this.idAPI = idAPI;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }
}
