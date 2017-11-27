package movies.com.co.myapplication.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import movies.com.co.myapplication.helper.IValidateInternet;
import movies.com.co.myapplication.presenter.BasePresenter;
import movies.com.co.myapplication.views.BaseActivity;
import movies.com.co.myapplication.views.IBaseView;

public class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {

    private BaseActivity baseActivity;
    private IValidateInternet validateInternet;
    private T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
        validateInternet = baseActivity.getValidateInternet();
    }

    @Override
    public void showProgress(int message) {
        baseActivity.showProgress(message);
    }

    @Override
    public void hideProgress() {
        baseActivity.hideProgress();
    }

    public BaseActivity getBaseActivityFragment() {
        return baseActivity;
    }

    public void setBaseActivityFragment(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public IValidateInternet getValidateInternet() {
        return validateInternet;
    }

    public void setValidateInternet(IValidateInternet validateInternet) {
        this.validateInternet = validateInternet;
    }

    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }
}
