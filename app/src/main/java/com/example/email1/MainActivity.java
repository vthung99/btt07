package com.example.email1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    ArrayList<ItemModel> list;
    ArrayList<ItemModel> favoriteItems = new ArrayList<>();
    ArrayList<String> search = new ArrayList<>();
    Button favorite;
    boolean favorited = false;
    AutoCompleteTextView editSearch;
    ArrayAdapter<String> searchAdapter;
    RecyclerView recyclerView;
    String searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add(new ItemModel("Abstract","Khai báo lớp, phương thức, interface trừu tượng không có thể hiện(instance) cụ thể","12:00PM",false));
        list.add(new ItemModel("Boolean","Khai báo biến kiểu logic với 2 trị: true, false","12:00PM",false));
        list.add(new ItemModel("Catch","Được sử dụng để bắt ngoại lệ, được sử dụng cùng với try để xử lý các ngoại lệ xảy ra trong chương trình","11:00PM",false));
        list.add(new ItemModel("Default","Mặc định đươc thực thi khi không có case nào trả về giá trị true (dùng trong switch case)","10:00PM",false));
        list.add(new ItemModel("Extends","Được sử dụng để định nghĩa lớp con kế thừa các thuộc tính và phương thức từ lớp cha","10:00PM",false));
        list.add(new ItemModel("Finally","Thực hiện một khối lệnh đến cùng bất chấp các ngoại lệ có thể xảy ra. Được sử dụng trong try-catch","10:00PM",false));
        list.add(new ItemModel("Instanceof","Kiểm tra xem một đối tượng nào đó có phải là một thể hiện của 1 class được định nghĩa trước hay không","09:00PM",false));
        list.add(new ItemModel("Return","Kết thúc phương thức và trả về giá trị cho phương thức","08:00PM",false));
        list.add(new ItemModel("Switch","Sử dụng trong câu lệnh điều khiển switch case","07:00PM",false));
        list.add(new ItemModel("Throws","Chỉ định cho qua ngoại lệ khi exception xảy ra","07:00PM",false));
        list.add(new ItemModel("Void","Chỉ định một phương thức không trả về giá trị","07:00PM",false));
        list.add(new ItemModel("While","Được sử dụng trong lệnh điều khiển while","07:00PM",false));

        for (int i = 0; i < list.size(); i++) {
            search.add(list.get(i).getName());
            search.add(list.get(i).getSubject());
        }
        editSearch = findViewById(R.id.edit_search);
        searchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, search);
        editSearch.setAdapter(searchAdapter);
        editSearch.addTextChangedListener(this);


        recyclerView = findViewById(R.id.recycle_view);
        favorite = findViewById(R.id.button);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final EmailAdapter adapter = new EmailAdapter(list);
        recyclerView.setAdapter(adapter);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorited = !favorited;
                favoriteItems.clear();
                if (favorited) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isFavorite())
                            favoriteItems.add(list.get(i));
                    }
                    EmailAdapter adapter1 = new EmailAdapter(favoriteItems);
                    recyclerView.setAdapter(adapter1);
                } else {
                    EmailAdapter adapter = new EmailAdapter(list);
                    recyclerView.setAdapter(adapter);
                }
            }
        });


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("BacNT", s + "");
        searchText = String.valueOf(editSearch.getText());
        ArrayList<ItemModel> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (s.toString().equals(list.get(i).getName()) || s.toString().equals(list.get(i).getSubject()))
                arrayList.add(list.get(i));
        }
        if (arrayList.size() == 0) {
            Toast.makeText(this, "Không có kết quả phù hợp", Toast.LENGTH_LONG).show();
        } else {
            EmailAdapter adapter2 = new EmailAdapter(arrayList);
            recyclerView.setAdapter(adapter2);
        }
    }

}
