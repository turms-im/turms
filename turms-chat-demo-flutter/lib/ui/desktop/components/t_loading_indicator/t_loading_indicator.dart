import 'package:flutter/cupertino.dart';

import '../../../themes/app_theme_extension.dart';

class TLoadingIndicator extends StatelessWidget {
  const TLoadingIndicator({super.key, required this.text});

  final String text;

  @override
  Widget build(BuildContext context) => Row(
        mainAxisAlignment: MainAxisAlignment.center,
        spacing: 8,
        children: [
          const RepaintBoundary(
            child: CupertinoActivityIndicator(),
          ),
          Text(
            text,
            style: context.appThemeExtension.descriptionTextStyle,
          ),
        ],
      );
}
