package ninja.corex.viewbind.googleProgressbar;
import android.animation.ObjectAnimator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import javax.security.auth.callback.Callback;

public class NexusRotationCrossDrawable extends Drawable implements Drawable.Callback {
    private static int ANIMATION_DURATION = 150;
    private static int ANIMATION_START_DELAY = 300;
    private static final android.view.animation.Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private int mCenter;
    private Point[] mArrowPoints;
    private Path mPath;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private int mRotationAngle;
    public NexusRotationCrossDrawable(int[] colors) {
        mArrowPoints = new Point[5];
        mPath = new Path();
        mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint1.setColor(colors[0]);
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(colors[1]);
        mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint3.setColor(colors[2]);
        mPaint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint4.setColor(colors[3]);
        initObjectAnimator();
    }
    private void initObjectAnimator() {
        final ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "rotationAngle", 0, 180);
        objectAnimator.setInterpolator(LINEAR_INTERPOLATOR);
        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mRotationAngle == 180) {
                    objectAnimator.setIntValues(180, 360);
                    objectAnimator.setStartDelay(ANIMATION_START_DELAY * 2);
                } else {
                    objectAnimator.setIntValues(0, 180);
                    objectAnimator.setStartDelay(ANIMATION_START_DELAY);
                    mRotationAngle = 0;
                }
                objectAnimator.start();
            }
        });
        objectAnimator.start();
    }
    @Override
    public void draw(Canvas canvas) {
        drawArrows(canvas);
    }
    private void drawArrows(Canvas canvas) {
        canvas.rotate(mRotationAngle, mCenter, mCenter);
        mPath.reset();
        mPath.moveTo(mArrowPoints[0].x, mArrowPoints[0].y);
        for (int i = 1; i < mArrowPoints.length; i++) {
            mPath.lineTo(mArrowPoints[i].x, mArrowPoints[i].y);
        }
        mPath.lineTo(mArrowPoints[0].x, mArrowPoints[0].y);
        canvas.save();
        canvas.drawPath(mPath, mPaint1);
        canvas.restore();
        canvas.save();
        canvas.rotate(90, mCenter, mCenter);
        canvas.drawPath(mPath, mPaint2);
        canvas.restore();
        canvas.save();
        canvas.rotate(180, mCenter, mCenter);
        canvas.drawPath(mPath, mPaint3);
        canvas.restore();
        canvas.save();
        canvas.rotate(270, mCenter, mCenter);
        canvas.drawPath(mPath, mPaint4);
        canvas.restore();
    }
    @Override
    public void invalidateDrawable(android.graphics.drawable.Drawable who) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }
    @Override
    public void scheduleDrawable(android.graphics.drawable.Drawable who, Runnable what, long when) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }
    @Override
    public void unscheduleDrawable(android.graphics.drawable.Drawable who, Runnable what) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }
    @Override
    public void setAlpha(int alpha) {
        mPaint1.setAlpha(alpha);
        mPaint2.setAlpha(alpha);
        mPaint3.setAlpha(alpha);
        mPaint4.setAlpha(alpha);
    }
    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint1.setColorFilter(cf);
        mPaint2.setColorFilter(cf);
        mPaint3.setColorFilter(cf);
        mPaint4.setColorFilter(cf);
    }
    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        measureDrawable(bounds);
    }
    private void measureDrawable(Rect bounds) {
        mCenter = bounds.centerX();
        int arrowMargin = bounds.width() / 50;
        int arrowWidth = bounds.width() / 15;
        int padding = mCenter - (int) (mCenter /  Math.sqrt(2));
        mArrowPoints[0] = new Point(mCenter - arrowMargin, mCenter - arrowMargin);
        mArrowPoints[1] = new Point(mArrowPoints[0].x, mArrowPoints[0].y - arrowWidth);
        mArrowPoints[2] = new Point(padding + arrowWidth, padding);
        mArrowPoints[3] = new Point(padding, padding + arrowWidth);
        mArrowPoints[4] = new Point(mArrowPoints[0].x - arrowWidth, mArrowPoints[0].y);
    }
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
    void setRotationAngle(int angle) {
        mRotationAngle = angle;
    }
    int getRotationAngle() {
        return mRotationAngle;
    }
    public static class Builder {
        private int[] mColors;
        public Builder(Context context) {
            initDefaults(context);
        }
        
        private void initDefaults(Context context) {
            mColors = new int[]{0xFFC93437, 0xFF375BF1, 0xFFF7D23E, 0xFF34A350}; //Red, blue, yellow, green
        }
        
        public Builder colors(int[] colors) {
            if (colors == null || colors.length != 4) {
                throw new IllegalArgumentException("Your color array must contains 4 values");
            }
            mColors = colors;
            return this;
        }
        public android.graphics.drawable.Drawable build() {
            return new NexusRotationCrossDrawable(mColors);
        }
    }
}