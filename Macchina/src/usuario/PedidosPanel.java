package usuario;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import negocio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PedidosPanel extends JPanel {
	 private static final long serialVersionUID = 1L;

	    private Empresa empresa;
	    private JTable table;
	    private PedidosTableModel tableModel;

	    public PedidosPanel(Empresa empresa) {
	        this.empresa = empresa;
	        setLayout(new BorderLayout());

	        tableModel = new PedidosTableModel();
	        table = new JTable(tableModel);
	        actualizarTabla();

	        add(new JScrollPane(table), BorderLayout.CENTER);

	        JPanel buttonPanel = new JPanel();
	        JButton addButton = new JButton("Agregar");
	        JButton editButton = new JButton("Modificar");
	        JButton deleteButton = new JButton("Eliminar");
	        JButton convertToVentaButton = new JButton("Convertir a Venta");

	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                PedidoDialog dialog = new PedidoDialog(null, empresa, null);
	                dialog.setVisible(true);
	                actualizarTabla();
	            }
	        });

	        editButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) {
	                    Pedido pedido = tableModel.getPedidoAt(selectedRow);
	                    PedidoDialog dialog = new PedidoDialog(null, empresa, pedido);
	                    dialog.setVisible(true);
	                    actualizarTabla();
	                } else {
	                    JOptionPane.showMessageDialog(PedidosPanel.this, "Por favor, seleccione un pedido para modificar.");
	                }
	            }
	        });

	        deleteButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) {
	                    Pedido pedido = tableModel.getPedidoAt(selectedRow);
	                    empresa.cancelarPedido(pedido.getId_Pedido());
	                    actualizarTabla();
	                } else {
	                    JOptionPane.showMessageDialog(PedidosPanel.this, "Por favor, seleccione un pedido para eliminar.");
	                }
	            }
	        });

	        convertToVentaButton.addActionListener(new ActionListener() {
	        	 @Override
	             public void actionPerformed(ActionEvent e) {
	                 int selectedRow = table.getSelectedRow();
	                 if (selectedRow != -1) {
	                     Pedido pedido = tableModel.getPedidoAt(selectedRow);
	                     PedidoDialog dialog = new PedidoDialog(null, empresa, pedido);
	                     dialog.setVisible(true);
	                     if (dialog.isVentaConfirmada()) {
	                         FormaDePago formaDePago = dialog.getFormaDePagoSeleccionada();
	                         Venta venta = Venta.realizarVentaPedido(empresa.generarIdVenta(), pedido, formaDePago);
	                         empresa.agregarVenta(venta);
	                         empresa.cancelarPedido(pedido.getId_Pedido());
	                         actualizarTabla();
	                     }
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

	    private void actualizarTabla() {
	        List<Pedido> pedidos = empresa.listarPedidos();
	        tableModel.setPedidos(pedidos);
	        tableModel.fireTableDataChanged();
	    }
	}