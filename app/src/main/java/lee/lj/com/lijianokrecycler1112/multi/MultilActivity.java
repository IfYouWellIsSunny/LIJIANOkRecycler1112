package lee.lj.com.lijianokrecycler1112.multi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lee.lj.com.lijianokrecycler1112.R;
import lee.lj.com.lijianokrecycler1112.multi.presenter.MultilActivityPresenter;

public class MultilActivity extends Activity implements MultiIView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.springview)
    SpringView springview;
    private MultilActivityPresenter presenter;
    private MultilActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MultilActivityPresenter(this);

        adapter = new MultilActivityAdapter(this);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);


        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));


        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

                presenter.onRefresh(true);
            }

            @Override
            public void onLoadmore() {
                presenter.onRefresh(false);
            }
        });

        presenter.onRefresh(true);


    }

    @Override
    public void success(MultiBean bean) {

        if (springview != null) {
            springview.onFinishFreshAndLoad();
        }
        adapter.addData(bean.getResult().getData());


    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this, " error ", Toast.LENGTH_SHORT).show();

    }

}
