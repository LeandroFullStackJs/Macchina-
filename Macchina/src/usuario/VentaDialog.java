package usuario;

import negocio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class VentaDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private Empresa empresa;
    private Venta venta;

    private JComboBox<Cliente> clienteComboBox;
    private JTextField totalField;
    private JComboBox<String> metodoPagoComboBox;
    private JComboBox<Integer> cuotasComboBox;
    private DefaultListModel<Autoparte> autopartesListModel;
    private JList<Autoparte> autopartesList;

    private JButton guardarButton;

    public VentaDialog(Frame owner, Empresa empresa, Venta venta) {
        super(owner, true);
        this.empresa = empresa;
        this.venta = venta;

        setTitle(venta == null ? "Nueva Venta" : "Modificar Venta");
        setSize(400, 400);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Cliente:"));
        clienteComboBox = new JComboBox<>();
        List<Cliente> clientes = empresa.listarClientes();
        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente);
        }
        add(clienteComboBox);

        add(new JLabel("Total:"));
        totalField = new JTextField();
        totalField.setEditable(false); // Hacer el campo de total no editable
        add(totalField);

        add(new JLabel("Método de Pago:"));
        metodoPagoComboBox = new JComboBox<>(new String[]{"Efectivo", "Débito", "Crédito"});
        metodoPagoComboBox.addActionListener(e -> {
            actualizarCuotasComboBox();
            actualizarTotal();
        });
        add(metodoPagoComboBox);

        add(new JLabel("Cuotas:"));
        cuotasComboBox = new JComboBox<>(new Integer[]{2, 3, 6});
        cuotasComboBox.setEnabled(false);
        cuotasComboBox.addActionListener(e -> actualizarTotal());
        add(cuotasComboBox);

        add(new JLabel("Autopartes:"));
        autopartesListModel = new DefaultListModel<>();
        autopartesList = new JList<>(autopartesListModel);
        List<Autoparte> autopartes = empresa.listarAutopartes();
        for (Autoparte autoparte : autopartes) {
            autopartesListModel.addElement(autoparte);
        }
        autopartesList.addListSelectionListener(e -> actualizarTotal());
        add(new JScrollPane(autopartesList));

        guardarButton = new JButton(venta == null ? "Agregar" : "Modificar");
        guardarButton.addActionListener(e -> {
            if (venta == null) {
                agregarVenta();
            } else {
                modificarVenta();
            }
        });
        add(guardarButton);

        if (venta != null) {
            cargarDatosVenta(venta);
        }

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

            List<Autoparte> autopartesSeleccionadas = autopartesList.getSelectedValuesList();
            ArrayList<DetalleVenta> detalles = new ArrayList<>();
            for (Autoparte autoparte : autopartesSeleccionadas) {
                DetalleVenta detalle = new DetalleVenta(empresa.generarIdDetalleVenta(), autoparte, autoparte.getPrecio_unitario(), 1);
                detalles.add(detalle);
                empresa.disminuirStock(detalle); // Disminuir el stock de cada autoparte
            }

            venta = new Venta(empresa.generarIdVenta(), new Date(), formaDePago, cliente);
            venta.setDetalles(detalles);
            // Llama a calcularMontoFinal() después de agregar los detalles de la venta
            venta.calcularMontoFinal(); 
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

    private void actualizarTotal() {
        double total = 0.0;
        List<Autoparte> autopartesSeleccionadas = autopartesList.getSelectedValuesList();
        for (Autoparte autoparte : autopartesSeleccionadas) {
            total += autoparte.getPrecio_unitario();
        }

        String metodoPago = (String) metodoPagoComboBox.getSelectedItem();
        if ("Crédito".equals(metodoPago) && cuotasComboBox.getSelectedItem() != null) {
            int cuotas = (int) cuotasComboBox.getSelectedItem();
            PagoCredito pagoCredito = new PagoCredito(cuotas);
            total = pagoCredito.calcularMontoFinal(total);
        }

        totalField.setText(String.valueOf(total));
        System.out.println("Total actualizado: " + total); // Agregar mensaje de depuración
    }
}
