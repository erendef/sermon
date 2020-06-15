package com.erencol.observablesample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Integer> busy ;
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();

    public LoginViewModel() {
    }

    private MutableLiveData<User> userMutableLiveData;
    LiveData<User> getUser(){
        if(userMutableLiveData == null)
            userMutableLiveData = new MutableLiveData<>();
        return userMutableLiveData;
    }

    public MutableLiveData<Integer> getBusy(){
        if(busy == null){
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }
        return busy;
    }



    public void onLoginClicked(){
        getBusy().setValue(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = new User(email.getValue(),password.getValue());
                if(!user.isEmailValid())
                    errorEmail.setValue("Doğru bir eposta adresi giriniz.");
                else
                    errorEmail.setValue(null);

                if(!user.isPasswordValid())
                    errorPassword.setValue("Doğru bir şifre giriniz.");
                else
                    errorPassword.setValue(null);

                userMutableLiveData.setValue(user);
                busy.setValue(8);

            }
        },2000);
    }

}
