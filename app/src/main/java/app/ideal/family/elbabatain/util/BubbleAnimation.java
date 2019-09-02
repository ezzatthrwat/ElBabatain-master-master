package app.ideal.family.elbabatain.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import app.ideal.family.elbabatain.R;

public class BubbleAnimation {

    public static void DoAnimation(View view , Context context){
        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator_inBubbleAnimation interpolator = new MyBounceInterpolator_inBubbleAnimation(0.2 , 20);
        myAnim.setInterpolator(interpolator);
        view.startAnimation(myAnim);
    }
}
