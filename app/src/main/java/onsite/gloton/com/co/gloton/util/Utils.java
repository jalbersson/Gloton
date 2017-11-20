package onsite.gloton.com.co.gloton.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 19/11/17.
 */

public class Utils extends AsyncTask<String, Void, Bitmap> {

    private Context context;
    private String url;
    private String nameFolder;
    private String nameImage;

    public Utils(String url,String nameImage, Context context,String nameFolder) {
        this.url = url;
        this.context = context;
        this.nameImage = nameImage;
        this.nameFolder = nameFolder;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        return downloadBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {


    }


    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();

            Log.d("statusCode", String.valueOf(statusCode));
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                Log.d("NombreImagen",String.valueOf(nameImage));
                File mFolder = new File(extStorageDirectory + "/gloton");
                if (!mFolder.exists()) {
                    mFolder.mkdir();
                }

                File subFolder = new File(mFolder + "/" + nameFolder);
                if (!subFolder.exists()) {
                    subFolder.mkdir();
                }

                File imgFile = new File(subFolder.getAbsolutePath()  +"/"+ nameImage);

                if (!imgFile.exists()) {
                    imgFile.createNewFile();
                }

                try {
                    FileOutputStream outStream = new FileOutputStream(imgFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return bitmap;
            }
        } catch (Exception e) {
            Log.d("URLCONNECTIONERROR", e.toString());
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();

            }
        }
        return null;
    }
}
