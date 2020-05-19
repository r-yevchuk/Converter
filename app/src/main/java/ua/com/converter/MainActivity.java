package ua.com.converter;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText editText;

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
        editText = findViewById(R.id.editText);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        editText.setOnKeyListener(keyListener);

        showResult(new Result());
    }

    private void showResult(Result result) {
        TextView tvResult = findViewById(R.id.tv_result);
        if (result != null) {
            tvResult.setText(result.toString());
        }
    }

    private Result logic(String data) {
        try {
            float parsed = Float.parseFloat(data);
            if (parsed < 1f || parsed > 1000){
                editText.setError("Value must be in range 1-1000");
                return null;
            }
            float temp = 60 / parsed;
            int min = (int) temp;
            int sec = Math.round((temp - min) * 60);
            return new Result(min, sec);
        } catch (Exception e) {
            editText.setError("Value must be number in range 1-1000");
        }
        return null;
    }

    public void onFrameClick(View view){
        editText.clearFocus();
    }
}
