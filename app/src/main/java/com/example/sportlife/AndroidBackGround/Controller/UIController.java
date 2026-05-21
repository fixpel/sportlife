package com.example.sportlife.AndroidBackGround.Controller;

import static androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FILL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportlife.Activity.ActivityExerciseDetail;
import com.example.sportlife.Activity.ActivityFavouriteDetails;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ExerciseCardResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ProfileResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;

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
        editTexts.forEach(e->e.setError(null));
        editTexts.forEach(e->{
            if(error.getErrors().containsKey(e.getTag().toString())){
                e.setError(error.getErrors().get(e.getTag().toString()).toString());
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
                Glide.with(holder.itemView.getContext())
                        .load(user.getAvatar())
                        .circleCrop()
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
                Glide.with(holder.itemView.getContext())
                        .load(inventory.getPhoto())
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
    public void findExercises(ExerciseCardResponse response, CallBackHandler callBack, Class<?extends Activity> to){
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
                ExerciseCardResponse.Exercise exercise=response.getExercises().get(position);
                View view=holder.itemView;
                ImageView photo=view.findViewById(R.id.imgExercise);
                Glide.with(holder.itemView.getContext()).load(exercise.getPhoto()).circleCrop().into(photo);
                TextView name=view.findViewById(R.id.tvName);
                TextView experts=view.findViewById(R.id.tvExpertise);
                ImageView favourites=view.findViewById(R.id.chkFavorite);
                if(exercise.getFavourites()){
                    Glide.with(holder.itemView.getContext())
                            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJBkdHJWiVPQwlcYkiakIzlEFm_9sJQlX53Q&s")
                            .centerCrop()
                            .into(favourites);//закрашенное сердечко
                }else{
                    Glide.with(holder.itemView.getContext())
                            .load("https://img.icons8.com/ios7/600w/228BE6/like.png")
                            .centerCrop()
                            .into(favourites);//не закрашенне сердечко
                }
                experts.setText(exercise.getExperts());
                name.setText(exercise.getName());
                favourites.setOnClickListener(v->{
                    if(exercise.getFavourites()) {
                        Glide.with(activity)
                                .load("https://img.icons8.com/ios7/600w/228BE6/like.png")
                                .centerCrop()
                                .into(favourites);//меняем на незакрашенное сердце
                        exercise.setFavourites(false);
                        callBack.onDeleteFavourite(exercise.getName());
                    }else{
                        Glide.with(activity)
                                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJBkdHJWiVPQwlcYkiakIzlEFm_9sJQlX53Q&s")
                                .centerCrop()
                                .into(favourites);//меняем на закрашеное сердцо
                        exercise.setFavourites(true);
                        callBack.onCreateFavourite(exercise.getName());
                }
                });
               view.setOnClickListener(v->{
                   if(to== ActivityExerciseDetail.class){
                       ActivityExerciseDetail.setNameExercise(name.getText().toString());
                   }else{
                       ActivityFavouriteDetails.setNameExercise(name.getText().toString());
                   }
                   openNextScreen(to);
               });
            }

            @Override
            public int getItemCount() {
                return response.getExercises().size();
            }
        });
    }
    @OptIn(markerClass = UnstableApi.class)
    public void findExercise(ExerciseCardResponse.Exercise exercise, CallBackHandler callBack){
        TextView name=activity.findViewById(R.id.tvExerciseTitle);
        name.setText(exercise.getName());
        ImageView favourite=activity.findViewById(R.id.chkFavorite);
        TextView description=activity.findViewById(R.id.tvTechnique);
        description.setText(exercise.getDescription());
        TextView items=activity.findViewById(R.id.tvEquipment);
        TextView muscles=activity.findViewById(R.id.tvMuscle);
        muscles.setText(String.join(", ",exercise.getMuscles()));
        items.setText(String.join(", ", exercise.getItems()));
        if(exercise.getFavourites()){
            Glide.with(activity)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJBkdHJWiVPQwlcYkiakIzlEFm_9sJQlX53Q&s")
                    .centerCrop()
                    .into(favourite);
        } else{
            Glide.with(activity)
                    .load("https://img.icons8.com/ios7/600w/228BE6/like.png")
                    .centerCrop()
                    .into(favourite);//меняем на незакрашенное сердце
        }
        favourite.setOnClickListener(v->{
            if(exercise.getFavourites()) {
                Glide.with(activity)
                        .load("https://img.icons8.com/ios7/600w/228BE6/like.png")
                        .centerCrop()
                        .into(favourite);//меняем на незакрашенное сердце
                exercise.setFavourites(false);
                callBack.onDeleteFavourite(exercise.getName());
            }else{
                Glide.with(activity)
                        .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJBkdHJWiVPQwlcYkiakIzlEFm_9sJQlX53Q&s")
                        .centerCrop()
                        .into(favourite);//меняем на закрашеное сердцо
                exercise.setFavourites(true);
                callBack.onCreateFavourite(exercise.getName());
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void profile(ProfileResponse response){
        errorService(response.toString());
        editTexts.forEach(e->{
            Glide.with(activity).load(response.getAvatar()).circleCrop().into((ImageView)activity.findViewById(R.id.imgAvatar));
            switch (e.getTag().toString()){
                case "login":
                    e.setText(response.getLogin());
                    break;
                case "experts":
                    if(response.getExperts()==null){
                        e.setText("уровень ещё неуказано");
                        break;
                    }else {
                        e.setText(response.getExperts());
                        break;
                    }
                case "activity":
                    e.setText(response.getActivity().toString());
                    break;
                case "top":
                    e.setText(response.getTop().toString());
                    break;
            }
        });
    }
}
