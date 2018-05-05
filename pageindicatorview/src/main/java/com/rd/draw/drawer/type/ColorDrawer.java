package com.rd.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.rd.animation.data.Value;
import com.rd.animation.data.type.ColorAnimationValue;
import com.rd.draw.data.Indicator;

public class ColorDrawer extends BaseDrawer {

    public ColorDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        super(paint, indicator);
    }

    public void draw(@NonNull Canvas canvas,
                     @NonNull Value value,
                     int position,
                     int coordinateX,
                     int coordinateY) {

        if (!(value instanceof ColorAnimationValue)) {
            return;
        }

        ColorAnimationValue v = (ColorAnimationValue) value;
        float radius = indicator.getRadius();
        int color = indicator.getSelectedColor();
        int foregroundColor = indicator.getSelectedForegroundColor();

        int selectedPosition = indicator.getSelectedPosition();
        int selectingPosition = indicator.getSelectingPosition();
        int lastSelectedPosition = indicator.getLastSelectedPosition();

        if (indicator.isInteractiveAnimation()) {
            if (position == selectingPosition) {
                color = v.getColor();
                foregroundColor = v.getForegroundColor();

            } else if (position == selectedPosition) {
                color = v.getColorReverse();
                foregroundColor = v.getForegroundColorReverse();
            }

        } else {
            if (position == selectedPosition) {
                color = v.getColor();
                foregroundColor = v.getForegroundColor();

            } else if (position == lastSelectedPosition) {
                color = v.getColorReverse();
                foregroundColor = v.getForegroundColorReverse();
            }
        }

        paint.setColor(color);
        canvas.drawCircle(coordinateX, coordinateY, radius, paint);
        if (indicator.isHasForeground()) {
            paint.setColor(foregroundColor);
            canvas.drawCircle(coordinateX, coordinateY, radius - indicator.getForegroundPadding(), paint);
        }
    }
}
