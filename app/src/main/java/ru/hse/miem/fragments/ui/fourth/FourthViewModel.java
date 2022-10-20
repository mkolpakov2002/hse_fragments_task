package ru.hse.miem.fragments.ui.fourth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FourthViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public FourthViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fourth fragment. Here you can see the answer.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}