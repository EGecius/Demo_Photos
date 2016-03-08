package com.egecius.demo_photos;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import retrofit.RetrofitSetup;

public class MainActivity extends AppCompatActivity {
	
	PhotoFilesUtils photoFilesUtils = new PhotoFilesUtils();
	PhotoUploader photoUploader = new PhotoUploader(new RetrofitSetup().getService());

	private File currentImageFile;
	/** Request code */
	static final int REQUEST_IMAGE_CAPTURE = 0;
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
				takePictureWithCameraApp();
			}
		});
	}

	/** requests the default camera of the device through an intent
	 * that also saves the photo to file
	 */
	private void takePictureWithCameraApp() {
		Log.v("Eg:MainActivity:41", "takePictureWithCameraApp");
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		ComponentName componentName = takePictureIntent.resolveActivity(getPackageManager());

		Log.i("Eg:MainActivity:46", "takePictureWithCameraApp componentName " + componentName);

		if (componentName != null) {
			//create the file for where the photo should be saved
			currentImageFile = photoFilesUtils.createFileForDefaultCamera();
			//if file is not null add it to the intent and start it.
			if (currentImageFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		} else {
			Log.i("Eg:MainActivity:57", "takePictureWithCameraApp componentName " + componentName);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){


			Log.v("Eg:MainActivity:47", "onActivityResult");

			boolean isResultOk = isResultOk(resultCode);

			showImage();
			uploadImage();

			Log.i("Eg:MainActivity:50", "onActivityResult requestCode " + requestCode);
			Log.i("Eg:MainActivity:52", "onActivityResult isResultOk " + isResultOk);
		}
	}

	private void uploadImage() {
		photoUploader.upload(currentImageFile);
	}

	private void showImage() {
		Picasso.with(getApplicationContext()).load(currentImageFile).into(imageView);
	}

	private boolean isResultOk(int resultCode) {
		return resultCode == RESULT_OK;
	}

}
