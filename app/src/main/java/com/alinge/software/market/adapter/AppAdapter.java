package com.alinge.software.market.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.alinge.software.market.R;
import com.alinge.software.market.net.VolleyUtils;
import com.alinge.software.market.net.bean.AppInfo;
import com.alinge.software.market.view.AsycImageView;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： yejianlin
 * 日期：2016/1/21
 * 作用：
 */
public class AppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int MORE_TYPE = 1;
    private List<AppInfo> apps;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener mListener;
    public AppAdapter(Context context) {
        super();
        this.context = context;
        inflater = LayoutInflater.from(context);
        apps = new ArrayList<AppInfo>();
    }
    public void setLists(List<AppInfo> apps){
        this.apps=apps;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return apps.size()+1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            ViewHolder itemHolder=(ViewHolder)holder;
            AppInfo app= apps.get(position);
            //异步加载图片
            VolleyUtils.display(itemHolder.appIcon, app.getAppIcon());
            itemHolder.appName.setText(app.getSoftwareName());
            //ScaleAnimation scale=new ScaleAnimation(0.5f,1.0f,0.5f,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
            //scale.setDuration(300);
            Animation scale=AnimationUtils.loadAnimation(context, R.anim.app_item_scale);
            itemHolder.itemView.startAnimation(scale);
            itemHolder.itemView.setTag(app);
            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        mListener.onItemClick(v);
                    }
                }
            });
        }else{
            MoreHolder moreHolder=(MoreHolder)holder;
            moreHolder.moreTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"more",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position<getItemCount()-1){
            return super.getItemViewType(position);
        }else {
            return  MORE_TYPE;
        }
    }



    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType==0){
            View itemView = inflater.inflate(R.layout.app_item, parent, false);
            vh = new ViewHolder(itemView);

        }else{
            View moreView=inflater.inflate(R.layout.app_item_more,parent,false);
            vh=new MoreHolder(moreView);
        }
        return vh;
    }
    public void addOnItemClickListener(OnItemClickListener itemClickListener){
        this.mListener=itemClickListener;
    }
    public interface  OnItemClickListener{
         void onItemClick(View itemView);
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        public AsycImageView appIcon;
        public  TextView appName;
        public View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            appIcon=(AsycImageView)itemView.findViewById(R.id.appIcon);
            appName=(TextView)itemView.findViewById(R.id.appName);
        }
    }
    class MoreHolder extends RecyclerView.ViewHolder{
        public TextView  moreTv;
        public MoreHolder(View itemView) {
            super(itemView);
           moreTv=(TextView)itemView.findViewById(R.id.moreTv);
        }
    }
}
