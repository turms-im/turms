import 'package:flutter/material.dart';

import '../../../themes/index.dart';

class TFocusTracker extends StatefulWidget {
  const TFocusTracker({Key? key, required this.child}) : super(key: key);

  final Widget child;

  @override
  State<TFocusTracker> createState() => _TFocusTrackerState();
}

class _TFocusTrackerState extends State<TFocusTracker> {
  static OverlayState? _overlayState;
  static OverlayEntry? _overlayEntry;

  @override
  void initState() {
    super.initState();
    FocusManager.instance.addListener(_onFocusChanged);
  }

  @override
  void dispose() {
    FocusManager.instance.removeListener(_onFocusChanged);
    _removeOverlayEntry();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => widget.child;

  void _removeOverlayEntry() {
    if (_overlayEntry case final overlayEntry?) {
      overlayEntry.remove();
      _overlayEntry = null;
    }
  }

  void _onFocusChanged() {
    final focus = FocusManager.instance.primaryFocus;
    if (focus == null || focus.context == null) {
      _removeOverlayEntry();
      return;
    }
    final rect = focus.rect;
    _removeOverlayEntry();
    _overlayState = Overlay.of(context);
    _overlayEntry = OverlayEntry(
        builder: (BuildContext context) =>
            _buildOverlayEntry(context, focus, rect));
    _overlayState!.insert(_overlayEntry!);
  }

  Widget _buildOverlayEntry(BuildContext context, FocusNode focus, Rect rect) {
    final parentFocusDebugLabel = focus.parent?.debugLabel ??
        focus.parent?.context?.widget.runtimeType.toString() ??
        '';
    final focusDebugLabel =
        focus.debugLabel ?? focus.context?.widget.runtimeType.toString() ?? '';
    return Positioned(
      left: rect.left,
      top: rect.top,
      child: IgnorePointer(
        child: UnconstrainedBox(
          child: Stack(
            children: [
              SizedBox(
                  width: rect.width,
                  height: rect.height,
                  child: DecoratedBox(
                    decoration: BoxDecoration(
                      borderRadius: Sizes.borderRadiusCircular4,
                      color: Colors.black.withValues(alpha: 0.3),
                    ),
                    child: const SizedBox.shrink(),
                  )),
              Positioned.fill(
                // The text rect size maybe larger than the box,
                // so we allow the text overflow.
                child: OverflowBox(
                  maxWidth: double.infinity,
                  maxHeight: double.infinity,
                  child: Center(
                    child: Text(
                        'Parent Focus: $parentFocusDebugLabel\nCurrent Focus: $focusDebugLabel',
                        style: Theme.of(context).textTheme.bodyMedium!.copyWith(
                            color: Colors.red, fontWeight: FontWeight.w700)),
                  ),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
