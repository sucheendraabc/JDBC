package com.demo.guidemoUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.demo.guidemo.Employee;
import com.demo.guidemo.EmployeeDAO;

@SuppressWarnings("serial")
public class EmployeeSearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JTable table;
	private EmployeeDAO employeeDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeSearchApp frame = new EmployeeSearchApp();
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
	public EmployeeSearchApp() {
		employeeDAO = new EmployeeDAO();
		setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		setTitle("EmployeeSearchApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setForeground(Color.RED);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblEnterLastname = new JLabel("Enter lastName");
		panel.add(lblEnterLastname);

		lastNameTextField = new JTextField();
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// get last name from the text field

				// Call the Employee DAO and get employees for last name

				// if the last name is empty then get all employees

				// print out employees
				String lastName = lastNameTextField.getText();
				List<Employee> employees = null;

				try 
				{
					if (lastName != null && lastName.trim().length() > 0) {
						employees = employeeDAO.searchEmployees(lastName);
					}
					else
					{
						employees = employeeDAO.getAllEmployees();
					}
					
				
				} 
				catch (Exception e) {
					// TODO: handle exception
				}
				
				EmployeeTableModel model = new EmployeeTableModel(employees);
			table.setModel(model);
				/*for (Employee employee : employees) 
				{
					System.out.println(employee);
				}*/
			}
		});
		panel.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
