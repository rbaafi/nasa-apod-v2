package edu.cnm.deepdive.nasaapod;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.nasaapod.service.ApodDatabase;
import io.reactivex.schedulers.Schedulers;

public class ApodApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    Picasso.setSingletonInstance(
        new Picasso.Builder(this)
            .loggingEnabled(true)
            .build()
    );
    ApodDatabase.setContext(this);
    ApodDatabase.getInstance().getApodDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

}
