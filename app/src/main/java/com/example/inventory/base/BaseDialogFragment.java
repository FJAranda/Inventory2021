package com.example.inventory.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.inventory.R;

public class BaseDialogFragment extends DialogFragment {
    public static final String KEY="requestdialog";
    public static final String KEY_BUNDLE = "result";
    public static  final String TITLE = "title";
    public static final String MESSAGE = "message";

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        if (getArguments() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(getArguments().getString(TITLE));
            builder.setMessage(getArguments().getString(MESSAGE));
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });
            builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(KEY_BUNDLE, true);
                    getActivity().getSupportFragmentManager().setFragmentResult(KEY, bundle);
                }
            });
            // Create the AlertDialog object and return it
            return builder.create();
        }
        return null;
    }

}
