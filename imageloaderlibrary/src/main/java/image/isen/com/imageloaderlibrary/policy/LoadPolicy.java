package image.isen.com.imageloaderlibrary.policy;

import image.isen.com.imageloaderlibrary.request.BitmapRequest;

/**
 * Created by Administrator on 2017/12/21.
 * 加载策略
 */
public interface LoadPolicy {
    /**
     * 两个BitmapRequest进行优先级的比较
     * @param request1
     * @param request2
     * @return
     */
    int compareTo(BitmapRequest request1, BitmapRequest request2);
}
