package image.isen.com.imageloaderlibrary.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import image.isen.com.imageloaderlibrary.disk.DiskLruCache;
import image.isen.com.imageloaderlibrary.disk.IOUtil;
import image.isen.com.imageloaderlibrary.request.BitmapRequest;


/**
 * Created by Administrator on 2017/12/21.
 */
public class DiskCache implements BitmapCache {
    private static DiskCache mDiskCache;
    //缓存路径
    private String mCacheDir = "Image";
    private static final int MB = 1024 * 1024;
    //jackwharton的杰作
    private DiskLruCache mDiskLruCache;

    private DiskCache(Context context) {
        initDiskCache(context);
    }

    public static DiskCache getInstance(Context context) {
        if (mDiskCache == null) {
            synchronized (DiskCache.class) {
                if (mDiskCache == null) {
                    mDiskCache = new DiskCache(context);
                }
            }
        }
        return mDiskCache;
    }

    private void initDiskCache(Context context) {
        //得到缓存的目录 android/data/data/image.isen.com.imageloaderframework/cache/Image
        File directory = getDiskCache(mCacheDir, context);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            //最后一个参数 指定缓存容量
            mDiskLruCache = DiskLruCache.open(directory, getAppVersion(context), 1, 50 * MB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存路径
     *
     * @param mCacheDir
     * @param context
     * @return
     */
    private File getDiskCache(String mCacheDir, Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + mCacheDir);
    }

    /**
     * 获取app版本号
     *
     * @param context
     * @return
     */
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        DiskLruCache.Editor edtor = null;
        OutputStream os = null;
        try {
            //路径必须是合法字符
            edtor = mDiskLruCache.edit(request.getImageUriMD5());
            os = edtor.newOutputStream(0);
            if (persistBitmap2Disk(bitmap, os)) {
                edtor.commit();
            } else {
                edtor.abort();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean persistBitmap2Disk(Bitmap bitmap, OutputStream os) {
        BufferedOutputStream bos = new BufferedOutputStream(os);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            IOUtil.closeQuietly(bos);
        }
        return true;

    }

    @Override
    public Bitmap get(BitmapRequest request) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(request.getImageUriMD5());
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {
        try {
            mDiskLruCache.remove(request.getImageUriMD5());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
