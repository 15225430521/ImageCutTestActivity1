package com.zhibo.imagecuttestactivity1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ImagePicker imagePicker = new ImagePicker();
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置是否裁剪图片
        imagePicker.setCropImage(true);

        img = findViewById(R.id.img);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == img){
            startChooser();
        }

    }

    private void startChooser() {
        // 启动图片选择器
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            // 选择图片回调
            @Override public void onPickImage(Uri imageUri) {
            }

            // 裁剪图片回调
            @Override public void onCropImage(Uri imageUri) {
                img.setImageURI(imageUri);
            }

            // 自定义裁剪配置
            @Override public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        // 是否启动多点触摸
                        .setMultiTouchEnabled(false)
                        // 设置网格显示模式
                        .setGuidelines(CropImageView.Guidelines.ON)
                        // 圆形/矩形
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(540, 960)
                        // 宽高比
                        .setAspectRatio(3, 4);
            }

            // 用户拒绝授权回调
            @Override public void onPermissionDenied(int requestCode, String[] permissions,
                                                     int[] grantResults) {
            }
        });
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

}
