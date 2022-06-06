package com.example.to_do_app;


import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.to_do_app.Adapter.ToDoAdapter;
import com.example.to_do_app.Model.ToDoModel;
import com.example.to_do_app.Utils.DataBaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AdapterNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewTask";
    private EditText medittext;
    private Button mSaveButton;
    private DataBaseHelper myDb;

    public static AdapterNewTask newInstance() {
        return new AdapterNewTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_task, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medittext = view.findViewById(R.id.editText);
        mSaveButton = view.findViewById(R.id.btn_save);
        myDb = new DataBaseHelper(getActivity());
        boolean isUpdate = false;
        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            String task = bundle.getString("task");
            medittext.setText(task);
            if (task.length() > 0) {
                mSaveButton.setEnabled(false);
            }
        }
        medittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (toString().equals("")) {
                    mSaveButton.setEnabled(false);
                    mSaveButton.setBackgroundColor(Color.GRAY);
                } else {
                    mSaveButton.setEnabled(true);
                    mSaveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        final boolean finalIsUpate = isUpdate;
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = medittext.getText().toString();


                if (finalIsUpate) {
                    myDb.updateTask(bundle.getInt("id"), text);

                } else {
                    ToDoModel item = new ToDoModel();
                    item.setTask(text);
                    item.setStatus(0);
                    myDb.insertTask(item);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if(activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogCloseListner(dialog);
        }
    }
}
