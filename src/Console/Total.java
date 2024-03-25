package Console;

public class Total {
    private double _total;
    private double _finalTotal;
    private double _sameCatDiscount;
    private double _firstTimeDiscount;

    public Total(){

    }

    public Total(double total, double finalTotal, double sameCatDiscount, double firstTimeDiscount){
        _total = total;
        _finalTotal = finalTotal;
        _sameCatDiscount = sameCatDiscount;
        _firstTimeDiscount = firstTimeDiscount;
    }

    public double GetTotal(){
        return _total;
    }
    public double GetFinalTotal(){
        return _finalTotal;
    }
    public double GetSameCatDiscount(){
        return _sameCatDiscount;
    }
    public double GetFirstTimeDiscount(){
        return _firstTimeDiscount;
    }
}
