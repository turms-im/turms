import 'package:drift/drift.dart';

import '../../../infra/sqlite/app_database.dart';
import '../models/app_setting.dart';

class AppSettingRepository {
  Future<void> upsertRememberMe(bool rememberMe) async {
    final now = DateTime.now();
    final rememberMeValue = DriftAny(rememberMe);
    await appDatabase.into(appDatabase.appSettingTable).insert(
        AppSettingTableCompanion.insert(
          id: AppSetting.rememberMe.id,
          value: rememberMeValue,
          createdDate: now,
          lastModifiedDate: now,
        ),
        onConflict: DoUpdate((old) => AppSettingTableCompanion.custom(
            value: Constant(rememberMeValue),
            lastModifiedDate: Constant(now))));
  }

  Future<List<AppSettingTableData>> selectAll() =>
      appDatabase.select(appDatabase.appSettingTable).get();
}

final appSettingRepository = AppSettingRepository();
