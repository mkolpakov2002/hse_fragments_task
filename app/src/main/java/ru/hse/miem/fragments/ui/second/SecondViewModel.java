package ru.hse.miem.fragments.ui.second;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SecondViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SecondViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is second fragment. Enter data to open second fragment.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}