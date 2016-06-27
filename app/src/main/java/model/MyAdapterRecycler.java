package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.eti.kanemaki.testejson.R;

/**
 * Created by alexandre on 26/06/2016.
 */
public class MyAdapterRecycler extends RecyclerView.Adapter<MyAdapterRecycler.ViewHolder> {

    private List<WebHead> mSpiderList;
    private Context mContext;

    public MyAdapterRecycler(List<WebHead> l, Context c) {
        mContext = c;
        mSpiderList = l;
    }

    @Override
    public MyAdapterRecycler.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_spider, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyAdapterRecycler.ViewHolder holder, int i) {
        WebHead item = mSpiderList.get(i);
        holder.txtHq.setText(item.getNum());
        Glide.with(mContext).load(item.getHq()).into(holder.imgHq);
    }

    @Override
    public int getItemCount() {
        return mSpiderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtHq;
        protected ImageView imgHq;

        public ViewHolder(View itemView) {
            super(itemView);
            txtHq = (TextView) itemView.findViewById(R.id.txt_hq);
            imgHq = (ImageView) itemView.findViewById(R.id.img_hq);
        }
    }


}