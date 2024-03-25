package Console;

import java.io.*;
import java.util.*;
import GUI.WestministerShoppingCenter;

public class WestministerShoppingManager implements ShoppingManager {

    private ArrayList<Electronics> _electronicProducts;
    private ArrayList<Clothing> _clothingProducts;
    private Validation _validate;
    private final String _operatingSystem;

    public WestministerShoppingManager(){
        _electronicProducts = new ArrayList<>();
        _clothingProducts = new ArrayList<>();
        _validate = new Validation();
        _operatingSystem = System.getProperty("os.name");
    }

    public int MainMenu(){
        System.out.println("\t\t>>>>> Westminster Online Shopping System <<<<<\n");
        System.out.println("\t[1] Add New Product");
        System.out.println("\t[2] Remove Product");
        System.out.println("\t[3] Print The List Of Products");
        System.out.println("\t[4] Save To A File");
        System.out.println("\t[5] Open Westminster Shopping Centre");
        System.out.println("\t[6] Exit");
        System.out.print("\nEnter Menu Number > ");
        return CaptureIntUserInput();
    }

    private int CaptureIntUserInput(){
        Scanner userInput = new Scanner(System.in);
        int input = -1;
        try{
            input = userInput.nextInt();
        }catch (Exception e){
            System.out.println("Enter A Valid Number .....!\n");
        }
        return input;
    }

    private double CaptureDoubleUserInput(){
        Scanner userInput = new Scanner(System.in);
        double input = -1;
        try{
            input = userInput.nextDouble();
        }catch (Exception e){
            System.out.println("Enter A Valid Number .....!\n");
        }
        return input;
    }

    private String CaptureStrUserInput(){
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    public void AddNewProduct(){
        System.out.println("\t\t>>>>> Add New Product <<<<<\n");
        if((_electronicProducts.size() + _clothingProducts.size()) <= 50){
            String productType, productId, productName, brand, size, color;
            int availableQty, warrantyPeriod = 0;
            double price = 0;

            //Capture and validate Product Type
            do{
                System.out.print("Product Types: (E)Electronics, (C)Clothing ");
                System.out.print("\nEnter Product Type (E/C) > ");
                productType = CaptureStrUserInput().trim();
                if (!_validate.IsValidProductType(productType)) {
                    System.out.println("Enter One Of These Product Types (E/C).....!\n");
                }
            } while (!_validate.IsValidProductType(productType));

            //Capture and validate Product ID
            boolean valid = true;
            do{
                valid = true;
                System.out.print("Enter Product ID > ");
                productId = CaptureStrUserInput().trim();
                if (!_validate.IsValidProductId(productId)) {
                    int length = productId.length();
                    if (length < 3 || length > 10) {
                        System.out.println("Enter Product ID With Minimum 3 And Maximum 10 Number Of Characters .....!\n");
                    } else {
                        System.out.println("Product ID Can Only Contain Letters And Numbers.....!\n");
                    }
                }
                for(Electronics product : _electronicProducts){
                    if(product.GetProductId().equalsIgnoreCase(productId)){
                        System.out.println("Product ID Already Exist.....!\n");
                        valid = false;
                    }
                }
                for(Clothing product : _clothingProducts){
                    if(product.GetProductId().equalsIgnoreCase(productId)){
                        System.out.println("Product ID Already Exist.....!\n");
                        valid = false;
                    }
                }
            } while (!(_validate.IsValidProductId(productId) && valid));

            //Capture and validate Product Name
            do{
                System.out.print("Enter Product Name > ");
                productName = CaptureStrUserInput().trim();
                if (!_validate.IsValidProductName(productName)) {
                    int length = productName.length();
                    if (length < 2 || length > 10) {
                        System.out.println(" Product Name With Minimum 3 And Maximum 10 Number Of Letters .....!\n");
                    } else {
                        System.out.println("Product Name Can Only Contain Letters .....!\n");
                    }
                }
            } while (!_validate.IsValidProductName(productName));

            //Capture and validate Product Available Qty
            do{
                System.out.print("Enter Product Available Qty > ");
                availableQty = CaptureIntUserInput();
                if(availableQty < 0){
                    System.out.println("Product Available Qty Should be Greater Than Or Equal To 0 .....!\n");
                }
            } while (availableQty < 0);

            //Capture and validate Product Price
            do{
                System.out.print("Enter Product Price > ");
                price = CaptureDoubleUserInput();
                if(price < 0){
                    System.out.println("Product Price Should be Greater Than Or Equal To 0 .....!\n");
                }
            } while (price < 0);

            //Based on product type, capture and validate other attributes
            if(productType.equalsIgnoreCase("E")){
                //Capture and validate Brand Name
                do{
                    System.out.print("Enter Brand Name > ");
                    brand = CaptureStrUserInput().trim();
                    if (!_validate.IsValidBrandName(brand)) {
                        int length = brand.length();
                        if (length < 2 || length > 10) {
                            System.out.println("Enter Brand Name With Minimum 2 And Maximum 10 Number Of Letters .....!\n");
                        } else {
                            System.out.println("Brand Name Can Only Contain Letters .....!\n");
                        }
                    }
                } while (!_validate.IsValidBrandName(brand));

                //Capture and validate Warranty Period
                do{
                    System.out.print("Enter Warranty Period(Weeks) > ");
                    warrantyPeriod = CaptureIntUserInput();
                    if(warrantyPeriod < 0){
                        System.out.println("Warranty Should be Greater Than Or Equal To 0 .....!\n");
                    }
                } while (warrantyPeriod < 0);

                _electronicProducts.add(new Electronics(productId, productName, availableQty, price, brand, warrantyPeriod));
            }else{
                //Capture and validate Size
                do{
                    System.out.print("Sizes: (XS)Extra Small, (S)Small, (M)Medium, (L)Large, (XL)Extra Large, (XXL)Double Extra Large ");
                    System.out.print("\nEnter Size (XS/S/M/L/XL/XXL) > ");
                    size = CaptureStrUserInput().trim();
                    if (!_validate.IsValidSize(size)) {
                        System.out.println("Enter One Of These Sizes (XS/S/M/L/XL/XXL) .....!\n");
                    }
                } while (!_validate.IsValidSize(size));

                //Capture and validate Color
                do{
                    System.out.print("Enter Color > ");
                    color = CaptureStrUserInput().trim();
                    if (!_validate.IsValidColor(color)) {
                        int length = color.length();
                        if (length < 3 || length > 10) {
                            System.out.println("Enter Color With Minimum 3 And Maximum 10 Number Of Letters .....!\n");
                        } else {
                            System.out.println("Color Can Only Contain Letters .....!\n");
                        }
                    }
                } while (!_validate.IsValidColor(color));

                _clothingProducts.add(new Clothing(productId, productName, availableQty, price, size, color));
            }

            System.out.println("Product Added Successfully.....!\n");
        }else{
            System.out.println("\nMaximum Product Limit is 50 .....!");
        }
    }

    public void RemoveProduct(){
        System.out.println("\t\t>>>>> Remove Product <<<<<\n");
        if((_electronicProducts.size() + _clothingProducts.size()) > 0){
            String productId, response;
            int productType = -1;

            do{
                System.out.print("Enter Product ID To Delete> ");
                productId = CaptureStrUserInput().trim();
                if (!_validate.IsValidProductId(productId)) {
                    int length = productId.length();
                    if (length < 3 || length > 10) {
                        System.out.println("Enter Product ID With Minimum 3 And Maximum 10 Number Of Characters .....!\n");
                    } else {
                        System.out.println("Product ID Can Only Contain Letters And Numbers.....!\n");
                    }
                }
            } while (!_validate.IsValidProductId(productId));

            for(Electronics product : _electronicProducts){
                if(product.GetProductId().equalsIgnoreCase(productId)){
                    System.out.println("Product ID: " + product.GetProductId());
                    System.out.println("Product Name: " + product.GetProductName());
                    System.out.println("Available Qty: " + product.GetAvailableQty());
                    System.out.println("Price: " + product.GetPrice());
                    System.out.println("Brand: " + product.GetBrand());
                    System.out.println("Warranty: " + product.GetWarrantyPeriod() + " weeks warranty");

                    productType = 0;
                    break;
                }
            }
            for(Clothing product : _clothingProducts){
                if(product.GetProductId().equalsIgnoreCase(productId)){
                    System.out.println("Product ID: " + product.GetProductId());
                    System.out.println("Product Name: " + product.GetProductName());
                    System.out.println("Available Qty: " + product.GetAvailableQty());
                    System.out.println("Price: " + product.GetPrice());
                    System.out.println("Size: " + product.GetSize());
                    System.out.println("Color: " + product.GetColor());

                    productType = 1;
                    break;
                }
            }

            if(productType > -1){
                //Capture and validate delete response
                do{
                    System.out.print("Response: (Y)Yes, (N)No ");
                    System.out.print("\nAre You Sure You Want To Delete This Product? (Y/N) > ");
                    response = CaptureStrUserInput().trim();
                    if (!_validate.IsValidResponse(response)) {
                        System.out.println("Enter One Of These Responses (Y/N).....!\n");
                    }
                } while (!_validate.IsValidResponse(response));

                if(response.equalsIgnoreCase("Y")){
                    String finalProductId = productId;
                    if(productType == 0){
                        _electronicProducts.removeIf(product -> product.GetProductId().equals(finalProductId));
                    }else{
                        _clothingProducts.removeIf(product -> product.GetProductId().equals(finalProductId));
                    }
                    System.out.println("Product Deleted Successfully.....!\n");
                }else{
                    System.out.println("Product Deletion Cancelled.....!\n");
                }
            }else{
                System.out.println("Unable To Find The Product.....!\n");
            }

        }else{
            System.out.println("Product List Is Empty.....!\n");
        }
    }

    public void PrintProductList(){
        System.out.println("\t\t>>>>> Print The List Of Products <<<<<\n");
        if((_electronicProducts.size() + _clothingProducts.size()) > 0){
            ArrayList<Electronics> tempElectronics = new ArrayList<>(_electronicProducts);
            ArrayList<Clothing> tempClothing = new ArrayList<>(_clothingProducts);

            tempElectronics.sort(Comparator.comparing(Product::GetProductId));
            tempClothing.sort(Comparator.comparing(Product::GetProductId));

            System.out.println("\t+---------------+-----------+---------------------------+-------------------+-------------------+---------------------------+---------------------------+");
            System.out.println("\t|\tProduct ID\t\t|\tName\t|\tAvailable Qty\t|\tPrice\t|\t\tBrand\t\t|\tWarranty\t|\tSize\t|\tColor\t|\t\tType\t\t|");
            System.out.println("\t+---------------+-----------+---------------------------+-------------------+-------------------+---------------------------+---------------------------+");

            for(Electronics product : tempElectronics){
                System.out.printf("\t| %-18s| %-10s| %-18s| %-10s| %-18s| %-14s| %-10s| %-10s| %-18s|\n",product.GetProductId(),product.GetProductName(),product.GetAvailableQty(),product.GetPrice(),product.GetBrand(),product.GetWarrantyPeriod(), "", "", "Electronics");
            }
            for(Clothing product : tempClothing){
                System.out.printf("\t| %-18s| %-10s| %-18s| %-10s| %-18s| %-14s| %-10s| %-10s| %-18s|\n",product.GetProductId(),product.GetProductName(),product.GetAvailableQty(),product.GetPrice(),"","", product.GetSize(), product.GetColor(), "Clothing");
            }
            System.out.println("\t+---------------+-----------+---------------------------+-------------------+-------------------+---------------------------+---------------------------+");

        }else{
            System.out.println("Product List Is Empty.....!\n");
        }
    }

    public void SaveToFile(){
        File file;

        if (_operatingSystem.startsWith("Windows") || _operatingSystem.startsWith("Mac")) {

            file = new File(_operatingSystem.startsWith("Windows") ? ".\\src\\Data\\ProductList.txt" : "./src/Data/ProductList.txt");

            //Delete the file if exist
            if (file.exists()) {
                file.delete();
            }

            try (FileOutputStream fos = new FileOutputStream(_operatingSystem.startsWith("Windows") ? ".\\src\\Data\\ProductList.txt" : "./src/Data/ProductList.txt");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                // Write the products data to the file
                oos.writeObject(_electronicProducts);
                oos.writeObject(_clothingProducts);
            } catch (IOException e) {
                System.out.println(e);
            }

            System.out.println("Saved Products To The File Successfully.....!");

        } else {
            System.out.println("Operating System Is Not Supported.....!");
        }
    }

    public void LoadFromFile(){
        if (_operatingSystem.startsWith("Windows") || _operatingSystem.startsWith("Mac")) {
            File file = new File(_operatingSystem.startsWith("Windows") ? ".\\src\\Data\\ProductList.txt" : "./src/Data/ProductList.txt");

            // If file exists then load data
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(_operatingSystem.startsWith("Windows") ? ".\\src\\Data\\ProductList.txt" : "./src/Data/ProductList.txt");
                     ObjectInputStream ois = new ObjectInputStream(fis)) {

                    // Read products ArrayList objects from the file
                    _electronicProducts = (ArrayList<Electronics>) ois.readObject();
                    _clothingProducts = (ArrayList<Clothing>) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("Reload Successful\n");
            }

        } else {
            System.out.println("Operating System Is Not Supported.....!");
        }
    }

    public void OpenOnlineShoppingGUI(){
        ShoppingCart shoppingCart = new ShoppingCart();
        new WestministerShoppingCenter(this, 0, shoppingCart).setVisible(true);
    }

    public ArrayList<Electronics> GetElectronicsList(){
        return _electronicProducts;
    }

    public ArrayList<Clothing> GetClothingList(){
        return _clothingProducts;
    }
}
