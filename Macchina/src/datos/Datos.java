package datos;
import java.io.*;

public class Datos implements Serializable {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FILENAME = "Empresa.bin";

	    public static boolean guardar(Object obj) {
	        try (FileOutputStream fout = new FileOutputStream(FILENAME);
	             ObjectOutputStream out = new ObjectOutputStream(fout)) {
	            out.writeObject(obj);
	            return true;
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }

	    public static Object recuperar() {
	        try (FileInputStream fin = new FileInputStream(FILENAME);
	             ObjectInputStream in = new ObjectInputStream(fin)) {
	            return in.readObject();
	        } catch (IOException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
}
