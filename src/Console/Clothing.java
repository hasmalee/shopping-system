package Console;

public class Clothing extends Product{
    private String _size;
    private String _color;

    public Clothing(){

    }
    public Clothing(String productId, String productName, int availableQty, double price, String size, String color){
        super(productId, productName, availableQty, price);
        _size = size;
        _color = color;
    }
    public void SetSize(String size){
        _size = size;
    }
    public void SetColor(String color){
        _color = color;
    }
    public String GetSize(){
        return _size;
    }
    public String GetColor(){
        return _color;
    }
}
