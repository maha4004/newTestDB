package com.example.softw.newtestdb;

import android.app.AlertDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }

    MainActivity mainActivity;

    protected String doInBackground(String... params){

        String type = params[0];
        String login_url = "http://192.168.1.9/login.php";
        if (type.equals("login")){
            try {
                String user_name = params[1];
                String user_pass = params [2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";

                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
          mainActivity.wrong();
        }
        return null;
    }

    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("login status");

    }

    protected void onPostExecute(String result){

        alertDialog.setMessage(result);
        alertDialog.show();
    }

    protected void onProgressUpdate(Void... values) {
       // super.onProgressUpdate(values);

    }
}
