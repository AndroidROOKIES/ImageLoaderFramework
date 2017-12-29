package image.isen.com.imageloaderlibrary.policy;

import image.isen.com.imageloaderlibrary.request.BitmapRequest;

/**
 * Created by Administrator on 2017/12/21.
 * 先进先出的加载策略
 */
public class SerialPolicy implements LoadPolicy {
    @Override
    public int compareTo(BitmapRequest request1, BitmapRequest request2) {
        return request1.getSerialNO() - request2.getSerialNO();
    }
}
