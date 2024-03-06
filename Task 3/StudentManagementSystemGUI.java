import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
class Student implements Serializable {									//******** CONTACT CLASS: INDIVIDUAL CONTACTS ********//
	private String name;
	private String rollno;
	private String grade;
	private String age;
	private String contact;
	private String address;

	//name, roll no, grade , age, contact, address
	public Student(String name, String rollno, String grade, String age, String contact, String address) {
		this.name = name;
		this.rollno = rollno;
		this.grade = grade;
		this.age = age;
		this.contact = contact;
		this.address = address;	
	}

	public String getName() {
		return name;
	}

	public String getRollNo() {
		return rollno;
	}

	public String getGrade() {
		return grade;
	}

	public String getAge() {
		return age;
	}

	public String getContact() {
		return contact;
	}

	public String getAddress() {
		return address;
	}
}

class StudentManagementSystem {														//******** ADDRESSBOOK CLASS: CLASS TO MANAGE THE COLLECTION THE CONTACTS ********//
	private ArrayList<Student> data;

	public StudentManagementSystem() {
		data = new ArrayList<>();
	}

	public void addData(Student student) {
		data.add(student);
	}

	public void removeData(Student student) {
		data.remove(student);
	}

	public Student searchData(String name) {
		for (Student student : data) {
			if (student.getName().equalsIgnoreCase(name)) {
				return student;
			}
		}
		return null;
	}

	public ArrayList<Student> getAllData() {
		return data;
	}

	public void saveToFile(String fileName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(data);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadFromFile(String fileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			data = (ArrayList<Student>) ois.readObject();
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}


class StudentManagementSystemGUI {													//******** ADDRESSBOOKGUI CLASS: GUI IMPLEMENTATION ********//
	private StudentManagementSystem sys;
	private JFrame frame;
	private JTextField txtname, txtrollno, txtgrade, txtage, txtcontact, txtaddress;
	private JTable table;

	public StudentManagementSystemGUI(StudentManagementSystem sys) {
		this.sys = sys;
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/zeus_logo.jpg"));
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("TASK 3");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "INPUT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(40, 30, 900, 150);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblname = new JLabel("NAME");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname.setBounds(120, 40, 80, 25);
		panel.add(lblname);

		JLabel lblroll = new JLabel("ROLL NO.");
		lblroll.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblroll.setBounds(352, 40, 80, 25);
		panel.add(lblroll);

		JLabel lblgrade = new JLabel("GRADE");
		lblgrade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblgrade.setBounds(610, 40, 80, 25);
		panel.add(lblgrade);
		
		JLabel lblage = new JLabel("AGE");
		lblage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblage.setBounds(120, 90, 80, 25);
		panel.add(lblage);

		JLabel lblcon = new JLabel("CONTACT NO.");
		lblcon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcon.setBounds(330, 90, 80, 25);
		panel.add(lblcon);

		JLabel lbladd = new JLabel("ADDRESS");
		lbladd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbladd.setBounds(600, 90, 80, 25);
		panel.add(lbladd);
		

		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(160, 35, 100, 30);
		panel.add(txtname);

		txtrollno = new JTextField();
		txtrollno.setColumns(10);
		txtrollno.setBounds(420, 35, 100, 30);
		panel.add(txtrollno);

		txtgrade = new JTextField();
		txtgrade.setColumns(10);
		txtgrade.setBounds(660, 35, 100, 30);
		panel.add(txtgrade);

		txtage = new JTextField();
		txtname.setColumns(10);
		txtage.setBounds(160, 85, 100, 30);
		panel.add(txtage);

		txtcontact = new JTextField();
		txtcontact.setColumns(10);
		txtcontact.setBounds(420, 85, 100, 30);
		panel.add(txtcontact);

		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(660, 85, 100, 30);
		panel.add(txtaddress);


		JButton btnadd = new JButton("SAVE");							//******** SAVE BUTTON ********//
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtname.getText();
				String rollno = txtrollno.getText();
				String grade = txtgrade.getText();
				String age = txtage.getText();
				String contact = txtcontact.getText();
				String address = txtaddress.getText();

				if (!name.isEmpty() && !rollno.isEmpty() &&  !age.isEmpty() && !grade.isEmpty() && !contact.isEmpty() && !address.isEmpty()) {
					sys.addData(new Student(name, rollno, grade, age, contact, address));

					LoadTable();

					try { 												//******** FILE HANDLING ********//
						FileWriter x = new FileWriter("file3.txt",true); 

						x.write("NAME: " +name + "\n"); 
						x.write("ROLL NO: " +rollno + "\n"); 
						x.write("GRADE: "+grade + "\n");
						x.write("AGE: " +age + "\n"); 
						x.write("CONTACT: " +contact + "\n"); 
						x.write("ADDRESS: "+address + "\n");
						x.write("\n");						
						x.close(); 					
					} 

					catch(Exception ae){ 
						System.out.println(ae); 
					}
					clearTxtField();
				} 
				else {
					JOptionPane.showMessageDialog(null, "Please fill in all fields.");
				}
			}
		});
		btnadd.setBounds(150, 210, 100, 30);
		frame.getContentPane().add(btnadd);


		JButton btnrem = new JButton("DELETE");							//******** DELETE BUTTON ********//
		btnrem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				Student student = sys.searchData(name);
				if (student != null) {
					sys.removeData(student);
					LoadTable();
					clearTxtField();
				} 
				else {
					JOptionPane.showMessageDialog(null, "NO DATA FOUND");
				}
			}
		});
		btnrem.setBounds(350, 210, 100, 30);
		frame.getContentPane().add(btnrem);


		JButton btnsrc = new JButton("SEARCH");							//******** SEARCH BUTTON ********//
		btnsrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				Student student = sys.searchData(name);

				if (student != null) {
					txtrollno.setText(student.getRollNo());
					txtgrade.setText(student.getGrade());
					txtage.setText(student.getAge());
					txtcontact.setText(student.getContact());
					txtaddress.setText(student.getAddress());				
				} 
				else {
					JOptionPane.showMessageDialog(null, "NO DATA FOUND");
				}
			}
		});
		btnsrc.setBounds(550, 210, 100, 30);
		frame.getContentPane().add(btnsrc);


		JButton btnExit = new JButton("EXIT");									//******** EXIT BUTTON ********//
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTxtField();
				frame = new JFrame();
				if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Exit Button", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(750, 210, 100, 30);
		frame.getContentPane().add(btnExit);

		DefaultTableModel model = new DefaultTableModel(new String[]{"NAME", "ROLL NO.", "GRADE", "AGE", "CONTACT", "ADDRESS"}, 0);
		table = new JTable(model);
		table.setBounds(300, 100, 700, 500);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(45, 280, 900, 250);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		frame.setVisible(true);
	}

	private void LoadTable() {											//******** LOADING TABLE ********//
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Student student : sys.getAllData()) {
			model.addRow(new Object[]{student.getName(), student.getRollNo(), student.getGrade(), student.getAge(), student.getContact(), student.getAddress()});
		}
	}

	private void clearTxtField() {										//******** CLEAR TEXTFIELDS ********//
		txtname.setText("");
		txtrollno.setText("");
		txtgrade.setText("");
		txtage.setText("");
		txtcontact.setText("");
		txtaddress.setText("");
		txtname.requestFocus();
	}

	//public static class Main {											//******** MAIN CLASS ********//
		public static void main(String[] args) {
			StudentManagementSystem sys = new StudentManagementSystem();
			new StudentManagementSystemGUI(sys);
		//}
	}
}