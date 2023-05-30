package ninja.corex.RenderUi.adapters;

import android.view.View;
import android.widget.HorizontalScrollView;

import ninja.corex.RenderUi.HorizontalElasticityBounceEffect;
import ninja.corex.RenderUi.VerticalElasticityBounceEffect;

/**
 * An adapter that enables over-scrolling support over a {@link HorizontalScrollView}.
 * <br/>Seeing that {@link HorizontalScrollView} only supports horizontal scrolling, this adapter
 * should only be used with a {@link HorizontalElasticityBounceEffect}.
 *
 * @author amit
 *
 * @see HorizontalElasticityBounceEffect
 * @see VerticalElasticityBounceEffect
 */
public class HorizontalScrollViewElasticityAdapter implements IElasticityAdapter {

    protected final HorizontalScrollView mView;

    public HorizontalScrollViewElasticityAdapter(HorizontalScrollView view) {
        mView = view;
    }

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return !mView.canScrollHorizontally(-1);
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return !mView.canScrollHorizontally(1);
    }
}
