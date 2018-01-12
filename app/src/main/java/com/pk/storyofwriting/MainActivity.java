package com.pk.storyofwriting;

import android.app.Activity;

import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity implements OnHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            HeadlineFragment headlineFragment = new HeadlineFragment();
            headlineFragment.setArguments(getIntent().getExtras());
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().add(R.id.container, headlineFragment).commit();
        }
    }
    @Override
    public void onArticleSelected(int position) {

        ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFragment != null) {
            articleFragment.updateArticleView(position);
        } else {
            articleFragment = new ArticleFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ArticleFragment.ARG_POSITION, position);
            articleFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, articleFragment).addToBackStack(null).commit();
        }


    }
}
