package c.asynctask2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        ProgressBar myProgressBar;

        public MyAsyncTask(ProgressBar target) {
            myProgressBar = target;
        }

        @Override
        protected Void doInBackground(Void... params) {
            for(int i=0; i<100; i++){
                publishProgress(i);
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            myProgressBar.setProgress(values[0]);
        }

    }

    Button buttonStart;
    ProgressBar progressBar1, progressBar2, progressBar3,progressBar4;
    MyAsyncTask asyncTask1, asyncTask2, asyncTask3,asyncTask4;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar1 = (ProgressBar)findViewById(R.id.progressbar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressbar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressbar3);
        progressBar4 = (ProgressBar)findViewById(R.id.progressbar4);

        buttonStart = (Button)findViewById(R.id.start);
        buttonStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final boolean API_LEVEL_11
                        = android.os.Build.VERSION.SDK_INT > 11;

                progressBar1.setProgress(0);
                progressBar2.setProgress(0);
                progressBar3.setProgress(0);
                progressBar4.setProgress(0);

                asyncTask1 = new MyAsyncTask(progressBar1);
                asyncTask2 = new MyAsyncTask(progressBar2);
                asyncTask3 = new MyAsyncTask(progressBar3);
                asyncTask4 = new MyAsyncTask(progressBar4);

                if(API_LEVEL_11)
                {
                    asyncTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    asyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    asyncTask3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    asyncTask4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

            }});

    }

    @SuppressLint("NewApi")
    private void StartAsyncTaskInParallel(MyAsyncTask task) {

        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }
}
