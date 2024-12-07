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
  ImageProvider? _selectedImage;
  bool _flipX = false;
  bool _flipY = false;
  double _angle = 0;

  @override
  void initState() {
    super.initState();
    _selectedImage = widget.user.image;
  }

  @override
  void dispose() {
    _selectedImage?.evict(cache: PaintingBinding.instance.imageCache);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final selectedImage = _selectedImage;
    final enableOperations = selectedImage != null;
    final user = widget.user;

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
                    child: selectedImage == null
                        ? TAvatar(
                            id: user.userId,
                            name: user.name,
                            textSize: 125,
                          )
                        : DecoratedBox(
                            decoration: const BoxDecoration(
                              color: Colors.black26,
                            ),
                            child: Stack(
                              children: [
                                Transform.flip(
                                  flipX: _flipX,
                                  flipY: _flipY,
                                  child: Transform.rotate(
                                    angle: _angle,
                                    child: Image(
                                      image: selectedImage,
                                      width: _imageSize,
                                      height: _imageSize,
                                      fit: BoxFit.contain,
                                      // If false, the image widget will blink
                                      // as the image loads,
                                      // while the image is loaded from the memory or filesystem,
                                      // it should be loaded very quickly.
                                      // so we set it to true to avoiding blinking.
                                      gaplessPlayback: true,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          )),
              ),
              Expanded(
                child: Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(appLocalizations.brightness),
                        const Text('50%'),
                      ],
                    ),
                    Slider(
                      value: 0,
                      max: 100,
                      divisions: 1,
                      onChanged: (value) {},
                    ),
                    const SizedBox(
                      height: 16,
                    ),
                    Text(appLocalizations.rotateAndFlip),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        TIconButton.outlined(
                          theme: theme,
                          iconData: Symbols.rotate_left_rounded,
                          containerSize: const Size.square(32),
                          tooltip: appLocalizations.rotateLeft,
                          disabled: !enableOperations,
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
                          disabled: !enableOperations,
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
                          disabled: !enableOperations,
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
                          disabled: !enableOperations,
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
                if (result?.isSinglePick ?? false) {
                  final file = result!.files[0];
                  final bytes = await file.readStream?.toFuture();
                  if ((bytes?.length ?? 0) > 0 &&
                      _allowedExtensions.contains(file.extension)) {
                    unawaited(_selectedImage?.evict(
                        cache: PaintingBinding.instance.imageCache));
                    _selectedImage = ResizeImage(MemoryImage(bytes!),
                        width: _imageSize.toInt(),
                        height: _imageSize.toInt(),
                        policy: ResizeImagePolicy.fit);
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
                // onTap: createGroupPageController.close,
              ),
              Sizes.sizedBoxW16,
              TTextButton(
                // isLoading: createGroupPageController.isCreating,
                // disabled:
                //     createGroupPageController.selectedUserContactIds.length <= 1,
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
