package usuario;

import javax.swing.JPanel;
import java.util.List;
import negocio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidosPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Empresa empresa;
    private JTable table;
    private PedidosTableModel tableModel;
    private VentasPanel ventasPanel;

    public PedidosPanel(Empresa empresa, VentasPanel ventasPanel) {
        this.empresa = empresa;
        this.ventasPanel = ventasPanel;
        setLayout(new BorderLayout());

        tableModel = new PedidosTableModel();
        table = new JTable(tableModel);
        actualizarTabla();  // Actualiza la tabla con los datos actuales 

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Agregar");
        JButton editButton = new JButton("Modificar");
        JButton deleteButton = new JButton("Eliminar");
        JButton convertToVentaButton = new JButton("Convertir a Venta");

     // Accion del boton Agregar 
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoDialog dialog = new PedidoDialog(null, empresa, null, null); // Crea un diálogo para agregar un nuevo pedido 
                dialog.setVisible(true);
                actualizarTabla(); // Actualiza la tabla después de agregar 
            }
        });

     // Accion del boton Modificar 
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // Obtiene la fila seleccionada 
                if (selectedRow != -1) {
                    Pedido pedido = tableModel.getPedidoAt(selectedRow);
                    PedidoDialog dialog = new PedidoDialog(null, empresa, pedido, null); // Crea un diálogo para modificar el pedido
                    dialog.setVisible(true);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(PedidosPanel.this, "Por favor, seleccione un pedido para modificar.");
                }
            }
        });
        
        // Accion del boton Eliminar 
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Pedido pedido = tableModel.getPedidoAt(selectedRow);
                    empresa.cancelarPedido(pedido.getId_Pedido()); // Cancela (elimina) el pedido de la empresa
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(PedidosPanel.this, "Por favor, seleccione un pedido para eliminar.");
                }
            }
        });

     // Accion del boton Convertir a Venta 
        convertToVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Pedido pedido = tableModel.getPedidoAt(selectedRow);
                    PedidoDialog dialog = new PedidoDialog(null, empresa, pedido, () -> ventasPanel.actualizarTabla()); // hace el updateCallback a la tabla de ventasPanel . 
                    dialog.setVisible(true);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(PedidosPanel.this, "Por favor, seleccione un pedido para convertir a venta.");
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(convertToVentaButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    
    private void actualizarTabla() { // Método para actualizar la tabla con los pedidos actuales de la empresa 
        List<Pedido> pedidos = empresa.listarPedidos();
        tableModel.setPedidos(pedidos);
        tableModel.fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado y debe actualizarse
    }
}
