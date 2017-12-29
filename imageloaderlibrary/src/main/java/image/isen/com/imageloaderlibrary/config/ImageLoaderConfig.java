package image.isen.com.imageloaderlibrary.config;


import image.isen.com.imageloaderlibrary.cache.BitmapCache;
import image.isen.com.imageloaderlibrary.cache.MemoryCache;
import image.isen.com.imageloaderlibrary.policy.LoadPolicy;
import image.isen.com.imageloaderlibrary.policy.ReversePolicy;

/**
 * Created by Administrator on 2017/12/21.
 */
public class ImageLoaderConfig {
    //缓存策略
    private BitmapCache bitmapCache = new MemoryCache();
    //加载策略
    private LoadPolicy loadPolicy = new ReversePolicy();
    //默认开启的线程数(根据CPU的核数来开启线程数)
    private int threadCount = Runtime.getRuntime().availableProcessors();
    //图片显示的配置
    private DisplayConfig displayConfig = new DisplayConfig();

    private ImageLoaderConfig() {

    }

    ;

    /**
     * 建造者模式
     */
    public static class Builder {
        private ImageLoaderConfig config;

        public Builder() {
            config = new ImageLoaderConfig();
        }

        /**
         * 设置缓存策略
         *
         * @param bitmapCache
         * @return
         */
        public Builder setCachePolicy(BitmapCache bitmapCache) {
            config.bitmapCache = bitmapCache;
            return this;
        }

        /**
         * 设置加载策略
         *
         * @param loaderPolicy
         * @return
         */
        public Builder setLoaderPolicy(LoadPolicy loaderPolicy) {
            config.loadPolicy = loaderPolicy;
            return this;
        }

        /**
         * 设置开启线程数
         *
         * @param count
         * @return
         */
        public Builder setThreadCount(int count) {
            config.threadCount = count;
            return this;
        }

        /**
         * 设置加载过程中的图片
         *
         * @param resID
         * @return
         */
        public Builder setLoadingImage(int resID) {
            config.displayConfig.loadingImage = resID;
            return this;
        }

        /**
         * 设置加载失败的图片
         *
         * @param resID
         * @return
         */
        public Builder setFailedImage(int resID) {
            config.displayConfig.failedImage = resID;
            return this;
        }

        public ImageLoaderConfig build() {
            return config;
        }
    }

    public BitmapCache getBitmapCache() {
        return bitmapCache;
    }

    public LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }
}
