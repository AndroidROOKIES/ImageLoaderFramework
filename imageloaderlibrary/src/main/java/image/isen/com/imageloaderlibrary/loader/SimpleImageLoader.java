package image.isen.com.imageloaderlibrary.loader;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

import image.isen.com.imageloaderlibrary.config.DisplayConfig;
import image.isen.com.imageloaderlibrary.config.ImageLoaderConfig;
import image.isen.com.imageloaderlibrary.request.BitmapRequest;
import image.isen.com.imageloaderlibrary.request.RequestQueue;


/**
 * Created by Administrator on 2017/12/21.
 */
public class SimpleImageLoader {
    //配置
    private ImageLoaderConfig config;
    //请求队列
    private RequestQueue mRequestQueue;

    private static volatile SimpleImageLoader mInstance;

    private SimpleImageLoader() {

    }

    private SimpleImageLoader(ImageLoaderConfig imageLoaderConfig) {
        this.config = imageLoaderConfig;
        mRequestQueue = new RequestQueue(config.getThreadCount());
        //开启请求队列
        mRequestQueue.start();
    }

    /**
     * 第一次初始化调用
     *
     * @param config
     * @return
     */
    public static SimpleImageLoader getInstance(ImageLoaderConfig config) {
        if (mInstance == null) {
            synchronized (SimpleImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new SimpleImageLoader(config);
                }
            }
        }
        return mInstance;
    }

    /**
     * 第二次调用
     *
     * @return
     */
    public static SimpleImageLoader getInstance() {
        if (mInstance == null) {
            throw new UnsupportedOperationException("没有初始化");
        }
        return mInstance;
    }

    /**
     * 对调用层暴露获取图片
     *
     * @param imageView
     * @param uri       http/file 开头
     */
    public void displayImage(ImageView imageView, String uri) {
        displayImage(imageView, uri, null, null);
    }

    /**
     * 重载
     *
     * @param imageView
     * @param uri
     * @param config
     * @param imageListener
     */
    public void displayImage(ImageView imageView, String uri, DisplayConfig config, ImageListener imageListener) {
        //实例化一个请求
        BitmapRequest bitmapRequest = new BitmapRequest(imageView, uri, imageListener, config);
        //添加请求到队列中
        mRequestQueue.addRequest(bitmapRequest);
    }

    //图片加载监听回调
    public static interface ImageListener {
        void onComplete(ImageView imageView, Bitmap bitmap, String uri);
    }

    //获取全局配置
    public ImageLoaderConfig getConfig() {
        return config;
    }
}
