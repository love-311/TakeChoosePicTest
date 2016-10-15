package www.love311.cn.takechoosepictest;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends TakePhotoActivity {

    @BindView(R.id.take_a_pic)
    TextView takeAPic;
    @BindView(R.id.choose_a_pic)
    TextView chooseAPic;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private TakePhoto takePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        takePhoto = getTakePhoto();

    }

    @OnClick({R.id.take_a_pic, R.id.choose_a_pic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.take_a_pic:
                File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                takePhoto.onPickFromCapture(imageUri);
                break;
            case R.id.choose_a_pic:
                takePhoto.onPickMultiple(5);
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images) {
        Glide.with(this).load(new File(images.get(images.size() - 1).getPath())).into(ivPic);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }
}
