package ru.hse.miem.fragments.ui.first;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirstViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FirstViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is first fragment. Enter data to open second fragment.");
    }

    public LiveData<String> getText() {
        return mText;
    }

}