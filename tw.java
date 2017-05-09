import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class tw extends JFrame implements ActionListener
{
	JFrame f ;
	JButton j1;// 회원가입
	JButton j2 ;// 로그인 버튼
	JLabel l1,l2 ,l3 ;
	JPasswordField t2;
	JTextField t1;
	Font font , font2;
	Color c ;
	
	tw()// 여기에 GUI
	{	
	f = new JFrame("TWTTER");
	j1 = new JButton("New");// 회원가입
	j2 = new JButton("Log in");// 로그인 버튼
	 l1 = new JLabel("ID");
	 l2 = new JLabel("PW");
	l3 = new JLabel("Twitter");
	t2 = new JPasswordField(50);
	t1 = new JTextField(50);
	font = new Font("맑은고딕",Font.BOLD,60);
	font2 = new Font("맑은고딕",Font.BOLD,15);
	c = new Color(0,150,255,50);
	
	
		JLabel ll = new JLabel();
		ll.setOpaque(true);
		ll.setSize(800, 800);
		ll.setBackground(c);
		f.add(ll);
		
		l3.setSize(300,100);
		l3.setFont(font);
		l3.setLocation(300, 100);
		f.add(l3);

		f.setLayout(null);
		///////////////////////////// 버튼
		j1.setSize(80,30);
		j1.setLocation(360,430);
		j1.setFont(font2);
		f.add(j1);
		j2.setSize(80,30);
		j2.setLocation(360,390);
		j2.setFont(font2);
		f.add(j2);
		/////////////////////////////////////
		l1.setSize(100,100);
		l1.setLocation(300, 285);
		l1.setFont(font2);
		f.add(l1);
		l2.setSize(100,100);
		l2.setLocation(300, 315);
		l2.setFont(font2);
		f.add(l2);
		
		t1.setSize(100, 30);
		t2.setSize(100, 30);
		t1.setLocation(350,320);
		t2.setLocation(350,350);
		f.add(t1);
		f.add(t2);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,800);
		f.setResizable(false);
		f.setVisible(true);
		
		t2.addActionListener(this);
		j1.addActionListener(this);
		j2.addActionListener(this);
	}
	
	public static void main(String[] args)
	{	
		tw twitter = new tw();
		 	}
	
	public void actionPerformed(ActionEvent ae)// 버튼 클릭시 이벤트 발생
	{
		Connection con = null;
		Object o = ae.getSource();
		String id = t1.getText();
		String pw = t2.getText();
		ResultSet name;
		String n=null;
		ResultSet cmp = null;
		
		if(o == j1)
		{
			System.out.println("dfdfdfdfdfdfd");
			 new join();	 
		}	
		else if(o == j2)
		{
			try
			{	
				Class.forName("com.mysql.jdbc.Driver");
		        con = (Connection) DriverManager.getConnection(
		            "jdbc:mysql://localhost/twitter_hw", "root", "kks2139");
		        Statement st = con.createStatement();
		        Statement st1 = con.createStatement();
				
				cmp = st.executeQuery("select ID, pw  from user where ID = '"+id+"' and pw = '"+pw+"'");
				//System.out.println(cmp.next());
				name = st1.executeQuery("select name from user where ID = '"+id+"'");
				
				//ResultSetMetaData rsmd = name.getMetaData();
		        
				while(name.next()){
						n = name.getString(1);
				}
				System.out.println(n+" <-- name");
				if(cmp.next())// cmp 가 true면 - 이미 있는 아이디면...
				{
					f.setVisible(false);
					myboard m = new myboard(n,id,pw);
				}
				else
				{
					  JOptionPane.showMessageDialog(null, "  Check again!",
			                  "Check your infomation again!", JOptionPane.WARNING_MESSAGE);
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