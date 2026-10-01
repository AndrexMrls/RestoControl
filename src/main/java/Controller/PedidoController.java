package Controller;

import Model.Pedido;
import View.FrmPedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PedidoController implements ActionListener {

    private FrmPedido vista;
    private List<Pedido> listaPedidos;

    public PedidoController(FrmPedido vista) {
        this.vista = vista;
        this.listaPedidos = new ArrayList<>();

        // Registrar eventos de botones
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        
        // --- NUEVO EVENTO (TAREA DE ANDRÉS) ---
        this.vista.btnActualizarEstado.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            registrarPedido();
        } else if (e.getSource() == vista.btnLimpiar) {
            vista.limpiarFormulario();
        } else if (e.getSource() == vista.btnBuscar) {
            buscarPedidoPorId();
        } else if (e.getSource() == vista.btnActualizarEstado) { 
            // --- NUEVA ACCIÓN (TAREA DE ANDRÉS) ---
            actualizarEstadoPedido();
        }
    }

    private void registrarPedido() {
        String idTexto = vista.txtIdPedido.getText().trim();
        String cliente = vista.txtCliente.getText().trim();
        int indiceMesa = vista.cmbMesa.getSelectedIndex();
        String plato = vista.txtPlato.getText().trim();
        String totalTexto = vista.txtTotal.getText().trim();

        if (idTexto.isEmpty() || cliente.isEmpty() || plato.isEmpty() || totalTexto.isEmpty()) {
            vista.setMensajeEstado("Error: Todos los campos son obligatorios.", true);
            return;
        }

        if (indiceMesa == 0) {
            vista.setMensajeEstado("Error: Debe seleccionar una mesa del 1 al 10.", true);
            return;
        }

        try {
            int idIngresado = Integer.parseInt(idTexto);
            double total = Double.parseDouble(totalTexto);

            // Validar unicidad de ID
            for (Pedido p : listaPedidos) {
                if (p.getIdPedido() == idIngresado) {
                    vista.setMensajeEstado("Error: El ID #" + idIngresado + " ya existe.", true);
                    vista.mostrarMensaje("El ID #" + idIngresado + " ya está registrado en el sistema.");
                    return;
                }
            }

            Pedido nuevoPedido = new Pedido(idIngresado, indiceMesa, cliente, plato, total);
            listaPedidos.add(nuevoPedido);

            vista.mostrarMensaje("¡Pedido #" + nuevoPedido.getIdPedido() + " registrado con éxito!");
            vista.limpiarFormulario();
            vista.setMensajeEstado("Registrado exitosamente: Pedido #" + nuevoPedido.getIdPedido(), false);

        } catch (NumberFormatException ex) {
            vista.setMensajeEstado("Error: ID y Total deben ser números válidos.", true);
        }
    }

    private void buscarPedidoPorId() {
        String idTexto = vista.txtBuscarId.getText().trim();

        if (idTexto.isEmpty()) {
            vista.setMensajeEstado("Ingrese un ID para buscar.", true);
            return;
        }

        try {
            int idBuscado = Integer.parseInt(idTexto);
            Pedido encontrado = null;

            for (Pedido p : listaPedidos) {
                if (p.getIdPedido() == idBuscado) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado != null) {
                // Despliega la ventana flotante con el pedido encontrado
                vista.mostrarVentanaResultado(encontrado);
                vista.setMensajeEstado("Pedido #" + idBuscado + " consultado.", false);
                vista.txtBuscarId.setText("");
            } else {
                vista.setMensajeEstado("No se encontró el pedido #" + idBuscado, true);
                vista.mostrarMensaje("El pedido con ID #" + idBuscado + " no existe en el sistema.");
            }

        } catch (NumberFormatException ex) {
            vista.setMensajeEstado("El ID de búsqueda debe ser un número entero.", true);
        }
    }

    // --- NUEVO MÉTODO (TAREA DE ANDRÉS) ---
    private void actualizarEstadoPedido() {
        String idTexto = vista.txtIdActualizar.getText().trim();
        String nuevoEstado = vista.cmbNuevoEstado.getSelectedItem().toString();

        if (idTexto.isEmpty()) {
            vista.setMensajeEstado("Ingrese el ID del pedido a actualizar.", true);
            return;
        }

        try {
            int idBuscado = Integer.parseInt(idTexto);
            boolean encontrado = false;

            for (Pedido p : listaPedidos) {
                if (p.getIdPedido() == idBuscado) {
                    p.setEstado(nuevoEstado); // Se actualiza el estado en el modelo
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                vista.setMensajeEstado("Estado actualizado a: " + nuevoEstado, false);
                vista.txtIdActualizar.setText("");
                vista.mostrarMensaje("El pedido #" + idBuscado + " ahora está: " + nuevoEstado);
            } else {
                vista.setMensajeEstado("No se encontró el pedido #" + idBuscado, true);
                vista.mostrarMensaje("No se puede actualizar. El pedido no existe.");
            }

        } catch (NumberFormatException ex) {
            vista.setMensajeEstado("El ID debe ser un número entero.", true);
        }
    }
}