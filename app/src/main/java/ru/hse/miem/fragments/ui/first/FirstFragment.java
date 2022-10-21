package ru.hse.miem.fragments.ui.first;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;

import ru.hse.miem.fragments.databinding.FragmentFirstBinding;
import ru.hse.miem.fragments.ui.ActivityCommunicate;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

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

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final MaterialButton buttonNext = binding.button4;

        if(listener.getFirstNumber()!=null)
            binding.editText.setText(String.valueOf(listener.getFirstNumber()));
        binding.editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                buttonNext.setEnabled(binding.editText.getText().length()>0);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        binding.editText.setTransformationMethod(null);
        buttonNext.setEnabled(binding.editText.getText().length()>0);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.setFirstNumber(Integer.parseInt(binding.editText.getText().toString()));
                    listener.openNextFragment(null);
                }
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