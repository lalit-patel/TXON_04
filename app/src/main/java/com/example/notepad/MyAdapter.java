package com.example.notepad;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    RealmResults<Note> notelits;

    public MyAdapter(Context context, RealmResults<Note> notelits) {
        this.context = context;
        this.notelits = notelits;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note=notelits.get(position);
        holder.titleoutput.setText(note.getTitle());
        holder.desoutput.setText(note.description);

        String formatedTime = DateFormat.getDateTimeInstance().format(note.CreatedTime);
        holder.timeop.setText(formatedTime);


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu menu= new  PopupMenu(context,view);
                menu .getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getTitle().equals("DELETE")){
                            Realm realm=Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context,"NOTE DELETED",Toast.LENGTH_SHORT).show();


                        }
                        return true;
                    }
                });
                menu.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {

        return notelits.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleoutput;
        TextView desoutput;
        TextView timeop;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleoutput=itemView.findViewById(R.id.titleoutput);
            desoutput=itemView.findViewById(R.id.descriptionoutput);
            timeop=itemView.findViewById(R.id.timeoutput);


        }
    }
}
