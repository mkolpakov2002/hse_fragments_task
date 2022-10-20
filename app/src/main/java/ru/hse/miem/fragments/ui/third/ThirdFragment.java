package ru.hse.miem.fragments.ui.third;

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

import ru.hse.miem.fragments.Constants;
import ru.hse.miem.fragments.databinding.FragmentThirdBinding;
import ru.hse.miem.fragments.ui.ActivityCommunicate;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    private ActivityCommunicate listener;

    private boolean isOperationSelected = false;

    int operationCode = -1;

    TextView textView;

    int firstNumber;

    int secondNumber;

    MaterialButton buttonNext;

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
        ThirdViewModel homeViewModel =
                new ViewModelProvider(this).get(ThirdViewModel.class);

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        firstNumber = listener.getFirstNumber();
        secondNumber = listener.getSecondNumber();
        operationCode = listener.getOperationCode();

        textView = binding.textThird;

        buttonNext = binding.button7;
        final MaterialButton buttonPrev = binding.button8;


        buttonNext.setEnabled(isOperationSelected);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.openNextFragment(null);
                    listener.setOperation(operationCode);
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

        binding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operationCode = Constants.plusCode;
                onRefresh();
            }
        });

        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operationCode = Constants.minusCode;
                onRefresh();
            }
        });

        binding.buttonUmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operationCode = Constants.umnCode;
                onRefresh();
            }
        });

        binding.buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operationCode = Constants.delCode;
                onRefresh();
            }
        });
        onRefresh();
        return root;
    }

    void onRefresh(){
        if(operationCode == -1){
            isOperationSelected = false;
            buttonNext.setEnabled(false);
            textView.setText(firstNumber + " ? " + secondNumber);
            binding.textThird2.setText("No selected operation");
        } else if(operationCode == Constants.plusCode){
            isOperationSelected = true;
            textView.setText(firstNumber + " + " + secondNumber);
            buttonNext.setEnabled(true);
            binding.textThird2.setText("You selected summation");
        } else if(operationCode == Constants.minusCode){
            isOperationSelected = true;
            textView.setText(firstNumber + " - " + secondNumber);
            buttonNext.setEnabled(true);
            binding.textThird2.setText("You selected subtraction");
        } else if(operationCode == Constants.delCode){
            isOperationSelected = true;
            textView.setText(firstNumber + " / " + secondNumber);
            buttonNext.setEnabled(true);
            binding.textThird2.setText("You selected multiplication");
        } else if(operationCode == Constants.umnCode){
            isOperationSelected = true;
            textView.setText(firstNumber + " * " + secondNumber);
            buttonNext.setEnabled(true);
            binding.textThird2.setText("You selected division");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}