package com.example.dog_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ExampleBottomSheetDialog extends BottomSheetDialogFragment {
    //private BottomSheetListener mListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
//        Button button= v.findViewById(R.id.bottomsheetbutton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener.onButtonClicked("button clicked");
//                dismiss();
//            }
//        });

        return v;
    }
//    public interface BottomSheetListener{
//        void onButtonClicked(String text);
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        try {
//            mListener = (BottomSheetListener) context;
//        }catch(ClassCastException e){
//            throw new ClassCastException(context.toString()+ " bottomsheetlistener");
//        }
//    }
}
