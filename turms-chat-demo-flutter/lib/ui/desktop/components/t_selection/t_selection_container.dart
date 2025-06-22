import 'package:flutter/rendering.dart';
import 'package:flutter/widgets.dart';

class TSelectionContainer extends StatefulWidget {
  const TSelectionContainer({
    super.key,
    required this.visible,
    required this.child,
  });

  final bool visible;
  final Widget child;

  @override
  State<TSelectionContainer> createState() => _TSelectionContainerState();
}

class _TSelectionContainerState extends State<TSelectionContainer> {
  SelectionRegistrar? registrar;

  @override
  Widget build(BuildContext context) {
    if (widget.visible) {
      final scope = context
          .dependOnInheritedWidgetOfExactType<TSelectionRegistrarScope>();
      if (scope == null) {
        return widget.child;
      }
      return SelectionRegistrarScope(
        registrar: scope.registrar,
        child: widget.child,
      );
    }
    final registrar = context
        .dependOnInheritedWidgetOfExactType<SelectionRegistrarScope>()
        ?.registrar;
    if (registrar == null) {
      return SelectionContainer.disabled(child: widget.child);
    }
    this.registrar = registrar;
    return TSelectionRegistrarScope(
      registrar: registrar,
      child: SelectionContainer.disabled(child: widget.child),
    );
  }
}

class TSelectionRegistrarScope extends InheritedWidget {
  const TSelectionRegistrarScope({
    super.key,
    required this.registrar,
    required super.child,
  });

  final SelectionRegistrar registrar;

  @override
  bool updateShouldNotify(TSelectionRegistrarScope oldWidget) =>
      registrar != oldWidget.registrar;

  static SelectionRegistrar? maybeOf(BuildContext context) => context
      .dependOnInheritedWidgetOfExactType<TSelectionRegistrarScope>()
      ?.registrar;
}
