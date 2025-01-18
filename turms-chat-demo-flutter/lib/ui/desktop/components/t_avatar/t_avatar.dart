import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../infra/math/math_utils.dart';
import '../../../l10n/app_localizations.dart';
import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/index.dart';
import '../index.dart';

/// The colors are copied from [Colors.primaries] but excluding gray colors.
const List<Color> _colors = [
  Colors.red,
  Colors.pink,
  Colors.purple,
  Colors.deepPurple,
  Colors.indigo,
  Colors.blue,
  Colors.lightBlue,
  Colors.cyan,
  Colors.teal,
  Colors.green,
  Colors.lightGreen,
  Colors.lime,
  // Colors.yellow,
  Colors.amber,
  Colors.orange,
  Colors.deepOrange,
  Colors.brown,
];

final _colorCount = _colors.length;

const _presenceBoxDiameter = 14.0;
const _prefixIconDimension = 12.0;

class TAvatar extends ConsumerWidget {
  TAvatar({
    super.key,
    required this.id,
    required this.name,
    this.image,
    this.icon,
    this.size = TAvatarSize.medium,
    this.textSize,
    this.presence = UserPresence.none,
    this.onPresenceSelected,
    this.presencePopupOffset,
  }) : firstChar = name.isEmpty ? '' : name.substring(0, 1);

  static const borderRadius = Sizes.borderRadiusCircular4;

  /// This can be any ID (e.g. user ID, group ID).
  final Int64 id;
  final String name;
  final String firstChar;
  final ImageProvider? image;
  final IconData? icon;
  final TAvatarSize size;
  final double? textSize;
  final UserPresence presence;
  final ValueChanged<UserPresence>? onPresenceSelected;
  final Offset? presencePopupOffset;

  /// Use oval instead of rounded rect so that
  /// the user presence indicator can display nicely with the avatar.
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final avatar = ClipRRect(
      borderRadius: borderRadius,
      child: _buildAvatar(context.theme),
    );
    if (presence == UserPresence.none) {
      return avatar;
    }
    return Stack(
        clipBehavior: Clip.none,
        children: [avatar, _buildPresence(appLocalizations)]);
  }

  Widget _buildAvatar(ThemeData theme) {
    final containerSize = size.containerSize;
    return SizedBox(
      width: containerSize,
      height: containerSize,
      child: _buildContent(image, theme, theme.appThemeExtension),
    );
  }

  Widget _buildContent(ImageProvider<Object>? img, ThemeData theme,
      AppThemeExtension appThemeExtension) {
    if (null != img) {
      // FittedBox is used as a fallback in case the image is not fitted.
      return FittedBox(child: Image(image: img));
    }
    if (null != icon) {
      return ColoredBox(
          color: theme.primaryColor,
          child: Icon(
            icon,
            fill: 1,
            color: Colors.white,
            size: size.iconSize,
          ));
    }
    if (name.isEmpty) {
      return ColoredBox(
        color: appThemeExtension.avatarBackgroundColor,
        child: Icon(Symbols.person_rounded,
            fill: 1,
            color: appThemeExtension.avatarIconColor,
            // The "person" icon seems smaller than other icons,
            // so we need to enlarge it.
            size: size.iconSize * 1.25),
      );
    }
    return ColoredBox(
      color: _pickColor(id),
      child: Center(
        child: Text(
          firstChar,
          textScaler: TextScaler.noScaling,
          style: TextStyle(
              fontSize: textSize ?? size.textSize, color: Colors.white),
        ),
      ),
    );
  }

  Color _pickColor(Int64 userId) => _colors[(userId % _colorCount).toInt()];

  Widget _buildPresence(AppLocalizations appLocalizations) {
    Widget content = SizedBox(
      width: _presenceBoxDiameter,
      height: _presenceBoxDiameter,
      child: CustomPaint(
        painter: _TAvatarUserPresencePainter(presence),
      ),
    );
    if (onPresenceSelected case final onPresenceSelected?) {
      content = TMenuPopup(
        anchor: content,
        targetAnchor: Alignment.topRight,
        followerAnchor: Alignment.topLeft,
        constrainFollowerWithTargetWidth: false,
        offset: presencePopupOffset ?? Offset.zero,
        padding: Sizes.paddingV8H16,
        entries: [
          TMenuEntry(
            value: UserPresence.available,
            label: appLocalizations.userPresenceAvailable,
            prefix: _buildMenuEntryPrefix(UserPresence.available),
          ),
          TMenuEntry(
            value: UserPresence.busy,
            label: appLocalizations.userPresenceBusy,
            prefix: _buildMenuEntryPrefix(UserPresence.busy),
          ),
          TMenuEntry(
            value: UserPresence.doNotDisturb,
            label: appLocalizations.userPresenceDoNotDisturb,
            prefix: _buildMenuEntryPrefix(UserPresence.doNotDisturb),
          ),
          TMenuEntry(
            value: UserPresence.away,
            label: appLocalizations.userPresenceAway,
            prefix: _buildMenuEntryPrefix(UserPresence.away),
          ),
          TMenuEntry(
            value: UserPresence.appearOffline,
            label: appLocalizations.userPresenceAppearOffline,
            prefix: _buildMenuEntryPrefix(UserPresence.appearOffline),
          ),
        ],
        onSelected: (item) {
          onPresenceSelected(item.value!);
        },
      );
    }
    return Positioned(
      child: content,
      right: 0,
      bottom: 0,
    );
  }

  SizedBox _buildMenuEntryPrefix(UserPresence presence) => SizedBox(
        width: _prefixIconDimension,
        height: _prefixIconDimension,
        child: CustomPaint(
          painter: _TAvatarUserPresencePainter(presence, hasBorder: false),
        ),
      );
}

enum UserPresence {
  none,
  available,
  away,
  busy,
  doNotDisturb,
  appearOffline,
  offline
}

const _padding = 1.0;
const _clockPointDistanceFromEdge = 3.0;
final _clockPoint = MathUtils.calculatePoint(
    _presenceBoxDiameter / 2,
    _presenceBoxDiameter / 2,
    _presenceBoxDiameter / 2,
    _clockPointDistanceFromEdge,
    45);

class _TAvatarUserPresencePainter extends CustomPainter {
  const _TAvatarUserPresencePainter(this.presence, {this.hasBorder = true});

  final UserPresence presence;
  final bool hasBorder;

  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint();

    final centerX = size.width / 2;
    final centerY = size.height / 2;
    if (hasBorder) {
      canvas.drawCircle(
          Offset(centerX, centerY),
          centerX,
          paint
            ..color = Colors.white
            ..style = PaintingStyle.fill);
    }
    switch (presence) {
      case UserPresence.available:
        canvas.drawCircle(
            Offset(centerX, centerY),
            centerX - _padding,
            paint
              ..color = Colors.green
              ..style = PaintingStyle.fill);
        break;
      case UserPresence.away:
        canvas
          ..drawCircle(Offset(centerX, centerY), centerX - _padding,
              paint..color = Colors.orangeAccent)
          ..drawPath(
              Path()
                ..moveTo(centerX, _clockPointDistanceFromEdge)
                ..lineTo(centerX, centerY)
                ..lineTo(_clockPoint.x, _clockPoint.y),
              paint
                ..color = Colors.white
                ..style = PaintingStyle.stroke
                ..strokeWidth = 1);
        break;
      case UserPresence.busy:
        canvas.drawCircle(Offset(centerX, centerY), centerX - _padding,
            paint..color = Colors.red);
        break;
      case UserPresence.doNotDisturb:
        final padding = size.width / 3.5;
        canvas
          ..drawCircle(Offset(centerX, centerY), centerX - _padding,
              paint..color = Colors.red)
          ..drawPath(
              Path()
                ..moveTo(padding, centerY)
                ..lineTo(size.width - padding, centerY),
              paint
                ..color = Colors.white
                ..style = PaintingStyle.stroke
                ..strokeWidth = 1);
        break;
      case UserPresence.appearOffline:
      case UserPresence.offline:
        final padding = size.width / 6;
        canvas
          ..drawCircle(
              Offset(centerX, centerY),
              centerX - _padding,
              paint
                ..color = Colors.grey.shade600
                ..style = PaintingStyle.fill)
          ..drawCircle(Offset(centerX, centerY), centerX - _padding * 2,
              paint..color = Colors.white)
          ..drawPath(
              Path()
                ..moveTo(centerX - padding, centerY - padding)
                ..lineTo(centerX + padding, centerY + padding)
                ..moveTo(centerX - padding, centerY + padding)
                ..lineTo(centerX + padding, centerY - padding),
              paint
                ..color = Colors.grey.shade600
                ..style = PaintingStyle.stroke
                ..strokeWidth = 1);
        break;
      case UserPresence.none:
        throw ArgumentError('presence must be set');
    }
  }

  @override
  bool shouldRepaint(_TAvatarUserPresencePainter oldDelegate) =>
      oldDelegate.presence != presence;
}

enum TAvatarSize {
  small(30),
  medium(40),
  large(60);

  const TAvatarSize(this.containerSize)
      : textSize = containerSize * 0.5,
        iconSize = containerSize * 0.75;

  final double containerSize;
  final double textSize;
  final double iconSize;
}
