package com.example.prac1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemClickListener {
    var mainTextView: TextView? = null
    var mainButton: Button? = null
    var mainEditText: EditText? = null
    var deleteButton: Button? = null

    var mainListView: ListView? = null
    var mArrayAdapter: ArrayAdapter<*>? = null
    var mNameList = ArrayList<String>(5)

    var selected = ArrayList<View>();
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainTextView = findViewById(R.id.main_textview)
        mainButton = findViewById(R.id.main_button);
        mainEditText = findViewById(R.id.main_edittext);
        deleteButton = findViewById(R.id.delete_button);

        mainButton?.setOnClickListener({ view -> onMainButtonIsCLicked(view)});
        mainTextView?.text = "Set in Java!"

        deleteButton?.setOnClickListener({ view -> onDeleteButtonIsClicked(view)});

        mainListView = findViewById(R.id.main_listview)
        mArrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            mNameList)
        mainListView?.adapter = mArrayAdapter

        mainListView?.onItemClickListener = this;
    }


    override fun onClick(v: View?) {

    }

    @SuppressLint("SetTextI18n")
    private fun onMainButtonIsCLicked(view: View?){
        val new = mainEditText?.text.toString()
        if (!mNameList.contains(new)) {
            mNameList.add(new);
        }

        mNameList.sort();
        mArrayAdapter?.notifyDataSetChanged();

        mainTextView?.text = mainEditText?.text.toString() + " is learning Android development!";
    }

    private fun onDeleteButtonIsClicked(view: View?){
        for (view in selected){
            if (view is TextView){
                mNameList.remove(view.text)
                mArrayAdapter?.notifyDataSetChanged();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("omg android", position.toString() + ": " + mNameList.get(position));
        val text = mNameList.get(position).toString();

        mainTextView?.text = text + " is learning Android development!";

        val selectedWordtoSpan = SpannableString(text);
        selectedWordtoSpan.setSpan(BackgroundColorSpan(Color.BLUE), 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val clearWordtoSpan = SpannableString(text);
        clearWordtoSpan.setSpan(BackgroundColorSpan(Color.WHITE), 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        if (view is TextView) {
            if (!selected.contains(view)) {
                view.text = selectedWordtoSpan;
                selected.add(view);
            } else {
                view.text = clearWordtoSpan;
                selected.remove(view);
            }
        }
    }
}