import Console.WestministerShoppingManager;

public class Main {
    public static void main(String[] args) {

        WestministerShoppingManager wmsm = new WestministerShoppingManager();
        wmsm.LoadFromFile();

        do{
            int menuNumber = wmsm.MainMenu(); //calling main menu in westminsters.m.
            switch (menuNumber){
                case -1:
                    break;
                case 1:
                    wmsm.AddNewProduct();
                    break;
                case 2:
                    wmsm.RemoveProduct();
                    break;
                case 3:
                    wmsm.PrintProductList();
                    break;
                case 4:
                    wmsm.SaveToFile();
                    break;
                case 5:
                    wmsm.OpenOnlineShoppingGUI();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid Menu Number .....!\n");
            }
        }while(true);

    }
}