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
    private String textModelFilename;
    private boolean initialized;
    public static BaiduSpeechSynthesizer instance;
    private static final int ERROR_CODE_SUCCESS = 0;
    private static final String APP_ID = "14580936";
    private static final String API_KEY = "KflRpME3kpB9s7srRCRrt4N1";
    private static final String SECRET_KEY = "UiWU6kwgPpNaqMsI1wpBQlTA6DtUGxHf";
    private static final String SPEECH_MODEL_FILENAME_MALE_VOICE = "bd_etts_common_speech_m15_mand_eng_high_am-mix_v3.0.0_20170505.dat";
    private static final String SPEECH_MODEL_FILENAME_FEMALE_VOICE = "bd_etts_common_speech_f7_mand_eng_high_am-mix_v3.0.0_20170512.dat";
    private static final String SPEECH_MODEL_FILENAME_DU_XIAO_YAO = "bd_etts_common_speech_yyjw_mand_eng_high_am-mix_v3.0.0_20170512.dat";
    private static final String SPEECH_MODEL_FILENAME_DU_YA_YA = "bd_etts_common_speech_as_mand_eng_high_am_v3.0.0_20170516.dat";
    private static final String TAG = BaiduSpeechSynthesizer.class.getName();

    private BaiduSpeechSynthesizer() {
        speechSynthesizer = SpeechSynthesizer.getInstance();
    }

    public void initialize(Context context) throws InitializationException {
        speechSynthesizer.setContext(context);
        speechSynthesizer.setAppId(APP_ID);
        speechSynthesizer.setApiKey(API_KEY, SECRET_KEY);
        File ttsDir = new File(context.getFilesDir() + "/BaiduTTS");
        if (!ttsDir.exists()) {
            boolean success = ttsDir.mkdir();
            Log.d(TAG, "success: " + success);
        }
//        String speechModelAssetFilename = "bd_etts_common_speech_m15_mand_eng_high_am-mix_v3.0.0_20170505.dat";
        String speechModelFilename = ttsDir + "/" + SPEECH_MODEL_FILENAME_MALE_VOICE;
        copyAssetFile(context, SPEECH_MODEL_FILENAME_MALE_VOICE, speechModelFilename);  //start here
        String textModelAssetFilename = "bd_etts_text.dat";
        textModelFilename = ttsDir + "/" + textModelAssetFilename;
        copyAssetFile(context, textModelAssetFilename, textModelFilename);
        speechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, speechModelFilename);
        speechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, textModelFilename);
        int errorCode = speechSynthesizer.initTts(TtsMode.MIX);
        if (errorCode != ERROR_CODE_SUCCESS)
            throw new InitializationException("Cannot initialize TTS", errorCode);
        initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public int speak(String text) {
        return speechSynthesizer.speak(text);
    }

    public int switchVoice(VoiceType type) {
        String speechModelFilename = null;
        switch (type) {
            case MALE_VOICE:
                speechModelFilename = "bd_etts_common_speech_m15_mand_eng_high_am-mix_v3.0.0_20170505.dat";
                break;
            case FEMALE_VOICE:
                speechModelFilename = "bd_etts_common_speech_f7_mand_eng_high_am-mix_v3.0.0_20170512.dat";
                break;
            case DU_XIAO_YAO:
                speechModelFilename = "bd_etts_common_speech_yyjw_mand_eng_high_am-mix_v3.0.0_20170512.dat";
                break;
            case DU_YA_YA:
                speechModelFilename = "bd_etts_common_speech_as_mand_eng_high_am_v3.0.0_20170516.dat";
        }
        return speechSynthesizer.loadModel(speechModelFilename, textModelFilename);
    }

    public int release() {
        instance = null;
        return speechSynthesizer.release();
    }

    public static BaiduSpeechSynthesizer getInstance() {
        if (instance == null)
            instance = new BaiduSpeechSynthesizer();
        return instance;
    }

    private void copyAssetFile(Context context, String assetFilename, String destinationFilename) {
        File destinationFile = new File(destinationFilename);
        if (!destinationFile.exists()) {
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

    public enum VoiceType {
        MALE_VOICE,
        FEMALE_VOICE,
        DU_XIAO_YAO,
        DU_YA_YA
    }
}