package ninja.corex.animation;

import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;

public class AnimationUtils {
	private static AlphaAnimation alphaAnimation;
	private static Handler handler;
	private static ScaleAnimation scaleAnimation;
	

	public static void FadeIn(View view) {
		alphaAnimation = new AlphaAnimation(1, 0);
		alphaAnimation.setDuration(1000);
		alphaAnimation.setFillAfter(true);
		alphaAnimation.setInterpolator(new BounceInterpolator());
		handler = new Handler();
		handler.postDelayed(() -> {
			view.startAnimation(alphaAnimation);
		}, 100);
	}

	public static void FadeCustom(View view, float initem, float outitem, int Timer) {
		alphaAnimation = new AlphaAnimation(initem, outitem);
		alphaAnimation.setDuration(Timer);
		alphaAnimation.setFillAfter(true);
		handler = new Handler();
		handler.postDelayed(() -> {
			view.startAnimation(alphaAnimation);
		}, 100);
	}

	public static void FadeOut(View view) {
		alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1000);
		alphaAnimation.setFillAfter(true);
		handler = new Handler();
		handler.postDelayed(() -> {
			view.startAnimation(alphaAnimation);
		}, 100);
	}

	public static void Sacla(View view, float fromDeltax, float fromDeltay, float novPartX, float notPartY, long Time) {
		scaleAnimation = new ScaleAnimation(fromDeltax, fromDeltay, notPartY, novPartX);
		scaleAnimation.setFillAfter(true);
		scaleAnimation.setDuration(Time);
		handler.postDelayed(() -> {
			view.startAnimation(scaleAnimation);
		}, 100);
	}

}