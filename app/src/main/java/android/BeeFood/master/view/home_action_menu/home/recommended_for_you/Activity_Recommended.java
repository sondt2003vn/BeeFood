package android.BeeFood.master.view.home_action_menu.home.recommended_for_you;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.LoaiDao;
import android.BeeFood.master.controller.Dao.UserDao;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.model.User;
import android.BeeFood.master.model.UserChiTiet;
import android.BeeFood.master.model.loaiFood;
import android.BeeFood.master.view.accountSetup.Screen_Pin_Code;
import android.BeeFood.master.view.accountSetup.Screen_Profile;
import android.BeeFood.master.view.home_action_menu.home.Adapter_Recommended;
import android.BeeFood.master.view.object.Loai_food;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class Activity_Recommended extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference reference = storage.getReference();

    private ArrayList<Loai_food> lis_FoodType = new ArrayList<>();
    private ArrayList<Food> lis_Food = new ArrayList<>();

    private Adapter_Recommended adapter_recommended;
    private Adapter_recommended_foodSort mAdapter_recommended_foodSort;
    private ImageView img_home_ActionMenu_Recommended_back;
    private RecyclerView recyclerView_home_ActionMenu_Recommended_sortList;
    private RecyclerView recyclerView_home_ActionMenu_Recommended_list;

    Uri uri;
    String url_avt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);

        AnhXa();


        mAdapter_recommended_foodSort = new Adapter_recommended_foodSort(Activity_Recommended.this);
        db.collection("loaifood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<loaiFood> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    list.add(new loaiFood(document.getId(), document.getData().get("ImageUrl").toString(),document.getData().get("NameLoai").toString()));
                                } catch (Exception e) {

                                }
                            }
                            mAdapter_recommended_foodSort.setData(list);
                        }
                    }
                });


        LinearLayoutManager manager = new LinearLayoutManager(Activity_Recommended.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_home_ActionMenu_Recommended_sortList.setLayoutManager(manager);
        recyclerView_home_ActionMenu_Recommended_sortList.setAdapter(mAdapter_recommended_foodSort);

        recyclerView_home_ActionMenu_Recommended_sortList.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    Toast.makeText(Activity_Recommended.this, "true", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Activity_Recommended.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });


        adapter_recommended = new Adapter_Recommended(Activity_Recommended.this);
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Food> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //sử tên loại sau
                                if (true){
                                    list.add(new Food(document.getData().get("namefood").toString(),
                                            document.getData().get("price").toString(),
                                            document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(),
                                            document.getData().get("email").toString(),
                                            "Chưa có id",
                                            document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(),
                                            document.getData().get("describle").toString()));
                                }
                            }
                            adapter_recommended.setData(list);
                        }
                    }
                });


        LinearLayoutManager manager1 = new LinearLayoutManager(Activity_Recommended.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_home_ActionMenu_Recommended_list.setLayoutManager(manager1);
        recyclerView_home_ActionMenu_Recommended_list.setAdapter(adapter_recommended);


        img_home_ActionMenu_Recommended_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Activity_Recommended.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
                onBackPressed();
            }
        });


    }

    public void AnhXa(){
        img_home_ActionMenu_Recommended_back = findViewById(R.id.home_ActionMenu_Recommended_back);
        recyclerView_home_ActionMenu_Recommended_sortList = findViewById(R.id.home_ActionMenu_Recommended_sortList);
        recyclerView_home_ActionMenu_Recommended_list = findViewById(R.id.home_ActionMenu_Recommended_list);
    }


}