import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';
import 'package:super_drag_and_drop/super_drag_and_drop.dart';

import '../../../../../../domain/user/models/index.dart';
import '../../../../../../infra/io/file_utils.dart';
import '../../../../../../infra/io/io_extensions.dart';
import '../../../../../../infra/units/math_extensions.dart';
import '../../../../../l10n/app_localizations.dart';
import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../themes/index.dart';
import '../../../../components/index.dart';
import '../../../../components/t_image_cropper/core/t_image_cropper_controller.dart';

const _allowedExtensions = ['png', 'jpg', 'jpeg'];
final _allowedFormats = [Formats.png, Formats.jpeg];
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
  CaptureResult? _profileImageCaptureResult;
  late TImageCropperController _imageCropperController;
  bool _flipX = false;
  bool _flipY = false;
  double _angle = 0;

  @override
  void initState() {
    super.initState();
    _profileImageProvider = widget.user.image;
    _imageCropperController = TImageCropperController();
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
    return TDropZone(
      formats: _allowedFormats,
      onPerformDrop: _onPerformDrop,
      child: Padding(
        padding: Sizes.paddingV8H16,
        child: Column(
          children: [
            Text(appLocalizations.editProfileImage),
            Sizes.sizedBoxH8,
            Row(
              spacing: 16,
              children: [
                _buildProfile(useImageMode, user),
                Expanded(
                  child: Column(
                    children: [
                      useImageMode && _profileImageCaptureResult != null
                          ? ClipRRect(
                              borderRadius: TAvatar.borderRadius,
                              child: SizedBox.square(
                                dimension: TAvatarSize.medium.containerSize,
                                child: TRawImage(
                                  image: _profileImageCaptureResult!.img,
                                  cropRect:
                                      _profileImageCaptureResult!.cropRect,
                                ),
                              ),
                            )
                          : TAvatar(id: user.userId, name: user.name),
                      const SizedBox(height: 16),
                      Text(appLocalizations.rotateAndFlip),
                      const SizedBox(height: 8),
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
                              _angle -= 90;
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
                              _angle += 90;
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
                ),
              ],
            ),
            Expanded(
              child: Align(
                alignment: Alignment.bottomRight,
                child: _buildActions(context, theme, appLocalizations),
              ),
            ),
          ],
        ),
      ),
    );
  }

  ClipRRect _buildProfile(bool useImageMode, User user) => ClipRRect(
    borderRadius: Sizes.borderRadiusCircular4,
    child: SizedBox(
      width: _imageSize,
      height: _imageSize,
      child: useImageMode
          ? DecoratedBox(
              decoration: const BoxDecoration(color: Colors.black26),
              child: TImageCropper(
                image: _profileImageProvider!,
                controller: _imageCropperController,
                flipX: _flipX,
                flipY: _flipY,
                rotationAngle: _angle,
                aspectRatio: 1,
                onCropAreaMoved: (containerRect, imageRect) async {
                  SchedulerBinding.instance.addPostFrameCallback((_) async {
                    final result = await _imageCropperController.capture
                        ?.call();
                    _profileImageCaptureResult = result!;
                    setState(() {});
                  });
                },
              ),
            )
          : TAvatar(id: user.userId, name: user.name, textSize: 125),
    ),
  );

  Widget _buildActions(
    BuildContext context,
    ThemeData theme,
    AppLocalizations appLocalizations,
  ) => Row(
    mainAxisAlignment: MainAxisAlignment.spaceBetween,
    children: [
      TTextButton(
        containerPadding: Sizes.paddingV4H8,
        text: appLocalizations.selectProfileImage,
        onTap: () async {
          final result = await FileUtils.pickFile(
            allowedExtensions: _allowedExtensions,
            withReadStream: true,
          );
          if (!mounted) {
            return;
          }
          assert(result!.isSinglePick);
          assert(result!.files.length == 1);
          if (result?.isSinglePick == true) {
            final file = result!.files[0];
            if (!_allowedExtensions.contains(file.extension)) {
              return;
            }
            final bytes = await file.readStream?.toFuture();
            if (mounted && bytes != null) {
              _loadImage(bytes);
            }
          }
        },
      ),
      Row(
        mainAxisAlignment: MainAxisAlignment.end,
        spacing: 16,
        children: [
          TTextButton.outlined(
            theme: theme,
            text: appLocalizations.cancel,
            containerPadding: Sizes.paddingV4H8,
            containerWidth: 72,
            onTap: () => Navigator.of(context).pop(),
          ),
          TTextButton(
            text: appLocalizations.confirm,
            containerPadding: Sizes.paddingV4H8,
            containerWidth: 72,
            onTap: () {
              // TODO: appLocalizations.confirm
            },
          ),
        ],
      ),
    ],
  );

  Future<void> _onPerformDrop(PerformDropEvent event) async {
    final newFiles = await event.session.readFiles();
    if (mounted && newFiles.isNotEmpty) {
      final bytes = await newFiles.first.readAll();
      _loadImage(bytes);
    }
  }

  void _loadImage(Uint8List bytes) {
    if (bytes.isEmpty) {
      return;
    }
    unawaited(
      _profileImageProvider?.evict(cache: PaintingBinding.instance.imageCache),
    );
    _profileImageProvider = MemoryImage(bytes);
    setState(() {});
  }
}

Future<void> showUserProfileImageEditorDialog(
  BuildContext context,
  User user,
) => showSimpleTDialog(
  routeName: '/user-profile-image-dialog',
  context: context,
  width: Sizes.userProfileImageDialogWidth,
  height: Sizes.userProfileImageDialogHeight,
  child: UserProfileImageEditorDialog(user: user),
);
