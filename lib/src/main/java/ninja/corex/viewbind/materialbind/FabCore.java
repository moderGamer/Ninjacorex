package ninja.corex.viewbind.materialbind;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

public class FabCore extends FloatingActionButton {
	public static String TAG = "Log";

	public FabCore(Context context) {
		super(context);
	}

	public FabCore(Context context, AttributeSet set) {
		super(context, set);
	}

	public FabCore(Context context, AttributeSet set, int def) {
		super(context, set, def);
	}

	public void setShapeCut(float shapesize) {
		ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder();
		builder.setAllCorners(CornerFamily.CUT,shapesize);
		setShapeAppearanceModel(builder.build());
		
		
	}

	public void setShapeRounde(float shapesize) {
		ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder();
		builder.setAllCorners(CornerFamily.ROUNDED,shapesize);
		setShapeAppearanceModel(builder.build());
		
	}

	public void setIcon(int icon) {
		try {
			setImageResource(icon);
		} catch (Throwable throwable) {
			Log.e(TAG, throwable.getMessage());
		}
	}

	public void setIconColorFilter(int image, PorterDuff.Mode porterDuff) {
		try {
			setColorFilter(image, porterDuff);
		} catch (RuntimeException r) {
			Log.e(TAG, r.getMessage());
		}
	}

}