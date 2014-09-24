package vista.controles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import vista.contenedores.AbstractCntBuscar;

public class FindButton extends JComponent implements MouseListener {
	private static final long serialVersionUID = 2890038925323894079L;

	private boolean mouseOver;

	private boolean mousePressed;

	private static final int SIZE = 20;

	private static final Dimension PREFERRED_DIMENSION = new Dimension(SIZE,
			SIZE);
	
	private Color color1 = new Color(153, 204, 255);

	private Color color2 = new Color(255, 230, 255);

	private Color borde = new Color(102, 102, 255);

	public FindButton() {
		this.enableInputMethods(true);
		this.addMouseListener(this);
		mouseOver = false;
		setPreferredSize(PREFERRED_DIMENSION);
		setMinimumSize(PREFERRED_DIMENSION);
		setMaximumSize(PREFERRED_DIMENSION);
		setSize(PREFERRED_DIMENSION);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int width, height, margin, iw, ih;

		width = getWidth();
		height = getHeight();

		GradientPaint gradient = null;

		margin = 1;

		if (this.isEnabled()) {
			if (mousePressed) {
				gradient = new GradientPaint(0, 0, color1.brighter(), 0,
						getHeight(), color2);
				margin = 3;
			} else if (mouseOver) {
				gradient = new GradientPaint(0, 0, color2.brighter(), 0,
						getHeight(), color1.brighter());
				margin = 2;
			} else {
				gradient = new GradientPaint(0, 0, color2, 0, getHeight(),
						color1);
			}
		} else {
			gradient = new GradientPaint(0, 0, color2, 0, getHeight(), color1);
		}

		iw = width - margin * 2;
		ih = height - margin * 2;

		g2.setPaint(gradient);
		g2.fillRoundRect(0, 0, width, height, 5, 5);

		g2.setColor(borde);
		g2.drawRoundRect(0, 0, width - 1, height - 1, 5, 5);

		g2.drawImage(
				new ImageIcon(AbstractCntBuscar.class
						.getResource("/main/resources/iconos/search.png"))
						.getImage(), margin, margin, iw, ih, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseOver = true;
		repaint();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseOver = false;
		repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		repaint();

	}

}