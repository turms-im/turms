import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;
import 'package:pub_semver/pub_semver.dart';

import '../app/app_config.dart';
import '../http/http_response_exception.dart';
import '../http/http_utils.dart';
import 'github_asset.dart';
import 'versioned_asset.dart';

class GithubUtils {
  GithubUtils._();

  static Future<File?>? _pendingDownload;

  static Future<VersionedAsset?> fetchVersion() async {
    // TODO: make configurable
    const url = 'https://api.github.com/repos/turms-im/turms/releases';

    final response =
        await http.get(Uri.parse(url)).timeout(const Duration(seconds: 10));

    if (response.statusCode == 200) {
      final releases = json.decode(response.body) as List<dynamic>;
      if (releases.isEmpty) {
        return null;
      }
      var latestRelease = releases[0] as Map<String, dynamic>;
      var latestReleasePublishedAt =
          DateTime.parse(latestRelease['published_at'] as String);
      final releaseCount = releases.length;
      for (var i = 1; i < releaseCount; i++) {
        final release = releases[i] as Map<String, dynamic>;
        final publishedAt = release['published_at'] as String;
        final releasePublishedAt = DateTime.parse(publishedAt);
        if (releasePublishedAt.isAfter(latestReleasePublishedAt)) {
          latestRelease = release;
          latestReleasePublishedAt = releasePublishedAt;
        }
      }

      final latestVersion = latestRelease['tag_name'] as String;
      final latestAssets = latestRelease['assets'] as List<dynamic>;
      for (final (asset as Map<String, dynamic>) in latestAssets) {
        final name = asset['name'] as String;
        if (name.contains('turms-chat-demo')) {
          return VersionedAsset(
              version: Version.parse(normalizeVersion(latestVersion)),
              asset: GithubAsset.fromJson(asset));
        }
      }
      return null;
    }
    throw HttpResponseException(response);
  }

  /// returns null if the current version is latest.
  static Future<File?> downloadLatestApp() async {
    var pending = _pendingDownload;
    if (pending == null) {
      _pendingDownload = pending = _downloadLatestApp0();
    }
    return pending;
  }

  static Future<File?> _downloadLatestApp0() async {
    final versionedAsset = await GithubUtils.fetchVersion();
    if (versionedAsset == null) {
      return null;
    }
    final currentVersion = Version.parse(
        GithubUtils.normalizeVersion(AppConfig.packageInfo.version));
    if (versionedAsset.version < currentVersion) {
      return null;
    }
    final asset = versionedAsset.asset;
    final filePath =
        '${AppConfig.appDir}${Platform.pathSeparator}app${Platform.pathSeparator}${asset.name!}';
    if (await File(filePath).exists()) {
      return File(filePath);
    }
    final downloadFile = await HttpUtils.downloadFile(
        taskId: filePath,
        uri: Uri.parse(asset.browserDownloadUrl!),
        filePath: filePath);
    return downloadFile?.file;
  }

  static String normalizeVersion(String version) {
    if (version.startsWith('v')) {
      final index = version.indexOf('-', 1);
      if (index == -1) {
        return version.substring(1);
      } else {
        return version.substring(1, index);
      }
    }
    final index = version.indexOf('-', 1);
    if (index == -1) {
      return version;
    } else {
      return version.substring(0, index);
    }
  }
}
