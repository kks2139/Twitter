import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class other extends JFrame implements ActionListener{

	JFrame f = new JFrame("board");
	JTextArea a = new JTextArea();
	JLabel l = new JLabel("board");
	JLabel ll = new JLabel();
	
	Font font = new Font("¸¼Àº°íµñ",Font.BOLD,20);
	Color c = new Color(0,150,255,50);
	
	other()
	{
		f.setLayout(null);
		l.setSize(100, 30);
		l.setLocation(40,20 );
		l.setFont(font);
		f.add(l);
		
		a.setSize(500, 470);
		a.setLocation(40, 50);
		f.add(a);
		
		f.setSize(600,600);
		otherboard();
		f.setVisible(true);
		f.setLayout(null);
		
		}
		
		protected void otherboard(){
		
			Connection con = null;
			String id;
			ResultSet rs2;
			String text=null;
			String i = myboard.t2.getText();
			try{
				Class.forName("com.mysql.jdbc.Driver");
		        con = (Connection) DriverManager.getConnection(
		            "jdbc:mysql://localhost/twitter_hw", "root", "kks2139");
		        
		        Statement st3 = con.createStatement();
		        rs2 = st3.executeQuery("select distinct article.ID,text from article, (select following from follow where ID='"+ i+"') as S where article.ID = S.following and article.rcv = S.following or article.rcv ='"+ i+ "' or article.ID ='"+i + "' and article.ID = '"+i+"' or article.rcv = '"+i+"' order by num desc");
				ResultSetMetaData rsmd = rs2.getMetaData();
				int colnum = rsmd.getColumnCount();
				a.setText("");
				while (rs2.next()) 
		        {
		           for (int k = 1; k <= colnum; k++)
		           {
		              a.append(rs2.getString(k)+" ");
		           }
		           a.append("\n");
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
		
	public void actionPerformed(ActionEvent ae)
	{
		
	}
}
