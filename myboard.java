import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class myboard extends JFrame implements ActionListener {
	String idd, name, p;
	JButton b = new JButton("Enter");// 글쓰는 창
	JButton bb = new JButton("follow");
	JButton cp = new JButton("pw change");
	JButton ob = new JButton("other board");
	JLabel ll = new JLabel();
	JTextField t = new JTextField(150);
	static JTextField t2 = new JTextField(150);

	JTextField ff = new JTextField(150);
	// //////////////////////////////////////////////////
	JLabel l1 = new JLabel("News!");
	JLabel l2 = new JLabel("following");
	JLabel l3 = new JLabel("followed by");

	JTextArea a1 = new JTextArea();
	JTextArea a2 = new JTextArea();
	JTextArea a3 = new JTextArea();

	Font ft = new Font("맑은고딕", Font.BOLD, 30);
	Font font = new Font("맑은고딕", Font.BOLD, 50);
	Font font2 = new Font("맑은고딕", Font.BOLD, 15);
	Color c = new Color(0, 150, 255, 50);

	myboard(String n, String id, String pw)// 여기에 GUI
	{
		name = n;
		p = pw;
		idd = id;

		JFrame f = new JFrame("Welcom! " + name + " ~");
		JLabel ll = new JLabel();

		f.setLayout(null);
		ll.setOpaque(true);
		ll.setSize(1000, 1000);
		ll.setBackground(c);
		f.add(ll);

		l1.setSize(100, 20);
		l1.setLocation(12, 70);
		l1.setFont(font2);
		f.add(l1);
		l2.setSize(100, 20);
		l2.setLocation(502, 70);
		l2.setFont(font2);
		f.add(l2);
		l3.setSize(100, 30);
		l3.setLocation(502, 380);
		l3.setFont(font2);
		f.add(l3);

		a1.setSize(400, 500);
		a1.setLocation(10, 100);
		a1.setBackground(new Color(255, 255, 255));
		f.add(a1);

		a2.setSize(200, 280);
		a2.setLocation(500, 100);
		a2.setBackground(new Color(255, 255, 255));
		f.add(a2);

		a3.setSize(200, 260);
		a3.setLocation(500, 410);
		a3.setBackground(new Color(255, 255, 255));
		f.add(a3);

		t.setSize(320, 60);
		t.setLocation(10, 600);
		f.add(t);

		b.setSize(80, 60);
		// b.setPreferredSize(new Dimension(30,30));
		b.setLocation(330, 600);
		f.add(b);

		bb.setSize(100, 30);
		bb.setLocation(600, 690);
		f.add(bb);

		ff.setSize(100, 30);
		ff.setLocation(600, 720);
		f.add(ff);

		cp.setSize(100, 30);
		cp.setLocation(500, 690);
		f.add(cp);

		ob.setSize(100, 30);
		ob.setLocation(600, 70);
		f.add(ob);
		t2.setSize(100, 30);
		t2.setLocation(600, 40);
		f.add(t2);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 800);
		f.setResizable(false);
		news();
		show_follow();
		f.setVisible(true);
		//news();

		cp.addActionListener(this);
		bb.addActionListener(this);
		b.addActionListener(this);
		ob.addActionListener(this);
		// j1.addActionListener(this);
		// j2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)// 버튼 클릭시 이벤트 발생
	{
		Connection con = null;
		Object o = ae.getSource();
		ResultSet rs, rs2;
		String text = null;
		news();
		if (o == cp) {
			new chpw(idd, p);
		}

		if (o == bb) {
			// insert
			String addList = "insert into follow values('" + idd + "','"
					+ ff.getText() + "')";

			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = (Connection) DriverManager.getConnection(
						"jdbc:mysql://localhost/twitter_hw", "root", "kks2139");

				Statement st3 = con.createStatement();
				st3.executeUpdate(addList);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			show_follow();
			news();
			ff.setText("");

		}
		if (o == b) {
			
			String addList2 = "insert into article(ID,text,rcv) values('" + idd
					+ "','" + t.getText() + "','" + idd + "')";
			
			news();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = (Connection) DriverManager.getConnection(
						"jdbc:mysql://localhost/twitter_hw", "root", "kks2139");
				
				if (t.getText().startsWith("@")) {

					// System.out.println(s);
				String	s = (t.getText().substring(1, t.getText().indexOf(" ")));// 차단목록을
				String addList3 = "insert into article(ID,text,rcv) values('" + idd
						+ "','" + t.getText() + "','" + s + "')";																// 여러개를
																				// 기억할
																				// 수
																				// 있게
																				// 만든다
					System.out.print(s/* str.substring(7,str.indexOf(">")) */);// //
																				// 잘
																				// 짤렸나
																				// 확인

					Statement stmt = con.createStatement();
					stmt.executeUpdate(addList3);
					t.setText("");
					news();
				} 
				else {
					
					Statement st4 = con.createStatement();
					st4.executeUpdate(addList2);
					t.setText("");
					news();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			news();

		}
		if (o == ob)// 다른유저의 보드 보기
		{
			new other();

		}
	}

	protected void news() {
		Connection con = null;
		String i = idd;
		ResultSet rs4;
		String text = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/twitter_hw", "root", "kks2139");

			Statement st3 = con.createStatement();
			rs4 = st3.executeQuery("select distinct article.ID,text from article, (select following from follow where ID='"+ i+"') as S where article.ID = S.following and article.rcv = S.following or article.rcv ='"+ i+ "' or article.ID ='"+i + "' and article.ID = '"+i+"' or article.rcv = '"+i+"' order by num desc");
			ResultSetMetaData rsmd = rs4.getMetaData();
			int colnum = rsmd.getColumnCount();
			a1.setText("");
			while (rs4.next()) {
				for (int k = 1; k <= colnum; k++) {
					a1.append(rs4.getString(k) + " ");
				}
				a1.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	protected void show_follow() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String fing = "select following from follow where ID ='" + idd + "'";
		String fwer = "select ID from follow where following = '" + idd + "'";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/twitter_hw", "root", "kks2139");

			stmt = con.createStatement();
			rs = stmt.executeQuery(fing);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();
			a2.setText("");
			while (rs.next()) {
				for (int k = 1; k <= colnum; k++) {
					a2.append(rs.getString(k) + " ");
				}
				a2.append("\n");
			}

			stmt = con.createStatement();
			rs = stmt.executeQuery(fwer);
			rsmd = rs.getMetaData();
			colnum = rsmd.getColumnCount();
			a3.setText("");
			while (rs.next()) {
				for (int k = 1; k <= colnum; k++) {
					a3.append(rs.getString(k) + " ");
				}
				a3.append("\n");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
