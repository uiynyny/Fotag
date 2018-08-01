package cs.fotag;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

import cs.fotag.model.Model;
import cs.fotag.model.Rates;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater=null;
    private ImageAdapter self = this;
    Model model;

    public ImageAdapter(Context c) {
        mContext = c;
        inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ImageAdapter(Context c, Model model){
        this.model=model;
        mContext=c;
        inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public class Holder {
        RatingBar rb;
        ImageView img;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();

        convertView = inflater.inflate(R.layout.thumbview,null);

        if(picRates[position] < model.getFilter()) {
            convertView.setVisibility(View.INVISIBLE);
        }else{
            convertView.setVisibility(View.VISIBLE);
        }

        holder.img = (ImageView) convertView.findViewById(R.id.imageview);
        holder.rb= (RatingBar) convertView.findViewById(R.id.rating);
        holder.img.setImageResource(mThumbIds[position]);
        final int pos=position;
        holder.rb.setRating((float) picRates[pos]);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleView.class);
                intent.putExtra("id", pos);
                Toast.makeText(mContext, ""+pos, Toast.LENGTH_SHORT).show();
                mContext.startActivity(intent);
            }
        });
        holder.rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                picRates[pos]=(int)rating;
                Toast.makeText(mContext, rating+" "+pos, Toast.LENGTH_SHORT).show();
                model.updateView();
                self.notifyDataSetChanged();
            }
        });
        return convertView;
    }

    // references to our images

    public Integer[] mThumbIds = {
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_8, R.drawable.sample_9,
            R.drawable.sample_10,R.drawable.sample_11
    };
    public Integer[] picRates = Rates.getInstance().getPicRates();
}