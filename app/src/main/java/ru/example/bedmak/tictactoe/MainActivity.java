package ru.example.bedmak.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int step = 0;
    private List<View> viewList = new LinkedList<>();
    private String[] viewTextArray = new String[9];

    private View.OnClickListener cellOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            setSymbol(view);
            if(step >= 5) {
                CharSequence winner = "Player" + lookForWinner() + "winner!";

                if(!winner.equals("")) {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(MainActivity.this, winner, duration);
                    toast.show();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewList.add(findViewById(R.id.cell0));
        viewList.add(findViewById(R.id.cell1));
        viewList.add(findViewById(R.id.cell2));
        viewList.add(findViewById(R.id.cell3));
        viewList.add(findViewById(R.id.cell4));
        viewList.add(findViewById(R.id.cell5));
        viewList.add(findViewById(R.id.cell6));
        viewList.add(findViewById(R.id.cell7));
        viewList.add(findViewById(R.id.cell8));

        for(View view: viewList) {
            TextView tv = (TextView) view;
            tv.setOnClickListener(cellOnClick);
        }

        if(savedInstanceState != null) {
            step = savedInstanceState.getInt("step");
            viewTextArray = savedInstanceState.getStringArray("viewTextArray");
            int k = 0;
            for(View view: viewList) {
                if(!viewTextArray[k].isEmpty()) {
                    TextView tv = (TextView) view;
                    tv.setText(viewTextArray[k]);
                    k++;
                }
            }
        }
    }

    public void onClick(View view) {

        if(view.getId() == R.id.buttonNewGame){
            newGame();
        }
    }

    protected void newGame() {
        for(View view: viewList) {
            TextView tv = (TextView) view;
            tv.setText("");
        }
        setZeroStep();
    }

    protected void setSymbol(View view ) {
        TextView tv = (TextView) findViewById(view.getId());

        if(tv.getText().toString().isEmpty()) {
            if (getStep() % 2 == 0) {
                tv.setText(R.string.X);
            } else {
                tv.setText(R.string.O);
            }
            step++;
        }
    }

    protected void setZeroStep() {
        step = 0;
    }

    protected int getStep() {
        return step;
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("step", step);
        int k = 0;
        for(View view: viewList) {
            TextView tv = (TextView) view;
            viewTextArray[k] = tv.getText().toString();
            k++;
        }
        savedInstanceState.putStringArray("viewTextArray", viewTextArray);
    }

    protected String lookForWinner() {
        return "X";     // Дописать логику
    }

}
