package photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * Allows getting a user image
 */
public class ImageGetterImpl implements ImageGetterInterface {

	@Override
	public void takePhotoWithCameraApp(Activity activity, @NonNull File imageFile) {
		Context ctx = activity.getApplicationContext();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		if (canIntentBeHandled(ctx, intent)) {
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
			activity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
		}
	}

	private boolean canIntentBeHandled(Context ctx, Intent intent) {
		return intent.resolveActivity(ctx.getPackageManager()) != null;
	}

}
