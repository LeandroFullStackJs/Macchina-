package usuario;

import negocio.DetallePedido;
import negocio.Pedido;

import javax.swing.table.AbstractTableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
public class PedidosTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	 private List<Pedido> pedidos;
	    private String[] columnNames = {"Cliente", "Autopartes", "Fecha", "Estado"};

	    public PedidosTableModel() {
	        this.pedidos = new ArrayList<>();
	    }

	    public void setPedidos(List<Pedido> pedidos) {
	        this.pedidos = pedidos;
	    }

	    @Override
	    public int getRowCount() {
	        return pedidos.size();
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Pedido pedido = pedidos.get(rowIndex);
	        switch (columnIndex) {
	            case 0:
	                return pedido.getCliente().getId();
	            case 1:
	                return detallesPedidoToString(pedido.getDetalles());
	            case 2:
	                return new SimpleDateFormat("yyyy-MM-dd").format(pedido.getFecha());
	            case 3:
	                return pedido.isCancelado();
	            default:
	                return null;
	        }
	    }

	    @Override
	    public String getColumnName(int column) {
	        return columnNames[column];
	    }

	    public Pedido getPedidoAt(int rowIndex) {
	        return pedidos.get(rowIndex);
	    }
	    
	    private String detallesPedidoToString(List<DetallePedido> detalles) {
	        StringBuilder detallesString = new StringBuilder();
	        for (DetallePedido detalle : detalles) {
	            detallesString.append(detalle.getAutoparte().getDenominacion())
	                          .append(" (Cantidad: ").append(detalle.getCantidad()).append("), ");
	        }
	        // Eliminar la última coma y espacio si hay elementos
	        if (detallesString.length() > 0) {
	            detallesString.setLength(detallesString.length() - 2);
	        }
	        return detallesString.toString();
	    }
	}
