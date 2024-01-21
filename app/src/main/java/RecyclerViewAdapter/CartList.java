package RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.delta.R;

public class CartList extends RecyclerView.Adapter<CartList.CartItemHolder>{

   private int thumbnails[];
   private String names[];
   private double prices[];

   public CartList(int[] tb, String[] n, double[] p){
      this.thumbnails = tb;
      this.names = n;
      this.prices = p;
   }

   @NonNull
   @Override
   public CartItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
      return new CartItemHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull CartItemHolder holder, int position) {
      holder.thumbnail.setImageResource(thumbnails[position]);
      holder.name.setText(names[position]);
      holder.price.setText(String.format("$%.2f", prices[position]));
   }

   @Override
   public int getItemCount() {
      return names.length;
   }

   public class CartItemHolder extends RecyclerView.ViewHolder{
      ImageView thumbnail;
      TextView name, price;

      public CartItemHolder(@NonNull View itemView) {
         super(itemView);
         this.thumbnail = itemView.findViewById(R.id.cart_thumbnail);
         this.name = itemView.findViewById(R.id.cart_name);
         this.price = itemView.findViewById(R.id.cart_price);
      }
   }
}