package usuario;

import negocio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PedidoDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private JTextField clienteField;
    private JTextField fechaField;
    private JTextField autopartesField;
    private Empresa empresa;
    private Pedido pedido;
    private boolean ventaConfirmada;
    private JComboBox<String> metodoPagoComboBox;
    private JComboBox<Integer> cuotasComboBox;
    private JButton guardarButton;
    private JButton convertirVentaButton;
    private Runnable updateVentasCallback;

    public PedidoDialog(Frame owner, Empresa empresa, Pedido pedido, Runnable updateVentasCallback) {
        super(owner, "Pedido", true);
        this.empresa = empresa;
        this.pedido = pedido;
        this.ventaConfirmada = false;
        this.updateVentasCallback = updateVentasCallback;

        setLayout(new GridLayout(6, 2));

        add(new JLabel("Cliente ID:"));
        clienteField = new JTextField();
        add(clienteField);

        add(new JLabel("Fecha (yyyy-MM-dd):"));
        fechaField = new JTextField();
        add(fechaField);

        add(new JLabel("Autopartes (separadas por comas):"));
        autopartesField = new JTextField();
        add(autopartesField);

        add(new JLabel("Método de Pago:"));
        metodoPagoComboBox = new JComboBox<>(new String[]{"Efectivo", "Débito", "Crédito"});
        metodoPagoComboBox.addActionListener(e -> actualizarCuotasComboBox());
        add(metodoPagoComboBox);

        add(new JLabel("Cuotas:"));
        cuotasComboBox = new JComboBox<>(new Integer[]{2, 3, 6});
        cuotasComboBox.setEnabled(false);
        add(cuotasComboBox);

     // Si se pasa un pedido, rellena los campos con los datos del pedido
        
        if (pedido != null) {
            clienteField.setText(String.valueOf(pedido.getCliente().getId()));
            fechaField.setText(new SimpleDateFormat("yyyy-MM-dd").format(pedido.getFecha()));
            autopartesField.setText(pedido.getAutopartesNombres());
        }

        // Boton para guardar el pedido
        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPedido();
            }
        });
        add(guardarButton);
        
        // Boton para convertir el pedido en una venta
        convertirVentaButton = new JButton("Convertir a Venta");
        convertirVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirPedidoAVenta();
            }
        });
        add(convertirVentaButton);

        setSize(400, 300);
        setLocationRelativeTo(owner);
    }

   
    private void actualizarCuotasComboBox() { // Método para habilitar o deshabilitar el ComboBox de cuotas basado en el metodo de pago seleccionado
        String metodoPago = (String) metodoPagoComboBox.getSelectedItem();
        cuotasComboBox.setEnabled("Crédito".equals(metodoPago)); // Habilita cuotas solo si el metodo de pago es credito
    }

    private void guardarPedido() {     // Metodo para guardar el pedido
        try {
            int clienteId = Integer.parseInt(clienteField.getText());
            Cliente cliente = empresa.buscarCliente(clienteId);
            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaField.getText());
            List<Autoparte> autopartes = empresa.buscarAutopartesPorNombres(autopartesField.getText());

            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
                return;
            }

            if (autopartes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Autopartes no encontradas.");
                return;
            }

            if (pedido == null) { // Si el pedido es nuevo, crea un nuevo pedido y lo agrega a la empresa
                Pedido nuevoPedido = new Pedido(0, fecha, cliente);
                for (Autoparte autoparte : autopartes) {
                    nuevoPedido.agregarDetalle(new DetallePedido(0, autoparte, autoparte.getPrecio_unitario(), 1)); // Cantidad 1 por defecto
                }
                empresa.agregarPedido(nuevoPedido);
            } else { // Si el pedido ya existe, actualiza sus datos
                pedido.setCliente(cliente);
                pedido.setFecha(fecha);
                pedido.getDetalles().clear();
                for (Autoparte autoparte : autopartes) {
                    pedido.agregarDetalle(new DetallePedido(0, autoparte, autoparte.getPrecio_unitario(), 1)); // Cantidad 1 por defecto
                }
                empresa.modificarPedido(pedido);
            }

            dispose(); // Cierra el diálogo
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos del pedido: " + ex.getMessage());
        }
    }

    // PONGO EL METODO convertirPedidoAVenta aca porque uso datos que estan directamente disponibles en PedidoDialog , como el pedido actual 
    //y los metodos de pago seleccionados . y el dialog controla el flujo del proceso de conversion de pedido a venta y la actualizacion de la interfaz 
    //y manejo de excepsiones , por eso hace el codigo mas comprensible y Encapsula la logica de la interfaz de usuario .
    
    private void convertirPedidoAVenta() {
        try {
            String metodoPago = (String) metodoPagoComboBox.getSelectedItem(); // Obtiene el metodo de pago seleccionado
            FormaDePago formaDePago;

            if ("Efectivo".equals(metodoPago)) {
                formaDePago = new PagoEfectivo();
            } else if ("Débito".equals(metodoPago)) {
                formaDePago = new PagoDebito();
            } else {
                int cuotas = (int) cuotasComboBox.getSelectedItem();
                formaDePago = new PagoCredito(cuotas);
            }

            Venta venta = Venta.realizarVentaPedido(empresa.generarIdVenta(), pedido, formaDePago);
            empresa.agregarVenta(venta);
            empresa.cancelarPedido(pedido.getId_Pedido());

            ventaConfirmada = true;
            if (updateVentasCallback != null) {
                updateVentasCallback.run(); // Ejecuta el callback para actualizar la lista de ventas
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al convertir el pedido en venta: " + e.getMessage());
        }
    }

    public boolean isVentaConfirmada() { // No la estoy utilizando 
        return ventaConfirmada;
    }

    public FormaDePago getFormaDePagoSeleccionada() { // Con este metodo obtengo la forma de pago seleccionada 
        String metodoPago = (String) metodoPagoComboBox.getSelectedItem();
        if ("Efectivo".equals(metodoPago)) {
            return new PagoEfectivo();
        } else if ("Débito".equals(metodoPago)) {
            return new PagoDebito();
        } else {
            int cuotas = (int) cuotasComboBox.getSelectedItem();
            return new PagoCredito(cuotas);
        }
    }
}
