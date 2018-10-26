package com.example.wolf.recycler_view_demo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder> {
    private List<Person> persons;
    private static final String TAG = PersonRecyclerViewAdapter.class.getName();

    public PersonRecyclerViewAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder(ViewGroup, int) called");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_person, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder(ViewHolder, int) called");
        Person person = persons.get(i);
        viewHolder.portraitImageView.setImageResource(person.getPortraitResId());
        viewHolder.nameTextView.setText(person.getName());
        viewHolder.genderTextView.setText(person.getGender());
        viewHolder.ageTextView.setText(person.getAge() + "");
        final String description = person.getDescription();
        viewHolder.descriptionTextView.setText(description);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.toast.setText(description);
                viewHolder.toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView portraitImageView;
        private TextView nameTextView, genderTextView, ageTextView, descriptionTextView;
        private Toast toast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            portraitImageView = itemView.findViewById(R.id.portraitImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            genderTextView = itemView.findViewById(R.id.genderTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            toast = Toast.makeText(itemView.getContext(), null, Toast.LENGTH_SHORT);
        }
    }
}