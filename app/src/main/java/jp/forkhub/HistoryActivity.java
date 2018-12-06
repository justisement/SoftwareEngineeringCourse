package jp.forkhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mobile.R;
import com.github.mobile.accounts.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HistoryActivity extends AppCompatActivity {
    private EditText editText;
    private TextView showTextView;
    public String content;
    // 要保存的文件名
    private String fileName = "users_information.txt";
    private Button returnView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        returnView = findViewById(R.id.historybutton);  // get the button, it is in activity_main.xml
        returnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        // 获取页面中的组件
        editText = (EditText) findViewById(R.id.addText);
        showTextView = (TextView) findViewById(R.id.showText);
        showTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        Button addButton = (Button) this.findViewById(R.id.addButton);
        Button showButton = (Button) this.findViewById(R.id.showButton);
        // 绑定单击事件
        addButton.setOnClickListener(listener);
        showButton.setOnClickListener(listener);
    }
    // 声明监听器
    public View.OnClickListener listener = new OnClickListener() {
        public void onClick(View v) {
            Button view = (Button) v;
            switch (view.getId()) {
                case R.id.addButton:
                    save();
                    break;
                case R.id.showButton:
                    read();
                    break;

            }

        }

    };
    /**
     *@author chenzheng_Java
     *保存用户输入的内容到文件
     */
    public void save() {

        content = editText.getText().toString();
        try {
            FileOutputStream outputStream = openFileOutput(fileName,
                    Activity.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(HistoryActivity.this, "保存成功", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * @author chenzheng_java
     * 读取刚才用户保存的内容
     */
    public void read() {
        try {
            FileInputStream inputStream = this.openFileInput(fileName);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.read(bytes) != -1) {
                arrayOutputStream.write(bytes, 0, bytes.length);
            }
            inputStream.close();
            arrayOutputStream.close();
            content = new String(arrayOutputStream.toByteArray());
            showTextView.setText(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
