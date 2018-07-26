package com.coviam.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class TESTLINK2 extends JFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static String filelist = "nothing";
	public static String filepath = null;
	//private JTextField Username;
	//private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TESTLINK2 frame = new TESTLINK2();
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
	public TESTLINK2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnAboutUs = new JMenu("Help");
		mnAboutUs.setBackground(Color.BLACK);
		menuBar.add(mnAboutUs);
		
		JMenuItem mntmVersion = new JMenuItem("Version");
		mntmVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,"Version 1.1x");
			}
		});
		mnAboutUs.add(mntmVersion);
		
		JSeparator separator_1 = new JSeparator();
		mnAboutUs.add(separator_1);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mntmAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,"For any help, Please contact Avinash - 999999999999");
			}
		});
		mnAboutUs.add(mntmAboutUs);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("List.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblSampleTest = new JLabel("Testlink : Excel to Xml Convertor");
		lblSampleTest.setForeground(Color.DARK_GRAY);
		lblSampleTest.setBounds(99, 72, 258, 17);
		lblSampleTest.setFont(new Font("Helvetica", Font.BOLD, 16));
		contentPane.add(lblSampleTest);
		

		
		JLabel lblOutput = new JLabel("");
		lblOutput.setBounds(75, 215, 444, 43);
		contentPane.add(lblOutput);
		
		JButton btnConvert = new JButton("Convert XML");
		btnConvert.setForeground(Color.DARK_GRAY);
		btnConvert.setBackground(Color.RED);
		btnConvert.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnConvert.setBounds(143, 184, 148, 29);
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialogue(null,”Successfully uploaded”);
				   try {
						StartTestLinkAutomation.Controller(filelist,filepath);
					} catch (ParserConfigurationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				JOptionPane.showMessageDialog(null,"XML conversion completed");
				final JFileChooser fc = new JFileChooser();
				
			}
		});
		contentPane.add(btnConvert);
		
		JButton btnNewButton = new JButton("Upload Excel");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnNewButton.setBounds(143, 143, 148, 29);
		//btnNewButton.setBackground(getBackground(Color.LIGHTGREY));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser chooser = new JFileChooser();
			        chooser.setMultiSelectionEnabled(true);
			        int option = chooser.showOpenDialog(TESTLINK2.this);
			        
			        if (option == JFileChooser.APPROVE_OPTION) {
			
			            File df = chooser.getCurrentDirectory();
			            File[] sf = chooser.getSelectedFiles();
			          
			          if (sf.length > 0) filelist = sf[0].getName();
			          for (int i = 1; i < sf.length; i++) {
			            filelist += ", " + sf[i].getName();
			          }
			          //String currentDir=System.getProperty("user.dir");
			          lblOutput.setText("File Chosen: "+ filelist);
			          filelist=df +"/"+ filelist;
			          filepath=df+"/";
			          
			        }
			        else {
			        	lblOutput.setText("You canceled.");
			        	
			        }
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnMerge = new JButton("Download Excel");
		btnMerge.setForeground(Color.DARK_GRAY);
		btnMerge.setBackground(Color.RED);
		btnMerge.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runtime rt = Runtime.getRuntime();
				String url = "https://docs.google.com/spreadsheets/d/1ZN72FoV7FhhKHcvG66xAYxmvwp1lt9mvhOZCBlWaHM8/edit#gid=82737032";
				try {
					rt.exec( "open " + url);
					//JOptionPane.showMessageDialog(null," Sorry link accesss only supported on MAC: Download here https://docs.google.com/spreadsheets/d/1ZN72FoV7FhhKHcvG66xAYxmvwp1lt9mvhOZCBlWaHM8/edit#gid=82737032");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null," Sorry link accesss only supported on MAC: Download here: https://docs.google.com/spreadsheets/d/1ZN72FoV7FhhKHcvG66xAYxmvwp1lt9mvhOZCBlWaHM8/edit#gid=82737032");
					e1.printStackTrace();
				}
			}
		});
		btnMerge.setBounds(143, 101, 148, 30);
		contentPane.add(btnMerge);
		
	
		
		ImageIcon background = new ImageIcon("/Users/avinash.t/Documents/coviam2.png");
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(124, -16, 180, 96);
		label.setIcon(background);
		
		JPanel panel = new JPanel();
		panel.setBounds(348, 6, 96, 56);
		contentPane.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 215, 1, 12);
		contentPane.add(separator);
		
		
		
		
	} 
}
