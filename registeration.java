

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class registeration extends JFrame implements ActionListener {
	private boolean isp1turn=true;
	private int count=0;
	private static final long serialVersionUID=1L;
	private Player p1,p2;
	private JFrame frame;
	private JLabel flabel;
	private JLabel slabel;
	private JLabel turnlabel;
	private JLabel lastlabel;
	JButton bt;
	JButton bt1;
	JLabel panellabel;
	JLabel lblPlayerName;
	JLabel lblPlayerCharacter;
	JLabel lblPlayerCharacter_2;
	JTextField tF;
	JTextField textField_1;
	JButton btNewButton;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	
	JButton[] btns=new JButton[9];
	public registeration() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 865, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TicTacToe");
		lblNewLabel.setForeground(new Color(255, 0, 51));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 36));
		lblNewLabel.setBounds(324, 10, 248, 71);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(277, 304, 326, -118);
		frame.getContentPane().add(desktopPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(10, 63, 831, 590);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel gamePanel=new JPanel();
		gamePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		gamePanel.setBackground(new Color(0, 0, 51));
		gamePanel.setBounds(3, 0, 831, 590);
		gamePanel.setLayout(null);
		gamePanel.setVisible(false);
		
		JButton resetlabel = new JButton("jnjk");
		resetlabel.setForeground(new Color(255,255,255));
		resetlabel.setBackground(new Color(0, 0, 51));
		resetlabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		resetlabel.setBorder(null);
		resetlabel.setBounds(750, 0, 562, 56);
		gamePanel.add(resetlabel);
		resetlabel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			isp1turn=true;
			
				start();
			}
		});
		
		
		flabel = new JLabel("P1:       X");
		flabel.setForeground(Color.WHITE);
		flabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		flabel.setBounds(558, 32, 262, 36);
		gamePanel.add(flabel);
		
		slabel = new JLabel("P2:       0");
		slabel.setForeground(Color.WHITE);
		slabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		slabel.setBounds(558, 64, 262, 36);
			gamePanel.add(slabel);
			
			turnlabel = new JLabel("Player1's turn");
			turnlabel.setForeground(Color.RED);
			turnlabel.setFont(new Font("Sylfaen", Font.BOLD, 28));
			turnlabel.setBounds(3, 60, 810, 58);
			gamePanel.add(turnlabel);
			
			JPanel btnpanel = new JPanel();
			btnpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnpanel.setBounds(199, 102, 329, 329);
			GridLayout grid=new GridLayout(3,3);
			btnpanel.setLayout(grid);
			gamePanel.add(btnpanel);
			
			lastlabel = new JLabel("");
			lastlabel.setForeground(Color.RED);
			lastlabel.setFont(new Font("Sylfaen", Font.BOLD, 28));
			lastlabel.setBounds(200, 420, 500, 100);
			gamePanel.add(lastlabel);
			
			for(int i=0;i<9;i++) {
				btns[i] = new JButton("");
				btns[i].setFont(new Font("Tahoma", Font.PLAIN, 26));
				btns[i].setForeground(new Color(255,255,255));
				btns[i].setBackground(new Color(0,0,51));
				btns[i].addActionListener(this);
				btnpanel.add(btns[i]);
			}
			
			bt=new JButton("Play Again");
			bt.setFont(new Font("Tahoma", Font.PLAIN, 26));
			bt.setForeground(new Color(255,255,255));
			bt.setBackground(new Color(255, 153, 102));
			bt.setBounds(10, 500, 170, 40);
			gamePanel.add(bt);
			bt.setVisible(false);
			bt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				bt.setVisible(false);
				bt1.setVisible(false);
				lastlabel.setText("");
				isp1turn=true;
					start();	
					java.util.Date dt=new Date();
					java.sql.Date dtsql=new java.sql.Date(dt.getYear(),dt.getMonth(),dt.getDate());
					try{
						Class.forName("com.mysql.jdbc.Driver");
							
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerecord","root", "");

							
							Statement stmt=con.createStatement();
							stmt.executeUpdate("create database if not exists gamerecord");
							stmt.executeUpdate("create table if not exists Tictactoe"+"(Player_1name varchar(50),Player_2name varchar(50),Winner varchar(50),Date date)");
							
							PreparedStatement pstmt=con.prepareStatement("Insert into tictactoe(Player_1name,Player_2name,Date) values(?,?,?)");
							
							pstmt.setString(1, p1.getname());
							pstmt.setString(2, p2.getname());
							pstmt.setDate(3, dtsql);
							pstmt.executeUpdate();
							
								con.close();
							}
							
							catch(SQLException e1) {
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				}
			});
			
			bt1=new JButton("New Match");
			bt1.setFont(new Font("Tahoma", Font.PLAIN, 26));
			bt1.setForeground(new Color(255,255,255));
			bt1.setBackground(new Color(255, 153, 102));
			bt1.setBounds(650, 500, 170, 40);
			gamePanel.add(bt1);
			bt1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panel.removeAll();
					panel.revalidate();
					panel.repaint();
					
					panel.add(panellabel);
					panel.add(lblPlayerName);
					panel.add(lblPlayerCharacter);
					panel.add(lblPlayerCharacter_2);
					panel.add(tF);
					panel.add(textField_1);
					panel.add(btNewButton);
					panel.add(lblNewLabel_1);
					panel.add(lblNewLabel_2);
					bt.setVisible(false);
					bt1.setVisible(false);
					lastlabel.setText("");
					isp1turn=true;
				}
			});
			bt1.setVisible(false);
			
			panellabel = new JLabel("Player 1 Name:");
			panellabel.setForeground(new Color(255, 255, 255));
			panellabel.setFont(new Font("Stencil", Font.PLAIN, 26));
			panellabel.setBounds(3, 10, 203, 85);
			panel.add(panellabel);
			
			lblPlayerName = new JLabel("Player 2 Name:");
			lblPlayerName.setForeground(new Color(255, 255, 255));
			lblPlayerName.setFont(new Font("Stencil", Font.PLAIN, 26));
			lblPlayerName.setBounds(3, 195, 203, 85);
			panel.add(lblPlayerName);
			
			lblPlayerCharacter = new JLabel("Player 1 character:");
			lblPlayerCharacter.setForeground(new Color(255, 255, 255));
			lblPlayerCharacter.setFont(new Font("Stencil", Font.PLAIN, 26));
			lblPlayerCharacter.setBounds(3, 83, 297, 85);
			panel.add(lblPlayerCharacter);
			
			lblPlayerCharacter_2 = new JLabel("Player 2 character:");
			lblPlayerCharacter_2.setForeground(new Color(255, 255, 255));
			lblPlayerCharacter_2.setFont(new Font("Stencil", Font.PLAIN, 26));
			lblPlayerCharacter_2.setBounds(3, 276, 297, 85);
			panel.add(lblPlayerCharacter_2);
			
			tF = new JTextField();
			tF.setBounds(218, 27, 581, 34);
			tF.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panel.add(tF);
			tF.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(218, 212, 581, 34);
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panel.add(textField_1);
		
			btNewButton = new JButton("Start");
			btNewButton.setBackground(new Color(255, 153, 102));
			btNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			btNewButton.setBounds(288, 365, 251, 72);
			btNewButton.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
					
					java.util.Date dt=new Date();
					java.sql.Date dtsql=new java.sql.Date(dt.getYear(),dt.getMonth(),dt.getDate());
					if(tF.getText()!="" && textField_1.getText()!="") {
					p1=new Player(tF.getText(),'X');
					p2=new Player(textField_1.getText(),'O');
				
					start();
					panel.removeAll(); 
				        panel.repaint();
				        panel.revalidate();
				        
				        //add new panel
				        panel.add(gamePanel);
				        gamePanel.setVisible(true);
				        panel.repaint();
				        panel.revalidate();
					}
				
				try{
			Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerecord","root", "");

				
				Statement stmt=con.createStatement();
				stmt.executeUpdate("create database if not exists gamerecord");
				stmt.executeUpdate("create table if not exists Tictactoe"+"(Player_1name varchar(50),Player_2name varchar(50),Winner varchar(50),Date date)");
				
				PreparedStatement pstmt=con.prepareStatement("Insert into tictactoe(Player_1name,Player_2name,Date) values(?,?,?)");
				
				pstmt.setString(1, p1.getname());
				pstmt.setString(2, p2.getname());
				pstmt.setDate(3, dtsql);
				pstmt.executeUpdate();
				
					con.close();
				}
				
				catch(SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			});

	panel.add(btNewButton);
	lblNewLabel_1 = new JLabel("X");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
	lblNewLabel_1.setForeground(Color.WHITE);
	lblNewLabel_1.setBounds(310, 103, 60, 34);
	panel.add(lblNewLabel_1);
	lblNewLabel_2 = new JLabel("O");
	lblNewLabel_2.setForeground(Color.WHITE);
	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
	lblNewLabel_2.setBounds(310, 293, 45, 34);
	panel.add(lblNewLabel_2);
			
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registeration window = new registeration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
public void start() {
	count=0;
	flabel.setText(p1.getname()+":   "+p1.getsymbol());
	slabel.setText(p2.getname()+":   "+p2.getsymbol());
	turnlabel.setText(p1.getname()+"'s turn");
	for(int i=0;i<9;i++) {
		btns[i].setText("");
		btns[i].setEnabled(true);
		btns[i].setBackground(new Color(0,0,51));
	}
}
public void winner(int n,int a,int b,int c) {
for(int i=0;i<9;i++) {
	btns[i].setEnabled(false);
	btns[i].setBackground(new Color(0,0,51));
}
if(a!=b && a!=c) {
btns[a].setBackground(new Color(102,204,153));
btns[b].setBackground(new Color(102,204,153));
btns[c].setBackground(new Color(102,204,153));
}
	if(n==1) {
		lastlabel.setText(p1.getname()+" is the Winner");
		insert(p1.getname());
		bt.setVisible(true);
		bt1.setVisible(true);
	}
	else if (n==2) {
		lastlabel.setText(p2.getname()+" is the Winner");
insert(p2.getname());
		bt.setVisible(true);
		bt1.setVisible(true);
	}
	else if(n==3) {
		lastlabel.setText("Draw");
		insert("Draw");
		bt.setVisible(true);
		bt1.setVisible(true);
	}
}
public void insert(String win){
	java.util.Date dt=new Date();
	java.sql.Date dtsql=new java.sql.Date(dt.getYear(),dt.getMonth(),dt.getDate());
	
	try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerecord","root", "");
	
	Statement stmt=con.createStatement();
	stmt.executeUpdate("create database if not exists tictactoe");
	stmt.executeUpdate("create table if not exists Tictactoe"+"(Player_1name varchar(50),Player_2name varchar(50),winner varchar(50),Date date)");
	
	PreparedStatement pstmt=con.prepareStatement("update tictactoe set winner=? where Player_1name=? and Player_2name =? and Date=? and winner is null");
	
	pstmt.setString(1, win);
	pstmt.setString(2, p1.getname());
	pstmt.setString(3, p2.getname());
	pstmt.setDate(4, dtsql);

	pstmt.executeUpdate();
	
		con.close();
}
	catch (ClassNotFoundException e) 
	{
		e.printStackTrace();
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
}
public void check() {
	if(count==9) {
		winner(3,0,0,0);
	}
	if(btns[0].getText()==btns[1].getText() && btns[0].getText()==btns[2].getText()) {
		if(btns[0].getText().toString().charAt(0)=='X') {
			winner(1,0,1,2);
		}else if(btns[0].getText().toString().charAt(0)=='O'){
			winner(2,0,1,2);
		}
		
	}
	else if(btns[0].getText()==btns[3].getText() && btns[0].getText()==btns[6].getText()) {
		if(btns[0].getText()=="X") {
			winner(1,0,3,6);
		}else if(btns[0].getText()=="O") {
			winner(2,0,3,6);
		}
	}
	else if(btns[3].getText()==btns[4].getText() && btns[3].getText()==btns[5].getText()) {
		if(btns[3].getText()=="X") {
			winner(1,3,4,5);
		}else if(btns[3].getText()=="O") {
			winner(2,3,4,5);
		}
	}
	else if(btns[6].getText()==btns[7].getText() && btns[6].getText()==btns[8].getText()) {
		if(btns[6].getText()=="X") {
			winner(1,6,7,8);
		}else if(btns[0].getText()=="O") {
			winner(2,6,7,8);
		}
	}
	else if(btns[1].getText()==btns[4].getText() && btns[1].getText()==btns[7].getText()) {
			if(btns[1].getText()=="X") {
				winner(1,1,4,7);
			}else if(btns[1].getText()=="O"){
				winner(2,1,4,7);
			}
		}
	else if(btns[2].getText()==btns[5].getText() && btns[2].getText()==btns[8].getText()) {
				if(btns[2].getText()=="X") {
					winner(1,2,5,8);
				}else if(btns[2].getText()=="O"){
					winner(2,2,5,8);
				}
			}
	else if(btns[0].getText()==btns[4].getText() && btns[0].getText()==btns[8].getText()) {
					if(btns[0].getText()=="X") {
						winner(1,0,4,8);
					}else if(btns[0].getText()=="O"){
						winner(2,0,4,8);
					}
				}
	else if(btns[2].getText()==btns[4].getText() && btns[2].getText()==btns[6].getText()) {
						if(btns[2].getText()=="X") {
							winner(1,2,4,6);
						}else if(btns[2].getText()=="O"){
							winner(2,2,4,6);
						}
					}
						
}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<9;i++) {
		if(e.getSource()==btns[i]) {
			if(isp1turn && btns[i].getText()=="") {
				
				btns[i].setText("X");
				btns[i].setBackground(new Color(255,0,0));
				turnlabel.setText(p2.getname()+"'s turn");
				isp1turn=false;
				count++;
				check();
				
			}
			else if(!isp1turn && btns[i].getText()=="") {
				btns[i].setText("O");
				btns[i].setBackground(new Color(51,102,255));
				turnlabel.setText(p1.getname()+"'s turn");
				isp1turn=true;
				count++;
				check();
			}
		}
		}
		// TODO Auto-generated method stub
		
	}
}
