import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class chpw extends JFrame implements ActionListener
{
	String fid;
	String ii;
	JFrame f = new JFrame("Change password");
	JButton b = new JButton("OK!");// È¸¿ø°¡ÀÔ
	JLabel l = new JLabel("ID");
	JLabel l2 = new JLabel("Past pw");
	JLabel l3 = new JLabel("New pw");
	JLabel ll = new JLabel();
	JTextField t = new JTextField(50);
	JTextField t2 = new JTextField(50);
	JTextField t3 = new JTextField(50);
	
	Font font = new Font("¸¼Àº°íµñ",Font.BOLD,11);
	Color c = new Color(0,150,255,50);
	
	chpw(String id,String pw)
	{
		ii = id;
		ll.setOpaque(true);
		ll.setSize(500, 500);
		ll.setBackground(c);
		f.add(ll);
		////////////////////////////////////////////
		//setid(id);
		b.setSize(60, 40);
		b.setLocation(215, 100);
		f.add(b);
		
		l.setSize(190, 30);
		l.setLocation(10, 20);
		l.setFont(font);
		f.add(l);

		l2.setSize(190, 30);
		l2.setLocation(10, 50);
		l2.setFont(font);
		f.add(l2);

		l3.setSize(190, 30);
		l3.setLocation(10, 80);
		l3.setFont(font);
		f.add(l3);
		
		t.setSize(150, 30);
		t.setLocation(60, 20);
		f.add(t);
		
		t2.setSize(150, 30);
		t2.setLocation(60, 50);
		f.add(t2);
		
		t3.setSize(150, 30);
		t3.setLocation(60, 80);
		f.add(t3);
		
		b.addActionListener(this);
		
		f.setLayout(null);
		f.setSize(300, 200);
		
		f.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		Connection con = null;
		Object o = ae.getSource();
		
		if(o == b)
		{
			JOptionPane.showMessageDialog(null, "Your password changed!",
	                  "Change pw", JOptionPane.OK_OPTION);	
			f.setVisible(false);
			
			try
			{	
				Class.forName("com.mysql.jdbc.Driver");
		        con = (Connection) DriverManager.getConnection(
		            "jdbc:mysql://localhost/twitter_hw", "root", "kks2139");
				Statement st = con.createStatement();
				st.executeUpdate("update user set pw='"+t3.getText()+"'"+" where ID = '"+ii+"'");
				//System.out.println(cmp.next());
				
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



















