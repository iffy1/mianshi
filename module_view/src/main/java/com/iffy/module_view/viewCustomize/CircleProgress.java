package com.iffy.module_view.viewCustomize;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.iffy.module_view.R;


/**
 * Created by peter on 2018/9/12.
 */

public class CircleProgress extends View {
    private final static String TAG = "CircleProgress";

    private float mCircleRadius;
    private float mStrokeWidth;
    private int mBackgroundColor;
    private int mForegroundColor;
    private int mTitleTextColor;

    private String mTitleText = "----";
    private int mValue = 0;
    private String mTempValueText = "0";
    private String mUnitText = "Kcal";

    private Paint mCoordinatePaint = new Paint();
    private Paint mBackgroundPaint = new Paint();
    private Paint mForegroundPaint = new Paint();
    private Paint mEndOutCirclePaint = new Paint();
    private Paint mEndInnerCirclePaint = new Paint();
    private Paint mTitleTextPaint = new Paint();
    private Paint mValueTextPaint = new Paint();
    private Paint mUnitTextPaint = new Paint();

    private int mPercent = 0;
    private int mTempPercent = 0;
    private int mViewWidth;
    private int mViewHeight;
    private int mCenterX;
    private int mCenterY;

    private int mPaddingStart = 0;
    private int mPaddingEnd = 0;
    private int mPaddingTop = 0;
    private int mPaddingBottom = 0;

    private boolean mIsFirstTime = true;

    private RectF mFrameRectF = new RectF();
    private RectF mFrameRectFB = new RectF();
    private Rect mTextRect = new Rect();

    public CircleProgress(Context context) {
        this(context, null);
        System.out.println("第一个构造函数");
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        System.out.println("第二个构造函数");
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println("第三个构造函数");
        //初始化属性值
        initTypedArray(context, attrs);
        //初始化画笔
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initTypedArray(Context context, AttributeSet attrs) {
        //获取自定义属性的根
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleProgress);
        try {
            //获取自定义属性<attr name="stroke_width" format="dimension"/>
            //获取不到使用默认值
            mStrokeWidth = ta.getDimension(R.styleable.CircleProgress_stroke_width, getResources().getDimension(R.dimen.circle_progress_default_stroke_width));

            //获取attr.xml中的属性 <attr name="background_color" format="color"/>
            //具体数在activity_customize_view.xml的 app:background_color="#445566"中 获取不到的话使用默认值
            //getColor(@StyleableRes int index, @ColorInt int defValue) 前面参数为app:background_color="#445566"中的颜色 后面为默认值
            mBackgroundColor = ta.getColor(R.styleable.CircleProgress_background_color, getResources().getColor(R.color.white, null));
            mForegroundColor = ta.getColor(R.styleable.CircleProgress_foreground_color, getResources().getColor(R.color.colorPrimary, null));

            //获取attr.xml中的属性 <attr name="text_color" format="color"/>
            //具体数在activity_customize_view.xml的 app:text_color="#FFEB3B"中 获取不到的话使用默认值
            mTitleTextColor = ta.getColor(R.styleable.CircleProgress_text_color, getResources().getColor(R.color.colorPrimary, null));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //ta不回收会造成内存泄漏
            if (ta != null) {
                ta.recycle();
            }
        }
    }

    //初始化画笔
    private void initPaint() {
        // FromArrayJava paint 十字线
        mCoordinatePaint.setColor(mForegroundColor);
        //抗锯齿
        mCoordinatePaint.setAntiAlias(true);
        //设置填充样式
        //STROKE空心 FILL填充
        mCoordinatePaint.setStyle(Paint.Style.STROKE);
        mCoordinatePaint.setStrokeWidth(1);

        // foreground paint 会动的进度圈
        mForegroundPaint.setColor(mForegroundColor);
        mForegroundPaint.setStrokeWidth(mStrokeWidth);
        //空心
        mForegroundPaint.setStyle(Paint.Style.STROKE);
        //设置画笔但始末端样
        mForegroundPaint.setStrokeCap(Paint.Cap.ROUND);
        mForegroundPaint.setAntiAlias(true);

        // background paint底层圈
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setStrokeWidth(mStrokeWidth);
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        mBackgroundPaint.setAntiAlias(true);

        // end out circle paint 结尾的装饰小圈
        mEndOutCirclePaint.setColor(getResources().getColor(R.color.white, null));
        mEndOutCirclePaint.setStyle(Paint.Style.FILL);
        mEndOutCirclePaint.setAntiAlias(true);

        // end inner circle paint 结尾装饰圈上的小点点
        mEndInnerCirclePaint.setColor(getResources().getColor(R.color.colorAccent, null));
        mEndInnerCirclePaint.setStyle(Paint.Style.FILL);
        mEndInnerCirclePaint.setAntiAlias(true);

        // title text paint 题目字
        mTitleTextPaint.setColor(mTitleTextColor);
        mTitleTextPaint.setTextSize(getResources().getDimension(R.dimen.circle_progress_default_title_size));
        mTitleTextPaint.setAntiAlias(true);
        //居中
        // drawText x默认是“hello world”字符串的左边在屏幕的位置，如果设置了paint.setTextAlign(Paint.Align.CENTER);那就会变成字符串的中心
        mTitleTextPaint.setTextAlign(Paint.Align.CENTER);

        // value text paint 数值
        mValueTextPaint.setColor(mForegroundColor);
        mValueTextPaint.setTextSize(getResources().getDimension(R.dimen.circle_progress_default_value_size));
        mValueTextPaint.setAntiAlias(true);
        mValueTextPaint.setTextAlign(Paint.Align.CENTER);

        // unit text paint 单位
        mUnitTextPaint.setColor(mForegroundColor);
        mUnitTextPaint.setTextSize(getResources().getDimension(R.dimen.circle_progress_default_unit_size));
        mUnitTextPaint.setAntiAlias(true);
        mUnitTextPaint.setTextAlign(Paint.Align.CENTER);
    }


    //测量View和它的内容来确定measured width和measured height，这个方法被measure(int, int)调用，而且会被子类重写来准确高效的测量他们的内容。
//ViewGroup设置测量规则和消费测量规则
//    View消费测量规则
//    View大小确定，使用规则EXACTLY
//    View大小不确定，使用规则AT_MOST
//    View大小不该限制，使用规则UNSPECIFIED
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            //当重写onMeasure这个方法的时候,必须调用setMeasuredDimension(int, int)来存储这个View的measured width和measured height，
            //            // 否则measure(int, int)会触发 IllegalStateException。
            setMeasuredDimension((int) getResources().getDimension(R.dimen.circle_progress_default_width),
                    (int) getResources().getDimension(R.dimen.circle_progress_default_height));
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) getResources().getDimension(R.dimen.circle_progress_default_width), heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, (int) getResources().getDimension(R.dimen.circle_progress_default_height));
        }

    }

    //在onLayout方法中对一些数据进行处理
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
        //getPaddingStart()获取xml中定义android:paddingStart="40dp"
        //这里在padding的基础上 添加了mStrokeWidth的1/4 笔刷宽度的1/4
        mPaddingStart = getPaddingStart() + (int) (mStrokeWidth / 4);
        mPaddingEnd = getPaddingEnd() + (int) (mStrokeWidth / 4);
        mPaddingTop = getPaddingTop() + (int) (mStrokeWidth / 4);
        mPaddingBottom = getPaddingBottom() + (int) (mStrokeWidth / 4);

        //实际宽度 总宽度（getWidth()）减去左右定制后的padding
        mViewWidth = getWidth() - mPaddingStart - mPaddingEnd;
        mViewHeight = getHeight() - mPaddingTop - mPaddingBottom;

        //找到绘制图案中心点 不是view的中心点
        //为什么不用getWidth()/2
        //左右padding不对称时候 getWidth()/2 还是在view的中心，但不是绘制图案的中心，所以不能用getWidth()/2
        System.out.println("getWidth()/2 is:" + getWidth() / 2);
        mCenterX = mPaddingStart + mViewWidth / 2;
        System.out.println("mCenterX is:" + mCenterX);
        mCenterY = mPaddingTop + mViewHeight / 2;

        //Radius半径 选择view高宽较小的值 作为半径 防止画到外面
        mCircleRadius = mViewWidth < mViewHeight ? mViewWidth / 2 : mViewHeight / 2;

        //RectF和Rect都表示的是一个矩形的区域
        //RectF() :构造一个无参的矩形；
        //RectF（float left,float top,float right,float bottom）
        //构造一个指定了4个参数的矩形 ，这些参数都是坐标，即矩形的宽width = right - left ，高height = bottom - top；
        //mFrameRectF是用来画弧线的
        mFrameRectF.set(mCenterX - mCircleRadius,
                mCenterY - mCircleRadius,
                mCenterX + mCircleRadius,
                mCenterY + mCircleRadius);

        //mFrameRectFB是用来画弧线的
        mFrameRectFB.set(mCenterX - mCircleRadius + mStrokeWidth / 2,
                mCenterY - mCircleRadius + mStrokeWidth / 2,
                mCenterX + mCircleRadius - mStrokeWidth / 2,
                mCenterY + mCircleRadius - mStrokeWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw");
        super.onDraw(canvas);
        if (mIsFirstTime) {
            startCircleProgressAnim();
            startValueAnim();
            mIsFirstTime = false;
        }

        // background circle 背景圈
        drawBackgroundCircle(canvas);

        // foreground circle 前景弧线和终点圈
        drawForegroundCircle(canvas);

        // content 字内容
        drawContent(canvas);

        // coordinate 十字坐标
        drawCoordinate(canvas);
    }

    //属性动画
    public void startCircleProgressAnim() {
        ValueAnimator anim = ValueAnimator.ofInt(0, mPercent);
        anim.setDuration(1000 * mPercent / 100);
        //LinearInterpolator线性插值器
        //anim.setInterpolator(new LinearInterpolator());
        //加速插值器
        anim.setInterpolator(new AccelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //主线程更新UI
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("当前属性动画onAnimationUpdate的线程是：" + Thread.currentThread().getName());
                mTempPercent = (int) animation.getAnimatedValue();
                //让我们的View进行刷新重新绘制的
                invalidate();
            }
        });
        anim.start();
    }

    //属性动画
    public void startValueAnim() {
        ValueAnimator anim = ValueAnimator.ofInt(0, mValue);
        anim.setDuration(1000 * mPercent / 100);
        //LinearInterpolator线性插值器
        anim.setInterpolator(new LinearInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTempValueText = Integer.toString((Integer) animation.getAnimatedValue());
                //让我们的View进行刷新重新绘制的
                invalidate();
            }
        });
        anim.start();
    }

    //画背景圈
    private void drawBackgroundCircle(Canvas canvas) {
        System.out.println("半径mCircleRadius - mStrokeWidth / 2:" + (mCircleRadius - mStrokeWidth / 2));
        System.out.println("半径mCircleRadius:" + mCircleRadius);
        //减去笔刷宽度的一半 不然有可能超出view
        canvas.drawCircle(mCenterX, mCenterY, mCircleRadius - mStrokeWidth / 2, mBackgroundPaint);
    }

    //画各种圈
    private void drawForegroundCircle(Canvas canvas) {
        // circle画圆弧
        // 第一种是通过 RectF 来确定弧线的位置和大小
        canvas.drawArc(mFrameRectFB, 270, (float) (3.6 * mTempPercent), false, mForegroundPaint);

        // circle画圆弧
        // 第二种是直接设置（第二种需要 API 21）
        //startAngle: 从 x 轴到弧线的起始点沿顺时针方向度量的角（以度为单位）。
        //sweepAngle: 从 startAngle 参数到弧线的结束点沿顺时针方向度量的角（以度为单位）
        //useCenter: 这个呢代表的是是否画出半径的意思（应该是叫半径吧，看图）
        //这个方法用得不对 相当于把坐标存入了mFrameRectF
//        canvas.drawArc(mFrameRectF.left + mStrokeWidth / 2, mFrameRectF.top + mStrokeWidth / 2, mFrameRectF.right - mStrokeWidth / 2,
//                mFrameRectF.bottom - mStrokeWidth / 2, 270, (float) (3.6 * mTempPercent), false, mForegroundPaint);

        // end circle
        if (mTempPercent != 0) {
            // end out circle终点小圆圈根据 正弦 余弦公式 角度和半径算出 距离圆心的x和y坐标
            float x = (float) (mCenterX + (mCircleRadius - mStrokeWidth / 2) * Math.cos((3.6 * mTempPercent - 90) * Math.PI / 180));
            float y = (float) (mCenterY + (mCircleRadius - mStrokeWidth / 2) * Math.sin((3.6 * mTempPercent - 90) * Math.PI / 180));
            canvas.drawCircle(x, y, mStrokeWidth * 3 / 4, mEndOutCirclePaint);

            // end inner circle终点小圆圈中的小圆圈
            canvas.drawCircle(x, y, mStrokeWidth / 4, mEndInnerCirclePaint);
        }
    }
    //画字 重点是保证字居中
    private void drawContent(Canvas canvas) {
        // title
        // Paint: .measureText() VS .getTextBounds()
        //两个方法可以用来测量文字宽高信息的.
        // getTextBounds() 还可以获得高度信息，因为其使用一个 Rect 对象对宽高信息进行存储；
        // measureText() 则只是返回宽度信息
        mTitleTextPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTextRect);

        //x默认是“hello world”字符串的左边在屏幕的位置
        //如果设置了paint.setTextAlign(Paint.Align.CENTER);那就会变成字符串的中心
        // 本例中已经设置了Paint.Align.CENTER 见mTitleTextPaint初始化部分
        canvas.drawText(mTitleText, mCenterX, mCenterY / 2 + mTextRect.height() / 2, mTitleTextPaint);

        // value
        mValueTextPaint.getTextBounds(mTempValueText, 0, mTempValueText.length(), mTextRect);
        canvas.drawText(mTempValueText, mCenterX, mCenterY + mTextRect.height() / 2, mValueTextPaint);

        // unit
        mUnitTextPaint.getTextBounds(mUnitText, 0, mUnitText.length(), mTextRect);
        canvas.drawText(mUnitText, mCenterX, mCenterY * 4 / 3, mUnitTextPaint);
    }
    //画十字坐标线
    private void drawCoordinate(Canvas canvas) {
        canvas.drawLine(0, mCenterY, 2 * mCenterX, mCenterY, mCoordinatePaint);
        canvas.drawLine(mCenterX, 0, mCenterX, 2 * mCenterY, mCoordinatePaint);
    }

    public int getPercentage() {
        return mPercent;
    }

    public void setPercentage(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("The value should be in 1 ~ 100");
        }

        mPercent = percent;
        invalidate();
    }

    public String getTitleText() {
        return mTitleText;
    }

    public void setTitleText(String titleText) {
        mTitleText = titleText;
        invalidate();
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("The value should be greater than 0!");
        }
        mValue = value;
        invalidate();
    }
}
