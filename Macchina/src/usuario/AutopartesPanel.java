package usuario;
import negocio.Autoparte;
import negocio.Empresa;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AutopartesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

    private Empresa empresa;
    private JTable table;
    private AutopartesTableModel tableModel;

    public AutopartesPanel(Empresa empresa) {
        this.empresa = empresa;
        setLayout(new BorderLayout());

        tableModel = new AutopartesTableModel();
        table = new JTable(tableModel);
        actualizarTabla();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Agregar");
        JButton editButton = new JButton("Modificar");
        JButton deleteButton = new JButton("Eliminar");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AutoparteDialog dialog = new AutoparteDialog(empresa, null);
                dialog.setVisible(true);
                actualizarTabla();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Autoparte autoparte = tableModel.getAutoparteAt(selectedRow);
                    AutoparteDialog dialog = new AutoparteDialog(empresa, autoparte);
                    dialog.setVisible(true);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(AutopartesPanel.this, "Por favor, seleccione una autoparte para modificar.");
                }
                
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Autoparte autoparte = tableModel.getAutoparteAt(selectedRow);
                    empresa.eliminarAutoparte(autoparte.getId()); // ERROR eliminarAutoparte , fijarse en la implementacion en la clase EMPRESA , tiene que pasarle un id . 
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(AutopartesPanel.this, "Por favor, seleccione una autoparte para eliminar.");
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void actualizarTabla() {
        ArrayList<Autoparte> autopartes = empresa.listarAutopartes();
        tableModel.setAutopartes(autopartes);
        tableModel.fireTableDataChanged();
    }
}
