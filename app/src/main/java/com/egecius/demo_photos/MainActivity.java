package com.egecius.demo_photos;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

	PhotoFilesUtils photoFilesUtils = new PhotoFilesUtils();

	private File currentImageFile;
	/** Request code */
	static final int REQUEST_IMAGE_CAPTURE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupButton();
	}

	private void setupButton() {
		findViewById(R.id.requestCamera).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				requestDefaultCamera();
			}
		});
	}

	/** requests the default camera of the device through an intent
	 * that also saves the photo to file
	 */
	private void requestDefaultCamera() {
		Log.v("Eg:MainActivity:41", "requestDefaultCamera");
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		ComponentName componentName = takePictureIntent.resolveActivity(getPackageManager());

		Log.i("Eg:MainActivity:46", "requestDefaultCamera componentName " + componentName);

		if (componentName != null) {
			//create the file for where the photo should be saved
			currentImageFile = photoFilesUtils.createFileForDefaultCamera();
			//if file is not null add it to the intent and start it.
			if (currentImageFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		} else {
			Log.i("Eg:MainActivity:57", "requestDefaultCamera componentName " + componentName);
		}
	}

	//ACTIVITY LIFECYCLE METHODS
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){


			Log.v("Eg:MainActivity:47", "onActivityResult");

			boolean isResultOk = isResultOk(resultCode);

			Log.i("Eg:MainActivity:50", "onActivityResult requestCode " + requestCode);
			Log.i("Eg:MainActivity:52", "onActivityResult isResultOk " + isResultOk);

			//displayImage();
			//displayThumbnails();y);
		}
		else{
			//Delete the file as it is blank, no picture was taken
//			currentImageFile.delete();
//			finish();
		}
	}

	private boolean isResultOk(int resultCode) {
		return resultCode == RESULT_OK;
	}

}
