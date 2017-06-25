package opencvandroidtest;

/**
 * Created by joshuayoung on 6/22/17.
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import android.view.View;
import java.util.ArrayList;

import com.example.joshuayoung.opencvandroidtest.R;

import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;



public class MainActivity_start_screen extends Activity{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public Mat hist = new Mat();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Called", "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.start_screen);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    protected void dispatchTakePictureIntent(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView image = (ImageView) findViewById(R.id.imageUploaded);
            image.setImageBitmap(imageBitmap);
            initialize(imageBitmap);
        }
    }

    protected void continueButton(View v) {
        //go to next view

        Intent intent = new Intent(this, MainActivity_show_camera.class);
        ArrayList<Integer> imageInt = new ArrayList<Integer>();
        org.opencv.utils.Converters.Mat_to_vector_int(hist, imageInt);
        intent.putExtra("histOrig", imageInt);
        this.startActivity(intent);
    }

    private void initialize(Bitmap imBitmap){
        OpenCVImage im = new OpenCVImage(imBitmap);
        Mat h = im.hist;
        hist = h;

    }

}