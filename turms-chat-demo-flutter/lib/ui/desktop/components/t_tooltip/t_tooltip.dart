// Copyright 2014 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

/// @docImport 'app.dart';
/// @docImport 'floating_action_button.dart';
/// @docImport 'icon_button.dart';
/// @docImport 'popup_menu.dart';
library;

import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import '../../../../infra/animation/animation_utils.dart';
import '../../../../infra/animation/dismissed_status_change_type.dart';
import '../../../themes/index.dart';

// Modified:
// * Disappear instead of keeping displaying when hovering.

class _ExclusiveMouseRegion extends MouseRegion {
  const _ExclusiveMouseRegion({super.onEnter, super.onExit, super.child});

  @override
  _RenderExclusiveMouseRegion createRenderObject(BuildContext context) =>
      _RenderExclusiveMouseRegion(onEnter: onEnter, onExit: onExit);
}

class _RenderExclusiveMouseRegion extends RenderMouseRegion {
  _RenderExclusiveMouseRegion({super.onEnter, super.onExit});

  static bool isOutermostMouseRegion = true;
  static bool foundInnermostMouseRegion = false;

  @override
  bool hitTest(BoxHitTestResult result, {required Offset position}) {
    var isHit = false;
    final outermost = isOutermostMouseRegion;
    isOutermostMouseRegion = false;
    if (size.contains(position)) {
      isHit =
          hitTestChildren(result, position: position) || hitTestSelf(position);
      if ((isHit || behavior == HitTestBehavior.translucent) &&
          !foundInnermostMouseRegion) {
        foundInnermostMouseRegion = true;
        result.add(BoxHitTestEntry(this, position));
      }
    }

    if (outermost) {
      // The outermost region resets the global states.
      isOutermostMouseRegion = true;
      foundInnermostMouseRegion = false;
    }
    return isHit;
  }
}

class TTooltip extends StatefulWidget {
  const TTooltip({
    super.key,
    this.message,
    this.richMessage,
    this.height,
    this.padding,
    this.margin,
    this.verticalOffset,
    this.preferBelow,
    this.excludeFromSemantics,
    this.decoration,
    this.textStyle,
    this.textAlign,
    this.waitDuration,
    this.showDuration,
    this.child,
  }) : assert(
         (message == null) != (richMessage == null),
         'Either `message` or `richMessage` must be specified',
       ),
       assert(
         richMessage == null || textStyle == null,
         'If `richMessage` is specified, `textStyle` will have no effect. '
         'If you wish to provide a `textStyle` for a rich tooltip, add the '
         '`textStyle` directly to the `richMessage` InlineSpan.',
       );

  /// The text to display in the tooltip.
  ///
  /// Only one of [message] and [richMessage] may be non-null.
  final String? message;

  /// The rich text to display in the tooltip.
  ///
  /// Only one of [message] and [richMessage] may be non-null.
  final InlineSpan? richMessage;

  /// The height of the tooltip's [child].
  ///
  /// If the [child] is null, then this is the tooltip's intrinsic height.
  final double? height;

  /// The amount of space by which to inset the tooltip's [child].
  ///
  /// On mobile, defaults to 16.0 logical pixels horizontally and 4.0 vertically.
  /// On desktop, defaults to 8.0 logical pixels horizontally and 4.0 vertically.
  final EdgeInsetsGeometry? padding;

  /// The empty space that surrounds the tooltip.
  ///
  /// Defines the tooltip's outer [Container.margin]. By default, a
  /// long tooltip will span the width of its window. If long enough,
  /// a tooltip might also span the window's height. This property allows
  /// one to define how much space the tooltip must be inset from the edges
  /// of their display window.
  ///
  /// If this property is null, then [TooltipThemeData.margin] is used.
  /// If [TooltipThemeData.margin] is also null, the default margin is
  /// 0.0 logical pixels on all sides.
  final EdgeInsetsGeometry? margin;

  /// The vertical gap between the widget and the displayed tooltip.
  ///
  /// When [preferBelow] is set to true and tooltips have sufficient space to
  /// display themselves, this property defines how much vertical space
  /// tooltips will position themselves under their corresponding widgets.
  /// Otherwise, tooltips will position themselves above their corresponding
  /// widgets with the given offset.
  final double? verticalOffset;

  /// Whether the tooltip defaults to being displayed below the widget.
  ///
  /// Defaults to true. If there is insufficient space to display the tooltip in
  /// the preferred direction, the tooltip will be displayed in the opposite
  /// direction.
  final bool? preferBelow;

  /// Whether the tooltip's [message] or [richMessage] should be excluded from
  /// the semantics tree.
  ///
  /// Defaults to false. A tooltip will add a [Semantics] label that is set to
  /// [TTooltip.message] if non-null, or the plain text value of
  /// [TTooltip.richMessage] otherwise. Set this property to true if the app is
  /// going to provide its own custom semantics label.
  final bool? excludeFromSemantics;

  /// The widget below this widget in the tree.
  ///
  /// {@macro flutter.widgets.ProxyWidget.child}
  final Widget? child;

  /// Specifies the tooltip's shape and background color.
  ///
  /// The tooltip shape defaults to a rounded rectangle with a border radius of
  /// 4.0. Tooltips will also default to an opacity of 90% and with the color
  /// [Colors.grey]\[700\] if [ThemeData.brightness] is [Brightness.dark], and
  /// [Colors.white] if it is [Brightness.light].
  final Decoration? decoration;

  /// The style to use for the message of the tooltip.
  ///
  /// If null, the message's [TextStyle] will be determined based on
  /// [ThemeData]. If [ThemeData.brightness] is set to [Brightness.dark],
  /// [TextTheme.bodyMedium] of [ThemeData.textTheme] will be used with
  /// [Colors.white]. Otherwise, if [ThemeData.brightness] is set to
  /// [Brightness.light], [TextTheme.bodyMedium] of [ThemeData.textTheme] will be
  /// used with [Colors.black].
  final TextStyle? textStyle;

  /// How the message of the tooltip is aligned horizontally.
  ///
  /// If this property is null, then [TooltipThemeData.textAlign] is used.
  /// If [TooltipThemeData.textAlign] is also null, the default value is
  /// [TextAlign.start].
  final TextAlign? textAlign;

  /// The length of time that a pointer must hover over a tooltip's widget
  /// before the tooltip will be shown.
  ///
  /// Defaults to 0 milliseconds (tooltips are shown immediately upon hover).
  final Duration? waitDuration;

  /// The length of time that the tooltip will be shown after a long press is
  /// released (if triggerMode is [TooltipTriggerMode.longPress]) or a tap is
  /// released (if triggerMode is [TooltipTriggerMode.tap]) or mouse pointer
  /// exits the widget.
  ///
  /// Defaults to 1.5 seconds for long press and tap released or 0.1 seconds
  /// for mouse pointer exits the widget.
  final Duration? showDuration;

  @override
  State<TTooltip> createState() => TTooltipState();
}

class TTooltipState extends State<TTooltip>
    with SingleTickerProviderStateMixin {
  static const double _defaultVerticalOffset = 24.0;
  static const bool _defaultPreferBelow = true;
  static const EdgeInsetsGeometry _defaultMargin = EdgeInsets.zero;
  static const Duration _fadeInDuration = Duration(milliseconds: 150);
  static const Duration _fadeOutDuration = Duration(milliseconds: 75);
  static const Duration _defaultShowDuration = Duration(milliseconds: 1500);
  static const Duration _defaultHoverShowDuration = Duration(milliseconds: 100);
  static const Duration _defaultWaitDuration = Duration.zero;
  static const bool _defaultExcludeFromSemantics = false;
  static const TextAlign _defaultTextAlign = TextAlign.start;

  final OverlayPortalController _overlayController = OverlayPortalController();

  // From InheritedWidgets
  late bool _visible;
  late TooltipThemeData _tooltipTheme;

  Duration get _hoverShowDuration =>
      widget.showDuration ??
      _tooltipTheme.showDuration ??
      _defaultHoverShowDuration;

  Duration get _waitDuration =>
      widget.waitDuration ?? _tooltipTheme.waitDuration ?? _defaultWaitDuration;

  /// The plain text message for this tooltip.
  ///
  /// This value will either come from [widget.message] or [widget.richMessage].
  String get _tooltipMessage =>
      widget.message ?? widget.richMessage!.toPlainText();

  Timer? _timer;
  AnimationController? _backingController;

  AnimationController get _controller =>
      _backingController ??= AnimationController(
        duration: _fadeInDuration,
        reverseDuration: _fadeOutDuration,
        vsync: this,
      )..addStatusListener(_handleStatusChanged);

  AnimationStatus _animationStatus = AnimationStatus.dismissed;

  void _handleStatusChanged(AnimationStatus status) {
    assert(mounted);
    switch (AnimationUtils.detectDismissedStatusChange(
      _animationStatus,
      status,
    )) {
      case DismissedStatusChangeType.becomeDismissed:
        _overlayController.hide();
      case DismissedStatusChangeType.becomeNotDismissed:
        _overlayController.show();
        SemanticsService.tooltip(_tooltipMessage);
      case DismissedStatusChangeType.noChange:
        break;
    }
    _animationStatus = status;
  }

  void _scheduleShowTooltip({required Duration withDelay}) {
    assert(mounted);
    void show() {
      assert(mounted);
      if (!_visible) {
        return;
      }
      _controller.forward();
      _timer?.cancel();
      _timer = null;
    }

    assert(
      !(_timer?.isActive ?? false) ||
          _controller.status != AnimationStatus.reverse,
      'timer must not be active when the tooltip is fading out',
    );
    switch (_controller.status) {
      case AnimationStatus.dismissed when withDelay.inMicroseconds > 0:
        _timer?.cancel();
        _timer = Timer(withDelay, show);
      // If the tooltip is already fading in or fully visible, skip the
      // animation and show the tooltip immediately.
      case AnimationStatus.dismissed:
      case AnimationStatus.forward:
      case AnimationStatus.reverse:
      case AnimationStatus.completed:
        show();
    }
  }

  void _scheduleDismissTooltip({required Duration withDelay}) {
    assert(mounted);
    assert(
      !(_timer?.isActive ?? false) ||
          _backingController?.status != AnimationStatus.reverse,
      'timer must not be active when the tooltip is fading out',
    );

    _timer?.cancel();
    _timer = null;
    // Use _backingController instead of _controller to prevent the lazy getter
    // from instaniating an AnimationController unnecessarily.
    switch (_backingController?.status) {
      case null:
      case AnimationStatus.reverse:
      case AnimationStatus.dismissed:
        break;
      // Dismiss when the tooltip is fading in: if there's a dismiss delay we'll
      // allow the fade in animation to continue until the delay timer fires.
      case AnimationStatus.forward:
      case AnimationStatus.completed:
        if (withDelay.inMicroseconds > 0) {
          _timer = Timer(withDelay, _controller.reverse);
        } else {
          _controller.reverse();
        }
    }
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    _visible = TooltipVisibility.of(context);
    _tooltipTheme = TooltipTheme.of(context);
  }

  // https://material.io/components/tooltips#specs
  double _getDefaultTooltipHeight() => switch (Theme.of(context).platform) {
    TargetPlatform.macOS ||
    TargetPlatform.linux ||
    TargetPlatform.windows => 24.0,
    TargetPlatform.android ||
    TargetPlatform.fuchsia ||
    TargetPlatform.iOS => 32.0,
  };

  EdgeInsets _getDefaultPadding() => switch (Theme.of(context).platform) {
    TargetPlatform.macOS || TargetPlatform.linux || TargetPlatform.windows =>
      const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
    TargetPlatform.android || TargetPlatform.fuchsia || TargetPlatform.iOS =>
      const EdgeInsets.symmetric(horizontal: 16.0, vertical: 4.0),
  };

  static double _getDefaultFontSize(TargetPlatform platform) =>
      switch (platform) {
        TargetPlatform.macOS ||
        TargetPlatform.linux ||
        TargetPlatform.windows => 12.0,
        TargetPlatform.android ||
        TargetPlatform.fuchsia ||
        TargetPlatform.iOS => 14.0,
      };

  Widget _buildTooltipOverlay(BuildContext context) {
    final overlayState = Overlay.of(context, debugRequiredFor: widget);
    final box = this.context.findRenderObject()! as RenderBox;
    final target = box.localToGlobal(
      box.size.center(Offset.zero),
      ancestor: overlayState.context.findRenderObject(),
    );

    final (
      TextStyle defaultTextStyle,
      BoxDecoration defaultDecoration,
    ) = switch (Theme.of(context)) {
      ThemeData(
        brightness: Brightness.dark,
        :final TextTheme textTheme,
        :final TargetPlatform platform,
      ) =>
        (
          textTheme.bodyMedium!.copyWith(
            color: Colors.black,
            fontSize: _getDefaultFontSize(platform),
          ),
          BoxDecoration(
            color: Colors.white.withValues(alpha: 0.9),
            borderRadius: Sizes.borderRadiusCircular4,
          ),
        ),
      ThemeData(
        brightness: Brightness.light,
        :final TextTheme textTheme,
        :final TargetPlatform platform,
      ) =>
        (
          textTheme.bodyMedium!.copyWith(
            color: Colors.white,
            fontSize: _getDefaultFontSize(platform),
          ),
          BoxDecoration(
            color: Colors.grey.shade700.withValues(alpha: 0.9),
            borderRadius: Sizes.borderRadiusCircular4,
          ),
        ),
    };

    final tooltipTheme = _tooltipTheme;
    final overlayChild = _TooltipOverlay(
      richMessage: widget.richMessage ?? TextSpan(text: widget.message),
      height:
          widget.height ??
          tooltipTheme.constraints?.minHeight ??
          _getDefaultTooltipHeight(),
      padding: widget.padding ?? tooltipTheme.padding ?? _getDefaultPadding(),
      margin: widget.margin ?? tooltipTheme.margin ?? _defaultMargin,
      decoration:
          widget.decoration ?? tooltipTheme.decoration ?? defaultDecoration,
      textStyle: widget.textStyle ?? tooltipTheme.textStyle ?? defaultTextStyle,
      textAlign:
          widget.textAlign ?? tooltipTheme.textAlign ?? _defaultTextAlign,
      animation: CurvedAnimation(
        parent: _controller,
        curve: Curves.fastOutSlowIn,
      ),
      target: target,
      verticalOffset:
          widget.verticalOffset ??
          tooltipTheme.verticalOffset ??
          _defaultVerticalOffset,
      preferBelow:
          widget.preferBelow ?? tooltipTheme.preferBelow ?? _defaultPreferBelow,
    );

    return SelectionContainer.maybeOf(context) == null
        ? overlayChild
        : SelectionContainer.disabled(child: overlayChild);
  }

  @override
  void dispose() {
    // _overlayController.hide();
    _timer?.cancel();
    _backingController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if (_tooltipMessage.isEmpty) {
      return widget.child ?? const SizedBox.shrink();
    }
    assert(debugCheckHasOverlay(context));
    final excludeFromSemantics =
        widget.excludeFromSemantics ??
        _tooltipTheme.excludeFromSemantics ??
        _defaultExcludeFromSemantics;
    Widget result = Semantics(
      tooltip: excludeFromSemantics ? null : _tooltipMessage,
      child: widget.child,
    );

    // Only check for gestures if tooltip should be visible.
    if (_visible) {
      result = _ExclusiveMouseRegion(
        onEnter: (_) {
          _scheduleShowTooltip(withDelay: _waitDuration);
        },
        onExit: (_) {
          _scheduleDismissTooltip(withDelay: _hoverShowDuration);
        },
        child: Listener(
          onPointerDown: (_) {
            _scheduleDismissTooltip(withDelay: _hoverShowDuration);
          },
          child: result,
        ),
      );
    }
    return OverlayPortal(
      controller: _overlayController,
      overlayChildBuilder: _buildTooltipOverlay,
      child: result,
    );
  }
}

/// A delegate for computing the layout of a tooltip to be displayed above or
/// below a target specified in the global coordinate system.
class TTooltipPositionDelegate extends SingleChildLayoutDelegate {
  /// Creates a delegate for computing the layout of a tooltip.
  ///
  /// The arguments must not be null.
  TTooltipPositionDelegate({
    required this.target,
    required this.verticalOffset,
    required this.preferBelow,
  });

  /// The offset of the target the tooltip is positioned near in the global
  /// coordinate system.
  final Offset target;

  /// The amount of vertical distance between the target and the displayed
  /// tooltip.
  final double verticalOffset;

  /// Whether the tooltip is displayed below its widget by default.
  ///
  /// If there is insufficient space to display the tooltip in the preferred
  /// direction, the tooltip will be displayed in the opposite direction.
  final bool preferBelow;

  @override
  BoxConstraints getConstraintsForChild(BoxConstraints constraints) =>
      constraints.loosen();

  @override
  Offset getPositionForChild(Size size, Size childSize) => positionDependentBox(
    size: size,
    childSize: childSize,
    target: target,
    verticalOffset: verticalOffset,
    preferBelow: preferBelow,
  );

  @override
  bool shouldRelayout(TTooltipPositionDelegate oldDelegate) =>
      target != oldDelegate.target ||
      verticalOffset != oldDelegate.verticalOffset ||
      preferBelow != oldDelegate.preferBelow;
}

class _TooltipOverlay extends StatelessWidget {
  const _TooltipOverlay({
    required this.height,
    required this.richMessage,
    this.padding,
    this.margin,
    this.decoration,
    this.textStyle,
    this.textAlign,
    required this.animation,
    required this.target,
    required this.verticalOffset,
    required this.preferBelow,
  });

  final InlineSpan richMessage;
  final double height;
  final EdgeInsetsGeometry? padding;
  final EdgeInsetsGeometry? margin;
  final Decoration? decoration;
  final TextStyle? textStyle;
  final TextAlign? textAlign;
  final Animation<double> animation;
  final Offset target;
  final double verticalOffset;
  final bool preferBelow;

  @override
  Widget build(BuildContext context) => Positioned.fill(
    bottom: MediaQuery.maybeViewInsetsOf(context)?.bottom ?? 0.0,
    child: CustomSingleChildLayout(
      delegate: TTooltipPositionDelegate(
        target: target,
        verticalOffset: verticalOffset,
        preferBelow: preferBelow,
      ),
      child: FadeTransition(
        opacity: animation,
        child: ConstrainedBox(
          constraints: BoxConstraints(minHeight: height),
          child: DefaultTextStyle(
            style: Theme.of(context).textTheme.bodyMedium!,
            child: Container(
              decoration: decoration,
              padding: padding,
              margin: margin,
              child: Center(
                widthFactor: 1.0,
                heightFactor: 1.0,
                child: Text.rich(
                  richMessage,
                  style: textStyle,
                  textAlign: textAlign,
                ),
              ),
            ),
          ),
        ),
      ),
    ),
  );
}
