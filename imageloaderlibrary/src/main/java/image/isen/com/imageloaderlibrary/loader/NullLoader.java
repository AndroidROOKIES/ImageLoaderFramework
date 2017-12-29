package image.isen.com.imageloaderlibrary.loader;

import android.graphics.Bitmap;

import image.isen.com.imageloaderlibrary.request.BitmapRequest;


/**
 * Created by Administrator on 2017/12/22.
 */
public class NullLoader extends AbstractLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        return null;
    }
}
