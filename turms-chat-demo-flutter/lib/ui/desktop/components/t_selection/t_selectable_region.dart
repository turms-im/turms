// Copyright 2014 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'dart:async';
import 'dart:math';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter/services.dart';

// Examples can assume:
// FocusNode _focusNode = FocusNode();
// late GlobalKey key;

const Set<PointerDeviceKind> _kLongPressSelectionDevices = <PointerDeviceKind>{
  PointerDeviceKind.touch,
  PointerDeviceKind.stylus,
  PointerDeviceKind.invertedStylus,
};

// In practice some selectables like widgetspan shift several pixels. So when
// the vertical position diff is within the threshold, compare the horizontal
// position to make the compareScreenOrder function more robust.
const double _kSelectableVerticalComparingThreshold = 3.0;

/// A widget that introduces an area for user selections.
///
/// Flutter widgets are not selectable by default. Wrapping a widget subtree
/// with a [TSelectableRegion] widget enables selection within that subtree (for
/// example, [Text] widgets automatically look for selectable regions to enable
/// selection). The wrapped subtree can be selected by users using mouse or
/// touch gestures, e.g. users can select widgets by holding the mouse
/// left-click and dragging across widgets, or they can use long press gestures
/// to select words on touch devices.
///
/// A [TSelectableRegion] widget requires configuration; in particular specific
/// [selectionControls] must be provided.
///
/// The [SelectionArea] widget from the [material] library configures a
/// [TSelectableRegion] in a platform-specific manner (e.g. using a Material
/// toolbar on Android, a Cupertino toolbar on iOS), and it may therefore be
/// simpler to use that widget rather than using [TSelectableRegion] directly.
///
/// ## An overview of the selection system.
///
/// Every [Selectable] under the [TSelectableRegion] can be selected. They form a
/// selection tree structure to handle the selection.
///
/// The [TSelectableRegion] is a wrapper over [SelectionContainer]. It listens to
/// user gestures and sends corresponding [SelectionEvent]s to the
/// [SelectionContainer] it creates.
///
/// A [SelectionContainer] is a single [Selectable] that handles
/// [SelectionEvent]s on behalf of child [Selectable]s in the subtree. It
/// creates a [SelectionRegistrarScope] with its [SelectionContainer.delegate]
/// to collect child [Selectable]s and sends the [SelectionEvent]s it receives
/// from the parent [SelectionRegistrar] to the appropriate child [Selectable]s.
/// It creates an abstraction for the parent [SelectionRegistrar] as if it is
/// interacting with a single [Selectable].
///
/// The [SelectionContainer] created by [TSelectableRegion] is the root node of a
/// selection tree. Each non-leaf node in the tree is a [SelectionContainer],
/// and the leaf node is a leaf widget whose render object implements
/// [Selectable]. They are connected through [SelectionRegistrarScope]s created
/// by [SelectionContainer]s.
///
/// Both [SelectionContainer]s and the leaf [Selectable]s need to register
/// themselves to the [SelectionRegistrar] from the
/// [SelectionContainer.maybeOf] if they want to participate in the
/// selection.
///
/// An example selection tree will look like:
///
/// {@tool snippet}
///
/// ```dart
/// MaterialApp(
///   home: SelectableRegion(
///     selectionControls: materialTextSelectionControls,
///     focusNode: _focusNode, // initialized to FocusNode()
///     child: Scaffold(
///       appBar: AppBar(title: const Text('Flutter Code Sample')),
///       body: ListView(
///         children: const <Widget>[
///           Text('Item 0', style: TextStyle(fontSize: 50.0)),
///           Text('Item 1', style: TextStyle(fontSize: 50.0)),
///         ],
///       ),
///     ),
///   ),
/// )
/// ```
/// {@end-tool}
///
///
///               SelectionContainer
///               (SelectableRegion)
///                  /         \
///                 /           \
///                /             \
///           Selectable          \
///      ("Flutter Code Sample")   \
///                                 \
///                          SelectionContainer
///                              (ListView)
///                              /       \
///                             /         \
///                            /           \
///                     Selectable        Selectable
///                     ("Item 0")         ("Item 1")
///
///
/// ## Making a widget selectable
///
/// Some leaf widgets, such as [Text], have all of the selection logic wired up
/// automatically and can be selected as long as they are under a
/// [TSelectableRegion].
///
/// To make a custom selectable widget, its render object needs to mix in
/// [Selectable] and implement the required APIs to handle [SelectionEvent]s
/// as well as paint appropriate selection highlights.
///
/// The render object also needs to register itself to a [SelectionRegistrar].
/// For the most cases, one can use [SelectionRegistrant] to auto-register
/// itself with the register returned from [SelectionContainer.maybeOf] as
/// seen in the example below.
///
/// {@tool dartpad}
/// This sample demonstrates how to create an adapter widget that makes any
/// child widget selectable.
///
/// ** See code in examples/api/lib/material/selectable_region/selectable_region.0.dart **
/// {@end-tool}
///
/// ## Complex layout
///
/// By default, the screen order is used as the selection order. If a group of
/// [Selectable]s needs to select differently, consider wrapping them with a
/// [SelectionContainer] to customize its selection behavior.
///
/// {@tool dartpad}
/// This sample demonstrates how to create a [SelectionContainer] that only
/// allows selecting everything or nothing with no partial selection.
///
/// ** See code in examples/api/lib/material/selection_container/selection_container.0.dart **
/// {@end-tool}
///
/// In the case where a group of widgets should be excluded from selection under
/// a [TSelectableRegion], consider wrapping that group of widgets using
/// [SelectionContainer.disabled].
///
/// {@tool dartpad}
/// This sample demonstrates how to disable selection for a Text in a Column.
///
/// ** See code in examples/api/lib/material/selection_container/selection_container_disabled.0.dart **
/// {@end-tool}
///
/// To create a separate selection system from its parent selection area,
/// wrap part of the subtree with another [TSelectableRegion]. The selection of the
/// child selection area can not extend past its subtree, and the selection of
/// the parent selection area can not extend inside the child selection area.
///
/// ## Tests
///
/// In a test, a region can be selected either by faking drag events (e.g. using
/// [WidgetTester.dragFrom]) or by sending intents to a widget inside the region
/// that has been given a [GlobalKey], e.g.:
///
/// ```dart
/// Actions.invoke(key.currentContext!, const SelectAllTextIntent(SelectionChangedCause.keyboard));
/// ```
///
/// See also:
///
///  * [SelectionArea], which creates a [TSelectableRegion] with
///    platform-adaptive selection controls.
///  * [SelectableText], which enables selection on a single run of text.
///  * [SelectionHandler], which contains APIs to handle selection events from the
///    [TSelectableRegion].
///  * [Selectable], which provides API to participate in the selection system.
///  * [SelectionRegistrar], which [Selectable] needs to subscribe to receive
///    selection events.
///  * [SelectionContainer], which collects selectable widgets in the subtree
///    and provides api to dispatch selection event to the collected widget.
class TSelectableRegion extends StatefulWidget {
  /// Create a new [TSelectableRegion] widget.
  ///
  /// The [selectionControls] are used for building the selection handles and
  /// toolbar for mobile devices.
  const TSelectableRegion({
    super.key,
    this.controller,
    this.contextMenuBuilder,
    required this.focusNode,
    required this.selectionControls,
    required this.child,
    this.magnifierConfiguration = TextMagnifierConfiguration.disabled,
    this.onSelectionChanged,
  });

  final TSelectableRegionController? controller;

  /// The configuration for the magnifier used with selections in this region.
  ///
  /// By default, [TSelectableRegion]'s [TextMagnifierConfiguration] is disabled.
  /// For a version of [TSelectableRegion] that adapts automatically to the
  /// current platform, consider [SelectionArea].
  ///
  /// {@macro flutter.widgets.magnifier.intro}
  final TextMagnifierConfiguration magnifierConfiguration;

  /// {@macro flutter.widgets.Focus.focusNode}
  final FocusNode focusNode;

  /// The child widget this selection area applies to.
  ///
  /// {@macro flutter.widgets.ProxyWidget.child}
  final Widget child;

  /// {@macro flutter.widgets.EditableText.contextMenuBuilder}
  final TSelectableRegionContextMenuBuilder? contextMenuBuilder;

  /// The delegate to build the selection handles and toolbar for mobile
  /// devices.
  ///
  /// The [emptyTextSelectionControls] global variable provides a default
  /// [TextSelectionControls] implementation with no controls.
  final TextSelectionControls selectionControls;

  /// Called when the selected content changes.
  final ValueChanged<SelectedContent?>? onSelectionChanged;

  /// Returns the [ContextMenuButtonItem]s representing the buttons in this
  /// platform's default selection menu.
  ///
  /// For example, [TSelectableRegion] uses this to generate the default buttons
  /// for its context menu.
  ///
  /// See also:
  ///
  /// * [TSelectableRegionState.contextMenuButtonItems], which gives the
  ///   [ContextMenuButtonItem]s for a specific SelectableRegion.
  /// * [EditableText.getEditableButtonItems], which performs a similar role but
  ///   for content that is both selectable and editable.
  /// * [AdaptiveTextSelectionToolbar], which builds the toolbar itself, and can
  ///   take a list of [ContextMenuButtonItem]s with
  ///   [AdaptiveTextSelectionToolbar.buttonItems].
  /// * [AdaptiveTextSelectionToolbar.getAdaptiveButtons], which builds the button
  ///   Widgets for the current platform given [ContextMenuButtonItem]s.
  static List<ContextMenuButtonItem> getSelectableButtonItems({
    required final SelectionGeometry selectionGeometry,
    required final VoidCallback onCopy,
    required final VoidCallback onSelectAll,
    required final VoidCallback? onShare,
  }) {
    final canCopy = selectionGeometry.status == SelectionStatus.uncollapsed;
    final canSelectAll = selectionGeometry.hasContent;
    final platformCanShare = switch (defaultTargetPlatform) {
      TargetPlatform.android =>
        selectionGeometry.status == SelectionStatus.uncollapsed,
      TargetPlatform.macOS ||
      TargetPlatform.fuchsia ||
      TargetPlatform.linux ||
      TargetPlatform.windows => false,
      // TODO(bleroux): the share button should be shown on iOS but the share
      // functionality requires some changes on the engine side because, on iPad,
      // it needs an anchor for the popup.
      // See: https://github.com/flutter/flutter/issues/141775.
      TargetPlatform.iOS => false,
    };
    final canShare = onShare != null && platformCanShare;

    // On Android, the share button is before the select all button.
    final showShareBeforeSelectAll =
        defaultTargetPlatform == TargetPlatform.android;

    // Determine which buttons will appear so that the order and total number is
    // known. A button's position in the menu can slightly affect its
    // appearance.
    return <ContextMenuButtonItem>[
      if (canCopy)
        ContextMenuButtonItem(
          onPressed: onCopy,
          type: ContextMenuButtonType.copy,
        ),
      if (canShare && showShareBeforeSelectAll)
        ContextMenuButtonItem(
          onPressed: onShare,
          type: ContextMenuButtonType.share,
        ),
      if (canSelectAll)
        ContextMenuButtonItem(
          onPressed: onSelectAll,
          type: ContextMenuButtonType.selectAll,
        ),
      if (canShare && !showShareBeforeSelectAll)
        ContextMenuButtonItem(
          onPressed: onShare,
          type: ContextMenuButtonType.share,
        ),
    ];
  }

  @override
  State<StatefulWidget> createState() => TSelectableRegionState();
}

/// State for a [TSelectableRegion].
class TSelectableRegionState extends State<TSelectableRegion>
    with TextSelectionDelegate
    implements SelectionRegistrar {
  late final Map<Type, Action<Intent>> _actions = <Type, Action<Intent>>{
    // SelectAllTextIntent: _makeOverridable(_SelectAllAction(this)),
    CopySelectionTextIntent: _makeOverridable(_CopySelectionAction(this)),
    ExtendSelectionToNextWordBoundaryOrCaretLocationIntent: _makeOverridable(
      _GranularlyExtendSelectionAction<
        ExtendSelectionToNextWordBoundaryOrCaretLocationIntent
      >(this, granularity: TextGranularity.word),
    ),
    ExpandSelectionToDocumentBoundaryIntent: _makeOverridable(
      _GranularlyExtendSelectionAction<ExpandSelectionToDocumentBoundaryIntent>(
        this,
        granularity: TextGranularity.document,
      ),
    ),
    ExpandSelectionToLineBreakIntent: _makeOverridable(
      _GranularlyExtendSelectionAction<ExpandSelectionToLineBreakIntent>(
        this,
        granularity: TextGranularity.line,
      ),
    ),
    ExtendSelectionByCharacterIntent: _makeOverridable(
      _GranularlyExtendCaretSelectionAction<ExtendSelectionByCharacterIntent>(
        this,
        granularity: TextGranularity.character,
      ),
    ),
    ExtendSelectionToNextWordBoundaryIntent: _makeOverridable(
      _GranularlyExtendCaretSelectionAction<
        ExtendSelectionToNextWordBoundaryIntent
      >(this, granularity: TextGranularity.word),
    ),
    ExtendSelectionToLineBreakIntent: _makeOverridable(
      _GranularlyExtendCaretSelectionAction<ExtendSelectionToLineBreakIntent>(
        this,
        granularity: TextGranularity.line,
      ),
    ),
    ExtendSelectionVerticallyToAdjacentLineIntent: _makeOverridable(
      _DirectionallyExtendCaretSelectionAction<
        ExtendSelectionVerticallyToAdjacentLineIntent
      >(this),
    ),
    ExtendSelectionToDocumentBoundaryIntent: _makeOverridable(
      _GranularlyExtendCaretSelectionAction<
        ExtendSelectionToDocumentBoundaryIntent
      >(this, granularity: TextGranularity.document),
    ),
  };

  final Map<Type, GestureRecognizerFactory> _gestureRecognizers =
      <Type, GestureRecognizerFactory>{};
  SelectionOverlay? _selectionOverlay;
  final LayerLink _startHandleLayerLink = LayerLink();
  final LayerLink _endHandleLayerLink = LayerLink();
  final LayerLink _toolbarLayerLink = LayerLink();
  final _SelectableRegionContainerDelegate _selectionDelegate =
      _SelectableRegionContainerDelegate();

  // there should only ever be one selectable, which is the SelectionContainer.
  Selectable? _selectable;

  bool get _hasSelectionOverlayGeometry =>
      _selectionDelegate.value.startSelectionPoint != null ||
      _selectionDelegate.value.endSelectionPoint != null;

  Orientation? _lastOrientation;
  SelectedContent? _lastSelectedContent;

  /// The [SelectionOverlay] that is currently visible on the screen.
  ///
  /// Can be null if there is no visible [SelectionOverlay].
  @visibleForTesting
  SelectionOverlay? get selectionOverlay => _selectionOverlay;

  /// The text processing service used to retrieve the native text processing actions.
  final ProcessTextService _processTextService = DefaultProcessTextService();

  /// The list of native text processing actions provided by the engine.
  final List<ProcessTextAction> _processTextActions = <ProcessTextAction>[];

  @override
  void initState() {
    super.initState();
    widget.controller?.hideContextMenu = _hideContextMenu;
    widget.focusNode.addListener(_handleFocusChanged);
    _initMouseGestureRecognizer();
    _initTouchGestureRecognizer();
    // Right clicks.
    _gestureRecognizers[TapGestureRecognizer] =
        GestureRecognizerFactoryWithHandlers<TapGestureRecognizer>(
          () => TapGestureRecognizer(debugOwner: this),
          (TapGestureRecognizer instance) {
            instance.onSecondaryTapDown = _handleRightClickDown;
          },
        );
    _initProcessTextActions();
  }

  /// Query the engine to initialize the list of text processing actions to show
  /// in the text selection toolbar.
  Future<void> _initProcessTextActions() async {
    _processTextActions.clear();
    _processTextActions.addAll(await _processTextService.queryTextActions());
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
      case TargetPlatform.iOS:
        break;
      case TargetPlatform.fuchsia:
      case TargetPlatform.linux:
      case TargetPlatform.macOS:
      case TargetPlatform.windows:
        return;
    }

    // Hide the text selection toolbar on mobile when orientation changes.
    final orientation = MediaQuery.orientationOf(context);
    if (_lastOrientation == null) {
      _lastOrientation = orientation;
      return;
    }
    if (orientation != _lastOrientation) {
      _lastOrientation = orientation;
      hideToolbar(defaultTargetPlatform == TargetPlatform.android);
    }
  }

  @override
  void didUpdateWidget(TSelectableRegion oldWidget) {
    super.didUpdateWidget(oldWidget);
    widget.controller?.hideContextMenu = _hideContextMenu;
    if (widget.focusNode != oldWidget.focusNode) {
      oldWidget.focusNode.removeListener(_handleFocusChanged);
      widget.focusNode.addListener(_handleFocusChanged);
      if (widget.focusNode.hasFocus != oldWidget.focusNode.hasFocus) {
        _handleFocusChanged();
      }
    }
  }

  void _hideContextMenu() {
    _selectionOverlay?.hide();
  }

  Action<T> _makeOverridable<T extends Intent>(Action<T> defaultAction) =>
      Action<T>.overridable(context: context, defaultAction: defaultAction);

  void _handleFocusChanged() {
    if (!widget.focusNode.hasFocus) {
      if (kIsWeb) {
        PlatformSelectableRegionContextMenu.detach(_selectionDelegate);
      }
      if (SchedulerBinding.instance.lifecycleState ==
          AppLifecycleState.resumed) {
        // We should only clear the selection when this SelectableRegion loses
        // focus while the application is currently running. It is possible
        // that the application is not currently running, for example on desktop
        // platforms, clicking on a different window switches the focus to
        // the new window causing the Flutter application to go inactive. In this
        // case we want to retain the selection so it remains when we return to
        // the Flutter application.
        clearSelection();
      }
    }
    if (kIsWeb) {
      PlatformSelectableRegionContextMenu.attach(_selectionDelegate);
    }
  }

  void _updateSelectionStatus() {
    final geometry = _selectionDelegate.value;
    final selection = switch (geometry.status) {
      SelectionStatus.uncollapsed || SelectionStatus.collapsed =>
        const TextSelection(baseOffset: 0, extentOffset: 1),
      SelectionStatus.none => const TextSelection.collapsed(offset: 1),
    };
    textEditingValue = TextEditingValue(text: '__', selection: selection);
    if (_hasSelectionOverlayGeometry) {
      _updateSelectionOverlay();
    } else {
      _selectionOverlay?.dispose();
      _selectionOverlay = null;
    }
  }

  // gestures.

  /// Whether the Shift key was pressed when the most recent [PointerDownEvent]
  /// was tracked by the [BaseTapAndDragGestureRecognizer].
  bool _isShiftPressed = false;

  // The position of the most recent secondary tap down event on this
  // SelectableRegion.
  Offset? _lastSecondaryTapDownPosition;

  // The device kind for the pointer of the most recent tap down event on this
  // SelectableRegion.
  PointerDeviceKind? _lastPointerDeviceKind;

  static bool _isPrecisePointerDevice(PointerDeviceKind pointerDeviceKind) {
    switch (pointerDeviceKind) {
      case PointerDeviceKind.mouse:
        return true;
      case PointerDeviceKind.trackpad:
      case PointerDeviceKind.stylus:
      case PointerDeviceKind.invertedStylus:
      case PointerDeviceKind.touch:
      case PointerDeviceKind.unknown:
        return false;
    }
  }

  // Converts the details.consecutiveTapCount from a TapAndDrag*Details object,
  // which can grow to be infinitely large, to a value between 1 and the supported
  // max consecutive tap count. The value that the raw count is converted to is
  // based on the default observed behavior on the native platforms.
  //
  // This method should be used in all instances when details.consecutiveTapCount
  // would be used.
  int _getEffectiveConsecutiveTapCount(int rawCount) {
    var maxConsecutiveTap = 3;
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
      case TargetPlatform.fuchsia:
        if (_lastPointerDeviceKind != null &&
            _lastPointerDeviceKind != PointerDeviceKind.mouse) {
          // When the pointer device kind is not precise like a mouse, native
          // Android resets the tap count at 2. For example, this is so the
          // selection can collapse on the third tap.
          maxConsecutiveTap = 2;
        }
        // From observation, these platforms reset their tap count to 0 when
        // the number of consecutive taps exceeds the max consecutive tap supported.
        // For example on native Android, when going past a triple click,
        // on the fourth click the selection is moved to the precise click
        // position, on the fifth click the word at the position is selected, and
        // on the sixth click the paragraph at the position is selected.
        return rawCount <= maxConsecutiveTap
            ? rawCount
            : (rawCount % maxConsecutiveTap == 0
                  ? maxConsecutiveTap
                  : rawCount % maxConsecutiveTap);
      case TargetPlatform.linux:
        // From observation, these platforms reset their tap count to 0 when
        // the number of consecutive taps exceeds the max consecutive tap supported.
        // For example on Debian Linux with GTK, when going past a triple click,
        // on the fourth click the selection is moved to the precise click
        // position, on the fifth click the word at the position is selected, and
        // on the sixth click the paragraph at the position is selected.
        return rawCount <= maxConsecutiveTap
            ? rawCount
            : (rawCount % maxConsecutiveTap == 0
                  ? maxConsecutiveTap
                  : rawCount % maxConsecutiveTap);
      case TargetPlatform.iOS:
      case TargetPlatform.macOS:
      case TargetPlatform.windows:
        // From observation, these platforms hold their tap count at the max
        // consecutive tap supported. For example on macOS, when going past a triple
        // click, the selection should be retained at the paragraph that was first
        // selected on triple click.
        return min(rawCount, maxConsecutiveTap);
    }
  }

  void _initMouseGestureRecognizer() {
    _gestureRecognizers[TapAndPanGestureRecognizer] =
        GestureRecognizerFactoryWithHandlers<TapAndPanGestureRecognizer>(
          () => TapAndPanGestureRecognizer(
            debugOwner: this,
            supportedDevices: <PointerDeviceKind>{PointerDeviceKind.mouse},
          ),
          (TapAndPanGestureRecognizer instance) {
            instance
              ..onTapTrackStart = _onTapTrackStart
              ..onTapTrackReset = _onTapTrackReset
              ..onTapDown = _startNewMouseSelectionGesture
              ..onTapUp = _handleMouseTapUp
              ..onDragStart = _handleMouseDragStart
              ..onDragUpdate = _handleMouseDragUpdate
              ..onDragEnd = _handleMouseDragEnd
              ..onCancel = clearSelection
              ..dragStartBehavior = DragStartBehavior.down;
          },
        );
  }

  void _onTapTrackStart() {
    _isShiftPressed = HardwareKeyboard.instance.logicalKeysPressed.intersection(
      <LogicalKeyboardKey>{
        LogicalKeyboardKey.shiftLeft,
        LogicalKeyboardKey.shiftRight,
      },
    ).isNotEmpty;
  }

  void _onTapTrackReset() {
    _isShiftPressed = false;
  }

  void _initTouchGestureRecognizer() {
    // A [TapAndHorizontalDragGestureRecognizer] is used on non-precise pointer devices
    // like PointerDeviceKind.touch so [SelectableRegion] gestures do not conflict with
    // ancestor Scrollable gestures in common scenarios like a vertically scrolling list view.
    _gestureRecognizers[TapAndHorizontalDragGestureRecognizer] =
        GestureRecognizerFactoryWithHandlers<
          TapAndHorizontalDragGestureRecognizer
        >(
          () => TapAndHorizontalDragGestureRecognizer(
            debugOwner: this,
            supportedDevices: PointerDeviceKind.values
                .where(
                  (PointerDeviceKind device) =>
                      device != PointerDeviceKind.mouse,
                )
                .toSet(),
          ),
          (TapAndHorizontalDragGestureRecognizer instance) {
            instance
              // iOS does not provide a device specific touch slop
              // unlike Android (~8.0), so the touch slop for a [Scrollable]
              // always default to kTouchSlop which is 18.0. When
              // [SelectableRegion] is the child of a horizontal
              // scrollable that means the [SelectableRegion] will
              // always win the gesture arena when competing with
              // the ancestor scrollable because they both have
              // the same touch slop threshold and the child receives
              // the [PointerEvent] first. To avoid this conflict
              // and ensure a smooth scrolling experience, on
              // iOS the [TapAndHorizontalDragGestureRecognizer]
              // will wait for all other gestures to lose before
              // declaring victory.
              ..eagerVictoryOnDrag = defaultTargetPlatform != TargetPlatform.iOS
              ..onTapDown = _startNewMouseSelectionGesture
              ..onTapUp = _handleMouseTapUp
              ..onDragStart = _handleMouseDragStart
              ..onDragUpdate = _handleMouseDragUpdate
              ..onDragEnd = _handleMouseDragEnd
              ..onCancel = clearSelection
              ..dragStartBehavior = DragStartBehavior.down;
          },
        );
    _gestureRecognizers[LongPressGestureRecognizer] =
        GestureRecognizerFactoryWithHandlers<LongPressGestureRecognizer>(
          () => LongPressGestureRecognizer(
            debugOwner: this,
            supportedDevices: _kLongPressSelectionDevices,
          ),
          (LongPressGestureRecognizer instance) {
            instance
              ..onLongPressStart = _handleTouchLongPressStart
              ..onLongPressMoveUpdate = _handleTouchLongPressMoveUpdate
              ..onLongPressEnd = _handleTouchLongPressEnd;
          },
        );
  }

  Offset? _doubleTapOffset;

  void _startNewMouseSelectionGesture(TapDragDownDetails details) {
    _lastPointerDeviceKind = details.kind;
    switch (_getEffectiveConsecutiveTapCount(details.consecutiveTapCount)) {
      case 1:
        widget.focusNode.requestFocus();
        switch (defaultTargetPlatform) {
          case TargetPlatform.android:
          case TargetPlatform.fuchsia:
          case TargetPlatform.iOS:
            // On mobile platforms the selection is set on tap up for the first
            // tap.
            break;
          case TargetPlatform.macOS:
          case TargetPlatform.linux:
          case TargetPlatform.windows:
            hideToolbar();
            // It is impossible to extend the selection when the shift key is
            // pressed and the start of the selection has not been initialized.
            // In this case we fallback on collapsing the selection to first
            // initialize the selection.
            final isShiftPressedValid =
                _isShiftPressed &&
                _selectionDelegate.value.startSelectionPoint != null;
            if (isShiftPressedValid) {
              _selectEndTo(offset: details.globalPosition);
              return;
            }
            _collapseSelectionAt(offset: details.globalPosition);
        }
      case 2:
        switch (defaultTargetPlatform) {
          case TargetPlatform.iOS:
            if (kIsWeb &&
                details.kind != null &&
                !_isPrecisePointerDevice(details.kind!)) {
              // Double tap on iOS web triggers when a drag begins after the double tap.
              _doubleTapOffset = details.globalPosition;
              break;
            }
            _selectWordAt(offset: details.globalPosition);
            if (details.kind != null &&
                !_isPrecisePointerDevice(details.kind!)) {
              _showHandles();
            }
          case TargetPlatform.android:
          case TargetPlatform.fuchsia:
          case TargetPlatform.macOS:
          case TargetPlatform.linux:
          case TargetPlatform.windows:
            _selectWordAt(offset: details.globalPosition);
        }
      case 3:
        switch (defaultTargetPlatform) {
          case TargetPlatform.android:
          case TargetPlatform.fuchsia:
          case TargetPlatform.iOS:
            if (details.kind != null &&
                _isPrecisePointerDevice(details.kind!)) {
              // Triple tap on static text is only supported on mobile
              // platforms using a precise pointer device.
              _selectParagraphAt(offset: details.globalPosition);
            }
          case TargetPlatform.macOS:
          case TargetPlatform.linux:
          case TargetPlatform.windows:
            _selectParagraphAt(offset: details.globalPosition);
        }
    }
    _updateSelectedContentIfNeeded();
  }

  void _handleMouseDragStart(TapDragStartDetails details) {
    switch (_getEffectiveConsecutiveTapCount(details.consecutiveTapCount)) {
      case 1:
        if (details.kind != null && !_isPrecisePointerDevice(details.kind!)) {
          // Drag to select is only enabled with a precise pointer device.
          return;
        }
        _selectStartTo(offset: details.globalPosition);
    }
    _updateSelectedContentIfNeeded();
  }

  void _handleMouseDragUpdate(TapDragUpdateDetails details) {
    switch (_getEffectiveConsecutiveTapCount(details.consecutiveTapCount)) {
      case 1:
        if (details.kind != null && !_isPrecisePointerDevice(details.kind!)) {
          // Drag to select is only enabled with a precise pointer device.
          return;
        }
        _selectEndTo(offset: details.globalPosition, continuous: true);
      case 2:
        switch (defaultTargetPlatform) {
          case TargetPlatform.android:
          case TargetPlatform.fuchsia:
            // Double tap + drag is only supported on Android when using a precise
            // pointer device or when not on the web.
            if (!kIsWeb ||
                details.kind != null &&
                    _isPrecisePointerDevice(details.kind!)) {
              _selectEndTo(
                offset: details.globalPosition,
                continuous: true,
                textGranularity: TextGranularity.word,
              );
            }
          case TargetPlatform.iOS:
            if (kIsWeb &&
                details.kind != null &&
                !_isPrecisePointerDevice(details.kind!) &&
                _doubleTapOffset != null) {
              // On iOS web a double tap does not select the word at the position,
              // until the drag has begun.
              _selectWordAt(offset: _doubleTapOffset!);
              _doubleTapOffset = null;
            }
            _selectEndTo(
              offset: details.globalPosition,
              continuous: true,
              textGranularity: TextGranularity.word,
            );
            if (details.kind != null &&
                !_isPrecisePointerDevice(details.kind!)) {
              _showHandles();
            }
          case TargetPlatform.macOS:
          case TargetPlatform.linux:
          case TargetPlatform.windows:
            _selectEndTo(
              offset: details.globalPosition,
              continuous: true,
              textGranularity: TextGranularity.word,
            );
        }
      case 3:
        switch (defaultTargetPlatform) {
          case TargetPlatform.android:
          case TargetPlatform.fuchsia:
          case TargetPlatform.iOS:
            // Triple tap + drag is only supported on mobile devices when using
            // a precise pointer device.
            if (details.kind != null &&
                _isPrecisePointerDevice(details.kind!)) {
              _selectEndTo(
                offset: details.globalPosition,
                continuous: true,
                textGranularity: TextGranularity.paragraph,
              );
            }
          case TargetPlatform.macOS:
          case TargetPlatform.linux:
          case TargetPlatform.windows:
            _selectEndTo(
              offset: details.globalPosition,
              continuous: true,
              textGranularity: TextGranularity.paragraph,
            );
        }
    }
    _updateSelectedContentIfNeeded();
  }

  void _handleMouseDragEnd(TapDragEndDetails details) {
    final isPointerPrecise =
        _lastPointerDeviceKind != null &&
        _lastPointerDeviceKind == PointerDeviceKind.mouse;
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
      case TargetPlatform.fuchsia:
        if (!isPointerPrecise) {
          // On Android, a drag gesture will only show the selection overlay when
          // the drag has finished and the pointer device kind is not precise.
          _showHandles();
          _showToolbar();
        }
      case TargetPlatform.iOS:
        if (!isPointerPrecise) {
          // On iOS, a drag gesture will only show the selection toolbar when
          // the drag has finished and the pointer device kind is not precise.
          _showToolbar();
        }
      case TargetPlatform.macOS:
      case TargetPlatform.linux:
      case TargetPlatform.windows:
        // The selection overlay is not shown on desktop platforms after a drag.
        break;
    }
    _finalizeSelection();
    _updateSelectedContentIfNeeded();
  }

  void _handleMouseTapUp(TapDragUpDetails details) {
    if (defaultTargetPlatform == TargetPlatform.iOS &&
        _positionIsOnActiveSelection(globalPosition: details.globalPosition)) {
      // On iOS when the tap occurs on the previous selection, instead of
      // moving the selection, the context menu will be toggled.
      final toolbarIsVisible = _selectionOverlay?.toolbarIsVisible ?? false;
      if (toolbarIsVisible) {
        hideToolbar(false);
      } else {
        _showToolbar();
      }
      return;
    }
    switch (_getEffectiveConsecutiveTapCount(details.consecutiveTapCount)) {
      case 1:
        switch (defaultTargetPlatform) {
          case TargetPlatform.android:
          case TargetPlatform.fuchsia:
          case TargetPlatform.iOS:
            hideToolbar();
            _collapseSelectionAt(offset: details.globalPosition);
          case TargetPlatform.macOS:
          case TargetPlatform.linux:
          case TargetPlatform.windows:
            // On desktop platforms the selection is set on tap down.
            break;
        }
      case 2:
        final isPointerPrecise = _isPrecisePointerDevice(details.kind);
        switch (defaultTargetPlatform) {
          case TargetPlatform.android:
          case TargetPlatform.fuchsia:
            if (!isPointerPrecise) {
              // On Android, a double tap will only show the selection overlay after
              // the following tap up when the pointer device kind is not precise.
              _showHandles();
              _showToolbar();
            }
          case TargetPlatform.iOS:
            if (!isPointerPrecise) {
              // On iOS, a double tap will only show the selection toolbar after
              // the following tap up when the pointer device kind is not precise.
              _showToolbar();
            }
          case TargetPlatform.macOS:
          case TargetPlatform.linux:
          case TargetPlatform.windows:
            // The selection overlay is not shown on desktop platforms
            // on a double click.
            break;
        }
    }
    _updateSelectedContentIfNeeded();
  }

  void _updateSelectedContentIfNeeded() {
    if (_lastSelectedContent?.plainText !=
        _selectable?.getSelectedContent()?.plainText) {
      _lastSelectedContent = _selectable?.getSelectedContent();
      widget.onSelectionChanged?.call(_lastSelectedContent);
    }
  }

  void _handleTouchLongPressStart(LongPressStartDetails details) {
    HapticFeedback.selectionClick();
    widget.focusNode.requestFocus();
    _selectWordAt(offset: details.globalPosition);
    // Platforms besides Android will show the text selection handles when
    // the long press is initiated. Android shows the text selection handles when
    // the long press has ended, usually after a pointer up event is received.
    if (defaultTargetPlatform != TargetPlatform.android) {
      _showHandles();
    }
    _updateSelectedContentIfNeeded();
  }

  void _handleTouchLongPressMoveUpdate(LongPressMoveUpdateDetails details) {
    _selectEndTo(
      offset: details.globalPosition,
      textGranularity: TextGranularity.word,
    );
    _updateSelectedContentIfNeeded();
  }

  void _handleTouchLongPressEnd(LongPressEndDetails details) {
    _finalizeSelection();
    _updateSelectedContentIfNeeded();
    _showToolbar();
    if (defaultTargetPlatform == TargetPlatform.android) {
      _showHandles();
    }
  }

  bool _positionIsOnActiveSelection({required Offset globalPosition}) {
    for (final selectionRect in _selectionDelegate.value.selectionRects) {
      final transform = _selectable!.getTransformTo(null);
      final globalRect = MatrixUtils.transformRect(transform, selectionRect);
      if (globalRect.contains(globalPosition)) {
        return true;
      }
    }
    return false;
  }

  void _handleRightClickDown(TapDownDetails details) {
    final previousSecondaryTapDownPosition = _lastSecondaryTapDownPosition;
    final toolbarIsVisible = _selectionOverlay?.toolbarIsVisible ?? false;
    _lastSecondaryTapDownPosition = details.globalPosition;
    widget.focusNode.requestFocus();
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
      case TargetPlatform.fuchsia:
      case TargetPlatform.windows:
        // If _lastSecondaryTapDownPosition is within the current selection then
        // keep the current selection, if not then collapse it.
        final lastSecondaryTapDownPositionWasOnActiveSelection =
            _positionIsOnActiveSelection(
              globalPosition: details.globalPosition,
            );
        if (!lastSecondaryTapDownPositionWasOnActiveSelection) {
          _collapseSelectionAt(offset: _lastSecondaryTapDownPosition!);
        }
        _showHandles();
        _showToolbar(location: _lastSecondaryTapDownPosition);
      case TargetPlatform.iOS:
        _selectWordAt(offset: _lastSecondaryTapDownPosition!);
        _showHandles();
        _showToolbar(location: _lastSecondaryTapDownPosition);
      case TargetPlatform.macOS:
        if (previousSecondaryTapDownPosition == _lastSecondaryTapDownPosition &&
            toolbarIsVisible) {
          hideToolbar();
          return;
        }
        _selectWordAt(offset: _lastSecondaryTapDownPosition!);
        _showHandles();
        _showToolbar(location: _lastSecondaryTapDownPosition);
      case TargetPlatform.linux:
        if (toolbarIsVisible) {
          hideToolbar();
          return;
        }
        // If _lastSecondaryTapDownPosition is within the current selection then
        // keep the current selection, if not then collapse it.
        final lastSecondaryTapDownPositionWasOnActiveSelection =
            _positionIsOnActiveSelection(
              globalPosition: details.globalPosition,
            );
        if (!lastSecondaryTapDownPositionWasOnActiveSelection) {
          _collapseSelectionAt(offset: _lastSecondaryTapDownPosition!);
        }
        _showHandles();
        _showToolbar(location: _lastSecondaryTapDownPosition);
    }
    _updateSelectedContentIfNeeded();
  }

  // Selection update helper methods.

  Offset? _selectionEndPosition;

  bool get _userDraggingSelectionEnd => _selectionEndPosition != null;
  bool _scheduledSelectionEndEdgeUpdate = false;

  /// Sends end [SelectionEdgeUpdateEvent] to the selectable subtree.
  ///
  /// If the selectable subtree returns a [SelectionResult.pending], this method
  /// continues to send [SelectionEdgeUpdateEvent]s every frame until the result
  /// is not pending or users end their gestures.
  void _triggerSelectionEndEdgeUpdate({TextGranularity? textGranularity}) {
    // This method can be called when the drag is not in progress. This can
    // happen if the child scrollable returns SelectionResult.pending, and
    // the selection area scheduled a selection update for the next frame, but
    // the drag is lifted before the scheduled selection update is run.
    if (_scheduledSelectionEndEdgeUpdate || !_userDraggingSelectionEnd) {
      return;
    }
    if (_selectable?.dispatchSelectionEvent(
          SelectionEdgeUpdateEvent.forEnd(
            globalPosition: _selectionEndPosition!,
            granularity: textGranularity,
          ),
        ) ==
        SelectionResult.pending) {
      _scheduledSelectionEndEdgeUpdate = true;
      SchedulerBinding.instance.addPostFrameCallback((Duration timeStamp) {
        if (!_scheduledSelectionEndEdgeUpdate) {
          return;
        }
        _scheduledSelectionEndEdgeUpdate = false;
        _triggerSelectionEndEdgeUpdate(textGranularity: textGranularity);
      }, debugLabel: 'SelectableRegion.endEdgeUpdate');
      return;
    }
  }

  void _onAnyDragEnd(DragEndDetails details) {
    if (widget.selectionControls is! TextSelectionHandleControls) {
      _selectionOverlay!.hideMagnifier();
      _selectionOverlay!.showToolbar();
    } else {
      _selectionOverlay!.hideMagnifier();
      _selectionOverlay!.showToolbar(
        context: context,
        contextMenuBuilder: (BuildContext context) =>
            widget.contextMenuBuilder!(context, this),
      );
    }
    _stopSelectionStartEdgeUpdate();
    _stopSelectionEndEdgeUpdate();
    _updateSelectedContentIfNeeded();
  }

  void _stopSelectionEndEdgeUpdate() {
    _scheduledSelectionEndEdgeUpdate = false;
    _selectionEndPosition = null;
  }

  Offset? _selectionStartPosition;

  bool get _userDraggingSelectionStart => _selectionStartPosition != null;
  bool _scheduledSelectionStartEdgeUpdate = false;

  /// Sends start [SelectionEdgeUpdateEvent] to the selectable subtree.
  ///
  /// If the selectable subtree returns a [SelectionResult.pending], this method
  /// continues to send [SelectionEdgeUpdateEvent]s every frame until the result
  /// is not pending or users end their gestures.
  void _triggerSelectionStartEdgeUpdate({TextGranularity? textGranularity}) {
    // This method can be called when the drag is not in progress. This can
    // happen if the child scrollable returns SelectionResult.pending, and
    // the selection area scheduled a selection update for the next frame, but
    // the drag is lifted before the scheduled selection update is run.
    if (_scheduledSelectionStartEdgeUpdate || !_userDraggingSelectionStart) {
      return;
    }
    if (_selectable?.dispatchSelectionEvent(
          SelectionEdgeUpdateEvent.forStart(
            globalPosition: _selectionStartPosition!,
            granularity: textGranularity,
          ),
        ) ==
        SelectionResult.pending) {
      _scheduledSelectionStartEdgeUpdate = true;
      SchedulerBinding.instance.addPostFrameCallback((Duration timeStamp) {
        if (!_scheduledSelectionStartEdgeUpdate) {
          return;
        }
        _scheduledSelectionStartEdgeUpdate = false;
        _triggerSelectionStartEdgeUpdate(textGranularity: textGranularity);
      }, debugLabel: 'SelectableRegion.startEdgeUpdate');
      return;
    }
  }

  void _stopSelectionStartEdgeUpdate() {
    _scheduledSelectionStartEdgeUpdate = false;
    _selectionEndPosition = null;
  }

  // SelectionOverlay helper methods.

  late Offset _selectionStartHandleDragPosition;
  late Offset _selectionEndHandleDragPosition;

  void _handleSelectionStartHandleDragStart(DragStartDetails details) {
    assert(_selectionDelegate.value.startSelectionPoint != null);

    final localPosition =
        _selectionDelegate.value.startSelectionPoint!.localPosition;
    final globalTransform = _selectable!.getTransformTo(null);
    _selectionStartHandleDragPosition = MatrixUtils.transformPoint(
      globalTransform,
      localPosition,
    );

    _selectionOverlay!.showMagnifier(
      _buildInfoForMagnifier(
        details.globalPosition,
        _selectionDelegate.value.startSelectionPoint!,
      ),
    );
    _updateSelectedContentIfNeeded();
  }

  void _handleSelectionStartHandleDragUpdate(DragUpdateDetails details) {
    _selectionStartHandleDragPosition =
        _selectionStartHandleDragPosition + details.delta;
    // The value corresponds to the paint origin of the selection handle.
    // Offset it to the center of the line to make it feel more natural.
    _selectionStartPosition =
        _selectionStartHandleDragPosition -
        Offset(0, _selectionDelegate.value.startSelectionPoint!.lineHeight / 2);
    _triggerSelectionStartEdgeUpdate();

    _selectionOverlay!.updateMagnifier(
      _buildInfoForMagnifier(
        details.globalPosition,
        _selectionDelegate.value.startSelectionPoint!,
      ),
    );
    _updateSelectedContentIfNeeded();
  }

  void _handleSelectionEndHandleDragStart(DragStartDetails details) {
    assert(_selectionDelegate.value.endSelectionPoint != null);
    final localPosition =
        _selectionDelegate.value.endSelectionPoint!.localPosition;
    final globalTransform = _selectable!.getTransformTo(null);
    _selectionEndHandleDragPosition = MatrixUtils.transformPoint(
      globalTransform,
      localPosition,
    );

    _selectionOverlay!.showMagnifier(
      _buildInfoForMagnifier(
        details.globalPosition,
        _selectionDelegate.value.endSelectionPoint!,
      ),
    );
    _updateSelectedContentIfNeeded();
  }

  void _handleSelectionEndHandleDragUpdate(DragUpdateDetails details) {
    _selectionEndHandleDragPosition =
        _selectionEndHandleDragPosition + details.delta;
    // The value corresponds to the paint origin of the selection handle.
    // Offset it to the center of the line to make it feel more natural.
    _selectionEndPosition =
        _selectionEndHandleDragPosition -
        Offset(0, _selectionDelegate.value.endSelectionPoint!.lineHeight / 2);
    _triggerSelectionEndEdgeUpdate();

    _selectionOverlay!.updateMagnifier(
      _buildInfoForMagnifier(
        details.globalPosition,
        _selectionDelegate.value.endSelectionPoint!,
      ),
    );
    _updateSelectedContentIfNeeded();
  }

  MagnifierInfo _buildInfoForMagnifier(
    Offset globalGesturePosition,
    SelectionPoint selectionPoint,
  ) {
    final globalTransform = _selectable!.getTransformTo(null).getTranslation();
    final globalTransformAsOffset = Offset(
      globalTransform.x,
      globalTransform.y,
    );
    final globalSelectionPointPosition =
        selectionPoint.localPosition + globalTransformAsOffset;
    final caretRect = Rect.fromLTWH(
      globalSelectionPointPosition.dx,
      globalSelectionPointPosition.dy - selectionPoint.lineHeight,
      0,
      selectionPoint.lineHeight,
    );

    return MagnifierInfo(
      globalGesturePosition: globalGesturePosition,
      caretRect: caretRect,
      fieldBounds: globalTransformAsOffset & _selectable!.size,
      currentLineBoundaries: globalTransformAsOffset & _selectable!.size,
    );
  }

  void _createSelectionOverlay() {
    assert(_hasSelectionOverlayGeometry);
    if (_selectionOverlay != null) {
      return;
    }
    final start = _selectionDelegate.value.startSelectionPoint;
    final end = _selectionDelegate.value.endSelectionPoint;
    _selectionOverlay = SelectionOverlay(
      context: context,
      debugRequiredFor: widget,
      startHandleType: start?.handleType ?? TextSelectionHandleType.left,
      lineHeightAtStart: start?.lineHeight ?? end!.lineHeight,
      onStartHandleDragStart: _handleSelectionStartHandleDragStart,
      onStartHandleDragUpdate: _handleSelectionStartHandleDragUpdate,
      onStartHandleDragEnd: _onAnyDragEnd,
      endHandleType: end?.handleType ?? TextSelectionHandleType.right,
      lineHeightAtEnd: end?.lineHeight ?? start!.lineHeight,
      onEndHandleDragStart: _handleSelectionEndHandleDragStart,
      onEndHandleDragUpdate: _handleSelectionEndHandleDragUpdate,
      onEndHandleDragEnd: _onAnyDragEnd,
      selectionEndpoints: selectionEndpoints,
      selectionControls: widget.selectionControls,
      selectionDelegate: this,
      clipboardStatus: null,
      startHandleLayerLink: _startHandleLayerLink,
      endHandleLayerLink: _endHandleLayerLink,
      toolbarLayerLink: _toolbarLayerLink,
      magnifierConfiguration: widget.magnifierConfiguration,
    );
  }

  void _updateSelectionOverlay() {
    if (_selectionOverlay == null) {
      return;
    }
    assert(_hasSelectionOverlayGeometry);
    final start = _selectionDelegate.value.startSelectionPoint;
    final end = _selectionDelegate.value.endSelectionPoint;
    _selectionOverlay!
      ..startHandleType = start?.handleType ?? TextSelectionHandleType.left
      ..lineHeightAtStart = start?.lineHeight ?? end!.lineHeight
      ..endHandleType = end?.handleType ?? TextSelectionHandleType.right
      ..lineHeightAtEnd = end?.lineHeight ?? start!.lineHeight
      ..selectionEndpoints = selectionEndpoints;
  }

  /// Shows the selection handles.
  ///
  /// Returns true if the handles are shown, false if the handles can't be
  /// shown.
  bool _showHandles() {
    if (_selectionOverlay != null) {
      _selectionOverlay!.showHandles();
      return true;
    }

    if (!_hasSelectionOverlayGeometry) {
      return false;
    }

    _createSelectionOverlay();
    _selectionOverlay!.showHandles();
    return true;
  }

  /// Shows the text selection toolbar.
  ///
  /// If the parameter `location` is set, the toolbar will be shown at the
  /// location. Otherwise, the toolbar location will be calculated based on the
  /// handles' locations. The `location` is in the coordinates system of the
  /// [Overlay].
  ///
  /// Returns true if the toolbar is shown, false if the toolbar can't be shown.
  bool _showToolbar({Offset? location}) {
    if (!_hasSelectionOverlayGeometry && _selectionOverlay == null) {
      return false;
    }

    // Web is using native dom elements to enable clipboard functionality of the
    // context menu: copy, paste, select, cut. It might also provide additional
    // functionality depending on the browser (such as translate). Due to this,
    // we should not show a Flutter toolbar for the editable text elements
    // unless the browser's context menu is explicitly disabled.
    if (kIsWeb && BrowserContextMenu.enabled) {
      return false;
    }

    if (_selectionOverlay == null) {
      _createSelectionOverlay();
    }

    _selectionOverlay!.toolbarLocation = location;
    if (widget.selectionControls is! TextSelectionHandleControls) {
      _selectionOverlay!.showToolbar();
      return true;
    }

    _selectionOverlay!.hideToolbar();

    _selectionOverlay!.showToolbar(
      context: context,
      contextMenuBuilder: (BuildContext context) =>
          widget.contextMenuBuilder!(context, this),
    );
    return true;
  }

  /// Sets or updates selection end edge to the `offset` location.
  ///
  /// A selection always contains a select start edge and selection end edge.
  /// They can be created by calling both [_selectStartTo] and [_selectEndTo], or
  /// use other selection APIs, such as [_selectWordAt] or [selectAll].
  ///
  /// This method sets or updates the selection end edge by sending
  /// [SelectionEdgeUpdateEvent]s to the child [Selectable]s.
  ///
  /// If `continuous` is set to true and the update causes scrolling, the
  /// method will continue sending the same [SelectionEdgeUpdateEvent]s to the
  /// child [Selectable]s every frame until the scrolling finishes or a
  /// [_finalizeSelection] is called.
  ///
  /// The `continuous` argument defaults to false.
  ///
  /// The `offset` is in global coordinates.
  ///
  /// Provide the `textGranularity` if the selection should not move by the default
  /// [TextGranularity.character]. Only [TextGranularity.character] and
  /// [TextGranularity.word] are currently supported.
  ///
  /// See also:
  ///  * [_selectStartTo], which sets or updates selection start edge.
  ///  * [_finalizeSelection], which stops the `continuous` updates.
  ///  * [clearSelection], which clears the ongoing selection.
  ///  * [_selectWordAt], which selects a whole word at the location.
  ///  * [_selectParagraphAt], which selects an entire paragraph at the location.
  ///  * [_collapseSelectionAt], which collapses the selection at the location.
  ///  * [selectAll], which selects the entire content.
  void _selectEndTo({
    required Offset offset,
    bool continuous = false,
    TextGranularity? textGranularity,
  }) {
    if (!continuous) {
      _selectable?.dispatchSelectionEvent(
        SelectionEdgeUpdateEvent.forEnd(
          globalPosition: offset,
          granularity: textGranularity,
        ),
      );
      return;
    }
    if (_selectionEndPosition != offset) {
      _selectionEndPosition = offset;
      _triggerSelectionEndEdgeUpdate(textGranularity: textGranularity);
    }
  }

  /// Sets or updates selection start edge to the `offset` location.
  ///
  /// A selection always contains a select start edge and selection end edge.
  /// They can be created by calling both [_selectStartTo] and [_selectEndTo], or
  /// use other selection APIs, such as [_selectWordAt] or [selectAll].
  ///
  /// This method sets or updates the selection start edge by sending
  /// [SelectionEdgeUpdateEvent]s to the child [Selectable]s.
  ///
  /// If `continuous` is set to true and the update causes scrolling, the
  /// method will continue sending the same [SelectionEdgeUpdateEvent]s to the
  /// child [Selectable]s every frame until the scrolling finishes or a
  /// [_finalizeSelection] is called.
  ///
  /// The `continuous` argument defaults to false.
  ///
  /// The `offset` is in global coordinates.
  ///
  /// Provide the `textGranularity` if the selection should not move by the default
  /// [TextGranularity.character]. Only [TextGranularity.character] and
  /// [TextGranularity.word] are currently supported.
  ///
  /// See also:
  ///  * [_selectEndTo], which sets or updates selection end edge.
  ///  * [_finalizeSelection], which stops the `continuous` updates.
  ///  * [clearSelection], which clears the ongoing selection.
  ///  * [_selectWordAt], which selects a whole word at the location.
  ///  * [_selectParagraphAt], which selects an entire paragraph at the location.
  ///  * [_collapseSelectionAt], which collapses the selection at the location.
  ///  * [selectAll], which selects the entire content.
  void _selectStartTo({
    required Offset offset,
    bool continuous = false,
    TextGranularity? textGranularity,
  }) {
    if (!continuous) {
      _selectable?.dispatchSelectionEvent(
        SelectionEdgeUpdateEvent.forStart(
          globalPosition: offset,
          granularity: textGranularity,
        ),
      );
      return;
    }
    if (_selectionStartPosition != offset) {
      _selectionStartPosition = offset;
      _triggerSelectionStartEdgeUpdate(textGranularity: textGranularity);
    }
  }

  /// Collapses the selection at the given `offset` location.
  ///
  /// The `offset` is in global coordinates.
  ///
  /// See also:
  ///  * [_selectStartTo], which sets or updates selection start edge.
  ///  * [_selectEndTo], which sets or updates selection end edge.
  ///  * [_finalizeSelection], which stops the `continuous` updates.
  ///  * [clearSelection], which clears the ongoing selection.
  ///  * [_selectWordAt], which selects a whole word at the location.
  ///  * [_selectParagraphAt], which selects an entire paragraph at the location.
  ///  * [selectAll], which selects the entire content.
  void _collapseSelectionAt({required Offset offset}) {
    _selectStartTo(offset: offset);
    _selectEndTo(offset: offset);
  }

  /// Selects a whole word at the `offset` location.
  ///
  /// The `offset` is in global coordinates.
  ///
  /// If the whole word is already in the current selection, selection won't
  /// change. One call [clearSelection] first if the selection needs to be
  /// updated even if the word is already covered by the current selection.
  ///
  /// One can also use [_selectEndTo] or [_selectStartTo] to adjust the selection
  /// edges after calling this method.
  ///
  /// See also:
  ///  * [_selectStartTo], which sets or updates selection start edge.
  ///  * [_selectEndTo], which sets or updates selection end edge.
  ///  * [_finalizeSelection], which stops the `continuous` updates.
  ///  * [clearSelection], which clears the ongoing selection.
  ///  * [_collapseSelectionAt], which collapses the selection at the location.
  ///  * [_selectParagraphAt], which selects an entire paragraph at the location.
  ///  * [selectAll], which selects the entire content.
  void _selectWordAt({required Offset offset}) {
    // There may be other selection ongoing.
    _finalizeSelection();
    _selectable?.dispatchSelectionEvent(
      SelectWordSelectionEvent(globalPosition: offset),
    );
  }

  /// Selects the entire paragraph at the `offset` location.
  ///
  /// The `offset` is in global coordinates.
  ///
  /// If the paragraph is already in the current selection, selection won't
  /// change. One call [clearSelection] first if the selection needs to be
  /// updated even if the paragraph is already covered by the current selection.
  ///
  /// One can also use [_selectEndTo] or [_selectStartTo] to adjust the selection
  /// edges after calling this method.
  ///
  /// See also:
  ///  * [_selectStartTo], which sets or updates selection start edge.
  ///  * [_selectEndTo], which sets or updates selection end edge.
  ///  * [_finalizeSelection], which stops the `continuous` updates.
  ///  * [clearSelection], which clear the ongoing selection.
  ///  * [_selectWordAt], which selects a whole word at the location.
  ///  * [selectAll], which selects the entire content.
  void _selectParagraphAt({required Offset offset}) {
    // There may be other selection ongoing.
    _finalizeSelection();
    _selectable?.dispatchSelectionEvent(
      SelectParagraphSelectionEvent(globalPosition: offset),
    );
  }

  /// Stops any ongoing selection updates.
  ///
  /// This method is different from [clearSelection] that it does not remove
  /// the current selection. It only stops the continuous updates.
  ///
  /// A continuous update can happen as result of calling [_selectStartTo] or
  /// [_selectEndTo] with `continuous` sets to true which causes a [Selectable]
  /// to scroll. Calling this method will stop the update as well as the
  /// scrolling.
  void _finalizeSelection() {
    _stopSelectionEndEdgeUpdate();
    _stopSelectionStartEdgeUpdate();
  }

  /// Removes the ongoing selection for this [TSelectableRegion].
  void clearSelection() {
    _finalizeSelection();
    _directionalHorizontalBaseline = null;
    _adjustingSelectionEnd = null;
    _selectable?.dispatchSelectionEvent(const ClearSelectionEvent());
    _updateSelectedContentIfNeeded();
  }

  Future<void> _copy() async {
    final data = _selectable?.getSelectedContent();
    if (data == null) {
      return;
    }
    await Clipboard.setData(ClipboardData(text: data.plainText));
  }

  Future<void> _share() async {
    final data = _selectable?.getSelectedContent();
    if (data == null) {
      return;
    }
    await SystemChannels.platform.invokeMethod('Share.invoke', data.plainText);
  }

  /// {@macro flutter.widgets.EditableText.getAnchors}
  ///
  /// See also:
  ///
  ///  * [contextMenuButtonItems], which provides the [ContextMenuButtonItem]s
  ///    for the default context menu buttons.
  TextSelectionToolbarAnchors get contextMenuAnchors {
    if (_lastSecondaryTapDownPosition != null) {
      return TextSelectionToolbarAnchors(
        primaryAnchor: _lastSecondaryTapDownPosition!,
      );
    }
    final renderBox = context.findRenderObject()! as RenderBox;
    return TextSelectionToolbarAnchors.fromSelection(
      renderBox: renderBox,
      startGlyphHeight: startGlyphHeight,
      endGlyphHeight: endGlyphHeight,
      selectionEndpoints: selectionEndpoints,
    );
  }

  bool? _adjustingSelectionEnd;

  bool _determineIsAdjustingSelectionEnd(bool forward) {
    if (_adjustingSelectionEnd != null) {
      return _adjustingSelectionEnd!;
    }
    final bool isReversed;
    final start = _selectionDelegate.value.startSelectionPoint!;
    final end = _selectionDelegate.value.endSelectionPoint!;
    if (start.localPosition.dy > end.localPosition.dy) {
      isReversed = true;
    } else if (start.localPosition.dy < end.localPosition.dy) {
      isReversed = false;
    } else {
      isReversed = start.localPosition.dx > end.localPosition.dx;
    }
    // Always move the selection edge that increases the selection range.
    return _adjustingSelectionEnd = forward != isReversed;
  }

  void _granularlyExtendSelection(TextGranularity granularity, bool forward) {
    _directionalHorizontalBaseline = null;
    if (!_selectionDelegate.value.hasSelection) {
      return;
    }
    _selectable?.dispatchSelectionEvent(
      GranularlyExtendSelectionEvent(
        forward: forward,
        isEnd: _determineIsAdjustingSelectionEnd(forward),
        granularity: granularity,
      ),
    );
    _updateSelectedContentIfNeeded();
  }

  double? _directionalHorizontalBaseline;

  void _directionallyExtendSelection(bool forward) {
    if (!_selectionDelegate.value.hasSelection) {
      return;
    }
    final adjustingSelectionExtend = _determineIsAdjustingSelectionEnd(forward);
    final baseLinePoint = adjustingSelectionExtend
        ? _selectionDelegate.value.endSelectionPoint!
        : _selectionDelegate.value.startSelectionPoint!;
    _directionalHorizontalBaseline ??= baseLinePoint.localPosition.dx;
    final globalSelectionPointOffset = MatrixUtils.transformPoint(
      context.findRenderObject()!.getTransformTo(null),
      Offset(_directionalHorizontalBaseline!, 0),
    );
    _selectable?.dispatchSelectionEvent(
      DirectionallyExtendSelectionEvent(
        isEnd: _adjustingSelectionEnd!,
        direction: forward
            ? SelectionExtendDirection.nextLine
            : SelectionExtendDirection.previousLine,
        dx: globalSelectionPointOffset.dx,
      ),
    );
    _updateSelectedContentIfNeeded();
  }

  // [TextSelectionDelegate] overrides.

  /// Returns the [ContextMenuButtonItem]s representing the buttons in this
  /// platform's default selection menu.
  ///
  /// See also:
  ///
  /// * [TSelectableRegion.getSelectableButtonItems], which performs a similar role,
  ///   but for any selectable text, not just specifically SelectableRegion.
  /// * [EditableTextState.contextMenuButtonItems], which performs a similar role
  ///   but for content that is not just selectable but also editable.
  /// * [contextMenuAnchors], which provides the anchor points for the default
  ///   context menu.
  /// * [AdaptiveTextSelectionToolbar], which builds the toolbar itself, and can
  ///   take a list of [ContextMenuButtonItem]s with
  ///   [AdaptiveTextSelectionToolbar.buttonItems].
  /// * [AdaptiveTextSelectionToolbar.getAdaptiveButtons], which builds the
  ///   button Widgets for the current platform given [ContextMenuButtonItem]s.
  List<ContextMenuButtonItem> get contextMenuButtonItems =>
      TSelectableRegion.getSelectableButtonItems(
        selectionGeometry: _selectionDelegate.value,
        onCopy: () {
          _copy();

          // On Android copy should clear the selection.
          switch (defaultTargetPlatform) {
            case TargetPlatform.android:
            case TargetPlatform.fuchsia:
              clearSelection();
            case TargetPlatform.iOS:
              hideToolbar(false);
            case TargetPlatform.linux:
            case TargetPlatform.macOS:
            case TargetPlatform.windows:
              hideToolbar();
          }
        },
        onSelectAll: () {
          switch (defaultTargetPlatform) {
            case TargetPlatform.android:
            case TargetPlatform.iOS:
            case TargetPlatform.fuchsia:
              selectAll(SelectionChangedCause.toolbar);
            case TargetPlatform.linux:
            case TargetPlatform.macOS:
            case TargetPlatform.windows:
              selectAll();
              hideToolbar();
          }
        },
        onShare: () {
          _share();

          // On Android, share should clear the selection.
          switch (defaultTargetPlatform) {
            case TargetPlatform.android:
            case TargetPlatform.fuchsia:
              clearSelection();
            case TargetPlatform.iOS:
              hideToolbar(false);
            case TargetPlatform.linux:
            case TargetPlatform.macOS:
            case TargetPlatform.windows:
              hideToolbar();
          }
        },
      )..addAll(_textProcessingActionButtonItems);

  List<ContextMenuButtonItem> get _textProcessingActionButtonItems {
    final buttonItems = <ContextMenuButtonItem>[];
    final data = _selectable?.getSelectedContent();
    if (data == null) {
      return buttonItems;
    }

    for (final action in _processTextActions) {
      buttonItems.add(
        ContextMenuButtonItem(
          label: action.label,
          onPressed: () async {
            final selectedText = data.plainText;
            if (selectedText.isNotEmpty) {
              await _processTextService.processTextAction(
                action.id,
                selectedText,
                true,
              );
              hideToolbar();
            }
          },
        ),
      );
    }
    return buttonItems;
  }

  /// The line height at the start of the current selection.
  double get startGlyphHeight =>
      _selectionDelegate.value.startSelectionPoint!.lineHeight;

  /// The line height at the end of the current selection.
  double get endGlyphHeight =>
      _selectionDelegate.value.endSelectionPoint!.lineHeight;

  /// Returns the local coordinates of the endpoints of the current selection.
  List<TextSelectionPoint> get selectionEndpoints {
    final start = _selectionDelegate.value.startSelectionPoint;
    final end = _selectionDelegate.value.endSelectionPoint;
    late List<TextSelectionPoint> points;
    final startLocalPosition = start?.localPosition ?? end!.localPosition;
    final endLocalPosition = end?.localPosition ?? start!.localPosition;
    if (startLocalPosition.dy > endLocalPosition.dy) {
      points = <TextSelectionPoint>[
        TextSelectionPoint(endLocalPosition, TextDirection.ltr),
        TextSelectionPoint(startLocalPosition, TextDirection.ltr),
      ];
    } else {
      points = <TextSelectionPoint>[
        TextSelectionPoint(startLocalPosition, TextDirection.ltr),
        TextSelectionPoint(endLocalPosition, TextDirection.ltr),
      ];
    }
    return points;
  }

  // [TextSelectionDelegate] overrides.
  // TODO(justinmc): After deprecations have been removed, remove
  // TextSelectionDelegate from this class.
  // https://github.com/flutter/flutter/issues/111213

  @Deprecated(
    'Use `contextMenuBuilder` instead. '
    'This feature was deprecated after v3.3.0-0.5.pre.',
  )
  @override
  bool get cutEnabled => false;

  @Deprecated(
    'Use `contextMenuBuilder` instead. '
    'This feature was deprecated after v3.3.0-0.5.pre.',
  )
  @override
  bool get pasteEnabled => false;

  @override
  void hideToolbar([bool hideHandles = true]) {
    _selectionOverlay?.hideToolbar();
    if (hideHandles) {
      _selectionOverlay?.hideHandles();
    }
  }

  @override
  void selectAll([SelectionChangedCause? cause]) {
    clearSelection();
    _selectable?.dispatchSelectionEvent(const SelectAllSelectionEvent());
    if (cause == SelectionChangedCause.toolbar) {
      _showToolbar();
      _showHandles();
    }
    _updateSelectedContentIfNeeded();
  }

  @Deprecated(
    'Use `contextMenuBuilder` instead. '
    'This feature was deprecated after v3.3.0-0.5.pre.',
  )
  @override
  void copySelection(SelectionChangedCause cause) {
    _copy();
    clearSelection();
  }

  @Deprecated(
    'Use `contextMenuBuilder` instead. '
    'This feature was deprecated after v3.3.0-0.5.pre.',
  )
  @override
  TextEditingValue textEditingValue = const TextEditingValue(text: '_');

  @Deprecated(
    'Use `contextMenuBuilder` instead. '
    'This feature was deprecated after v3.3.0-0.5.pre.',
  )
  @override
  void bringIntoView(TextPosition position) {
    /* SelectableRegion must be in view at this point. */
  }

  @Deprecated(
    'Use `contextMenuBuilder` instead. '
    'This feature was deprecated after v3.3.0-0.5.pre.',
  )
  @override
  void cutSelection(SelectionChangedCause cause) {
    assert(false);
  }

  @Deprecated(
    'Use `contextMenuBuilder` instead. '
    'This feature was deprecated after v3.3.0-0.5.pre.',
  )
  @override
  void userUpdateTextEditingValue(
    TextEditingValue value,
    SelectionChangedCause cause,
  ) {
    /* SelectableRegion maintains its own state */
  }

  @Deprecated(
    'Use `contextMenuBuilder` instead. '
    'This feature was deprecated after v3.3.0-0.5.pre.',
  )
  @override
  Future<void> pasteText(SelectionChangedCause cause) async {
    assert(false);
  }

  // [SelectionRegistrar] override.

  @override
  void add(Selectable selectable) {
    assert(_selectable == null);
    _selectable = selectable;
    _selectable!.addListener(_updateSelectionStatus);
    _selectable!.pushHandleLayers(_startHandleLayerLink, _endHandleLayerLink);
  }

  @override
  void remove(Selectable selectable) {
    assert(_selectable == selectable);
    _selectable!.removeListener(_updateSelectionStatus);
    _selectable!.pushHandleLayers(null, null);
    _selectable = null;
  }

  @override
  void dispose() {
    _selectable?.removeListener(_updateSelectionStatus);
    _selectable?.pushHandleLayers(null, null);
    _selectionDelegate.dispose();
    // In case dispose was triggered before gesture end, remove the magnifier
    // so it doesn't remain stuck in the overlay forever.
    _selectionOverlay?.hideMagnifier();
    _selectionOverlay?.dispose();
    _selectionOverlay = null;
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    assert(debugCheckHasOverlay(context));
    Widget result = SelectionContainer(
      registrar: this,
      delegate: _selectionDelegate,
      child: widget.child,
    );
    if (kIsWeb) {
      result = PlatformSelectableRegionContextMenu(child: result);
    }
    return CompositedTransformTarget(
      link: _toolbarLayerLink,
      child: RawGestureDetector(
        gestures: _gestureRecognizers,
        behavior: HitTestBehavior.translucent,
        excludeFromSemantics: true,
        child: Actions(
          actions: _actions,
          child: Focus(
            includeSemantics: false,
            focusNode: widget.focusNode,
            child: result,
          ),
        ),
      ),
    );
  }
}

/// An action that does not override any [Action.overridable] in the subtree.
///
/// If this action is invoked by an [Action.overridable], it will immediately
/// invoke the [Action.overridable] and do nothing else. Otherwise, it will call
/// [invokeAction].
abstract class _NonOverrideAction<T extends Intent> extends ContextAction<T> {
  Object? invokeAction(T intent, [BuildContext? context]);

  @override
  Object? invoke(T intent, [BuildContext? context]) {
    if (callingAction != null) {
      return callingAction!.invoke(intent);
    }
    return invokeAction(intent, context);
  }
}

class _SelectAllAction extends _NonOverrideAction<SelectAllTextIntent> {
  _SelectAllAction(this.state);

  final TSelectableRegionState state;

  @override
  void invokeAction(SelectAllTextIntent intent, [BuildContext? context]) {
    state.selectAll(SelectionChangedCause.keyboard);
  }
}

class _CopySelectionAction extends _NonOverrideAction<CopySelectionTextIntent> {
  _CopySelectionAction(this.state);

  final TSelectableRegionState state;

  @override
  void invokeAction(CopySelectionTextIntent intent, [BuildContext? context]) {
    state._copy();
  }
}

class _GranularlyExtendSelectionAction<T extends DirectionalTextEditingIntent>
    extends _NonOverrideAction<T> {
  _GranularlyExtendSelectionAction(this.state, {required this.granularity});

  final TSelectableRegionState state;
  final TextGranularity granularity;

  @override
  void invokeAction(T intent, [BuildContext? context]) {
    state._granularlyExtendSelection(granularity, intent.forward);
  }
}

class _GranularlyExtendCaretSelectionAction<
  T extends DirectionalCaretMovementIntent
>
    extends _NonOverrideAction<T> {
  _GranularlyExtendCaretSelectionAction(
    this.state, {
    required this.granularity,
  });

  final TSelectableRegionState state;
  final TextGranularity granularity;

  @override
  void invokeAction(T intent, [BuildContext? context]) {
    if (intent.collapseSelection) {
      // Selectable region never collapses selection.
      return;
    }
    state._granularlyExtendSelection(granularity, intent.forward);
  }
}

class _DirectionallyExtendCaretSelectionAction<
  T extends DirectionalCaretMovementIntent
>
    extends _NonOverrideAction<T> {
  _DirectionallyExtendCaretSelectionAction(this.state);

  final TSelectableRegionState state;

  @override
  void invokeAction(T intent, [BuildContext? context]) {
    if (intent.collapseSelection) {
      // Selectable region never collapses selection.
      return;
    }
    state._directionallyExtendSelection(intent.forward);
  }
}

class _SelectableRegionContainerDelegate
    extends MultiSelectableSelectionContainerDelegate {
  final Set<Selectable> _hasReceivedStartEvent = <Selectable>{};
  final Set<Selectable> _hasReceivedEndEvent = <Selectable>{};

  Offset? _lastStartEdgeUpdateGlobalPosition;
  Offset? _lastEndEdgeUpdateGlobalPosition;

  @override
  void remove(Selectable selectable) {
    _hasReceivedStartEvent.remove(selectable);
    _hasReceivedEndEvent.remove(selectable);
    super.remove(selectable);
  }

  void _updateLastEdgeEventsFromGeometries() {
    if (currentSelectionStartIndex != -1 &&
        selectables[currentSelectionStartIndex].value.hasSelection) {
      final start = selectables[currentSelectionStartIndex];
      final localStartEdge =
          start.value.startSelectionPoint!.localPosition +
          Offset(0, -start.value.startSelectionPoint!.lineHeight / 2);
      _lastStartEdgeUpdateGlobalPosition = MatrixUtils.transformPoint(
        start.getTransformTo(null),
        localStartEdge,
      );
    }
    if (currentSelectionEndIndex != -1 &&
        selectables[currentSelectionEndIndex].value.hasSelection) {
      final end = selectables[currentSelectionEndIndex];
      final localEndEdge =
          end.value.endSelectionPoint!.localPosition +
          Offset(0, -end.value.endSelectionPoint!.lineHeight / 2);
      _lastEndEdgeUpdateGlobalPosition = MatrixUtils.transformPoint(
        end.getTransformTo(null),
        localEndEdge,
      );
    }
  }

  @override
  SelectionResult handleSelectAll(SelectAllSelectionEvent event) {
    final result = super.handleSelectAll(event);
    for (final selectable in selectables) {
      _hasReceivedStartEvent.add(selectable);
      _hasReceivedEndEvent.add(selectable);
    }
    // Synthesize last update event so the edge updates continue to work.
    _updateLastEdgeEventsFromGeometries();
    return result;
  }

  /// Selects a word in a [Selectable] at the location
  /// [SelectWordSelectionEvent.globalPosition].
  @override
  SelectionResult handleSelectWord(SelectWordSelectionEvent event) {
    final result = super.handleSelectWord(event);
    if (currentSelectionStartIndex != -1) {
      _hasReceivedStartEvent.add(selectables[currentSelectionStartIndex]);
    }
    if (currentSelectionEndIndex != -1) {
      _hasReceivedEndEvent.add(selectables[currentSelectionEndIndex]);
    }
    _updateLastEdgeEventsFromGeometries();
    return result;
  }

  /// Selects a paragraph in a [Selectable] at the location
  /// [SelectParagraphSelectionEvent.globalPosition].
  @override
  SelectionResult handleSelectParagraph(SelectParagraphSelectionEvent event) {
    final result = super.handleSelectParagraph(event);
    if (currentSelectionStartIndex != -1) {
      _hasReceivedStartEvent.add(selectables[currentSelectionStartIndex]);
    }
    if (currentSelectionEndIndex != -1) {
      _hasReceivedEndEvent.add(selectables[currentSelectionEndIndex]);
    }
    _updateLastEdgeEventsFromGeometries();
    return result;
  }

  @override
  SelectionResult handleClearSelection(ClearSelectionEvent event) {
    final result = super.handleClearSelection(event);
    _hasReceivedStartEvent.clear();
    _hasReceivedEndEvent.clear();
    _lastStartEdgeUpdateGlobalPosition = null;
    _lastEndEdgeUpdateGlobalPosition = null;
    return result;
  }

  @override
  SelectionResult handleSelectionEdgeUpdate(SelectionEdgeUpdateEvent event) {
    if (event.type == SelectionEventType.endEdgeUpdate) {
      _lastEndEdgeUpdateGlobalPosition = event.globalPosition;
    } else {
      _lastStartEdgeUpdateGlobalPosition = event.globalPosition;
    }
    return super.handleSelectionEdgeUpdate(event);
  }

  @override
  void dispose() {
    _hasReceivedStartEvent.clear();
    _hasReceivedEndEvent.clear();
    super.dispose();
  }

  @override
  SelectionResult dispatchSelectionEventToChild(
    Selectable selectable,
    SelectionEvent event,
  ) {
    switch (event.type) {
      case SelectionEventType.startEdgeUpdate:
        _hasReceivedStartEvent.add(selectable);
        ensureChildUpdated(selectable);
      case SelectionEventType.endEdgeUpdate:
        _hasReceivedEndEvent.add(selectable);
        ensureChildUpdated(selectable);
      case SelectionEventType.clear:
        _hasReceivedStartEvent.remove(selectable);
        _hasReceivedEndEvent.remove(selectable);
      case SelectionEventType.selectAll:
      case SelectionEventType.selectWord:
      case SelectionEventType.selectParagraph:
        break;
      case SelectionEventType.granularlyExtendSelection:
      case SelectionEventType.directionallyExtendSelection:
        _hasReceivedStartEvent.add(selectable);
        _hasReceivedEndEvent.add(selectable);
        ensureChildUpdated(selectable);
    }
    return super.dispatchSelectionEventToChild(selectable, event);
  }

  @override
  void ensureChildUpdated(Selectable selectable) {
    if (_lastEndEdgeUpdateGlobalPosition != null &&
        _hasReceivedEndEvent.add(selectable)) {
      final synthesizedEvent = SelectionEdgeUpdateEvent.forEnd(
        globalPosition: _lastEndEdgeUpdateGlobalPosition!,
      );
      if (currentSelectionEndIndex == -1) {
        handleSelectionEdgeUpdate(synthesizedEvent);
      }
      selectable.dispatchSelectionEvent(synthesizedEvent);
    }
    if (_lastStartEdgeUpdateGlobalPosition != null &&
        _hasReceivedStartEvent.add(selectable)) {
      final synthesizedEvent = SelectionEdgeUpdateEvent.forStart(
        globalPosition: _lastStartEdgeUpdateGlobalPosition!,
      );
      if (currentSelectionStartIndex == -1) {
        handleSelectionEdgeUpdate(synthesizedEvent);
      }
      selectable.dispatchSelectionEvent(synthesizedEvent);
    }
  }

  @override
  SelectedContent? getSelectedContent() {
    final selections = <SelectedContent>[
      for (final Selectable selectable in selectables)
        if (selectable.getSelectedContent() case final data?) data,
    ];
    if (selections.isEmpty) {
      return null;
    }
    final buffer = StringBuffer();
    var isFirst = true;
    for (final selection in selections) {
      if (!isFirst) {
        // 10 = '\n'
        buffer.writeCharCode(10);
      }
      buffer.write(selection.plainText);
      isFirst = false;
    }
    return SelectedContent(plainText: buffer.toString());
  }

  @override
  void didChangeSelectables() {
    if (_lastEndEdgeUpdateGlobalPosition != null) {
      handleSelectionEdgeUpdate(
        SelectionEdgeUpdateEvent.forEnd(
          globalPosition: _lastEndEdgeUpdateGlobalPosition!,
        ),
      );
    }
    if (_lastStartEdgeUpdateGlobalPosition != null) {
      handleSelectionEdgeUpdate(
        SelectionEdgeUpdateEvent.forStart(
          globalPosition: _lastStartEdgeUpdateGlobalPosition!,
        ),
      );
    }
    final selectableSet = selectables.toSet();
    _hasReceivedEndEvent.removeWhere(
      (Selectable selectable) => !selectableSet.contains(selectable),
    );
    _hasReceivedStartEvent.removeWhere(
      (Selectable selectable) => !selectableSet.contains(selectable),
    );
    super.didChangeSelectables();
  }
}

typedef TSelectableRegionContextMenuBuilder =
    Widget Function(
      BuildContext context,
      TSelectableRegionState selectableRegionState,
    );

class TSelectableRegionController {
  VoidCallback? hideContextMenu;
}
