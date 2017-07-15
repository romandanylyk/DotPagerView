package com.rd.pageindicatorview.data;

import com.rd.animation.type.AnimationType;
import com.rd.draw.IndicatorShape;
import com.rd.draw.data.Orientation;
import com.rd.draw.data.RtlMode;

public class CustomizationConverter {

    public static AnimationType getAnimationType(int position) {
        switch (position) {
            case 0:
                return AnimationType.NONE;

            case 1:
                return AnimationType.COLOR;

            case 2:
                return AnimationType.SCALE;

            case 3:
                return AnimationType.WORM;

            case 4:
                return AnimationType.SLIDE;

            case 5:
                return AnimationType.FILL;

            case 6:
                return AnimationType.THIN_WORM;

            case 7:
                return AnimationType.DROP;

            case 8:
                return AnimationType.SWAP;

            default:
                return AnimationType.NONE;
        }
    }

    public static Orientation getOrientation(int position) {
        switch (position) {
            case 0:
                return Orientation.HORIZONTAL;

            case 1:
                return Orientation.VERTICAL;

            default:
                return Orientation.HORIZONTAL;
        }
    }

    public static RtlMode getRtlMode(int position) {
        switch (position) {
            case 0:
                return RtlMode.On;

            case 1:
                return RtlMode.Off;

            case 2:
                return RtlMode.Auto;

            default:
                return RtlMode.Off;
        }
    }

    public static IndicatorShape getShape(int position) {
        switch( position) {
            case 0:
                return IndicatorShape.CIRCLE;
            case 1:
                return IndicatorShape.RECTANGLE;
            default:
                return IndicatorShape.CIRCLE;
        }
    }
}
