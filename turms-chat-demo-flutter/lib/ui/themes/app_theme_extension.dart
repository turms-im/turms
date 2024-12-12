import 'package:flutter/material.dart';

import 'colors.dart';
import 'fonts.dart';
import 'sizes.dart';
import 'styles.dart';

// TODO: we need to provide different font styles for different fonts
// as even using the same font properties, the text may display quite different.
class AppThemeExtension extends ThemeExtension<AppThemeExtension> {
  const AppThemeExtension({
    required this.themeMode,
    required this.successColor,
    required this.warningColor,
    required this.errorColor,
    required this.infoColor,
    required this.dangerColor,
    required this.dangerTextStyle,
    required this.highlightTextStyle,
    required this.maskColor,
    required this.avatarIconColor,
    required this.avatarBackgroundColor,
    required this.checkboxColor,
    required this.checkboxTextStyle,
    required this.iconButtonContainerHoveredColor,
    required this.iconButtonContainerPressedColor,
    required this.menuDecoration,
    required this.menuItemColor,
    required this.menuItemHoveredColor,
    required this.menuItemTextStyle,
    required this.popupDecoration,
    required this.tabTextStyle,
    required this.toastDecoration,
    required this.homePageBackgroundColor,
    required this.mainNavigationRailBackgroundColor,
    required this.mainNavigationRailIconColor,
    required this.subNavigationRailSearchBarBackgroundColor,
    required this.subNavigationRailLoadingIndicatorBackgroundColor,
    required this.subNavigationRailDividerColor,
    required this.chatSessionPaneDividerColor,
    required this.chatSessionDetailsDrawerBackgroundColor,
    required this.chatSessionMessageTextStyle,
    required this.chatSessionMessageEmojiTextStyle,
    required this.tileBackgroundColor,
    required this.tileBackgroundHighlightedColor,
    required this.tileBackgroundHoveredColor,
    required this.tileBackgroundFocusedColor,
    required this.conversationTileMessageTextStyle,
    required this.conversationTileDraftTextStyle,
    required this.conversationTileHighlightedTextStyle,
    required this.conversationTileTimestampTextStyle,
    required this.messageAttachmentColor,
    required this.messageAttachmentHoveredColor,
    required this.messageBubbleErrorIconBackgroundColor,
    required this.messageBubbleErrorIconColor,
    required this.fileTableTitleTextStyle,
    required this.fileTableCellTextStyle,
    required this.settingPageSubNavigationRailDividerColor,
    required this.dialogTitleTextStyleMedium,
    required this.dialogTitleTextStyleLarge,
    required this.descriptionTextStyle,
    required this.linkTextStyle,
    required this.linkHoveredTextStyle,
  });

  static final light = AppThemeExtension(
      themeMode: ThemeMode.light,
      successColor: AppColors.green6,
      warningColor: AppColors.gold6,
      errorColor: Colors.red,
      infoColor: AppColors.blue6,
      dangerColor: Colors.red,
      dangerTextStyle: const TextStyle(color: Colors.red),
      highlightTextStyle: const TextStyle(color: Colors.red),
      maskColor: Colors.black54,
      avatarIconColor: Colors.white,
      avatarBackgroundColor: const Color.fromARGB(255, 117, 117, 117),
      checkboxColor: AppColors.gray6,
      checkboxTextStyle:
          const TextStyle(color: Color(0xA6000000), fontSize: 16),
      // hsl(0, 0%, 91%)
      iconButtonContainerHoveredColor: const Color.fromARGB(255, 231, 231, 231),
      // hsl(0, 0%, 85%)
      iconButtonContainerPressedColor: const Color.fromARGB(255, 218, 218, 218),
      menuDecoration: BoxDecoration(
        color: Colors.white,
        borderRadius: Sizes.borderRadiusCircular2,
        border: Border.all(color: Colors.grey.shade400),
        // border: Border.all(color: AppColors.gray5),
        boxShadow: Styles.boxShadow,
      ),
      menuItemTextStyle: const TextStyle(
        fontSize: 12,
      ),
      menuItemColor: Colors.white,
      menuItemHoveredColor: Colors.grey.shade300,
      popupDecoration: const BoxDecoration(
          color: Colors.white,
          borderRadius: Sizes.borderRadiusCircular4,
          boxShadow: Styles.boxShadow),
      tabTextStyle: const TextStyle(color: Color.fromARGB(255, 89, 89, 89)),
      toastDecoration: const BoxDecoration(
        color: Colors.white,
        borderRadius: Sizes.borderRadiusCircular8,
        boxShadow: Styles.boxShadow,
      ),
      // hsl(0, 0%, 95%)
      homePageBackgroundColor: const Color.fromARGB(255, 243, 243, 243),
      // hsl(0, 0%, 93%)
      mainNavigationRailBackgroundColor:
          const Color.fromARGB(255, 237, 237, 237),
      // hsl(0, 0%, 42%)
      mainNavigationRailIconColor: const Color.fromARGB(255, 107, 107, 107),
      // hsl(0, 0%, 95%)
      subNavigationRailSearchBarBackgroundColor:
          const Color.fromARGB(255, 247, 247, 247),
      subNavigationRailLoadingIndicatorBackgroundColor:
          const Color.fromARGB(255, 237, 237, 237),
      subNavigationRailDividerColor: const Color.fromARGB(255, 213, 213, 213),
      chatSessionPaneDividerColor: const Color.fromARGB(255, 231, 231, 231),
      chatSessionDetailsDrawerBackgroundColor: Colors.white,
      chatSessionMessageTextStyle: const TextStyle(
        fontSize: 14,
      ),
      chatSessionMessageEmojiTextStyle: TextStyle(
          fontSize: 20,
          fontFamily: Fonts.emojiFontFamily,
          fontFamilyFallback: Fonts.emojiFontFamilyFallback),
      // hsl(0, 0%, 97%)
      tileBackgroundColor: const Color.fromARGB(255, 247, 247, 247),
      tileBackgroundHighlightedColor: const Color.fromARGB(255, 210, 210, 210),
      // hsl(0, 0%, 92%)
      tileBackgroundHoveredColor: const Color.fromARGB(255, 234, 234, 234),
      // hsl(0, 0%, 87%)
      tileBackgroundFocusedColor: const Color.fromARGB(255, 222, 222, 222),
      conversationTileMessageTextStyle: const TextStyle(
        color: AppColors.gray7,
        fontSize: 12,
      ),
      conversationTileDraftTextStyle: const TextStyle(color: Colors.red),
      conversationTileHighlightedTextStyle:
          TextStyle(backgroundColor: AppColors.primary.withValues(alpha: 0.3)),
      conversationTileTimestampTextStyle:
          const TextStyle(color: AppColors.gray7, fontSize: 12),
      messageAttachmentColor: const Color.fromARGB(255, 250, 250, 250),
      messageAttachmentHoveredColor: Colors.white,
      messageBubbleErrorIconBackgroundColor:
          const Color.fromARGB(255, 250, 81, 81),
      messageBubbleErrorIconColor: Colors.white,
      fileTableTitleTextStyle:
          const TextStyle(color: Color.fromARGB(255, 51, 51, 51)),
      fileTableCellTextStyle:
          const TextStyle(color: Color.fromARGB(255, 102, 102, 102)),
      settingPageSubNavigationRailDividerColor:
          const Color.fromARGB(255, 240, 240, 240),
      dialogTitleTextStyleMedium:
          TextStyle(fontSize: 14, color: Colors.grey.shade600),
      dialogTitleTextStyleLarge:
          TextStyle(fontSize: 16, color: Colors.grey.shade600),
      descriptionTextStyle: const TextStyle(
        // TODO: Or Color(0xA6000000)?
        color: Colors.grey,
      ),
      linkTextStyle: const TextStyle(
        color: AppColors.blue5,
      ),
      linkHoveredTextStyle: const TextStyle(
        color: AppColors.blue6,
      ));

  // TODO
  static final dark = light;

  final ThemeMode themeMode;

  // Semantic colors/styles
  final Color successColor;
  final Color warningColor;
  final Color errorColor;
  final Color infoColor;

  final Color dangerColor;
  final TextStyle dangerTextStyle;
  final TextStyle highlightTextStyle;

  // Background colors
  final Color maskColor;

  // Component colors/styles
  final Color avatarIconColor;
  final Color avatarBackgroundColor;

  final Color checkboxColor;
  final TextStyle checkboxTextStyle;

  final Color iconButtonContainerHoveredColor;
  final Color iconButtonContainerPressedColor;

  final BoxDecoration menuDecoration;
  final Color menuItemColor;
  final Color menuItemHoveredColor;
  final TextStyle menuItemTextStyle;

  final BoxDecoration popupDecoration;

  final TextStyle tabTextStyle;

  final BoxDecoration toastDecoration;

  // Page colors/styles
  final Color homePageBackgroundColor;
  final Color mainNavigationRailBackgroundColor;
  final Color mainNavigationRailIconColor;
  final Color subNavigationRailSearchBarBackgroundColor;
  final Color subNavigationRailLoadingIndicatorBackgroundColor;
  final Color subNavigationRailDividerColor;

  final Color chatSessionPaneDividerColor;
  final Color chatSessionDetailsDrawerBackgroundColor;
  final TextStyle chatSessionMessageTextStyle;
  final TextStyle chatSessionMessageEmojiTextStyle;

  final Color tileBackgroundColor;
  final Color tileBackgroundHighlightedColor;
  final Color tileBackgroundHoveredColor;
  final Color tileBackgroundFocusedColor;

  final TextStyle conversationTileMessageTextStyle;
  final TextStyle conversationTileDraftTextStyle;
  final TextStyle conversationTileHighlightedTextStyle;
  final TextStyle conversationTileTimestampTextStyle;

  final Color messageAttachmentColor;
  final Color messageAttachmentHoveredColor;
  final Color messageBubbleErrorIconBackgroundColor;
  final Color messageBubbleErrorIconColor;

  final TextStyle fileTableTitleTextStyle;
  final TextStyle fileTableCellTextStyle;

  final Color settingPageSubNavigationRailDividerColor;
  final TextStyle dialogTitleTextStyleMedium;
  final TextStyle dialogTitleTextStyleLarge;

  // Common text styles
  final TextStyle descriptionTextStyle;
  final TextStyle linkTextStyle;
  final TextStyle linkHoveredTextStyle;

  @override
  AppThemeExtension copyWith({
    ThemeMode? themeMode,
    Color? successColor,
    Color? warningColor,
    Color? errorColor,
    Color? infoColor,
    Color? dangerColor,
    TextStyle? dangerTextStyle,
    TextStyle? highlightTextStyle,
    Color? maskColor,
    Color? avatarIconColor,
    Color? avatarBackgroundColor,
    Color? checkboxColor,
    TextStyle? checkboxTextStyle,
    Color? iconButtonContainerHoveredColor,
    Color? iconButtonContainerPressedColor,
    BoxDecoration? menuDecoration,
    Color? menuItemColor,
    Color? menuItemHoveredColor,
    TextStyle? menuItemTextStyle,
    BoxDecoration? popupDecoration,
    TextStyle? tabTextStyle,
    BoxDecoration? toastDecoration,
    Color? homePageBackgroundColor,
    Color? mainNavigationRailBackgroundColor,
    Color? mainNavigationRailIconColor,
    Color? subNavigationRailSearchBarBackgroundColor,
    Color? subNavigationRailLoadingIndicatorBackgroundColor,
    Color? subNavigationRailDividerColor,
    Color? chatSessionPaneDividerColor,
    Color? chatSessionDetailsDrawerBackgroundColor,
    TextStyle? chatSessionMessageEditorTextStyle,
    TextStyle? chatSessionMessageEditorEmojiTextStyle,
    Color? tileBackgroundColor,
    Color? tileBackgroundHighlightedColor,
    Color? tileBackgroundHoveredColor,
    Color? tileBackgroundFocusedColor,
    TextStyle? conversationTileMessageTextStyle,
    TextStyle? conversationTileDraftTextStyle,
    TextStyle? conversationTileHighlightedTextStyle,
    TextStyle? conversationTileTimestampTextStyle,
    Color? messageAttachmentColor,
    Color? messageAttachmentHoveredColor,
    Color? messageBubbleErrorIconBackgroundColor,
    Color? messageBubbleErrorIconColor,
    TextStyle? fileTableTitleTextStyle,
    TextStyle? fileTableCellTextStyle,
    Color? settingPageSubNavigationRailDividerColor,
    TextStyle? dialogTitleTextStyleMedium,
    TextStyle? dialogTitleTextStyleLarge,
    TextStyle? descriptionTextStyle,
    TextStyle? linkTextStyle,
    TextStyle? linkHoveredTextStyle,
  }) =>
      AppThemeExtension(
        themeMode: themeMode ?? this.themeMode,
        successColor: successColor ?? this.successColor,
        warningColor: warningColor ?? this.warningColor,
        errorColor: errorColor ?? this.errorColor,
        infoColor: infoColor ?? this.infoColor,
        dangerColor: dangerColor ?? this.dangerColor,
        dangerTextStyle: dangerTextStyle ?? this.dangerTextStyle,
        highlightTextStyle: highlightTextStyle ?? this.highlightTextStyle,
        maskColor: maskColor ?? this.maskColor,
        avatarIconColor: avatarIconColor ?? this.avatarIconColor,
        avatarBackgroundColor:
            avatarBackgroundColor ?? this.avatarBackgroundColor,
        checkboxColor: checkboxColor ?? this.checkboxColor,
        checkboxTextStyle: checkboxTextStyle ?? this.checkboxTextStyle,
        iconButtonContainerHoveredColor: iconButtonContainerHoveredColor ??
            this.iconButtonContainerHoveredColor,
        iconButtonContainerPressedColor: iconButtonContainerPressedColor ??
            this.iconButtonContainerPressedColor,
        menuDecoration: menuDecoration ?? this.menuDecoration,
        menuItemColor: menuItemColor ?? this.menuItemColor,
        menuItemHoveredColor: menuItemHoveredColor ?? this.menuItemHoveredColor,
        menuItemTextStyle: menuItemTextStyle ?? this.menuItemTextStyle,
        popupDecoration: popupDecoration ?? this.popupDecoration,
        tabTextStyle: tabTextStyle ?? this.tabTextStyle,
        toastDecoration: toastDecoration ?? this.toastDecoration,
        homePageBackgroundColor:
            homePageBackgroundColor ?? this.homePageBackgroundColor,
        mainNavigationRailBackgroundColor: mainNavigationRailBackgroundColor ??
            this.mainNavigationRailBackgroundColor,
        mainNavigationRailIconColor:
            mainNavigationRailIconColor ?? this.mainNavigationRailIconColor,
        subNavigationRailSearchBarBackgroundColor:
            subNavigationRailSearchBarBackgroundColor ??
                this.subNavigationRailSearchBarBackgroundColor,
        subNavigationRailLoadingIndicatorBackgroundColor:
            subNavigationRailLoadingIndicatorBackgroundColor ??
                this.subNavigationRailLoadingIndicatorBackgroundColor,
        subNavigationRailDividerColor:
            subNavigationRailDividerColor ?? this.subNavigationRailDividerColor,
        chatSessionPaneDividerColor:
            chatSessionPaneDividerColor ?? this.chatSessionPaneDividerColor,
        chatSessionDetailsDrawerBackgroundColor:
            chatSessionDetailsDrawerBackgroundColor ??
                this.chatSessionDetailsDrawerBackgroundColor,
        chatSessionMessageTextStyle:
            chatSessionMessageEditorTextStyle ?? chatSessionMessageTextStyle,
        chatSessionMessageEmojiTextStyle:
            chatSessionMessageEditorEmojiTextStyle ??
                chatSessionMessageEmojiTextStyle,
        tileBackgroundColor: tileBackgroundColor ?? this.tileBackgroundColor,
        tileBackgroundHighlightedColor: tileBackgroundHighlightedColor ??
            this.tileBackgroundHighlightedColor,
        tileBackgroundHoveredColor:
            tileBackgroundHoveredColor ?? this.tileBackgroundHoveredColor,
        tileBackgroundFocusedColor:
            tileBackgroundFocusedColor ?? this.tileBackgroundFocusedColor,
        conversationTileMessageTextStyle: conversationTileMessageTextStyle ??
            this.conversationTileMessageTextStyle,
        conversationTileDraftTextStyle: conversationTileDraftTextStyle ??
            this.conversationTileDraftTextStyle,
        conversationTileHighlightedTextStyle:
            conversationTileHighlightedTextStyle ??
                this.conversationTileHighlightedTextStyle,
        conversationTileTimestampTextStyle:
            conversationTileTimestampTextStyle ??
                this.conversationTileTimestampTextStyle,
        messageAttachmentColor:
            messageAttachmentColor ?? this.messageAttachmentColor,
        messageAttachmentHoveredColor:
            messageAttachmentHoveredColor ?? this.messageAttachmentHoveredColor,
        messageBubbleErrorIconBackgroundColor:
            messageBubbleErrorIconBackgroundColor ??
                this.messageBubbleErrorIconBackgroundColor,
        messageBubbleErrorIconColor:
            messageBubbleErrorIconColor ?? this.messageBubbleErrorIconColor,
        fileTableTitleTextStyle:
            fileTableTitleTextStyle ?? this.fileTableTitleTextStyle,
        fileTableCellTextStyle:
            fileTableCellTextStyle ?? this.fileTableCellTextStyle,
        settingPageSubNavigationRailDividerColor:
            settingPageSubNavigationRailDividerColor ??
                this.settingPageSubNavigationRailDividerColor,
        dialogTitleTextStyleMedium:
            dialogTitleTextStyleMedium ?? this.dialogTitleTextStyleMedium,
        dialogTitleTextStyleLarge:
            dialogTitleTextStyleLarge ?? this.dialogTitleTextStyleLarge,
        descriptionTextStyle: descriptionTextStyle ?? this.descriptionTextStyle,
        linkTextStyle: linkTextStyle ?? this.linkTextStyle,
        linkHoveredTextStyle: linkHoveredTextStyle ?? this.linkHoveredTextStyle,
      );

  @override
  AppThemeExtension lerp(covariant AppThemeExtension? other, double t) {
    if (other is! AppThemeExtension) {
      return this;
    }
    return AppThemeExtension(
      themeMode: t < 0.5 ? themeMode : other.themeMode,
      successColor: Color.lerp(successColor, other.successColor, t)!,
      warningColor: Color.lerp(warningColor, other.warningColor, t)!,
      errorColor: Color.lerp(errorColor, other.errorColor, t)!,
      infoColor: Color.lerp(infoColor, other.infoColor, t)!,
      dangerColor: Color.lerp(dangerColor, other.dangerColor, t)!,
      dangerTextStyle:
          TextStyle.lerp(dangerTextStyle, other.dangerTextStyle, t)!,
      highlightTextStyle:
          TextStyle.lerp(highlightTextStyle, other.highlightTextStyle, t)!,
      maskColor: Color.lerp(maskColor, other.maskColor, t)!,
      avatarIconColor: Color.lerp(avatarIconColor, other.avatarIconColor, t)!,
      avatarBackgroundColor:
          Color.lerp(avatarBackgroundColor, other.avatarBackgroundColor, t)!,
      checkboxColor: Color.lerp(checkboxColor, other.checkboxColor, t)!,
      checkboxTextStyle:
          TextStyle.lerp(checkboxTextStyle, other.checkboxTextStyle, t)!,
      iconButtonContainerHoveredColor: Color.lerp(
          iconButtonContainerHoveredColor,
          other.iconButtonContainerHoveredColor,
          t)!,
      iconButtonContainerPressedColor: Color.lerp(
          iconButtonContainerPressedColor,
          other.iconButtonContainerPressedColor,
          t)!,
      menuDecoration:
          BoxDecoration.lerp(menuDecoration, other.menuDecoration, t)!,
      menuItemColor: Color.lerp(menuItemColor, other.menuItemColor, t)!,
      menuItemHoveredColor:
          Color.lerp(menuItemHoveredColor, other.menuItemHoveredColor, t)!,
      menuItemTextStyle:
          TextStyle.lerp(menuItemTextStyle, other.menuItemTextStyle, t)!,
      popupDecoration:
          BoxDecoration.lerp(popupDecoration, other.popupDecoration, t)!,
      tabTextStyle: TextStyle.lerp(tabTextStyle, other.tabTextStyle, t)!,
      toastDecoration:
          BoxDecoration.lerp(toastDecoration, other.toastDecoration, t)!,
      homePageBackgroundColor: Color.lerp(
          homePageBackgroundColor, other.homePageBackgroundColor, t)!,
      mainNavigationRailBackgroundColor: Color.lerp(
          mainNavigationRailBackgroundColor,
          other.mainNavigationRailBackgroundColor,
          t)!,
      mainNavigationRailIconColor: Color.lerp(
          mainNavigationRailIconColor, other.mainNavigationRailIconColor, t)!,
      subNavigationRailSearchBarBackgroundColor: Color.lerp(
          subNavigationRailSearchBarBackgroundColor,
          other.subNavigationRailSearchBarBackgroundColor,
          t)!,
      subNavigationRailLoadingIndicatorBackgroundColor: Color.lerp(
          subNavigationRailLoadingIndicatorBackgroundColor,
          other.subNavigationRailLoadingIndicatorBackgroundColor,
          t)!,
      subNavigationRailDividerColor: Color.lerp(subNavigationRailDividerColor,
          other.subNavigationRailDividerColor, t)!,
      chatSessionPaneDividerColor: Color.lerp(
          chatSessionPaneDividerColor, other.chatSessionPaneDividerColor, t)!,
      chatSessionDetailsDrawerBackgroundColor: Color.lerp(
          chatSessionDetailsDrawerBackgroundColor,
          other.chatSessionDetailsDrawerBackgroundColor,
          t)!,
      chatSessionMessageTextStyle: TextStyle.lerp(
          chatSessionMessageTextStyle, other.chatSessionMessageTextStyle, t)!,
      chatSessionMessageEmojiTextStyle: TextStyle.lerp(
          chatSessionMessageEmojiTextStyle,
          other.chatSessionMessageEmojiTextStyle,
          t)!,
      tileBackgroundColor:
          Color.lerp(tileBackgroundColor, other.tileBackgroundColor, t)!,
      tileBackgroundHighlightedColor: Color.lerp(tileBackgroundHighlightedColor,
          other.tileBackgroundHighlightedColor, t)!,
      tileBackgroundHoveredColor: Color.lerp(
          tileBackgroundHoveredColor, other.tileBackgroundHoveredColor, t)!,
      tileBackgroundFocusedColor: Color.lerp(
          tileBackgroundFocusedColor, other.tileBackgroundFocusedColor, t)!,
      conversationTileMessageTextStyle: TextStyle.lerp(
          conversationTileMessageTextStyle,
          other.conversationTileMessageTextStyle,
          t)!,
      conversationTileDraftTextStyle: TextStyle.lerp(
          conversationTileDraftTextStyle,
          other.conversationTileDraftTextStyle,
          t)!,
      conversationTileHighlightedTextStyle: TextStyle.lerp(
          conversationTileHighlightedTextStyle,
          other.conversationTileHighlightedTextStyle,
          t)!,
      conversationTileTimestampTextStyle: TextStyle.lerp(
          conversationTileTimestampTextStyle,
          other.conversationTileTimestampTextStyle,
          t)!,
      messageAttachmentColor:
          Color.lerp(messageAttachmentColor, other.messageAttachmentColor, t)!,
      messageAttachmentHoveredColor: Color.lerp(messageAttachmentHoveredColor,
          other.messageAttachmentHoveredColor, t)!,
      messageBubbleErrorIconBackgroundColor: Color.lerp(
          messageBubbleErrorIconBackgroundColor,
          other.messageBubbleErrorIconBackgroundColor,
          t)!,
      messageBubbleErrorIconColor: Color.lerp(
          messageBubbleErrorIconColor, other.messageBubbleErrorIconColor, t)!,
      fileTableTitleTextStyle: TextStyle.lerp(
          fileTableTitleTextStyle, other.fileTableTitleTextStyle, t)!,
      fileTableCellTextStyle: TextStyle.lerp(
          fileTableCellTextStyle, other.fileTableCellTextStyle, t)!,
      settingPageSubNavigationRailDividerColor: Color.lerp(
          settingPageSubNavigationRailDividerColor,
          other.settingPageSubNavigationRailDividerColor,
          t)!,
      dialogTitleTextStyleMedium: TextStyle.lerp(
          dialogTitleTextStyleMedium, other.dialogTitleTextStyleMedium, t)!,
      dialogTitleTextStyleLarge: TextStyle.lerp(
          dialogTitleTextStyleLarge, other.dialogTitleTextStyleLarge, t)!,
      descriptionTextStyle:
          TextStyle.lerp(descriptionTextStyle, other.descriptionTextStyle, t)!,
      linkTextStyle: TextStyle.lerp(linkTextStyle, other.linkTextStyle, t)!,
      linkHoveredTextStyle:
          TextStyle.lerp(linkHoveredTextStyle, other.linkHoveredTextStyle, t)!,
    );
  }
}

extension BuildContextExtension on BuildContext {
  ThemeData get theme => Theme.of(this);

  AppThemeExtension get appThemeExtension =>
      Theme.of(this).extension<AppThemeExtension>()!;
}

extension ThemeDataExtension on ThemeData {
  AppThemeExtension get appThemeExtension => extension<AppThemeExtension>()!;
}
