package vista.formularios;

import java.util.ArrayList;
import java.util.List;
import dao.ResponsableDAO;
import entity.Responsable;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.contenedores.cntArea;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FrmResponsable extends AbstractMaestro {

	private static final long serialVersionUID = 1L;

	private Responsable responsable;

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	private ResponsableDAO responsableDAO = new ResponsableDAO();

	public ResponsableDAO getResponsableDAO() {
		return responsableDAO;
	}

	public void setResponsableDAO(ResponsableDAO responsableDAO) {
		this.responsableDAO = responsableDAO;
	}

	private List<Responsable> responsableL = new ArrayList<Responsable>();

	public List<Responsable> getResponsableL() {
		return responsableL;
	}

	public void setResponsableL(List<Responsable> responsableL) {
		this.responsableL = responsableL;
	}

	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;	
	public final cntArea cntarea;

	public FrmResponsable() {
		super("Responsables");		
		pnlContenido.setBounds(0, 0, 0, 0);
		getBarra().setBounds(0, 0, 539, 39);
		
		JLabel lblCdigo = new JLabel("Codigo");
		lblCdigo.setBounds(227, 11, 46, 14);
		pnlContenido.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(286, 8, 122, 20);
		txtCodigo.setColumns(10);
		
		pnlContenido.add(txtCodigo);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(227, 36, 54, 14);
		pnlContenido.add(lblDescripcin);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 207, 273);
		
		pnlContenido.add(scrollPane);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(286, 33, 122, 20);		
		pnlContenido.add(txtDescripcion);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setBounds(227, 61, 31, 14);
		pnlContenido.setLayout(null);
		cntarea = new cntArea();
		cntarea.setBounds(286, 64, 104, 24);
		pnlContenido.add(cntarea);
		pnlContenido.add(lblArea);

		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setResponsable(getResponsableL().get(selectedRow));						
						else
							setResponsable(null);
						llenar_datos();
					}
				});
		cntarea.setVisible(false);
		lblArea.setVisible(false);
		iniciar();
	}


	@Override
	public void nuevo() {
		setResponsable(new Responsable());
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {	
		getResponsableDAO().crear_editar(getResponsable());
	}

	@Override
	public void eliminar() {
		setEstado(VISTA);
		vista_noedicion();
	}

	@Override
	public void llenar_datos() {
		if (getResponsable() != null) {
			txtCodigo.setText(getResponsable().getIdresponsable());
			txtDescripcion.setText(getResponsable().getNombre());
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Responsable responsable : getResponsableL()) {
			model.addRow(new Object[] { responsable.getIdresponsable(), responsable.getNombre()});
		}
		if (getResponsableL().size() > 0) {
			setResponsable(getResponsableL().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setResponsableL(getResponsableDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		tblLista.setEnabled(false);		
		cntarea.btnBuscar.setEnabled(true);
		cntarea.txtCodigo.setEditable(true);
		cntarea.txtDescripcion.setEditable(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		tblLista.setEnabled(true);
		cntarea.btnBuscar.setEnabled(false);
		cntarea.txtCodigo.setEditable(false);
		cntarea.txtDescripcion.setEditable(false);
	}
	


	@Override
	public void init() {
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		
	}

	@Override
	public void llenarDesdeVista() {
		getResponsable().setIdresponsable(txtCodigo.getText());
		getResponsable().setNombre(txtDescripcion.getText());		
	}

	@Override
	public boolean isValidaVista() {
		if(this.txtCodigo.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Falta dato codigo");
			return false;
		}
		if(this.txtDescripcion.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Falta dato Descripcion");
			return false;
		}
		return true;
	}

}
