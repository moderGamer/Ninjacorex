package ninja.corex.viewbind.textviewcore;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.ColorRes;
import com.google.android.material.textfield.TextInputEditText;
import ninja.corex.exceptionbind.ColorNotFoundException;

public class EditTextCore extends TextInputEditText {
	public EditTextCore(Context context) {
		super(context);
	}

	public EditTextCore(Context context, AttributeSet set) {
		super(context, set);
	}

	public EditTextCore(Context context, AttributeSet set, int def) {
		super(context, set, def);
	}

	public void setAutoScroll() {
		setEllipsize(TextUtils.TruncateAt.MARQUEE);
		setMarqueeRepeatLimit(-1);
		setSingleLine(true);
		setSelected(true);
	}

	public void setHintColorString(@ColorRes String color) {
		if (color != null) {
			setHintTextColor(ColorStateList.valueOf(Color.parseColor(color)));
		} else {
			throw new ColorNotFoundException("You add color String??");
		}
	}

	public void setHighlightColorString(@ColorRes String color) {
		if (color != null) {
			setHighlightColor(Color.parseColor(color));
		} else {
			throw new ColorNotFoundException("You add Color String???");
		}
	}

	public void setMaxSizeText(int size) {
		if (size > 1) {
			setFilters(new InputFilter[] { new InputFilter.LengthFilter(size) });
		} else {
			throw new NumberFormatException("Can you add Number 0 > 100??");
		}
	}

	public void setBackGroundColorsString(String color) {
		if (color != null) {
			getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
		} else {
			throw new ColorNotFoundException("You Add Color?");
		}
	}

	public void setBackGroundColors(int color) {
		if (color > 1) {
			getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
		} else {
			throw new ColorNotFoundException("You Add Color?");
		}
	}
	



}