package com.example.dell.majumandiriproductlist;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class JaysRub extends AppCompatActivity {

    //Constants
    final String WHITE = "#ffffff";
    final String BLACK = "#000000";
    final String GREY = "#a6a6a6";
    final float BACKGROUND_NORMAL = 1.0f;
    final float BACKGROUND_DIM = 0.7f;
    final float BACKGROUND_GONE = 0.0f;

    LinearLayout contentLayout;
    PopupWindow popUpWindow;
    Button btnClose;
    int[] buttonList = {R.id.buttonJaysRubChicken,
            R.id.buttonJaysRubRibs,
            R.id.buttonJaysRubSeafood,
            R.id.buttonJaysRubSteak
    };
    ImageView imageView;
    TextView textNameEdit;
    TextView textWeightEdit;
    TextView textPerCrtEdit;

    Button chicken;
    Button ribs;
    Button seafood;
    Button steak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jays_rub);

        Toolbar toolJaysRub = (Toolbar)findViewById(R.id.toolbarJaysRub);
        setSupportActionBar(toolJaysRub);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Setup layout
        contentLayout = (LinearLayout) findViewById(R.id.jaysRubContentLayout);
        contentLayout.setBackgroundColor(Color.parseColor(WHITE));

        //Set buttons
        chicken = (Button)findViewById(R.id.buttonJaysRubChicken);
        ribs = (Button)findViewById(R.id.buttonJaysRubRibs);
        seafood = (Button)findViewById(R.id.buttonJaysRubSeafood);
        steak = (Button)findViewById(R.id.buttonJaysRubSteak);

        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnClick(chicken);
            }
        });

        ribs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnClick(ribs);
            }
        });

        seafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnClick(seafood);
            }
        });

        steak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnClick(steak);
            }
        });
    }

    private void btnOnClick(Button b) {
        try {
            //Blur the background
            for (int id : buttonList) {
                Button btn = (Button)findViewById(id);
                btn.setAlpha(BACKGROUND_GONE);
                btn.setClickable(false);
            }
            contentLayout.setBackgroundColor(Color.parseColor(BLACK));
            contentLayout.setAlpha(BACKGROUND_DIM);

            //Generate helper function
            Helpers helpers = new Helpers(getApplicationContext());

            //Show the popup window
            LayoutInflater inflater = (LayoutInflater)JaysRub.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup, (ViewGroup)findViewById(R.id.popup_element));
            popUpWindow = new PopupWindow(layout, helpers.dpToPx(600), helpers.dpToPx(750)); //(context, width, height)
            popUpWindow.showAtLocation(layout, Gravity.CENTER, 0, 0); //(layout, gravity, x, y)

            //Set up pictures
            imageView = (ImageView)layout.findViewById(R.id.popupImageLayout);
            textNameEdit = (TextView)layout.findViewById(R.id.textNameEdit);
            textWeightEdit = (TextView)layout.findViewById(R.id.textWeightEdit);
            textPerCrtEdit = (TextView)layout.findViewById(R.id.textPerCrtEdit);
            switch (b.getId()){
                case R.id.buttonJaysRubChicken:
                    imageView.setImageResource(R.drawable.ic_unknown);
                    textNameEdit.setText(R.string.Jays_rub_chicken_label);
                    textWeightEdit.setText(R.string.Jays_rub_weight);
                    textPerCrtEdit.setText(R.string.Jays_rub_price);
                    break;
                case R.id.buttonJaysRubRibs:
                    imageView.setImageResource(R.drawable.ic_unknown);
                    textNameEdit.setText(R.string.Jays_rub_ribs_label);
                    textWeightEdit.setText(R.string.Jays_rub_weight);
                    textPerCrtEdit.setText(R.string.Jays_rub_price);
                    break;
                case R.id.buttonJaysRubSeafood:
                    imageView.setImageResource(R.drawable.ic_unknown);
                    textNameEdit.setText(R.string.Jays_rub_seafood_label);
                    textWeightEdit.setText(R.string.Jays_rub_weight);
                    textPerCrtEdit.setText(R.string.Jays_rub_price);
                    break;
                case R.id.buttonJaysRubSteak:
                    imageView.setImageResource(R.drawable.ic_unknown);
                    textNameEdit.setText(R.string.Jays_rub_steak_label);
                    textWeightEdit.setText(R.string.Jays_rub_weight);
                    textPerCrtEdit.setText(R.string.Jays_rub_price);
                    break;
            }
            //Setup close button
            btnClose= (Button)layout.findViewById(R.id.button_close);
            btnClose.setOnClickListener(button_close);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener button_close = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            contentLayout.setBackgroundColor(Color.parseColor(WHITE));
            contentLayout.setAlpha(BACKGROUND_NORMAL);
            for (int id : buttonList) {
                Button btn = (Button)findViewById(id);
                btn.setAlpha(BACKGROUND_NORMAL);
                btn.setClickable(true);
            }
            popUpWindow.dismiss();
        }
    };
}
