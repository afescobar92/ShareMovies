package movies.com.co.myapplication.views;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dmax.dialog.SpotsDialog;
import movies.com.co.myapplication.helper.IValidateInternet;
import movies.com.co.myapplication.helper.ShowAlertDialog;
import movies.com.co.myapplication.helper.ValidateInternet;
import movies.com.co.myapplication.presenter.BasePresenter;


public class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {


    private IValidateInternet validateInternet;
    private AlertDialog progressDialog;
    private T presenter;
    private ShowAlertDialog showAlertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validateInternet = new ValidateInternet(BaseActivity.this);
        this.showAlertDialog = new ShowAlertDialog(this);
        createProgressDialog();

    }

    public ShowAlertDialog getShowAlertDialog() {
        return showAlertDialog;
    }

    @Override
    public void showProgress(int message) {
        if (message != 0){
            progressDialog.setMessage(getResources().getString(message));
        }
        progressDialog.show();
    }

    public void createProgressDialog(){
        this.progressDialog = new SpotsDialog(this);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    public IValidateInternet getValidateInternet() {
        return validateInternet;
    }


    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

}
