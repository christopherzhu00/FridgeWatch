package hack.ucla.lahacks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by alex on 4/4/2015.
 *
 * contains all the necessary functions to add and remove from a database
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FoodDB";
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v("DB Debug", "mysqlhelper start");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("DB Debug", "OnCreate running");
        // SQL statement to create food table
        String CREATE_FOOD_TABLE = "CREATE TABLE foods ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "foodname TEXT, "+
                "amount INTEGER )";

        // create foods table
        db.execSQL(CREATE_FOOD_TABLE);
        Log.v("DB Debug", "foods table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("DB Debug", "OnUpgrade Ran");
        // Drop older foods table if existed
        db.execSQL("DROP TABLE IF EXISTS foods");

        // create fresh foods table
        this.onCreate(db);
    }

    // foods table name
    private static final String TABLE_FOODS = "foods";

    // foods Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FOODNAME = "foodname";
    private static final String KEY_AMOUNT = "amount";

    private static final String[] COLUMNS = {KEY_ID,KEY_AMOUNT,KEY_AMOUNT};

    public void addFoodType(FoodType foodtype){
        Log.d("addFoodType", foodtype.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, foodtype.getFoodname()); // get foodname
        values.put(KEY_AMOUNT, foodtype.getAmount()); // get amount

        // 3. insert
        db.insert(TABLE_FOODS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public FoodType getFoodType(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_FOODS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build foodtype object
        FoodType foodtype = new FoodType();
        foodtype.setId(Integer.parseInt(cursor.getString(0)));
        foodtype.setFoodname(cursor.getString(1));
        foodtype.setAmount(Double.parseDouble(cursor.getString(2)));

        Log.d("getFoodType("+id+")", foodtype.toString());

        // 5. return foodtype
        return foodtype;
    }

    // Get All Foods
    public List<FoodType> getAllFoods() {
        List<FoodType> foodtypes = new LinkedList<FoodType>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_FOODS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build foodtype and add it to list
        FoodType foodtype = null;
        if (cursor.moveToFirst()) {
            do {
                foodtype = new FoodType();
                //all data comes out as a string and we need to cast it into the correct type
                foodtype.setId(Integer.parseInt(cursor.getString(0)));
                foodtype.setFoodname(cursor.getString(1));
                foodtype.setAmount(Double.parseDouble(cursor.getString(2)));

                // Add foodtype to foodtypes
                foodtypes.add(foodtype);
            } while (cursor.moveToNext());
        }

        Log.d("getAllFoods()", foodtypes.toString());

        // return foods
        return foodtypes;
    }

    // Updating single food
    public int updateFoodType(FoodType foodtype) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("foodname", foodtype.getFoodname()); // get foodname
        values.put("amount", foodtype.getAmount()); // get amount

        // 3. updating row
        int i = db.update(TABLE_FOODS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(foodtype.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single food
    public void deleteFoodType(FoodType foodtype) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_FOODS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(foodtype.getId()) });

        // 3. close
        db.close();

        Log.d("deleteFoodType", foodtype.toString());

    }
}