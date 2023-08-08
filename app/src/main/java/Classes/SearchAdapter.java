package Classes;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paletteperspectivesfinal.MainBuyerActivity;
import com.example.paletteperspectivesfinal.ProfileActivity;
import com.example.paletteperspectivesfinal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private List<User> searchResults;
    FirebaseStorage storage;
    StorageReference storageRef;

    public SearchAdapter(List<User> searchResults) {

        this.searchResults = searchResults;
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = searchResults.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView detailsTextView;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);
            imageView = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click on search result item
                    User selectedUser = searchResults.get(getAdapterPosition());
                    openUserProfile(selectedUser, itemView);
                }
            });
        }

        public void bind(User user) {

            nameTextView.setText(user.getFirstName());
            detailsTextView.setText(user.getLastName());
            StorageReference defaultImageRef = storageRef.child(user.getProfilePictureUrl());
            defaultImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // Load the default profile image into profileImageView
                    Glide.with(itemView.getContext())
                            .load(uri)
                            .into(imageView);
                }
            });
        }
    }

    private void openUserProfile(User user, View itemView) {
        Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
        intent.putExtra("selectedUser", user);
        itemView.getContext().startActivity(intent);
    }
}
