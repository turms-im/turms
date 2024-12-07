import 'dart:convert';
import 'dart:io';

import 'package:turms_chat_demo/infra/built_in_types/built_in_type_helpers.dart';

class Task {
  Task({required this.name, required this.executable, required this.arguments});

  final String name;
  final String executable;
  final List<String> arguments;
}

Future<void> runTask(
    String taskName, String executable, List<String>? arguments) async {
  if (arguments == null || arguments.isEmpty) {
    stdout.writeln("Start '$taskName' task. Running: $executable");
  } else {
    stdout.writeln(
        "Start '$taskName' task. Running: $executable ${arguments.join(' ')}");
  }
  final process =
      await Process.start(executable, arguments ?? [], runInShell: true);
  process.stdout.transform(utf8.decoder).listen(stdout.write);
  process.stderr.transform(utf8.decoder).listen(stderr.write);

  final exitCode = await process.exitCode;
  if (exitCode == 0) {
    stdout.writeln('Exit code: $exitCode');
  } else {
    stderr.writeln('Exit code: $exitCode');
  }
  await stdout.flush();
  await stderr.flush();
}

Future<void> generateEnvFile() async {
  final envFile = File('.env');
  final lines = envFile.readAsLinesSync();

  final code = StringBuffer()
    ..write('class EnvVars {\n')
    ..write('  EnvVars._();\n\n');

  for (final line in lines) {
    if (line.isBlank || line.startsWith('#')) {
      continue;
    }
    final parts = line.split('=');
    final key = parts[0];
    final value = parts[1];
    final name = key.constCaseToCamelCase();
    if (value == 'true' || value == 'false') {
      code.write("  static const bool $name = bool.fromEnvironment('$key');\n");
    } else if (value.contains('.') && double.tryParse(value) != null) {
      code.write(
          "  static final double $name = double.parse(const String.fromEnvironment('$key'));\n");
    } else if (int.tryParse(value) != null) {
      code.write("  static const int $name = int.fromEnvironment('$key');\n");
    } else {
      code.write(
          "  static const String $name = String.fromEnvironment('$key');\n");
    }
  }
  code.write('}');
  final file = File('./lib/infra/env/env_vars.dart');
  await file.create(recursive: true);
  await file.writeAsString(code.toString());
  print('Generated the environment variables file: ${file.absolute.path}');
}

Future<void> main() async {
  await generateEnvFile();
  final tasks = [
    Task(
        name: 'generate l10n dart files',
        executable: 'flutter',
        arguments: ['gen-l10n']),
    Task(
        name: 'generate assets and database dart files',
        executable: 'dart',
        arguments: [
          'run',
          'build_runner',
          'build',
          '--delete-conflicting-outputs'
        ])
  ];
  if (Platform.isLinux) {
    // final linuxDeviceInfo = await DeviceInfoPlugin().linuxInfo;
    // // https://pub.dev/packages/system_tray#prerequisite
    // if (linuxDeviceInfo.id.toLowerCase() == 'ubuntu') {
    //   tasks.add(Task(
    //       name: 'install dependencies of system_tray',
    //       executable: 'sudo',
    //       arguments: ['apt-get', 'install', 'libayatana-appindicator3-dev']));
    // } else {
    //   tasks.add(Task(
    //       name: 'install dependencies of system_tray',
    //       executable: 'sudo',
    //       arguments: [
    //         'apt-get',
    //         'install',
    //         'appindicator3-0.1',
    //         'libappindicator3-dev'
    //       ]));
    // }
  }
  for (final task in tasks) {
    await runTask(task.name, task.executable, task.arguments);
  }
}
