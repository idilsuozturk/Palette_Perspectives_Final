package Classes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paletteperspectivesfinal.ProfileActivity;
import com.example.paletteperspectivesfinal.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private List<User> searchResults;

    public SearchAdapter(List<User> searchResults) {
        this.searchResults = searchResults;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);

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
        }
    }

    private void openUserProfile(User user, View itemView) {
        Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
        intent.putExtra("selectedUser", user);
        itemView.getContext().startActivity(intent);
    }
}
