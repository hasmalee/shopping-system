package GUI;

import Console.OrderItem;
import Console.ShoppingCart;
import Console.Total;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ViewShoppingCart extends GUIOptions {
    private ShoppingCart _shoppingcart;
    private ArrayList<OrderItem> _products;
    private JTable productsTable;
    public ViewShoppingCart(ShoppingCart shoppingCart){
        _shoppingcart = shoppingCart;
        _products = _shoppingcart.GetProducts() != null ? _shoppingcart.GetProducts() : new ArrayList<>();
        SetWindow(1000,600,"Shopping Cart");
        DrawBody();
    }

    private void DrawBody(){
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel bodyPanel = new JPanel(new GridBagLayout());
        bodyPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        JTable productTable = DrawProductsTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(900, 300));
        productTable.setRowHeight(100);

        for (int columnIndex = 0; columnIndex < productTable.getColumnCount(); columnIndex++) {
            productTable.getColumnModel().getColumn(columnIndex).setCellRenderer(new MultilineTableCellRenderer());
        }

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        bodyPanel.add(scrollPane, gbc);

        Total total = _shoppingcart.CalculateTotalCost();

        JTextArea cartDetailsTextArea = new JTextArea();
        Font textAreaFont = new Font("SansSerif",1,15); // Example: Arial, plain style, size 14
        cartDetailsTextArea.setFont(textAreaFont);
        cartDetailsTextArea.setEditable(false);
        cartDetailsTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        String defaultText = String.format("%.2f",total.GetTotal()) + " :Total" +
                "\n\n" + String.format("%.2f",total.GetFirstTimeDiscount()) + " :First Purchase Discount (10%)" +
                "\n\n" + String.format("%.2f",total.GetSameCatDiscount()) + " :Three Items in same Category Discount (20%)" +
                "\n\n" + String.format("%.2f",total.GetFinalTotal()) + " :Final Total";

        cartDetailsTextArea.setText(defaultText);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.EAST;
        bodyPanel.add(cartDetailsTextArea, gbc);

        add("North", bodyPanel);
    }

    private JTable DrawProductsTable(){
        String[] columnNames = {"Product", "Quantity", "Price"};
        String[][] productsArray;
        productsArray = new String[_products.size()][columnNames.length];

        for(int i=0;i<_products.size();i++){
            for(int j=0;j<columnNames.length;j++){
                if (j == 0) {
                    productsArray[i][j] = _products.get(i).GetName().replace(",", "\n");
                } else if (j == 1) {
                    productsArray[i][j] = String.valueOf(_products.get(i).GetQty());
                } else {
                    productsArray[i][j] = String.format("%.2f", _products.get(i).GetPrice());
                }
            }
        }

        productsTable = new JTable(productsArray,columnNames);
        productsTable.setFont(new Font("SansSerif",1,15));
        productsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));

        return productsTable;
    }

    class MultilineTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setText("<html><div style='text-align: center;'>" + value.toString().replace("\n", "<br>") + "</div></html>");
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            return label;
        }
    }
}
