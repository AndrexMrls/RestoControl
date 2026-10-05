package Model; 

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private int idPedido;
    private int numeroMesa;
    private String cliente;
    private String detalleConsumo;
    private double total;
    private LocalDateTime fechaHora;
    
    // --- NUEVO ATRIBUTO ---
    private String estado; 

    public Pedido(int idPedido, int numeroMesa, String cliente, String detalleConsumo, double total) {
        this.idPedido = idPedido;
        this.numeroMesa = numeroMesa;
        this.cliente = cliente;
        this.detalleConsumo = detalleConsumo;
        this.total = total;
        this.fechaHora = LocalDateTime.now();
        
       
        this.estado = "Pendiente"; 
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDetalleConsumo() {
        return detalleConsumo;
    }

    public void setDetalleConsumo(String detalleConsumo) {
        this.detalleConsumo = detalleConsumo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getFechaHoraFormateada() {
        return fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // --- NUEVOS GETTERS Y SETTERS (TAREA DE RAMIRO) ---
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}