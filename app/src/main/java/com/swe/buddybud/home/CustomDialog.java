package com.swe.buddybud.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

import androidx.fragment.app.DialogFragment;

import com.swe.buddybud.R;

public class CustomDialog extends DialogFragment {

    private static final String ARG_NICKNAME = "nickname";
    private static final String ARG_PROFILE_IMAGE_ID = "profileImageId";

    public static CustomDialog newInstance(String nickname, int profileImageId) {
        CustomDialog fragment = new CustomDialog();
        Bundle args = new Bundle();
        args.putString(ARG_NICKNAME, nickname);
        args.putInt(ARG_PROFILE_IMAGE_ID, profileImageId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (getArguments() != null) {
            String nickname = getArguments().getString(ARG_NICKNAME);
            int profileImageId = getArguments().getInt(ARG_PROFILE_IMAGE_ID);

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_willow_request, null);

            CircleImageView profileImageView = dialogView.findViewById(R.id.dialog_profile_image);
            TextView nicknameTextView = dialogView.findViewById(R.id.dialog_nickname);
            TextView cancel = dialogView.findViewById(R.id.dialog_cancel);
            TextView send = dialogView.findViewById(R.id.dialog_send);

            profileImageView.setImageResource(profileImageId);
            nicknameTextView.setText(nickname);

            // 취소를 누를 경우
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            // 전송을 누를 경우
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CustomDialog", nickname + "에게 Send");
                    dismiss(); //
                }
            });

            builder.setView(dialogView);
        }

        Dialog dialog = builder.create();

        int dpValue = 300; // 300dp
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int pixelValue = (int) (dpValue * displayMetrics.density); // 픽셀 값으로 변환

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Window window = dialog.getWindow();
                if (window != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(window.getAttributes());
                    layoutParams.width = pixelValue;
                    window.setAttributes(layoutParams);
                }
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }
}
