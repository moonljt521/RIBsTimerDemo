package com.gogovan.test.app.history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gogovan.test.app.common.Utils.WUtils;
import com.shinetechina.demo.R;
import com.gogovan.test.app.common.data.entities.TimeTaskEntity;

import java.util.List;

/**
 * Created by Arthur on 2018/11/10.
 */
public class HostoryListAdapter extends RecyclerView.Adapter<HostoryListAdapter.ItemViewHolder> {

    private List<TimeTaskEntity> list;

    public HostoryListAdapter(@NonNull List<TimeTaskEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_task,parent,
                false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        TimeTaskEntity en = this.list.get(position);


        holder.setTimeTask(en);

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;

        TextView timeTextView;

        public ItemViewHolder(View itemView) {

            super(itemView);

            nameTextView = itemView.findViewById(R.id.task_name_text);
            timeTextView = itemView.findViewById(R.id.time_text);
        }

        public void setTimeTask(TimeTaskEntity entity) {

            nameTextView.setText(entity.getTaskName());

            timeTextView.setText(WUtils.stringFromTime(entity.getFinishSeconds()));
        }
    }
}
