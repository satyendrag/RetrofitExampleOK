package com.tranzita.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tranzita.myapplication.adapters.TodoAdapter;
import com.tranzita.myapplication.api.ApiClient;
import com.tranzita.myapplication.api.ApiInterface;
import com.tranzita.myapplication.models.TodoModel;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewTodo;
    ApiInterface apiInterface;
    List<TodoModel> todoLists;
    TodoAdapter todoAdapter;
    TextView tvError;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvError = findViewById(R.id.tv_error);
        recyclerViewTodo = findViewById(R.id.recycler_view_todo_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<List<TodoModel>> call = apiInterface.getTodoList();

        call.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                Log.d("TAG", response.code()+"");
                todoLists = response.body();
                todoAdapter = new TodoAdapter(MainActivity.this, todoLists);
                recyclerViewTodo.setLayoutManager(linearLayoutManager);
                recyclerViewTodo.setAdapter(todoAdapter);
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {
               tvError.setText(R.string.error);
            }
        });
    }
}