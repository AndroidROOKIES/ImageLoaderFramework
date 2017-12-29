package image.isen.com.imageloaderlibrary.cache;

import android.graphics.Bitmap;

import image.isen.com.imageloaderlibrary.request.BitmapRequest;


/**
 * Created by Administrator on 2017/12/21.
 * 缓存策略
 */
public interface BitmapCache {
    /**
     * 缓存bitmap
     *
     * @param request
     * @param bitmap
     */
    void put(BitmapRequest request, Bitmap bitmap);

    /**
     * 通过请求取bitmap
     *
     * @param request
     */
    Bitmap get(BitmapRequest request);

    /**
     * 移除对象
     *
     * @param request
     */
    void remove(BitmapRequest request);
}
