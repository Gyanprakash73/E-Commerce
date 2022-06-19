package com.gyan.ecommerce.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyan.ecommerce.LocalDatabase.SqLiteHelper;
import com.gyan.ecommerce.Model.LocalDataModel;
import com.gyan.ecommerce.Model.Products;
import com.gyan.ecommerce.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    private List<LocalDataModel> localDataModelList;
    private Context context;
    SqLiteHelper sqLiteHelper;

    public CartAdapter(List<LocalDataModel> localDataModelList, Context context) {
        this.localDataModelList = localDataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.child_cart,parent,false);
        sqLiteHelper=new SqLiteHelper(context);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        LocalDataModel model=localDataModelList.get(position);
        Picasso.get().load(model.getImg_product()).into(holder.productImage);
        holder.productName.setText(model.getName());
        holder.tvQty.setText(model.getQty());

        //set price
        if (model.getQty().equals("1")){
            holder.finalPrice.setText(model.getFinal_price());
        }else {
            DecimalFormat type = new DecimalFormat("0.00");
            double price=Double.parseDouble(model.getFinal_price()) * Integer.parseInt(model.getQty());
            holder.finalPrice.setText(type.format(price));
        }

        //use for qty increment
        holder.tvAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty=model.getQty();
                int count=Integer.parseInt(qty)+1;

                sqLiteHelper.updateQty(String.valueOf(count),model.getID());
                model.setQty(String.valueOf(count));
                holder.tvQty.setText(model.getQty());

                DecimalFormat type = new DecimalFormat("0.00");
                double price=Double.parseDouble(model.getFinal_price()) * Integer.parseInt(model.getQty());
                holder.finalPrice.setText(type.format(price));
            }
        });

        //use for qty decrement
        holder.tvSubstraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty=model.getQty();
                int count=Integer.parseInt(qty)-1;

                if (count>0){
                    sqLiteHelper.updateQty(String.valueOf(count),model.getID());
                    model.setQty(String.valueOf(count));
                    holder.tvQty.setText(model.getQty());

                    DecimalFormat type = new DecimalFormat("0.00");
                    double price=Double.parseDouble(model.getFinal_price()) * Integer.parseInt(model.getQty());
                    holder.finalPrice.setText(type.format(price));
                }
            }
        });

        //use for qty delete
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Product")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sqLiteHelper.deleteData(model.getID());
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return localDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,finalPrice,tvQty,tvSubstraction,tvAddition;
        ImageView productImage,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            finalPrice = itemView.findViewById(R.id.tvFinalPrice);
            tvQty = itemView.findViewById(R.id.tvQty);
            productImage = itemView.findViewById(R.id.productImg);
            delete = itemView.findViewById(R.id.delete);
            tvSubstraction = itemView.findViewById(R.id.tvSubstraction);
            tvAddition = itemView.findViewById(R.id.tvAddition);
        }
    }

}
