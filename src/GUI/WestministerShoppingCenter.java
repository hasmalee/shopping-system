package GUI;

import Console.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class WestministerShoppingCenter extends GUIOptions {
    private ShoppingManager _shoppingManager;
    private JTable productsTable;
    ArrayList<Electronics> electronicList;
    ArrayList<Clothing> clothingList;
    ArrayList<OrderItem> _orderItemList;
    private String[] categoryList = new String[]{"All", "Electronics", "Clothing"};
    private ShoppingCart _shoppingCart;

    public WestministerShoppingCenter(ShoppingManager shoppingManager, int type, ShoppingCart shoppingCart){
        _shoppingManager = shoppingManager;
        _shoppingCart = shoppingCart;
        _orderItemList = _shoppingCart.GetProducts() != null ? _shoppingCart.GetProducts() : new ArrayList<>();
        SetWindow(1000,800,"Westminister Shopping Center");
        DrawBody(type);
    }

    private void DrawBody(int type){
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel bodyPanel = new JPanel(new GridBagLayout());
        bodyPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        electronicList = new ArrayList<>(_shoppingManager.GetElectronicsList());
        clothingList = new ArrayList<>(_shoppingManager.GetClothingList());

        JButton shoppingCartButton = new JButton("Shopping Cart");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        bodyPanel.add(shoppingCartButton, gbc);

        shoppingCartButton.addActionListener(e -> OpenShoppingCart());

        JLabel categoryLabel = new JLabel("Select Product Category:");

        JComboBox<String> selectCategoryDropDown = new JComboBox<>(categoryList);
        selectCategoryDropDown.setPreferredSize(new Dimension(200, 30));
        selectCategoryDropDown.setSelectedIndex(type);

        JPanel textAndDropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        textAndDropdownPanel.add(categoryLabel);
        textAndDropdownPanel.add(selectCategoryDropDown);

        textAndDropdownPanel.setBackground(Color.WHITE);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        bodyPanel.add(textAndDropdownPanel, gbc);

        JTable productTable = DrawProductsTable(type);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(900, 300));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        bodyPanel.add(scrollPane, gbc);

        JSeparator separator = new JSeparator();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bodyPanel.add(separator, gbc);

        JTextArea productDetailsTextArea = new JTextArea();
        Font textAreaFont = new Font("SansSerif",1,15); // Example: Arial, plain style, size 14
        productDetailsTextArea.setFont(textAreaFont);
        productDetailsTextArea.setEditable(false);
        productDetailsTextArea.setBackground(Color.WHITE);
        String defaultText = "Selected Product - Details" +
                "\n\nProduct Id: " +
                "\n\nCategory: " +
                "\n\nName: " +
                "\n\nBrand: " +
                "\n\nWarranty: " +
                "\n\nItems Available: ";
        productDetailsTextArea.setText(defaultText);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        bodyPanel.add(productDetailsTextArea, gbc);

        JButton addToShoppingCartButton = new JButton("Add To Shopping Cart");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.SOUTH;
        bodyPanel.add(addToShoppingCartButton, gbc);

        addToShoppingCartButton.addActionListener(e -> AddToShoppingCart(productTable));

        add("North", bodyPanel);

        selectCategoryDropDown.addActionListener(e -> {
            String selectedCategory = (String) selectCategoryDropDown.getSelectedItem();
            FilterTableByCategory(selectedCategory);
        });

        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String productId = String.valueOf(productTable.getValueAt(selectedRow, 0));
                String category = String.valueOf(productTable.getValueAt(selectedRow, 2));
                String details = GetDetails(productId, category);
                productDetailsTextArea.setText(details);
            }
        });
    }

    private void FilterTableByCategory(String selectedCategory) {
        this.setVisible(false);
        int type = 0;
        if(selectedCategory.equalsIgnoreCase("Electronics")){
            type = 1;
        }else if(selectedCategory.equalsIgnoreCase("Clothing")){
            type = 2;
        }
        new WestministerShoppingCenter(_shoppingManager, type, _shoppingCart).setVisible(true);
    }

    private void AddToShoppingCart(JTable productTable){
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String productId = String.valueOf(productTable.getValueAt(selectedRow, 0));
            String name = String.valueOf(productTable.getValueAt(selectedRow, 1));
            double price = Double.parseDouble(String.valueOf(productTable.getValueAt(selectedRow, 3)));
            String info = String.valueOf(productTable.getValueAt(selectedRow, 4));

            boolean found = false;
            for(OrderItem product : _orderItemList){
                if(product.GetProductId().equalsIgnoreCase((productId))){
                    int existingQty = product.GetQty();
                    existingQty = existingQty + 1;
                    product.SetQty(existingQty);
                    found = true;
                    break;
                }
            }

            if(!found){
                _orderItemList.add(new OrderItem(productId, productId + "," + name + "," + info, 1, price));
            }

            _shoppingCart.AddProduct(_orderItemList);
        }
    }

    private void OpenShoppingCart(){
        new ViewShoppingCart(_shoppingCart).setVisible(true);
    }

    private String GetDetails(String productId, String category) {
        if(category.equalsIgnoreCase("Electronics")){
            for(Electronics product : electronicList){
                if(product.GetProductId().equalsIgnoreCase(productId)){
                    return "Selected Product - Details" +
                            "\n\nProduct Id: " + productId +
                            "\n\nCategory: Electronics" +
                            "\n\nName: " + product.GetProductName() +
                            "\n\nBrand: " + product.GetBrand() +
                            "\n\nWarranty: " + product.GetWarrantyPeriod() + " weeks warranty"+
                            "\n\nItems Available: " + product.GetAvailableQty();
                }
            }
        }else{
            for(Clothing product : clothingList){
                if(product.GetProductId().equalsIgnoreCase(productId)){
                    return "Selected Product - Details" +
                            "\n\nProduct Id: " + productId +
                            "\n\nCategory: Clothing" +
                            "\n\nName: " + product.GetProductName() +
                            "\n\nSize: " + product.GetSize() +
                            "\n\nColor: " + product.GetColor() +
                            "\n\nItems Available: " + product.GetAvailableQty();
                }
            }
        }
        return "Selected Product - Details";
    }

    private JTable DrawProductsTable(int type){
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Info"};
        String[][] productsArray;
        int twoDimentionalArraySize = 0;
        if(type == 0){
            twoDimentionalArraySize = electronicList.size() + clothingList.size();
            productsArray = new String[twoDimentionalArraySize][columnNames.length];

            int lastRowIndex = 0;
            for(int i=0;i<electronicList.size();i++){
                for(int j=0;j<columnNames.length;j++){
                    if (j == 0) {
                        productsArray[i][j] = electronicList.get(i).GetProductId();
                    } else if (j == 1) {
                        productsArray[i][j] = electronicList.get(i).GetProductName();
                    } else if (j == 2) {
                        productsArray[i][j] = "Electronics";
                    } else if (j == 3) {
                        productsArray[i][j] = String.format("%.2f", electronicList.get(i).GetPrice());
                    } else {
                        productsArray[i][j] = electronicList.get(i).GetBrand() + ", " + electronicList.get(i).GetWarrantyPeriod() + " weeks warranty";
                    }
                }
                lastRowIndex = i;
            }

            for(int i=0;i<clothingList.size();i++){
                for(int j=0;j<columnNames.length;j++){
                    if (j == 0) {
                        productsArray[i + lastRowIndex + 1][j] = clothingList.get(i).GetProductId();
                    } else if (j == 1) {
                        productsArray[i + lastRowIndex + 1][j] = clothingList.get(i).GetProductName();
                    } else if (j == 2) {
                        productsArray[i + lastRowIndex + 1][j] = "Clothing";
                    } else if (j == 3) {
                        productsArray[i + lastRowIndex + 1][j] = String.format("%.2f", clothingList.get(i).GetPrice());
                    } else {
                        productsArray[i + lastRowIndex + 1][j] = clothingList.get(i).GetSize() + ", " + clothingList.get(i).GetColor();
                    }
                }
            }
        }else if(type == 1){
            twoDimentionalArraySize = electronicList.size();
            productsArray = new String[twoDimentionalArraySize][columnNames.length];

            for(int i=0;i<electronicList.size();i++){
                for(int j=0;j<columnNames.length;j++){
                    if (j == 0) {
                        productsArray[i][j] = electronicList.get(i).GetProductId();
                    } else if (j == 1) {
                        productsArray[i][j] = electronicList.get(i).GetProductName();
                    } else if (j == 2) {
                        productsArray[i][j] = "Electronics";
                    } else if (j == 3) {
                        productsArray[i][j] = String.format("%.2f", electronicList.get(i).GetPrice());
                    } else {
                        productsArray[i][j] = electronicList.get(i).GetBrand() + ", " + electronicList.get(i).GetWarrantyPeriod() + " weeks warranty";
                    }
                }
            }
        }else{
            twoDimentionalArraySize = clothingList.size();
            productsArray = new String[twoDimentionalArraySize][columnNames.length];
            for(int i=0;i<clothingList.size();i++){
                for(int j=0;j<columnNames.length;j++){
                    if (j == 0) {
                        productsArray[i][j] = clothingList.get(i).GetProductId();
                    } else if (j == 1) {
                        productsArray[i][j] = clothingList.get(i).GetProductName();
                    } else if (j == 2) {
                        productsArray[i][j] = "Clothing";
                    } else if (j == 3) {
                        productsArray[i][j] = String.format("%.2f", clothingList.get(i).GetPrice());
                    } else {
                        productsArray[i][j] = clothingList.get(i).GetSize() + ", " + clothingList.get(i).GetColor();
                    }
                }
            }
        }

        productsTable = new JTable(productsArray,columnNames);
        productsTable.setFont(new Font("SansSerif",1,15));
        productsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));

        return productsTable;
    }

}
