package com.example.turdsawce.emailtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.turdsawce.emailtest.models.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView senderText;
        private TextView bodyText;
        private TextView subjectText;
        private TextView timeText;
        private ImageView starImg;
        private ImageView senderImg;

        private final RecyclerViewClickHandler handler;

        public MyViewHolder(@NonNull View itemView, RecyclerViewClickHandler handler) {
            super(itemView);

            this.handler  = handler;
            this.senderText = itemView.findViewById(R.id.sender_text);
            this.bodyText = itemView.findViewById(R.id.body_text);
            this.subjectText = itemView.findViewById(R.id.subject_text);
            this.timeText = itemView.findViewById(R.id.time_text);
            this.starImg = itemView.findViewById(R.id.msg_star);
            this.senderImg = itemView.findViewById(R.id.msg_icon);

            itemView.setOnClickListener(this);
        }

        public void bind(Message msg) {

            this.senderText.setText(msg.getFrom());
            this.bodyText.setText(msg.getBody());
            this.subjectText.setText(msg.getSubject());
            LocalDate sentAt = msg.getSentAt();
            String sentAtStr = sentAt.toString();
            this.timeText.setText(sentAtStr);
            //myViewHolder.starImg;
            //myViewHolder.senderImg;
        }

        @Override
        public void onClick(View v) {
            this.handler.onClick(v, this.getAdapterPosition());
        }
    }

    // Instance variables
    private final List<Message> messageList;
    private final RecyclerViewClickHandler handler;

    public MessageAdapter(List<Message> messageList, RecyclerViewClickHandler handler) {
        this.messageList = new ArrayList<>(messageList);
        this.handler = handler;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.email_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v, this.handler);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if(i < messageList.size()) {
            Message msg = this.messageList.get(i);
            myViewHolder.bind(msg);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public String getMsgId(int pos) {
        if(pos >= 0 && pos < this.messageList.size()) {
            return this.messageList.get(pos).getMsgId();
        }

        return null;
    }
}
