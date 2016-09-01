package eu.fiveminutes.demo.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import eu.fiveminutes.demo.R;
import eu.fiveminutes.util.DisplayUtils;
import eu.fiveminutes.util.DisplayUtilsImpl;

public final class DisplayUtilsExampleActivity extends Activity {

    @InjectView(R.id.display_utils_activity_dp_tp_px_text_view)
    protected TextView dpToPxTextView;

    @InjectView(R.id.display_utils_activity_px_tp_dp_text_view)
    protected TextView pxToDpTextView;

    @InjectView(R.id.display_utils_activity_screen_height_text_view)
    protected TextView screenWidthTextView;

    @InjectView(R.id.display_utils_activity_screen_width_text_view)
    protected TextView screenHeightTextView;

    @InjectView(R.id.display_utils_activity_status_bar_height_text_view)
    protected TextView statusBarHeightTextView;

    private DisplayUtils displayUtils;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_utils_example);
        injectViews();
        displayUtils = new DisplayUtilsImpl(getResources(), getWindowManager());

        populateTextVies();
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }

    private void populateTextVies() {
        dpToPxTextView.setText(String.valueOf(displayUtils.getDpToPx(100)));
        pxToDpTextView.setText(String.valueOf(displayUtils.getPxToDp(100)));
        screenWidthTextView.setText(String.valueOf(displayUtils.getScreenWidth()));
        screenHeightTextView.setText(String.valueOf(displayUtils.getScreenHeight()));
        statusBarHeightTextView.setText(String.valueOf(displayUtils.getStatusBarHeight()));
    }
}
