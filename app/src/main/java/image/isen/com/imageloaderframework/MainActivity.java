package image.isen.com.imageloaderframework;

import android.Manifest;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

import image.isen.com.imageloaderlibrary.cache.DoubleCache;
import image.isen.com.imageloaderlibrary.config.ImageLoaderConfig;
import image.isen.com.imageloaderlibrary.loader.SimpleImageLoader;
import image.isen.com.imageloaderlibrary.policy.ReversePolicy;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;


public class MainActivity extends AppCompatActivity {
    SimpleImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "读写SD卡权限", R.drawable.permission_ic_storage));
        HiPermission.create(MainActivity.this).permissions(permissionItems).checkMutiPermission(new PermissionCallback() {
            @Override
            public void onClose() {

            }

            @Override
            public void onFinish() {
                init();
            }

            @Override
            public void onDeny(String permission, int position) {

            }

            @Override
            public void onGuarantee(String permission, int position) {

            }
        });
    }

    private void init() {
        GridView listview = (GridView) findViewById(R.id.listview);
        listview.setAdapter(new MyAdapter(this));

        //配置
        ImageLoaderConfig.Builder build = new ImageLoaderConfig.Builder();
        build.setThreadCount(3) //线程数量
                .setLoaderPolicy(new ReversePolicy()) //加载策略
                .setCachePolicy(new DoubleCache(this)) //缓存策略
                .setLoadingImage(R.drawable.loading)//正在加载时的默认图片
                .setFailedImage(R.drawable.not_found);//加载失败显示的图片

        ImageLoaderConfig config = build.build();
        //初始化
        imageLoader = SimpleImageLoader.getInstance(config);
    }


    class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return imageThumbUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return imageThumbUrls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item, parent, false);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ImageView imageView = holder.imageView;

            //请求图片
            imageLoader.displayImage(imageView, imageThumbUrls[position]);
            return convertView;
        }

        private class ViewHolder {
            public ImageView imageView;
        }
    }


    public final static String[] imageThumbUrls = new String[]{
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1896554130,2595173329&fm=27&gp=0.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
            "http://s.happymmall.com/mmall_fe/dist/resource/banner-qx.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",//DB123421AC
            "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_8243.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383248_3693.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383242_3127.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383242_9576.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383242_1721.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383219_5806.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383214_7794.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383213_4418.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383213_3557.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383210_8779.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383172_4577.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383166_3407.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383166_2224.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383166_7301.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383165_7197.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383150_8410.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383131_3736.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383130_5094.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383130_7393.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383129_8813.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383100_3554.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383093_7894.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383092_2432.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383092_3071.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383091_3119.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383059_6589.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383059_8814.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383059_2237.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383058_4330.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383038_3602.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382942_3079.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382942_8125.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382942_4881.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382941_4559.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382941_3845.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382924_8955.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382923_2141.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382923_8437.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382922_6166.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382922_4843.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382905_5804.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382904_3362.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382904_2312.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382904_4960.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382900_2418.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382881_4490.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382881_5935.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382880_3865.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382880_4662.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382879_2553.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382862_5375.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382862_1748.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382861_7618.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382861_8606.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382861_8949.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382841_9821.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382840_6603.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382840_2405.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382840_6354.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382839_5779.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382810_7578.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382810_2436.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382809_3883.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382809_6269.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382808_4179.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382790_8326.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382789_7174.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382789_5170.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382789_4118.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382788_9532.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382767_3184.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382767_4772.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382766_4924.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382766_5762.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406382765_7341.jpg"};
}
