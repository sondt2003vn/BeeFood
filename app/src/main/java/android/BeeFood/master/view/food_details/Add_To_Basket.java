package android.BeeFood.master.view.food_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.BeeFood.master.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Add_To_Basket extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar_add_To_Basket_toolbar;
    private ImageView img_add_To_Basket_banner;
    private TextView tv_add_To_Basket_name, tv_add_To_Basket_description, tv_add_To_Basket_number, tv_add_To_Basket_vote, tv_add_To_Basket_reviews,
            tv_add_To_Basket_khoangCach, tv_add_To_Basket_soTien;
    private ImageButton btn_add_To_Basket_number_giam, btn_add_To_Basket_number_tang;
    private Button btn_add_To_Basket_number_add;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_basket);

        anhXa();


        setSupportActionBar(toolbar_add_To_Basket_toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_add_To_Basket_number_add.setOnClickListener(this);
        btn_add_To_Basket_number_giam.setOnClickListener(this);
        btn_add_To_Basket_number_tang.setOnClickListener(this);

        tv_add_To_Basket_number.setText("" + count);

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
                        count += 5;
                        Thread.sleep(1000);
                          tv_add_To_Basket_number.setText("" + count);
                      }catch (Exception e){

                      }
                    }
                });
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_To_Basket_number_add:
                Intent intent = new Intent(Add_To_Basket.this, Checkout_Oders.class);
                startActivity(intent);
                finish();
                break;
            case R.id.add_To_Basket_number_tang:
                count++;
                tv_add_To_Basket_number.setText("" + count);
                break;
            case R.id.add_To_Basket_number_giam:
                if (count <= 0) {
                    Toast.makeText(this, "Số lượng không thể nhỏ hơn 0!", Toast.LENGTH_SHORT).show();
                }else {
                    count--;
                    tv_add_To_Basket_number.setText("" + count);
                }

                break;
        }
    }
}