package com.egecius.demo_photos;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoFilesUtils {

	/*Method to create a file to send to the default camera so it knows where to
		*save the created image
		*/
	public File createFileForDefaultCamera(){
		// Location where the photos will be stored. The created images will be shared
		// between applications and persist after app has been uninstalled.
		//File mediaStorageDir = new File(Environment.getExternalStorageDirectory()+"/FiatAccompli/");
		File mediaStorageDir = new File(Environment.getExternalStorageDirectory()+"/DCIM/Camera");
		// Create the storage directory if it does not exist
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				Log.d("CreateFile", "Failed to create photo gallery directory");
				return null;
			}
		}

		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";

		File image = null;
		try{
			image = File.createTempFile(imageFileName, ".jpg", mediaStorageDir);
		}catch(IOException e){
			Log.d("MainActivity", "Failed to create photo file");
			e.printStackTrace();
		}
		return image;
	}

}
