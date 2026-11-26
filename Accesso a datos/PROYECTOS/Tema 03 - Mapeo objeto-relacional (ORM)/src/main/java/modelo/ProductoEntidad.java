package modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Producto")
public class ProductoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock", nullable = false)
    private Integer stock = 0;

    protected ProductoEntidad() {

    } // CONSTRUCTOR

    public ProductoEntidad(Integer id, String nombre, BigDecimal precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Integer getId() {return id;}
    public String getNombre() {return nombre;}
    public BigDecimal getPrecio() {return precio;}
    public Integer getStock() {return stock;}

    public void setId(Integer id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPrecio(BigDecimal precio) {this.precio = precio;}
    public void setStock(Integer stock) {this.stock = stock;}
}
