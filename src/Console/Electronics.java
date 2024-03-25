package Console;

public class Electronics extends Product{
    private String _brand;
    private int _warrantyPeriod;

    public Electronics(){

    }
    public Electronics(String productId, String productName, int availableQty, double price, String brand, int warrantyPeriod){
        super(productId, productName, availableQty, price);
        _brand = brand;
        _warrantyPeriod = warrantyPeriod;
    }

    public void SetBrand(String brand){
        _brand = brand;
    }
    public void SetWarrantyPeriod(int warrantyPeriod){
        _warrantyPeriod = warrantyPeriod;
    }
    public String GetBrand(){
        return _brand;
    }
    public int GetWarrantyPeriod(){
        return _warrantyPeriod;
    }
}
