const affixes = <String>['B', 'KB', 'MB', 'GB', 'TB', 'PB'];
const divider = 1024;

extension FileSizeExtensions on num {
  String toHumanReadableFileSize({int round = 2}) {
    final size = this;
    var runningDivider = divider;
    var runningPreviousDivider = 0;
    var affix = 0;

    while (size >= runningDivider && affix < affixes.length - 1) {
      runningPreviousDivider = runningDivider;
      runningDivider *= divider;
      affix++;
    }

    var result =
        (runningPreviousDivider == 0 ? size : size / runningPreviousDivider)
            .toStringAsFixed(round);

    if (result.endsWith('0' * round)) {
      result = result.substring(0, result.length - round - 1);
    }

    return '$result ${affixes[affix]}';
  }
}

extension FileSizeExtensionsInt on int {
  // ignore: non_constant_identifier_names
  int get KB => this * 1024;

  // ignore: non_constant_identifier_names
  int get MB => this * 1024 * 1024;
}

extension FileSizeExtensionsBigInt on BigInt {
  // ignore: non_constant_identifier_names
  BigInt get KB => this * BigInt.from(1024);

  // ignore: non_constant_identifier_names
  BigInt get MB => this * BigInt.from(1024 * 1024);
}
