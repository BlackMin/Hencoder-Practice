package com.baymax.android.hencoder.lesson9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baymax.android.hencoder.R;

import java.util.ArrayList;
import java.util.List;

public class Lesson9Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson9);
        TextTagsLayout textTagsLayout = findViewById(R.id.tags_layout);
        if(textTagsLayout != null) {
            textTagsLayout.setTags(getTags());
        }
    }

    private List<String> getTags() {
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("这个是tag2");
        tags.add("hello world");
        tags.add("你好，世界");
        tags.add("java");
        tags.add("python");
        tags.add("jvm");
        tags.add("kotlin");
        tags.add("c/c++");
        tags.add("delphi");
        tags.add("go");
        tags.add("《西游记》是中国古代第一部浪漫主义章回体长篇神魔小说。现存明刊百回本《西游记》均无作者署名。清代学者吴玉搢等首先提出《西游记》作者是明代吴承恩 [1]  。这部小说以“唐僧取经”这一历史事件为蓝本，通过作者的艺术加工，深刻地描绘了当时的社会现实。全书主要描写了孙悟空出世及大闹天宫后，遇见了唐僧、猪八戒和沙僧三人，西行取经，一路降妖伏魔，经历了九九八十一难，终于到达西天见到如来佛祖，最终五圣成真的故事。\n" +
                "《西游记》自问世以来在民间广为流传，各式各样的版本层出不穷，明代刊本有六种，清代刊本、抄本也有七种，典籍所记已佚版本十三种。鸦片战争以后，大量中国古典文学作品被译为西文，《西游记》渐渐传入欧美，被译为英、法、德、意、西、手语、世（世界语）、斯（斯瓦西里语）、俄、捷、罗、波、日、朝、越等文种。中外学者发表了不少研究论文和专著，对这部小说作出了极高的评价。");
        tags.add("xtend");
        tags.add("sql");
        tags.add("android");
        tags.add("产品设计");
        return tags;
    }
}
