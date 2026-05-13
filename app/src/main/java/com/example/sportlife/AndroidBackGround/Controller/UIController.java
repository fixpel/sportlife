package com.example.sportlife.AndroidBackGround.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.Activity.ActivityExercisedetail;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        editTexts.forEach(e->e.setError(null));
        editTexts.forEach(e->{
            if(error.getErrors().containsKey(e.getTag().toString())){
                e.setError(error.getErrors().get(e.getTag().toString()).toString());
            }else{
                return;
            }
        });
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
                    boolean isSelected = !v.isSelected();
                    if(isSelected){
                        SearchService.getItems().add(name.getText().toString());
                    }else{
                        SearchService.getItems().remove(name.getText().toString());
                    }
                    v.setSelected(isSelected);
                });
            }

            @Override
            public int getItemCount() {
                return response.getInventories().size();
            }
        });
    }
    public void findExercises(SearchResponse response, CallBackHandler callBack){
        RecyclerView recyclerView=activity.findViewById(R.id.recyclerViewResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=activity.getLayoutInflater().inflate(R.layout.item_result,parent,false);
                return new RecyclerView.ViewHolder(view){};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                SearchResponse.Exercise exercise=response.getExercises().get(position);
                ImageView photo=holder.itemView.findViewById(R.id.imgExercise);
                errorService(photo.toString());
                Picasso.get().load(exercise.getPhoto()).fit().centerCrop().into(photo);
                TextView name=holder.itemView.findViewById(R.id.tvName);
                TextView experts=holder.itemView.findViewById(R.id.tvExpertise);
                ImageView favourites=holder.itemView.findViewById(R.id.chkFavorite);
                if(exercise.getFavourites()){
                    Picasso.get()
                            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJBkdHJWiVPQwlcYkiakIzlEFm_9sJQlX53Q&s")
                            .fit()
                            .centerCrop()
                            .into(favourites);//закрашенное сердечко
                }else{
                    Picasso.get()
                            .load("https://img.icons8.com/ios7/600w/228BE6/like.png")
                            .fit()
                            .centerCrop()
                            .into(favourites);;//не закрашенне сердечко
                }
                experts.setText(exercise.getExperts());
                name.setText(exercise.getName());
                favourites.setOnClickListener(v->{
                    if(exercise.getFavourites()) {
                        Picasso.get()
                                .load("https://img.icons8.com/ios7/600w/228BE6/like.png")
                                .fit()
                                .centerCrop()
                                .into(favourites);//меняем на незакрашенное сердце
                        exercise.setFavourites(false);
                        callBack.onDeleteFavourite(exercise.getName());
                    }else{
                        Picasso.get()
                                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJBkdHJWiVPQwlcYkiakIzlEFm_9sJQlX53Q&s")
                                .fit()
                                .centerCrop()
                                .into(favourites);//меняем на закрашеное сердцо
                    exercise.setFavourites(true);
                    callBack.onCreateFavourite(exercise.getName());
                }
                });
                holder.itemView.setOnClickListener(v->openNextScreen(ActivityExercisedetail.class));
            }

            @Override
            public int getItemCount() {
                return response.getExercises().size();
            }
        });
    }
}
