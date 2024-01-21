package RecyclerViewAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.delta.R;

public class viewHolderSecond extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView title;
    TextView description;

    public viewHolderSecond(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.rvSecondImage);
        this.title = itemView.findViewById(R.id.rvSecondTitle);
        this.description = itemView.findViewById(R.id.rvSecondDesc);
    }
}
