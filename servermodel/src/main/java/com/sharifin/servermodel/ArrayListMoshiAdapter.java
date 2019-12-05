package com.sharifin.servermodel;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class ArrayListMoshiAdapter<T> extends JsonAdapter<ArrayList<T>> {

    public static final JsonAdapter.Factory FACTORY = (type, annotations, moshi) -> {
        Class<?> rawType = Types.getRawType(type);
        if (!annotations.isEmpty()) return null;
        if (rawType == ArrayList.class) {
            return newArrayListAdapter(type, moshi).nullSafe();
        }
        return null;
    };

    private static <T> JsonAdapter<ArrayList<T>> newArrayListAdapter(Type type, Moshi moshi) {
        Type elementType = Types.collectionElementType(type, Collection.class);
        JsonAdapter<T> elementAdapter = moshi.adapter(elementType);
        return new ArrayListMoshiAdapter<>(elementAdapter);
    }

    private final JsonAdapter<T> elementAdapter;

    private ArrayListMoshiAdapter(JsonAdapter<T> elementAdapter){
        this.elementAdapter = elementAdapter;
    }

    @Override
    public ArrayList<T> fromJson(JsonReader reader) throws IOException {
        ArrayList<T> result = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            result.add(elementAdapter.fromJson(reader));
        }
        reader.endArray();
        return result;
    }

    @Override
    public void toJson(JsonWriter writer, ArrayList<T> value) throws IOException {
        writer.beginArray();
        for (T element : value) {
            elementAdapter.toJson(writer, element);
        }
        writer.endArray();
    }
}
