package photo;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * Allows getting images, such as photo taken by the camera app
 */
public interface PhotoGetterInterface {

	int REQUEST_IMAGE_CAPTURE = 1;

	/**
	 * Requests default camera app to take a picture and save it into the file provided
	 *
	 * @param activity activity that will receive onActivityResult call with request code {@link
	 * PhotoGetterImpl#REQUEST_IMAGE_CAPTURE}
	 * @param imageFile file to write taken photo to
	 */
	void takePhotoWithCameraApp(Activity activity, @NonNull File imageFile);
}
