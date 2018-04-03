package com.nagizade.stringtohex.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nagizade.stringtohex.R;


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
        Button convertButton = (Button) view.findViewById(R.id.convertButton);
        text2 = view.findViewById(R.id.editText2);
        text1 = view.findViewById(R.id.editText);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eText1 = text1.getText().toString();
                String eText2 = text2.getText().toString();

                if (eText1.equals("") && !(eText2.equals(""))) {
                     bytes = eText2.getBytes();
                     emptyOne = "1";
                }
                if (eText2.equals("") && !(eText1.equals(""))) {
                     bytes = eText1.getBytes();
                     emptyOne = "2";
                }
                if (eText1.equals("") && eText2.equals("")) {

                }

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
        });
        return view;
    }
}