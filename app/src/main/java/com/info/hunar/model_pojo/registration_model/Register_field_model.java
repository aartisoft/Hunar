package com.info.hunar.model_pojo.registration_model;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.info.hunar.BR;

/**
 * Created by Raghvendra Sahu on 13-Feb-20.
 */
public class Register_field_model extends BaseObservable {

    //public ObservableField<String> text = new ObservableField<>();
    private int selectedId=-1;
    private String Name;
    private String Mobile;
    private String Email;
    private String Password;
    private String Address;
    Context context;

    public Register_field_model(Context context) {
        this.context = context;
    }

    private MutableLiveData<Register_field_model> userMutableLiveData;

    LiveData<Register_field_model> getRegisterField() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }


    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    @Bindable
    public String getName() {
        if (Name == null) {
            return "";
        }
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
        notifyPropertyChanged(BR.model);
    }

    public String getMobile() {
        if (Mobile == null) {
            return "";
        }
        return Mobile;
    }

    public void setMobile(String mobile) {
        this.Mobile = mobile;
        notifyPropertyChanged(BR.model);
    }

    public String getEmail() {
        if (Email == null) {
            return "";
        }
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
        notifyPropertyChanged(BR.model);
    }

    public String getPassword() {
        if (Password == null) {
            return "";
        }
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
        notifyPropertyChanged(BR.model);
    }

    public String getAddress() {
        if (Address == null) {
            return "";
        }
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
        notifyPropertyChanged(BR.model);
    }



}
