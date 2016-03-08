package retrofit;

import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

public interface ApiService {

	@Multipart
	@POST ("/upload")
	void upload(@Part ("myfile") TypedFile file, Callback<String> callback);


}
