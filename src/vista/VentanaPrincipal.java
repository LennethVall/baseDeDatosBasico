package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import modelo.AccesoBd;
import modelo.Proveedor;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;

public class VentanaPrincipal extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVerTabla;
	private JTextField textField;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JTable tabla;
    private DefaultTableModel modelo;
    private TableRowSorter<DefaultTableModel> sorter;


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaPrincipal() {
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 600, 400);
	    setTitle("La tabla");

	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	   
	    btnVerTabla = new JButton("Ver tabla");
	    btnVerTabla.setBounds(20, 320, 100, 25);
	    btnVerTabla.addActionListener(this);
	    contentPane.add(btnVerTabla);

	    btnLimpiar = new JButton("Limpiar");
	    btnLimpiar.setBounds(130, 320, 100, 25);
	    btnLimpiar.addActionListener(this);
	    contentPane.add(btnLimpiar);

	    textField = new JTextField();
	    textField.setBounds(20, 50, 120, 25);
	    contentPane.add(textField);

	    btnBuscar = new JButton("Buscar");
	    btnBuscar.setBounds(166, 50, 100, 25);
	    btnBuscar.addActionListener(this);
	    contentPane.add(btnBuscar);

	    
	    modelo = new DefaultTableModel();
	    modelo.addColumn("Codigo");
	    modelo.addColumn("Nombre");
	    modelo.addColumn("Telefono");
	    modelo.addColumn("Email");

	    tabla = new JTable(modelo);
	    sorter = new TableRowSorter<>(modelo);
	    tabla.setRowSorter(sorter);

	    tabla.setFont(new Font("Arial", Font.PLAIN, 14));
	    tabla.setRowHeight(22);

	    tabla.getTableHeader().setBackground(new Color(255, 255, 180)); // Amarillo claro
	    tabla.getTableHeader().setForeground(Color.BLACK);              // Texto negro para buena lectura
	    tabla.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 14)); // Opcional: fuente más visible

	    
	    JScrollPane scrollTabla = new JScrollPane(tabla);
	    scrollTabla.setBounds(20, 86, 550, 200);
	    contentPane.add(scrollTabla);
	 
	    scrollTabla.getViewport().setBackground(new Color(30, 90, 160));
	    tabla.setBackground(new Color(0, 51, 102));
	    tabla.setForeground(Color.WHITE); 

	   

	    
	    btnSalir = new JButton("Salir");
	    btnSalir.setBounds(485, 322, 89, 23);
	    contentPane.add(btnSalir);
	    
	    JLabel lblNewLabel = new JLabel("Introduce texto a buscar");
	    lblNewLabel.setBounds(20, 25, 210, 14);
	    contentPane.add(lblNewLabel);
	    btnSalir.addActionListener(this);
	    lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 18));

	}

				
	
	public void accesoBD() {
	    AccesoBd accesoBD = new AccesoBd();
	    Map<Integer, Proveedor> proveedores = new TreeMap<>();

	    try {
	        accesoBD.consultaProveedores1(proveedores);

	        
	        modelo.setRowCount(0);

	        for (Proveedor proveedor : proveedores.values()) {
	            modelo.addRow(new Object[]{
	                proveedor.getCodigo(),
	                proveedor.getNombre(),
	                proveedor.getTfno(),
	                proveedor.getEmail()
	            });
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnVerTabla)
			accesoBD();
		if (e.getSource() == btnBuscar) {
		    String nombre = textField.getText().trim();

		    if (nombre.isEmpty()) {
		        sorter.setRowFilter(null); // Quita el filtro si no hay texto
		    } else {
		        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + nombre));
		    }
		}

			
		if(e.getSource()==btnLimpiar)
			modelo.setRowCount(0);
			
		if (e.getSource() == btnSalir) {
            dispose();
		
	}
	}
	}

