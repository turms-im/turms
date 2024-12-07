import 'package:flutter/material.dart';

import '../../../themes/index.dart';
import 't_button.dart';

class TTextButton extends StatelessWidget {
  const TTextButton(
      {super.key,
      required this.text,
      this.textStyle,
      this.textStyleHovered,
      this.addContainer = true,
      this.containerWidth,
      this.containerHeight,
      this.containerColor,
      this.containerColorHovered,
      EdgeInsets? containerPadding,
      this.containerBorder,
      this.containerBorderHovered,
      this.isLoading = false,
      this.disabled = false,
      this.prefix,
      this.onTap})
      : containerPadding = containerPadding ??
            (containerHeight == null ? Sizes.paddingV8H16 : null);

  factory TTextButton.outlined(
          {required ThemeData theme,
          required String text,
          double? containerWidth,
          double? containerHeight,
          EdgeInsets? containerPadding,
          Widget? prefix,
          VoidCallback? onTap}) =>
      TTextButton(
        text: text,
        textStyle: const TextStyle(color: Colors.black),
        textStyleHovered: TextStyle(color: theme.primaryColor),
        containerBorder: Border.all(color: theme.dividerColor),
        containerBorderHovered: Border.all(color: theme.primaryColor),
        containerColor: Colors.white,
        containerWidth: containerWidth,
        containerHeight: containerHeight,
        containerPadding: containerPadding,
        prefix: prefix,
        onTap: onTap,
      );

  final bool addContainer;
  final double? containerWidth;
  final double? containerHeight;
  final Color? containerColor;
  final Color? containerColorHovered;
  final EdgeInsets? containerPadding;
  final BoxBorder? containerBorder;
  final BoxBorder? containerBorderHovered;
  final bool isLoading;
  final bool disabled;
  final VoidCallback? onTap;

  final String text;
  final TextStyle? textStyle;
  final TextStyle? textStyleHovered;

  final Widget? prefix;

  @override
  Widget build(BuildContext context) => TButton(
      addContainer: addContainer,
      containerWidth: containerWidth,
      containerHeight: containerHeight,
      containerColor: containerColor ?? context.theme.primaryColor,
      containerColorHovered: containerColorHovered,
      containerPadding: containerPadding,
      containerBorder: containerBorder,
      containerBorderHovered: containerBorderHovered,
      isLoading: isLoading,
      disabled: disabled,
      onTap: onTap,
      childHovered: IntrinsicWidth(
        child: Center(
          child: AnimatedDefaultTextStyle(
            style: textStyleHovered ??
                textStyle ??
                const TextStyle(color: Colors.white),
            duration: const Duration(milliseconds: 200),
            child: Text(
              text,
              textAlign: TextAlign.center,
            ),
          ),
        ),
      ),
      prefix: prefix,
      child: IntrinsicWidth(
        child: Center(
          child: AnimatedDefaultTextStyle(
            style: textStyle ?? const TextStyle(color: Colors.white),
            duration: const Duration(milliseconds: 200),
            child: Text(
              text,
              textAlign: TextAlign.center,
            ),
          ),
        ),
      ));
}
