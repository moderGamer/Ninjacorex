package ninja.corex.viewbind.textviewcore;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.material.textview.MaterialTextView;
import ninja.corex.exceptionbind.ColorNotFoundException;

public class TextViewCore extends MaterialTextView {
	public TextViewCore(Context context) {
		super(context);
	}

	public TextViewCore(Context context, AttributeSet set) {
		super(context, set);
	}

	public TextViewCore(Context context, AttributeSet set, int def) {
		super(context, set, def);
	}

	public void setAutoScroll() {
		setEllipsize(TextUtils.TruncateAt.MARQUEE);
		setMarqueeRepeatLimit(-1);
		setSingleLine(true);
		setSelected(true);
	}
	public void setTextColorString(String colors){
		if(colors != null){
			setTextColor(ColorStateList.valueOf(Color.parseColor(colors)));
		}else{
			throw new ColorNotFoundException("are You Add color Hex??");
		}
	}
}