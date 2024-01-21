package SearchViewAdapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StringViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    StringViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(android.R.id.text1);
    }
}
