package ninja.corex;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.appcompat.widget.SwitchCompat;

public class MyLinearLayout extends LinearLayout {
	private SwitchCompat aSwitchCompat;
	private OnChack onChack;

	public MyLinearLayout(Context context) {
		super(context);
		init();
	}

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		setOrientation(VERTICAL);
		setPadding(0, 0, 0, 0);
		setGravity(Gravity.CENTER);
		aSwitchCompat = new SwitchCompat(getContext());
		aSwitchCompat.setPadding(0, 0, 0, 0);
		aSwitchCompat.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
		//	aSwitchCompat.setThumbDrawable(gradientDrawable());
		aSwitchCompat.setOnCheckedChangeListener((button, is) -> {
			if (is) {

			} else {

			}

		});

		addView(aSwitchCompat);
	}

	public void onChack(OnChack onChack) {
		aSwitchCompat.setOnCheckedChangeListener((compatButton, isChack) -> {
			onChack.onChack(isChack);
		});
	}

	public void setChack(boolean is) {
		aSwitchCompat.setChecked(is);
	}

	public void ColorStart() {
		setBackground(gradientDrawable());
	}

	public void ColorEnd() {
		setBackgroundColor(Color.GREEN);
	}

	public interface OnChack {
		void onChack(boolean ch);

	}

	public GradientDrawable gradientDrawable() {
		GradientDrawable gradientDrawable2 = new GradientDrawable();
		gradientDrawable2.setColor(Color.BLACK);
		gradientDrawable2.setCornerRadius(20f);
		gradientDrawable2.setStroke(1, Color.RED);
		return gradientDrawable2;
	}

}