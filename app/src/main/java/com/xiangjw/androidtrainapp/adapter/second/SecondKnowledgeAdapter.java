package com.xiangjw.androidtrainapp.adapter.second;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.xiangjw.androidtrainapp.R;
import com.xiangjw.androidtrainapp.adapter.base.BaseListClickListener;
import com.xiangjw.androidtrainapp.adapter.base.BaseViewHolder;
import com.xiangjw.androidtrainapp.bean.second.SecondKnowledge;
import com.xiangjw.androidtrainapp.databinding.SecondKnowledgeItemBinding;
import com.xiangjw.androidtrainapp.utils.StringUtils;

import java.util.List;

public class SecondKnowledgeAdapter extends RecyclerView.Adapter<BaseViewHolder<SecondKnowledgeItemBinding>> {

    public interface ListClickListener extends BaseListClickListener {
        void onClickItem(SecondKnowledge item);
    }

    private List<SecondKnowledge> list;

    private ListClickListener listener;

    public SecondKnowledgeAdapter(List<SecondKnowledge> list , ListClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public void setList(List<SecondKnowledge> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BaseViewHolder<SecondKnowledgeItemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SecondKnowledgeItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()) , R.layout.second_knowledge_item , parent , false);

        BaseViewHolder<SecondKnowledgeItemBinding> holder = new BaseViewHolder(binding.getRoot() , binding);
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
    public void onBindViewHolder(@NonNull BaseViewHolder<SecondKnowledgeItemBinding> holder, int position) {
        holder.getBinding().setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
