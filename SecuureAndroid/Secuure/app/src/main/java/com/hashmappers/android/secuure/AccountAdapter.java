package com.hashmappers.android.secuure;

import android.content.Context;
import android.content.Intent;
import android.renderscript.RenderScript;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import static android.support.v4.app.ActivityCompat.startActivity;

//import static android.support.v4.app.ActivityCompatJB.startActivity;

/**
 * Created by voidtm on 11/16/16.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountHolder> {

    //HashMap<String, HashMap<String, Account>> accounts;
    Context context;
    HashMap<Integer, Account> accountTable;
    ArrayList accList;


    public static class AccountHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView wsName;
        private TextView accName;
        private Account acc;
        private Context context;
        public AccountHolder(View v, Context cont) {
            super(v);
            accName = (TextView) v.findViewById(R.id.account);
            wsName = (TextView) v.findViewById(R.id.website);
            v.setOnClickListener(this);
            this.context = cont;
        }

        public void bindAccount(Account a) {
            acc = a;
            wsName.setText(acc.getAppName());

            accName.setText(acc.getUsername());
        }

        @Override
        public void onClick(View v) {
            Log.d("RecyclerView", "CLICK!" + acc.getUsername());

            Global.resetAccount();
            Account editAccount = Global.getAcc();
            editAccount.set(acc);
            // should jump to editingAccounts from here
            context.startActivity(new Intent(context, EditingAccounts.class));
        }
    }


    public AccountAdapter(HashMap<Integer, Account> map, Context context) {

        // Hashmap is duplicated into an list for easy display
        // brings the question is array needed?
        accountTable = map;
        accList = new ArrayList();
        accList.addAll(accountTable.entrySet());
        this.context = context;
    }

    @Override
    public AccountAdapter.AccountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View node = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_acc_row, parent, false);
        //node.setBackground("border.xml");
        return new AccountHolder(node, context);

    }

    @Override
    public void onBindViewHolder(AccountAdapter.AccountHolder holder, int position) {
        // Gets the
        HashMap.Entry<Integer, Account> entry = (HashMap.Entry) accList.get(position);
        Account acc = entry.getValue();
        holder.bindAccount(acc);
    }

    @Override
    public int getItemCount() {
        return accList.size();
    }
}
