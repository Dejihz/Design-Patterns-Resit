package Model.Expense;

public class PlasticTaxDecorator extends ExpenseWrapper {
    private String tempName;
    public PlasticTaxDecorator(TaxDeductibleExpense expense) {
        super(expense);
        tempName = getName();
        setName(getName()+ "(plastic tax)");
    }
    @Override
    public Double calculateTotal() {
        if (!isRefunded())
        {
            total= cost + 0.25 + (( getTax() /100)* cost);
            setName(tempName+ "(plastic tax)");
        }
        else
        {
            total= cost + (( getTax() /100)* cost);
            setName(tempName+ "(plastic tax refunded)");
        }

        return total;
    }

    public void refund()
    {
        refunded = true;
    }
}
