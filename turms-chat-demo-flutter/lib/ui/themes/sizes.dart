import 'package:flutter/widgets.dart';

import '../desktop/components/t_divider/t_vertical_divider.dart';
import '../desktop/components/t_list_tile/t_list_tile.dart';

/// Principals:
/// The 2x Grid is the geometric foundation of all the visual elements
class Sizes {
  const Sizes._();

  static const unit = 2;

  // Space
  static const paddingH1 = EdgeInsets.symmetric(horizontal: 1);
  static const paddingV2 = EdgeInsets.symmetric(vertical: 2);
  static const paddingH2 = EdgeInsets.symmetric(horizontal: 2);
  static const paddingV2H4 = EdgeInsets.symmetric(vertical: 2, horizontal: 4);
  static const paddingV4 = EdgeInsets.symmetric(vertical: 4);
  static const paddingV4H8 = EdgeInsets.symmetric(vertical: 4, horizontal: 8);
  static const paddingV4H4 = EdgeInsets.symmetric(vertical: 4, horizontal: 4);
  static const paddingH4 = EdgeInsets.symmetric(horizontal: 4);
  static const paddingV8 = EdgeInsets.symmetric(vertical: 8);
  static const paddingV8H8 = EdgeInsets.symmetric(vertical: 8, horizontal: 8);
  static const paddingV8H16 = EdgeInsets.symmetric(vertical: 8, horizontal: 16);
  static const paddingH12 = EdgeInsets.symmetric(horizontal: 12);
  static const paddingV16 = EdgeInsets.symmetric(vertical: 16);
  static const paddingH16 = EdgeInsets.symmetric(horizontal: 16);
  static const paddingV16H8 = EdgeInsets.symmetric(vertical: 16, horizontal: 8);
  static const paddingV16H16 =
      EdgeInsets.symmetric(vertical: 16, horizontal: 16);

  static const paddingH8 = EdgeInsets.symmetric(horizontal: 8);

  // Radius
  static const borderRadius0 = BorderRadius.zero;
  static const borderRadiusCircular2 = BorderRadius.all(Radius.circular(2));
  static const borderRadiusCircular4 = BorderRadius.all(Radius.circular(4));
  static const borderRadiusCircular8 = BorderRadius.all(Radius.circular(8));

  // Sized boxes
  static const sizedBox0 = SizedBox.shrink();
  static const sizedBoxInfinity = SizedBox.expand();
  static const sizedBoxW4 = SizedBox(width: 4);
  static const sizedBoxH4 = SizedBox(height: 4);
  static const sizedBoxW8 = SizedBox(width: 8);
  static const sizedBoxH8 = SizedBox(height: 8);
  static const sizedBoxH12 = SizedBox(height: 12);
  static const sizedBoxW16 = SizedBox(width: 16);
  static const sizedBoxH16 = SizedBox(height: 16);
  static const sizedBoxH32 = SizedBox(height: 32);

  // Components
  static const alertWidth = 320.0;
  static const alertHeight = 140.0;

  static const dialogWidthMedium = 552.0;
  static const dialogHeightMedium = 472.0;

  static const dateRangePickerWidth = 576.0;
  static const dateRangePickerHeight = 312.0;

  // Application
  static const mainNavigationRailWidth = 56.0;
  static const subNavigationRailWidth = 248.0;
  static const subNavigationRailMinWidth = 240.0;
  static const subNavigationRailMaxWidth = 480.0;
  static const subNavigationRailPadding = Sizes.paddingH12;
  static const subNavigationRailDividerSize =
      TMovableVerticalDividerSize.medium;
  static const homePageHeaderHeight = defaultListTile;
  static const conversationTileHeight = defaultListTile;

  static const mainNavigationRailElementPopupOffsetX = 4.0;

  static const userProfilePopupWidth = 280.0;
  static const userProfilePopupHeight = 160.0;

  static const aboutPageWidth = 440.0;
  static const aboutPageHeight = 300.0;

  static const stickerPickerWidth = 460.0;
  static const stickerPickerHeight = 460.0;

  static const chatSessionPaneFooterHeight = 240.0;
  static const chatSessionPaneFooterMinHeight = 128.0;
  static const chatSessionPaneFooterMaxHeight = 512.0;
  static const chatHistoryDialogWidth = 696.0;
  static const chatHistoryDialogHeight = 640.0;

  static const userProfileImageDialogWidth = 520.0;
  static const userProfileImageDialogHeight = 440.0;

  static const friendRequestDialogWidth = 400.0;
  static const friendRequestDialogHeight = 300.0;

  static const titleBarSize = Size(36, 28);
}
