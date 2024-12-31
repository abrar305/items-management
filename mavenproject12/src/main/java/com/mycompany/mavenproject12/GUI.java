package com.mycompany.mavenproject12;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUI extends JFrame {
    private ProductManager manager = new ProductManager();
    private JTable productTable;
    private DefaultTableModel tableModel;

    public GUI() {
        setTitle("Items Management");
        setSize(1000, 700); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color sidebarColor = new Color(161, 204, 209); 
Color formBackgroundColor = new Color(244, 242, 222); 
Color tableBackgroundColor = new Color(233, 179, 132); 
Color buttonColor = new Color(124, 157, 150); 
Color buttonTextColor = Color.WHITE; 
Color tableTextColor = Color.BLACK; 
Color headerColor = new Color(161, 204, 209); 



       
        JPanel sidebar = new JPanel();
        sidebar.setBackground(sidebarColor);
        sidebar.setPreferredSize(new Dimension(200, getHeight())); 
        add(sidebar, BorderLayout.WEST);

        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(formBackgroundColor);

        
        JLabel nameLabel = new JLabel("Name:");
        JLabel categoryLabel = new JLabel("Category:");
        JLabel priceLabel = new JLabel("Price:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField priceField = new JTextField();

        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        categoryField.setFont(new Font("Arial", Font.PLAIN, 18));
        priceField.setFont(new Font("Arial", Font.PLAIN, 18));

        
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        addButton.setBackground(buttonColor);
        deleteButton.setBackground(buttonColor);
        updateButton.setBackground(buttonColor);

        addButton.setForeground(buttonTextColor);
        deleteButton.setForeground(buttonTextColor);
        updateButton.setForeground(buttonTextColor);

        addButton.setFont(new Font("Arial", Font.PLAIN, 14)); 
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 14));
        updateButton.setFont(new Font("Arial", Font.PLAIN, 14));

       
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(categoryLabel);
        formPanel.add(categoryField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBackground(formBackgroundColor);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

       
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Price"}, 0);
        productTable = new JTable(tableModel);
        productTable.setFont(new Font("Arial", Font.PLAIN, 16));
        productTable.setRowHeight(30);
        productTable.setBackground(tableBackgroundColor);
        productTable.setForeground(tableTextColor);
        productTable.getTableHeader().setBackground(headerColor);
        productTable.getTableHeader().setForeground(Color.WHITE);
        productTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(productTable);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(255, 69, 0)); 
        exitButton.setForeground(buttonTextColor);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 14)); 
        exitButton.addActionListener(e -> System.exit(0));

        JPanel exitPanel = new JPanel();
        exitPanel.setBackground(formBackgroundColor);
        exitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.add(exitButton);
        add(exitPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(e -> {
            int id = manager.getProducts().size() + 1;
            String name = nameField.getText();
            String category = categoryField.getText();
            double price = Double.parseDouble(priceField.getText());
            manager.addProduct(new Product(id, name, category, price));
            updateTable();
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                manager.deleteProduct(id);
                updateTable();
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String name = nameField.getText();
                String category = categoryField.getText();
                double price = Double.parseDouble(priceField.getText());
                manager.updateProduct(id, name, category, price);
                updateTable();
            }
        });

        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Product product : manager.getProducts()) {
            tableModel.addRow(new Object[]{product.getId(), product.getName(), product.getCategory(), product.getPrice()});
        }
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }
}
