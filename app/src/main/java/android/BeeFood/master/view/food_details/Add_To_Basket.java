package android.BeeFood.master.view.food_details;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.BuyFoodDao;
import android.BeeFood.master.controller.Dao.OderFoodDao;
import android.BeeFood.master.model.BuyFood;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.model.OderFood;
import android.BeeFood.master.view.home_action_menu.home.mycart.Activity_MyCart;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Add_To_Basket extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences sharedPreferences;
    String idFood = "";
    int count = 1;
    private Toolbar toolbar_add_To_Basket_toolbar;
    private ImageView img_add_To_Basket_banner, img_add_To_Basket_addCart;
    private TextView tv_add_To_Basket_name, tv_add_To_Basket_description, tv_add_To_Basket_number, tv_add_To_Basket_vote, tv_add_To_Basket_reviews,
            tv_add_To_Basket_khoangCach, tv_add_To_Basket_soTien;
    private ImageButton btn_add_To_Basket_number_giam, btn_add_To_Basket_number_tang;
    private Button btn_add_To_Basket_number_add;
    OderFood oderFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_basket);

        anhXa();
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent intent = getIntent();
                        String idFood = intent.getStringExtra("key_idFood");
                        SharedPreferences sharedPref = getApplication().getSharedPreferences("USER", Context.MODE_PRIVATE);
                        String email = sharedPref.getString("email", "");
                        Food food = new Food();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(idFood.equalsIgnoreCase(document.getId())){
                                    food = new Food(document.getData().get("namefood").toString(),
                                            document.getData().get("price").toString(),
                                            document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(),
                                            document.getData().get("email").toString(),
                                            "Chưa có id",
                                            document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(),
                                            document.getData().get("describle").toString());
                                }
                            }
                        }
                        int giatien=Integer.parseInt(food.getPrice())*Integer.parseInt(tv_add_To_Basket_number.getText().toString());
                            oderFood = new OderFood(idFood,food.getEmail(),giatien+"",tv_add_To_Basket_number.getText().toString(),"2.4");
                    }
                });

        setSupportActionBar(toolbar_add_To_Basket_toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn_add_To_Basket_number_add.setOnClickListener(this);
        btn_add_To_Basket_number_giam.setOnClickListener(this);
        btn_add_To_Basket_number_tang.setOnClickListener(this);

        tv_add_To_Basket_number.setText("" + count);

        img_add_To_Basket_addCart.setOnClickListener(this);

//        SharedPreferences sharedPref = Add_To_Basket.this.getSharedPreferences("USER", Context.MODE_PRIVATE);
//        String email = sharedPref.getString("email", "");

        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent intent2 = getIntent();
                        idFood = intent2.getStringExtra("key_idFood");
                        Food food = new Food();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (idFood.equalsIgnoreCase(document.getId())) {
                                    food = new Food(document.getData().get("namefood").toString(), document.getData().get("price").toString(), document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(), document.getData().get("email").toString(), document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(), document.getData().get("describle").toString());
                                }
                            }
                        }
                        //
                        Glide.with(getApplication()).load(food.getUrl()).into(img_add_To_Basket_banner);
                        tv_add_To_Basket_name.setText(food.getName());
                        btn_add_To_Basket_number_add.setText("Add to basket - " + food.getPrice() + " vnd");
                        tv_add_To_Basket_description.setText(food.getDescrible());
                    }
                });

    }

    public void anhXa() {
        toolbar_add_To_Basket_toolbar = findViewById(R.id.add_To_Basket_toolbar);

        img_add_To_Basket_banner = findViewById(R.id.add_To_Basket_banner);
        tv_add_To_Basket_vote = findViewById(R.id.add_To_Basket_vote);
        tv_add_To_Basket_reviews = findViewById(R.id.add_To_Basket_reviews);
        tv_add_To_Basket_khoangCach = findViewById(R.id.add_To_Basket_khoangCach);
        tv_add_To_Basket_soTien = findViewById(R.id.add_To_Basket_soTien);
        tv_add_To_Basket_name = findViewById(R.id.add_To_Basket_name);
        tv_add_To_Basket_description = findViewById(R.id.add_To_Basket_description);
        tv_add_To_Basket_number = findViewById(R.id.add_To_Basket_number);
        img_add_To_Basket_addCart = findViewById(R.id.add_To_Basket_addCart);

        btn_add_To_Basket_number_giam = findViewById(R.id.add_To_Basket_number_giam);
        btn_add_To_Basket_number_tang = findViewById(R.id.add_To_Basket_number_tang);

        btn_add_To_Basket_number_add = findViewById(R.id.add_To_Basket_number_add);
        btn_add_To_Basket_number_tang.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                count += 5;
                                Log.i("SONDTPH", count + "");
                                Thread.sleep(1000);
                                tv_add_To_Basket_number.setText("" + count);
                                if (!btn_add_To_Basket_number_tang.isSelected()) {
                                    break;
                                }
                            }
                        } catch (Exception e) {

                        }
                    }
                });
                thread.run();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_To_Basket_number_add:
                if (Integer.parseInt(tv_add_To_Basket_number.getText().toString()) != 0) {
                    Intent intent = new Intent(Add_To_Basket.this, Checkout_Oders.class);
                    intent.putExtra("key_soluongSP", Integer.parseInt(tv_add_To_Basket_number.getText().toString()));
                    intent.putExtra("key_idFood", idFood);
                    startActivity(intent);
                } else {
                    Toast.makeText(Add_To_Basket.this, "Số lượng lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_To_Basket_number_tang:
                count++;
                tv_add_To_Basket_number.setText("" + count);
                break;
            case R.id.add_To_Basket_number_giam:
                if (count <= 1) {
                    Toast.makeText(this, "Số lượng không thể nhỏ hơn 1!", Toast.LENGTH_SHORT).show();
                } else {
                    count--;
                    tv_add_To_Basket_number.setText("" + count);
                }
                break;
            case R.id.add_To_Basket_addCart:
                if (Integer.parseInt(tv_add_To_Basket_number.getText().toString()) != 0) {
                    Toast.makeText(Add_To_Basket.this,"Số Lượng"+tv_add_To_Basket_number.getText().toString() , Toast.LENGTH_SHORT).show();
                    OderFoodDao oderFoodDao = new OderFoodDao();
                    oderFood.setPriceOderFood(Integer.parseInt(oderFood.getPriceOderFood())*Integer.parseInt(tv_add_To_Basket_number.getText().toString())+"");
                    oderFood.setAmountoffood(tv_add_To_Basket_number.getText().toString());
                    oderFoodDao.AddBuyFood(oderFood,this);
                    Intent intent = new Intent(getApplication(), Activity_MyCart.class);
                    intent.putExtra("key_soluongSP", Integer.parseInt(tv_add_To_Basket_number.getText().toString()));
                    intent.putExtra("key_idFood", idFood);
                    startActivity(intent);
                } else {
                    Toast.makeText(Add_To_Basket.this, "Số lượng lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}