package ru.hse.miem.fragments.ui.second;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;

import ru.hse.miem.fragments.databinding.FragmentSecondBinding;
import ru.hse.miem.fragments.ui.ActivityCommunicate;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private ActivityCommunicate listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ActivityCommunicate) context;
        } catch (ClassCastException castException) {
            /** The activity does not implement the listener. */
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SecondViewModel homeViewModel =
                new ViewModelProvider(this).get(SecondViewModel.class);

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSecond;
        final MaterialButton buttonNext = binding.button4;
        final MaterialButton buttonPrev = binding.button6;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        if(listener.getSecondNumber()!=null)
            binding.editTextSecond.setText(String.valueOf(listener.getSecondNumber()));
        binding.editTextSecond.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                buttonNext.setEnabled(binding.editTextSecond.getText().length()>0);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        binding.editTextSecond.setTransformationMethod(null);
        buttonNext.setEnabled(binding.editTextSecond.getText().length()>0);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.setSecondNumber(Integer.parseInt(binding.editTextSecond.getText().toString()));
                    listener.openNextFragment(null);
                }
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null)
                    listener.openPrevFragment(null);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}