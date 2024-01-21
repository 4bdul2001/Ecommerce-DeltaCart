package RecyclerViewAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delta.R;

public class viewHolderThird extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView title;
    TextView description;

    public viewHolderThird(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.rvThirdImage);
        this.title = itemView.findViewById(R.id.rvThirdTitle);
        this.description = itemView.findViewById(R.id.rvThirdDesc);
    }
}
