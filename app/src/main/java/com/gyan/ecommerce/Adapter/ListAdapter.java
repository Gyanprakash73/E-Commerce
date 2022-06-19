package com.gyan.ecommerce.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyan.ecommerce.LocalDatabase.SqLiteHelper;
import com.gyan.ecommerce.Model.Images;
import com.gyan.ecommerce.Model.LocalDataModel;
import com.gyan.ecommerce.Model.Products;
import com.gyan.ecommerce.Model.ResultArray;
import com.gyan.ecommerce.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Products> products;
    private Context context;
    SqLiteHelper sqLiteHelper;
    LocalDataModel localDataModel;
    List<LocalDataModel> qtyList;

    public ListAdapter(List<Products> productsList, Context context) {
        this.products = productsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_list,parent,false);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        localDataModel=new LocalDataModel();
        sqLiteHelper=new SqLiteHelper(context);
        Products products1 = products.get(position);

        //set Value into textview
        Picasso.get().load(products1.getImg_product()).into(holder.productImg);
        holder.productName.setText(products1.getName());
        holder.finalPrice.setText("₹"+products1.getFinal_price());

        //use for set value into offer
        if (products1.getDiscount().equals("0")){
            holder.actualPrice.setTransitionVisibility(View.INVISIBLE);
            holder.linear2.setVisibility(View.INVISIBLE);
        }else {
            holder.actualPrice.setText("₹" + products1.getActual_price());
            holder.offer.setText(products1.getDiscount());
        }

        //show QTY
        qtyList=sqLiteHelper.getData();
        for (LocalDataModel model:qtyList){
            if (products1.getID().equals(model.getID())){
                holder.relativeQtyAdd.setVisibility(View.GONE);
                holder.relativeQtyAddSubs.setVisibility(View.VISIBLE);
                holder.tvQty.setText(model.getQty());
             //   Toast.makeText(context, products1.getID()+" True "+model.getID(), Toast.LENGTH_SHORT).show();
            }else {
                holder.relativeQtyAdd.setVisibility(View.VISIBLE);
                holder.relativeQtyAddSubs.setVisibility(View.GONE);
              //  Toast.makeText(context, products1.getID()+" False "+model.getID(), Toast.LENGTH_SHORT).show();
            }
        }

        //use for add first qty
        holder.relativeQtyAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localDataModel.setID(products1.getID());
                localDataModel.setName(products1.getName());
                localDataModel.setFinal_price(products1.getFinal_price());
                localDataModel.setImg_product(products1.getImg_product());
                localDataModel.setQty("1");
                sqLiteHelper.addData(localDataModel);
                Toast.makeText(v.getContext(), "Product added to cart successfully", Toast.LENGTH_SHORT).show();
                holder.relativeQtyAdd.setVisibility(View.GONE);
                holder.relativeQtyAddSubs.setVisibility(View.VISIBLE);
                holder.tvQty.setText(localDataModel.getQty());
            }
        });

        //use for qty increment
        holder.tvAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LocalDataModel model:qtyList) {
                    if (products1.getID().equals(model.getID())) {
                        String qty = model.getQty();
                        Log.d("QTY", "qty " + qty);
                        int count = Integer.parseInt(qty) + 1;
                        sqLiteHelper.updateQty(String.valueOf(count), products1.getID());
                        model.setQty(String.valueOf(count));
                        holder.tvQty.setText(model.getQty());
                    }
                }
            }
        });

        //use for qty decrement
        holder.tvSubstraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LocalDataModel model:qtyList) {
                    if (products1.getID().equals(model.getID())) {
                        String qty = model.getQty();
                        int count = Integer.parseInt(qty) - 1;
                        sqLiteHelper.updateQty(String.valueOf(count), products1.getID());
                        model.setQty(String.valueOf(count));
                        if (count > 0) {
                            holder.tvQty.setText(model.getQty());
                        } else {
                            sqLiteHelper.deleteData(products1.getID());
                            holder.relativeQtyAdd.setVisibility(View.VISIBLE);
                            holder.relativeQtyAddSubs.setVisibility(View.GONE);

                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView productName,finalPrice,actualPrice,offer,tvSubstraction,tvQty,tvAddition;
        LinearLayout linear2;
        RelativeLayout relativeQtyAddSubs,relativeQtyAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.productImg);
            productName = itemView.findViewById(R.id.productName);
            finalPrice = itemView.findViewById(R.id.tvFinalPrice);
            actualPrice = itemView.findViewById(R.id.tvActualPrice);
            offer = itemView.findViewById(R.id.tvOffer);
            linear2 = itemView.findViewById(R.id.linear2);
            relativeQtyAddSubs = itemView.findViewById(R.id.relativeQtyAddSubs);
            relativeQtyAdd = itemView.findViewById(R.id.relativeQtyAdd);
            tvSubstraction = itemView.findViewById(R.id.tvSubstraction);
            tvQty = itemView.findViewById(R.id.tvQty);
            tvAddition = itemView.findViewById(R.id.tvAddition);
        }
    }

}
