package com.example.budgetmanagement.Dialogs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.budgetmanagement.AddInvestmentActivity;
import com.example.budgetmanagement.AddLoanActivity;
import com.example.budgetmanagement.R;
import com.example.budgetmanagement.ShoppingActivity;
import com.example.budgetmanagement.TransferActivity;


public class AddTransactionDialog extends DialogFragment {
    private static final String TAG = "AddTransactionDialog";

    private RelativeLayout shopping, investment, loan, transaction;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_transaction, null);
        shopping = view.findViewById(R.id.shoppingRelLayout);
        investment = (RelativeLayout) view.findViewById(R.id.investmentRelLayout);
        loan = (RelativeLayout) view.findViewById(R.id.loanRelLayout);
        transaction = (RelativeLayout) view.findViewById(R.id.transactionRelLayout);

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                startActivity(intent);
            }
        });

        investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddInvestmentActivity.class);
                startActivity(intent);
            }
        });

        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddLoanActivity.class);
                startActivity(intent);
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TransferActivity.class);
                startActivity(intent);
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Add Transaction")
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setView(view);

        return builder.create();
    }
}
