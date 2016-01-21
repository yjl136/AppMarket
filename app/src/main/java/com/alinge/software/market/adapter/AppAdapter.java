package com.alinge.software.market.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {
    private List<AppInfo> apps;
    private Context context;
    private LayoutInflater inflater;

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
        return apps.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      AppInfo app= apps.get(position);
        //异步加载图片
        VolleyUtils.display(holder.appIcon,app.getAppIcon());
        holder.appName.setText(app.getSoftwareName());
    }

    @Override
    public AppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.app_item, null);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public AsycImageView appIcon;
        public  TextView appName;
        public ViewHolder(View itemView) {
            super(itemView);
            appIcon=(AsycImageView)itemView.findViewById(R.id.appIcon);
            appName=(TextView)itemView.findViewById(R.id.appName);
        }
    }
}
