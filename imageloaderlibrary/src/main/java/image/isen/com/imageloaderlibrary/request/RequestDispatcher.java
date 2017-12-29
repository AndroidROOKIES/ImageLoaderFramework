package image.isen.com.imageloaderlibrary.request;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

import image.isen.com.imageloaderlibrary.loader.Loader;
import image.isen.com.imageloaderlibrary.loader.LoaderManager;


/**
 * Created by Administrator on 2017/12/21.
 * 转发器
 * 请求转发线程，不断从请求队列获取请求
 */
public class RequestDispatcher extends Thread {
    //请求队列
    private BlockingQueue<BitmapRequest> mRequestQueue;

    public RequestDispatcher(BlockingQueue<BitmapRequest> mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                BitmapRequest request = mRequestQueue.take();
                /**
                 * 处理请求对象
                 */
                String schema = parseSchema(request.getImageUrl());
                //获取加载器
                Loader loader = LoaderManager.getInstance().getLoader(schema);
                loader.loadImage(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过解析图片地址的前缀http、file来判断是本地加载还是网络加载
     * @param imageUrl
     * @return
     */
    private String parseSchema(String imageUrl) {
        if (imageUrl.contains("://")) {
            return imageUrl.split("://")[0];
        } else {
            Log.i("ImageLoader", "不支持此类型");
        }
        return null;
    }
}
