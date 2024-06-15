package usuario;

import javax.swing.*;
import negocio.Autoparte;
import negocio.Empresa;
import negocio.Vehiculo;
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
    private JTextField idField;
    private JTextField marcaField;
    private JTextField modeloField;
    private Runnable updateCallback; // Callback para actualizar la interfaz

    public AutoparteDialog(Empresa empresa, Autoparte autoparte, Runnable updateCallback) {
        this.empresa = empresa;
        this.autoparte = autoparte;
        this.updateCallback = updateCallback;

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

        if (autoparte != null) { // Agrega o modifica la autoparte en la empresa . 
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
                    int id = Integer.parseInt(idField.getText());
                    String denominacion = denominacionField.getText();
                    String descripcion = descripcionField.getText();
                    int categoria = Integer.parseInt(categoriaField.getText());
                    Vehiculo vehiculo = obtenerVehiculoDesdeInterfaz();
                    double precio = Double.parseDouble(precioField.getText());
                    String enlace = enlaceField.getText();
                    int stock = Integer.parseInt(stockField.getText());
                    int stockMinimo = Integer.parseInt(stockMinimoField.getText());

                    Autoparte nuevaAutoparte = new Autoparte(id, denominacion, descripcion, categoria,
                            vehiculo, precio, enlace, stock, stockMinimo);

                    if (autoparte == null) {
                        empresa.agregarAutoparte(nuevaAutoparte);
                    } else {
                        empresa.modificarAutoparte(id, nuevaAutoparte);
                    }

                    
                    if (updateCallback != null) { // Llama al callback de actualización si no es nulo
                        updateCallback.run();
                    }

                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos para el ID, categoría, precio, stock y stock mínimo.");
                }
            }
        });

        getContentPane().add(saveButton);
    }

    private Vehiculo obtenerVehiculoDesdeInterfaz() { // Metodo auxiliar para obtener un objeto Vehiculo a partir de los campos de texto . 
        String marca = marcaField.getText();
        String modelo = modeloField.getText();
        return new Vehiculo(marca, modelo);
    }
}
