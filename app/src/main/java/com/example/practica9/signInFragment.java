package com.example.practica9;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.practica9.databinding.FragmentSignInBinding;
import com.google.firebase.auth.FirebaseAuth;

public class signInFragment extends Fragment {

private FragmentSignInBinding binding;
private NavController nav;
private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSignInBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nav = Navigation.findNavController(view);
        mAuth = FirebaseAuth.getInstance();
         binding.emailsignIn.setOnClickListener(v -> {
             nav.navigate(R.id.action_signInFragment_to_signUpFragment);
         });

        binding.emailsignIn.setOnClickListener(v ->{
            String email = binding.email.getText().toString();
            String password = binding.psswd.getText().toString();

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            nav.navigate(R.id.action_signInFragment_to_chatFragment);
                        }else{
                            Toast.makeText(requireContext(),task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }


            });
        } );
    }
}
