package vista.contenedores;

import java.awt.Color;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import vista.Sys;
import vista.barras.BarraMaestro;

import java.util.List;
import java.awt.Dimension;

import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.ComponentOrientation;

public abstract class AbstractCntBuscar<T> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JXTextFieldEntityAC<T> txtCodigo;
	public JTextField txtDescripcion;
	public JLabel btnBuscar;

	private static final int _dim = 16;

	public AbstractCntBuscar(String[] cabeceras, int[] anchos) {

		setForeground(Color.LIGHT_GRAY);
		this.setBounds(152, 11, 220, 20);
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 46, 106, 28, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		txtCodigo = new JXTextFieldEntityAC<T>(getFormulario(), cabeceras,
				anchos) {
			private static final long serialVersionUID = 1L;

			/*
			 * Sobreescribir este motodo en caso de que se quiere buscar a
			 * partir del dígito N, por defecto es 3. En este caso va a mostrar
			 * el popup a partir del 2do digito
			 */
			@Override
			public int getMinimoBusqueda() {
				return 1;
			}

			/*
			 * Sobreescribir este motodo para recuperar el valor seleccionado
			 */
			@Override
			public void cargaDatos() {
				if (getSeleccionado() != null) {
					AbstractCntBuscar.this.cargarDatos(getSeleccionado());
				} else {
					txtCodigo.setText("");
					txtDescripcion.setText("");
				}
			}
			
			@Override
			public boolean coincideBusqueda(T dato, String cadena) {
				return AbstractCntBuscar.this.coincideBusqueda(dato, cadena);
			}

			@Override
			public Object[] entity2Object(T entity) {
				return AbstractCntBuscar.this.entity2Object(entity);
			}

			@Override
			public String getEntityCode(T entity) {
				return AbstractCntBuscar.this.getEntityCode(entity);
			}
		};

		txtCodigo.setColumns(5);
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();

		gbc_txtCodigo.fill = GridBagConstraints.BOTH;
		gbc_txtCodigo.gridx = 0;
		gbc_txtCodigo.gridy = 0;
		add(txtCodigo, gbc_txtCodigo);

		txtDescripcion = new JTextField(15);
		txtDescripcion.setEditable(false);
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
		gbc_txtDescripcion.gridx = 1;
		gbc_txtDescripcion.gridy = 0;
		add(txtDescripcion, gbc_txtDescripcion);

		btnBuscar = new JLabel("");
		btnBuscar.setFocusTraversalPolicyProvider(true);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtCodigo.requestFocus();
				txtCodigo.checkForAndShowSuggestions();
			}
		});
		btnBuscar.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
				.getResource("/main/resources/iconos/vistaprevia.png"))
				.getImage().getScaledInstance(_dim, _dim,
						java.awt.Image.SCALE_DEFAULT)));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.gridx = 2;
		gbc_btnBuscar.gridy = 0;
		add(btnBuscar, gbc_btnBuscar);
	}

	public AbstractCntBuscar() {
		this(new String[] { "Codigo", "Descripcion" }, new int[] { 90, 200 });
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	}

	public Window getFormulario() {
		return (Window) Sys.mainF;
	}

	/**
	 * 
	 * @param Actualiza
	 *            lista de Datos a filtrar
	 */
	public void setData(List<T> data) {
		txtCodigo.setData(data);
	}

	public T getSeleccionado() {
		return txtCodigo.getSeleccionado();
	}

	public void setSeleccionado(T seleccionado) {
		txtCodigo.setSeleccionado(seleccionado);
		cargarDatos(seleccionado);
	}

	public static ResizableIcon getResizableIconFromResource16x16(
			String resource) {
		return ImageWrapperResizableIcon.getIcon(AbstractCntBuscar.class
				.getResource(resource), new Dimension(30, 30));
	}

	public abstract void cargarDatos(T entity);

	public abstract boolean coincideBusqueda(T entity, String cadena);

	public abstract Object[] entity2Object(T entity);

	public abstract String getEntityCode(T entity);
}