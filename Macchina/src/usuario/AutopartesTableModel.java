package usuario;
import negocio.Autoparte;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
public class AutopartesTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Autoparte> autopartes;
    private String[] columnNames = {"Código", "Denominación", "Descripción", "Categoría", "Precio", "Enlace", "Stock", "Stock Mínimo"};

    public AutopartesTableModel() {
        this.autopartes = new ArrayList<>();
    }

    public void setAutopartes(List<Autoparte> autopartes) {
        this.autopartes = autopartes;
    }

    @Override
    public int getRowCount() {
        return autopartes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Autoparte autoparte = autopartes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return autoparte.getId();
            case 1:
                return autoparte.getDenominacion();
            case 2:
                return autoparte.getDescripcion();
            case 3:
                return autoparte.getCategoria();
            case 4:
                return autoparte.getPrecio_unitario();
            case 5:
                return autoparte.getEnlace();
            case 6:
                return autoparte.getStock();
            case 7:
                return autoparte.getStock_minimo();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Autoparte getAutoparteAt(int rowIndex) {
        return autopartes.get(rowIndex);
    }
}