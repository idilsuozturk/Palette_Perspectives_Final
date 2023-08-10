package Classes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paletteperspectivesfinal.R;
import com.example.paletteperspectivesfinal.SellerShowArtPieceActivity;
import com.example.paletteperspectivesfinal.ShowArtPieceActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ImageAdapterFor extends RecyclerView.Adapter<ImageAdapterFor.ImageViewHolder>{
    private List<String> imageUrls;
    private Context context;
    private ImageAdapterFor.OnItemClickListener listener;
    StorageReference storageRef;
    FirebaseStorage storage;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ImageAdapterFor.OnItemClickListener listener) {
        this.listener = listener;
    }

    public ImageAdapterFor(List<String> imageUrls, Context context) {
        this.imageUrls = imageUrls;
        this.context = context;
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    @NonNull
    @Override
    public ImageAdapterFor.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageAdapterFor.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapterFor.ImageViewHolder holder, int position) {
        String imageUrl = imageUrls.get(position);
        StorageReference defaultImageRef = storageRef.child(imageUrl);
        defaultImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.imageView.getContext())
                        .load(uri)
                        .fitCenter()
                        .into(holder.imageView);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the SellerShowArtPieceActivity and pass the image URL
                Intent intent = new Intent(context, ShowArtPieceActivity.class);
                intent.putExtra("imageUrl", imageUrl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView); // Make sure this matches your layout file
        }
    }
}
