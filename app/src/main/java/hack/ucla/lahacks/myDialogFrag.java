package hack.ucla.lahacks;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class myDialogFrag extends DialogFragment {

    int counter  = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        LinearLayout linLayout=
                new LinearLayout(getActivity());
        Button up = new Button(getActivity());
        Button down = new Button(getActivity());
        down.setText("-");
        up.setText("+");

        final TextView count = new TextView(getActivity());
        count.setText(Integer.toString(counter));
        linLayout.addView(down);
        linLayout.addView(count);
        linLayout.addView(up);


        up.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                counter++;
                count.setText(Integer.toString(counter));
            }
        });

        down.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (counter > 0) {
                    counter--;
                    count.setText(Integer.toString(counter));
                }
            }
        });




       return linLayout;
    }
}



