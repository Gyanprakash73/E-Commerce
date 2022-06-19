package com.gyan.ecommerce.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gyan.ecommerce.Adapter.ListAdapter;
import com.gyan.ecommerce.Model.Products;
import com.gyan.ecommerce.Model.ResultArray;
import com.gyan.ecommerce.databinding.FragmentAttaAndFloursBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttaAndFloursFragment extends Fragment {

    FragmentAttaAndFloursBinding binding;
    private static final String JSON_URL = "https://programmergyan.000webhostapp.com/groceries.json";
    List<Products> productsList;

    public AttaAndFloursFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAttaAndFloursBinding.inflate(inflater,container,false);

        productsList = new ArrayList<>();
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                response -> {
                    try {

                        JSONObject obj = new JSONObject(response);

                        JSONArray resultArray = obj.getJSONArray("result_array");

                        JSONObject resultArrayObject = resultArray.getJSONObject(0);

                        JSONArray productArray = resultArrayObject.getJSONArray("products");

                        for (int i = 0; i < productArray.length(); i++) {

                            JSONObject productObject = productArray.getJSONObject(i);

                            if (resultArrayObject.getString("name").equals(productObject.getString("category_name"))) {

                                JSONArray imageArray = productObject.getJSONArray("images");

                                for (int j = 0; j < imageArray.length(); j++) {

                                    JSONObject imageObject = imageArray.getJSONObject(j);

                                    Products hero = new Products(imageObject.getString("img_product"),productObject.getString("ID"),productObject.getString("name"),productObject.getString("final_price")
                                            ,productObject.getString("actual_price"),productObject.getString("discount"),productObject.getString("qty"));

                                    productsList.add(hero);
                                }
                            }
                        }

                        binding.recycler.setAdapter(new ListAdapter(productsList,getContext()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        return binding.getRoot();
    }
}