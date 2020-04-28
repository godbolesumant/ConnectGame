package sumant.androidapplication.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static int LOGLEVEL = 2;
    public static boolean ERROR = LOGLEVEL > 0;
    public static boolean WARN = LOGLEVEL > 1;
    public static boolean INFO = LOGLEVEL > 2;

    boolean player1 = true;
    boolean winnerFound = false;
    int[][] status = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };  /* 0-Blank; 1-yellow; 2-red */

    public int checkWin() {

        // Row wise check
        for (int i = 0; i < 3; i++) {
            if ((status[i][0] == status[i][1]) && (status[i][1] == status[i][2])) {
                if (status[i][0] != 0) {
                    return status[i][0];
                }
            }
        }

        // Column wise check
        for (int i = 0; i < 3; i++) {
            if ((status[0][i] == status[1][i]) && (status[1][i] == status[2][i])) {
                if (status[0][i] != 0) {
                    return status[0][i];
                }
            }
        }

        //Diagonal wise check
        if ((status[0][0] == status[1][1]) && (status[1][1] == status[2][2])) {
            if (status[1][1] != 0) {
                return status[1][1];
            }
        } else if ((status[0][2] == status[1][1]) && (status[1][1] == status[2][0])) {
            if (status[1][1] != 0) {
                return status[1][1];
            }
        }

        return 0;  // No winner yet
    }

    public void dropOn(View view) {
        ImageView image = (ImageView)view;
        if (INFO) Log.i("Tag", image.getTag().toString());

        int tag = (int) Integer.parseInt(image.getTag().toString());
        int row = tag / 3;
        int col = tag % 3;

        if (winnerFound == false) {
            if (status[row][col] == 0) {
                image.setTranslationY(-1500);
                if (player1) {
                    image.setImageResource(R.drawable.yellow);
                    status[row][col] = 1;
                    player1 = false;
                } else {
                    image.setImageResource(R.drawable.red);
                    status[row][col] = 2;
                    player1 = true;
                }
                image.animate().translationYBy(1500).setDuration(500);
            }

            switch (checkWin()) {
                case 0:
                    break;

                case 1:
                    Toast.makeText(this, "Yellow has Won !", Toast.LENGTH_SHORT).show();
                    winnerFound = true;
                    break;

                case 2:
                    Toast.makeText(this, "Red has Won !", Toast.LENGTH_SHORT).show();
                    winnerFound = true;
                    break;

                default:
                    break;
            }
        }

    }

    public void buttonClickFunction(View view) {
        ImageView image;
        image = (ImageView)findViewById(R.id.imageView1);
        image.setImageResource(0);
        image = (ImageView)findViewById(R.id.imageView2);
        image.setImageResource(0);
        image = (ImageView)findViewById(R.id.imageView3);
        image.setImageResource(0);
        image = (ImageView)findViewById(R.id.imageView4);
        image.setImageResource(0);
        image = (ImageView)findViewById(R.id.imageView5);
        image.setImageResource(0);
        image = (ImageView)findViewById(R.id.imageView6);
        image.setImageResource(0);
        image = (ImageView)findViewById(R.id.imageView7);
        image.setImageResource(0);
        image = (ImageView)findViewById(R.id.imageView8);
        image.setImageResource(0);
        image = (ImageView)findViewById(R.id.imageView9);
        image.setImageResource(0);

        player1 = true;
        for (int i=0; i< 3; i++) {
            for (int j=0; j<3; j++) {
                status[i][j] = 0;
            }
        }
        winnerFound = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
