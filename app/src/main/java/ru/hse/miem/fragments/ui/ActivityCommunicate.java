package ru.hse.miem.fragments.ui;

import android.os.Bundle;

public interface ActivityCommunicate {
    void openNextFragment(Bundle b);
    void openPrevFragment(Bundle b);
    void setFirstNumber(int i);
    void setSecondNumber(int i);
    void setOperation(int code);
    Integer getFirstNumber();
    Integer getSecondNumber();
    int getOperationCode();
    float getAnswer();
}
