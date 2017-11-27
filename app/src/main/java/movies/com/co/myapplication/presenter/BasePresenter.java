package movies.com.co.myapplication.presenter;


import movies.com.co.myapplication.helper.IValidateInternet;
import movies.com.co.myapplication.views.IBaseView;

public class BasePresenter<T extends IBaseView>{

    private T view;
    private IValidateInternet validateInternet;


    public void inject(T view, IValidateInternet validateInternet) {
        this.view = view;
        this.validateInternet = validateInternet;
    }


    public T getView() {
        return view;
    }

    public IValidateInternet getValidateInternet() {
        return validateInternet;
    }
}
