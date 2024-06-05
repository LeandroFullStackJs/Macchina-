package usuario;
import negocio.Venta;
import negocio.Empresa;
import negocio.FormaDePago;
import negocio.PagoCredito;
import negocio.PagoDebito;
import negocio.PagoEfectivo;
import negocio.Pedido;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentasPanel extends JPanel {

	private static final long serialVersionUID = 1L;

    private Empresa empresa;
    private JTable ventasTable;
    private VentasTableModel ventasTableModel;

    public VentasPanel(Empresa empresa) {
        this.empresa = empresa;
        setLayout(new BorderLayout());

        ventasTableModel = new VentasTableModel();
        ventasTableModel.setVentas(empresa.listarVentas());

        ventasTable = new JTable(ventasTableModel);
        add(new JScrollPane(ventasTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton agregarButton = new JButton("Venta Directa");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentaDialog dialog = new VentaDialog(null, empresa, null);
                dialog.setVisible(true);
                ventasTableModel.setVentas(empresa.listarVentas());
                ventasTableModel.fireTableDataChanged();
            }
        });

        JButton eliminarButton = new JButton("Eliminar Venta");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ventasTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmado = JOptionPane.showConfirmDialog(VentasPanel.this, "¿Estás seguro de eliminar esta venta?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmado == JOptionPane.YES_OPTION) {
                        try {
                            Venta venta = ventasTableModel.getVentaAt(selectedRow);
                            empresa.eliminarVenta(venta.getId());
                            ventasTableModel.setVentas(empresa.listarVentas());
                            ventasTableModel.fireTableDataChanged();
                        } catch (IndexOutOfBoundsException ex) {
                            JOptionPane.showMessageDialog(VentasPanel.this, "Error al eliminar la venta: índice fuera de los límites.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(VentasPanel.this, "Por favor, seleccione una venta para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(eliminarButton);
        buttonPanel.add(agregarButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getVentasTable() {
        return ventasTable;
    }
}