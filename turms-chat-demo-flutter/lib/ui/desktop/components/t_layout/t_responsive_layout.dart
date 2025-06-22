import 'package:flutter/widgets.dart';

class TResponsiveLayout extends StatelessWidget {
  const TResponsiveLayout({
    super.key,
    required this.portraitLayoutContent,
    required this.landscapeLayoutContent,
  });
  final Widget portraitLayoutContent;
  final Widget landscapeLayoutContent;

  @override
  Widget build(BuildContext context) => LayoutBuilder(
    builder: (context, constraints) {
      if (constraints.maxWidth > 600) {
        return landscapeLayoutContent;
      }
      return portraitLayoutContent;
    },
  );
}
