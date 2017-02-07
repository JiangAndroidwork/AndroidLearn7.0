package com.laojiang.androidlearn70.utils.weight;



import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/1/10 17:41.
 */
public class CanDragListView extends ListView {

    private ListAdapter mAdapter;
    private WindowManager mWindowManager;
    /**
     * item镜像的布局参数
     */
    private WindowManager.LayoutParams mWindowLayoutParams;
    private WindowManager.LayoutParams mNewWindowLayoutParams;
    /**
     * 振动器
     */
    private Vibrator mVibrator;
    /**
     * 选中的item的position
     */
    private int mSelectedPosition;
    /**
     * 选中的item的View对象
     */
    private View mItemView;
    /**
     * 用于拖拽的镜像，这里直接用一个ImageView装载Bitmap
     */
    private ImageView mDragIV;
    private ImageView mNewDragIv;
    /**
     * 选中的item的镜像Bitmap
     */
    private Bitmap mBitmap;
    /**
     * 按下的点到所在item的上边缘的距离
     */
    private int mPoint2ItemTop;

    /**
     * 按下的点到所在item的左边缘的距离
     */
    private int mPoint2ItemLeft;

    /**
     * CanDragListView距离屏幕顶部的偏移量
     */
    private int mOffset2Top;
    /**
     * CanDragListView自动向下滚动的边界值
     */
    private int mDownScrollBorder;

    /**
     * CanDragListView自动向上滚动的边界值
     */
    private int mUpScrollBorder;
    /**
     * CanDragListView自动滚动的速度
     */
    private static final int speed = 20;

    /**
     * CanDragListView距离屏幕左边的偏移量
     */
    private int mOffset2Left;
    /**
     * 状态栏的高度
     */
    private int mStatusHeight;
    /**
     * 按下的系统时间
     */
    private long mActionDownTime = 0;
    /**
     * 移动的系统时间
     */
    private long mActionMoveTime = 0;
    /**
     * 默认长按事件时间是1000毫秒
     */
    private long mLongClickTime = 1000;
    /**
     * 是否可拖拽，默认为false
     */
    private boolean isDrag = false;
    /**
     * 按下是的x坐标
     */
    private int mDownX;
    /**
     * 按下是的y坐标
     */
    private int mDownY;

    /**
     * item发生变化回调的接口
     */
    private OnChanageListener onChanageListener;

    /**
     * 设置回调接口
     *
     * @param onChanageListener
     */
    public void setOnChangeListener(OnChanageListener onChanageListener) {
        this.onChanageListener = onChanageListener;
    }

    public CanDragListView(Context context) {
        this(context, null);
    }

    public CanDragListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanDragListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mAdapter = getAdapter();
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mStatusHeight = getStatusHeight(context); // 获取状态栏的高度
    }

    private Handler mHandler = new Handler();

    // 用来处理长按的Runnable
    private Runnable mLongClickRunnable = new Runnable() {

        @Override
        public void run() {
            isDrag = true; // 设置可以拖拽
            mVibrator.vibrate(100); // 震动100毫秒
            if (mItemView != null) {
                mItemView.setVisibility(View.INVISIBLE);// 隐藏该item
            }
            Log.i("CanDragListView", "**mLongClickRunnable**");
            // 根据我们按下的点显示item镜像
            createDragImage(mBitmap, mDownX, mDownY);
        }
    };

    /**
     * 当mDownY的值大于向上滚动的边界值，触发自动向上滚动 当mDownY的值小于向下滚动的边界值，触犯自动向下滚动 否则不进行滚动
     */
    private Runnable mScrollRunnable = new Runnable() {

        @Override
        public void run() {
            int scrollY;
            if (mDownY > mUpScrollBorder) {
                scrollY = speed;
                mHandler.postDelayed(mScrollRunnable, 25);
            } else if (mDownY < mDownScrollBorder) {
                scrollY = -speed;
                mHandler.postDelayed(mScrollRunnable, 25);
            } else {
                scrollY = 0;
                mHandler.removeCallbacks(mScrollRunnable);
            }

            // 所以我们在这里调用下onSwapItem()方法来交换item
            onSwapItem(mDownY, mDownY);

            smoothScrollBy(scrollY, 10);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Log.i("CanDragListView", mSelectedPosition+"****"+mItemView);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mActionDownTime = event.getDownTime();
                mDownX = (int) event.getX();
                mDownY = (int) event.getY();

                // 根据按下的坐标获取item对应的position
                mSelectedPosition = pointToPosition(mDownX, mDownY);
                // 如果是无效的position，即值为-1
                if (mSelectedPosition == AdapterView.INVALID_POSITION) {
                    return super.onTouchEvent(event);
                }
                // 根据position获取对应的item
                mItemView = getChildAt(mSelectedPosition - getFirstVisiblePosition());
                // 使用Handler延迟mLongClickTime执行mLongClickRunnable
                mHandler.postDelayed(mLongClickRunnable, mLongClickTime);
                if (mItemView != null) {
                    // 下面这几个距离大家可以参考我的博客上面的图来理解下
                    mPoint2ItemTop = mDownY - mItemView.getTop();
                    mPoint2ItemLeft = mDownX - mItemView.getLeft();

                    mOffset2Top = (int) (event.getRawY() - mDownY);
                    mOffset2Left = (int) (event.getRawX() - mDownX);

                    // 获取CanDragListView自动向上滚动的偏移量，小于这个值，CanDragListView向下滚动
                    mDownScrollBorder = getHeight() / 4;
                    // 获取CanDragListView自动向下滚动的偏移量，大于这个值，CanDragListView向上滚动
                    mUpScrollBorder = getHeight() * 3 / 4;

                    // 将该item进行绘图缓存
                    mItemView.setDrawingCacheEnabled(true);
                    // 从缓存中获取bitmap
                    mBitmap = Bitmap.createBitmap(mItemView.getDrawingCache());
                    // 释放绘图缓存，避免出现重复的缓存对象
                    mItemView.destroyDrawingCache();
                }

                // Log.i("CanDragListView", "****"+isDrag);
                break;
            case MotionEvent.ACTION_MOVE:
                // TODO
                if (isDrag) {
                    int moveX = (int) event.getX();
                    int moveY = (int) event.getY();
                    if (!isOnTouchInItem(mItemView, moveX, moveY)) {
                        mHandler.removeCallbacks(mLongClickRunnable);
                    }
                    mDownX = moveX;
                    mDownY = moveY;
                    onDragItem(moveX, moveY);
                }
                break;
            case MotionEvent.ACTION_UP:
                onStopDrag();
                mHandler.removeCallbacks(mLongClickRunnable);
                mHandler.removeCallbacks(mScrollRunnable);

                isDrag = false;
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 判断手指按下的坐标是否在item范围内
     *
     * @param view
     * @param downX
     * @param downY
     * @return
     */
    private boolean isOnTouchInItem(View view, int downX, int downY) {
        if (view == null) {
            return false;
        }
        int leftX = view.getLeft();
        int topY = view.getTop();
        if (downX < leftX || downX > leftX + view.getWidth()) {
            return false;
        }
        if (downY < topY || downY > topY + view.getHeight()) {
            return false;
        }
        return true;
    }

    /**
     * 创建拖动的镜像
     *
     * @param bitmap
     * @param downX
     *            按下的点相对父控件的X坐标
     * @param downY
     *            按下的点相对父控件的X坐标
     */
    private void createDragImage(Bitmap bitmap, int downX, int downY) {
        mWindowLayoutParams = new WindowManager.LayoutParams();
        mWindowLayoutParams.format = PixelFormat.TRANSLUCENT; // 图片之外的其他地方透明
        mWindowLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mWindowLayoutParams.x = downX - mPoint2ItemLeft + mOffset2Left;
        mWindowLayoutParams.y = downY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
        mWindowLayoutParams.alpha = 0.55f; // 透明度
        mWindowLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        mNewWindowLayoutParams = new WindowManager.LayoutParams();
        mNewWindowLayoutParams.format = PixelFormat.TRANSLUCENT; // 图片之外的其他地方透明
        mNewWindowLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mNewWindowLayoutParams.x = downX - mPoint2ItemLeft + mOffset2Left;
        mNewWindowLayoutParams.y = downY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
        // mNewWindowLayoutParams.alpha = 0.55f; // 透明度
        mNewWindowLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mNewWindowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mNewWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        mDragIV = new ImageView(getContext());
        mNewDragIv = new ImageView(getContext());
        mDragIV.setImageBitmap(bitmap);
        mWindowManager.addView(mDragIV, mWindowLayoutParams);
        mWindowManager.addView(mNewDragIv, mNewWindowLayoutParams);
    }

    /**
     * 移除镜像
     */
    private void removeDragImage() {
        if (mDragIV != null) {
            mWindowManager.removeView(mDragIV);
            mDragIV = null;
        }
        if (mNewDragIv != null) {
            mWindowManager.removeView(mNewDragIv);
            mNewDragIv = null;
        }
    }

    /**
     * 拖动item，在里面实现了item镜像的位置更新，item的相互交换以及ListView的自行滚动
     *
     */
    private void onDragItem(int moveX, int moveY) {
        if (mWindowLayoutParams != null && mDragIV != null) {
            mWindowLayoutParams.x = moveX - mPoint2ItemLeft + mOffset2Left;
            mWindowLayoutParams.y = moveY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
            mWindowManager.updateViewLayout(mDragIV, mWindowLayoutParams); // 更新镜像的位置
        }
        onSwapItem(moveX, moveY);
        // ListView自动滚动
        mHandler.post(mScrollRunnable);

    }

    /**
     * 交换item,并且控制item之间的显示与隐藏效果
     *
     * @param moveX
     * @param moveY
     */
    private void onSwapItem(int moveX, int moveY) {
        // 获取我们手指移动到的那个item的position
        int position = pointToPosition(moveX, moveY);

        // 假如tempPosition 改变了并且tempPosition不等于-1,则进行交换
        if (position != mSelectedPosition && position != AdapterView.INVALID_POSITION) {

            // mAdapter.getItem(mSelectedPosition);
            View newItem = getChildAt(position - getFirstVisiblePosition());
            View oldItem = getChildAt(mSelectedPosition - getFirstVisiblePosition());

            mNewWindowLayoutParams.x = moveX - (moveX - oldItem.getLeft()) + mOffset2Left;
            mNewWindowLayoutParams.y = moveY - (moveY - oldItem.getTop()) + mOffset2Top - mStatusHeight;

            newItem.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(newItem.getDrawingCache());
            newItem.destroyDrawingCache();
            mNewDragIv.setImageBitmap(bitmap);
            if (newItem != null && oldItem != null) {
                newItem.setVisibility(INVISIBLE);// 隐藏拖动到的位置的item
                oldItem.setVisibility(VISIBLE);//显示之前的
                mWindowManager.updateViewLayout(mNewDragIv, mNewWindowLayoutParams); // 更新镜像的位置
                if (onChanageListener != null) {
                    Log.i("CanDragListView", "**onSwapItem**");
                    onChanageListener.onChange(mSelectedPosition, position);
                }
            }

            mSelectedPosition = position;
        }
    }

    /**
     * 停止拖拽我们将之前隐藏的item显示出来，并将镜像移除
     */
    private void onStopDrag() {
        View view = getChildAt(mSelectedPosition - getFirstVisiblePosition());
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
        // ((DragAdapter)this.getAdapter()).setItemHide(-1);
        removeDragImage();
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    private static int getStatusHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * 监听数据拖拽的接口，用来更新数据显示
     */
    public interface OnChanageListener {

        /**
         * 当item交换位置的时候回调的方法，在此处实现数据的交换
         *
         * @param start
         *            开始的position
         * @param to
         *            拖拽到的position
         */
        public void onChange(int start, int to);
    }

}

