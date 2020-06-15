package com.erencol.observablesample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.annotation.NonNull;

public class LoginViewModelOld extends BaseObservable {
    private String email;
    private String password;
    private int busy = 8; //View.Gone
    public final ObservableField<String> errorPassword = new ObservableField<>();
    public final ObservableField<String> errorEmail = new ObservableField<>();

    public LoginViewModelOld() {
    }

    private MutableLiveData<User> userMutableLiveData;

    LiveData<User> getUser(){
        if(userMutableLiveData == null)
            userMutableLiveData = new MutableLiveData<>();
        return userMutableLiveData;
    }

    @Bindable
    @NonNull
    public String getEmail(){
        return this.email;
    }

    public void setEmail(@NonNull String email){
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    @NonNull
    public String getPassword(){
        return this.password;
    }

    public void setPassword(@NonNull String password){
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public int getBusy(){
        return this.busy;
    }

    public void setBusy(int busy){
        this.busy = busy;
        notifyPropertyChanged(BR.busy);
    }

    public void onLoginClicked(){
        setBusy(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = new User(getEmail(),getPassword());
                if(!user.isEmailValid())
                    errorEmail.set("Doğru bir eposta adresi giriniz.");
                else
                    errorEmail.set(null);

                if(!user.isPasswordValid())
                    errorPassword.set("Doğru bir şifre giriniz.");
                else
                    errorPassword.set(null);

                userMutableLiveData.setValue(user);
                setBusy(8);

            }
        },1000);
    }
}
