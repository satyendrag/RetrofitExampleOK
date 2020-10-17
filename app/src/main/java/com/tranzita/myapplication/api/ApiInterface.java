package com.tranzita.myapplication.api;

import com.tranzita.myapplication.models.TodoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/todos")
    Call<List<TodoModel>> getTodoList();
}
