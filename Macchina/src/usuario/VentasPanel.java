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
        buttonPanel.add(agregarButton);

        JButton convertirPedidoButton = new JButton("Convertir Pedido a Venta");
        convertirPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ventasTable.getSelectedRow();
                if (selectedRow != -1) {
                    Venta venta = ventasTableModel.getVentaAt(selectedRow);
                    if (venta.getPedido() != null) {
                        empresa.convertirPedidoAVenta(venta.getId());
                        ventasTableModel.setVentas(empresa.listarVentas());
                        ventasTableModel.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(VentasPanel.this, "La venta seleccionada no tiene un pedido asociado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(VentasPanel.this, "Seleccione una venta para convertir.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(convertirPedidoButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getVentasTable() {
        return ventasTable;
    }
}