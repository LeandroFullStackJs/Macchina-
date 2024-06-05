package usuario;
import negocio.Venta;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VentasTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

    private List<Venta> ventas;
    private String[] columnNames = {"ID", "Fecha", "Cliente", "Total", "Forma de Pago"};

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    @Override
    public int getRowCount() {
        return ventas != null ? ventas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = ventas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return venta.getId();
            case 1:
                return venta.getFecha();
            case 2:
                return (venta.getCliente() != null) ? venta.getCliente().getNombre() : "Desconocido";
            case 3:
                return venta.getMontoFinal();
            case 4:
                return venta.getFormaDePago().toString();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Venta getVentaAt(int rowIndex) {
        return ventas.get(rowIndex);
    }
}
