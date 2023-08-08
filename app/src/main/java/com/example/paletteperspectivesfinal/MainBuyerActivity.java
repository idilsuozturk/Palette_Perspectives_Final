package com.example.paletteperspectivesfinal;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Classes.SearchAdapter;
import Classes.User;

public class MainBuyerActivity extends AppCompatActivity{
    private EditText searchEditText;
    private Button searchButton;
    private RecyclerView searchRecyclerView;

    private FirebaseFirestore firestore;
    private FirebaseUser currentUser;

    private List<User> searchResults;
    private SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_main_page);
        Button bestSellersButton = findViewById(R.id.button39);
        Button favouritesButton = findViewById(R.id.button40);
        Button accountButton = findViewById(R.id.button41);
        searchEditText = findViewById(R.id.editTextText4);
        searchButton = findViewById(R.id.search);
        searchRecyclerView = findViewById(R.id.recyclerView);

        firestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        searchResults = new ArrayList<>();
        searchAdapter = new SearchAdapter(searchResults);

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.setAdapter(searchAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        bestSellersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBuyerActivity.this, BuyerBestSellersActivity.class);
                startActivity(intent);
            }
        });
        favouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBuyerActivity.this, BuyerFavouritesActivity.class);
                startActivity(intent);
            }
        });
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBuyerActivity.this, BuyerProfile.class);
                startActivity(intent);
            }
        });

    }
    private void performSearch() {
        String query = searchEditText.getText().toString().trim();

        if (!query.isEmpty()) {
            firestore.collection("Users")
                    .whereEqualTo("FirstName", query)
                    .whereNotEqualTo("ID", currentUser.getUid())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                searchResults.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    User user = document.toObject(User.class);
                                    searchResults.add(user);
                                }
                                searchAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
    }
}
