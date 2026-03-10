package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import modelo.AccesoBd;
import modelo.Proveedor;

public class VentanaPrincipal extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnVerTabla;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Vista de la tabla");
		contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(null);   // Layout absoluto
	    setContentPane(contentPane);

	    // Crear JTextArea
	    textArea = new JTextArea();
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	   
	    // Crear JScrollPane y meter dentro el JTextArea
	    JScrollPane scrollPane = new JScrollPane(textArea);

	    // IMPORTANTE: el tamaño se pone al scroll, no al textArea
	    scrollPane.setBounds(42, 26, 338, 189);

	    // Añadir el scroll al panel
	    contentPane.add(scrollPane);
	    
	    btnVerTabla = new JButton("Ver tabla");
	    btnVerTabla.addActionListener(this);
	    btnVerTabla.setBounds(161, 225, 111, 21);
	    contentPane.add(btnVerTabla);
		
		
		
		
	}
	public void accesoBD()
	{
		AccesoBd accesoBD=new AccesoBd();
		Map<Integer, Proveedor> proveedores = new TreeMap<>();
		try 
		{
			accesoBD.consultaProveedores1(proveedores);
			for (Proveedor proveedor : proveedores.values()) {
			    textArea.append("CÓDIGO: " + proveedor.getCodigo() + "\n");
			    textArea.append("NOMBRE: " + proveedor.getNombre() + "\n");
			    textArea.append("TELÉFONO: " + proveedor.getTfno() + "\n");
			    textArea.append("EMAIL: " + proveedor.getEmail() + "\n");
			    textArea.append("-----------------------------\n");
			}

			
		}catch(Exception exception)
		{
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnVerTabla)
			accesoBD();
		
	}
}