package usuario;
import negocio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class FPrimera extends JFrame {

	private Empresa empresa;
    private JTextArea displayArea;

    public FPrimera(Empresa empresa) {
        this.empresa = empresa;
        setTitle("Gestión de Tutta la Macchina");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 2));

        // Botones
        JButton btnGestionClientes = new JButton("Gestionar Clientes");
        JButton btnGestionVentas = new JButton("Gestionar Ventas");
        JButton btnGestionPedidos = new JButton("Gestionar Pedidos");
        JButton btnGestionAutopartes = new JButton("Gestionar Autopartes");

        controlPanel.add(btnGestionClientes);
        controlPanel.add(btnGestionVentas);
        controlPanel.add(btnGestionPedidos);
        controlPanel.add(btnGestionAutopartes);

        add(controlPanel, BorderLayout.SOUTH);

        // Action Listeners
        btnGestionClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionarClientes();
            }
        });

        btnGestionVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionarVentas();
            }
        });

        btnGestionPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionarPedidos();
            }
        });

        btnGestionAutopartes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionarAutopartes();
            }
        });
    }

    private void gestionarClientes() {
        // Implementación de la gestión de clientes
        String[] options = {"Agregar", "Listar", "Buscar", "Modificar", "Eliminar", "Volver"};
        int choice = JOptionPane.showOptionDialog(this, "Seleccione una opción", "Gestión de Clientes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                // Agregar Cliente
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                String direccion = JOptionPane.showInputDialog("Ingrese la dirección del cliente:");
                String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del cliente:");
                String localidad = JOptionPane.showInputDialog("Ingrese la localidad del cliente:");
                String provincia = JOptionPane.showInputDialog("Ingrese la provincia del cliente:");
                String email = JOptionPane.showInputDialog("Ingrese el email del cliente:");
                Cliente cliente = new Cliente(empresa.listarClientes().size() + 1, nombre, direccion, telefono, localidad, provincia, email);
                empresa.agregarCliente(cliente);
                displayArea.setText("Cliente agregado: " + cliente);
                break;
            case 1:
                // Listar Clientes
                displayArea.setText("Clientes:\n" + empresa.listarClientes());
                break;
            case 2:
                // Buscar Cliente
                int idBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente a buscar:"));
                Cliente clienteEncontrado = empresa.buscarCliente(idBuscar);
                if (clienteEncontrado != null) {
                    displayArea.setText("Cliente encontrado: " + clienteEncontrado);
                } else {
                    displayArea.setText("Cliente no encontrado.");
                }
                break;
            case 3:
                // Modificar Cliente
                int idModificar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente a modificar:"));
                Cliente clienteModificar = empresa.buscarCliente(idModificar);
                if (clienteModificar != null) {
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del cliente:", clienteModificar.getNombre());
                    String nuevaDireccion = JOptionPane.showInputDialog("Ingrese la nueva dirección del cliente:", clienteModificar.getDireccion());
                    String nuevoTelefono = JOptionPane.showInputDialog("Ingrese el nuevo teléfono del cliente:", clienteModificar.getTelefono());
                    String nuevaLocalidad = JOptionPane.showInputDialog("Ingrese la nueva localidad del cliente:", clienteModificar.getLocalidad());
                    String nuevaProvincia = JOptionPane.showInputDialog("Ingrese la nueva provincia del cliente:", clienteModificar.getProvincia());
                    String nuevoEmail = JOptionPane.showInputDialog("Ingrese el nuevo email del cliente:", clienteModificar.getEmail());
                    Cliente clienteModificado = new Cliente(idModificar, nuevoNombre, nuevaDireccion, nuevoTelefono, nuevaLocalidad, nuevaProvincia, nuevoEmail);
                    empresa.modificarCliente(idModificar, clienteModificado);
                    displayArea.setText("Cliente modificado: " + clienteModificado);
                } else {
                    displayArea.setText("Cliente no encontrado.");
                }
                break;
            case 4:
                // Eliminar Cliente
                int idEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente a eliminar:"));
                empresa.eliminarCliente(idEliminar);
                displayArea.setText("Cliente eliminado.");
                break;
            case 5:
                // Volver
                displayArea.setText("");
                break;
        }
    }

    private void gestionarVentas() {
        // Implementación de la gestión de ventas
        String[] options = {"Registrar Venta Directa", "Registrar Venta desde Pedido", "Listar Ventas", "Buscar Venta", "Volver"};
        int choice = JOptionPane.showOptionDialog(this, "Seleccione una opción", "Gestión de Ventas",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                // Registrar Venta Directa
                int idVentaDirecta = empresa.listarVentas().size() + 1;
                int idClienteDirecta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente:"));
                Cliente clienteDirecta = empresa.buscarCliente(idClienteDirecta);
                if (clienteDirecta != null) {
                    String[] mediosPago = {"Débito", "Efectivo", "Crédito"};
                    int medioPagoSeleccionado = JOptionPane.showOptionDialog(this, "Seleccione un medio de pago", "Medio de Pago",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, mediosPago, mediosPago[0]);

                    FormaDePago medioDePagoDirecta = null;
                    if (medioPagoSeleccionado == 0) {
                        medioDePagoDirecta = new PagoDebito();
                    } else if (medioPagoSeleccionado == 1) {
                        medioDePagoDirecta = new PagoEfectivo();
                    } else if (medioPagoSeleccionado == 2) {
                        int cuotas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de cuotas (2, 3, 6):"));
                        medioDePagoDirecta = new PagoCredito(cuotas);
                    }

                    Venta ventaDirecta = new Venta(idVentaDirecta, clienteDirecta.getNombre(), medioDePagoDirecta);
                    empresa.agregarVenta(ventaDirecta);
                    displayArea.setText("Venta directa registrada: " + ventaDirecta);
                } else {
                    displayArea.setText("Cliente no encontrado.");
                }
                break;
            case 1:
                // Registrar Venta desde Pedido
                int idVentaPedido = empresa.listarVentas().size() + 1;
                int idPedido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pedido a convertir en venta:"));
                Pedido pedido = empresa.buscarPedido(idPedido);
                if (pedido != null) {
                    String nombreClientePedido = pedido.getCliente().getNombre();
                    String[] mediosPagoPedido = {"Débito", "Efectivo", "Crédito"};
                    int medioPagoSeleccionadoPedido = JOptionPane.showOptionDialog(this, "Seleccione un medio de pago", "Medio de Pago",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, mediosPagoPedido, mediosPagoPedido[0]);

                    FormaDePago medioDePagoPedido = null;
                    if (medioPagoSeleccionadoPedido == 0) {
                        medioDePagoPedido = new PagoDebito();
                    } else if (medioPagoSeleccionadoPedido == 1) {
                        medioDePagoPedido = new PagoEfectivo();
                    } else if (medioPagoSeleccionadoPedido == 2) {
                        int cuotas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de cuotas (2, 3, 6):"));
                        medioDePagoPedido = new PagoCredito(cuotas);
                    }

                    Venta ventaPedido = new Venta(idVentaPedido, nombreClientePedido, medioDePagoPedido);
                    empresa.agregarVenta(ventaPedido);
                    empresa.cancelarPedido(idPedido); // Eliminar el pedido convertido en venta
                    displayArea.setText("Venta registrada desde pedido: " + ventaPedido);
                } else {
                    displayArea.setText("Pedido no encontrado.");
                }
                break;
            case 2:
                // Listar Ventas
                displayArea.setText("Ventas:\n" + empresa.listarVentas());
                break;
            case 3:
                // Buscar Venta
                int idBuscarVenta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la venta a buscar:"));
                Venta ventaEncontrada = empresa.buscarVenta(idBuscarVenta);
                if (ventaEncontrada != null) {
                    displayArea.setText("Venta encontrada: " + ventaEncontrada);
                } else {
                    displayArea.setText("Venta no encontrada.");
                }
                break;
            case 4:
                // Volver
                displayArea.setText("");
                break;
        }
    }

    private void gestionarPedidos() {
        // Implementación de la gestión de pedidos
        String[] options = {"Agregar", "Listar", "Buscar", "Modificar", "Cancelar", "Volver"};
        int choice = JOptionPane.showOptionDialog(this, "Seleccione una opción", "Gestión de Pedidos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                // Agregar Pedido
                int idPedido = empresa.listarPedidos().size() + 1;
                int idClientePedido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente:"));
                Cliente clientePedido = empresa.buscarCliente(idClientePedido);
                if (clientePedido != null) {
                    LocalDate fechaPedido = LocalDate.now();
                    double montoTotal = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto total del pedido:"));
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de items del pedido:"));
                    boolean cancelado = false;

                    Pedido pedido = new Pedido(idPedido, fechaPedido.toString(), montoTotal, cantidad, clientePedido, cancelado);
                    empresa.agregarPedido(pedido);
                    displayArea.setText("Pedido agregado: " + pedido);
                } else {
                    displayArea.setText("Cliente no encontrado.");
                }
                break;
            case 1:
                // Listar Pedidos
                displayArea.setText("Pedidos:\n" + empresa.listarPedidos());
                break;
            case 2:
                // Buscar Pedido
                int idBuscarPedido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pedido a buscar:"));
                Pedido pedidoEncontrado = empresa.buscarPedido(idBuscarPedido);
                if (pedidoEncontrado != null) {
                    displayArea.setText("Pedido encontrado: " + pedidoEncontrado);
                } else {
                    displayArea.setText("Pedido no encontrado.");
                }
                break;
            case 3:
                // Modificar Pedido
                int idModificarPedido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pedido a modificar:"));
                Pedido pedidoModificar = empresa.buscarPedido(idModificarPedido);
                if (pedidoModificar != null) {
                    String nuevoNombreCliente = JOptionPane.showInputDialog("Ingrese el nuevo nombre del cliente:", pedidoModificar.getCliente().getNombre());
                    Cliente nuevoClientePedido = empresa.buscarCliente(Integer.parseInt(nuevoNombreCliente));
                    if (nuevoClientePedido != null) {
                        pedidoModificar.setCliente(nuevoClientePedido);
                        empresa.modificarPedido(idModificarPedido, pedidoModificar);
                        displayArea.setText("Pedido modificado: " + pedidoModificar);
                    } else {
                        displayArea.setText("Cliente no encontrado.");
                    }
                } else {
                    displayArea.setText("Pedido no encontrado.");
                }
                break;
            case 4:
                // Cancelar Pedido
                int idCancelarPedido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pedido a cancelar:"));
                empresa.cancelarPedido(idCancelarPedido);
                displayArea.setText("Pedido cancelado.");
                break;
            case 5:
                // Volver
                displayArea.setText("");
                break;
        }
    }

    private void gestionarAutopartes() {
        // Implementación de la gestión de autopartes
        String[] options = {"Agregar", "Listar", "Buscar", "Modificar", "Eliminar", "Volver"};
        int choice = JOptionPane.showOptionDialog(this, "Seleccione una opción", "Gestión de Autopartes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                // Agregar Autoparte
                String nombreAutoparte = JOptionPane.showInputDialog("Ingrese la denominación de la autoparte:");
                String descripcion = JOptionPane.showInputDialog("Ingrese la descripción de la autoparte:");
                int categoria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la categoría de la autoparte:"));
                String vehiculoMarca = JOptionPane.showInputDialog("Ingrese la marca del vehículo:");
                String vehiculoModelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo:");
                Vehiculo vehiculo = new Vehiculo(vehiculoMarca, vehiculoModelo);
                double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de la autoparte:"));
                String enlace = JOptionPane.showInputDialog("Ingrese el enlace de la autoparte:");
                int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock de la autoparte:"));
                int stockMinimo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock mínimo de la autoparte:"));

                Autoparte autoparte = new Autoparte(empresa.listarAutopartes().size() + 1, nombreAutoparte, descripcion, categoria, vehiculo, precio, enlace, stock, stockMinimo);
                empresa.agregarAutoparte(autoparte);
                displayArea.setText("Autoparte agregada: " + autoparte);
                break;
            case 1:
                // Listar Autopartes
                displayArea.setText("Autopartes:\n" + empresa.listarAutopartes());
                break;
            case 2:
                // Buscar Autoparte
                int idBuscarAutoparte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la autoparte a buscar:"));
                Autoparte autoparteEncontrada = empresa.buscarAutoparte(idBuscarAutoparte);
                if (autoparteEncontrada != null) {
                    displayArea.setText("Autoparte encontrada: " + autoparteEncontrada);
                } else {
                    displayArea.setText("Autoparte no encontrada.");
                }
                break;
            case 3:
                // Modificar Autoparte
                int idModificarAutoparte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la autoparte a modificar:"));
                Autoparte autoparteModificar = empresa.buscarAutoparte(idModificarAutoparte);
                if (autoparteModificar != null) {
                    String nuevoNombreAutoparte = JOptionPane.showInputDialog("Ingrese la nueva denominación de la autoparte:", autoparteModificar.getDenominacion());
                    String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción de la autoparte:", autoparteModificar.getDescripcion());
                    int nuevaCategoria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva categoría de la autoparte:", autoparteModificar.getCategoria()));
                    String nuevaMarcaVehiculo = JOptionPane.showInputDialog("Ingrese la nueva marca del vehículo:", autoparteModificar.getVehiculo().getMarca());
                    String nuevoModeloVehiculo = JOptionPane.showInputDialog("Ingrese el nuevo modelo del vehículo:", autoparteModificar.getVehiculo().getModelo());
                    Vehiculo nuevoVehiculo = new Vehiculo(nuevaMarcaVehiculo, nuevoModeloVehiculo);
                    double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio de la autoparte:", autoparteModificar.getPrecio_unitario()));
                    String nuevoEnlace = JOptionPane.showInputDialog("Ingrese el nuevo enlace de la autoparte:", autoparteModificar.getEnlace());
                    int nuevoStock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo stock de la autoparte:", autoparteModificar.getStock()));
                    int nuevoStockMinimo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo stock mínimo de la autoparte:", autoparteModificar.getStock_minimo()));

                    Autoparte autoparteModificada = new Autoparte(idModificarAutoparte, nuevoNombreAutoparte, nuevaDescripcion, nuevaCategoria, nuevoVehiculo, nuevoPrecio, nuevoEnlace, nuevoStock, nuevoStockMinimo);
                    empresa.modificarAutoparte(idModificarAutoparte, autoparteModificada);
                    displayArea.setText("Autoparte modificada: " + autoparteModificada);
                } else {
                    displayArea.setText("Autoparte no encontrada.");
                }
                break;
            case 4:
                // Eliminar Autoparte
                int idEliminarAutoparte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la autoparte a eliminar:"));
                empresa.eliminarAutoparte(idEliminarAutoparte);
                displayArea.setText("Autoparte eliminada.");
                break;
            case 5:
                // Volver
                displayArea.setText("");
                break;
        }
    }

    public static void main(String[] args) {
        Empresa empresa = new Empresa("Tutta la Macchina");
        FPrimera frame = new FPrimera(empresa);
        frame.setVisible(true);
    }

}
