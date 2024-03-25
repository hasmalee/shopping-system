package Console;

import java.util.ArrayList;
import java.util.Arrays;

public class Validation {
    public boolean IsValidProductType(String producType){
        ArrayList<String> validTypes = new ArrayList<>(Arrays.asList("E", "C"));
        return validTypes.contains(producType.toUpperCase());
    }
    public boolean IsValidProductId(String productId){
        return productId.length() >= 3 && productId.length() <= 10 && productId.matches("^[a-zA-Z0-9]+$");
    }
    public boolean IsValidProductName(String productName){
        return productName.length() >= 2 && productName.length() <= 10 && productName.matches("^[a-zA-Z]+$");
    }

    public boolean IsValidBrandName(String brandName){
        return brandName.length() >= 2 && brandName.length() <= 10 && brandName.matches("^[a-zA-Z]+$");
    }

    public boolean IsValidSize(String size){
        ArrayList<String> validSizes = new ArrayList<>(Arrays.asList("XS", "S", "M", "L", "XL", "XXL"));
        return validSizes.contains(size.toUpperCase());
    }

    public boolean IsValidColor(String color){
        return color.length() >= 3 && color.length() <= 10 && color.matches("^[a-zA-Z]+$");
    }

    public boolean IsValidResponse(String response){
        ArrayList<String> validResponses = new ArrayList<>(Arrays.asList("Y", "N"));
        return validResponses.contains(response.toUpperCase());
    }
}
