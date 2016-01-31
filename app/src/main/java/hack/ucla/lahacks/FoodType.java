package hack.ucla.lahacks;

/**
 * Created by alex on 4/4/2015.
 *
 * Data structure for foodtype.
 * holds a name of a food and the amount of that food.
 *
 * every time this class is updated a fresh install of the app might be necessary
 * to eliminate issues within SQLite
 */
public class FoodType {

    private int id;
    private String foodname;
    private Double amount;

    public FoodType(){}

    public FoodType(String name, Double amount) {
        super();
        this.foodname = name;
        this.amount = amount;
    }
    //getters and setters
    public String getFoodname() {
        return this.foodname;
    }
    public Double getAmount() {
        return this.amount;
    }
    public void setFoodname(String f) {
        this.foodname = f;
    }
    public void setAmount(Double a) {
        this.amount = a;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int i) {
        this.id = i;
    }

    //for debugging and reading help
    @Override
    public String toString() {
        return "Book [id=" + id + ", Foodname=" + foodname + ", Amount=" + amount
                + "]";
    }
}
