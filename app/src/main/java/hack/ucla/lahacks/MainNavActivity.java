package hack.ucla.lahacks;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainNavActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        Log.v("DB Debug", "about to start sqhelper");
        MySQLiteHelper db = new MySQLiteHelper(this);
        Log.v("DB Debug", "MySQLiteHelper ran.");
        db.getWritableDatabase();
        Log.v("DB Debug", "getWritableDatabase ran.");
        /**
         * CRUD Operations
         * */
        // add foods
        FoodType apple = new FoodType("apple", 2.0);
        FoodType apple1 = new FoodType("apple", 2.0);

        db.addFoodType(apple);
        db.addFoodType(apple1);
        // get all foods
        List<FoodType> list = db.getAllFoods();

        Log.v("DB Debug", "amount: " + list.get(1).getAmount() + ", foodname: " + list.get(1).getFoodname()
                + ", id: " + list.get(1).getId());
        // delete one food
        db.deleteFoodType(list.get(0));


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

      /*  ArrayList<Button> buttonList = new ArrayList<Button>();
        //add buttons to the buttonList.
        Log.d("MainActivity", "populating buttonList");
        Button imgbtn0 = new Button(this);
        imgbtn0.setText("Bacon");
        imgbtn0.setTypeface(null, Typeface.BOLD);
        //centers test at the bottom of the image
        imgbtn0.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        imgbtn0.setBackgroundResource(R.drawable.bacon);

        Button imgbtn1 = new Button(this);
        imgbtn1.setText("Eggs");
        imgbtn1.setTypeface(null, Typeface.BOLD);
        imgbtn1.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        imgbtn1.setBackgroundResource(R.drawable.eggs);

        Button imgbtn2 = new Button(this);
        imgbtn2.setText("Ham");
        imgbtn2.setTypeface(null, Typeface.BOLD);
        imgbtn2.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        imgbtn2.setBackgroundResource(R.drawable.ham);

        Button imgbtn3 = new Button(this);
        imgbtn3.setText("Sausage");
        imgbtn3.setTypeface(null, Typeface.BOLD);
        imgbtn3.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        imgbtn3.setBackgroundResource(R.drawable.sausage);

        Button imgbtn4 = new Button(this);
        imgbtn4.setText("Tomatoes");
        imgbtn4.setTypeface(null, Typeface.BOLD);
        imgbtn4.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        imgbtn4.setBackgroundResource(R.drawable.tomatoes);

        buttonList.add(imgbtn0);
        buttonList.add(imgbtn1);
        buttonList.add(imgbtn2);
        buttonList.add(imgbtn3);
        buttonList.add(imgbtn4);

        //use this to get the screen dimensions
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //init gridview to populate with icons
        GridView gridview = (GridView) findViewById(R.id.gridview);
        Log.d("MainActivity", "gridview set");
        //pass width and height to help with smaller screens
        gridview.setAdapter(new ImageButtonAdapter(this, buttonList, metrics.widthPixels,
                metrics.heightPixels));
        Log.d("MainActivity", "gridview adapter set");



        GridView grid = (GridView) findViewById(R.id.gridview);

        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};
        // use your custom layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.gridlayout, R.id.gridtext, values);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                myDialogFrag myDiag = new myDialogFrag();
                myDiag.show(getFragmentManager(), "Diag");
            }
        });
    }*/


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ButtonAdapter(this));

    }

    //=========================================================================================
    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2" };

    /*public class FoodIcon {
        public int icon;
        public String title;
        public FoodIcon() {
            super();
        }

        public FoodIcon(int icon, String title) {
            super();
            this.icon = icon;
            this.title = title;
        }
    }*/


    public class ButtonAdapter extends BaseAdapter {
        private Context mContext;

        // Gets the context so it can be used later
        public ButtonAdapter(Context c) {
            mContext = c;
        }

        // Total number of things contained within the adapter
        public int getCount() {
            return values.length;
        }

        // Require for structure, not really used in my code.
        public Object getItem(int position) {
            return null;
        }

        // Require for structure, not really used in my code. Can
        // be used to get the id of an item in the adapter for
        // manual control.
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position,
                            View convertView, ViewGroup parent) {
            Button btn;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                btn = new Button(mContext);
                btn.setLayoutParams(new GridView.LayoutParams(500, 500));
                btn.setPadding(8, 8, 8, 8);
            }
            else {
                btn = (Button) convertView;
            }

            btn.setText(values[position]);
            // filenames is an array of strings
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundResource(R.drawable.bacon);
            btn.setOnClickListener(new MyOnClickListener(position));
            btn.setId(position);

            return btn;
        }
    }

    static class FoodTemp
    {
        ImageButton imgIcon;
        TextView txtTitle;
    }

    class MyOnClickListener implements View.OnClickListener
    {
        private final int position;

        public MyOnClickListener(int position)
        {
            this.position = position;
        }

        public void onClick(View v)
        {
            // Preform a function based on the position
            //someFunction(this.position)
            myDialogFrag myDiag = new myDialogFrag();
            myDiag.show(getFragmentManager(), "Diag");
        }
    }

    //=======================================================================================

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }



    //filler code. doesnt do stuff yet
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.app_name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main_nav, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_nav, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainNavActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
