package com.example.lighting;

import android.Manifest;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Camera camera = null;
    private boolean isOpen = false;
    String[] permissions = {Manifest.permission.CAMERA};
    private List<String> permissionList = new ArrayList<>();
    private final int REQUEST_CODE = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        iv_img = (ImageView) findViewById(R.id.iv_img);
//        tv_type = (TextView) findViewById(R.id.tv_type);
//        tv_type.setText("点击打开");

            if (camera == null) {
                camera = Camera.open();
            }
            openFlashLight();
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
//                permissionList.add(permission);
//            }
//        }
//        if (!permissionList.isEmpty()) {
//            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), REQUEST_CODE);
//        } else {
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case REQUEST_CODE:
//
//                break;
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void openFlashLight() {
        Camera.Parameters param = camera.getParameters();
        if (!isOpen) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        } else {
            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        }
        camera.setParameters(param);
        isOpen = !isOpen;
        camera.startPreview();
    }

    @Override
    protected void onDestroy() {
        camera.stopPreview();
        camera.release();
        camera = null;
        super.onDestroy();
    }
}
