package com.example.pictureverification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int imgIndex = 100;      // 所选图片的下标
    List<Integer> nums;      // 保存8位随机数集合
    int text_random;
    private TextView name;

    private static String[] tagName = {
            "鼠", "牛", "虎", "兔",
            "龙", "蛇", "马", "羊",
            "猴", "鸡", "狗", "猪",
    };

    int[] img_id = {
            R.id.image_1, R.id.image_2,
            R.id.image_3, R.id.image_4,
            R.id.image_5, R.id.image_6,
            R.id.image_7, R.id.image_8,
    };

    int[] imgResources = {
            R.drawable.shu1, R.drawable.niu1, R.drawable.hu1,
            R.drawable.tu1, R.drawable.long1, R.drawable.she1,
            R.drawable.ma1, R.drawable.yang1, R.drawable.hou1,
            R.drawable.ji1, R.drawable.gou1, R.drawable.zhu1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.text1);
        getRandomNumber();
    }

    private void getRandomNumber() {
        nums = new ArrayList<>();
        while (nums.size() < 8) {
            int random = (int) (Math.random() * 12);        // 从0到11抽选随机数字
            if (!nums.contains(random))
                nums.add(random);
        }
        text_random = (int) (Math.random() * 8);
        name.setText(tagName[nums.get(text_random)]);

        for (int i = 0; i < nums.size(); i++) {
            ImageView imageView = findViewById(img_id[i]);
            imageView.setImageResource(imgResources[nums.get(i)]);
            imageView.setBackgroundResource(R.color.black);
            imageView.setOnClickListener(this);
        }
    }

    public void onRefresh(View view) {
        getRandomNumber();
    }

    public void onLogin(View view) {
        if (imgIndex == nums.get(text_random))
            Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "验证失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < nums.size(); i++) {
            ImageView imageView = findViewById(img_id[i]);
            imageView.setBackgroundResource(R.color.black);
            if (imageView.getId() == v.getId())
                imgIndex = nums.get(i);
        }

        v.setBackgroundResource(R.color.gules);
    }
}
