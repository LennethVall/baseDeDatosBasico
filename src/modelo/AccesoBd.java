package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ResourceBundle;

public class AccesoBd{
		
		// Los siguientes atributos se utilizan para recoger los valores del fich de
		// configuracion
		private ResourceBundle configFile;
		private String urlBD;
		private String userBD;
		private String passwordBD;

		// Sentencias SQL		
		final String SQLCONSULTAPROVEEDOR = "SELECT * FROM proveedor WHERE cod_proveedor =?";
		final String CONSULTAPROVEEDOR = "SELECT * FROM proveedor";
		

		// Para la conexi n utilizamos un fichero de configuaraci n, config que
		// guardamos en el paquete control:
		public AccesoBd() {
			this.configFile = ResourceBundle.getBundle("vista.configClase");
			this.urlBD = this.configFile.getString("Conn");
			this.userBD = this.configFile.getString("DBUser");
			this.passwordBD = this.configFile.getString("DBPass");
		}

	
		
		public void consultaProveedores(int id,Map<Integer, Proveedor> proveedores) throws Exception {

		    try (Connection con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
		    		PreparedStatement stmt = con.prepareStatement(SQLCONSULTAPROVEEDOR)) 
		    {
		    	stmt.setInt(1, id);
	        	try (ResultSet rs = stmt.executeQuery())
	        	{	        

			        while (rs.next()) {
	
			            Proveedor proveedor = new Proveedor();
			            proveedor.setCodigo(rs.getInt("cod_proveedor"));
			            proveedor.setNombre(rs.getString("nombre_proveedor"));	
			            proveedores.put(proveedor.getCodigo(), proveedor);
			        }
	        	}
	
		    } 

		    
		}
		

		public void consultaProveedores1(Map<Integer, Proveedor> proveedores) throws Exception {

		    try (Connection con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
		    		PreparedStatement stmt = con.prepareStatement(CONSULTAPROVEEDOR)) 
		    {
	        	try (ResultSet rs = stmt.executeQuery())
	        	{	        

			        while (rs.next()) {
	
			            Proveedor proveedor = new Proveedor();
			            proveedor.setCodigo(rs.getInt("cod_proveedor"));
			            proveedor.setNombre(rs.getString("nombre_proveedor"));	
			            proveedor.setTfno(rs.getInt("telefono"));
			            proveedor.setEmail(rs.getString("email"));
			            proveedores.put(proveedor.getCodigo(), proveedor);
			        }
	        	}
	
		    } 

		    
		}


}

	
		
	