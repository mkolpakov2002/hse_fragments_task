package ru.hse.miem.fragments.ui.fourth;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import ru.hse.miem.fragments.Constants;
import ru.hse.miem.fragments.R;
import ru.hse.miem.fragments.databinding.FragmentFirstBinding;
import ru.hse.miem.fragments.databinding.FragmentFourthBinding;
import ru.hse.miem.fragments.ui.ActivityCommunicate;
import ru.hse.miem.fragments.ui.first.FirstViewModel;

public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;

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
        FourthViewModel homeViewModel =
                new ViewModelProvider(this).get(FourthViewModel.class);

        binding = FragmentFourthBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFourth;
        final MaterialButton buttonPrev = binding.button4;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        final TextView textViewAnswer = binding.answer;
        textViewAnswer.setText(solved()+Float.toString(listener.getAnswer()));

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.openPrevFragment(null);
            }
        });
        return root;
    }

    String solved(){
        int operationCode = listener.getOperationCode();
        int firstNumber = listener.getFirstNumber();
        int secondNumber = listener.getSecondNumber();

        if(operationCode == -1){
            return (firstNumber + " ? " + secondNumber+ " = ");
        } else if(operationCode == Constants.plusCode){
            return(firstNumber + " + " + secondNumber+ " = ");
        } else if(operationCode == Constants.minusCode){
            return(firstNumber + " - " + secondNumber+ " = ");
        } else if(operationCode == Constants.delCode){
            return(firstNumber + " / " + secondNumber+ " = ");
        } else{
            return(firstNumber + " * " + secondNumber+ " = ");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}