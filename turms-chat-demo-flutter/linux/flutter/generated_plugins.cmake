#
# Generated file, do not edit.
#

list(APPEND FLUTTER_PLUGIN_LIST
  file_selector_linux
  flutter_platform_alert
  flutter_secure_storage_linux
  irondash_engine_context
  media_kit_libs_linux
  media_kit_video
  open_file_linux
  screen_retriever_linux
  sqlite3_flutter_libs
  super_native_extensions
  tray_manager
  url_launcher_linux
  volume_controller
  window_manager
)

list(APPEND FLUTTER_FFI_PLUGIN_LIST
  rust_lib_turms_chat_demo
)

set(PLUGIN_BUNDLED_LIBRARIES)

foreach(plugin ${FLUTTER_PLUGIN_LIST})
  add_subdirectory(flutter/ephemeral/.plugin_symlinks/${plugin}/linux plugins/${plugin})
  target_link_libraries(${BINARY_NAME} PRIVATE ${plugin}_plugin)
  list(APPEND PLUGIN_BUNDLED_LIBRARIES $<TARGET_FILE:${plugin}_plugin>)
  list(APPEND PLUGIN_BUNDLED_LIBRARIES ${${plugin}_bundled_libraries})
endforeach(plugin)

foreach(ffi_plugin ${FLUTTER_FFI_PLUGIN_LIST})
  add_subdirectory(flutter/ephemeral/.plugin_symlinks/${ffi_plugin}/linux plugins/${ffi_plugin})
  list(APPEND PLUGIN_BUNDLED_LIBRARIES ${${ffi_plugin}_bundled_libraries})
endforeach(ffi_plugin)
