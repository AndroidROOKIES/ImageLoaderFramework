package image.isen.com.imageloaderlibrary.policy;
import image.isen.com.imageloaderlibrary.request.BitmapRequest;

/**
 * Created by Administrator on 2017/12/21.
 * 后进先出的加载策略
 */
public class ReversePolicy implements LoadPolicy {
    @Override
    public int compareTo(BitmapRequest request1, BitmapRequest request2) {
        return request2.getSerialNO() - request1.getSerialNO();
    }
}
