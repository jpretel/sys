package vista.formularios.modal;

import vista.controles.DSGTableModel;
import vista.controles.ModalInternalFrame;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModalDetalleReferencia extends ModalInternalFrame {
	Object[][] data;

	public ModalDetalleReferencia(String[] cabeceras, Object[][] data) {
		setSize(new Dimension(570, 350));
		setPreferredSize(new Dimension(200, 200));
		setTitle("Detalle de Referencia");

		final DSGTableModel detalleTM = new DSGTableModel(cabeceras) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				if (column == 4)
					return true;
				return false;
			}

			@Override
			public void addRow() {

			}
		};

		this.pnlNorte = new JPanel();
		this.pnlNorte.setPreferredSize(new Dimension(10, 40));
		this.pnlNorte.setMinimumSize(new Dimension(70, 10));
		getContentPane().add(this.pnlNorte, BorderLayout.NORTH);
		this.pnlNorte.setLayout(null);

		this.lblDetalleDe = new JLabel("Detalle de:");
		this.lblDetalleDe.setBounds(10, 11, 52, 14);
		this.pnlNorte.add(this.lblDetalleDe);

		this.pnlSur = new JPanel();
		this.pnlSur.setPreferredSize(new Dimension(10, 40));
		this.pnlSur.setMinimumSize(new Dimension(70, 10));
		getContentPane().add(this.pnlSur, BorderLayout.SOUTH);
		this.pnlSur.setLayout(null);

		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalDetalleReferencia.this.data = null;
				dispose();
			}
		});

		this.btnCancelar.setBounds(451, 11, 89, 23);
		this.pnlSur.add(this.btnCancelar);

		this.btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualizar cantidades
				int rows = detalleTM.getRowCount();
				for (int i = 0; i < rows; i++) {
					ModalDetalleReferencia.this.data[i][4] = Float
							.parseFloat(detalleTM.getValueAt(i, 4).toString());
				}
				dispose();
			}
		});

		this.btnAceptar.setBounds(338, 11, 89, 23);
		this.pnlSur.add(this.btnAceptar);

		this.scrlDetalle = new JScrollPane();
		getContentPane().add(this.scrlDetalle, BorderLayout.CENTER);

		this.table = new JTable(detalleTM);
		this.scrlDetalle.setViewportView(this.table);

		this.data = data;

		for (Object[] o : this.data) {
			detalleTM.addRow(o);
		}

	}

	private static final long serialVersionUID = 1L;
	private JPanel pnlNorte;
	private JPanel pnlSur;
	private JScrollPane scrlDetalle;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblDetalleDe;
}
