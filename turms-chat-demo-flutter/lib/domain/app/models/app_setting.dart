enum AppSetting {
  rememberMe(0);

  const AppSetting(this.id);

  final int id;

  static AppSetting fromId(int id) => values.firstWhere((e) => e.id == id);
}
