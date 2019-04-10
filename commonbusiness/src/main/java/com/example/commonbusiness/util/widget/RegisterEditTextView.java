package com.example.commonbusiness.util.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.songup.modelmvpapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterEditTextView extends RelativeLayout {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.et_user_msg)
    EditText etUserMsg;
    @BindView(R.id.iv_line)
    ImageView ivLine;
    @BindView(R.id.tv_required)
    TextView tvRequired;
    @BindView(R.id.tv_error)
    TextView tvError;

    public RegisterEditTextView(Context context) {
        this(context, null);
    }

    public RegisterEditTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegisterEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.ll_regsiter_edit_view, this, true);
        ButterKnife.bind(view);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RegisterEditTextView);
        initTyped(typedArray);
    }

    private void initTyped(TypedArray typedArray) {
        final String textHint = typedArray.getString(R.styleable.RegisterEditTextView_et_hint_text);
        boolean isShowing = typedArray.getBoolean(R.styleable.RegisterEditTextView_required_isShowing, false);
        int imgResourceId = typedArray.getResourceId(R.styleable.RegisterEditTextView_left_img_src, 0);
        String textError = typedArray.getString(R.styleable.RegisterEditTextView_tv_error_text);
        boolean errorIsVisiblity = typedArray.getBoolean(R.styleable.RegisterEditTextView_tv_error_isVisibility, false);
        int leftIvWidht = (int) typedArray.getDimension(R.styleable.RegisterEditTextView_left_img_width, 17);
        int leftIvheight = (int) typedArray.getDimension(R.styleable.RegisterEditTextView_left_img_height, 24);
        int maxLength = typedArray.getInteger(R.styleable.RegisterEditTextView_maxLenght, 100);
        String digits = typedArray.getString(R.styleable.RegisterEditTextView_digits);
        int inputType = typedArray.getInteger(R.styleable.RegisterEditTextView_input_type,1);
        if (inputType == 4){
            onlyChinese();
        }
        final String inputHint = typedArray.getString(R.styleable.RegisterEditTextView_input_hint);
        LayoutParams layoutParams = new LayoutParams(leftIvWidht, leftIvheight);
        if (digits != null) {
            etUserMsg.setKeyListener(DigitsKeyListener.getInstance(digits));
        }
        InputFilter[] filters = {new InputFilter.LengthFilter(maxLength)};
        etUserMsg.setFilters(filters);

        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        ivLeft.setLayoutParams(layoutParams);
        setTvError(textError);
        setTvRequiredVisiblity(isShowing);
        setTvErrorVisiblity(errorIsVisiblity);
        if (textHint != null) {
            etUserMsg.setHint(textHint);
        }
        if (imgResourceId != 0) {
            ivLeft.setBackgroundResource(imgResourceId);
        }

//        etUserMsg.setInputType(inputType);
        etUserMsg.setOnFocusChangeListener(new

                                                   OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    tvError.setVisibility(INVISIBLE);
                    etUserMsg.setHint(inputHint);
                } else {
                    // 此处为失去焦点时的处理内容
//                    tvError.setVisibility(INVISIBLE);
                    etUserMsg.setHint(textHint);
                }
            }
        });
        etUserMsg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tvError.setVisibility(INVISIBLE);
                etUserMsg.setHint(inputHint);
            }
        });
        typedArray.recycle();
    }

    private void setTvError(String textError) {
        if (textError != null) {
            tvError.setText(textError);
        }
    }

    public void setTvErrorVisiblity(boolean isVisiblity) {
        tvError.setVisibility(isVisiblity ? VISIBLE : INVISIBLE);
    }

    public void setTvRequiredVisiblity(boolean isVisiblity) {
        tvRequired.setVisibility(isVisiblity ? VISIBLE : INVISIBLE);
    }

    public EditText getEditText() {
        return etUserMsg;
    }
    public void setEditText(String content) {
        etUserMsg.setText(content);
    }
    public void setCannotEditText() {
        etUserMsg.setFocusable(false);
        etUserMsg.setFocusableInTouchMode(false);
    }

    public void onlyChinese(){
        etUserMsg.addTextChangedListener(new BaseTextWatch(){
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    for (int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if (c <= 0x4e00 || c >= 0X9fff) {
                            s.delete(i,i+1);
                        }
                    }
                }
            }
        });
    }
}
