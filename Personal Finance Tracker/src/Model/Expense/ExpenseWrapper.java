package Model.Expense;

public abstract class ExpenseWrapper implements Expense {

    private final Expense wrappedExpense;
    protected double total;
    protected double tax;
    protected final double cost;
    private String name;
    protected boolean refunded;


    public ExpenseWrapper(TaxDeductibleExpense expense)
    {
        wrappedExpense = expense;
        this.cost = wrappedExpense.getCost();
        this.name = wrappedExpense.getName();
        setTax();
        this.tax = getTax();

        refunded = false;
    }


    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refundable) {
        refunded = refundable;
    }

    public double getTax() {
        return this.tax;
    }

    public void setTax() {
        if(getCost()< 1000){
            tax = 15;
        }
        else if(getCost()> 1000 && getCost() <= 5000){
            tax = 31;
        }
        else
        {
            tax = 45;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Double getCost() {
        return cost;
    }


    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public Double calculateTotal() {
        total= cost + ( (getTax() /100)* cost);
        return total;
    }

}
