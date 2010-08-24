package ventanas;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridBagLayout;

import java.awt.Point;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JDesktopPane jDesktopPane = null;
	private JButton initButton = null;
	private JLabel functionLabel = null;
	private PanelGrafico dibujoPanel = null;
	private JTextField numIteracionesBox = null;
	private JLabel numIteracionesLabel = null;
	private JTextField nField = null;
	private JTextField cField = null;
	private JLabel nLabel = null;
	private JLabel cLabel = null;
	private JLabel realLabel = null;
	private JLabel realField = null;
	private JLabel imagLabel = null;
	private JLabel imagField = null;
	private JTextField arribaField = null;
	private JTextField abajoField = null;
	private JTextField izquierdaField = null;
	private JTextField derechaField = null;
	private JButton ejesButton = null;
	private JCheckBox raicesField = null;
	private JButton iterarButton = null;
	
	private boolean modoIterar;
	private boolean mostrandoIteraciones;
	
	private JMenuBar barraMenu = null;
	private JMenu ayudaMenu = null;
	private JMenuItem acercadeMenuItem = null;
	private JLabel iterarLabel = null;
	/**
	 * This is the default constructor
	 */
	public VentanaPrincipal() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(780, 505);
		this.setJMenuBar(getBarraMenu());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Metodo de Newton");
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				Component c = e.getComponent();
				dibujoPanel.setBounds(new Rectangle(15, 52, c.getWidth()-30, c.getHeight()-142));
				imagField.setBounds(new Rectangle(270, c.getHeight()-85, 154, 28));
				imagLabel.setBounds(new Rectangle(228, c.getHeight()-85, 39, 28));
				realField.setBounds(new Rectangle(62, c.getHeight()-85, 153, 28));
				realLabel.setBounds(new Rectangle(16, c.getHeight()-85, 42, 28));
				raicesField.setBounds(new Rectangle(450, c.getHeight()-85, 125, 26));
				iterarButton.setBounds(new Rectangle(580, c.getHeight()-85, 83, 28));
				iterarLabel.setBounds(new Rectangle(664, c.getHeight()-85, 109, 23));
			}
		});
		
		modoIterar=false;
		mostrandoIteraciones=false;

		int n, c;
		
		if(nField.getText() == "")
			 n = 3;
		else
			 n = Integer.parseInt(nField.getText());
		
		if(cField.getText() == "")
			 c = -1;
		else
			 c = Integer.parseInt(cField.getText());
		
		dibujoPanel.inicializar(n, c);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJDesktopPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jDesktopPane	
	 * 	
	 * @return javax.swing.JDesktopPane	
	 */
	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			iterarLabel = new JLabel();
			iterarLabel.setBounds(new Rectangle(664, this.getHeight()-85, 109, 23));
			iterarLabel.setText("");
			imagField = new JLabel();
			imagField.setBounds(new Rectangle(270, this.getHeight()-85, 154, 28));
			imagField.setText("");
			imagLabel = new JLabel();
			imagLabel.setBounds(new Rectangle(228, this.getHeight()-85, 39, 28));
			imagLabel.setText("Imag:");
			realField = new JLabel();
			realField.setBounds(new Rectangle(62, this.getHeight()-85, 153, 28));
			realField.setText("");
			realLabel = new JLabel();
			realLabel.setBounds(new Rectangle(16, this.getHeight()-85, 42, 28));
			realLabel.setText("Real:");
			cLabel = new JLabel();
			cLabel.setBounds(new Rectangle(162, 17, 16, 23));
			cLabel.setText("C:");
			nLabel = new JLabel();
			nLabel.setBounds(new Rectangle(99, 17, 16, 23));
			nLabel.setText("n:");
			numIteracionesLabel = new JLabel();
			numIteracionesLabel.setText("Numero de iteraciones:");
			numIteracionesLabel.setSize(new Dimension(155, 15));
			numIteracionesLabel.setPreferredSize(new Dimension(155, 15));
			numIteracionesLabel.setLocation(new Point(224, 21));
			functionLabel = new JLabel();
			functionLabel.setBounds(new Rectangle(22, 21, 64, 15));
			functionLabel.setPreferredSize(new Dimension(64, 15));
			functionLabel.setText("Z^n + C");
			jDesktopPane = new JDesktopPane();
			jDesktopPane.setBackground(new Color(238, 238, 238));
			jDesktopPane.add(getInitButton(), null);
			jDesktopPane.add(functionLabel, null);
			jDesktopPane.add(getJPanel(), null);
			jDesktopPane.add(getNumIteracionesBox(), null);
			jDesktopPane.add(numIteracionesLabel, null);
			jDesktopPane.add(getNField(), null);
			jDesktopPane.add(getCField(), null);
			jDesktopPane.add(nLabel, null);
			jDesktopPane.add(cLabel, null);
			jDesktopPane.add(realLabel, null);
			jDesktopPane.add(realField, null);
			jDesktopPane.add(imagLabel, null);
			jDesktopPane.add(imagField, null);
			jDesktopPane.add(getArribaField(), null);
			jDesktopPane.add(getAbajoField(), null);
			jDesktopPane.add(getIzquierdaField(), null);
			jDesktopPane.add(getDerechaField(), null);
			jDesktopPane.add(getEjesButton(), null);
			jDesktopPane.add(getRaicesField(), null);
			jDesktopPane.add(getIterarButton(), null);
			jDesktopPane.add(iterarLabel, null);
		}
		return jDesktopPane;
	}

	/**
	 * This method initializes initButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInitButton() {
		if (initButton == null) {
			initButton = new JButton();
			initButton.setBounds(new Rectangle(428, 9, 76, 36));
			initButton.setText("Iniciar");
			initButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					initClick();
				}
			});
		}
		return initButton;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (dibujoPanel == null) {
			dibujoPanel = new PanelGrafico();
			dibujoPanel.setLayout(new GridBagLayout());
			dibujoPanel.setBounds(new Rectangle(15, 52, this.getWidth()-30, this.getHeight()-142));
			dibujoPanel.setBackground(Color.white);
			dibujoPanel.addMouseListener(new java.awt.event.MouseListener() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					if(!modoIterar)
						dibujoPanel.ratonPulsa(e.getPoint());
				}
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(modoIterar)
					{
						int iteraciones;
						if(numIteracionesBox.getText() == "")
							 iteraciones = 0;
						else
							 iteraciones = Integer.parseInt(numIteracionesBox.getText());
						
						dibujoPanel.muestraIteraciones(e.getPoint(), iteraciones);
						
						modoIterar=false;
						iterarLabel.setText("");
						iterarButton.setText("Limpiar");
					}
				}
				public void mouseReleased(java.awt.event.MouseEvent e) {
					if(!modoIterar)
					{
						dibujoPanel.ratonSuelta(e.getPoint());
	
						arribaField.setText(""+dibujoPanel.getArriba());
						abajoField.setText(""+dibujoPanel.getAbajo());
						izquierdaField.setText(""+dibujoPanel.getIzquierda());
						derechaField.setText(""+dibujoPanel.getDerecha());
					}
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
				}
				public void mouseExited(java.awt.event.MouseEvent e) {
				}
			});
			dibujoPanel.addMouseMotionListener(new java.awt.event.MouseMotionListener() {
				public void mouseMoved(java.awt.event.MouseEvent e) {
					dibujoPanel.ratonMoviendo(e.getPoint(), realField, imagField);
				}
				public void mouseDragged(java.awt.event.MouseEvent e) {
					dibujoPanel.ratonArrastrando(e.getPoint());
				}
			});
		}
		return dibujoPanel;
	}
	
	private void initClick()
	{
		int iteraciones;
		if(numIteracionesBox.getText() == "")
			 iteraciones = 0;
		else
			 iteraciones = Integer.parseInt(numIteracionesBox.getText());
		
		dibujoPanel.newton(iteraciones);
	}

	/**
	 * This method initializes numIteracionesBox	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumIteracionesBox() {
		if (numIteracionesBox == null) {
			numIteracionesBox = new JTextField();
			numIteracionesBox.setPreferredSize(new Dimension(105, 25));
			numIteracionesBox.setLocation(new Point(386, 16));
			numIteracionesBox.setSize(new Dimension(32, 25));
			numIteracionesBox.setText("10");
		}
		return numIteracionesBox;
	}

	/**
	 * This method initializes nField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNField() {
		if (nField == null) {
			nField = new JTextField();
			nField.setPreferredSize(new Dimension(30, 25));
			nField.setLocation(new Point(121, 16));
			nField.setSize(new Dimension(30, 25));
			nField.setText("3");
			nField.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("Actualizando N");
					int n, c;
					
					if(nField.getText() == "")
						 n = 3;
					else
						 n = Integer.parseInt(nField.getText());
					
					if(cField.getText() == "")
						 c = -1;
					else
						 c = Integer.parseInt(cField.getText());
					
					dibujoPanel.inicializar(n, c);
				}
			});
		}
		return nField;
	}

	/**
	 * This method initializes cField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCField() {
		if (cField == null) {
			cField = new JTextField();
			cField.setPreferredSize(new Dimension(30, 25));
			cField.setLocation(new Point(180, 16));
			cField.setSize(new Dimension(30, 25));
			cField.setText("-1");
			cField.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("Actualizando C");
					int n, c;
					
					if(nField.getText() == "")
						 n = 3;
					else
						 n = Integer.parseInt(nField.getText());
					
					if(cField.getText() == "")
						 c = -1;
					else
						 c = Integer.parseInt(cField.getText());
					
					dibujoPanel.inicializar(n, c);
				}
			});
		}
		return cField;
	}

	/**
	 * This method initializes arribaField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getArribaField() {
		if (arribaField == null) {
			arribaField = new JTextField();
			arribaField.setPreferredSize(new Dimension(45, 20));
			arribaField.setLocation(new Point(566, 4));
			arribaField.setSize(new Dimension(45, 20));
			arribaField.setText("5.0");
		}
		return arribaField;
	}

	/**
	 * This method initializes abajoField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAbajoField() {
		if (abajoField == null) {
			abajoField = new JTextField();
			abajoField.setPreferredSize(new Dimension(45, 20));
			abajoField.setLocation(new Point(566, 29));
			abajoField.setSize(new Dimension(45, 20));
			abajoField.setText("-5.0");
		}
		return abajoField;
	}

	/**
	 * This method initializes izquierdaField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getIzquierdaField() {
		if (izquierdaField == null) {
			izquierdaField = new JTextField();
			izquierdaField.setPreferredSize(new Dimension(45, 20));
			izquierdaField.setLocation(new Point(516, 18));
			izquierdaField.setSize(new Dimension(45, 20));
			izquierdaField.setText("-10.0");
		}
		return izquierdaField;
	}

	/**
	 * This method initializes derechaField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDerechaField() {
		if (derechaField == null) {
			derechaField = new JTextField();
			derechaField.setPreferredSize(new Dimension(45, 20));
			derechaField.setLocation(new Point(616, 18));
			derechaField.setSize(new Dimension(45, 20));
			derechaField.setText("10.0");
		}
		return derechaField;
	}

	/**
	 * This method initializes ejesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEjesButton() {
		if (ejesButton == null) {
			ejesButton = new JButton();
			ejesButton.setBounds(new Rectangle(667, 11, 80, 33));
			ejesButton.setText("Escalar");
			ejesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dibujoPanel.setArriba(Double.parseDouble(arribaField.getText()));
					dibujoPanel.setAbajo(Double.parseDouble(abajoField.getText()));
					dibujoPanel.setIzquierda(Double.parseDouble(izquierdaField.getText()));
					dibujoPanel.setDerecha(Double.parseDouble(derechaField.getText()));
					
					dibujoPanel.actualizarCoordenadasRelativas();
					dibujoPanel.repaint();
				}
			});
		}
		return ejesButton;
	}

	/**
	 * This method initializes raicesField	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getRaicesField() {
		if (raicesField == null) {
			raicesField = new JCheckBox();
			raicesField.setBounds(new Rectangle(450, this.getHeight()-85, 125, 26));
			raicesField.setText("Mostrar raices");
			raicesField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(raicesField.isSelected())
					{
						int n;
						double c;
						if(nField.getText() == "")
							 n = 2;
						else
							 n = Integer.parseInt(nField.getText());
						
						if(cField.getText() == "")
							 c = 1;
						else
							 c = Integer.parseInt(cField.getText());
						
						dibujoPanel.inicializar(n, c);
						dibujoPanel.muestraSoluciones();
					}
					else
					{
						dibujoPanel.ocultaSoluciones();
					}
				}
			});
		}
		return raicesField;
	}

	/**
	 * This method initializes iterarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getIterarButton() {
		if (iterarButton == null) {
			iterarButton = new JButton();
			iterarButton.setBounds(new Rectangle(580, this.getHeight()-85, 83, 28));
			iterarButton.setText("Iterar");
			iterarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(mostrandoIteraciones)
					{
						mostrandoIteraciones = false;
						dibujoPanel.ocultaIteraciones();
						iterarButton.setText("Iterar");
					}
					else
					{
						mostrandoIteraciones = true;
						modoIterar=true;
						iterarLabel.setText("Marca un punto");
					}
				}
			});
		}
		return iterarButton;
	}

	/**
	 * This method initializes barraMenu	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getBarraMenu() {
		if (barraMenu == null) {
			barraMenu = new JMenuBar();
			barraMenu.add(getAyudaMenu());
		}
		return barraMenu;
	}

	/**
	 * This method initializes ayudaMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getAyudaMenu() {
		if (ayudaMenu == null) {
			ayudaMenu = new JMenu();
			ayudaMenu.setText("Ayuda");
			ayudaMenu.setHorizontalAlignment(SwingConstants.LEADING);
			ayudaMenu.setComponentOrientation(ComponentOrientation.UNKNOWN);
			ayudaMenu.setHorizontalTextPosition(SwingConstants.TRAILING);
			ayudaMenu.add(getJMenuItem());
		}
		return ayudaMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (acercadeMenuItem == null) {
			acercadeMenuItem = new JMenuItem();
			acercadeMenuItem.setText("Acerca de...");
			acercadeMenuItem.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AcercaDe acercade = new AcercaDe();
					acercade.setVisible(true);
				}
			});
		}
		return acercadeMenuItem;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
