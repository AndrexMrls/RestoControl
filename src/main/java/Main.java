import Controller.PedidoController;
import View.FrmPedido;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Esto activa un diseño más moderno y suave para las ventanas
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo cargar el diseño del sistema.");
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            FrmPedido vista = new FrmPedido();
            new PedidoController(vista);
            vista.setVisible(true);
        });
    }
}