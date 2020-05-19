package ua.com.converter;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    private final View.OnKeyListener keyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                String data = editText.getText().toString();
                showResult(logic(data));
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_input);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        editText.setOnKeyListener(keyListener);

        showResult(new Result());
    }

    public void startConvert(View v) {
        String data = editText.getText().toString();
        showResult(logic(data));
    }

    private void showResult(Result result) {
        TextView tvResult = findViewById(R.id.tv_result);
        tvResult.setText(result.toString());
    }

    private Result logic(String data) {
        int min = 0;
        int sec = 0;

        if (data != null) {
            float parsed = 0f;
            try {
                parsed = Float.parseFloat(data);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Not a Number", Toast.LENGTH_SHORT).show();
            }

            float temp = 60 / parsed;
            min = (int) temp;
            sec = Math.round((temp - min) * 60);
        }
        return new Result(min, sec);
    }
}
