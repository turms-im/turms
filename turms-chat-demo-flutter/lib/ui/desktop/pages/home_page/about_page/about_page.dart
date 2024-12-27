import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/svg.dart';
import 'package:url_launcher/url_launcher_string.dart';

import '../../../../../infra/app/app_config.dart';
import '../../../../../infra/assets/assets.gen.dart';
import '../../../../../infra/github/github_client.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../themes/app_theme_extension.dart';
import '../../../../themes/sizes.dart';
import '../../../components/index.dart';

// Don't call "showAboutDialog" to avoid name conflict with
// the one in "flutter/lib/src/material/about.dart".
Future<void> showAppAboutDialog(BuildContext context) => showCustomTDialog(
    routeName: '/about-dialog', context: context, child: const AboutPage());

class AboutPage extends ConsumerStatefulWidget {
  const AboutPage({super.key});

  @override
  ConsumerState<AboutPage> createState() => _AboutPageState();
}

class _AboutPageState extends ConsumerState<AboutPage> {
  bool _isDownloading = false;

  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return _buildView(context.appThemeExtension, appLocalizations);
  }

  Future<bool> _openGitHub() =>
      launchUrlString('https://github.com/turms-im/turms');

  void _updateIsDownloading(bool isDownloading) {
    if (_isDownloading != isDownloading) {
      _isDownloading = isDownloading;
      setState(() {});
    }
  }
}

extension _AboutPageView on _AboutPageState {
  Widget _buildView(AppThemeExtension appThemeExtension,
          AppLocalizations appLocalizations) =>
      SizedBox(
        width: Sizes.aboutPageWidth,
        height: Sizes.aboutPageHeight,
        child: Stack(
          children: [
            Padding(
              padding: const EdgeInsets.only(top: 32, bottom: 16),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  SvgPicture.asset(
                    width: 320,
                    Assets.images.logo,
                  ),
                  Column(children: [
                    TTextButton(
                        text: appLocalizations.update,
                        isLoading: _isDownloading,
                        onTap: () async {
                          _updateIsDownloading(true);
                          // TODO: Support installing automatically
                          String text;
                          try {
                            final file = await GithubUtils.downloadLatestApp();
                            if (file == null) {
                              text = appLocalizations.alreadyLatestVersion;
                            } else {
                              // TODO: i10n
                              text = 'Downloaded: ${file.absolute.path}';
                            }
                          } catch (e) {
                            text =
                                'Failed to download latest application: ${e.toString()}';
                          }
                          _updateIsDownloading(false);
                          unawaited(TToast.showToast(context, text));
                        })
                  ]),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                          '${appLocalizations.version}:  ${AppConfig.packageInfo.version}'),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    spacing: 8,
                    children: [
                      const Text('GitHub'),
                      TTextButton(
                          text: 'github.com/turms-im/turms',
                          containerColor: Colors.transparent,
                          containerColorHovered: Colors.transparent,
                          textStyle: appThemeExtension.linkTextStyle,
                          textStyleHovered:
                              appThemeExtension.linkHoveredTextStyle,
                          onTap: _openGitHub)
                    ],
                  )
                ],
              ),
            ),
            const TTitleBar(
              displayCloseOnly: true,
              popOnCloseTapped: true,
            )
          ],
        ),
      );
}
