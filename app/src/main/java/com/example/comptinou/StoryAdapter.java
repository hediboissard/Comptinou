package com.example.comptinou;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    public List<Story> stories;
    public MyStoriesActivity activity;

    public StoryAdapter(List<Story> stories, MyStoriesActivity activity) {
        this.stories = stories;
        this.activity = activity;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.storyTitle.setText(story.getTitle());
        holder.storyPreview.setText(story.getPreview());
        holder.storyDate.setText(story.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onStoryClick(story.getId());
            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gérer le partage de l'histoire
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, story.getTitle());
                shareIntent.putExtra(Intent.EXTRA_TEXT, story.getContent());
                activity.startActivity(Intent.createChooser(shareIntent, "Partager l'histoire"));
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers l'écran d'édition
                Intent intent = new Intent(activity, CreateStoryActivity.class);
                intent.putExtra("EDIT_STORY_ID", story.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        TextView storyTitle;
        TextView storyPreview;
        TextView storyDate;
        ImageButton shareButton;
        ImageButton editButton;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            storyTitle = itemView.findViewById(R.id.storyTitle);
            storyPreview = itemView.findViewById(R.id.storyPreview);
            storyDate = itemView.findViewById(R.id.storyDate);
            shareButton = itemView.findViewById(R.id.shareButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}