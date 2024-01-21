package RecyclerViewAdapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delta.R;

public class viewHolderFirst extends RecyclerView.ViewHolder{

    ImageView imageView;

    public viewHolderFirst(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.rvFirstImage);
    }
}
