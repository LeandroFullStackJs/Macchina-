package usuario;

import negocio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientesPanel extends JPanel {
	private static final long serialVersionUID = 1L;

    private Empresa empresa;
    private JTable clientesTable;
    private ClientesTableModel clientesTableModel;

    public ClientesPanel(Empresa empresa) {
        this.empresa = empresa;
        setLayout(new BorderLayout());

        clientesTableModel = new ClientesTableModel();
        clientesTableModel.setClientes(empresa.listarClientes());

        clientesTable = new JTable(clientesTableModel);
        add(new JScrollPane(clientesTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        
        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteDialog dialog = new ClienteDialog(null, empresa, null);
                dialog.setVisible(true);
                clientesTableModel.setClientes(empresa.listarClientes());
                clientesTableModel.fireTableDataChanged();
            }
        });
        buttonPanel.add(agregarButton);

        JButton modificarButton = new JButton("Modificar");
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientesTable.getSelectedRow();
                if (selectedRow != -1) {
                    Cliente cliente = clientesTableModel.getClienteAt(selectedRow);
                    ClienteDialog dialog = new ClienteDialog(null, empresa, cliente);
                    dialog.setVisible(true);
                    clientesTableModel.setClientes(empresa.listarClientes());
                    clientesTableModel.fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(ClientesPanel.this, "Seleccione un cliente para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(modificarButton);

     /*   JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientesTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(ClientesPanel.this, "¿Está seguro de que desea eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Cliente cliente = clientesTableModel.getClienteAt(selectedRow);
                        empresa.eliminarCliente(cliente.getId());
                        clientesTableModel.setClientes(empresa.listarClientes());
                        clientesTableModel.fireTableDataChanged();
                    }
                } else {
                    JOptionPane.showMessageDialog(ClientesPanel.this, "Seleccione un cliente para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(eliminarButton); */

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getClientesTable() {
        return clientesTable;
    }
}