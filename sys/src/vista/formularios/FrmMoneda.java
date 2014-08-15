package vista.formularios;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dao.MonedaDAO;
import entity.Moneda;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.utilitarios.MaestroTableModel;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class FrmMoneda extends AbstractMaestro {

	private static final long serialVersionUID = 1L;

	private Moneda moneda;

	private MonedaDAO monedaDAO = new MonedaDAO();

	private List<Moneda> monedas = new ArrayList<Moneda>();
	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private final ButtonGroup grpTipoMoneda = new ButtonGroup();
	private JTextField txtSimbolo;
	private JRadioButton optNac;
	private JRadioButton optExt;
	private JRadioButton optOtra;

	public FrmMoneda() {
		super("Monedas");

		getBarra().setFormMaestro(this);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(287, 26, 33, 14);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(346, 26, 47, 20);
		txtCodigo.setColumns(10);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(287, 56, 54, 14);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 267, 234);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(346, 52, 183, 22);

		optNac = new JRadioButton("Moneda Nacional");
		optNac.setSelected(true);
		optNac.setBounds(284, 128, 107, 23);
		optNac.setMnemonic(0);
		grpTipoMoneda.add(optNac);

		optExt = new JRadioButton("Primera moneda Extranjera");
		optExt.setBounds(284, 154, 157, 23);
		optExt.setMnemonic(1);
		grpTipoMoneda.add(optExt);

		optOtra = new JRadioButton("Otra Moneda");
		optOtra.setMnemonic(2);
		optOtra.setBounds(284, 180, 157, 23);
		grpTipoMoneda.add(optOtra);
		pnlContenido.setLayout(null);
		pnlContenido.add(scrollPane);
		pnlContenido.add(lblCdigo);
		pnlContenido.add(lblDescripcin);
		pnlContenido.add(txtDescripcion);
		pnlContenido.add(txtCodigo);
		pnlContenido.add(optNac);
		pnlContenido.add(optExt);
		pnlContenido.add(optOtra);

		JLabel lblSimbolo = new JLabel("Simbolo");
		lblSimbolo.setBounds(287, 89, 54, 14);
		pnlContenido.add(lblSimbolo);

		txtSimbolo = new JTextField();
		txtSimbolo.setColumns(10);
		txtSimbolo.setBounds(346, 85, 71, 22);
		pnlContenido.add(txtSimbolo);

		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setMoneda(getMonedas().get(selectedRow));
						else
							setMoneda(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setMoneda(new Moneda());
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {
		getMonedaDAO().crear_editar(getMoneda());
	}

	@Override
	public void llenarDesdeVista() {
		getMoneda().setIdmoneda(txtCodigo.getText());
		getMoneda().setDescripcion(txtDescripcion.getText());
		getMoneda().setSimbolo(txtSimbolo.getText());
		getMoneda().setTipo(grpTipoMoneda.getSelection().getMnemonic());
	};

	@Override
	public void eliminar() {
		setEstado(VISTA);
		vista_noedicion();
	}

	@Override
	public void llenar_datos() {
		if (getMoneda() != null) {
			txtCodigo.setText(getMoneda().getIdmoneda());
			txtDescripcion.setText(getMoneda().getDescripcion());
			txtSimbolo.setText(getMoneda().getSimbolo());
			switch (getMoneda().getTipo()) {
			case 0:
				optNac.setSelected(true);
				break;
			case 1:
				optExt.setSelected(true);
				break;
			default:
				optExt.setSelected(true);
				break;
			}
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
			txtSimbolo.setText("");
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Moneda obj : getMonedas()) {
			model.addRow(new Object[] { obj.getIdmoneda(), obj.getDescripcion() });
		}
		if (getMonedas().size() > 0) {
			setMoneda(getMonedas().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setMonedas(getMonedaDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		txtSimbolo.setEditable(true);
		Enumeration<AbstractButton> opts = grpTipoMoneda.getElements();
		while (opts.hasMoreElements()) {
			opts.nextElement().setEnabled(true);
		}
		
		
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtSimbolo.setEditable(false);
		Enumeration<AbstractButton> opts = grpTipoMoneda.getElements();
		while (opts.hasMoreElements()) {
			opts.nextElement().setEnabled(false);
		}
		tblLista.setEnabled(true);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidaVista() {
		if (txtCodigo.getText().trim().isEmpty())
			return false;
		if (txtDescripcion.getText().trim().isEmpty())
			return false;
		return true;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public MonedaDAO getMonedaDAO() {
		return monedaDAO;
	}

	public void setMonedaDAO(MonedaDAO monedaDAO) {
		this.monedaDAO = monedaDAO;
	}

	public List<Moneda> getMonedas() {
		return monedas;
	}

	public void setMonedas(List<Moneda> monedas) {
		this.monedas = monedas;
	}
}
