package com.example.deli.exp6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class EditFileActivity extends AppCompatActivity {

    private FileUtils fileUtils;
    private Button save, read, delete;
    private EditText fileContent;
    private AutoCompleteTextView fileName;
    private String file_name, file_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_file);

        fileUtils = new FileUtils();
        save = (Button) findViewById(R.id.save_button);
        read = (Button) findViewById(R.id.read_button);
        delete = (Button) findViewById(R.id.delete_button);
        fileContent = (EditText) findViewById(R.id.f_content_editText);
        fileName = (AutoCompleteTextView) findViewById(R.id.f_name_autoCompleteTextView);

        fileName.setAdapter(
                new ArrayAdapter<String>
                        (EditFileActivity.this, android.R.layout.simple_dropdown_item_1line,
                                EditFileActivity.this.fileList()));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file_name = fileName.getText().toString();
                file_content = fileContent.getText().toString();

                fileUtils.saveContent(EditFileActivity.this, file_name, file_content);
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file_name = fileName.getText().toString();

                file_content = fileUtils.getContent(EditFileActivity.this, file_name);
                fileContent.setText(file_content);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file_name = fileName.getText().toString();

                fileUtils.deleteFile(EditFileActivity.this, file_name);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_file, menu);
        return true;
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
}
//for you
