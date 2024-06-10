package usuario;
import negocio.*;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FPrimera extends JFrame {
    private static final long serialVersionUID = 1L;

    private Empresa empresa;

    public FPrimera() {
        empresa = Empresa.recuperarse();

        setTitle("Gestión de Autopartes Tutta la Macchina");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        ClientesPanel clientesPanel = new ClientesPanel(empresa);
        AutopartesPanel autopartesPanel = new AutopartesPanel(empresa);
        VentasPanel ventasPanel = new VentasPanel(empresa);
        PedidosPanel pedidosPanel = new PedidosPanel(empresa, ventasPanel);
        
        tabbedPane.addTab("Clientes", clientesPanel);
        tabbedPane.addTab("Autopartes", autopartesPanel);
        tabbedPane.addTab("Pedidos", pedidosPanel);
        tabbedPane.addTab("Ventas", ventasPanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);  
 
        // Barra de menú
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu archivoMenu = new JMenu("Archivo");
        menuBar.add(archivoMenu);

        JMenuItem salirMenuItem = new JMenuItem("Salir");
        salirMenuItem.addActionListener(e -> {
            empresa.guardar(); // Guardar datos al salir
            System.exit(0);
        });
        archivoMenu.add(salirMenuItem);

        // Listener para guardar datos al cerrar la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                empresa.guardar(); // Guardar datos al cerrar la ventana
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FPrimera frame = new FPrimera();
            frame.setVisible(true);
        });
    }
}