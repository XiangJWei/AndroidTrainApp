package com.xiangjw.androidtrainapp.adapter.first;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiangjw.androidtrainapp.adapter.base.BaseListClickListener;
import com.xiangjw.androidtrainapp.adapter.base.BaseViewHolder;
import com.xiangjw.androidtrainapp.bean.first.FirstKnowledge;
import com.xiangjw.androidtrainapp.databinding.FirstKnowledgeItemBinding;
import com.xiangjw.androidtrainapp.utils.StringUtils;

import java.util.List;

public class FirstKnowledgeAdapter extends RecyclerView.Adapter<BaseViewHolder<FirstKnowledgeItemBinding>> {

    public interface ListClickListener extends BaseListClickListener {
        void onClickItem(FirstKnowledge item);
    }

    private List<FirstKnowledge> list;

    private ListClickListener listener;

    public FirstKnowledgeAdapter(List<FirstKnowledge> list , ListClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public void setList(List<FirstKnowledge> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BaseViewHolder<FirstKnowledgeItemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FirstKnowledgeItemBinding binding = FirstKnowledgeItemBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false);
        BaseViewHolder<FirstKnowledgeItemBinding> holder = new BaseViewHolder(binding.getRoot() , binding);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){//列表项点击
                    listener.onClickItem(list.get(holder.getAdapterPosition()));
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<FirstKnowledgeItemBinding> holder, int position) {
        holder.getBinding().title.setText(StringUtils.convert(list.get(position).getName()));
        holder.getBinding().type.setText(StringUtils.convert(list.get(position).getType()));
        holder.getBinding().time.setText(StringUtils.convert(list.get(position).getCreateTime()));
        holder.getBinding().subtitle.setText(StringUtils.convert(list.get(position).getSubject()));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
