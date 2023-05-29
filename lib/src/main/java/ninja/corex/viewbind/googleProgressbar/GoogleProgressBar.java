package ninja.corex.viewbind.googleProgressbar;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public  class GoogleProgressBar extends ProgressBar {
	public static int _type = 0;
	public static int[] _color = new int[]{0xFFC93437, 0xFF375BF1, 0xFFF7D23E, 0xFF34A350}; //Red, blue, yellow, green
    private enum ProgressType {
        FOLDING_CIRCLES,
        GOOGLE_MUSIC_DICES,
        NEXUS_ROTATION_CROSS,
        CHROME_FLOATING_CIRCLES
    }
    public GoogleProgressBar(Context context) {
        this(context, null);
    }
    public GoogleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,android.R.attr.progressBarStyle);
    }
    public GoogleProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode())
            return;
        final int typeIndex = _type;
		final int[] colorsId = _color;
        android.graphics.drawable.Drawable drawable = buildDrawable(context,typeIndex,colorsId);
        if(drawable!=null)
        setIndeterminateDrawable(drawable);
    }
    private android.graphics.drawable.Drawable buildDrawable(Context context, int typeIndex,int[] colorsId) {
        android.graphics.drawable.Drawable drawable = null;
        ProgressType type = ProgressType.values()[typeIndex];
        switch (type){
            case FOLDING_CIRCLES:
                drawable = new FoldingCirclesDrawable.Builder(context).colors(colorsId).build();
                break;
            case GOOGLE_MUSIC_DICES:
                drawable = new GoogleMusicDicesDrawable.Builder().build();
                break;
            case NEXUS_ROTATION_CROSS:
                drawable = new NexusRotationCrossDrawable.Builder(context).colors(colorsId).build();
                break;
            case CHROME_FLOATING_CIRCLES:
                drawable = new ChromeFloatingCirclesDrawable.Builder(context).colors(colorsId).build();
                break;
        }
        return drawable;
    }
    public static void setType(int types) {
    	_type = types;
    }
    public static void setColor(int[] colors) {
    	_color = colors;
    }
}