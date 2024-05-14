/**
 * 
 */
package app;

/**
 * 
 */
public class Proveedor {
    private int codigo;
    private String nombre;
    private String cif;
    private String codigoPostal;
    private String pais;
    private String nombreContacto;
    private String cargoContacto;
    private String mail;
    private String telefono;
    private String codigoContable;
    private String monedaPorDefecto;
    private String tipoIVA;
    private String tipoPago;
    private String plazoPago;
    private String numeroCuenta;
    
    // Constructor
    public Proveedor(int codigo, String nombre, String cif, String codigoPostal, String pais, String nombreContacto, String cargoContacto, String mail, String telefono, String codigoContable, String monedaPorDefecto, String tipoIVA, String tipoPago, String plazoPago, String numeroCuenta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cif = cif;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.nombreContacto = nombreContacto;
        this.cargoContacto = cargoContacto;
        this.mail = mail;
        this.telefono = telefono;
        this.codigoContable = codigoContable;
        this.monedaPorDefecto = monedaPorDefecto;
        this.tipoIVA = tipoIVA;
        this.tipoPago = tipoPago;
        this.plazoPago = plazoPago;
        this.numeroCuenta = numeroCuenta;
    }
    
    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    // Continuar con los getters y setters para los demás atributos...
    
    // Método toString para imprimir la información del proveedor
    @Override
    public String toString() {
        return "Proveedor [codigo=" + codigo + ", nombre=" + nombre + ", cif=" + cif + ", codigoPostal=" + codigoPostal + ", pais=" + pais + ", nombreContacto=" + nombreContacto + ", cargoContacto=" + cargoContacto + ", mail=" + mail + ", telefono=" + telefono + ", codigoContable=" + codigoContable + ", monedaPorDefecto=" + monedaPorDefecto + ", tipoIVA=" + tipoIVA + ", tipoPago=" + tipoPago + ", plazoPago=" + plazoPago + ", numeroCuenta=" + numeroCuenta + "]";
    }
}