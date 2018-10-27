package com.example.wolf.speech_demo;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaiduSpeechSynthesizer {
    private SpeechSynthesizer speechSynthesizer;
    public static BaiduSpeechSynthesizer instance;
    private static final int ERROR_CODE_SUCCESS = 0;
    private static final String APP_ID = "14580936";
    private static final String API_KEY = "KflRpME3kpB9s7srRCRrt4N1";
    private static final String SECRET_KEY = "UiWU6kwgPpNaqMsI1wpBQlTA6DtUGxHf";
    private static final String TAG = BaiduSpeechSynthesizer.class.getName();

    private BaiduSpeechSynthesizer(Context context) throws InitializationException {
        speechSynthesizer = SpeechSynthesizer.getInstance();
        speechSynthesizer.setContext(context.getApplicationContext());
        speechSynthesizer.setAppId(APP_ID);
        speechSynthesizer.setApiKey(API_KEY, SECRET_KEY);
        File ttsDir = new File(context.getFilesDir() + "/BaiduTTS");
        if (!ttsDir.exists()) {
            boolean success = ttsDir.mkdir();
            Log.d(TAG, "success: " + success);
        }
        String speechModelAssetFilename = "bd_etts_common_speech_m15_mand_eng_high_am-mix_v3.0.0_20170505.dat";
        String speechModelFilename = ttsDir + "/" + speechModelAssetFilename;
        copyAssetFile(context, speechModelAssetFilename, speechModelFilename);
        String textModelAssetFilename = "bd_etts_text.dat";
        String textModelFilename = ttsDir + "/" + textModelAssetFilename;
        copyAssetFile(context, textModelAssetFilename, textModelFilename);
        speechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, speechModelFilename);
        speechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, textModelFilename);
        int errorCode = speechSynthesizer.initTts(TtsMode.MIX);
        if (errorCode != ERROR_CODE_SUCCESS)
            throw new InitializationException("Cannot initialize TTS", errorCode);
    }

    public void initialize() {
        //start here
    }

    public int speak(String text) {
        return speechSynthesizer.speak(text);
    }

    public int release() {
        return speechSynthesizer.release();
    }

    public static BaiduSpeechSynthesizer getInstance(Context context) throws InitializationException {
        if (instance == null)
            instance = new BaiduSpeechSynthesizer(context);
        return instance;
    }

    private void copyAssetFile(Context context, String assetFilename, String destinationFilename) {
        try (FileOutputStream outputStream = new FileOutputStream(destinationFilename)) {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(assetFilename);
            byte[] src = new byte[inputStream.available()];
            inputStream.read(src);
            outputStream.write(src);
        } catch (IOException ex) {
            Log.e(TAG, null, ex);
        }
    }

    public class InitializationException extends Exception {
        private int errorCode;

        private InitializationException(String message, int errorCode) {
            super(message);
            this.errorCode = errorCode;
        }

        public int getErrorCode() {
            return errorCode;
        }
    }
}