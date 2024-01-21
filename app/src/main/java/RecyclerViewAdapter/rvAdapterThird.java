package RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delta.R;

import java.util.ArrayList;

public class rvAdapterThird extends RecyclerView.Adapter<viewHolderThird> {

    ArrayList<ViewData> data;

    public rvAdapterThird(ArrayList<ViewData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public viewHolderThird onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rvcard_3, parent, false);
        return new viewHolderThird(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderThird holder, int position) {
        holder.imageView.setImageResource(data.get(position).getImage());
        holder.title.setText(data.get(position).getTitle());
        holder.description.setText(data.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
