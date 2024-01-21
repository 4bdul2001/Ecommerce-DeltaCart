package SearchViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SuggestionsAdapter extends RecyclerView.Adapter<StringViewHolder> {
    private final List<String> suggestions;
    private AdapterView.OnItemClickListener itemClickListener;

    public SuggestionsAdapter(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate your item layout here
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StringViewHolder holder, int position) {
        // Bind your data to the view
        holder.textView.setText(suggestions.get(position));

        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(null, view, position, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public void updateData(List<String> newData) {
        suggestions.clear();
        suggestions.addAll(newData);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener clickListener) {
        this.itemClickListener = clickListener;
    }
}
