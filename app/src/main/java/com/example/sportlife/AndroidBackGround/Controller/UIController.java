package com.example.sportlife.AndroidBackGround.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        this.errorService("2");
        editTexts.forEach(e->e.setError(null));
        errorService("3");
        editTexts.forEach(e->e.setError(error.getErrors().get(e.getTag().toString()).toString()));
    }
    public void errorService(String message){
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
    }
    public void findTop(FindTopResponse response){
        RecyclerView recyclerView=activity.findViewById(R.id.recyclerViewTop);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new RecyclerView.Adapter(){
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=activity.getLayoutInflater().inflate(R.layout.item_top,parent,false);
                return new RecyclerView.ViewHolder(view){};
            }
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                FindTopResponse.EmployeeTop user=response.getTop().get(position);
                TextView name = holder.itemView.findViewById(R.id.userName);
                TextView rank = holder.itemView.findViewById(R.id.userRank);
                ImageView avatar = holder.itemView.findViewById(R.id.avatarIcon);
                name.setText(user.getLogin());
                rank.setText(user.getExperts());
                Picasso.get()
                        .load(user.getAvatar())
                        .fit()
                        .centerCrop()
                        .into(avatar);
            }
            @Override
            public int getItemCount() {
                return response.getTop().size();
            }
        });
    }
    public void findInventory(FindInventoryResponse response){
        RecyclerView recyclerView=activity.findViewById(R.id.recyclerInventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=activity.getLayoutInflater().inflate(R.layout.item_equipment,parent,false);
                return new RecyclerView.ViewHolder(view){};
            }
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                FindInventoryResponse.InventoryObject inventory = response.getInventories().get(position);
                ImageView photo = holder.itemView.findViewById(R.id.imgEquipment);
                TextView name = holder.itemView.findViewById(R.id.tvEquipmentName);
                name.setText(inventory.getName());
                Picasso.get()
                        .load(inventory.getPhoto())
                        .fit()
                        .centerCrop()
                        .into(photo);
                holder.itemView.setOnClickListener(v->{
                    SearchService.getItems().add(name.getText().toString());});
            }

            @Override
            public int getItemCount() {
                return response.getInventories().size();
            }
        });
    }
    public void findExercises(SearchResponse response){
        RecyclerView recyclerView=activity.findViewById(R.id.recyclerViewResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=activity.getLayoutInflater().inflate(R.layout.activity_result,parent,false);
                return new RecyclerView.ViewHolder(view){};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                SearchResponse.Exercise exercise=response.getExercises().get(position);
                View view=holder.itemView;
                ImageView photo=view.findViewById(R.id.imgExercise);
                TextView name=view.findViewById(R.id.tvName);
                TextView experts=view.findViewById(R.id.tvExpertise);
                ImageView favourites=view.findViewById(R.id.chkFavorite);
                if(exercise.getFavourites()){
                    Picasso.get()
                            .load("")
                            .fit()
                            .centerCrop()
                            .into(favourites);//закрашенное сердечко
                }else{
                    Picasso.get()
                            .load("")
                            .fit()
                            .centerCrop()
                            .into(favourites);;//не закрашенне сердечко
                }
                experts.setText(exercise.getExperts());
                name.setText(exercise.getName());
                Picasso.get()
                        .load(exercise.getPhoto())
                        .fit()
                        .centerCrop()
                        .into(photo);
                favourites.setOnClickListener(v->{
                    if(exercise.getFavourites()) {
                        Picasso.get()
                                .load("")
                                .fit()
                                .centerCrop()
                                .into(favourites);//меняем на незакрашенное сердце
                        exercise.setFavourites(false);
                    }else{
                        Picasso.get()
                                .load("")
                                .fit()
                                .centerCrop()
                                .into(favourites);//меняем на закрашеное сердцо
                    exercise.setFavourites(true);
                }
                });

            }

            @Override
            public int getItemCount() {
                return response.getExercises().size();
            }
        });
    }
}
