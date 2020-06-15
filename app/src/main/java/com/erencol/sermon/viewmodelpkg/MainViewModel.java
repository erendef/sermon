package com.erencol.sermon.viewmodelpkg;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.erencol.sermon.Data.Service.Host;
import com.erencol.sermon.Data.Service.ISermons;
import com.erencol.sermon.Data.Service.SermonClient;
import com.erencol.sermon.Model.Sermon;

import java.util.List;
import java.util.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends Observable {
    public MutableLiveData<Integer> busy;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Sermon> sermonMutableLiveData;
    LiveData<Sermon> getSermon(){
        if(sermonMutableLiveData == null)
            sermonMutableLiveData = new MutableLiveData<>();
        return sermonMutableLiveData;
    }

    private MutableLiveData<List<Sermon>> sermonListMutableLiveData;
    public MutableLiveData<List<Sermon>> getSermonList(){
        if(sermonListMutableLiveData == null)
            sermonListMutableLiveData = new MutableLiveData<>();
        return sermonListMutableLiveData;
    }


    public MutableLiveData<Integer> getBusy(){
        if(busy == null){
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }
        return busy;
    }



    public void getSermons(){
        getBusy().setValue(0);

        ISermons sermonsService = SermonClient.create();
        Disposable disposable = sermonsService.getSermons(Host.getSermons)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Sermon>>() {
                    @Override
                    public void accept(List<Sermon> sermons) throws Exception {
                        getSermonList().setValue(sermons);
                        setChanged();
                        notifyObservers();
                        getBusy().setValue(8);
                    }},new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
    }

}
