package ninja.corex.viewbind.materialbind;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.translation.TranslationCapability;

import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Map;
import java.util.List;
import ninja.corex.exceptionbind.ColorNotFoundException;

public class InputLayoutCore extends TextInputLayout {
	protected MaterialShapeDrawable shapeDrawable;

	public InputLayoutCore(Context context) {
		super(context);
	}

	public InputLayoutCore(Context context, AttributeSet set) {
		super(context, set);
	}

	public InputLayoutCore(Context context, AttributeSet set, int def) {
		super(context, set, def);
	}

	public void setShapeCut(int size, ColorStateList list) {
		shapeDrawable = new MaterialShapeDrawable(
				ShapeAppearanceModel.builder().setAllCorners(CornerFamily.CUT, size).build());
		shapeDrawable.setFillColor(list);
		setBackground(shapeDrawable);
	}

	public void setShapeRounded(int size, ColorStateList list) {
		shapeDrawable = new MaterialShapeDrawable(
				ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, size).build());
		shapeDrawable.setFillColor(list);
		setBackground(shapeDrawable);
	}

	public void setTextColorString(String string) {
		if (string != null) {
			setHintTextColor(ColorStateList.valueOf(Color.parseColor(string)));
		} else {
			throw new ColorNotFoundException("Are Your Add Color Hex???");
		}
	}

}