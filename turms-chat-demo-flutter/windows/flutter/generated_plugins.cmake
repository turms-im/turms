#
# Generated file, do not edit.
#

list(APPEND FLUTTER_PLUGIN_LIST
  file_selector_windows
  flutter_inappwebview_windows
  flutter_platform_alert
  flutter_secure_storage_windows
  gal
  irondash_engine_context
  media_kit_libs_windows_audio
  media_kit_libs_windows_video
  media_kit_video
  screen_brightness_windows
  screen_retriever_windows
  sqlite3_flutter_libs
  super_native_extensions
  tray_manager
  url_launcher_windows
  window_manager
  windows_notification
)

list(APPEND FLUTTER_FFI_PLUGIN_LIST
  media_kit_native_event_loop
  rust_lib_turms_chat_demo
)

set(PLUGIN_BUNDLED_LIBRARIES)

foreach(plugin ${FLUTTER_PLUGIN_LIST})
  add_subdirectory(flutter/ephemeral/.plugin_symlinks/${plugin}/windows plugins/${plugin})
  target_link_libraries(${BINARY_NAME} PRIVATE ${plugin}_plugin)
  list(APPEND PLUGIN_BUNDLED_LIBRARIES $<TARGET_FILE:${plugin}_plugin>)
  list(APPEND PLUGIN_BUNDLED_LIBRARIES ${${plugin}_bundled_libraries})
endforeach(plugin)

foreach(ffi_plugin ${FLUTTER_FFI_PLUGIN_LIST})
  add_subdirectory(flutter/ephemeral/.plugin_symlinks/${ffi_plugin}/windows plugins/${ffi_plugin})
  list(APPEND PLUGIN_BUNDLED_LIBRARIES ${${ffi_plugin}_bundled_libraries})
endforeach(ffi_plugin)
