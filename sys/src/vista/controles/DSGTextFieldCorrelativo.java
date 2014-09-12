package vista.controles;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import vista.utilitarios.StringUtils;

public class DSGTextFieldCorrelativo extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int total;
	public DSGTextFieldCorrelativo(int total){
		super();
		this.total = total;
		setDocument(new JTextFieldLimit(total, true));
		
		setHorizontalAlignment(SwingConstants.RIGHT);
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (getText() != null && !getText().trim().isEmpty())
					setText(StringUtils._padl(getText(), DSGTextFieldCorrelativo.this.total, '0'));
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setValue(Object value){
		if(value == null)
			setText(null);
		else
			setText((String) value);
	}
	
	public void setValue(int value){
		setText(StringUtils._padl(String.valueOf(value), DSGTextFieldCorrelativo.this.total, '0'));
	}
}
