package Console;

import java.io.Serializable;

abstract class Product implements Serializable {

    private String _productId;
    private String _productName;
    private int _availableQty;
    private double _price;

    public Product(){

    }
    public Product(String productId, String productName, int availableQty, double price){
        _productId = productId;
        _productName = productName;
        _availableQty = availableQty;
        _price = price;
    }

    public void SetProductId(String productId){
        _productId = productId;
    }
    public void SetProductName(String productName){
        _productName = productName;
    }
    public void SetAvailableQty(int availableQty){
        _availableQty = availableQty;
    }
    public void SetPrice(double price){_price = price;
    }

    public String GetProductId(){
        return _productId;
    }
    public String GetProductName(){
        return _productName;
    }
    public int GetAvailableQty(){
        return _availableQty;
    }
    public double GetPrice(){
        return  _price;
    }
}
