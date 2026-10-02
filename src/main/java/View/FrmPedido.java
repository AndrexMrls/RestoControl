package View;

import Model.Pedido;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmPedido extends JFrame {

    // Componentes de Búsqueda
    public JTextField txtBuscarId = new JTextField(6);
    public JButton btnBuscar = new JButton("Buscar");

    // Componentes de Actualizar 
    public JTextField txtIdActualizar = new JTextField(6);
    public JComboBox<String> cmbNuevoEstado = new JComboBox<>(new String[]{"En preparación", "Entregado", "Cancelado"});
    public JButton btnActualizarEstado = new JButton("Actualizar");

    // Componentes del Formulario
    public JTextField txtIdPedido = new JTextField(12);
    public JTextField txtCliente = new JTextField(12);
    public JComboBox<String> cmbMesa;
    public JTextArea txtPlato = new JTextArea(3, 12);
    public JTextField txtTotal = new JTextField(12);

    // Botones de acción
    public JButton btnGuardar = new JButton("Registrar Pedido");
    public JButton btnLimpiar = new JButton("Limpiar Formulario");

    // Barra de estado
    public JLabel lblEstado = new JLabel(" Listo para operar.");

    // --- PALETA DE COLORES Y FUENTES ---
    private Color colorFondo = new Color(245, 247, 250); // Gris muy claro
    private Color colorPanel = Color.WHITE;
    private Color colorAzul = new Color(0, 123, 255);
    private Color colorVerde = new Color(40, 167, 69);
    private Color colorRojo = new Color(220, 53, 69);
    private Color colorNaranja = new Color(253, 126, 20); 
    private Color colorAzulClaro = new Color(204, 235, 255); // Azul claro para el banner
    
    private Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 14);
    private Font fuenteNormal = new Font("Segoe UI", Font.PLAIN, 13);

    public FrmPedido() {
        setTitle("RestoControl - Gestión de Pedidos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(540, 680); // Ajustado para que el banner y todo el contenido quepa perfecto
        setLocationRelativeTo(null);
        getContentPane().setBackground(colorFondo);

        UIManager.put("TextField.font", fuenteNormal);
        UIManager.put("Label.font", fuenteNormal);

        String[] opcionesMesa = {
            "Seleccionar...", "Mesa 1", "Mesa 2", "Mesa 3", "Mesa 4", "Mesa 5",
            "Mesa 6", "Mesa 7", "Mesa 8", "Mesa 9", "Mesa 10"
        };
        cmbMesa = new JComboBox<>(opcionesMesa);
        cmbMesa.setFont(fuenteNormal);

        // --- FILTROS DE TECLADO ---
        txtTotal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) return;
                if (c == '.' && !txtTotal.getText().contains(".")) return;
                e.consume();
            }
        });

        KeyAdapter filtroNumeros = new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) e.consume();
            }
        };
        
        txtIdPedido.addKeyListener(filtroNumeros);
        txtBuscarId.addKeyListener(filtroNumeros);
        txtIdActualizar.addKeyListener(filtroNumeros);

        // --- ESTILIZAR BOTONES ---
        estilizarBoton(btnGuardar, colorVerde);
        estilizarBoton(btnBuscar, colorAzul);
        estilizarBoton(btnLimpiar, colorRojo);
        estilizarBoton(btnActualizarEstado, colorAzul);

        // =========================================================
        // --- 1. BANNER AZUL CLARO (SUPERIOR) ---
        // =========================================================
        JPanel panelBanner = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        panelBanner.setBackground(colorAzulClaro);
        
        JLabel lblTituloApp = new JLabel("RESTOCONTROL");
        lblTituloApp.setFont(new Font("Segoe UI", Font.BOLD, 22)); 
        lblTituloApp.setForeground(Color.BLACK); 
        panelBanner.add(lblTituloApp);

        // =========================================================
        // --- 2. CONFIGURACIÓN DEL LOGO ORIGINAL (70x70) ---
        // =========================================================
        JLabel lblLogo = new JLabel();
        lblLogo.setBorder(new EmptyBorder(5, 10, 5, 10));
        try {
            java.net.URL imgURL = getClass().getResource("/img/logo.png");
            if (imgURL != null) {
                ImageIcon iconoOriginal = new ImageIcon(imgURL);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imagenEscalada));
            } else {
                lblLogo.setText("[ LOGO ]");
                lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 16));
                lblLogo.setForeground(colorAzul);
            }
        } catch (Exception e) {
            lblLogo.setText("[ LOGO ]");
        }

        // =========================================================
        // --- 3. PANEL DE OPCIONES (Buscar y Actualizar) ---
        // =========================================================
        JPanel panelOpciones = new JPanel(new GridLayout(2, 1, 5, 5));
        panelOpciones.setBackground(colorFondo);

        JPanel panelBusqueda = crearPanelConTitulo("Consultar Pedido");
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBusqueda.add(new JLabel("ID Pedido:"));
        panelBusqueda.add(txtBuscarId);
        panelBusqueda.add(btnBuscar);

        JPanel panelActualizar = crearPanelConTitulo("Actualizar Estado");
        panelActualizar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelActualizar.add(new JLabel("ID Pedido:"));
        panelActualizar.add(txtIdActualizar);
        panelActualizar.add(cmbNuevoEstado);
        panelActualizar.add(btnActualizarEstado);

        panelOpciones.add(panelBusqueda);
        panelOpciones.add(panelActualizar);

        // Unimos el Logo (Izquierda) con las Opciones (Centro)
        JPanel panelLogoOpciones = new JPanel(new BorderLayout());
        panelLogoOpciones.setBackground(colorFondo);
        panelLogoOpciones.add(lblLogo, BorderLayout.WEST);
        panelLogoOpciones.add(panelOpciones, BorderLayout.CENTER);

        // Agrupamos el Banner arriba y el (Logo + Opciones) debajo
        JPanel panelSuperiorCompleto = new JPanel(new BorderLayout());
        panelSuperiorCompleto.setBackground(colorFondo);
        panelSuperiorCompleto.add(panelBanner, BorderLayout.NORTH);
        panelSuperiorCompleto.add(panelLogoOpciones, BorderLayout.CENTER);

        // --- PANEL CENTRAL (FORMULARIO) ---
        JPanel panelFormulario = crearPanelConTitulo("Datos del Nuevo Pedido");
        panelFormulario.setLayout(new GridLayout(5, 2, 10, 15));
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                panelFormulario.getBorder(), new EmptyBorder(10, 15, 10, 15)));

        panelFormulario.add(new JLabel("ID del Pedido:"));
        panelFormulario.add(txtIdPedido);
        panelFormulario.add(new JLabel("Cliente:"));
        panelFormulario.add(txtCliente);
        panelFormulario.add(new JLabel("Mesa:"));
        panelFormulario.add(cmbMesa);
        panelFormulario.add(new JLabel("Plato (Detalle):"));
        txtPlato.setLineWrap(true);
        panelFormulario.add(new JScrollPane(txtPlato));
        panelFormulario.add(new JLabel("Total ($):"));
        panelFormulario.add(txtTotal);

        // --- PANEL INFERIOR (BOTONES Y ESTADO) ---
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(colorFondo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(colorFondo);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);

        JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelStatus.setBackground(new Color(230, 230, 230));
        lblEstado.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblEstado.setForeground(Color.DARK_GRAY);
        panelStatus.add(lblEstado);

        panelInferior.add(panelBotones, BorderLayout.CENTER);
        panelInferior.add(panelStatus, BorderLayout.SOUTH);

        // --- ENSAMBLADO FINAL ---
        JPanel contenedorPrincipal = new JPanel(new BorderLayout(10, 10));
        contenedorPrincipal.setBackground(colorFondo);
        contenedorPrincipal.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        contenedorPrincipal.add(panelSuperiorCompleto, BorderLayout.NORTH); // Banner + Logo + Opciones
        contenedorPrincipal.add(panelFormulario, BorderLayout.CENTER);
        contenedorPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(contenedorPrincipal);
    }

    // --- MÉTODOS DE DISEÑO UI ---
    
    private void estilizarBoton(JButton boton, Color colorBg) {
        boton.setBackground(colorBg);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    private JPanel crearPanelConTitulo(String titulo) {
        JPanel panel = new JPanel();
        panel.setBackground(colorPanel);
        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)), titulo);
        borde.setTitleFont(fuenteTitulo);
        borde.setTitleColor(colorAzul);
        panel.setBorder(borde);
        return panel;
    }

    // --- MÉTODOS DE LA VENTA EMERGENTE ---
    public void mostrarVentanaResultado(Pedido pedido) {
        JDialog ventanaDetalle = new JDialog(this, "Detalle del Pedido #" + pedido.getIdPedido(), true);
        ventanaDetalle.setLayout(new BorderLayout());
        ventanaDetalle.setSize(400, 380);
        ventanaDetalle.setLocationRelativeTo(this);
        ventanaDetalle.getContentPane().setBackground(colorFondo);

        JPanel panelDatos = crearPanelConTitulo("Información de la Consulta");
        panelDatos.setLayout(new GridLayout(7, 2, 10, 15)); 
        panelDatos.setBorder(BorderFactory.createCompoundBorder(
                panelDatos.getBorder(), new EmptyBorder(15, 20, 15, 20)));

        panelDatos.add(new JLabel("ID Pedido:"));
        panelDatos.add(new JLabel("<html><b>" + pedido.getIdPedido() + "</b></html>"));
        panelDatos.add(new JLabel("Cliente:"));
        panelDatos.add(new JLabel(pedido.getCliente()));
        panelDatos.add(new JLabel("Mesa:"));
        panelDatos.add(new JLabel("Mesa " + pedido.getNumeroMesa()));
        panelDatos.add(new JLabel("Plato:"));
        panelDatos.add(new JLabel("<html>" + pedido.getDetalleConsumo() + "</html>"));
        panelDatos.add(new JLabel("Total:"));
        JLabel lblTot = new JLabel("$ " + pedido.getTotal());
        lblTot.setForeground(colorVerde);
        lblTot.setFont(fuenteTitulo);
        panelDatos.add(lblTot);
        
        // MOSTRAR EL ESTADO 
        panelDatos.add(new JLabel("Estado:"));
        JLabel lblEst = new JLabel(pedido.getEstado());
        lblEst.setFont(new Font("Segoe UI", Font.BOLD, 13));
        if (pedido.getEstado().equals("Entregado")) lblEst.setForeground(colorVerde);
        else if (pedido.getEstado().equals("Cancelado")) lblEst.setForeground(colorRojo);
        else lblEst.setForeground(colorNaranja);
        panelDatos.add(lblEst);

        panelDatos.add(new JLabel("Fecha y Hora:"));
        panelDatos.add(new JLabel(pedido.getFechaHoraFormateada()));

        JButton btnCerrar = new JButton("Cerrar Ventana");
        estilizarBoton(btnCerrar, Color.DARK_GRAY);
        btnCerrar.addActionListener(e -> ventanaDetalle.dispose());

        JPanel panelBotonCerrar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotonCerrar.setBackground(colorFondo);
        panelBotonCerrar.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelBotonCerrar.add(btnCerrar);

        ventanaDetalle.add(panelDatos, BorderLayout.CENTER);
        ventanaDetalle.add(panelBotonCerrar, BorderLayout.SOUTH);
        ventanaDetalle.setVisible(true);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "RestoControl", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setMensajeEstado(String mensaje, boolean esError) {
        lblEstado.setText(" " + mensaje);
        lblEstado.setForeground(esError ? colorRojo : colorVerde);
    }

    public void limpiarFormulario() {
        txtIdPedido.setText("");
        txtCliente.setText("");
        cmbMesa.setSelectedIndex(0);
        txtPlato.setText("");
        txtTotal.setText("");
        txtBuscarId.setText("");
        txtIdActualizar.setText(""); 
        setMensajeEstado("Formulario limpio y listo.", false);
        lblEstado.setForeground(Color.DARK_GRAY);
    }
}