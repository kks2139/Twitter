import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class join extends JFrame implements ActionListener
{
		JFrame f = new JFrame("Register");
		JButton j = new JButton("OK!");// È¸¿ø°¡ÀÔ
		JLabel l1 = new JLabel("Name");
		JLabel l2 = new JLabel("ID");
		JLabel l3 = new JLabel("PW");
		JLabel l = new JLabel("welcome !!");
		JTextField t1 = new JTextField(50);
		JTextField t2 = new JTextField(50);
		JPasswordField t3 = new JPasswordField(50);
		///////////////////////////////////////////////////////////
		JFrame jf = new JFrame("~ message ~");
		JButton jb = new JButton("OK!");
		JLabel bb = new JLabel();
		JLabel jl = new JLabel("You joined the Twitter!");
		Font ft = new Font("¸¼Àº°íµñ",Font.BOLD,30);
		
		Font font = new Font("¸¼Àº°íµñ",Font.BOLD,50);
		Font font2 = new Font("¸¼Àº°íµñ",Font.BOLD,15);
		Color c = new Color(0,150,255,50);
		
		JLabel ll = new JLabel();
		
		join()
		{
		f.setLayout(null);
		ll.setOpaque(true);
		ll.setSize(800, 800);
		ll.setBackground(c);
		f.add(ll);
		
		l.setSize(400,150);
		l.setFont(font);
		l.setLocation(270, 130);
		f.add(l);
		
		j.setSize(80,30);
		j.setLocation(360,430);
		j.setFont(font2);
		f.add(j);
		/////////////////////////////////////
		l1.setSize(100,100);
		l1.setLocation(300, 285);
		l1.setFont(font2);
		f.add(l1);
		l2.setSize(100,100);
		l2.setLocation(300, 315);
		l2.setFont(font2);
		f.add(l2);
		l3.setSize(100,100);
		l3.setLocation(300, 345);
		l3.setFont(font2);
		f.add(l3);
		
		t1.setSize(100, 30);
		t2.setSize(100, 30);
		t3.setSize(100, 30);
		t1.setLocation(350,320);
		t2.setLocation(350,350);
		t3.setLocation(350,380);
		
		f.add(t1);
		f.add(t2);
		f.add(t3);
		
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,800);
		f.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//t3.addActionListener(this);
		j.addActionListener(this);
		}
		
		public void welcom()
		{
			jf.setLayout(null);
			jf.setSize(450, 300);
			//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setResizable(false);
			jf.setVisible(true);
			
			bb.setOpaque(true);
			bb.setSize(500, 400);
			bb.setBackground(c);
			jf.add(bb);
			
			jl.setSize(400, 50);
			jl.setLocation(45, 100);
			jl.setFont(ft);
			jf.add(jl);
			
			jb.setSize(50,30);
			jb.setLocation(100, 100);
			jf.add(jb);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jb.addActionListener(null);
		}

		
		public void actionPerformed(ActionEvent ae)
		{
			Connection con = null;
			Object o = ae.getSource();
			String name = t1.getText();
			String id = t2.getText();
			String pw = t3.getText();
			ResultSet cmp = null;
			
			
			if(o == j)
			{System.out.println(" "+name+" "+id+" "+pw+" ");
				try
				{	
					Class.forName("com.mysql.jdbc.Driver");
			        con = (Connection) DriverManager.getConnection(
			            "jdbc:mysql://localhost/twitter_hw", "root", "kks2139");
					Statement st = con.createStatement();
					
					cmp = st.executeQuery("select ID from user where ID = '"+id+"'");
					//System.out.println(cmp.next());
					
					if(cmp.next())// cmp °¡ true¸é - ÀÌ¹Ì ÀÖ´Â ¾ÆÀÌµð¸é...
					{
						JOptionPane.showMessageDialog(null, "That ID already exist!",
				                  "!!!!!", JOptionPane.OK_OPTION);	
					}
					else
					{System.out.println(" "+name+" "+id+" "+pw+" //////ppp");
					st.executeUpdate("insert into user values ('"+id+"','"+pw+"','"+name+"')");
					
					JOptionPane.showMessageDialog(null, "WELCOME TO TWITTER",
			                  "JOIN", JOptionPane.OK_OPTION);	
					f.setVisible(false);
					}
				}
				catch (SQLException e)
			    {
			        e.printStackTrace();
			    }
			    catch (ClassNotFoundException e)
			    {
			        e.printStackTrace();
			    }
			}
				
		}
}


