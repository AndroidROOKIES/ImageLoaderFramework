package image.isen.com.imageloaderlibrary.request;

import android.widget.ImageView;

import java.lang.ref.SoftReference;

import image.isen.com.imageloaderlibrary.config.DisplayConfig;
import image.isen.com.imageloaderlibrary.loader.SimpleImageLoader;
import image.isen.com.imageloaderlibrary.policy.LoadPolicy;
import image.isen.com.imageloaderlibrary.utils.MD5Utils;


/**
 * Created by Administrator on 2017/12/21.
 * 对请求进行排序和封装
 */
public class BitmapRequest implements Comparable<BitmapRequest> {
    //持有imageView的软引用
    private SoftReference<ImageView> imageViewSoft;
    //图片路径
    private String imageUrl;
    //MD5加密的图片路径
    private String imageUriMD5;
    //下载完成的监听
    public SimpleImageLoader.ImageListener imageListener;

    private DisplayConfig displayConfig;


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SimpleImageLoader.ImageListener getImageListener() {
        return imageListener;
    }

    public void setImageListener(SimpleImageLoader.ImageListener imageListener) {
        this.imageListener = imageListener;
    }


    //加载策略
    private LoadPolicy loadPolicy = SimpleImageLoader.getInstance().getConfig().getLoadPolicy();
    //优先级编号
    private int serialNO;

    public BitmapRequest(ImageView imageView, String imageUrl, SimpleImageLoader.ImageListener imageListener, DisplayConfig displayConfig) {
        this.imageViewSoft = new SoftReference<ImageView>(imageView);
        //解决图片错位，设置可见的imageview的tag
        imageView.setTag(imageUrl);
        this.imageUrl = imageUrl;
        this.imageUriMD5 = MD5Utils.toMD5(imageUrl);
        this.imageListener = imageListener;
        if (displayConfig != null) {
            this.displayConfig = displayConfig;
        }
    }

    public int getSerialNO() {
        return serialNO;
    }

    public void setSerialNO(int serialNO) {
        this.serialNO = serialNO;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageUriMD5() {
        return imageUriMD5;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    public LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    /**
     * 优先级的确定
     *
     * @param bitmapRequest
     * @return
     */
    @Override
    public int compareTo(BitmapRequest bitmapRequest) {
        return loadPolicy.compareTo(bitmapRequest, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitmapRequest that = (BitmapRequest) o;

        if (serialNO != that.serialNO) return false;
        return loadPolicy != null ? loadPolicy.equals(that.loadPolicy) : that.loadPolicy == null;

    }

    @Override
    public int hashCode() {
        int result = loadPolicy != null ? loadPolicy.hashCode() : 0;
        result = 31 * result + serialNO;
        return result;
    }

    public ImageView getImageView() {
        return imageViewSoft.get();
    }
}
