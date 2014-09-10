package vista.controles;

import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import vista.utilitarios.StringUtils;

public class DSGTextFieldCorrelativo extends JFormattedTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DSGTextFieldCorrelativo(int total) {

		DefaultFormatterFactory factory = new DefaultFormatterFactory(
				new NumberFormatter(new DecimalFormat(StringUtils._replicate(
						total, '0'))));

		setFormatterFactory(factory);

		setHorizontalAlignment(SwingConstants.RIGHT);

	}
}
