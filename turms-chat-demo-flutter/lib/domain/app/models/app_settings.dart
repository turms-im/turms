import '../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../infra/sqlite/app_database.dart';
import 'app_setting.dart';

class AppSettings {
  AppSettings(this._settingToValue);

  factory AppSettings.fromTableData(List<AppSettingTableData> records) {
    final entries = records.map((r) => (AppSetting.fromId(r.id), r));
    final idToSetting = {
      for (final entry in entries)
        entry.$1: _parseValue(entry.$1, entry.$2.value.rawSqlValue)
    };
    return AppSettings(idToSetting);
  }

  final Map<AppSetting, Object> _settingToValue;

  static Object _parseValue(AppSetting setting, Object value) =>
      switch (setting) {
        AppSetting.rememberMe => (value as int).toBool(),
      };

  bool? getRememberMe() {
    final value = _settingToValue[AppSetting.rememberMe];
    if (value == null) {
      return null;
    }
    return value as bool;
  }
}
