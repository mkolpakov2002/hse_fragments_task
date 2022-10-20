package ru.hse.miem.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import ru.hse.miem.fragments.databinding.ActivityMainBinding;
import ru.hse.miem.fragments.ui.ActivityCommunicate;

public class MainActivity extends AppCompatActivity implements ActivityCommunicate {

    private ActivityMainBinding binding;

    NavController navController;

    int fragmentId;

    Integer firstNumber = null;

    Integer secondNumber = null;

    int operationCode = -1;

    float answer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final MaterialButton firstButton = binding.button;
        final MaterialButton secondButton = binding.button2;
        final MaterialButton thirdButton = binding.button3;
        final MaterialButton fourthButton = binding.button5;

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.mobile_navigation);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Log.e("APP_LOG_TAG", "onDestinationChanged: " + destination.getLabel());
                fragmentId = destination.getId();
                //отслеживания фпагмента на главном экране
                if(fragmentId==R.id.navigation_first){
                    secondButton.setVisibility(View.INVISIBLE);
                    thirdButton.setVisibility(View.INVISIBLE);
                    fourthButton.setVisibility(View.INVISIBLE);
                } else if(fragmentId==R.id.navigation_second){
                    secondButton.setVisibility(View.VISIBLE);
                    thirdButton.setVisibility(View.INVISIBLE);
                    fourthButton.setVisibility(View.INVISIBLE);
                } else if(fragmentId==R.id.navigation_third){
                    secondButton.setVisibility(View.VISIBLE);
                    thirdButton.setVisibility(View.VISIBLE);
                    fourthButton.setVisibility(View.INVISIBLE);
                } else if(fragmentId==R.id.navigation_fourth){
                    secondButton.setVisibility(View.VISIBLE);
                    thirdButton.setVisibility(View.VISIBLE);
                    fourthButton.setVisibility(View.VISIBLE);
                }
            }
        });

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.navigation_first);
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.navigation_second);
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.navigation_third);
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.navigation_fourth);
            }
        });
    }

    @Override
    public void openNextFragment(Bundle bundle) {
        if(bundle==null)
            bundle = new Bundle();
        switch (fragmentId){
            case (R.id.navigation_first):
                navController.navigate(R.id.navigation_second, bundle);
                break;
            case (R.id.navigation_second):

                navController.navigate(R.id.navigation_third, bundle);
                break;
            default:
                navController.navigate(R.id.navigation_fourth, bundle);
                break;
        }
    }

    @Override
    public void openPrevFragment(Bundle bundle) {
        if(bundle==null)
            bundle = new Bundle();
        switch (fragmentId){
            case (R.id.navigation_third):
                navController.navigate(R.id.navigation_second, bundle);
                break;
            case (R.id.navigation_fourth):
                navController.navigate(R.id.navigation_third, bundle);
                break;
            default:
                navController.navigate(R.id.navigation_first, bundle);
                break;
        }
    }

    @Override
    public void onBackPressed(){
        switch (fragmentId){
            case (R.id.navigation_first):
                finish();
                break;
            default:
                openPrevFragment(null);
                break;
        }
    }

    @Override
    public void setFirstNumber(int i) {
        firstNumber = i;
        Log.e("miem", String.valueOf(firstNumber));
    }

    @Override
    public void setSecondNumber(int i) {
        secondNumber = i;
        Log.e("miem", String.valueOf(secondNumber));
    }

    @Override
    public void setOperation(int code) {
        operationCode = code;
    }

    @Override
    public Integer getFirstNumber() {
        return firstNumber;
    }

    @Override
    public Integer getSecondNumber() {
        return secondNumber;
    }

    @Override
    public int getOperationCode() {
        return operationCode;
    }

    @Override
    public float getAnswer() {
        if(operationCode == Constants.plusCode)
            return firstNumber+secondNumber;
        else if(operationCode == Constants.minusCode)
            return firstNumber-secondNumber;
        else if(operationCode == Constants.umnCode)
            return firstNumber*secondNumber;
        else return ((float) firstNumber)/((float) secondNumber);
    }
}