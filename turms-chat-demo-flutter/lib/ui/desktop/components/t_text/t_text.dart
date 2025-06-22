// Copyright 2014 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

/// @docImport 'package:flutter/gestures.dart';
/// @docImport 'package:flutter/material.dart';
///
/// @docImport 'editable_text.dart';
/// @docImport 'gesture_detector.dart';
/// @docImport 'implicit_animations.dart';
/// @docImport 'transitions.dart';
/// @docImport 'widget_span.dart';
library;

import 'dart:math';
import 'dart:ui' as ui show TextHeightBehavior;

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import 't_paragraph.dart';

// Examples can assume:
// late String _name;
// late BuildContext context;

class _NullWidget extends StatelessWidget {
  const _NullWidget();

  @override
  Widget build(BuildContext context) {
    throw FlutterError(
      'A DefaultTextStyle constructed with DefaultTextStyle.fallback cannot be incorporated into the widget tree, '
      'it is meant only to provide a fallback value returned by DefaultTextStyle.of() '
      'when no enclosing default text style is present in a BuildContext.',
    );
  }
}

/// A run of text with a single style.
///
/// The [TText] widget displays a string of text with single style. The string
/// might break across multiple lines or might all be displayed on the same line
/// depending on the layout constraints.
///
/// The [style] argument is optional. When omitted, the text will use the style
/// from the closest enclosing [DefaultTextStyle]. If the given style's
/// [TextStyle.inherit] property is true (the default), the given style will
/// be merged with the closest enclosing [DefaultTextStyle]. This merging
/// behavior is useful, for example, to make the text bold while using the
/// default font family and size.
///
/// {@tool snippet}
///
/// This example shows how to display text using the [TText] widget with the
/// [overflow] set to [TextOverflow.ellipsis].
///
/// ![If the text overflows, the Text widget displays an ellipsis to trim the overflowing text](https://flutter.github.io/assets-for-api-docs/assets/widgets/text_ellipsis.png)
///
/// ```dart
/// Container(
///   width: 100,
///   decoration: BoxDecoration(border: Border.all()),
///   child: Text(overflow: TextOverflow.ellipsis, 'Hello $_name, how are you?'))
/// ```
/// {@end-tool}
///
/// {@tool snippet}
///
/// Setting [maxLines] to `1` is not equivalent to disabling soft wrapping with
/// [softWrap]. This is apparent when using [TextOverflow.fade] as the following
/// examples show.
///
/// ![If a second line overflows the Text widget displays a horizontal fade](https://flutter.github.io/assets-for-api-docs/assets/widgets/text_fade_max_lines.png)
///
/// ```dart
/// Text(
///   overflow: TextOverflow.fade,
///   maxLines: 1,
///   'Hello $_name, how are you?')
/// ```
///
/// Here soft wrapping is enabled and the [TText] widget tries to wrap the words
/// "how are you?" to a second line. This is prevented by the [maxLines] value
/// of `1`. The result is that a second line overflows and the fade appears in a
/// horizontal direction at the bottom.
///
/// ![If a single line overflows the Text widget displays a horizontal fade](https://flutter.github.io/assets-for-api-docs/assets/widgets/text_fade_soft_wrap.png)
///
/// ```dart
/// Text(
///   overflow: TextOverflow.fade,
///   softWrap: false,
///   'Hello $_name, how are you?')
/// ```
///
/// Here soft wrapping is disabled with `softWrap: false` and the [TText] widget
/// attempts to display its text in a single unbroken line. The result is that
/// the single line overflows and the fade appears in a vertical direction at
/// the right.
///
/// {@end-tool}
///
/// Using the [Text.rich] constructor, the [TText] widget can
/// display a paragraph with differently styled [TextSpan]s. The sample
/// that follows displays "Hello beautiful world" with different styles
/// for each word.
///
/// {@tool snippet}
///
/// ![The word "Hello" is shown with the default text styles. The word "beautiful" is italicized. The word "world" is bold.](https://flutter.github.io/assets-for-api-docs/assets/widgets/text_rich.png)
///
/// ```dart
/// const Text.rich(
///   TextSpan(
///     text: 'Hello', // default text style
///     children: <TextSpan>[
///       TextSpan(text: ' beautiful ', style: TextStyle(fontStyle: FontStyle.italic)),
///       TextSpan(text: 'world', style: TextStyle(fontWeight: FontWeight.bold)),
///     ],
///   ),
/// )
/// ```
/// {@end-tool}
///
/// ## Interactivity
///
/// To make [TText] react to touch events, wrap it in a [GestureDetector] widget
/// with a [GestureDetector.onTap] handler.
///
/// In a Material Design application, consider using a [TextButton] instead, or
/// if that isn't appropriate, at least using an [InkWell] instead of
/// [GestureDetector].
///
/// To make sections of the text interactive, use [RichText] and specify a
/// [TapGestureRecognizer] as the [TextSpan.recognizer] of the relevant part of
/// the text.
///
/// ## Selection
///
/// [TText] is not selectable by default. To make a [TText] selectable, one can
/// wrap a subtree with a [SelectionArea] widget. To exclude a part of a subtree
/// under [SelectionArea] from selection, once can also wrap that part of the
/// subtree with [SelectionContainer.disabled].
///
/// {@tool dartpad}
/// This sample demonstrates how to disable selection for a Text under a
/// SelectionArea.
///
/// ** See code in examples/api/lib/material/selection_container/selection_container_disabled.0.dart **
/// {@end-tool}
///
/// See also:
///
///  * [RichText], which gives you more control over the text styles.
///  * [DefaultTextStyle], which sets default styles for [TText] widgets.
///  * [SelectableRegion], which provides an overview of the selection system.
class TText extends StatelessWidget {
  /// Creates a text widget.
  ///
  /// If the [style] argument is null, the text will use the style from the
  /// closest enclosing [DefaultTextStyle].
  ///
  /// The [overflow] property's behavior is affected by the [softWrap] argument.
  /// If the [softWrap] is true or null, the glyph causing overflow, and those
  /// that follow, will not be rendered. Otherwise, it will be shown with the
  /// given overflow option.
  const TText(
    String this.data, {
    super.key,
    this.style,
    this.strutStyle,
    this.textAlign,
    this.textDirection,
    this.locale,
    this.softWrap,
    this.overflow,
    @Deprecated(
      'Use textScaler instead. '
      'Use of textScaleFactor was deprecated in preparation for the upcoming nonlinear text scaling support. '
      'This feature was deprecated after v3.12.0-2.0.pre.',
    )
    this.textScaleFactor,
    this.textScaler,
    this.maxLines,
    this.semanticsLabel,
    this.textWidthBasis,
    this.textHeightBehavior,
    this.selectionColor,
  }) : textSpan = null,
       assert(
         textScaler == null || textScaleFactor == null,
         'textScaleFactor is deprecated and cannot be specified when textScaler is specified.',
       );

  /// Creates a text widget with a [InlineSpan].
  ///
  /// The following subclasses of [InlineSpan] may be used to build rich text:
  ///
  /// * [TextSpan]s define text and children [InlineSpan]s.
  /// * [WidgetSpan]s define embedded inline widgets.
  ///
  /// See [RichText] which provides a lower-level way to draw text.
  const TText.rich(
    InlineSpan this.textSpan, {
    super.key,
    this.style,
    this.strutStyle,
    this.textAlign,
    this.textDirection,
    this.locale,
    this.softWrap,
    this.overflow,
    @Deprecated(
      'Use textScaler instead. '
      'Use of textScaleFactor was deprecated in preparation for the upcoming nonlinear text scaling support. '
      'This feature was deprecated after v3.12.0-2.0.pre.',
    )
    this.textScaleFactor,
    this.textScaler,
    this.maxLines,
    this.semanticsLabel,
    this.textWidthBasis,
    this.textHeightBehavior,
    this.selectionColor,
  }) : data = null,
       assert(
         textScaler == null || textScaleFactor == null,
         'textScaleFactor is deprecated and cannot be specified when textScaler is specified.',
       );

  /// The text to display.
  ///
  /// This will be null if a [textSpan] is provided instead.
  final String? data;

  /// The text to display as a [InlineSpan].
  ///
  /// This will be null if [data] is provided instead.
  final InlineSpan? textSpan;

  /// If non-null, the style to use for this text.
  ///
  /// If the style's "inherit" property is true, the style will be merged with
  /// the closest enclosing [DefaultTextStyle]. Otherwise, the style will
  /// replace the closest enclosing [DefaultTextStyle].
  final TextStyle? style;

  /// {@macro flutter.painting.textPainter.strutStyle}
  final StrutStyle? strutStyle;

  /// How the text should be aligned horizontally.
  final TextAlign? textAlign;

  /// The directionality of the text.
  ///
  /// This decides how [textAlign] values like [TextAlign.start] and
  /// [TextAlign.end] are interpreted.
  ///
  /// This is also used to disambiguate how to render bidirectional text. For
  /// example, if the [data] is an English phrase followed by a Hebrew phrase,
  /// in a [TextDirection.ltr] context the English phrase will be on the left
  /// and the Hebrew phrase to its right, while in a [TextDirection.rtl]
  /// context, the English phrase will be on the right and the Hebrew phrase on
  /// its left.
  ///
  /// Defaults to the ambient [Directionality], if any.
  final TextDirection? textDirection;

  /// Used to select a font when the same Unicode character can
  /// be rendered differently, depending on the locale.
  ///
  /// It's rarely necessary to set this property. By default its value
  /// is inherited from the enclosing app with `Localizations.localeOf(context)`.
  ///
  /// See [RenderParagraph.locale] for more information.
  final Locale? locale;

  /// Whether the text should break at soft line breaks.
  ///
  /// If false, the glyphs in the text will be positioned as if there was unlimited horizontal space.
  final bool? softWrap;

  /// How visual overflow should be handled.
  ///
  /// If this is null [TextStyle.overflow] will be used, otherwise the value
  /// from the nearest [DefaultTextStyle] ancestor will be used.
  final TextOverflow? overflow;

  /// Deprecated. Will be removed in a future version of Flutter. Use
  /// [textScaler] instead.
  ///
  /// The number of font pixels for each logical pixel.
  ///
  /// For example, if the text scale factor is 1.5, text will be 50% larger than
  /// the specified font size.
  ///
  /// The value given to the constructor as textScaleFactor. If null, will
  /// use the [MediaQueryData.textScaleFactor] obtained from the ambient
  /// [MediaQuery], or 1.0 if there is no [MediaQuery] in scope.
  @Deprecated(
    'Use textScaler instead. '
    'Use of textScaleFactor was deprecated in preparation for the upcoming nonlinear text scaling support. '
    'This feature was deprecated after v3.12.0-2.0.pre.',
  )
  final double? textScaleFactor;

  /// {@macro flutter.painting.textPainter.textScaler}
  final TextScaler? textScaler;

  /// An optional maximum number of lines for the text to span, wrapping if necessary.
  /// If the text exceeds the given number of lines, it will be truncated according
  /// to [overflow].
  ///
  /// If this is 1, text will not wrap. Otherwise, text will be wrapped at the
  /// edge of the box.
  ///
  /// If this is null, but there is an ambient [DefaultTextStyle] that specifies
  /// an explicit number for its [DefaultTextStyle.maxLines], then the
  /// [DefaultTextStyle] value will take precedence. You can use a [RichText]
  /// widget directly to entirely override the [DefaultTextStyle].
  final int? maxLines;

  /// {@template flutter.widgets.Text.semanticsLabel}
  /// An alternative semantics label for this text.
  ///
  /// If present, the semantics of this widget will contain this value instead
  /// of the actual text. This will overwrite any of the semantics labels applied
  /// directly to the [TextSpan]s.
  ///
  /// This is useful for replacing abbreviations or shorthands with the full
  /// text value:
  ///
  /// ```dart
  /// const Text(r'$$', semanticsLabel: 'Double dollars')
  /// ```
  /// {@endtemplate}
  final String? semanticsLabel;

  /// {@macro flutter.painting.textPainter.textWidthBasis}
  final TextWidthBasis? textWidthBasis;

  /// {@macro dart.ui.textHeightBehavior}
  final ui.TextHeightBehavior? textHeightBehavior;

  /// The color to use when painting the selection.
  ///
  /// This is ignored if [SelectionContainer.maybeOf] returns null
  /// in the [BuildContext] of the [TText] widget.
  ///
  /// If null, the ambient [DefaultSelectionStyle] is used (if any); failing
  /// that, the selection color defaults to [DefaultSelectionStyle.defaultColor]
  /// (semi-transparent grey).
  final Color? selectionColor;

  @override
  Widget build(BuildContext context) {
    final defaultTextStyle = DefaultTextStyle.of(context);
    var effectiveTextStyle = style;
    if (style == null || style!.inherit) {
      effectiveTextStyle = defaultTextStyle.style.merge(style);
    }
    if (MediaQuery.boldTextOf(context)) {
      effectiveTextStyle = effectiveTextStyle!.merge(
        const TextStyle(fontWeight: FontWeight.bold),
      );
    }
    final registrar = SelectionContainer.maybeOf(context);
    final textScaler = switch ((this.textScaler, textScaleFactor)) {
      (final TextScaler textScaler, _) => textScaler,
      // For unmigrated apps, fall back to textScaleFactor.
      (null, final double textScaleFactor) => TextScaler.linear(
        textScaleFactor,
      ),
      (null, null) => MediaQuery.textScalerOf(context),
    };
    late Widget result;
    if (registrar != null) {
      result = MouseRegion(
        cursor:
            DefaultSelectionStyle.of(context).mouseCursor ??
            SystemMouseCursors.text,
        child: _SelectableTextContainer(
          textAlign: textAlign ?? defaultTextStyle.textAlign ?? TextAlign.start,
          textDirection: textDirection,
          // RichText uses Directionality.of to obtain a default if this is null.
          locale: locale,
          // RichText uses Localizations.localeOf to obtain a default if this is null
          softWrap: softWrap ?? defaultTextStyle.softWrap,
          overflow:
              overflow ??
              effectiveTextStyle?.overflow ??
              defaultTextStyle.overflow,
          textScaler: textScaler,
          maxLines: maxLines ?? defaultTextStyle.maxLines,
          strutStyle: strutStyle,
          textWidthBasis: textWidthBasis ?? defaultTextStyle.textWidthBasis,
          textHeightBehavior:
              textHeightBehavior ??
              defaultTextStyle.textHeightBehavior ??
              DefaultTextHeightBehavior.maybeOf(context),
          selectionColor:
              selectionColor ??
              DefaultSelectionStyle.of(context).selectionColor ??
              DefaultSelectionStyle.defaultColor,
          text: TextSpan(
            style: effectiveTextStyle,
            text: data,
            children: textSpan != null ? <InlineSpan>[textSpan!] : null,
          ),
        ),
      );
    } else {
      result = RichText(
        textAlign: textAlign ?? defaultTextStyle.textAlign ?? TextAlign.start,
        textDirection: textDirection,
        // RichText uses Directionality.of to obtain a default if this is null.
        locale: locale,
        // RichText uses Localizations.localeOf to obtain a default if this is null
        softWrap: softWrap ?? defaultTextStyle.softWrap,
        overflow:
            overflow ??
            effectiveTextStyle?.overflow ??
            defaultTextStyle.overflow,
        textScaler: textScaler,
        maxLines: maxLines ?? defaultTextStyle.maxLines,
        strutStyle: strutStyle,
        textWidthBasis: textWidthBasis ?? defaultTextStyle.textWidthBasis,
        textHeightBehavior:
            textHeightBehavior ??
            defaultTextStyle.textHeightBehavior ??
            DefaultTextHeightBehavior.maybeOf(context),
        selectionColor:
            selectionColor ??
            DefaultSelectionStyle.of(context).selectionColor ??
            DefaultSelectionStyle.defaultColor,
        text: TextSpan(
          style: effectiveTextStyle,
          text: data,
          children: textSpan != null ? <InlineSpan>[textSpan!] : null,
        ),
      );
    }
    if (semanticsLabel != null) {
      result = Semantics(
        textDirection: textDirection,
        label: semanticsLabel,
        child: ExcludeSemantics(child: result),
      );
    }
    return result;
  }

  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(StringProperty('data', data, showName: false));
    if (textSpan != null) {
      properties.add(
        textSpan!.toDiagnosticsNode(
          name: 'textSpan',
          style: DiagnosticsTreeStyle.transition,
        ),
      );
    }
    style?.debugFillProperties(properties);
    properties.add(
      EnumProperty<TextAlign>('textAlign', textAlign, defaultValue: null),
    );
    properties.add(
      EnumProperty<TextDirection>(
        'textDirection',
        textDirection,
        defaultValue: null,
      ),
    );
    properties.add(
      DiagnosticsProperty<Locale>('locale', locale, defaultValue: null),
    );
    properties.add(
      FlagProperty(
        'softWrap',
        value: softWrap,
        ifTrue: 'wrapping at box width',
        ifFalse: 'no wrapping except at line break characters',
        showName: true,
      ),
    );
    properties.add(
      EnumProperty<TextOverflow>('overflow', overflow, defaultValue: null),
    );
    properties.add(
      DoubleProperty('textScaleFactor', textScaleFactor, defaultValue: null),
    );
    properties.add(IntProperty('maxLines', maxLines, defaultValue: null));
    properties.add(
      EnumProperty<TextWidthBasis>(
        'textWidthBasis',
        textWidthBasis,
        defaultValue: null,
      ),
    );
    properties.add(
      DiagnosticsProperty<ui.TextHeightBehavior>(
        'textHeightBehavior',
        textHeightBehavior,
        defaultValue: null,
      ),
    );
    if (semanticsLabel != null) {
      properties.add(StringProperty('semanticsLabel', semanticsLabel));
    }
  }
}

class _SelectableTextContainer extends StatefulWidget {
  const _SelectableTextContainer({
    required this.text,
    required this.textAlign,
    this.textDirection,
    required this.softWrap,
    required this.overflow,
    required this.textScaler,
    this.maxLines,
    this.locale,
    this.strutStyle,
    required this.textWidthBasis,
    this.textHeightBehavior,
    required this.selectionColor,
  });

  final InlineSpan text;
  final TextAlign textAlign;
  final TextDirection? textDirection;
  final bool softWrap;
  final TextOverflow overflow;
  final TextScaler textScaler;
  final int? maxLines;
  final Locale? locale;
  final StrutStyle? strutStyle;
  final TextWidthBasis textWidthBasis;
  final ui.TextHeightBehavior? textHeightBehavior;
  final Color selectionColor;

  @override
  State<_SelectableTextContainer> createState() =>
      _SelectableTextContainerState();
}

class _SelectableTextContainerState extends State<_SelectableTextContainer> {
  late final _SelectableTextContainerDelegate _selectionDelegate;
  final GlobalKey _textKey = GlobalKey();

  @override
  void initState() {
    super.initState();
    _selectionDelegate = _SelectableTextContainerDelegate(_textKey);
  }

  @override
  void dispose() {
    _selectionDelegate.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => SelectionContainer(
    delegate: _selectionDelegate,
    // Use [_RichText] wrapper so the underlying [RenderParagraph] can register
    // its [Selectable]s to the [SelectionContainer] created by this widget.
    child: _RichText(
      textKey: _textKey,
      textAlign: widget.textAlign,
      textDirection: widget.textDirection,
      locale: widget.locale,
      softWrap: widget.softWrap,
      overflow: widget.overflow,
      textScaler: widget.textScaler,
      maxLines: widget.maxLines,
      strutStyle: widget.strutStyle,
      textWidthBasis: widget.textWidthBasis,
      textHeightBehavior: widget.textHeightBehavior,
      selectionColor: widget.selectionColor,
      text: widget.text,
    ),
  );
}

class _RichText extends StatelessWidget {
  const _RichText({
    this.textKey,
    required this.text,
    required this.textAlign,
    this.textDirection,
    required this.softWrap,
    required this.overflow,
    required this.textScaler,
    this.maxLines,
    this.locale,
    this.strutStyle,
    required this.textWidthBasis,
    this.textHeightBehavior,
    required this.selectionColor,
  });

  final GlobalKey? textKey;
  final InlineSpan text;
  final TextAlign textAlign;
  final TextDirection? textDirection;
  final bool softWrap;
  final TextOverflow overflow;
  final TextScaler textScaler;
  final int? maxLines;
  final Locale? locale;
  final StrutStyle? strutStyle;
  final TextWidthBasis textWidthBasis;
  final ui.TextHeightBehavior? textHeightBehavior;
  final Color selectionColor;

  @override
  Widget build(BuildContext context) {
    final registrar = SelectionContainer.maybeOf(context);
    return RichText(
      key: textKey,
      textAlign: textAlign,
      textDirection: textDirection,
      locale: locale,
      softWrap: softWrap,
      overflow: overflow,
      textScaler: textScaler,
      maxLines: maxLines,
      strutStyle: strutStyle,
      textWidthBasis: textWidthBasis,
      textHeightBehavior: textHeightBehavior,
      selectionRegistrar: registrar,
      selectionColor: selectionColor,
      text: text,
    );
  }
}

// In practice some selectables like widgetspan shift several pixels. So when
// the vertical position diff is within the threshold, compare the horizontal
// position to make the compareScreenOrder function more robust.
const double _kSelectableVerticalComparingThreshold = 3.0;

class _SelectableTextContainerDelegate
    extends MultiSelectableSelectionContainerDelegate {
  _SelectableTextContainerDelegate(GlobalKey textKey) : _textKey = textKey;

  final GlobalKey _textKey;

  TRenderParagraph get paragraph =>
      _textKey.currentContext!.findRenderObject()! as TRenderParagraph;

  @override
  SelectionResult handleSelectParagraph(SelectParagraphSelectionEvent event) {
    final result = _handleSelectParagraph(event);
    if (currentSelectionStartIndex != -1) {
      _hasReceivedStartEvent.add(selectables[currentSelectionStartIndex]);
    }
    if (currentSelectionEndIndex != -1) {
      _hasReceivedEndEvent.add(selectables[currentSelectionEndIndex]);
    }
    _updateLastEdgeEventsFromGeometries();
    return result;
  }

  SelectionResult _handleSelectParagraph(SelectParagraphSelectionEvent event) {
    if (event.absorb) {
      for (var index = 0; index < selectables.length; index += 1) {
        dispatchSelectionEventToChild(selectables[index], event);
      }
      currentSelectionStartIndex = 0;
      currentSelectionEndIndex = selectables.length - 1;
      return SelectionResult.next;
    }

    // First pass, if the position is on a placeholder then dispatch the selection
    // event to the [Selectable] at the location and terminate.
    for (var index = 0; index < selectables.length; index += 1) {
      final selectableIsPlaceholder = !paragraph.selectableBelongsToParagraph(
        selectables[index],
      );
      if (selectableIsPlaceholder &&
          selectables[index].boundingBoxes.isNotEmpty) {
        for (final rect in selectables[index].boundingBoxes) {
          final globalRect = MatrixUtils.transformRect(
            selectables[index].getTransformTo(null),
            rect,
          );
          if (globalRect.contains(event.globalPosition)) {
            currentSelectionStartIndex = currentSelectionEndIndex = index;
            return dispatchSelectionEventToChild(selectables[index], event);
          }
        }
      }
    }

    SelectionResult? lastSelectionResult;
    var foundStart = false;
    int? lastNextIndex;
    for (var index = 0; index < selectables.length; index += 1) {
      if (!paragraph.selectableBelongsToParagraph(selectables[index])) {
        if (foundStart) {
          final SelectionEvent synthesizedEvent = SelectParagraphSelectionEvent(
            globalPosition: event.globalPosition,
            absorb: true,
          );
          final result = dispatchSelectionEventToChild(
            selectables[index],
            synthesizedEvent,
          );
          if (selectables.length - 1 == index) {
            currentSelectionEndIndex = index;
            _flushInactiveSelections();
            return result;
          }
        }
        continue;
      }
      final existingGeometry = selectables[index].value;
      lastSelectionResult = dispatchSelectionEventToChild(
        selectables[index],
        event,
      );
      if (index == selectables.length - 1 &&
          lastSelectionResult == SelectionResult.next) {
        if (foundStart) {
          currentSelectionEndIndex = index;
        } else {
          currentSelectionStartIndex = currentSelectionEndIndex = index;
        }
        return SelectionResult.next;
      }
      if (lastSelectionResult == SelectionResult.next) {
        if (selectables[index].value == existingGeometry && !foundStart) {
          lastNextIndex = index;
        }
        if (selectables[index].value != existingGeometry && !foundStart) {
          assert(selectables[index].boundingBoxes.isNotEmpty);
          assert(selectables[index].value.selectionRects.isNotEmpty);
          final selectionAtStartOfSelectable = selectables[index]
              .boundingBoxes[0]
              .overlaps(selectables[index].value.selectionRects[0]);
          var startIndex = 0;
          if (lastNextIndex != null && selectionAtStartOfSelectable) {
            startIndex = lastNextIndex + 1;
          } else {
            startIndex = lastNextIndex == null && selectionAtStartOfSelectable
                ? 0
                : index;
          }
          for (var i = startIndex; i < index; i += 1) {
            final SelectionEvent synthesizedEvent =
                SelectParagraphSelectionEvent(
                  globalPosition: event.globalPosition,
                  absorb: true,
                );
            dispatchSelectionEventToChild(selectables[i], synthesizedEvent);
          }
          currentSelectionStartIndex = startIndex;
          foundStart = true;
        }
        continue;
      }
      if (index == 0 && lastSelectionResult == SelectionResult.previous) {
        return SelectionResult.previous;
      }
      if (selectables[index].value != existingGeometry) {
        if (!foundStart && lastNextIndex == null) {
          currentSelectionStartIndex = 0;
          for (var i = 0; i < index; i += 1) {
            final SelectionEvent synthesizedEvent =
                SelectParagraphSelectionEvent(
                  globalPosition: event.globalPosition,
                  absorb: true,
                );
            dispatchSelectionEventToChild(selectables[i], synthesizedEvent);
          }
        }
        currentSelectionEndIndex = index;
        // Geometry has changed as a result of select paragraph, need to clear the
        // selection of other selectables to keep selection in sync.
        _flushInactiveSelections();
      }
      return SelectionResult.end;
    }
    assert(lastSelectionResult == null);
    return SelectionResult.end;
  }

  /// Initializes the selection of the selectable children.
  ///
  /// The goal is to find the selectable child that contains the selection edge.
  /// Returns [SelectionResult.end] if the selection edge ends on any of the
  /// children. Otherwise, it returns [SelectionResult.previous] if the selection
  /// does not reach any of its children. Returns [SelectionResult.next]
  /// if the selection reaches the end of its children.
  ///
  /// Ideally, this method should only be called twice at the beginning of the
  /// drag selection, once for start edge update event, once for end edge update
  /// event.
  SelectionResult _initSelection(
    SelectionEdgeUpdateEvent event, {
    required bool isEnd,
  }) {
    assert(
      (isEnd && currentSelectionEndIndex == -1) ||
          (!isEnd && currentSelectionStartIndex == -1),
    );
    SelectionResult? finalResult;
    // Begin the search for the selection edge at the opposite edge if it exists.
    final hasOppositeEdge = isEnd
        ? currentSelectionStartIndex != -1
        : currentSelectionEndIndex != -1;
    var newIndex = switch ((isEnd, hasOppositeEdge)) {
      (true, true) => currentSelectionStartIndex,
      (true, false) => 0,
      (false, true) => currentSelectionEndIndex,
      (false, false) => 0,
    };
    bool? forward;
    late SelectionResult currentSelectableResult;
    // This loop sends the selection event to one of the following to determine
    // the direction of the search.
    //  - The opposite edge index if it exists.
    //  - Index 0 if the opposite edge index does not exist.
    //
    // If the result is `SelectionResult.next`, this loop look backward.
    // Otherwise, it looks forward.
    //
    // The terminate condition are:
    // 1. the selectable returns end, pending, none.
    // 2. the selectable returns previous when looking forward.
    // 2. the selectable returns next when looking backward.
    while (newIndex < selectables.length &&
        newIndex >= 0 &&
        finalResult == null) {
      currentSelectableResult = dispatchSelectionEventToChild(
        selectables[newIndex],
        event,
      );
      switch (currentSelectableResult) {
        case SelectionResult.end:
        case SelectionResult.pending:
        case SelectionResult.none:
          finalResult = currentSelectableResult;
        case SelectionResult.next:
          if (forward == false) {
            newIndex += 1;
            finalResult = SelectionResult.end;
          } else if (newIndex == selectables.length - 1) {
            finalResult = currentSelectableResult;
          } else {
            forward = true;
            newIndex += 1;
          }
        case SelectionResult.previous:
          if (forward ?? false) {
            newIndex -= 1;
            finalResult = SelectionResult.end;
          } else if (newIndex == 0) {
            finalResult = currentSelectableResult;
          } else {
            forward = false;
            newIndex -= 1;
          }
      }
    }
    if (isEnd) {
      currentSelectionEndIndex = newIndex;
    } else {
      currentSelectionStartIndex = newIndex;
    }
    _flushInactiveSelections();
    return finalResult!;
  }

  SelectionResult _adjustSelection(
    SelectionEdgeUpdateEvent event, {
    required bool isEnd,
  }) {
    assert(() {
      if (isEnd) {
        assert(
          currentSelectionEndIndex < selectables.length &&
              currentSelectionEndIndex >= 0,
        );
        return true;
      }
      assert(
        currentSelectionStartIndex < selectables.length &&
            currentSelectionStartIndex >= 0,
      );
      return true;
    }());
    SelectionResult? finalResult;
    // Determines if the edge being adjusted is within the current viewport.
    //  - If so, we begin the search for the new selection edge position at the
    //    currentSelectionEndIndex/currentSelectionStartIndex.
    //  - If not, we attempt to locate the new selection edge starting from
    //    the opposite end.
    //  - If neither edge is in the current viewport, the search for the new
    //    selection edge position begins at 0.
    //
    // This can happen when there is a scrollable child and the edge being adjusted
    // has been scrolled out of view.
    final isCurrentEdgeWithinViewport = isEnd
        ? value.endSelectionPoint != null
        : value.startSelectionPoint != null;
    final isOppositeEdgeWithinViewport = isEnd
        ? value.startSelectionPoint != null
        : value.endSelectionPoint != null;
    var newIndex = switch ((
      isEnd,
      isCurrentEdgeWithinViewport,
      isOppositeEdgeWithinViewport,
    )) {
      (true, true, true) => currentSelectionEndIndex,
      (true, true, false) => currentSelectionEndIndex,
      (true, false, true) => currentSelectionStartIndex,
      (true, false, false) => 0,
      (false, true, true) => currentSelectionStartIndex,
      (false, true, false) => currentSelectionStartIndex,
      (false, false, true) => currentSelectionEndIndex,
      (false, false, false) => 0,
    };
    bool? forward;
    late SelectionResult currentSelectableResult;
    // This loop sends the selection event to one of the following to determine
    // the direction of the search.
    //  - currentSelectionEndIndex/currentSelectionStartIndex if the current edge
    //    is in the current viewport.
    //  - The opposite edge index if the current edge is not in the current viewport.
    //  - Index 0 if neither edge is in the current viewport.
    //
    // If the result is `SelectionResult.next`, this loop look backward.
    // Otherwise, it looks forward.
    //
    // The terminate condition are:
    // 1. the selectable returns end, pending, none.
    // 2. the selectable returns previous when looking forward.
    // 2. the selectable returns next when looking backward.
    while (newIndex < selectables.length &&
        newIndex >= 0 &&
        finalResult == null) {
      currentSelectableResult = dispatchSelectionEventToChild(
        selectables[newIndex],
        event,
      );
      switch (currentSelectableResult) {
        case SelectionResult.end:
        case SelectionResult.pending:
        case SelectionResult.none:
          finalResult = currentSelectableResult;
        case SelectionResult.next:
          if (forward == false) {
            newIndex += 1;
            finalResult = SelectionResult.end;
          } else if (newIndex == selectables.length - 1) {
            finalResult = currentSelectableResult;
          } else {
            forward = true;
            newIndex += 1;
          }
        case SelectionResult.previous:
          if (forward ?? false) {
            newIndex -= 1;
            finalResult = SelectionResult.end;
          } else if (newIndex == 0) {
            finalResult = currentSelectableResult;
          } else {
            forward = false;
            newIndex -= 1;
          }
      }
    }
    if (isEnd) {
      final forwardSelection =
          currentSelectionEndIndex >= currentSelectionStartIndex;
      if (forward != null &&
          ((!forwardSelection &&
                  forward &&
                  newIndex >= currentSelectionStartIndex) ||
              (forwardSelection &&
                  !forward &&
                  newIndex <= currentSelectionStartIndex))) {
        currentSelectionStartIndex = currentSelectionEndIndex;
      }
      currentSelectionEndIndex = newIndex;
    } else {
      final forwardSelection =
          currentSelectionEndIndex >= currentSelectionStartIndex;
      if (forward != null &&
          ((!forwardSelection &&
                  !forward &&
                  newIndex <= currentSelectionEndIndex) ||
              (forwardSelection &&
                  forward &&
                  newIndex >= currentSelectionEndIndex))) {
        currentSelectionEndIndex = currentSelectionStartIndex;
      }
      currentSelectionStartIndex = newIndex;
    }
    _flushInactiveSelections();
    return finalResult!;
  }

  /// The compare function this delegate used for determining the selection
  /// order of the [Selectable]s.
  ///
  /// Sorts the [Selectable]s by their top left [Rect].
  @override
  Comparator<Selectable> get compareOrder => _compareScreenOrder;

  static int _compareScreenOrder(Selectable a, Selectable b) {
    // Attempt to sort the selectables under a [_SelectableTextContainerDelegate]
    // by the top left rect.
    final rectA = MatrixUtils.transformRect(
      a.getTransformTo(null),
      a.boundingBoxes.first,
    );
    final rectB = MatrixUtils.transformRect(
      b.getTransformTo(null),
      b.boundingBoxes.first,
    );
    final result = _compareVertically(rectA, rectB);
    if (result != 0) {
      return result;
    }
    return _compareHorizontally(rectA, rectB);
  }

  /// Compares two rectangles in the screen order solely by their vertical
  /// positions.
  ///
  /// Returns positive if a is lower, negative if a is higher, 0 if their
  /// order can't be determine solely by their vertical position.
  static int _compareVertically(Rect a, Rect b) {
    // The rectangles overlap so defer to horizontal comparison.
    if ((a.top - b.top < _kSelectableVerticalComparingThreshold &&
            a.bottom - b.bottom > -_kSelectableVerticalComparingThreshold) ||
        (b.top - a.top < _kSelectableVerticalComparingThreshold &&
            b.bottom - a.bottom > -_kSelectableVerticalComparingThreshold)) {
      return 0;
    }
    if ((a.top - b.top).abs() > _kSelectableVerticalComparingThreshold) {
      return a.top > b.top ? 1 : -1;
    }
    return a.bottom > b.bottom ? 1 : -1;
  }

  /// Compares two rectangles in the screen order by their horizontal positions
  /// assuming one of the rectangles enclose the other rect vertically.
  ///
  /// Returns positive if a is lower, negative if a is higher.
  static int _compareHorizontally(Rect a, Rect b) {
    // a encloses b.
    if (a.left - b.left < precisionErrorTolerance &&
        a.right - b.right > -precisionErrorTolerance) {
      return -1;
    }
    // b encloses a.
    if (b.left - a.left < precisionErrorTolerance &&
        b.right - a.right > -precisionErrorTolerance) {
      return 1;
    }
    if ((a.left - b.left).abs() > precisionErrorTolerance) {
      return a.left > b.left ? 1 : -1;
    }
    return a.right > b.right ? 1 : -1;
  }

  // From [SelectableRegion].

  // Clears the selection on all selectables not in the range of
  // currentSelectionStartIndex..currentSelectionEndIndex.
  //
  // If one of the edges does not exist, then this method will clear the selection
  // in all selectables except the existing edge.
  //
  // If neither of the edges exist this method immediately returns.
  void _flushInactiveSelections() {
    if (currentSelectionStartIndex == -1 && currentSelectionEndIndex == -1) {
      return;
    }
    if (currentSelectionStartIndex == -1 || currentSelectionEndIndex == -1) {
      final skipIndex = currentSelectionStartIndex == -1
          ? currentSelectionEndIndex
          : currentSelectionStartIndex;
      selectables
          .where((Selectable target) => target != selectables[skipIndex])
          .forEach(
            (Selectable target) => dispatchSelectionEventToChild(
              target,
              const ClearSelectionEvent(),
            ),
          );
      return;
    }
    final int skipStart = min(
      currentSelectionStartIndex,
      currentSelectionEndIndex,
    );
    final int skipEnd = max(
      currentSelectionStartIndex,
      currentSelectionEndIndex,
    );
    for (var index = 0; index < selectables.length; index += 1) {
      if (index >= skipStart && index <= skipEnd) {
        continue;
      }
      dispatchSelectionEventToChild(
        selectables[index],
        const ClearSelectionEvent(),
      );
    }
  }

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

  /// Selects a word in a selectable at the location
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

    if (event.granularity == TextGranularity.paragraph) {
      if (event.type == SelectionEventType.endEdgeUpdate) {
        return currentSelectionEndIndex == -1
            ? _initSelection(event, isEnd: true)
            : _adjustSelection(event, isEnd: true);
      }
      return currentSelectionStartIndex == -1
          ? _initSelection(event, isEnd: false)
          : _adjustSelection(event, isEnd: false);
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

/// A paragraph of rich text.
///
/// {@youtube 560 315 https://www.youtube.com/watch?v=rykDVh-QFfw}
///
/// The [RichText] widget displays text that uses multiple different styles. The
/// text to display is described using a tree of [TextSpan] objects, each of
/// which has an associated style that is used for that subtree. The text might
/// break across multiple lines or might all be displayed on the same line
/// depending on the layout constraints.
///
/// Text displayed in a [RichText] widget must be explicitly styled. When
/// picking which style to use, consider using [DefaultTextStyle.of] the current
/// [BuildContext] to provide defaults. For more details on how to style text in
/// a [RichText] widget, see the documentation for [TextStyle].
///
/// Consider using the [TText] widget to integrate with the [DefaultTextStyle]
/// automatically. When all the text uses the same style, the default constructor
/// is less verbose. The [Text.rich] constructor allows you to style multiple
/// spans with the default text style while still allowing specified styles per
/// span.
///
/// {@tool snippet}
///
/// This sample demonstrates how to mix and match text with different text
/// styles using the [RichText] Widget. It displays the text "Hello bold world,"
/// emphasizing the word "bold" using a bold font weight.
///
/// ![](https://flutter.github.io/assets-for-api-docs/assets/widgets/rich_text.png)
///
/// ```dart
/// RichText(
///   text: TextSpan(
///     text: 'Hello ',
///     style: DefaultTextStyle.of(context).style,
///     children: const <TextSpan>[
///       TextSpan(text: 'bold', style: TextStyle(fontWeight: FontWeight.bold)),
///       TextSpan(text: ' world!'),
///     ],
///   ),
/// )
/// ```
/// {@end-tool}
///
/// ## Selections
///
/// To make this [RichText] Selectable, the [RichText] needs to be in the
/// subtree of a [SelectionArea] or [SelectableRegion] and a
/// [SelectionRegistrar] needs to be assigned to the
/// [RichText.selectionRegistrar]. One can use
/// [SelectionContainer.maybeOf] to get the [SelectionRegistrar] from a
/// context. This enables users to select the text in [RichText]s with mice or
/// touch events.
///
/// The [selectionColor] also needs to be set if the selection is enabled to
/// draw the selection highlights.
///
/// {@tool snippet}
///
/// This sample demonstrates how to assign a [SelectionRegistrar] for RichTexts
/// in the SelectionArea subtree.
///
/// ![](https://flutter.github.io/assets-for-api-docs/assets/widgets/rich_text.png)
///
/// ```dart
/// RichText(
///   text: const TextSpan(text: 'Hello'),
///   selectionRegistrar: SelectionContainer.maybeOf(context),
///   selectionColor: const Color(0xAF6694e8),
/// )
/// ```
/// {@end-tool}
///
/// See also:
///
///  * [TextStyle], which discusses how to style text.
///  * [TextSpan], which is used to describe the text in a paragraph.
///  * [TText], which automatically applies the ambient styles described by a
///    [DefaultTextStyle] to a single string.
///  * [Text.rich], a const text widget that provides similar functionality
///    as [RichText]. [Text.rich] will inherit [TextStyle] from [DefaultTextStyle].
///  * [SelectableRegion], which provides an overview of the selection system.
class RichText extends MultiChildRenderObjectWidget {
  /// Creates a paragraph of rich text.
  ///
  /// The [maxLines] property may be null (and indeed defaults to null), but if
  /// it is not null, it must be greater than zero.
  ///
  /// The [textDirection], if null, defaults to the ambient [Directionality],
  /// which in that case must not be null.
  RichText({
    super.key,
    required this.text,
    this.textAlign = TextAlign.start,
    this.textDirection,
    this.softWrap = true,
    this.overflow = TextOverflow.clip,
    @Deprecated(
      'Use textScaler instead. '
      'Use of textScaleFactor was deprecated in preparation for the upcoming nonlinear text scaling support. '
      'This feature was deprecated after v3.12.0-2.0.pre.',
    )
    double textScaleFactor = 1.0,
    TextScaler textScaler = TextScaler.noScaling,
    this.maxLines,
    this.locale,
    this.strutStyle,
    this.textWidthBasis = TextWidthBasis.parent,
    this.textHeightBehavior,
    this.selectionRegistrar,
    this.selectionColor,
  }) : assert(maxLines == null || maxLines > 0),
       assert(selectionRegistrar == null || selectionColor != null),
       assert(
         textScaleFactor == 1.0 || identical(textScaler, TextScaler.noScaling),
         'Use textScaler instead.',
       ),
       textScaler = _effectiveTextScalerFrom(textScaler, textScaleFactor),
       super(
         children: WidgetSpan.extractFromInlineSpan(
           text,
           _effectiveTextScalerFrom(textScaler, textScaleFactor),
         ),
       );

  static TextScaler _effectiveTextScalerFrom(
    TextScaler textScaler,
    double textScaleFactor,
  ) => switch ((textScaler, textScaleFactor)) {
    (final TextScaler scaler, 1.0) => scaler,
    (TextScaler.noScaling, final double textScaleFactor) => TextScaler.linear(
      textScaleFactor,
    ),
    (final TextScaler scaler, _) => scaler,
  };

  /// The text to display in this widget.
  final InlineSpan text;

  /// How the text should be aligned horizontally.
  final TextAlign textAlign;

  /// The directionality of the text.
  ///
  /// This decides how [textAlign] values like [TextAlign.start] and
  /// [TextAlign.end] are interpreted.
  ///
  /// This is also used to disambiguate how to render bidirectional text. For
  /// example, if the [text] is an English phrase followed by a Hebrew phrase,
  /// in a [TextDirection.ltr] context the English phrase will be on the left
  /// and the Hebrew phrase to its right, while in a [TextDirection.rtl]
  /// context, the English phrase will be on the right and the Hebrew phrase on
  /// its left.
  ///
  /// Defaults to the ambient [Directionality], if any. If there is no ambient
  /// [Directionality], then this must not be null.
  final TextDirection? textDirection;

  /// Whether the text should break at soft line breaks.
  ///
  /// If false, the glyphs in the text will be positioned as if there was unlimited horizontal space.
  final bool softWrap;

  /// How visual overflow should be handled.
  final TextOverflow overflow;

  /// Deprecated. Will be removed in a future version of Flutter. Use
  /// [textScaler] instead.
  ///
  /// The number of font pixels for each logical pixel.
  ///
  /// For example, if the text scale factor is 1.5, text will be 50% larger than
  /// the specified font size.
  @Deprecated(
    'Use textScaler instead. '
    'Use of textScaleFactor was deprecated in preparation for the upcoming nonlinear text scaling support. '
    'This feature was deprecated after v3.12.0-2.0.pre.',
  )
  double get textScaleFactor => textScaler.textScaleFactor;

  /// {@macro flutter.painting.textPainter.textScaler}
  final TextScaler textScaler;

  /// An optional maximum number of lines for the text to span, wrapping if necessary.
  /// If the text exceeds the given number of lines, it will be truncated according
  /// to [overflow].
  ///
  /// If this is 1, text will not wrap. Otherwise, text will be wrapped at the
  /// edge of the box.
  final int? maxLines;

  /// Used to select a font when the same Unicode character can
  /// be rendered differently, depending on the locale.
  ///
  /// It's rarely necessary to set this property. By default its value
  /// is inherited from the enclosing app with `Localizations.localeOf(context)`.
  ///
  /// See [RenderParagraph.locale] for more information.
  final Locale? locale;

  /// {@macro flutter.painting.textPainter.strutStyle}
  final StrutStyle? strutStyle;

  /// {@macro flutter.painting.textPainter.textWidthBasis}
  final TextWidthBasis textWidthBasis;

  /// {@macro dart.ui.textHeightBehavior}
  final ui.TextHeightBehavior? textHeightBehavior;

  /// The [SelectionRegistrar] this rich text is subscribed to.
  ///
  /// If this is set, [selectionColor] must be non-null.
  final SelectionRegistrar? selectionRegistrar;

  /// The color to use when painting the selection.
  ///
  /// This is ignored if [selectionRegistrar] is null.
  ///
  /// See the section on selections in the [RichText] top-level API
  /// documentation for more details on enabling selection in [RichText]
  /// widgets.
  final Color? selectionColor;

  @override
  TRenderParagraph createRenderObject(BuildContext context) {
    assert(textDirection != null || debugCheckHasDirectionality(context));
    return TRenderParagraph(
      text,
      textAlign: textAlign,
      textDirection: textDirection ?? Directionality.of(context),
      softWrap: softWrap,
      overflow: overflow,
      textScaler: textScaler,
      maxLines: maxLines,
      strutStyle: strutStyle,
      textWidthBasis: textWidthBasis,
      textHeightBehavior: textHeightBehavior,
      locale: locale ?? Localizations.maybeLocaleOf(context),
      registrar: selectionRegistrar,
      selectionColor: selectionColor,
    );
  }

  @override
  void updateRenderObject(BuildContext context, TRenderParagraph renderObject) {
    assert(textDirection != null || debugCheckHasDirectionality(context));
    renderObject
      ..text = text
      ..textAlign = textAlign
      ..textDirection = textDirection ?? Directionality.of(context)
      ..softWrap = softWrap
      ..overflow = overflow
      ..textScaler = textScaler
      ..maxLines = maxLines
      ..strutStyle = strutStyle
      ..textWidthBasis = textWidthBasis
      ..textHeightBehavior = textHeightBehavior
      ..locale = locale ?? Localizations.maybeLocaleOf(context)
      ..registrar = selectionRegistrar
      ..selectionColor = selectionColor;
  }

  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(
      EnumProperty<TextAlign>(
        'textAlign',
        textAlign,
        defaultValue: TextAlign.start,
      ),
    );
    properties.add(
      EnumProperty<TextDirection>(
        'textDirection',
        textDirection,
        defaultValue: null,
      ),
    );
    properties.add(
      FlagProperty(
        'softWrap',
        value: softWrap,
        ifTrue: 'wrapping at box width',
        ifFalse: 'no wrapping except at line break characters',
        showName: true,
      ),
    );
    properties.add(
      EnumProperty<TextOverflow>(
        'overflow',
        overflow,
        defaultValue: TextOverflow.clip,
      ),
    );
    properties.add(
      DiagnosticsProperty<TextScaler>(
        'textScaler',
        textScaler,
        defaultValue: TextScaler.noScaling,
      ),
    );
    properties.add(IntProperty('maxLines', maxLines, ifNull: 'unlimited'));
    properties.add(
      EnumProperty<TextWidthBasis>(
        'textWidthBasis',
        textWidthBasis,
        defaultValue: TextWidthBasis.parent,
      ),
    );
    properties.add(StringProperty('text', text.toPlainText()));
    properties.add(
      DiagnosticsProperty<Locale>('locale', locale, defaultValue: null),
    );
    properties.add(
      DiagnosticsProperty<StrutStyle>(
        'strutStyle',
        strutStyle,
        defaultValue: null,
      ),
    );
    properties.add(
      DiagnosticsProperty<TextHeightBehavior>(
        'textHeightBehavior',
        textHeightBehavior,
        defaultValue: null,
      ),
    );
  }
}
