package com.egecius.demo_photos;

import android.util.Log;

import java.io.File;

import retrofit.ApiService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Single Responsibility:
 *
 * //todo
 */
public class PhotoUploader {

	private final ApiService apiService;

	public PhotoUploader(ApiService apiService) {
		this.apiService = apiService;
	}

	public void upload(File imageFile) {
		TypedFile typedFile = new TypedFile("multipart/form-data", imageFile);
		apiService.upload(typedFile, new Callback<String>() {
			@Override
			public void success(String s, Response response) {
				Log.v("Eg:PhotoUploader:29", "success");
			}

			@Override
			public void failure(RetrofitError error) {
				Log.v("Eg:PhotoUploader:37", "failure");
			}
		});

	}


}
