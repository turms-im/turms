import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class SecureStorageUtils {
  SecureStorageUtils._();

  static const FlutterSecureStorage _secureStorage = FlutterSecureStorage(
      aOptions: AndroidOptions(encryptedSharedPreferences: true),
      iOptions: IOSOptions(accessibility: KeychainAccessibility.first_unlock));

  static Future<void> setString(String key, String value) async =>
      _secureStorage.write(key: key, value: value);

  static Future<void> setBool(String key, bool value) async =>
      _secureStorage.write(key: key, value: value.toString());

  static Future<void> setNum(String key, num value) async =>
      _secureStorage.write(key: key, value: value.toString());

  static Future<Map<String, String>> getKeyToValue() async =>
      _secureStorage.readAll();
}
