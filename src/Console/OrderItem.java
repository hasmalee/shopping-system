package Console;

public class OrderItem {
    private String _productId;
    private String _name;
    private int _qty;
    private double _price;

    public OrderItem(){

    }

    public OrderItem(String productId, String name, int qty, double price){
        _productId = productId;
        _name = name;
        _qty = qty;
        _price = price;
    }

    public void SetProductId(String productId){
        _productId = productId;
    }
    public void SetName(String name){
        _name = name;
    }
    public void SetQty(int qty){
        _qty = qty;
    }
    public void SetPrice(double price){
        _price = price;
    }

    public String GetProductId(){
        return _productId;
    }
    public String GetName(){
        return _name;
    }
    public int GetQty(){
        return _qty;
    }
    public double GetPrice(){
        return _price;
    }
}
