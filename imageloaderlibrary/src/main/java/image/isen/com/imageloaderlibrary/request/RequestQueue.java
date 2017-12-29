package image.isen.com.imageloaderlibrary.request;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/12/21.
 */
public class RequestQueue {

    /**
     * 阻塞式队列
     * 多线程共享
     * 生产效率与消费效率相差太远
     * 优先级队列
     * 优先级高的先被消费
     * 每个请求都有编号
     */
    private BlockingQueue<BitmapRequest> mRequestQueue = new PriorityBlockingQueue<>();
    //转发器的数量
    private int threadCount;
    //一组转发器
    private RequestDispatcher[] mDispatchers;
    //线程安全的  不能i++  ++i
    private AtomicInteger i = new AtomicInteger(0);

    public RequestQueue(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * 添加请求对象
     *
     * @param request
     */
    public void addRequest(BitmapRequest request) {
        //判断请求队列中是否包含该请求
        if (!mRequestQueue.contains(request)) {
            //给请求进行编号
            request.setSerialNO(i.incrementAndGet());
            mRequestQueue.add(request);
        } else {
            Log.i("ImageLoader", "请求已经存在 编号：" + request.getSerialNO());
        }
    }

    /**
     * 开启请求
     */
    public void start() {
        //先停止再开启
        stop();
        startDispatchers();
    }

    /**
     * 开启转发器
     */
    private void startDispatchers() {
        mDispatchers = new RequestDispatcher[threadCount];
        for (int i = 0; i < threadCount; i++) {
            RequestDispatcher p = new RequestDispatcher(mRequestQueue);
            mDispatchers[i] = p;
            mDispatchers[i].start();
        }
    }

    //暂停请求
    public void stop() {

    }

}
