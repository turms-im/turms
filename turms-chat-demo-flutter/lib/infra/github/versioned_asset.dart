import 'package:pub_semver/pub_semver.dart';

import 'github_asset.dart';

class VersionedAsset {
  VersionedAsset({required this.version, required this.asset});

  final Version version;
  final GithubAsset asset;
}
