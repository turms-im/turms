import 'package:flutter/material.dart';

import 'index.dart';

ThemeData getLightTheme({String? fontFamily}) => ThemeData(
  useMaterial3: true,
  extensions: [AppThemeExtension.light],
  dividerColor: AppColors.gray5,
  colorScheme: const ColorScheme.light(primary: AppColors.primary),
  fontFamily: fontFamily,
  textSelectionTheme: const TextSelectionThemeData(cursorColor: Colors.black),
  inputDecorationTheme: const InputDecorationTheme(
    contentPadding: Sizes.paddingV4H8,
    border: InputBorder.none,
  ),
);

ThemeData getDarkTheme({String? fontFamily}) => ThemeData(
  useMaterial3: true,
  extensions: [AppThemeExtension.dark],
  dividerColor: AppColors.gray5,
  colorScheme: const ColorScheme.dark(primary: AppColors.primary),
  fontFamily: fontFamily,
  textSelectionTheme: const TextSelectionThemeData(cursorColor: Colors.black),
  inputDecorationTheme: const InputDecorationTheme(
    contentPadding: Sizes.paddingV4H8,
    border: InputBorder.none,
  ),
);
