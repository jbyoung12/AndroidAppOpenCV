package opencvandroidtest;

/**
 * Created by joshuayoung on 6/22/17.
 */
import java.util.*;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.example.joshuayoung.opencvandroidtest.R;
import org.opencv.android.JavaCameraView;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;
import android.graphics.Bitmap;
import org.opencv.android.Utils;



public class OpenCVImage {
    public Mat hist = new Mat();
    public Mat image = new Mat();

    public OpenCVImage(Bitmap bm){
        Mat mat = new Mat();
        Utils.bitmapToMat(bm, mat);
        image = mat;
        go(mat);
    }

    public OpenCVImage(Mat m){
        go(m);
    }


    private void go (Mat m){
        hist = makeGrayHistogram(m);
    }

    private Mat makeGrayHistogram(Mat rgb){
        Mat gray = new Mat();
        Imgproc.cvtColor(rgb, gray, Imgproc.COLOR_RGB2GRAY);

        List<Mat> images = new ArrayList<Mat>();
        images.add(gray);
        int gbins = 32;
        MatOfFloat gRanges = new MatOfFloat(0,255);
        MatOfInt channels = new MatOfInt(0); //num of images
        MatOfInt histSize = new MatOfInt(gbins);

        Mat newHist = new Mat();
        Imgproc.calcHist(images, channels, new Mat(), newHist, histSize, gRanges);
        return newHist;


    }
}
