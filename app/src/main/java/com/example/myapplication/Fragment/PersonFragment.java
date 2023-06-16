package com.example.myapplication.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.LoginActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.UpdateActivity;
import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonFragment extends Fragment {


    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    TextView ten, ngaysinh, gioitinh, gmail, sdt;
    Button btnUdate, btnLogout;
    View view;
    public PersonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_person, container, false);

        init();
        getUserInfor();
        return view;
    }

    public void init(){
        ten = view.findViewById(R.id.tenuser);
        ngaysinh = view.findViewById(R.id.bduser);
        gioitinh = view.findViewById(R.id.sexUser);
        gmail = view.findViewById(R.id.gmailUser);
        sdt = view.findViewById(R.id.phoneUser);

        btnUdate = view.findViewById(R.id.btnUpdate);
        btnUdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });

        btnLogout = view.findViewById(R.id.btnlogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inte = new Intent(getContext(), LoginActivity.class);
                inte.putExtra("keylogout", "logout");
                startActivity(inte);
                getActivity().finish();
            }
        });
    }

    public void getUserInfor(){

        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<UserModel> call =apiEcommerceShop.getUser(MainActivity.id_user);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.code() == 200){
                    List<UserModel> userModels = response.body().getUserModel();
                    ten.setText(userModels.get(0).getUser_name());
                    ngaysinh.setText(userModels.get(0).getBd());
                    gioitinh.setText(userModels.get(0).getSex());
                    gmail.setText(userModels.get(0).getGmail());
                    sdt.setText(userModels.get(0).getPhone());
                }
                else {
                    Toast.makeText(getContext(), "Fail" + response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getContext(), "404"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}