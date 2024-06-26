package Model.Expense;

//note change to init in readme
public class GlassTaxDecorator extends ExpenseWrapper
{
    private boolean isbroken;
    private String tempName;
    public GlassTaxDecorator(TaxDeductibleExpense expense) {
        super(expense);
        isbroken = false;
        tempName = getName();
        setName(getName()+ "(glass tax)");
    }

    //refactor calculate total to add more functionality (Glass and plastic)
    @Override
    public Double calculateTotal()
    {
        if (!isRefunded() && !isbroken)
        {
        total= cost + 0.25 + (( getTax() /100)* cost);
            setName(tempName+ "(glass tax)");
        }
        else if(isRefunded() && !isbroken)
        {
            total= cost + (( getTax() /100)* cost);
            setName(tempName+ "(glass tax refunded)");
        }
        else if(!isRefunded() && isbroken)
        {
            total= cost + 0.25 + (( getTax() /100)* cost);
            setName(tempName+ "(broken w glass tax)");

        }
        return total;
    }

    public void refund()
    {
            refunded = true;
    }
    public void isBroken()
    {
            isbroken = true;
    }
}