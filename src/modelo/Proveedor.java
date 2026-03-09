package modelo;

public class Proveedor {
	private int codigo;
	private String nombre;
	private int tfno;
	private String email;
	
	public Proveedor() {}

	

	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getTfno() {
		return tfno;
	}

	public void setTfno(int tfno) {
		this.tfno = tfno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public Proveedor(int codigo, String nombre, int tfno, String email) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.tfno = tfno;
		this.email = email;
	}



	@Override
	public String toString() {
		return "Proveedor [codigo=" + codigo + ", nombre=" + nombre + ", tfno=" + tfno + ", email=" + email + "]";
	}
	
}