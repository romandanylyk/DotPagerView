package com.rd.pageindicatorview.customize;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;

import com.rd.draw.IndicatorShape;
import com.rd.pageindicatorview.base.BaseActivity;
import com.rd.pageindicatorview.data.Customization;
import com.rd.pageindicatorview.data.CustomizationConverter;
import com.rd.pageindicatorview.sample.R;

public class CustomizeActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    public static final String EXTRAS_CUSTOMIZATION = "EXTRAS_CUSTOMIZATION";
    public static final int EXTRAS_CUSTOMIZATION_REQUEST_CODE = 1000;

    private Customization customization;

    public static void start(@NonNull Activity activity, @NonNull Customization customization) {
        Intent intent = new Intent(activity, CustomizeActivity.class);
        intent.putExtra(EXTRAS_CUSTOMIZATION, customization);
        activity.startActivityForResult(intent, EXTRAS_CUSTOMIZATION_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_customize);

        initToolbar();
        displayBackButton(true);
        initData();
        initViews();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(EXTRAS_CUSTOMIZATION, customization);
        setResult(Activity.RESULT_OK, intent);

        super.finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerShape:
                IndicatorShape shape = CustomizationConverter.getShape(position);
                customization.setShape(shape);
                break;
            case R.id.spinnerAnimationType:
                customization.setAnimationType(CustomizationConverter.getAnimationType(position));
                break;

            case R.id.spinnerOrientation:
                customization.setOrientation(CustomizationConverter.getOrientation(position));
                break;

            case R.id.spinnerRtl:
                customization.setRtlMode(CustomizationConverter.getRtlMode(position));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {/*empty*/}

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.switchInteractiveAnimation:
                customization.setInteractiveAnimation(isChecked);
                break;

            case R.id.switchAutoVisibility:
                customization.setAutoVisibility(isChecked);
                break;
        }
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            customization = intent.getParcelableExtra(EXTRAS_CUSTOMIZATION);
        } else {
            customization = new Customization();
        }
    }

    private void initViews() {
        Spinner spinnerShape = (Spinner) findViewById(R.id.spinnerShape);
        setSpinnerAdapter(spinnerShape,R.array.shape);
        spinnerShape.setOnItemSelectedListener( this );
        spinnerShape.setSelection(customization.getShape().ordinal());

        Spinner spinnerAnimationType = (Spinner) findViewById(R.id.spinnerAnimationType);
        setSpinnerAdapter(spinnerAnimationType, R.array.animation_type);
        spinnerAnimationType.setOnItemSelectedListener(this);
        spinnerAnimationType.setSelection(customization.getAnimationType().ordinal());

        Spinner spinnerOrientation = (Spinner) findViewById(R.id.spinnerOrientation);
        setSpinnerAdapter(spinnerOrientation, R.array.orientation);
        spinnerOrientation.setOnItemSelectedListener(this);
        spinnerOrientation.setSelection(customization.getOrientation().ordinal());

        Spinner spinnerRtl = (Spinner) findViewById(R.id.spinnerRtl);
        setSpinnerAdapter(spinnerRtl, R.array.rtl);
        spinnerRtl.setOnItemSelectedListener(this);
        spinnerRtl.setSelection(customization.getRtlMode().ordinal());

        Switch switchInteractiveAnimation = (Switch) findViewById(R.id.switchInteractiveAnimation);
        switchInteractiveAnimation.setOnCheckedChangeListener(this);
        switchInteractiveAnimation.setChecked(customization.isInteractiveAnimation());

        Switch switchAutoVisibility = (Switch) findViewById(R.id.switchAutoVisibility);
        switchAutoVisibility.setOnCheckedChangeListener(this);
        switchAutoVisibility.setChecked(customization.isAutoVisibility());

    }

    private void setSpinnerAdapter(@Nullable Spinner spinner, int textArrayId) {
        if (spinner != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, textArrayId, R.layout.item_spinner_selected);
            adapter.setDropDownViewResource(R.layout.item_spinner_drop_down);
            spinner.setAdapter(adapter);
            spinner.getBackground().setColorFilter(getResources().getColor(R.color.gray_500), PorterDuff.Mode.SRC_ATOP);
        }
    }
}
