package com.nagizade.stringtobinary.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nagizade.stringtobinary.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConverterFragment extends Fragment {

    private EditText text2;
    private EditText text1;
    private byte[] bytes;
    private String emptyOne;

    public ConverterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_converter, container, false);
        Button convertButton = view.findViewById(R.id.convertButton);
        Button clearButton  = view.findViewById(R.id.clearButton);

        text2 = view.findViewById(R.id.editText2);
        text1 = view.findViewById(R.id.editText);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eText1 = text1.getText().toString();
                String eText2 = text2.getText().toString();

                if (TextUtils.isEmpty(eText1) && !(TextUtils.isEmpty(eText2))) {
                    bytes = eText2.getBytes();
                    emptyOne = "1";
                }
                eText1.isEmpty();
                if (TextUtils.isEmpty(eText2) && !(TextUtils.isEmpty(eText1))) {
                    bytes = eText1.getBytes();
                    emptyOne = "2";
                }
                if (TextUtils.isEmpty(eText1) && TextUtils.isEmpty(eText2)) {

                    showAlert(getActivity(), "WARNING", "Please enter something to convert");

                }
                if (bytes != null) {
                    StringBuilder binary = new StringBuilder();
                    for (byte b : bytes) {
                        int val = b;
                        for (int i = 0; i < 8; i++) {
                            binary.append((val & 128) == 0 ? 0 : 1);
                            val <<= 1;
                        }
                        binary.append(' ');
                    }

                    switch (emptyOne) {
                        case "1":
                            text1.setText(binary);
                            break;
                        case "2":
                            text2.setText(binary);
                            break;
                    }

                }
            }
        });

        //If user clicked Clear button we will clear all EditText's
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText("");
                text2.setText("");
            }
        });
        return view;
    }

    public void showAlert(Context context,String title,String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
