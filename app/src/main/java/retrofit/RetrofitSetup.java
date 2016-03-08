package retrofit;

import com.egecius.demo_photos.BuildConfig;

/** Prepares Retrofit to be used */
public class RetrofitSetup {

	private static final String BASE_URL = "http://workt.co.uk/api/contractor/";

	/** Returns {@link ApiService} which allows making API calls */
	public ApiService getService() {
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(BASE_URL)
				.setLogLevel(getLoggingLevel())
				.build();

		return restAdapter.create(ApiService.class);
	}

	private RestAdapter.LogLevel getLoggingLevel() {

		if (BuildConfig.DEBUG) {
			return RestAdapter.LogLevel.FULL;
		} else {
			return RestAdapter.LogLevel.NONE;
		}
	}

}