package Model.Expense;

public class TaxDeductibleExpense implements Expense {
    private double total;
    private double tax;
    private final double cost;
    private final String name;

    public TaxDeductibleExpense(double cost, String name){
        this.cost = cost;
        this.name = name;
        setTax();
    }


    public double getTax() {

        return this.tax;
    }

    public void setTax() {
        if(getCost()< 1000){
            tax = ( 15 /100)* cost;

        }
        else if(getCost()> 1000 && getCost() <= 5000){
            tax = ( 31 /100)* cost;
        }
        else
        {
            tax = ( 45 /100)* cost;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @Override
    public Double calculateTotal() {
        total= cost + getTax();
        return total;
    }
}
