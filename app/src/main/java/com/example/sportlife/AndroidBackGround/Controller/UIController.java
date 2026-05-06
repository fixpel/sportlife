package com.example.sportlife.AndroidBackGround.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.Activity.MainActivity;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class UIController {
    private  Activity activity;
    private  List<TextView> editTexts;

    public void openNextScreen(Class<? extends Activity> to){
        Intent intent=new Intent(activity, to);
        activity.startActivity(intent);
        activity.finish();
    }
    public void ErrorAdvice(ErrorResponse error){
        editTexts.forEach(e->e.setError(null));
        editTexts.forEach(e->e.setError(error.getErrors().get(e.getTag().toString()).toString()));
    }
    public void errorService(String message){
        System.out.println(message);
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
    }
    public void findTop(FindTopResponse response){
        RecyclerView recyclerView=null;
        recyclerView.setAdapter(new RecyclerView.Adapter(){
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=activity.getLayoutInflater().inflate(R.layout.item_top,parent,false);
                return new RecyclerView.ViewHolder(view){};
            }
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                FindTopResponse.Top user=response.getTop().get(position);
                TextView name = holder.itemView.findViewById(R.id.userName);
                TextView rank = holder.itemView.findViewById(R.id.userRank);
                ImageView avatar = holder.itemView.findViewById(R.id.avatarIcon);
                name.setText(user.getLogin());
                rank.setText(user.getExperts());
                Picasso.get().load(user.getAvatar()).into(avatar);
            }
            @Override
            public int getItemCount() {
                return response.getTop().size();
            }
        });

    }
}
