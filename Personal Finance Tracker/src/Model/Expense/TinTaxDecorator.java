package Model.Expense;

//note change to init in readme
public class TinTaxDecorator extends ExpenseWrapper{

    public TinTaxDecorator(TaxDeductibleExpense expense) {
        super(expense);
        setName(getName()+ "(tin tax)");
    }

    @Override
    public Double calculateTotal() {
        total= cost + 0.15+ (( getTax() /100)* cost);
        return total;
    }



}
