package hack.ucla.lahacks;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by alex on 4/4/2015.
 *
 * Takes a context and a list of image buttons and
 * creates an adapter to display them in an adapterView
 */


public class ImageButtonAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Button> buttonList;
    int width, height;
    //pass screen width and height to help with weird formatting on smaller phones
    public ImageButtonAdapter(Context c, ArrayList<Button> b, int screenWidth, int screenHeight) {
        Log.d("ImageButtonAdapter", "Started ImageButtonAdapter");
        mContext = c;
        buttonList = b;
        width = screenWidth;
        height = screenHeight;
        }
    public ImageButtonAdapter(Context c, ArrayList<Button> b) {
        Log.d("ImageButtonAdapter", "Started ImageButtonAdapter");
        mContext = c;
        buttonList = b;
        //make width very large
        width = 9999;
        height = 9999;
    }
    @Override
    public int getCount() {
        return buttonList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    //overrode this to return ImageButton instead of View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //load button
        Button imgbtn = buttonList.get(position);


        imgbtn.setMaxHeight((int) (width*.4));
        imgbtn.setMaxWidth((int) (height*.4));
        imgbtn.setLayoutParams(new GridView.LayoutParams(500 , 500));
        return imgbtn;
    }


}








