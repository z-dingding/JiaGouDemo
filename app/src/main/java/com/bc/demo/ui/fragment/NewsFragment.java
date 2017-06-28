package com.bc.demo.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.demo.AppContext;
import com.bc.demo.R;
import com.bc.demo.base.BaseFragment;
import com.bc.demo.mvp.view.NoteBookActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ding on 2017/5/21.
 */
public class NewsFragment extends BaseFragment {


    @Bind(R.id.fm_my_switch)
    Switch fmMySwitch;
    @Bind(R.id.fm_my_tv_changePhoto)
    TextView tv_changPhoto;
    @Bind(R.id.fm_my_circleImg)
    ImageView iv_circleImg;
    @Bind(R.id.fm_my_tv_noteBook)
    TextView tv_noteBook;


   //图片的类型 setDataAndType()的Type
    private static final String IMAGE_UNSPECIFIED = "image/*";
 /**　图片文件　*/
    File pictureFile;
    /**　图片的名称　*/
    String imageName;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void initView(View view) {

    }


    @Override
    public void initData() {
        boolean isShowNightModel = AppContext.getSwitchModel();
        fmMySwitch.setChecked(isShowNightModel);
        fmMySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AppContext.setSwitchModel(b);
                NewsFragment.this.getActivity().recreate();
            }
        });
    }

    @OnClick({R.id.fm_my_tv_changePhoto, R.id.fm_my_circleImg, R.id.fm_my_tv_noteBook})
    public void setClickMethod(View view) {
        switch (view.getId()) {
            case R.id.fm_my_tv_changePhoto:
                new CameraPop(getActivity(), view, "aaa");

                break;
            case R.id.fm_my_circleImg:
                new CameraPop(getActivity(), view, "aaa");

                break;
            case R.id.fm_my_tv_noteBook:
                startActivity(new Intent(getActivity(), NoteBookActivity.class));
                break;

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class CameraPop extends PopupWindow {
        private Context mContext;
        private String type;

        public CameraPop(final Context mContext, View parent, final String type) {
            this.type = type;
            this.mContext = mContext;

            View view = View.inflate(mContext, R.layout.camara_pop, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_in));
            LinearLayout ll_popup = (LinearLayout) view
                    .findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.in_to_top));

            // 设置popupWindow的宽和高
            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            setHeight(ViewGroup.LayoutParams.FILL_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();
            //拍照
            Button camera = (Button) view
                    .findViewById(R.id.item_popupwindows_camera);
            //相册
            Button photoPic = (Button) view
                    .findViewById(R.id.item_popupwindows_Photo);
            //取消
            Button cancle = (Button) view
                    .findViewById(R.id.item_popupwindows_cancel);
            //选择相机拍照
            camera.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    imageName = "/" + getStringToday() + ".jpg";
                    Intent camera_Intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    camera_Intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                            Environment.getExternalStorageDirectory(), imageName)));
                    startActivityForResult(camera_Intent,
                            1);//参数一 Intent,参数二 请求码 1
                    dismiss();
                }
            });
            //选择从相册获取
            photoPic.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_photo = new Intent(Intent.ACTION_PICK, null);
                    intent_photo.setDataAndType(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            IMAGE_UNSPECIFIED);
                    startActivityForResult(intent_photo, 2);////参数一 Intent,参数二 请求码 1
                    dismiss();
                }
            });
            //取消操作
            cancle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:
               //得到图片文件
                pictureFile = new File(Environment.getExternalStorageDirectory()
                        + imageName);
                //转化为uri裁剪
                startPhotoZoom(Uri.fromFile(pictureFile));
                break;
            case 2:
                //直接获取uri裁剪
                Uri uri = data.getData();
                startPhotoZoom(uri);

                break;

            case 3: // 取得裁剪后的图片
                if (data == null) {
                    return;
                }
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
                    photo.compress(Bitmap.CompressFormat.JPEG, 50, stream);

                    iv_circleImg.setImageBitmap(photo);//这个应该在上传成功后显示的  下面是上传的网络请求
                    //接下来可进行上传操作可以上传File类型或ByeArrayOutPutStream 类型
                   // ........
                }

                break;

        }

    }


    /**
     * 获取当前时间
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 图片剪切
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 180);
        intent.putExtra("outputY", 180);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }


}
