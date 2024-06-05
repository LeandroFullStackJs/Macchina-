package usuario;
import negocio.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClientesTableModel extends AbstractTableModel {
	 private static final long serialVersionUID = 1L;

	    private List<Cliente> clientes;
	    private String[] columnNames = {"ID", "Nombre", "Apellido", "DNI", "Dirección", "Teléfono", "Localidad", "Provincia", "Email"};

	    public ClientesTableModel() {
	        this.clientes = new ArrayList<>();
	    }

	    public void setClientes(List<Cliente> clientes) {
	        this.clientes = clientes;
	    }

	    @Override
	    public int getRowCount() {
	        return clientes.size();
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Cliente cliente = clientes.get(rowIndex);
	        switch (columnIndex) {
	            case 0:
	                return cliente.getId();
	            case 1:
	                return cliente.getNombre();
	            case 2:
	                return cliente.getApellido();
	            case 3:
	                return cliente.getDni();
	            case 4:
	                return cliente.getDireccion();
	            case 5:
	                return cliente.getTelefono();
	            case 6:
	                return cliente.getLocalidad();
	            case 7:
	                return cliente.getProvincia();
	            case 8:
	                return cliente.getEmail();
	            default:
	                return null;
	        }
	    }

	    @Override
	    public String getColumnName(int column) {
	        return columnNames[column];
	    }

	    public Cliente getClienteAt(int rowIndex) {
	        return clientes.get(rowIndex);
	    }
}
