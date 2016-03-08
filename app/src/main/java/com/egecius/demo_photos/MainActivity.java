package com.egecius.demo_photos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import photo.ImageGetterImpl;
import photo.ImageGetterInterface;
import photo.PhotoFilesUtils;
import photo.PhotoUploader;
import retrofit.RetrofitSetup;

public class MainActivity extends AppCompatActivity {

	private PhotoUploader photoUploader = new PhotoUploader(new RetrofitSetup().getService());
	private ImageGetterInterface imageGetter = new ImageGetterImpl();
	private File currentImageFile = new PhotoFilesUtils().createFileForDefaultCamera();

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUi();
	}

	private void initUi() {
		setContentView(R.layout.activity_main);
		setupButton();
		imageView = (ImageView) findViewById(R.id.image);
	}

	private void setupButton() {
		findViewById(R.id.requestCamera).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				imageGetter.takePhotoWithCameraApp(MainActivity.this, currentImageFile);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		Log.v("Eg:MainActivity:55", "onActivityResult");
		if (resultCode == RESULT_OK && requestCode == ImageGetterInterface.REQUEST_IMAGE_CAPTURE){
			showImage();
//			uploadImage();
		}
	}

	private void showImage() {
		Log.v("Eg:MainActivity:61", "showImage currentImageFile " + currentImageFile);
		Picasso.with(getApplicationContext()).load(currentImageFile).into(imageView);
	}

	private void uploadImage() {
		Log.v("Eg:MainActivity:67", "uploadImage");
		photoUploader.upload(currentImageFile);
	}

}
