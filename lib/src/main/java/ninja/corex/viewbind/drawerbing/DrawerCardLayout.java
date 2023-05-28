package ninja.corex.viewbind.drawerbing;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import ninja.corex.exceptionbind.ColorNotFoundException;

public class DrawerCardLayout extends MaterialCardView {
	private ShapeAppearanceModel.Builder model;
	private int Cut = CornerFamily.CUT;
	private int Normal = CornerFamily.ROUNDED;

	public DrawerCardLayout(Context c) {
		super(c);
	}

	public DrawerCardLayout(Context c, AttributeSet set) {
		super(c, set);
		setShapeStyleCut(10f);
	}

	public DrawerCardLayout(Context c, AttributeSet set, int def) {
		super(c, set, def);
		setShapeStyleCut(10f);
	}

	@Override
	protected void onMeasure(int w, int h) {
		super.onMeasure(w, h);
	}

	public void setShapeStyleCut(float size) {
		model = new ShapeAppearanceModel.Builder();
		model.setAllCorners(Cut, size);
		setShapeAppearanceModel(model.build());
	}

	public void setShapeStyleRounder(float size) {
		model = new ShapeAppearanceModel.Builder();
		model.setAllCorners(Cut, size);
		setShapeAppearanceModel(model.build());
	}

	public GradientDrawable setColorSesane(int color, int Stoker, int StokerColor) {
		GradientDrawable gb = new GradientDrawable();
		if (color > 0 || Stoker > 0 || StokerColor > 0) {
			gb.setColor(color);
			gb.setStroke(Stoker, StokerColor);
		} else {
			throw new ColorNotFoundException("Error Color Not Found");
		}
		return gb;
	}

}