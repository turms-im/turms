import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../../domain/user/models/index.dart';
import '../../../../../../infra/io/file_utils.dart';
import '../../../../../../infra/io/io_extensions.dart';
import '../../../../../../infra/units/math_extensions.dart';
import '../../../../../l10n/app_localizations.dart';
import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../themes/index.dart';
import '../../../../components/index.dart';

const _allowedExtensions = ['png', 'jpg', 'jpeg'];
const _imageSize = 300.0;

class UserProfileImageEditorDialog extends ConsumerStatefulWidget {
  const UserProfileImageEditorDialog({super.key, required this.user});

  final User user;

  @override
  ConsumerState<UserProfileImageEditorDialog> createState() =>
      _UserProfileImageEditorDialogState();
}

class _UserProfileImageEditorDialogState
    extends ConsumerState<UserProfileImageEditorDialog> {
  ImageProvider? _profileImageProvider;
  bool _flipX = false;
  bool _flipY = false;
  double _angle = 0;

  @override
  void initState() {
    super.initState();
    _profileImageProvider = widget.user.image;
  }

  @override
  void dispose() {
    _profileImageProvider?.evict(cache: PaintingBinding.instance.imageCache);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final user = widget.user;
    final useImageMode = _profileImageProvider != null;

    return Padding(
      padding: const EdgeInsets.only(left: 16, top: 8, bottom: 16),
      child: Column(
        children: [
          Text(appLocalizations.editProfileImage),
          Sizes.sizedBoxH8,
          Row(
            spacing: 16,
            children: [
              ClipRRect(
                borderRadius: Sizes.borderRadiusCircular4,
                child: SizedBox(
                    width: _imageSize,
                    height: _imageSize,
                    child: useImageMode
                        ? DecoratedBox(
                            decoration: const BoxDecoration(
                              color: Colors.black26,
                            ),
                            child: Transform.flip(
                              flipX: _flipX,
                              flipY: _flipY,
                              child: Transform.rotate(
                                angle: _angle,
                                child: TImageCropper(
                                  image: _profileImageProvider!,
                                  aspectRatio: 1,
                                ),
                              ),
                            ),
                          )
                        : TAvatar(
                            id: user.userId,
                            name: user.name,
                            textSize: 125,
                          )),
              ),
              Expanded(
                child: Column(
                  children: [
                    Text(appLocalizations.rotateAndFlip),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        TIconButton.outlined(
                          theme: theme,
                          iconData: Symbols.rotate_left_rounded,
                          containerSize: const Size.square(32),
                          tooltip: appLocalizations.rotateLeft,
                          disabled: !useImageMode,
                          onTap: () {
                            _angle -= 90.degreesToRadians();
                            setState(() {});
                          },
                        ),
                        TIconButton.outlined(
                          theme: theme,
                          iconData: Symbols.rotate_right_rounded,
                          containerSize: const Size.square(32),
                          tooltip: appLocalizations.rotateRight,
                          disabled: !useImageMode,
                          onTap: () {
                            _angle += 90.degreesToRadians();
                            setState(() {});
                          },
                        ),
                        TIconButton.outlined(
                          theme: theme,
                          iconData: Symbols.flip_rounded,
                          containerSize: const Size.square(32),
                          tooltip: appLocalizations.flipHorizontally,
                          disabled: !useImageMode,
                          onTap: () {
                            _flipX = !_flipX;
                            setState(() {});
                          },
                        ),
                        TIconButton.outlined(
                          theme: theme,
                          iconData: Symbols.flip_rounded,
                          containerSize: const Size.square(32),
                          iconRotate: 90.degreesToRadians(),
                          tooltip: appLocalizations.flipVertically,
                          disabled: !useImageMode,
                          onTap: () {
                            _flipY = !_flipY;
                            setState(() {});
                          },
                        ),
                      ],
                    ),
                  ],
                ),
              )
            ],
          ),
          Expanded(
            child: Align(
                alignment: Alignment.bottomRight,
                child: _buildActions(context, theme, appLocalizations)),
          ),
        ],
      ),
    );
  }

  Widget _buildActions(BuildContext context, ThemeData theme,
          AppLocalizations appLocalizations) =>
      Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          TTextButton(
              containerPadding: Sizes.paddingV4H8,
              text: appLocalizations.selectProfileImage,
              onTap: () async {
                final result = await FileUtils.pickFile(
                    allowedExtensions: _allowedExtensions,
                    withReadStream: true);
                if (!mounted) {
                  return;
                }
                if (result?.isSinglePick == true) {
                  final file = result!.files[0];
                  final bytes = await file.readStream?.toFuture();
                  if (mounted &&
                      (bytes?.length ?? 0) > 0 &&
                      _allowedExtensions.contains(file.extension)) {
                    unawaited(_profileImageProvider?.evict(
                        cache: PaintingBinding.instance.imageCache));
                    _profileImageProvider = MemoryImage(bytes!);
                    setState(() {});
                  }
                }
              }),
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              TTextButton.outlined(
                theme: theme,
                text: appLocalizations.cancel,
                containerPadding: Sizes.paddingV4H8,
                containerWidth: 72,
                onTap: () => Navigator.of(context).pop(),
              ),
              Sizes.sizedBoxW16,
              TTextButton(
                text: appLocalizations.confirm,
                containerPadding: Sizes.paddingV4H8,
                containerWidth: 72,
                onTap: () {
                  // TODO: appLocalizations.confirm
                },
              ),
              Sizes.sizedBoxW16
            ],
          ),
        ],
      );
}

Future<void> showUserProfileImageEditorDialog(
        BuildContext context, User user) =>
    showSimpleTDialog(
      routeName: '/user-profile-image-dialog',
      context: context,
      width: Sizes.userProfileImageDialogWidth,
      height: Sizes.userProfileImageDialogHeight,
      child: UserProfileImageEditorDialog(
        user: user,
      ),
    );
