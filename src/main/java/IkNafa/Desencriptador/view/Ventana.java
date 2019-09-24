package IkNafa.Desencriptador.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import IkNafa.Desencriptador.model.Configuracion;
import IkNafa.Desencriptador.model.ContadorTexto;

import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnDesencriptar;
	private JButton btnAbrirConfig;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel lblPath;
	private Configuracion config;
	private Cuentas cuentas;
	private String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		config = new Configuracion();
		initComponents();
	}
	private void initComponents() {
		setTitle("DESENCRIPTADOR -IKER NAFARRATE-");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{601, 0};
		gbl_contentPane.rowHeights = new int[]{(int) (getHeight()*0.83), (int) (getHeight()*0.15), (int) (getHeight()*0.2)};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(getScrollPane(), gbc_scrollPane);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(getPanel(), gbc_panel);
		setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setSize(getWidth(),(int) (getHeight()*0.15));
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			panel.add(getLblPath());
			panel.add(getBtnDesencriptar());
			panel.add(getBtnAbrirConfig());
		}
		return panel;
	}
	private JButton getBtnDesencriptar() {
		if (btnDesencriptar == null) {
			btnDesencriptar = new JButton("DESENCRIPTAR");
			btnDesencriptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(lblPath.getForeground() == Color.RED) {
						textArea.setText(ContadorTexto.traducirTexto(textArea.getText(), config));
						if(cuentas != null) {
							cuentas.dispose();
						}
						cuentas = new Cuentas(ContadorTexto.contarLetrasTexto(textArea.getText()));
					}else {
						textArea.setText(ContadorTexto.traducirFichero(path, config));
						if(cuentas != null) {
							cuentas.dispose();
						}
						cuentas = new Cuentas(ContadorTexto.contarLetrasFichero(path));
					}
					

				}
			});
		}
		return btnDesencriptar;
	}
	private JButton getBtnAbrirConfig() {
		if (btnAbrirConfig == null) {
			btnAbrirConfig = new JButton("ABRIR CONFIG");
			btnAbrirConfig.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Desktop.getDesktop().open(new File("./Config.txt"));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(Ventana.this, "No se pudo abrir la configuracion");
					}
				}
			});
		}
		return btnAbrirConfig;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea_1());
		}
		return scrollPane;
	}
	private JTextArea getTextArea_1() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}
	private JLabel getLblPath() {
		if (lblPath == null) {
			lblPath = new JLabel("No path selected");
			lblPath.setHorizontalAlignment(SwingConstants.LEFT);
			lblPath.setForeground(Color.RED);
			
			lblPath.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == 1) {
						JFileChooser chooser = new JFileChooser();
						chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
						chooser.showOpenDialog(Ventana.this);
						path = chooser.getSelectedFile().getAbsolutePath();
						lblPath.setText(chooser.getSelectedFile().getName());
						lblPath.setForeground(Color.GREEN);
					}else if(e.getButton() == 3) {
						path = "";
						lblPath.setText("No path selected");
						lblPath.setForeground(Color.RED);
						
					}
					
				}
			});
		}
		return lblPath;
	}
}
