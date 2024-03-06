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
class Contact implements Serializable {									//******** CONTACT CLASS: INDIVIDUAL CONTACTS ********//
	private String name;
	private String phoneNumber;
	private String emailAddress;

	public Contact(String name, String phoneNumber, String emailAddress) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
}

class AddressBook {														//******** ADDRESSBOOK CLASS: CLASS TO MANAGE THE COLLECTION THE CONTACTS ********//
	private ArrayList<Contact> contacts;

	public AddressBook() {
		contacts = new ArrayList<>();
	}

	public void addContact(Contact contact) {
		contacts.add(contact);
	}

	public void removeContact(Contact contact) {
		contacts.remove(contact);
	}

	public Contact searchContact(String name) {
		for (Contact contact : contacts) {
			if (contact.getName().equalsIgnoreCase(name)) {
				return contact;
			}
		}
		return null;
	}

	public ArrayList<Contact> getAllContacts() {
		return contacts;
	}

	public void saveToFile(String fileName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(contacts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadFromFile(String fileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			contacts = (ArrayList<Contact>) ois.readObject();
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}


class AddressBookGUI {													//******** ADDRESSBOOKGUI CLASS: GUI IMPLEMENTATION ********//
	private AddressBook addressBook;
	private JFrame frame;
	private JTextField txtname, txtcontact, txtemail;
	private JTable table;

	public AddressBookGUI(AddressBook addressBook) {
		this.addressBook = addressBook;
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/zeus_logo.jpg"));
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("TASK 5");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "INPUT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(40, 30, 900, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNM = new JLabel("NAME");
		lblNM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNM.setBounds(120, 40, 80, 25);
		panel.add(lblNM);

		JLabel lblCON = new JLabel("CONTACT NO.");
		lblCON.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCON.setBounds(330, 40, 80, 25);
		panel.add(lblCON);

		JLabel lblEM = new JLabel("E-MAIL");
		lblEM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEM.setBounds(610, 40, 80, 25);
		panel.add(lblEM);

		txtname = new JTextField();
		txtname.setBounds(160, 35, 100, 30);
		panel.add(txtname);
		txtname.setColumns(10);

		txtcontact = new JTextField();
		txtcontact.setColumns(10);
		txtcontact.setBounds(420, 35, 100, 30);
		panel.add(txtcontact);

		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(660, 35, 100, 30);
		panel.add(txtemail);


		JButton btnadd = new JButton("SAVE");							//******** SAVE BUTTON ********//
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				String phone = txtcontact.getText();
				String email = txtemail.getText();

				if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
					addressBook.addContact(new Contact(name, phone, email));

					LoadTable();

					try { 												//******** FILE HANDLING ********//
						FileWriter x = new FileWriter("file5.txt",true); 

						x.write("NAME: " +name + "\n"); 
						x.write("CONTACT: " +phone + "\n"); 
						x.write("E-MAIL: "+email + "\n"); 
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
		btnadd.setBounds(150, 180, 100, 30);
		frame.getContentPane().add(btnadd);


		JButton btnrem = new JButton("DELETE");							//******** DELETE BUTTON ********//
		btnrem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				Contact contact = addressBook.searchContact(name);
				if (contact != null) {
					addressBook.removeContact(contact);
					LoadTable();
					clearTxtField();
				} 
				else {
					JOptionPane.showMessageDialog(null, "Contact not found.");
				}
			}
		});
		btnrem.setBounds(350, 180, 100, 30);
		frame.getContentPane().add(btnrem);


		JButton btnsrc = new JButton("SEARCH");							//******** SEARCH BUTTON ********//
		btnsrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				Contact contact = addressBook.searchContact(name);

				if (contact != null) {
					txtcontact.setText(contact.getPhoneNumber());
					txtemail.setText(contact.getEmailAddress());
				} 
				else {
					JOptionPane.showMessageDialog(null, "Contact not found.");
				}
			}
		});
		btnsrc.setBounds(550, 180, 100, 30);
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
		btnExit.setBounds(750, 180, 100, 30);
		frame.getContentPane().add(btnExit);

		DefaultTableModel model = new DefaultTableModel(new String[]{"NAME", "CONTACT NO.", "E-MAIL"}, 0);
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
		for (Contact contact : addressBook.getAllContacts()) {
			model.addRow(new Object[]{contact.getName(), contact.getPhoneNumber(), contact.getEmailAddress()});
		}
	}

	private void clearTxtField() {										//******** CLEAR TEXTFIELDS ********//
		txtname.setText("");
		txtcontact.setText("");
		txtemail.setText("");
		txtname.requestFocus();
	}

	public static class Main {											//******** MAIN CLASS ********//
		public static void main(String[] args) {
			AddressBook addressBook = new AddressBook();
			new AddressBookGUI(addressBook);
		}
	}
}