package image.isen.com.imageloaderlibrary.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;

import image.isen.com.imageloaderlibrary.request.BitmapRequest;
import image.isen.com.imageloaderlibrary.utils.BitmapDecoder;
import image.isen.com.imageloaderlibrary.utils.ImageViewHelper;


/**
 * Created by Administrator on 2017/12/21.
 */
public class LocalLoader extends AbstractLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        //得到本地图片的路径
        final String path = Uri.parse(request.getImageUrl()).getPath();
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        BitmapDecoder decoder = new BitmapDecoder() {
            @Override
            public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {
                return BitmapFactory.decodeFile(path, options);
            }
        };
        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView())
                , ImageViewHelper.getImageViewHeight(request.getImageView()));
    }
}
