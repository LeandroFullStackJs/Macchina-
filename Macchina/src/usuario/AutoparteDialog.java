package usuario;

import java.awt.EventQueue;

import javax.swing.JDialog;
import negocio.Autoparte;
import negocio.Empresa;
import negocio.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AutoparteDialog extends JDialog {

	  private static final long serialVersionUID = 1L;

	    private JTextField denominacionField;
	    private JTextField descripcionField;
	    private JTextField categoriaField;
	    private JTextField precioField;
	    private JTextField enlaceField;
	    private JTextField stockField;
	    private JTextField stockMinimoField;
	    private Autoparte autoparte;
	    private Empresa empresa;
	    private JTextField idField ;
	    private JTextField marcaField ;
	    private JTextField modeloField;
	    
	    public AutoparteDialog(Empresa empresa, Autoparte autoparte) {
	        this.empresa = empresa;
	        this.autoparte = autoparte;

	        setTitle(autoparte == null ? "Agregar Autoparte" : "Modificar Autoparte");
	        setSize(442, 419);
	        getContentPane().setLayout(new GridLayout(11, 2));
	        
	        idField = new JTextField();
	        denominacionField = new JTextField();
	        descripcionField = new JTextField();
	        categoriaField = new JTextField();
	        precioField = new JTextField();
	        enlaceField = new JTextField();
	        stockField = new JTextField();
	        stockMinimoField = new JTextField();
	        marcaField = new JTextField();
	        modeloField = new JTextField();
	     
	        
	        if (autoparte != null) {
	        	idField.setText(String.valueOf(autoparte.getId()));
	            denominacionField.setText(autoparte.getDenominacion());
	            descripcionField.setText(autoparte.getDescripcion());
	            categoriaField.setText(String.valueOf(autoparte.getCategoria()));
	            precioField.setText(String.valueOf(autoparte.getPrecio_unitario()));
	            enlaceField.setText(autoparte.getEnlace());
	            stockField.setText(String.valueOf(autoparte.getStock()));
	            stockMinimoField.setText(String.valueOf(autoparte.getStock_minimo()));
	            marcaField.setText(autoparte.getVehiculo().getMarca());
	            modeloField.setText(autoparte.getVehiculo().getModelo());
	        }
	        getContentPane().add(new JLabel("Id:"));
	        getContentPane().add(idField);
	        getContentPane().add(new JLabel("Denominación:"));
	        getContentPane().add(denominacionField);
	        getContentPane().add(new JLabel("Descripción:"));
	        getContentPane().add(descripcionField);
	        getContentPane().add(new JLabel("Categoría:"));
	        getContentPane().add(categoriaField);
	        getContentPane().add(new JLabel("Precio:"));
	        getContentPane().add(precioField);
	        getContentPane().add(new JLabel("Enlace:"));
	        getContentPane().add(enlaceField);
	        getContentPane().add(new JLabel("Stock:"));
	        getContentPane().add(stockField);
	        getContentPane().add(new JLabel("Stock Mínimo:"));
	        getContentPane().add(stockMinimoField);
	        getContentPane().add(new JLabel("Marca Vehiculo:"));
	        getContentPane().add(marcaField);
	        getContentPane().add(new JLabel("Modelo Vehiculo:"));
	        getContentPane().add(modeloField);

	        JButton saveButton = new JButton("Guardar");
	        saveButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    int id = Integer.parseInt(idField.getText()); // Ingresar el ID desde la interfaz
	                    String denominacion = denominacionField.getText();
	                    String descripcion = descripcionField.getText();
	                    int categoria = Integer.parseInt(categoriaField.getText()); // Suponiendo que la categoría es un entero
	                    Vehiculo vehiculo = obtenerVehiculoDesdeInterfaz(); // Método para obtener el vehículo desde la interfaz
	                    double precio = Double.parseDouble(precioField.getText());
	                    String enlace = enlaceField.getText();
	                    int stock = Integer.parseInt(stockField.getText());
	                    int stockMinimo = Integer.parseInt(stockMinimoField.getText());

	                    Autoparte nuevaAutoparte = new Autoparte(id, denominacion, descripcion, categoria, 
	                                                             vehiculo, precio, enlace, stock, stockMinimo);

	                    if (autoparte == null) {
	                        // Agregar nueva autoparte
	                        empresa.agregarAutoparte(nuevaAutoparte);
	                    } else {
	                        // Actualizar autoparte existente
	                        empresa.modificarAutoparte(id, nuevaAutoparte);
	                    }

	                    dispose(); // Cierra el diálogo
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos para el ID, categoría, precio, stock y stock mínimo.");
	                }
	            }
	        });

	        getContentPane().add(saveButton);
	    }
	    
	    private Vehiculo obtenerVehiculoDesdeInterfaz() {
	        String marca = marcaField.getText();
	        String modelo = modeloField.getText();
	        return new Vehiculo(marca, modelo); // Constructor de la clase Vehiculo
	    }
	}
