package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.controles.DSGTableModel;
import vista.utilitarios.MaestroTableModel;
import vista.utilitarios.editores.TableTextEditor;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import dao.CfgCentralizaAlmDAO;
import dao.ConceptoDAO;
import dao.GrupoDAO;
import dao.SubgrupoDAO;
import entity.CfgCentralizaAlm;
import entity.CfgCentralizaAlmPK;
import entity.Concepto;
import entity.Cuenta;
import entity.SubgrupoPK;

import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmCfgCentralizaAlm extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblLista;

	private JTable tblCentraliza;

	private List<Concepto> conceptos;
	private List<CfgCentralizaAlm> centraliza;

	private Concepto concepto;
	private ConceptoDAO conceptoDAO = new ConceptoDAO();
	private GrupoDAO grupoDAO = new GrupoDAO();
	private SubgrupoDAO subgrupoDAO = new SubgrupoDAO();
	private CfgCentralizaAlmDAO centralizaDAO = new CfgCentralizaAlmDAO();
	private JScrollPane scrollPaneNum;

	public FrmCfgCentralizaAlm() {
		super("Configuracion de Centralización");

		JScrollPane scrollPane = new JScrollPane();

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tblCentraliza = new JTable(new DSGTableModel(new String[] {
				"Cod. Grupo", "Grupo", "Cod. Subgrupo", "Sub Grupo", "Debe",
				"Haber" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				return getEditar();
			}

			@Override
			public void addRow() {
				addRow(new Object[] { "", "", "", "", "", "" });
			}
		});

		tblCentraliza.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPaneNum = new JScrollPane(tblCentraliza);

		getCentralizaTM().setNombre_detalle("Almacenes");
		getCentralizaTM().setObligatorios(0, 1, 2);
		getCentralizaTM().setRepetidos(0, 2);
		getCentralizaTM().setScrollAndTable(scrollPaneNum, tblCentraliza);

		TableColumnModel cModel = tblCentraliza.getColumnModel();
		cModel.getColumn(0).setCellEditor(new TableTextEditor(3, true));
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(10)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								202, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.scrollPaneNum,
								GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
						.addGap(10)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(11)
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(this.scrollPaneNum,
												Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 230,
												Short.MAX_VALUE)
										.addComponent(scrollPane,
												Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 230,
												Short.MAX_VALUE)).addGap(11)));
		pnlContenido.setLayout(groupLayout);
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setConcepto(getConceptos().get(selectedRow));
						else
							setConcepto(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void DoNuevo() {
		// TODO Auto-generated method stub
		// super.DoNuevo();
	}

	@Override
	public void nuevo() {
		//
	}

	@Override
	public void anular() {

	}

	@Override
	public void grabar() {
		for (CfgCentralizaAlm c : centraliza) {
			centralizaDAO.crear_editar(c);
		}
	}

	@Override
	public void llenarDesdeVista() {
		setCentraliza(new ArrayList<CfgCentralizaAlm>());
		String idconcepto;
		idconcepto = getConcepto().getIdconcepto();

		for (int i = 0; i < getCentralizaTM().getRowCount(); i++) {
			CfgCentralizaAlmPK id = new CfgCentralizaAlmPK();
			CfgCentralizaAlm alm = new CfgCentralizaAlm();

			id.setIdconcepto(idconcepto);
			id.setIdgrupo(getCentralizaTM().getValueAt(i, 0).toString());
			id.setIdsubgrupo(getCentralizaTM().getValueAt(i, 2).toString());

			Cuenta debe, haber;
			debe = new Cuenta();
			debe.setIdcuenta(getCentralizaTM().getValueAt(i, 4).toString());

			haber = new Cuenta();
			haber.setIdcuenta(getCentralizaTM().getValueAt(i, 5).toString());

			alm.setId(id);
			alm.setCta_debe(debe);
			alm.setCta_haber(haber);

			getCentraliza().add(alm);
		}
	}

	@Override
	public void llenar_datos() {
		getCentralizaTM().limpiar();
		if (getConcepto() != null) {
			setCentraliza(null);
			setCentraliza(centralizaDAO.getPorConcepto(getConcepto()));
			for (CfgCentralizaAlm alm : getCentraliza()) {
				alm.setGrupo(grupoDAO.find(alm.getId().getIdgrupo()));
				SubgrupoPK ids = new SubgrupoPK();
				ids.setIdgrupo(alm.getId().getIdgrupo());
				ids.setIdsubgrupo(alm.getId().getIdsubgrupo());
				alm.setSubgrupo(subgrupoDAO.find(ids));
				
				getCentralizaTM().addRow(
						new Object[] { 
								alm.getGrupo().getIdgrupo(),
								alm.getGrupo().getDescripcion(),
								alm.getSubgrupo().getId().getIdsubgrupo(),
								alm.getSubgrupo().getDescripcion(),
								alm.getCta_debe().getIdcuenta(),
								alm.getCta_haber().getIdcuenta() });
			}
		}
	}

	@Override
	public boolean isValidaVista() {

		if (!validarDetalles()) {
			return false;
		}
		return true;
	}

	private boolean validarDetalles() {
		return getCentralizaTM().esValido();
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Concepto obj : getConceptos()) {
			model.addRow(new Object[] { obj.getIdconcepto(),
					obj.getDescripcion() });
		}
		if (getConceptos().size() > 0) {
			setConcepto(getConceptos().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setConceptos(conceptoDAO.findAll());
	}

	@Override
	public void vista_edicion() {
		getCentralizaTM().setEditar(true);

	}

	@Override
	public void vista_noedicion() {
		getCentralizaTM().setEditar(false);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub

	}

	public DSGTableModel getCentralizaTM() {
		return ((DSGTableModel) tblCentraliza.getModel());
	}

	@Override
	public void eliminar() {
		// ////
	}

	public List<Concepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}

	public List<CfgCentralizaAlm> getCentraliza() {
		return centraliza;
	}

	public void setCentraliza(List<CfgCentralizaAlm> centraliza) {
		this.centraliza = centraliza;
	}

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	public CfgCentralizaAlmDAO getCentralizaDAO() {
		return centralizaDAO;
	}

	public void setCentralizaDAO(CfgCentralizaAlmDAO centralizaDAO) {
		this.centralizaDAO = centralizaDAO;
	}
}