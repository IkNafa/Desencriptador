package IkNafa.Desencriptador.view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Color;

public class Cuentas extends JDialog {
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private String texto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cuentas dialog = new Cuentas("Hola \nlinea");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Cuentas(String pText) {
		texto = pText;
		initComponents();
	}
	private void initComponents() {
		setBounds(100, 100, 242, 540);
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);
		setVisible(true);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea(texto);
			textArea.setForeground(Color.GREEN);
			textArea.setBackground(Color.BLACK);
			textArea.setEditable(false);
		}
		return textArea;
	}
}
