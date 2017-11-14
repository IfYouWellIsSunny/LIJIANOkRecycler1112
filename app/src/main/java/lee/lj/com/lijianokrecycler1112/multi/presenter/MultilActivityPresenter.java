package lee.lj.com.lijianokrecycler1112.multi.presenter;


import lee.lj.com.lijianokrecycler1112.multi.MultiBean;
import lee.lj.com.lijianokrecycler1112.multi.MultiIView;
import lee.lj.com.lijianokrecycler1112.multi.module.MultilActivityModule;

/**
 * Created by muhanxi on 17/11/10.
 */

public class MultilActivityPresenter {


    private MultiIView view;
    private MultilActivityModule multilActivityModule ;

    public MultilActivityPresenter(MultiIView view){
        this.view = view;
        this.multilActivityModule = new MultilActivityModule();
    }


    /**
     * 下啦刷新
     */
    public void onRefresh(boolean up) {

        multilActivityModule.onRefresh(up, new MultilActivityModule.ModuleCallBack() {
            @Override
            public void success(MultiBean bean) {
                view.success(bean);
            }

            @Override
            public void failure(Exception e) {

                view.failure(e);
            }
        });


    }




}
