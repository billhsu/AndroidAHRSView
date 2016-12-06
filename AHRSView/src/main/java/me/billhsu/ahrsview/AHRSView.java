package me.billhsu.ahrsview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.SoftReference;

/**
 * AHRS view for Android.
 * https://github.com/billhsu/AndroidAHRSView
 * <p>
 * Author: Shipeng Xu
 */
public class AHRSView extends View {
    private float roll = 190.0f, pitch = 0.0f, yaw = 0.0f;

    private Paint horizontalLine;
    private Paint ahrsLine;
    private Paint groundPaint;
    private Paint skyPaint;
    private Paint textPaint;
    private SoftReference<Bitmap> ahrsBitmapRef = new SoftReference<>(null);
    private SoftReference<Bitmap> ahrsFlippedBitmapRef = new SoftReference<>(null);

    private int width;
    private int height;

    public AHRSView(Context context) {
        super(context);
    }

    public AHRSView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAHRSView();
    }

    public AHRSView(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        initAHRSView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = width / 2;
        float centerY = height / 2;
        float horizontalWidth = width * 0.05f;
        Bitmap ahrsBitmap = getAHRSBitmap();
        canvas.save();
        canvas.rotate(roll, centerX, centerY);
        float pitch180 = (pitch % 360.0f) - 180.0f;
        int yCenter = ahrsBitmap.getHeight() / 2;
        float yInterval = ahrsBitmap.getHeight() / 72.0f; // 72 = 4 * 18
        if (pitch180 > 0) {
            yCenter -= (180 - pitch180) / 10.0f * yInterval;
        } else {
            yCenter += (180 + pitch180) / 10.0f * yInterval;
        }
        canvas.drawBitmap(ahrsBitmap, width / 2 - ahrsBitmap.getWidth() / 2, height / 2 - yCenter, null);

        canvas.restore();
        canvas.drawLine(centerX, centerY, centerX - horizontalWidth, centerY + horizontalWidth, horizontalLine);
        canvas.drawLine(centerX, centerY, centerX + horizontalWidth, centerY + horizontalWidth, horizontalLine);
        canvas.drawLine(centerX - horizontalWidth, centerY + horizontalWidth, centerX - horizontalWidth * 2, centerY, horizontalLine);
        canvas.drawLine(centerX + horizontalWidth, centerY + horizontalWidth, centerX + horizontalWidth * 2, centerY, horizontalLine);
        canvas.drawLine(centerX - horizontalWidth * 2, centerY, centerX - horizontalWidth * 4, centerY, horizontalLine);
        canvas.drawLine(centerX + horizontalWidth * 2, centerY, centerX + horizontalWidth * 4, centerY, horizontalLine);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        updateAHRSBitmap();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setRoll(float roll) {
        this.roll = roll % 360;
        invalidate();
    }

    public void setPitch(float pitch) {
        this.pitch = pitch % 360;
        invalidate();
    }

    public void setYaw(float yaw) {
        this.yaw = yaw % 360;
        invalidate();
    }

    private void updateAHRSBitmap() {
        Bitmap ahrsBitmap = ahrsBitmapRef.get();
        if (ahrsBitmap != null) {
            ahrsBitmap.recycle();
        }
        ahrsBitmapRef = new SoftReference<>(drawAHRSBitmap(false));

        Bitmap ahrsFlippedBitmap = ahrsFlippedBitmapRef.get();
        if (ahrsFlippedBitmap != null) {
            ahrsFlippedBitmap.recycle();
        }
        ahrsFlippedBitmapRef = new SoftReference<>(drawAHRSBitmap(true));
    }

    private Bitmap getAHRSBitmap() {
        if (Math.abs(roll % 360 - 180) <= 90) {
            Bitmap ahrsFlippedBitmap = ahrsFlippedBitmapRef.get();
            if (ahrsFlippedBitmap == null) {
                ahrsFlippedBitmap = drawAHRSBitmap(true);
                ahrsFlippedBitmapRef = new SoftReference<>(ahrsFlippedBitmap);
            }
            return ahrsFlippedBitmap;
        } else {
            Bitmap ahrsBitmap = ahrsBitmapRef.get();
            if (ahrsBitmap == null) {
                ahrsBitmap = drawAHRSBitmap(false);
                ahrsBitmapRef = new SoftReference<>(ahrsBitmap);
            }
            return ahrsBitmap;
        }
    }

    private Bitmap drawAHRSBitmap(boolean flipped) {
        int size = Math.max(width, height) * 2;
        Bitmap bitmap = Bitmap.createBitmap(size, size * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        float shortLineLength = size * 0.05f;
        float longLineLength = size * 0.1f;
        float pitchInterval = size * 2.0f / 72.0f; // 72 = 18 * 4
        canvas.drawRect(0, 0, size, size / 2, groundPaint);
        canvas.drawRect(0, size / 2, size, size, skyPaint);
        canvas.drawRect(0, size, size, size + size / 2, groundPaint);
        canvas.drawRect(0, size / 2 + size, size, size + size, skyPaint);
        for (int i = -1; i >= -17; --i) {
            float length = Math.abs(i) % 2 == 1 ? shortLineLength : longLineLength;
            int yIndex = -i;
            drawAHRSLine(canvas, length, yIndex * pitchInterval, Integer.toString(i * 10), flipped);
        }
        canvas.drawLine(0, pitchInterval * 18, size, pitchInterval * 18, ahrsLine);

        for (int i = 17; i >= 1; --i) {
            float length = Math.abs(i) % 2 == 1 ? shortLineLength : longLineLength;
            int yIndex = 36 - i;
            drawAHRSLine(canvas, length, yIndex * pitchInterval, Integer.toString(i * 10), flipped);
        }
        canvas.drawLine(0, pitchInterval * 36, size, pitchInterval * 36, ahrsLine);

        for (int i = -1; i >= -17; --i) {
            float length = Math.abs(i) % 2 == 1 ? shortLineLength : longLineLength;
            int yIndex = 36 - i;
            drawAHRSLine(canvas, length, yIndex * pitchInterval, Integer.toString(i * 10), flipped);
        }
        canvas.drawLine(0, pitchInterval * 54, size, pitchInterval * 54, ahrsLine);

        for (int i = 17; i >= 1; --i) {
            float length = Math.abs(i) % 2 == 1 ? shortLineLength : longLineLength;
            int yIndex = 72 - i;
            drawAHRSLine(canvas, length, yIndex * pitchInterval, Integer.toString(i * 10), flipped);
        }
        return bitmap;
    }

    private void drawAHRSLine(Canvas canvas, float length, float positionY, String pitchText, boolean flipped) {
        float textLength = textPaint.measureText(pitchText);
        int size = canvas.getWidth();
        if (flipped) {
            canvas.save();
            canvas.rotate(180, size / 2 - length - textLength / 2 - 5, positionY);
        }
        canvas.drawText(pitchText, size / 2 - length - textLength - 5, positionY + 10, textPaint);
        if (flipped) {
            canvas.restore();
        }
        if (flipped) {
            canvas.save();
            canvas.rotate(180, size / 2 + length + 5 + textLength / 2, positionY);
        }
        canvas.drawText(pitchText, size / 2 + length + 5, positionY + 10, textPaint);
        if (flipped) {
            canvas.restore();
        }
        canvas.drawLine(size / 2 - length, positionY, size / 2 + length, positionY, ahrsLine);
    }

    private int measure(int measureSpec) {
        int result = 0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.UNSPECIFIED) {
            result = 200;
        } else {
            result = specSize;
        }
        return result;
    }

    protected void initAHRSView() {
        horizontalLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        horizontalLine.setStrokeWidth(5);
        horizontalLine.setColor(Color.parseColor("#E0D51B"));

        ahrsLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        ahrsLine.setStrokeWidth(6);
        ahrsLine.setColor(Color.WHITE);

        skyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        skyPaint.setColor(Color.parseColor("#0166D8"));
        skyPaint.setStyle(Style.FILL);

        groundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        groundPaint.setColor(Color.parseColor("#AA3E17"));
        groundPaint.setStyle(Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(20);
    }

}
