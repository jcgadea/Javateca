package app;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

@SuppressWarnings("serial")
public class Splash extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Splash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 470);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setOpaque(false);
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Splash.class.getResource("/resources/splash.png")));
		lblNewLabel.setBounds(0, 0, 640, 426);
		layeredPane.add(lblNewLabel);
		
		JLabel lblJavateca = new JLabel("JavaTeca");
		lblJavateca.setFont(new Font("Open Sans", Font.PLAIN, 50));
		layeredPane.setLayer(lblJavateca, 1);
		lblJavateca.setBounds(394, 139, 202, 75);
		layeredPane.add(lblJavateca);
		
		JLabel lblV = new JLabel("v0.1");
		lblV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		layeredPane.setLayer(lblV, 1);
		lblV.setBounds(577, 253, 31, 20);
		layeredPane.add(lblV);
		
		JLabel label = new JLabel("");
		layeredPane.setLayer(label, 1);
		label.setIcon(new ImageIcon(Splash.class.getResource("/resources/GPL3.png")));
		label.setBounds(516, 382, 80, 30);
		layeredPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u00A9 2018 Jos\u00E9 Carlos Gadea");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		layeredPane.setLayer(lblNewLabel_1, 1);
		lblNewLabel_1.setBounds(356, 398, 150, 14);
		layeredPane.add(lblNewLabel_1);
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

}
