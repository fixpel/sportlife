package com.example.sportlife.AndroidBackGround.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.media3.common.util.UnstableApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportlife.Activity.ActivityProfile;
import com.example.sportlife.Activity.ActivityResultDetail;
import com.example.sportlife.Activity.ActivityFavouriteDetails;
import com.example.sportlife.AndroidBackGround.Client.TranslateClient;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindAvatarResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ExerciseCardResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ProfileResponse;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
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
        SessionManager session=new SessionManager(activity);
        editTexts.forEach(e->e.setText(null));
        editTexts.forEach(e->{
            if(error.getErrors().containsKey(e.getTag().toString())){
                try {
                    e.setText(TranslateClient.translateString(error.getErrors().get(e.getTag().toString()).toString(),"Error",session.getLanguage()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void errorService(String message,String type){
        SessionManager session=new SessionManager(activity);
        switch (type) {

            case "ConnectException":
                message = "30";
                break;

            case "UnknownHostException":
                message = "31";
                break;

            case "SocketTimeoutException":
                message = "32";
                break;

            case "SocketException":
                message = "33";
                break;

            case "SSLHandshakeException":
            case "SSLException":
                message = "34";
                break;
            case "ApiException":
                message = "1";
                break;
            case "Message":
                message=message;
                break;
            default:
                message="35";
                break;
        }

        try {
            message=TranslateClient.translateString(message,"Error",session.getLanguage());
            Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
        } catch (IOException e) {

        }
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
                rank.setText(TranslateClient.translateLevel(activity,user.getExperts()));
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
        SessionManager session=new SessionManager(activity);
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
                try {
                    name.setText(TranslateClient.translateString(inventory.getId(),"Inventory",session.getLanguage()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                holder.itemView.setContentDescription(inventory.getId());
                holder.itemView.setSelected(SearchService.getItems().contains(holder.itemView.getContentDescription().toString()));
                Glide.with(holder.itemView.getContext())
                        .load(inventory.getPhoto())
                        .circleCrop()
                        .into(photo);
                holder.itemView.setOnClickListener(v->{
                    boolean isSelected=!SearchService.getItems().contains(holder.itemView.getContentDescription().toString());
                    if(isSelected){
                        SearchService.getItems().add(holder.itemView.getContentDescription().toString());
                    }else{
                        SearchService.getItems().remove(holder.itemView.getContentDescription().toString());
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
                SessionManager session=new SessionManager(activity);
                ExerciseCardResponse.Exercise exercise=response.getExercises().get(position);
                View view=holder.itemView;
                ImageView photo=view.findViewById(R.id.imgExercise);
                Glide.with(holder.itemView.getContext()).load(exercise.getPhoto()).circleCrop().into(photo);
                TextView name=view.findViewById(R.id.tvName);
                TextView experts=view.findViewById(R.id.tvExpertise);
                ImageView favourites=view.findViewById(R.id.chkFavorite);
                if(exercise.getFavourites()){
                    Glide.with(holder.itemView.getContext())
                            .load(R.drawable.painted_heart)
                            .circleCrop()
                            .into(favourites);
                }else{
                    Glide.with(holder.itemView.getContext())
                            .load(R.drawable.unpainted_heart)
                            .circleCrop()
                            .into(favourites);
                }
                experts.setText(TranslateClient.translateLevel(activity,exercise.getExperts()));
                try {
                    name.setText(TranslateClient.translateString(exercise.getId(),"Result",session.getLanguage()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                favourites.setOnClickListener(v->{
                    if(exercise.getFavourites()) {
                        Glide.with(activity)
                                .load(R.drawable.unpainted_heart)
                                .circleCrop()
                                .into(favourites);
                        exercise.setFavourites(false);
                        callBack.onDeleteFavourite(exercise.getName());
                    }else{
                        Glide.with(activity)
                                .load(R.drawable.painted_heart)
                                .circleCrop()
                                .into(favourites);
                        exercise.setFavourites(true);
                        callBack.onCreateFavourite(exercise.getName());
                }
                });
               view.setOnClickListener(v->{
                   if(to== ActivityResultDetail.class){
                       ActivityResultDetail.setIdExercise(exercise.getId());
                   }else{
                       ActivityFavouriteDetails.setIdExercise(exercise.getId());
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
    public void findExercise(ExerciseCardResponse.Exercise exercise, CallBackHandler callBack) throws IOException {
        SessionManager session=new SessionManager(activity);
        TextView name=activity.findViewById(R.id.tvExerciseTitle);
        name.setText(TranslateClient.translateString(exercise.getId(),"Result",session.getLanguage()));
        ImageView favourite=activity.findViewById(R.id.chkFavorite);
        TextView description=activity.findViewById(R.id.tvTechnique);
        description.setText(TranslateClient.translateString(exercise.getId(),"Technique",session.getLanguage()));
        TextView items=activity.findViewById(R.id.tvEquipment);
        TextView muscles=activity.findViewById(R.id.tvMuscle);
        muscles.setText(String.join(", ", TranslateClient.translateMuscles(activity,"muscle",exercise.getMuscles())));
        items.setText(String.join(", ",TranslateClient.translateInventories(exercise.getItems(),"Inventory",session.getLanguage())));
        WebView video=activity.findViewById(R.id.videoContainer);
        video.getSettings().setJavaScriptEnabled(true);
        video.setWebViewClient(new WebViewClient());
        video.getSettings().setUseWideViewPort(true);
        video.getSettings().setLoadWithOverviewMode(true);
        video.getSettings().setDomStorageEnabled(true);
        String html =
                "<html>" +
                        "<head>" +
                        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                        "</head>" +
                        "<body style='margin:0;padding:0;background:black;'>" +
                        "<iframe " +
                        "src='https://rutube.ru/play/embed/" + exercise.getVideo() + "' " +
                        "width='100%' " +
                        "height='100%' " +
                        "frameborder='0' " +
                        "allow='autoplay; fullscreen' " +
                        "allowfullscreen>" +
                        "</iframe>" +
                        "</body>" +
                        "</html>";

        video.loadDataWithBaseURL(
                "https://rutube.ru/",
                html,
                "text/html",
                "UTF-8",
                null
        );
        if(exercise.getFavourites()){
            Glide.with(activity)
                    .load(R.drawable.painted_heart)
                    .circleCrop()
                    .into(favourite);
        } else{
            Glide.with(activity)
                    .load(R.drawable.unpainted_heart)
                    .circleCrop()
                    .into(favourite);
        }
        favourite.setOnClickListener(v->{
            if(exercise.getFavourites()) {
                Glide.with(activity)
                        .load(R.drawable.unpainted_heart)
                        .centerCrop()
                        .into(favourite);
                exercise.setFavourites(false);
                callBack.onDeleteFavourite(exercise.getName());
            }else{
                Glide.with(activity)
                        .load(R.drawable.painted_heart)
                        .circleCrop()
                        .into(favourite);
                exercise.setFavourites(true);
                callBack.onCreateFavourite(exercise.getName());
            }
        });
    }
    public void profile(ProfileResponse response){
        editTexts.forEach(e->{
            Glide.with(activity).load(response.getAvatar()).circleCrop().into((ImageView)activity.findViewById(R.id.imgAvatar));
            switch (e.getTag().toString()){
                case "name":
                    e.setText(response.getLogin());
                    break;
                case "experts":
                    if(response.getExperts()==null){
                        e.setText("уровень ещё неуказано");
                        break;
                    }else {
                        e.setText(TranslateClient.translateLevel(activity,response.getExperts()));
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
    public void findAvatars(FindAvatarResponse response, AlertDialog dialog){
        RecyclerView recyclerView = dialog.findViewById(R.id.recylerAvatar);
        recyclerView.setLayoutManager(new GridLayoutManager(dialog.getContext(), 2));
        final int[] selectedPosition = {-1};
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = dialog.getLayoutInflater()
                        .inflate(R.layout.item_avatar, parent, false);
                return new RecyclerView.ViewHolder(view){};
            }

            @Override
            public void onBindViewHolder(
                    @NonNull RecyclerView.ViewHolder holder,
                    int position
            ) {
                String name = response.getNames().get(position);
                ImageView avatar = holder.itemView.findViewById(R.id.imgAvatarItem);
                MaterialButton button = holder.itemView.findViewById(R.id.button);
                Glide.with(holder.itemView.getContext())
                        .load(name)
                        .circleCrop()
                        .into(avatar);
                button.setSelected(position == selectedPosition[0]);
                button.setOnClickListener(v -> {
                    int oldPosition = selectedPosition[0];
                    if(selectedPosition[0] == position){
                        selectedPosition[0] = -1;
                        ActivityProfile.setSelectedAvatarName(null);
                    } else{
                        selectedPosition[0] = position;
                        ActivityProfile.setSelectedAvatarName(name);
                    }
                    notifyItemChanged(oldPosition);
                    notifyItemChanged(selectedPosition[0]);
                });
            }
            @Override
            public int getItemCount() {
                return response.getNames().size();
            }
        });
    }
}
