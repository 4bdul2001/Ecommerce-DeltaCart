package RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delta.R;

import java.util.ArrayList;

public class rvAdapterFirst extends RecyclerView.Adapter<viewHolderFirst>{

    ArrayList<ViewData> data;

    public rvAdapterFirst(ArrayList<ViewData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public viewHolderFirst onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rvcard_1, parent, false);
        return new viewHolderFirst(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderFirst holder, int position) {
        holder.imageView.setImageResource(data.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
