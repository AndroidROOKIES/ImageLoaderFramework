package image.isen.com.imageloaderlibrary.loader;


import image.isen.com.imageloaderlibrary.request.BitmapRequest;

/**
 * Created by Administrator on 2017/12/21.
 * 加载器
 */
public interface Loader {
    /**
     * 加载图片
     *
     * @param request
     */
    void loadImage(BitmapRequest request);
}
