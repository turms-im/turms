import 'dart:async';

import 'package:dotted_border/dotted_border.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:super_clipboard/super_clipboard.dart';
import 'package:super_drag_and_drop/super_drag_and_drop.dart';

import '../../../l10n/app_localizations.dart';
import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/app_theme_extension.dart';
import '../../../themes/sizes.dart';

class TDropZone extends ConsumerStatefulWidget {
  const TDropZone(
      {super.key,
      required this.formats,
      this.onDropOver,
      required this.onPerformDrop,
      this.onDropEnter,
      this.onDropLeave,
      this.onDropEnded,
      required this.child});

  final List<DataFormat> formats;
  final FutureOr<DropOperation> Function(DropOverEvent)? onDropOver;
  final Future<void> Function(PerformDropEvent) onPerformDrop;
  final void Function(DropEvent)? onDropEnter;
  final void Function(DropEvent)? onDropLeave;
  final void Function(DropEvent)? onDropEnded;
  final Widget child;

  @override
  ConsumerState createState() => _TDropZoneState();
}

class _TDropZoneState extends ConsumerState<TDropZone> {
  bool _dragging = false;

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return Stack(
      children: [
        DropRegion(
            formats: widget.formats,
            onDropOver: widget.onDropOver ?? _onDropOver,
            onDropEnter: _onDropEnter,
            onDropLeave: _onDropLeave,
            onPerformDrop: widget.onPerformDrop,
            child: widget.child),
        _buildMask(theme, appLocalizations)
      ],
    );
  }

  IgnorePointer _buildMask(ThemeData theme, AppLocalizations localizations) =>
      // Ignore pointer to not obstruct "DropRegion"
      IgnorePointer(
          child: AnimatedOpacity(
              opacity: _dragging ? 1.0 : 0.0,
              duration: const Duration(milliseconds: 100),
              child: Padding(
                padding: Sizes.paddingV4H4,
                child: DottedBorder(
                  borderType: BorderType.RRect,
                  dashPattern: [12, 10],
                  color: theme.primaryColor,
                  strokeWidth: 2,
                  radius: const Radius.circular(8),
                  child: ClipRRect(
                    child: ColoredBox(
                      color: Colors.white.withValues(alpha: 0.6),
                      child: Center(
                        child: Text(
                          localizations.dropFilesHere,
                          style: TextStyle(color: theme.primaryColor),
                        ),
                      ),
                    ),
                  ),
                ),
              )));

  DropOperation _onDropOver(DropOverEvent event) =>
      event.session.allowedOperations.contains(DropOperation.copy)
          ? DropOperation.copy
          : DropOperation.none;

  void _onDropEnter(DropEvent event) {
    _dragging = true;
    widget.onDropEnter?.call(event);
    setState(() {});
  }

  void _onDropLeave(DropEvent event) {
    _dragging = false;
    widget.onDropLeave?.call(event);
    setState(() {});
  }
}

extension DropSessionExtensions on DropSession {
  Future<List<DataReaderFile>> readFiles({
    bool includeDirectories = false,
  }) async {
    final futures = items.map((item) {
      final completer = Completer<DataReaderFile>();
      try {
        item.dataReader!.getFile(null, completer.complete,
            onError: completer.completeError);
      } catch (e) {
        completer.completeError(e);
      }
      return completer.future;
    });
    final files = await Future.wait(futures);
    if (includeDirectories) {
      return files;
    }
    return files
        .where((file) =>
            // The size of directory is null
            file.fileSize != null)
        .toList();
  }
}
