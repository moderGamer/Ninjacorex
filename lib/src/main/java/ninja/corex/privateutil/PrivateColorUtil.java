package ninja.corex.privateutil;

import android.graphics.Color;
import android.util.SparseIntArray;
import com.google.android.material.shape.CornerFamily;

public class PrivateColorUtil {
	public static final int ColorCardBackground = Color.parseColor("#FF281D1B");
	public static final int ColorStokerCard = Color.parseColor("#FFFFAF7A");
	public static final int ColorStokerColorCard = 0;
	public static final int DialogBackground = 0;
	public static final int DialogStokerBackground = 0;
	public static final int DrawerBackground = 0;
	public static final int CornerCut = CornerFamily.CUT;
	public static final int CornerNormal = CornerFamily.ROUNDED;
	public static final int colorPrimary = 0;
	public static final int colorPrimaryVariant = 0;
	public static final int colorOnPrimary = 0;
	public static final int colorSecondary = 0;
	public static final int colorSecondaryVariant = 0;
	public static final int colorOnSecondary = 0;
	private static SparseIntArray sparseIntArray;

	public PrivateColorUtil() {
		sparseIntArray = new SparseIntArray();

	}

	public synchronized static void setColor(int oldColor, int newColor) {
		int old = getColor(oldColor);
		if (old == newColor) {
			return;
		}
	}

	public synchronized static int getColor(int type) {
		return sparseIntArray.get(type);
	}

	public synchronized static void apply(int user) {
		int color = sparseIntArray.get(user);
		switch(user){
			case colorOnSecondary:
			color = Color.BLUE;
			break ;
			
		}
	}

}