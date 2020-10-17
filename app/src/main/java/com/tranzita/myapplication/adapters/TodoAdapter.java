package com.tranzita.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranzita.myapplication.R;
import com.tranzita.myapplication.models.TodoModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    Context context;
    List<TodoModel> todoLists;

    public TodoAdapter(Context context, List<TodoModel> todoLists) {
        this.context = context;
        this.todoLists = todoLists;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_todo_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
            TodoModel todoModel = todoLists.get(position);
            holder.tvId.setText("Id: " +todoModel.getId());
            holder.tvTitle.setText(todoModel.getTitle());
            String status = todoModel.getCompleted() ? "True" : "False";
            holder.tvStatus.setText("Completed: " + status);

            // deleting item on LongClick
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    todoLists.remove(position);
                    notifyDataSetChanged();
                    return false;
                }
            });
    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder{
        TextView tvId, tvTitle, tvStatus;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}
