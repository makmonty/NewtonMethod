package ventanas;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.KeyEvent;

public class AcercaDe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JDesktopPane jDesktopPane = null;
	private JTextPane jTextPane = null;
	/**
	 * This is the default constructor
	 */
	public AcercaDe() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(474, 391);
		this.setContentPane(getJContentPane());
		this.setTitle("Acerca de");
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
			jDesktopPane = new JDesktopPane();
			jDesktopPane.add(getJTextPane(), null);
		}
		return jDesktopPane;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setBounds(new Rectangle(16, 15, 430, 293));
			jTextPane.setEditable(false);
			jTextPane.setContentType("text/html");
			jTextPane.setText("<html>\n  <head>\n\n  </head>\n  <body style=\"font-family:helvetica; text-align:center\">\n    <h1>M&eacute;todo de Newton</h1><h2>v1.0</h2><p>Aplicaci&oacute;n desarrollada por:</p>\n<p>&Aacute;ngel Brasero Moreno &lt;<a href=\"mailto:angel.brasero@gmail.com\">angel.brasero@gmail.com</a>&gt;<br />Nabil Chiboub &lt;<a href=\"mailto:nabilusss@hotmail.com\">nabilusss@hotmail.com</a>&gt;</p>\n<p>Para la asignatura<br /><a href=\"http://ma1.eii.us.es/material/AJM_sd/web/sistemas_dinamicos/php/index.php\">Sistemas Din&aacute;micos</a></p>\n<p><a href=\"http://www.informatica.us.es\">Escuela T&eacute;cnica Superior de Ingenier&iacute;a Inform&aacute;tica de Sevilla</a></p>  </body>\n</html>\n");
		}
		return jTextPane;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
