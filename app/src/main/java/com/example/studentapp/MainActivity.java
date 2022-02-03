package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText ename, esurname, esubject, emark, id;
    Button savebtn, viewbtn, updatebtn, deletebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DBHelper(this);

        ename = findViewById(R.id.editTextName);
        esurname = findViewById(R.id.editTextsurname);
        esubject = findViewById(R.id.editTextsubject);
        emark = findViewById(R.id.editTextmark);
        id = findViewById(R.id.editTextid);
        savebtn = findViewById(R.id.btnSave);
        viewbtn = findViewById(R.id.btnView);
        updatebtn = findViewById(R.id.updatebtn);
        deletebtn = findViewById(R.id.deletebtn);

        InsertData();
        viewAll();
        UpdateData();
        delete();
    }

    public void InsertData() {
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(ename.getText().toString(),
                        esurname.getText().toString(),
                        esubject.getText().toString(),
                        emark.getText().toString());
                if(isInserted = true)
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Saved", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewAll(){
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("Surname :"+ res.getString(2)+"\n");
                    buffer.append("Subject :"+ res.getString(3)+"\n");
                    buffer.append("Marks :"+ res.getString(4)+"\n\n");
                }
                showMessage("Data",buffer.toString());

            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = myDb.updateData(id.getText().toString(),ename.getText().toString(),esurname.getText().toString(),esubject.getText().toString(),emark.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void delete(){
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRow = myDb.delete(id.getText().toString());
                if(deleteRow > 0)
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

}