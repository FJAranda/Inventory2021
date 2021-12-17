package com.example.inventory.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    public static final String REQUEST ="requestdialog";
    public static final String KEY_BUNDLE = "result";
    public static  final String TITLE = "title";
    public static final String MESSAGE = "message";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d("oncreatedialog", "bundle null");
        // Use the Builder class for convenient dialog construction
        if (getArguments() != null){
            Log.d("oncreatedialog", "bundlenotnull");
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
                    Bundle result = new Bundle();
                    result.putBoolean(KEY_BUNDLE, true);
                    getActivity().getSupportFragmentManager().setFragmentResult(REQUEST, result);
                }
            });
            // Create the AlertDialog object and return it
            return builder.create();
        }
        return null;
    }

    /*public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("oncreatedialog", "bundle null");
        // Use the Builder class for convenient dialog construction
        if (getArguments() != null){
            Log.d("oncreatedialog", "bundlenotnull");
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
    }*/

}
