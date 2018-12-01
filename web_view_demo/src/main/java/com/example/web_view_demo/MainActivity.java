package com.example.web_view_demo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/html_page.html");
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public String showToast(String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                return "showToast(String) called";
            }
        }, "JsInterface");
        findViewById(R.id.evaluateJsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "View.OnClickListener().onClick(View) called";
                webView.evaluateJavascript("javascript:showDialog('" + message + "')", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "value: " + value);
                    }
                });
            }
        });
    }
}