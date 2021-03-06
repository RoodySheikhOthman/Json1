package com.example.acer.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String newMessages;
    String greenColors="";
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);

        String s =
                "{\n" +
                        "\"colors\": [\n" +
                        "{\n" +
                        "\"color\": \"black\",\n" +
                        "\"category\": \"hue\",\n" +
                        "\"type\": \"primary\",\n" +
                        "\"code\": {\n" +
                        "\"rgba\": [255,255,255,1],\n" +
                        "\"hex\": \"#000\"\n" +
                        "}\n" +
                        "},\n" +
                        "{\n" +
                        "\"color\": \"white\",\n" +
                        "\"category\": \"value\",\n" +
                        "\"code\": {\n" +
                        "\"rgba\": [0,0,0,1],\n" +
                        "\"hex\": \"#FFF\"\n" +
                        "}\n" +
                        "},\n" +
                        "{\n" +
                        "\"color\": \"red\",\n" +
                        "\"category\": \"hue\",\n" +
                        "\"type\": \"primary\",\n" +
                        "\"code\": {\n" +
                        "\"rgba\": [255,0,0,1],\n" +
                        "\"hex\": \"#FF0\"\n" +
                        "}\n" +
                        "},\n" +
                        "{\n" +
                        "\"color\": \"blue\",\n" +
                        "\"category\": \"hue\",\n" +
                        "\"type\": \"primary\",\n" +
                        "\"code\": {\n" +
                        "\"rgba\": [0,0,255,1],\n" +
                        "\"hex\": \"#00F\"\n" +
                        "}\n" +
                        "},\n" +
                        "{\n" +
                        "\"color\": \"yellow\",\n" +
                        "\"category\": \"hue\",\n" +
                        "\"type\": \"primary\",\n" +
                        "\"code\": {\n" +
                        "\"rgba\": [255,255,0,1],\n" +
                        "\"hex\": \"#FF0\"\n" +
                        "}\n" +
                        "},\n" +
                        "{\n" +
                        "\"color\": \"green\",\n" +
                        "\"category\": \"hue\",\n" +
                        "\"type\": \"secondary\",\n" +
                        "\"code\": {\n" +
                        "\"rgba\": [0,255,0,1],\n" +
                        "\"hex\": \"#0F0\"\n" +
                        "}\n" +
                        "}\n" +
                        "]\n" +
                        "}";

        try {

            JSONObject messages = (JSONObject) new JSONTokener(s).nextValue();
            JSONArray colors = (JSONArray) messages.getJSONArray("colors");
            for (int i=0; i<colors.length(); i++) {
                JSONObject color = (JSONObject) colors.get(i);
                JSONObject code = (JSONObject) color.get("code");
                JSONArray rgba = (JSONArray) code.get("rgba");
                if((int)rgba.get(1)== 255) {
                    counter++;
                    greenColors+= color.getString("color")+(" - ");
                }
            }
            JSONObject message = new JSONObject();
            JSONObject newcode = new JSONObject();
            message.put("color","orange");
            message.put("category","hue");
            int[] newrgba = new int[] {255, 165, 0, 1};
            newcode.put("rgba", Arrays.toString(newrgba));
            newcode.put("hex","#FA0");
            message.put("code",newcode);
            messages.put("colors",message);
            newMessages = s+"\n"+messages.toString(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void modify(View view) {
        textView.setText(newMessages);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
    public void list(View view) {
        textView.setText(greenColors);
    }
    public void count(View view) {
        textView.setText(Integer.toString(counter));
    }
}

