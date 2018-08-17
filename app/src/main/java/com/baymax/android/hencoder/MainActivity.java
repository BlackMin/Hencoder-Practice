package com.baymax.android.hencoder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baymax.android.hencoder.lesson6.DashboardActivity;
import com.baymax.android.hencoder.lesson6.PieViewActivity;
import com.baymax.android.hencoder.lesson6.SportsActivity;
import com.baymax.android.hencoder.lesson6.TestActivity;
import com.baymax.android.hencoder.lesson6.TextLayoutActivity;
import com.baymax.android.hencoder.lesson6.TipsViewActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private LessonAdapter mLessonAdapter = new LessonAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mLessonAdapter);
        loadData();
    }

    private void loadData() {
        List<Lesson> list = new ArrayList<>();
        list.add(new Lesson("lesson6",6));
        list.add(new Lesson("lesson7",7));
        list.add(new Lesson("lesson8",8));
        mLessonAdapter.setList(list);
        mLessonAdapter.notifyDataSetChanged();
    }


    public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonHolder> {

        private List<Lesson> mList = null;

        public void setList(List<Lesson> list) {
            mList = list;
        }

        @NonNull
        @Override
        public LessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LessonHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.list_view_item_lesson, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull LessonHolder holder, int position) {
            if( mList != null && mList.size() > position) {
                Lesson lesson = mList.get(position);
                holder.bindView(lesson);
            }
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        public class LessonHolder extends RecyclerView.ViewHolder {

            private TextView lessonNameTextView;

            public LessonHolder(View itemView) {
                super(itemView);
                lessonNameTextView = itemView.findViewById(R.id.tv_lesson_name);
            }

            public void bindView(Lesson lesson) {
                if(lesson != null) {
                    lessonNameTextView.setText(lesson.name);
                    itemView.setOnClickListener(v->{
                        try {
                            Class clazz = Class.forName("com.baymax.android.hencoder.lesson"+lesson.lessonId+".Lesson"+lesson.lessonId+"Activity");
                            Intent intent = new Intent(MainActivity.this,clazz);
                            MainActivity.this.startActivity(intent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }
    }
}
