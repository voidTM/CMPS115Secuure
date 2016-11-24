package com.unittest.android.phpunittest;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by voidtm on 11/3/16.
 * Based off retrofit.GsonConverterFactory
 * It converts string Class to plain text
 * for sending over as Json
 * helps to prevent double quotes.
 */

public class GsonStringConverterFactory extends Converter.Factory
{
    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    public static GsonStringConverterFactory create() {
        return create(new Gson());
    }

    public static GsonStringConverterFactory create(Gson gson) {
        return new GsonStringConverterFactory(gson);
    }

    private final Gson gson;

    private GsonStringConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return new Converter<ResponseBody, String>() {

                @Override
                public String convert(ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if(String.class.equals(type)) {
            return new Converter<String, RequestBody>() {
                @Override
                public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value);
                }
            };
        }

        return null;
    }

}

