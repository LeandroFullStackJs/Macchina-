package usuario;
import negocio.*;
import java.awt.EventQueue;

import javax.swing.JDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
    private Empresa empresa;
    private Venta venta;

    private JComboBox<Cliente> clienteComboBox;
    private JTextField totalField;
    private JComboBox<String> metodoPagoComboBox;
    private JComboBox<Integer> cuotasComboBox;

    private JButton guardarButton;

    public VentaDialog(Frame owner, Empresa empresa, Venta venta) {
        super(owner, true);
        this.empresa = empresa;
        this.venta = venta;

        setTitle(venta == null ? "Nueva Venta" : "Modificar Venta");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Cliente:"));
        clienteComboBox = new JComboBox<>();
        List<Cliente> clientes = empresa.listarClientes();
        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente);
        }
        add(clienteComboBox);

        add(new JLabel("Total:"));
        totalField = new JTextField();
        add(totalField);

        add(new JLabel("Método de Pago:"));
        metodoPagoComboBox = new JComboBox<>(new String[]{"Efectivo", "Débito", "Crédito"});
        metodoPagoComboBox.addActionListener(e -> actualizarCuotasComboBox());
        add(metodoPagoComboBox);

        add(new JLabel("Cuotas:"));
        cuotasComboBox = new JComboBox<>(new Integer[]{2, 3, 6});
        cuotasComboBox.setEnabled(false);
        add(cuotasComboBox);

        guardarButton = new JButton(venta == null ? "Agregar" : "Modificar");
        add(guardarButton);

        if (venta != null) {
            cargarDatosVenta(venta);
        }

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (venta == null) {
                    agregarVenta();
                } else {
                    modificarVenta();
                }
            }
        });

        setLocationRelativeTo(owner);
    }

    private void cargarDatosVenta(Venta venta) {
        clienteComboBox.setSelectedItem(venta.getCliente());
        totalField.setText(String.valueOf(venta.getMontoFinal()));
        if (venta.getFormaDePago() instanceof PagoCredito) {
            metodoPagoComboBox.setSelectedItem("Crédito");
            PagoCredito pagoCredito = (PagoCredito) venta.getFormaDePago();
            cuotasComboBox.setSelectedItem(pagoCredito.getCuotas());
            cuotasComboBox.setEnabled(true);
        } else if (venta.getFormaDePago() instanceof PagoDebito) {
            metodoPagoComboBox.setSelectedItem("Débito");
        } else if (venta.getFormaDePago() instanceof PagoEfectivo) {
            metodoPagoComboBox.setSelectedItem("Efectivo");
        }
    }

    private void agregarVenta() {
        try {
            Cliente cliente = (Cliente) clienteComboBox.getSelectedItem();
            double total = Double.parseDouble(totalField.getText().trim());
            String metodoPago = (String) metodoPagoComboBox.getSelectedItem();
            FormaDePago formaDePago;

            if ("Efectivo".equals(metodoPago)) {
                formaDePago = new PagoEfectivo();
            } else if ("Débito".equals(metodoPago)) {
                formaDePago = new PagoDebito();
            } else {
                int cuotas = (int) cuotasComboBox.getSelectedItem();
                formaDePago = new PagoCredito(cuotas);
            }

            venta = new Venta(empresa.generarIdVenta(), new Date(), formaDePago, cliente);
            empresa.agregarVenta(venta);

            JOptionPane.showMessageDialog(this, "Venta agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El total debe ser un número.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarVenta() {
        // Implementación similar a agregarVenta, pero modificando la venta existente
    }

    private void actualizarCuotasComboBox() {
        String metodoPago = (String) metodoPagoComboBox.getSelectedItem();
        if ("Crédito".equals(metodoPago)) {
            cuotasComboBox.setEnabled(true);
        } else {
            cuotasComboBox.setEnabled(false);
        }
    }
}
