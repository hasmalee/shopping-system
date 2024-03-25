package Console;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<OrderItem> _cartItems;




    public ShoppingCart(){

        _cartItems = new ArrayList<>();
    }

    public ArrayList<OrderItem> GetProducts(){

        return _cartItems;
    }
    public void AddProduct(ArrayList<OrderItem> cartItems){
        _cartItems = cartItems;
    }
    public void RemoveProduct(){

    }
    public Total CalculateTotalCost(){
        double sameCategoryDiscount = 0;
        double fistTimeDiscount = 0;
        double total =0;
        double finalTotal = 0;

        for(OrderItem item : _cartItems){
            if(item.GetQty() >= 3){
                total = total + (item.GetQty() * item.GetPrice());
                sameCategoryDiscount = sameCategoryDiscount + (((item.GetQty() * item.GetPrice()) * 20) / 100);
            }else {
                total = total + (item.GetQty() * item.GetPrice());
            }
        }

        if(total > 0){
            fistTimeDiscount = ((total * 10) / 100);
        }

        finalTotal =  total - sameCategoryDiscount - fistTimeDiscount;

        return new Total(total, finalTotal, sameCategoryDiscount, fistTimeDiscount);
    }
}
