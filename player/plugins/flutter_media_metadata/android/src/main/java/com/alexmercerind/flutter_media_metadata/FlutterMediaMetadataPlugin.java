package com.alexmercerind.flutter_media_metadata;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import java.io.IOException;
import java.util.HashMap;

public class FlutterMediaMetadataPlugin implements FlutterPlugin, MethodCallHandler {
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
    channel = new MethodChannel(binding.getBinaryMessenger(), "flutter_media_metadata");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull final MethodCall call, @NonNull final Result result) {
    if ("MetadataRetriever".equals(call.method)) {
      final String filePath = call.argument("filePath");

      new Thread(() -> {
        final HashMap<String, Object> response = new HashMap<>();
        try {
          MetadataRetriever retriever = new MetadataRetriever();
          retriever.setFilePath(filePath);

          response.put("metadata", retriever.getMetadata());
          response.put("albumArt", retriever.getAlbumArt());

          // Безопасное закрытие для разных API
          try {
            if (Build.VERSION.SDK_INT >= 29) {
              try {
                retriever.close(); // может бросать IOException
              } catch (IOException ignored) {}
            } else {
              retriever.release();
            }
          } catch (Throwable ignored) {}
        } catch (final Throwable t) {
          new Handler(Looper.getMainLooper()).post(() ->
              result.error("MetadataError", t.getMessage(), null));
          return;
        }

        new Handler(Looper.getMainLooper()).post(() -> result.success(response));
      }).start();

    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    if (channel != null) channel.setMethodCallHandler(null);
  }
}
