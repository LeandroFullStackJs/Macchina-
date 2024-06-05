package usuario;

import negocio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteDialog extends JDialog {

	  private static final long serialVersionUID = 1L;

	    private Empresa empresa;
	    private Cliente cliente;
	    private JTextField idField;
	    private JTextField nombreField;
	    private JTextField apellidoField;
	    private JTextField dniField;
	    private JTextField direccionField;
	    private JTextField telefonoField;
	    private JTextField localidadField;
	    private JTextField provinciaField;
	    private JTextField emailField;

	    private JButton guardarButton;

	    public ClienteDialog(Frame owner, Empresa empresa, Cliente cliente) {
	        super(owner, true);
	        this.empresa = empresa;
	        this.cliente = cliente;

	        setTitle(cliente == null ? "Agregar Cliente" : "Modificar Cliente");
	        setSize(400, 400);
	        setLayout(new GridLayout(10, 2));

	        add(new JLabel("ID:"));
	        idField = new JTextField();
	        add(idField);

	        add(new JLabel("Nombre:"));
	        nombreField = new JTextField();
	        add(nombreField);

	        add(new JLabel("Apellido:"));
	        apellidoField = new JTextField();
	        add(apellidoField);

	        add(new JLabel("DNI:"));
	        dniField = new JTextField();
	        add(dniField);

	        add(new JLabel("Dirección:"));
	        direccionField = new JTextField();
	        add(direccionField);

	        add(new JLabel("Teléfono:"));
	        telefonoField = new JTextField();
	        add(telefonoField);

	        add(new JLabel("Localidad:"));
	        localidadField = new JTextField();
	        add(localidadField);

	        add(new JLabel("Provincia:"));
	        provinciaField = new JTextField();
	        add(provinciaField);

	        add(new JLabel("Email:"));
	        emailField = new JTextField();
	        add(emailField);

	        guardarButton = new JButton(cliente == null ? "Agregar" : "Modificar");
	        add(guardarButton);

	        if (cliente != null) {
	            cargarDatosCliente(cliente);
	        }

	        guardarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (cliente == null) {
	                    agregarCliente();
	                } else {
	                    modificarCliente();
	                }
	            }
	        });

	        setLocationRelativeTo(owner);
	    }

	    private void cargarDatosCliente(Cliente cliente) {
	        idField.setText(String.valueOf(cliente.getId()));
	        nombreField.setText(cliente.getNombre());
	        apellidoField.setText(cliente.getApellido());
	        dniField.setText(cliente.getDni());
	        direccionField.setText(cliente.getDireccion());
	        telefonoField.setText(cliente.getTelefono());
	        localidadField.setText(cliente.getLocalidad());
	        provinciaField.setText(cliente.getProvincia());
	        emailField.setText(cliente.getEmail());

	        idField.setEditable(false);  // ID no editable en modificación
	    }

	    private void agregarCliente() {
	        try {
	            int id = Integer.parseInt(idField.getText().trim());
	            String nombre = nombreField.getText().trim();
	            String apellido = apellidoField.getText().trim();
	            String dni = dniField.getText().trim();
	            String direccion = direccionField.getText().trim();
	            String telefono = telefonoField.getText().trim();
	            String localidad = localidadField.getText().trim();
	            String provincia = provinciaField.getText().trim();
	            String email = emailField.getText().trim();

	            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || email.isEmpty()) {
	                throw new IllegalArgumentException("Los campos nombre, apellido, dni y email son obligatorios.");
	            }

	            Cliente nuevoCliente = new Cliente(id, nombre, apellido, dni, direccion, telefono, localidad, provincia, email);
	            empresa.agregarCliente(nuevoCliente);

	            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            dispose();
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
	        } catch (IllegalArgumentException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private void modificarCliente() {
	        try {
	            int id = Integer.parseInt(idField.getText().trim());
	            String nombre = nombreField.getText().trim();
	            String apellido = apellidoField.getText().trim();
	            String dni = dniField.getText().trim();
	            String direccion = direccionField.getText().trim();
	            String telefono = telefonoField.getText().trim();
	            String localidad = localidadField.getText().trim();
	            String provincia = provinciaField.getText().trim();
	            String email = emailField.getText().trim();

	            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || email.isEmpty()) {
	                throw new IllegalArgumentException("Los campos nombre, apellido, dni y email son obligatorios.");
	            }

	            Cliente clienteModificado = new Cliente(id, nombre, apellido, dni, direccion, telefono, localidad, provincia, email);
	            empresa.modificarCliente(id, clienteModificado);

	            JOptionPane.showMessageDialog(this, "Cliente modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            dispose();
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
	        } catch (IllegalArgumentException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}